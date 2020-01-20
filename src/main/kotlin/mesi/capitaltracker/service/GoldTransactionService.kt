package mesi.capitaltracker.service

import mesi.capitaltracker.dao.GoldTransaction
import mesi.capitaltracker.dao.GoldTransactionRepo
import org.springframework.beans.factory.annotation.Autowired

class GoldTransactionService {

    @Autowired
    private lateinit var investorService : InvestorService

    @Autowired
    private lateinit var repo : GoldTransactionRepo

    fun addTransactionForUser(userId : Long, transaction : GoldTransaction) : GoldTransaction {
        val investor = investorService.get(userId)
        transaction.investor = investor
        return repo.save(transaction)
    }
}