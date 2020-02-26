package com.fdobrotv.elvl.impl.service

import com.fdobrotv.elvl.impl.repository.QuoteRepository
import com.fdobrotv.elvl.model.Elvl
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.*


interface ElvlService {
    fun getAll(): List<Elvl>
    fun getByIsin(isin: String): Optional<Elvl>
}

@Service
class ElvlServiceImpl(private val quoteRepository: QuoteRepository) : ElvlService {
    override fun getAll(): List<Elvl> {
        return quoteRepository.findAll().map { Elvl().isin(it.isin).price(it.elvl) }
    }

    override fun getByIsin(isin: String): Optional<Elvl> {
        return quoteRepository.findFirstByIsinOrderByCreatedAtDesc(isin).map {
            Elvl().isin(isin).price(it.bid ?: it.ask ?: BigDecimal.ZERO)
        }
    }
}