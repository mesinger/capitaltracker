package mesi.capitaltracker.dao

import javax.persistence.*

@Entity
@Table(name = "goldinvestments")
data class GoldInvestment(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @Column(length = 50, nullable = false)
        val manufacturer : String,

        @Column(name = "weight", nullable = false)
        val weightInOunces : Double

)


