package mesi.capitaltracker.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionRepo {

    @Autowired
    private lateinit var goldRepo : GoldTransactionRepo

    @Autowired
    private lateinit var stockRepo : StockTransactionRepo

    fun gold() = goldRepo
    fun stock() = stockRepo
}