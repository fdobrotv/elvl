package com.fdobrotv.elvl.impl.repository

import com.fdobrotv.elvl.impl.entity.QuoteEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuoteRepository: CrudRepository<QuoteEntity, UUID> {
    fun findFirstByOrderByCreatedAtDesc(): Optional<QuoteEntity>

    fun findFirstByIsinOrderByCreatedAtDesc(isin: String): Optional<QuoteEntity>

    fun findByIsin(isin: String): Optional<QuoteEntity>


    @Query("SELECT DISTINCT isin, MAX(created_at) \n" +
            "FROM quote \n" +
            "GROUP BY isin \n" +
            "ORDER BY MAX(created_at) DESC, isin" +
            "WHERE m.isin = :isin",
            nativeQuery = true)
    fun findByIsin2(@Param("isin") isin: String): List<QuoteEntity>
}