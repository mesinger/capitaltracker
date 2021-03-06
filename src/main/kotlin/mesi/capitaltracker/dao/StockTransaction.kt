package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "stocktransactions")
data class StockTransaction(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(length = 25, nullable = false)
        val symbol: String,

        @ManyToOne
        val investor: User,

        @Column(nullable = false)
        val timestamp: LocalDate,

        @Column(nullable = false)
        @Convert(converter = CurrencyAmountConverter::class)
        val amount: CurrencyAmount,

        @Column(nullable = false)
        @Convert(converter = CurrencyAmountConverter::class)
        val fees: CurrencyAmount,

        @Column(nullable = false)
        val quantity: Double,

        @Column(nullable = false)
        @Enumerated(value = EnumType.STRING)
        val direction: TransactionDirection
)

interface StockTransactionRepo : JpaRepository<StockTransaction, Long>
