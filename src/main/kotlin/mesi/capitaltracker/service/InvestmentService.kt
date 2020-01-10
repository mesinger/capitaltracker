package mesi.capitaltracker.service

import mesi.capitaltracker.dao.Investment
import mesi.capitaltracker.dao.InvestmentRepository
import mesi.capitaltracker.dao.Investor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InvestmentService {

    @Autowired
    private lateinit var repo : InvestmentRepository

    fun get(id : Long) : Investment? {
        return repo.findById(id).orElseGet { null }
    }

    fun save(investment : Investment) : Investment {
        return repo.save(investment)
    }
}