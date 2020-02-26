//package com.fdobrotv.elvl.impl.integration
//
//import com.fdobrotv.elvl.impl.mapper.toEntity
//import com.fdobrotv.elvl.impl.repository.QuoteRepository
//import com.fdobrotv.elvl.impl.util.bigDecimal
//import com.fdobrotv.elvl.impl.util.quoteOne
//import com.fdobrotv.elvl.impl.util.quoteTwo
//import com.fdobrotv.elvl.model.QuoteIn
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import mu.KotlinLogging
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
//import org.springframework.transaction.annotation.Propagation
//import org.springframework.transaction.annotation.Transactional
//import java.util.*
//import kotlin.random.Random.Default.nextLong
//
//private val logger = KotlinLogging.logger {}
//
//@DataJpaTest(properties = ["spring.h2.console.enabled=true"])
////@Transactional(propagation = Propagation.NESTED)
//class RepositoryTest {
//    @Autowired
//    lateinit var quoteRepository: QuoteRepository
//
////    @Autowired
////    lateinit var entityManager: TestEntityManager
//
//    @Test
//    fun injectedComponentsAreNotNull() {
//        assertThat(quoteRepository).isNotNull
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun persistenceTest() {
//        quoteRepository.save(quoteOne.toEntity(quoteOne.bid))
//
//        val quoteEntity = quoteRepository.findByIsin(quoteOne.isin)
//        Assertions.assertEquals(quoteOne.isin, quoteEntity.get().isin)
//    }
//
//    @Test
//    @Throws(Exception::class)
//    fun shouldGetLastUniqueByIsin() {
//        val dataSet =
//                listOf(
//                        quoteOne,
//                        quoteTwo,
//                        QuoteIn().isin("RU100A0JX0J2").bid(bigDecimal(55.2)).ask(bigDecimal(44.2)),
//                        QuoteIn().isin("RU100A0JX0J2").bid(bigDecimal(54.2)).ask(bigDecimal(43.2))
//                ).map { it.toEntity(it.bid) }
//
//        dataSet.map {
//            Thread.sleep(nextLong(100, 1000))
//            println(quoteRepository.saveAndFlush(it).createdAt.toInstant())
//        }
//
//        Thread.sleep(4000)
//
//        logger.info { quoteRepository.findAll() }
//
//        val quoted = quoteRepository.findTopByIsinOrderByCreatedAtDesc("RU000A0JX0J2")
//        Assertions.assertEquals("RU000A0JX0J2", quoted.get().isin)
//        Assertions.assertEquals(100.5, quoted.get().bid)
//    }
//}