package mesi.capitaltracker.service

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonUnwrapped
import mesi.capitaltracker.dao.*
import java.time.LocalDate

data class UserTransaction(
        val name : String,
        val amount : Double,
        val fees : Double,
        val currency : String,
        val direction : TransactionDirection,
        @JsonIgnore
        val investor : User,
        val timestamp : LocalDate
)

fun GoldTransaction.toUserTransaction() : UserTransaction {
    return UserTransaction(
            name = "gold: $ounce ounces",
            amount = amount.amount,
            fees = fees.amount,
            currency = amount.currency.currencyCode,
            direction = direction,
            investor = investor,
            timestamp = timestamp
    )
}

fun StockTransaction.toUserTransaction() : UserTransaction {
    return UserTransaction(
            name = "stock: $quantity x $symbol",
            amount = amount.amount,
            fees = fees.amount,
            currency = amount.currency.currencyCode,
            direction = direction,
            investor = investor,
            timestamp = timestamp
    )
}
