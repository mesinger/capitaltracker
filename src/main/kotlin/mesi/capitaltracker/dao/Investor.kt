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

        @OneToMany(targetEntity = Transaction::class)
        val incomingTransactions : MutableSet<Transaction> = mutableSetOf(),

        @OneToMany(targetEntity = Transaction::class)
        val outgoingTransactions : MutableSet<Transaction> = mutableSetOf()
)