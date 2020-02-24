package com.fdobrotv.elvl.impl.util

import com.fdobrotv.elvl.model.QuoteIn

val quoteOne = QuoteIn()
        .isin("RU000A0JX0J2")
        .bid(bigDecimal(100.2))
        .ask(bigDecimal(101.9))

val quoteTwo = QuoteIn()
        .isin("RU000A0JX0J2")
        .bid(bigDecimal(100.5))
        .ask(bigDecimal(101.9))