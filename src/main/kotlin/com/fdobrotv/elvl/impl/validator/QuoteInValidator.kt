package com.fdobrotv.elvl.impl.validator

import com.fdobrotv.elvl.model.QuoteIn
import org.springframework.validation.Errors
import org.springframework.validation.Validator

class QuoteInValidator : Validator {
    override fun validate(target: Any, errors: Errors) {
        val quoteIn: QuoteIn = target as QuoteIn

        if (quoteIn.bid != null && quoteIn.bid >= quoteIn.ask) {
            errors.rejectValue("bid", "bid.must.be.less.than.ask");
        }
    }

    override fun supports(clazz: Class<*>): Boolean {
        return QuoteIn::class.java == clazz
    }
}