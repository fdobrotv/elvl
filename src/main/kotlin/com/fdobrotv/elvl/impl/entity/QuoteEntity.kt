package com.fdobrotv.elvl.impl.entity

import java.math.BigDecimal
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "quote")
class QuoteEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = null,
        var isin: String? = null,
        var bid: BigDecimal? = null,
        var ask: BigDecimal? = null
)