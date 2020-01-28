package mesi.capitaltracker.dao

interface FinanceDao {
    fun getGoldPriceInUsd(): Double
    fun getExchangeRate(from : String, to : String) : Double
    fun getStockPrice(symbol : String) : Double
    fun getStockCurrency(symbol : String) : String
}

//@Component
internal class FinanceDaoDummy : FinanceDao {
    override fun getGoldPriceInUsd(): Double {
        return 1500.0
    }

    override fun getExchangeRate(from: String, to: String): Double {
        return 1.2
    }

    override fun getStockPrice(symbol: String): Double {
        return 100.0
    }

    override fun getStockCurrency(symbol: String): String {
        return "USD"
    }
}
