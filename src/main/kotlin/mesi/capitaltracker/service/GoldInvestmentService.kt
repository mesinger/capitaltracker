package mesi.capitaltracker.service

import mesi.capitaltracker.dao.GoldInvestment
import mesi.capitaltracker.dao.GoldRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GoldInvestmentService {

    @Autowired
    private lateinit var repo : GoldRepository

    fun get(id : Long) : GoldInvestment? {
        return repo.findById(id).orElseGet { null }
    }
}