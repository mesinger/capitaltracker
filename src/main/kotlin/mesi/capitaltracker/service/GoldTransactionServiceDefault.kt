package mesi.capitaltracker.service

import mesi.capitaltracker.dao.CurrencyAmount
import mesi.capitaltracker.dao.GoldTransaction
import mesi.capitaltracker.dao.GoldTransactionRepo
import mesi.capitaltracker.dao.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class GoldTransactionServiceDefault : GoldTransactionService {

    @Autowired
    private lateinit var goldTransactionRepo: GoldTransactionRepo

    @Autowired
    private lateinit var userService: UserService

    override fun add(input: GoldTransactionInputData): GoldTransaction {
        val user = userService.get(input.userId)
        val goldTransaction = getGoldTransactionFromInputData(input, user)
        return goldTransactionRepo.save(goldTransaction)
    }

    override fun getAllByUser(userId: Long): List<UserTransaction> {
        return goldTransactionRepo.findAll().filter { it.investor.id == userId }.map { it.toUserTransaction() }
    }

    private fun getGoldTransactionFromInputData(input : GoldTransactionInputData, user : User) = GoldTransaction(
            id = 0,
            investor = user,
            amount = CurrencyAmount(input.amount, Currency.getInstance(input.currency)),
            fees = CurrencyAmount(input.fees, Currency.getInstance(input.currency)),
            ounce = input.ounce,
            direction = input.direction,
            timestamp = input.timestamp
    )
}