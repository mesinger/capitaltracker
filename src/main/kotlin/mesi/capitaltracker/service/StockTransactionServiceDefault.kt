package mesi.capitaltracker.service

import mesi.capitaltracker.dao.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class StockTransactionServiceDefault : StockTransactionService {

    @Autowired
    private lateinit var stockTransactionRepo: StockTransactionRepo

    @Autowired
    private lateinit var userService: UserService

    override fun add(input: StockTransactionInputData): StockTransaction {
        val user = userService.get(input.userId)
        val stockTransaction = getGoldTransactionFromInputData(input, user)
        return stockTransactionRepo.save(stockTransaction)
    }

    override fun getAllByUser(userId: Long): List<UserTransaction> {
        return stockTransactionRepo.findAll().filter { it.investor.id == userId }.map { it.toUserTransaction() }
    }

    private fun getGoldTransactionFromInputData(input : StockTransactionInputData, user : User) = StockTransaction(
            id = 0,
            investor = user,
            amount = CurrencyAmount(input.amount, Currency.getInstance(input.currency)),
            fees = CurrencyAmount(input.fees, Currency.getInstance(input.currency)),
            quantity = input.quantity,
            direction = input.direction,
            timestamp = input.timestamp,
            symbol = input.symbol
    )
}