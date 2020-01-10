package mesi.capitaltracker.dao

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "transactions")
data class Transaction(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @Column(nullable = false)
        val timestamp : LocalDate,

        @Column(nullable = false)
        val value : Double,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val currency : Currency,

        @ManyToOne
        var investment: Investment? = null
)