package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "goldtransactions")
data class GoldTransaction(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @ManyToOne
        var investor : Investor?,

        @Column(nullable = false)
        val timestamp : LocalDate,

        @Column(nullable = false)
        val ounce : Double,

        @Column(nullable = false)
        val value : Double,

        @Column(nullable = false)
        val fees : Double,

        @Column(nullable = false)
        val currency : String
)

interface GoldTransactionRepo : JpaRepository<GoldTransaction, Long>
