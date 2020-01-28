package mesi.capitaltracker.dao

import mesi.capitaltracker.util.FinanceDaoCache
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import yahoofinance.YahooFinance

@Component
class YahooFinanceDao : FinanceDao {

    @Autowired
    private lateinit var cache : FinanceDaoCache

    override fun getGoldPriceInUsd(): Double {
        val yahooGold = "GC=F"
        return cache.get(yahooGold) ?: kotlin.run {
            val goldPrice = YahooFinance.get(yahooGold).getQuote().price.toDouble()
            cache.put(yahooGold, goldPrice)
            return goldPrice
        }
    }

    override fun getExchangeRate(from: String, to: String): Double {
        if(from == to) return 1.0

        val yahooFx = getFxSymbol(from, to)
        return cache.get(yahooFx) ?: kotlin.run {
            val exchangeRate = YahooFinance.getFx(getFxSymbol(from, to)).price.toDouble()
            cache.put(yahooFx, exchangeRate)
            return exchangeRate
        }
    }

    private fun getFxSymbol(from: String, to: String) = "${from.toUpperCase()}${to.toUpperCase()}=X"
}