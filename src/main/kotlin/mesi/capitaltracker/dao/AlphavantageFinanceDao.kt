package mesi.capitaltracker.dao

import org.patriques.AlphaVantageConnector
import org.patriques.ForeignExchange

//@Component
class AlphavantageFinanceDao : FinanceDao {

    private val alphaVantageConnector = AlphaVantageConnector("irgendwas", 2000)
    
    override fun getGoldPriceInUsd(): Double {
        return 1500.0
    }

    override fun getExchangeRate(from: String, to: String): Double {
        val exchanger = ForeignExchange(alphaVantageConnector)
        return exchanger.currencyExchangeRate(from.toUpperCase(), to.toUpperCase()).data.exchangeRate.toDouble()
    }
}