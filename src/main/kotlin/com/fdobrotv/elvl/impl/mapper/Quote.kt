package com.fdobrotv.elvl.impl.mapper

import com.fdobrotv.elvl.impl.entity.QuoteEntity
import com.fdobrotv.elvl.model.Quote


fun Quote.toEntity(): QuoteEntity {
    return QuoteEntity(
            isin = isin,
            bid = bid,
            ask = ask
    )
}

fun QuoteEntity.toDTO(): Quote {
    return Quote()
            .id(id)
            .isin(isin)
            .bid(bid)
            .ask(ask)
}