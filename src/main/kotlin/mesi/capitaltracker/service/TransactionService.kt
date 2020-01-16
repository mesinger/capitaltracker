package mesi.capitaltracker.service

import mesi.capitaltracker.dao.*
import mesi.capitaltracker.utility.InvestmentNotFoundException
import mesi.capitaltracker.utility.InvestorNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionService {

    @Autowired
    private lateinit var investorService : InvestorService

    @Autowired
    private lateinit var goldInvestmentService: GoldInvestmentService

    @Autowired
    private lateinit var repo : GoldTransactionRepository

    @Autowired
    private lateinit var financeApi : FinanceApi

    fun addIncomingTransaction(userId : Long, investmentId : Long, goldTransaction : GoldTransaction) : GoldTransaction {
        val investor = findInvestor(userId)
        val investment = goldInvestmentService.get(investmentId) ?: throw InvestmentNotFoundException()

        goldTransaction.goldInvestment = investment
        investor.incomingGoldTransactions.add(goldTransaction)

        return repo.save(goldTransaction)
    }

    fun getIncommingAccountGoldBalance(userId: Long) : Double {
        return findInvestor(userId).incomingGoldTransactions.map { it.value }.sum()
    }

    fun getAccountGoldInOunce(userId: Long) : Double {
        val investor = findInvestor(userId)
        val userTransactions = investor.incomingGoldTransactions

        return userTransactions.map { it.goldInvestment!!.weightInOunces }.sum()
    }

    fun getAccountGoldBalance(userId: Long) : Double {
        val ounces = getAccountGoldInOunce(userId)
        val pricePerOunce = financeApi.getPriceFor("GOLD")
        val priceInDollar = ounces * pricePerOunce
        val priceInEuro = financeApi.getChangeRateFor(Currency.USD, Currency.EUR) * priceInDollar
        val totalFees = findInvestor(userId).incomingGoldTransactions.map { it.fees }.sum()
        return priceInEuro - totalFees
    }

    private fun findInvestor(userId: Long) : Investor {
        return investorService.get(userId) ?: throw InvestorNotFoundException()
    }
}