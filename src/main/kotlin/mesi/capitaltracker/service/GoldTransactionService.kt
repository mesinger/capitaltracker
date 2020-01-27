package mesi.capitaltracker.service

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
    private lateinit var financeApi : FinanceApiService

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

        val investedInUsd = transactions.map { financeApi.forex.transformTo(it.currency, "USD", it.value) }.sum()
        val feesInUsd = transactions.map { financeApi.forex.transformTo(it.currency, "USD", it.fees) }.sum()

        val ounces = transactions.map { it.ounce }.sum()
        val currentOuncePriceInUsd = financeApi.gold.goldPrice()

        val usdEur = financeApi.forex.exchangeRate("USD", "EUR")

        return InvestmentOverview(
                invested = investedInUsd * usdEur + feesInUsd * usdEur,
                current = ounces * currentOuncePriceInUsd * usdEur
        )
    }
}