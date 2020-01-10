package mesi.capitaltracker.dao

import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@DiscriminatorValue("goldcoin")
class GoldCoin(
        id : Long = 0L,
        prettyName : String,

        @Column(length = 50, nullable = true)
        val manufacturer : String,

        @Column(nullable = true)
        val ounce : Double

) : Investment(id, prettyName, Currency.USD)
