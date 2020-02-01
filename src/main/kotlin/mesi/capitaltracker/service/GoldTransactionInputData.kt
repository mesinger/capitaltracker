package mesi.capitaltracker.service

import com.fasterxml.jackson.annotation.JsonProperty
import mesi.capitaltracker.dao.TransactionDirection
import java.time.LocalDate

data class GoldTransactionInputData (
        @JsonProperty("user")
        val userId : Long,
        val timestamp : LocalDate,
        val ounce : Double,
        val amount : Double,
        val fees : Double,
        val currency : String,
        val direction : TransactionDirection
)