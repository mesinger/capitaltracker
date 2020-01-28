package mesi.capitaltracker.service

import mesi.capitaltracker.dao.StockTransaction
import mesi.capitaltracker.dao.StockTransactionRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class StockTransactionService {

    @Autowired
    private lateinit var investorService : InvestorService

    @Autowired
    private lateinit var repo : StockTransactionRepo

    @Autowired
    private lateinit var financeApi : FinanceApiService

    fun addTransactionForUser(userId : Long, transaction : StockTransaction) : StockTransaction {
        val investor = investorService.get(userId)
        transaction.investor = investor
        return repo.save(transaction)
    }

    fun getTransactionsForUser(userId: Long) : List<StockTransaction> {
        return repo.findAll().filter { it.investor?.id == userId }
    }

    fun getTransactionsForUsersByShare(userId: Long, symbol : String) : List<StockTransaction> {
        return getTransactionsForUser(userId).filter { it.symbol == symbol }
    }

    fun getShareInvestmentOverviewForUser(userId: Long, symbol : String, targetCurrency : String) : InvestmentOverview {
        val transactions = getTransactionsForUsersByShare(userId, symbol)

        val numOfShares = transactions.map { it.quantity }.sum()
        val investedTotal = transactions.map { financeApi.forex.transformTo(it.currency, targetCurrency, it.price * it.quantity + it.fees) }.sum()

        val currentStockPrice = financeApi.stock.price(symbol)
        val stockCurrency = financeApi.stock.currency(symbol)

        val stockToTargetExchangeRate = financeApi.forex.exchangeRate(stockCurrency, targetCurrency)

        return InvestmentOverview(
                invested = investedTotal,
                current = numOfShares * (currentStockPrice * stockToTargetExchangeRate)
        )
    }
}