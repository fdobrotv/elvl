package com.fdobrotv.elvl.impl.repository

import com.fdobrotv.elvl.impl.entity.QuoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuoteRepository: JpaRepository<QuoteEntity, UUID> {
    fun findFirstByOrderByCreatedAtDesc(): Optional<QuoteEntity>

    fun findFirstByIsinOrderByCreatedAtDesc(isin: String): Optional<QuoteEntity>

    fun findByIsin(isin: String): Optional<QuoteEntity>

    fun findTopByIsinOrderByCreatedAtDesc(isin: String) : Optional<QuoteEntity>
}