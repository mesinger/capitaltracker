package mesi.capitaltracker.service

import mesi.capitaltracker.dao.FinanceDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface StockPriceService {
    fun price(symbol : String) : Double
    fun currency(symbol : String) : String
}

@Component
class StockPriceServiceImpl : StockPriceService {

    @Autowired
    private lateinit var financeDao: FinanceDao

    override fun price(symbol: String): Double {
        return financeDao.getStockPrice(symbol)
    }

    override fun currency(symbol: String): String {
        return financeDao.getStockCurrency(symbol)
    }
}