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
class StockTransactionServiceTest {

    @Autowired
    private lateinit var transactionService : StockTransactionService

    @MockBean
    private lateinit var repo : StockTransactionRepo

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
    fun testGetShareInvestmentOverviewForUser() {
        val transactions = listOf(provideTransaction(), provideTransaction())
        Mockito.`when`(transactionService.getTransactionsForUser(1)).thenReturn(transactions)

        // stock price + fee
        val invested = (300.0 * 4) + (5.0 * 2)
        // num of stocks * stockprice
        val current = 100.0 * 4
        val overview = transactionService.getShareInvestmentOverviewForUser(1, "AAPL", "EUR")

        assertEquals(invested, overview.invested)
        assertEquals(current, overview.current)
    }

    @TestConfiguration
    internal class ContextProvider {
        @Bean
        fun service(): StockTransactionService {
            return StockTransactionService()
        }

        @Bean
        fun financeDao(): FinanceDao {
            return object : FinanceDao {
                override fun getGoldPriceInUsd(): Double = 1.5
                override fun getExchangeRate(from: String, to: String): Double = 1.25
                override fun getStockPrice(symbol: String): Double = 100.0
                override fun getStockCurrency(symbol: String): String = "EUR"
            }
        }
    }

    private fun provideTransaction() = StockTransaction(1, "AAPL", investor, LocalDate.MIN, 300.0, 2, 5.0, "EUR")
}