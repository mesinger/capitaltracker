package mesi.capitaltracker.service

import mesi.capitaltracker.dao.FinanceDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface ForexService {
    fun exchangeRate(from: String, to: String) : Double
    fun transformTo(from: String, to: String, value: Double) = exchangeRate(from, to) * value
}

@Component
class ForexServiceImpl : ForexService {

    @Autowired
    private lateinit var financeDao: FinanceDao

    override fun exchangeRate(from: String, to: String): Double {
        return financeDao.getExchangeRate(from, to)
    }
}
