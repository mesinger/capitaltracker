package mesi.capitaltracker.service

import com.fasterxml.jackson.annotation.JsonProperty
import mesi.capitaltracker.dao.TransactionDirection
import java.time.LocalDate

data class StockTransactionInputData (
        @JsonProperty("user")
        val userId : Long,
        val timestamp : LocalDate,
        val symbol : String,
        val amount : Double,
        val fees : Double,
        val currency : String,
        val quantity : Double,
        val direction : TransactionDirection
)