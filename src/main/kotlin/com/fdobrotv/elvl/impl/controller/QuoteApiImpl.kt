package com.fdobrotv.elvl.impl.controller

import com.fdobrotv.elvl.api.QuotesApi
import com.fdobrotv.elvl.impl.service.QuoteService
import com.fdobrotv.elvl.model.Quote
import com.fdobrotv.elvl.model.QuoteIn
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
class QuoteApiImpl(
        private val quoteService: QuoteService
) : QuotesApi {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handle(e: HttpMessageNotReadableException?) {
        logger.warn("Returning HTTP 400 Bad Request", e)
    }

    override fun getAllQuotes(): ResponseEntity<MutableList<Quote>> {
        return ResponseEntity.ok(quoteService.getAll().toMutableList())
    }

    override fun createQuote(quote: QuoteIn): ResponseEntity<Quote> {
        return ResponseEntity.ok(quoteService.create(quote))
    }
}