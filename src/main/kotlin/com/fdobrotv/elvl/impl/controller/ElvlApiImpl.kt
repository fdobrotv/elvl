package com.fdobrotv.elvl.impl.controller

import com.fdobrotv.elvl.api.ElvlsApi
import com.fdobrotv.elvl.impl.service.ElvlService
import com.fdobrotv.elvl.model.Elvl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class ElvlsApiImpl(
        private val elvlService: ElvlService
) : ElvlsApi {
    override fun getAllElvls(): ResponseEntity<MutableList<Elvl>> {
        return ResponseEntity.ok(elvlService.getAll().toMutableList())
    }

    override fun getElvlByIsin(isin: String): ResponseEntity<Elvl> {
        return elvlService.getByIsin(isin)
                .map { ResponseEntity.ok(it) }
                .orElseGet { ResponseEntity.notFound().build() }
    }
}