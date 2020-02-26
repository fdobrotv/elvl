package com.fdobrotv.elvl.impl.mapper

import com.fdobrotv.elvl.impl.entity.QuoteEntity
import com.fdobrotv.elvl.model.Quote
import com.fdobrotv.elvl.model.QuoteIn
import java.math.BigDecimal


fun QuoteIn.toEntity(elvl: BigDecimal): QuoteEntity {
    return QuoteEntity(
            isin = isin,
            bid = bid,
            ask = ask,
            elvl = elvl
    )
}

fun QuoteEntity.toDTO(): Quote {
    return Quote()
            .id(id)
            .isin(isin)
            .bid(bid)
            .ask(ask)
            .elvl(elvl)
}