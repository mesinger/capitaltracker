package mesi.capitaltracker.dao

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "goldtransactions")
data class GoldTransaction(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @Column(nullable = false)
        val timestamp : LocalDate,

        @Column(nullable = false)
        val value : Double,

        @Column(nullable = false)
        val fees : Double,

        @ManyToOne
        var goldInvestment: GoldInvestment? = null
)