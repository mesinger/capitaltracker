package mesi.capitaltracker.dao

import javax.persistence.*

@Entity
@Table(name = "investors")
data class Investor(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @Column(length = 50, nullable = false)
        val name : String,

        @OneToMany(targetEntity = GoldTransaction::class)
        val incomingGoldTransactions : MutableSet<GoldTransaction> = mutableSetOf(),

        @OneToMany(targetEntity = GoldTransaction::class)
        val outgoingGoldTransactions : MutableSet<GoldTransaction> = mutableSetOf()
)