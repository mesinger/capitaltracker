package mesi.capitaltracker.service

import mesi.capitaltracker.dao.Transaction
import mesi.capitaltracker.dao.TransactionRepository
import mesi.capitaltracker.utility.InvestmentNotFoundException
import mesi.capitaltracker.utility.InvestorNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionService {

    @Autowired
    private lateinit var investorService : InvestorService

    @Autowired
    private lateinit var investmentService: InvestmentService

    @Autowired
    private lateinit var repo : TransactionRepository

    fun addIncomingTransaction(userId : Long, investmentId : Long, transaction : Transaction) : Transaction {
        val investor = investorService.get(userId) ?: throw InvestorNotFoundException()
        val investment = investmentService.get(investmentId) ?: throw InvestmentNotFoundException()

        transaction.investment = investment
        investor.incomingTransactions.add(transaction)

        return repo.save(transaction)
    }
}