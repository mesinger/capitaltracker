package mesi.capitaltracker.service

import mesi.capitaltracker.dao.GoldTransaction

interface GoldTransactionService {
    fun add(input : GoldTransactionInputData) : GoldTransaction
    fun getAllByUser(userId : Long) : List<UserTransaction>
}