package mesi.capitaltracker.service

import mesi.capitaltracker.dao.FinanceDao
import mesi.capitaltracker.dao.GoldTransaction
import mesi.capitaltracker.dao.GoldTransactionRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GoldTransactionService {

    @Autowired
    private lateinit var investorService : InvestorService

    @Autowired
    private lateinit var repo : GoldTransactionRepo

    @Autowired
    private lateinit var financeDao: FinanceDao

    fun addTransactionForUser(userId : Long, transaction : GoldTransaction) : GoldTransaction {
        val investor = investorService.get(userId)
        transaction.investor = investor
        return repo.save(transaction)
    }

    fun getTransactionsForUser(userId: Long) : List<GoldTransaction> {
        return repo.findAll().filter { it.investor?.id == userId }
    }

    fun getInvestmentOverviewForUser(userId: Long) : InvestmentOverview {
        val transactions = getTransactionsForUser(userId)

        val investedTotal = transactions.map { it.value }.sum()
        val feesTotal = transactions.map { it.fees }.sum()

        val ounces = transactions.map { it.ounce }.sum()
        val currentOuncePriceInUsd = financeDao.getResourcePrice("gold")
        val usdEur = financeDao.getExchangeRate("usd", "eur")
        val currentValue = ounces * currentOuncePriceInUsd * usdEur

        return InvestmentOverview(
                invested = investedTotal + feesTotal,
                current = currentValue
        )
    }
}