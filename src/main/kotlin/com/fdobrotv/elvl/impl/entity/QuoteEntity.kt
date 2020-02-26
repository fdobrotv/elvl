package com.fdobrotv.elvl.impl.entity

import org.springframework.data.annotation.CreatedDate
import java.math.BigDecimal
import java.util.*
import javax.persistence.*


//TODO: move id and createdAt into Base entity
@Entity
@Table(name = "quote")
class QuoteEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: UUID? = null,
        val isin: String,
        val bid: BigDecimal? = null,
        val ask: BigDecimal,
        var elvl: BigDecimal = bid ?: ask,

        @CreatedDate
        val createdAt: Date = Date()
)