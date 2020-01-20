package mesi.capitaltracker.service

import junit.framework.Assert.*
import mesi.capitaltracker.dao.Investor
import mesi.capitaltracker.dao.InvestorRepo
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
import java.util.*

@RunWith(SpringRunner::class)
@DataJpaTest
class InvestorServiceTest {

    @TestConfiguration
    internal class ContextProvider {
        @Bean
        fun service(): InvestorService {
            return InvestorService()
        }
    }

    @Autowired
    private lateinit var service : InvestorService

    @MockBean
    private lateinit var repo : InvestorRepo

    @Test
    fun testGetExistingInvestor() {
        val investor = Investor(1,"name")
        Mockito.`when`(repo.findById(1)).thenReturn(Optional.of(investor))

        val fetchedInvestor = service.get(investor.id)

        assertNotNull(fetchedInvestor)
        assertEquals(fetchedInvestor, investor)
    }

    @Test
    fun testGetNullForNonExistingInvestor() {
        Mockito.`when`(repo.findById(1)).thenReturn(Optional.empty())
        assertThrows<InvestorNotFoundException> { service.get(1) }
    }

    @Test
    fun testSaveInvestor() {
        val investor = Investor(1,"name")
        Mockito.`when`(repo.save(investor)).thenReturn(investor)

        service.save(investor)
        verify(repo, times(1)).save(investor)
    }
}