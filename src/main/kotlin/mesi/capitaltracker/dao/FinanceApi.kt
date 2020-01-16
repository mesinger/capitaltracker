package mesi.capitaltracker.dao

import org.springframework.stereotype.Component

interface FinanceApi {
    fun getPriceFor(symbol : String) : Double
    fun getChangeRateFor(from : Currency, to : Currency) : Double
}

@Component
internal class FinanceApiMocked : FinanceApi {

    override fun getPriceFor(symbol: String): Double {
        return 1552.65
    }

    override fun getChangeRateFor(from: Currency, to: Currency): Double {
        return 0.9
    }
}