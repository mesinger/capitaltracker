package mesi.capitaltracker.dao

import org.springframework.stereotype.Component
import yahoofinance.YahooFinance

@Component
class YahooFinanceDao : FinanceDao {
    override fun getGoldPriceInUsd(): Double {
        return YahooFinance.get("GC=F").getQuote().price.toDouble()
    }

    override fun getExchangeRate(from: String, to: String): Double {
       return YahooFinance.getFx(getFxSymbol(from, to)).price.toDouble()
    }

    private fun getFxSymbol(from: String, to: String) = "${from.toUpperCase()}${to.toUpperCase()}=X"
}