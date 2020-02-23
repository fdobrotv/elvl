package com.fdobrotv.elvl.impl.service

import com.fdobrotv.elvl.impl.mapper.toDTO
import com.fdobrotv.elvl.impl.mapper.toEntity
import com.fdobrotv.elvl.impl.repository.QuoteRepository
import com.fdobrotv.elvl.model.Quote
import org.springframework.stereotype.Service

interface QuoteService {
    fun getAll(): List<Quote>
    fun create(quote: Quote): Quote
}

@Service
class QuoteServiceImpl(
        private val quoteRepository: QuoteRepository
) : QuoteService {
    override fun getAll(): List<Quote> {
        return quoteRepository.findAll().map { it.toDTO() }
    }

    override fun create(quote: Quote): Quote {
        return quoteRepository.save(quote.toEntity()).toDTO()
    }
}