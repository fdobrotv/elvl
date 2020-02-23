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
        var isin: String? = null,
        var bid: BigDecimal? = null,
        var ask: BigDecimal? = null,
        @CreatedDate
        val createdAt: Date = Date()
)