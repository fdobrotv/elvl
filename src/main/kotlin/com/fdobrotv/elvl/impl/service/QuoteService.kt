package com.fdobrotv.elvl.impl.service

import com.fdobrotv.elvl.impl.mapper.toDTO
import com.fdobrotv.elvl.impl.mapper.toEntity
import com.fdobrotv.elvl.impl.repository.QuoteRepository
import com.fdobrotv.elvl.model.Quote
import org.springframework.stereotype.Service
import java.math.BigDecimal

interface QuoteService {
    fun getAll(): List<Quote>
    fun create(quote: Quote): Quote
}

@Service
class QuoteServiceImpl(
        private val quoteRepository: QuoteRepository
) : QuoteService {

    lateinit var elvl: BigDecimal

    init {
        elvl = quoteRepository.findFirstByOrderByCreatedAtDesc().map {
            it.bid ?: it.ask ?: BigDecimal.ZERO
        }.orElseGet {
            BigDecimal.ZERO
        }
    }

    override fun getAll(): List<Quote> {
        return quoteRepository.findAll().map { it.toDTO() }
    }

    override fun create(quote: Quote): Quote {
        //TODO: add validation with using of @Validate (bid должен быть меньше ask && isin – 12 символов)

        if (quote.bid > elvl) {
            elvl = quote.bid
        } else if (quote.ask < elvl) {
            elvl = quote.ask
        }

        return quoteRepository.save(quote.toEntity()).toDTO()
    }
}