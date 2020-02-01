package mesi.capitaltracker.dao

import java.util.*
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class CurrencyAmountConverter : AttributeConverter<CurrencyAmount, String> {

    private val separator = ";"

    override fun convertToDatabaseColumn(attribute: CurrencyAmount): String {
        return "${attribute.amount}$separator${attribute.currency.currencyCode}"
    }

    override fun convertToEntityAttribute(dbData: String): CurrencyAmount? {
        val data = dbData.split(separator)
        if(data.size == 2) {
            return CurrencyAmount(amount = data[0].toDouble(), currency = Currency.getInstance(data[1]))
        }
        return null
    }
}