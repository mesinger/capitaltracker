package mesi.capitaltracker.service

import junit.framework.Assert.assertEquals
import mesi.capitaltracker.dao.GoldTransaction
import mesi.capitaltracker.dao.GoldTransactionRepo
import mesi.capitaltracker.dao.Investor
import mesi.capitaltracker.util.InvestorNotFoundException
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@RunWith(SpringRunner::class)
@DataJpaTest
class GoldTransactionServiceTest {

    @Autowired
    private lateinit var transactionService : GoldTransactionService

    @MockBean
    private lateinit var repo : GoldTransactionRepo

    @MockBean
    private lateinit var investorService : InvestorService

    private val investor = Investor(1, "name")

    @Test
    fun testAddTransactionForUser() {
        Mockito.`when`(investorService.get(1)).thenReturn(investor)
        val transaction = provideTransaction()
        Mockito.`when`(repo.save(transaction)).thenReturn(transaction.also { it.investor = investor })

        val savedTransaction = transactionService.addTransactionForUser(1, transaction)

        verify(repo, times(1)).save(transaction)
        assertEquals(transaction, savedTransaction)
    }

    @Test
    fun testAddTransactionForUnknownUser() {
        Mockito.`when`(investorService.get(1)).thenThrow(InvestorNotFoundException::class.java)
        assertThrows<InvestorNotFoundException> { transactionService.addTransactionForUser(1, provideTransaction()) }
    }

    @TestConfiguration
    internal class ContextProvider {
        @Bean
        fun service(): GoldTransactionService {
            return GoldTransactionService()
        }
    }

    private fun provideTransaction() = GoldTransaction(1, null, LocalDate.MIN, 1.0, 0.1)
}