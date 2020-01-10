package mesi.capitaltracker.service

import junit.framework.Assert
import mesi.capitaltracker.dao.*
import mesi.capitaltracker.dao.Currency
import mesi.capitaltracker.utility.InvestmentNotFoundException
import mesi.capitaltracker.utility.InvestorNotFoundException
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
class TransactionServiceTest {

    @TestConfiguration
    internal class ContextProvider {
        @Bean
        fun service(): TransactionService {
            return TransactionService()
        }
    }

    @Autowired
    private lateinit var service : TransactionService

    @MockBean
    private lateinit var repo : TransactionRepository

    @MockBean
    private lateinit var investorService: InvestorService
    @MockBean
    private lateinit var investmentService: InvestmentService

    private val investor = Investor(1, "rico")
    private val investment : Investment = GoldCoin(1, "goldcoin", "man", 1.0)

    @Test
    fun testAddValidIncommingTransaction() {
        Mockito.`when`(investorService.get(1)).thenReturn(investor)
        Mockito.`when`(investmentService.get(1)).thenReturn(investment)

        val transaction = Transaction(1, LocalDate.MIN, 1.0, Currency.USD)
        Mockito.`when`(repo.save(transaction)).thenReturn(transaction)
        val storedTransaction = service.addIncomingTransaction(investor.id, investment.id, transaction)

        assertNotNull(investor.incomingTransactions.first())
        assertEquals(storedTransaction, investor.incomingTransactions.first())
    }

    @Test
    fun testAddIncommingTransactionWithInvalidInvestor() {
        Mockito.`when`(investorService.get(1)).thenReturn(null)
        Mockito.`when`(investmentService.get(1)).thenReturn(investment)

        val transaction = Transaction(1, LocalDate.MIN, 1.0, Currency.USD)
        Mockito.`when`(repo.save(transaction)).thenReturn(transaction)

        assertThrows<InvestorNotFoundException> { service.addIncomingTransaction(investor.id, investment.id, transaction) }
    }

    @Test
    fun testAddIncommingTransactionWithInvalidInvestment() {
        Mockito.`when`(investorService.get(1)).thenReturn(investor)
        Mockito.`when`(investmentService.get(1)).thenReturn(null)

        val transaction = Transaction(1, LocalDate.MIN, 1.0, Currency.USD)
        Mockito.`when`(repo.save(transaction)).thenReturn(transaction)

        assertThrows<InvestmentNotFoundException> { service.addIncomingTransaction(investor.id, investment.id, transaction) }
    }
}