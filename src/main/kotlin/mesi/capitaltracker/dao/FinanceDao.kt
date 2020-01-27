package mesi.capitaltracker.dao

interface FinanceDao {
    fun getGoldPriceInUsd(): Double
    fun getExchangeRate(from : String, to : String) : Double
}

//@Component
internal class FinanceDaoDummy : FinanceDao {
    override fun getGoldPriceInUsd(): Double {
        return 1500.0
    }

    override fun getExchangeRate(from: String, to: String): Double {
        return 1.2
    }

}
