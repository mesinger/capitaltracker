package mesi.capitaltracker.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionService {

    @Autowired
    lateinit var gold : GoldTransactionService

    @Autowired
    lateinit var stock : StockTransactionService
}