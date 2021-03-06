package com.fdobrotv.elvl.impl.service

import com.fdobrotv.elvl.impl.mapper.toDTO
import com.fdobrotv.elvl.impl.mapper.toEntity
import com.fdobrotv.elvl.impl.repository.QuoteRepository
import com.fdobrotv.elvl.model.Quote
import com.fdobrotv.elvl.model.QuoteIn
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

interface QuoteService {
    fun getAll(): List<Quote>
    fun create(quote: QuoteIn): Quote
}

@Service
class QuoteServiceImpl(
        private val quoteRepository: QuoteRepository
) : QuoteService {

    @Transactional(readOnly = true)
    override fun getAll(): List<Quote> {
        return quoteRepository.findAll().map { it.toDTO() }
    }

    @Transactional
    override fun create(quote: QuoteIn): Quote {
        val calculatedElvl: BigDecimal = calculateElvl(quote)

        return quoteRepository.save(quote.toEntity(calculatedElvl)).toDTO()
    }

    private fun calculateElvl(quote: QuoteIn): BigDecimal {
        return quoteRepository.findFirstByOrderByCreatedAtDesc().map {
            if (quote.bid == null) {
                quote.ask
            } else {
                when {
                    quote.bid > it.elvl -> {
                        quote.bid
                    }
                    quote.ask < it.elvl -> {
                        quote.ask
                    }
                    else -> {
                        //TODO: We have trouble there if elvl == bid or ask
                        // Add a test for this
                        //throw IllegalStateException("elvl == bid or ask")
                        quote.bid
                    }
                }
            }
        }.orElseGet { quote.bid ?: quote.ask }
    }
}