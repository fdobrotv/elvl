package com.fdobrotv.elvl.impl.integration

import com.fdobrotv.elvl.impl.mapper.toEntity
import com.fdobrotv.elvl.impl.repository.QuoteRepository
import com.fdobrotv.elvl.impl.util.bigDecimal
import com.fdobrotv.elvl.impl.util.quoteOne
import com.fdobrotv.elvl.impl.util.quoteTwo
import com.fdobrotv.elvl.model.QuoteIn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional


@DataJpaTest(properties = ["spring.h2.console.enabled=true"])
@Transactional(propagation = Propagation.NOT_SUPPORTED)
class RepositoryTest {
    @Autowired
    lateinit var quoteRepository: QuoteRepository

    @Test
    fun injectedComponentsAreNotNull() {
        assertThat(quoteRepository).isNotNull
    }

    @Test
    @Throws(Exception::class)
    fun persistenceTest() {
        quoteRepository.save(quoteOne.toEntity())

        val quoteEntity = quoteRepository.findByIsin(quoteOne.isin)
        Assertions.assertEquals(quoteOne.isin, quoteEntity.get().isin)
    }

    @Test
    @Throws(Exception::class)
    fun shouldGetLastUniqueByIsin() {
        val dataSet =
                listOf(
                        quoteOne,
                        quoteTwo,
                        QuoteIn().isin("RU100A0JX0J2").bid(bigDecimal(55.2)).ask(bigDecimal(44.2)),
                        QuoteIn().isin("RU100A0JX0J2").bid(bigDecimal(54.2)).ask(bigDecimal(43.2))
                ).map { it.toEntity() }

        dataSet.map {
            Thread.sleep(100)
            println(quoteRepository.save(quoteOne.toEntity()).createdAt.toInstant())
        }

        val quoted = quoteRepository.findFirstByIsinOrderByCreatedAtDesc("RU000A0JX0J2")
        Assertions.assertEquals("RU000A0JX0J2", quoted.get().isin)
        Assertions.assertEquals(100.5, quoted.get().bid)
    }
}