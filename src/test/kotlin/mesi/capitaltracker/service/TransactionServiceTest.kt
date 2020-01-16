package mesi.capitaltracker.service

import mesi.capitaltracker.dao.*
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
    private lateinit var repo : GoldTransactionRepository

    @MockBean
    private lateinit var investorService: InvestorService
    @MockBean
    private lateinit var goldInvestmentService: GoldInvestmentService

    private val investor = Investor(1, "rico")
    private val investment : GoldInvestment = GoldInvestment(1, "Wiener Philharmoniker", 1.0)

    @Test
    fun testAddValidIncommingTransaction() {
        Mockito.`when`(investorService.get(1)).thenReturn(investor)
        Mockito.`when`(goldInvestmentService.get(1)).thenReturn(investment)

        val transaction = GoldTransaction(1, LocalDate.MIN, 1.0, investment)
        Mockito.`when`(repo.save(transaction)).thenReturn(transaction)
        val storedTransaction = service.addIncomingTransaction(investor.id, 1, transaction)

        assertNotNull(investor.incomingGoldTransactions.first())
        assertEquals(storedTransaction, investor.incomingGoldTransactions.first())
    }

    @Test
    fun testAddIncommingTransactionWithInvalidInvestor() {
        Mockito.`when`(investorService.get(1)).thenReturn(null)
        Mockito.`when`(goldInvestmentService.get(1)).thenReturn(investment)

        val transaction = GoldTransaction(1, LocalDate.MIN, 1.0, investment)
        Mockito.`when`(repo.save(transaction)).thenReturn(transaction)

        assertThrows<InvestorNotFoundException> { service.addIncomingTransaction(investor.id, 1, transaction) }
    }

    @Test
    fun testAddIncommingTransactionWithInvalidInvestment() {
        Mockito.`when`(investorService.get(1)).thenReturn(investor)
        Mockito.`when`(goldInvestmentService.get(1)).thenReturn(null)

        val transaction = GoldTransaction(1, LocalDate.MIN, 1.0, investment)
        Mockito.`when`(repo.save(transaction)).thenReturn(transaction)

        assertThrows<InvestmentNotFoundException> { service.addIncomingTransaction(investor.id, 1, transaction) }
    }
}