package mesi.capitaltracker.service

import mesi.capitaltracker.dao.Investor
import mesi.capitaltracker.dao.InvestorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InvestorService {

    @Autowired
    private lateinit var repo : InvestorRepository

    fun get(id : Long) : Investor? {
        return repo.findById(id).orElseGet { null }
    }

    fun save(investor : Investor) : Investor {
        return repo.save(investor)
    }
}