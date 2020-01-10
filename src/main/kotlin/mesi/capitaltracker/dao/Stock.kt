package mesi.capitaltracker.dao

import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@DiscriminatorValue("stock")
class Stock(
        id : Long = 0L,
        prettyName : String,
        targetCurrency : Currency,

        @Column(length = 50, nullable = true)
        val isin : String

) : Investment(id, prettyName, targetCurrency)