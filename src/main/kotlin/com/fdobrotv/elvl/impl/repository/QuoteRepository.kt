package com.fdobrotv.elvl.impl.repository

import com.fdobrotv.elvl.impl.entity.QuoteEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface QuoteRepository: CrudRepository<QuoteEntity, UUID> {
    fun findFirstByOrderByCreatedAtDesc(): Optional<QuoteEntity>
}