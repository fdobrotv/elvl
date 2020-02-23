package com.fdobrotv.elvl.impl.controller

import com.fdobrotv.elvl.api.QuotesApi
import com.fdobrotv.elvl.impl.service.QuoteService
import com.fdobrotv.elvl.model.Quote
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class QuoteController(
        private val quoteService: QuoteService
) : QuotesApi {
    override fun getAllQuotes(): ResponseEntity<MutableList<Quote>> {
        return super.getAllQuotes()
    }

    override fun createQuote(quote: Quote): ResponseEntity<Void> {
        return super.createQuote(quote)
    }
}