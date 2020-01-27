package mesi.capitaltracker.service

import junit.framework.Assert.assertEquals
import mesi.capitaltracker.dao.*
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

    @Test
    fun testGetTransactionsForUser() {
        val transactions = listOf(provideTransaction(), provideTransaction())
        Mockito.`when`(repo.findAll()).thenReturn(transactions)

        val actual = transactionService.getTransactionsForUser(1)
        assertEquals(transactions, actual)
    }

    @Test
    fun testGetInvestmentOverviewForUser() {
        val transactions = listOf(provideTransaction(), provideTransaction())
        Mockito.`when`(transactionService.getTransactionsForUser(1)).thenReturn(transactions)

        // invested + fees
        val invested = (1.0 + 1.0) + (0.1 + 0.1)
        // ounces * current price * exchagne rate - fees
        val current = (1.0 + 1.0) * 1.5 * 1.25
        val overview = transactionService.getInvestmentOverviewForUser(1)

        assertEquals(invested, overview.invested)
        assertEquals(current, overview.current)
    }

    @TestConfiguration
    internal class ContextProvider {
        @Bean
        fun service(): GoldTransactionService {
            return GoldTransactionService()
        }

        @Bean
        fun financeDao(): FinanceDao {
            return object : FinanceDao {
                override fun getResourcePrice(name: String): Double = 1.5
                override fun getExchangeRate(from: String, to: String): Double = 1.25
            }
        }
    }

    private fun provideTransaction() = GoldTransaction(1, investor, LocalDate.MIN, 1.0, 0.1, 1.0)
}