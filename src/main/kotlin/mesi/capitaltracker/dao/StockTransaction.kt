package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "stocktransactions")
data class StockTransaction (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long,

    @Column(length = 25, nullable = false)
    val symbol : String,

    @ManyToOne
    var investor : Investor?,

    @Column(nullable = false)
    val timestamp : LocalDate,

    @Column(nullable = false)
    val price : Double,

    @Column(nullable = false)
    val quantity : Int,

    @Column(nullable = false)
    val fees : Double,

    @Column(length = 10, nullable = false)
    val currency : String
)

interface StockTransactionRepo : JpaRepository<StockTransaction, Long>
