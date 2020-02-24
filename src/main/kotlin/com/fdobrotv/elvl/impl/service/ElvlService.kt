package com.fdobrotv.elvl.impl.service

import com.fdobrotv.elvl.impl.repository.QuoteRepository
import com.fdobrotv.elvl.model.Elvl
import org.springframework.stereotype.Service
import java.math.BigDecimal


interface ElvlService {
    fun getAll(): List<Elvl>
    fun getByIsin(isin: String): Elvl
}

@Service
class ElvlServiceImpl (private val quoteRepository: QuoteRepository) : ElvlService {
    override fun getAll(): List<Elvl> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getByIsin(isin: String): Elvl {
        val elvl = quoteRepository.findFirstByOrderByCreatedAtDescAndIsin(isin)
                .map { it.bid ?: it.ask ?: BigDecimal.ZERO }
                .orElseGet { BigDecimal.ZERO }
        return Elvl().isin(isin).price(elvl)
    }
}