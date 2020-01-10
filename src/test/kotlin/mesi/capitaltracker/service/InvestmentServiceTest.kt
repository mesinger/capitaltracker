package mesi.capitaltracker.service

import junit.framework.Assert
import mesi.capitaltracker.dao.Currency
import mesi.capitaltracker.dao.Investment
import mesi.capitaltracker.dao.InvestmentRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
class InvestmentServiceTest {

    @TestConfiguration
    internal class ContextProvider {
        @Bean
        fun service(): InvestmentService {
            return InvestmentService()
        }
    }

    @Autowired
    private lateinit var service : InvestmentService

    @MockBean
    private lateinit var repo : InvestmentRepository

    private val investment = Investment(1,"name", Currency.USD)

    @Test
    fun testGetExistingInvestor() {
        Mockito.`when`(repo.findById(1)).thenReturn(Optional.of(investment))

        val fetchedInvestment = service.get(investment.id)

        Assert.assertNotNull(fetchedInvestment)
        Assert.assertEquals(fetchedInvestment!!.prettyName, investment.prettyName)
    }

    @Test
    fun testGetNullForNonExistingInvestor() {
        Mockito.`when`(repo.findById(1)).thenReturn(Optional.empty())
        val fetchedInvestor = service.get(1)
        Assert.assertNull(fetchedInvestor)
    }

    @Test
    fun testSaveInvestor() {
        Mockito.`when`(repo.save(investment)).thenReturn(investment)

        service.save(investment)
        Mockito.verify(repo, Mockito.times(1)).save(investment)
    }
}