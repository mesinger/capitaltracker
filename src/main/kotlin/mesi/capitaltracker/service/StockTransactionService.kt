package mesi.capitaltracker.service

import mesi.capitaltracker.dao.GoldTransaction
import mesi.capitaltracker.dao.StockTransaction

interface StockTransactionService {
    fun add(input : StockTransactionInputData) : StockTransaction
    fun getAllByUser(userId : Long) : List<UserTransaction>
}