package mesi.capitaltracker.service

import mesi.capitaltracker.dao.FinanceDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface GoldPriceService {
    fun goldPrice() : Double
}

@Component
class GoldPriceServiceImpl : GoldPriceService {

    @Autowired
    private lateinit var financeDao: FinanceDao

    override fun goldPrice(): Double {
        return financeDao.getGoldPriceInUsd()
    }
}