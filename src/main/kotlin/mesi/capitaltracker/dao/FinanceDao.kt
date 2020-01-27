package mesi.capitaltracker.dao

import org.springframework.stereotype.Component

interface FinanceDao {
    fun getResourcePrice(name : String) : Double
    fun getExchangeRate(from : String, to : String) : Double
}

@Component
class FinanceDaoDummy : FinanceDao {
    override fun getResourcePrice(name: String): Double {
        return 1500.0
    }

    override fun getExchangeRate(from: String, to: String): Double {
        return 1.2
    }

}
