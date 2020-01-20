package mesi.capitaltracker.service

import mesi.capitaltracker.dao.Investor
import mesi.capitaltracker.dao.InvestorRepo
import mesi.capitaltracker.util.InvestorNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InvestorService {

    @Autowired
    private lateinit var repo : InvestorRepo

    fun get(id : Long) : Investor {
        return repo.findById(id).orElseGet { throw InvestorNotFoundException() }
    }

    fun save(investor : Investor) : Investor {
        return repo.save(investor)
    }
}