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
        val investor : User,

        @Column(nullable = false)
        val timestamp : LocalDate,

        @Column(nullable = false)
        val ounce : Double,

        @Column(nullable = false)
        @Convert(converter = CurrencyAmountConverter::class)
        val amount : CurrencyAmount,

        @Column(nullable = false)
        @Convert(converter = CurrencyAmountConverter::class)
        val fees : CurrencyAmount,

        @Column(nullable = false)
        @Enumerated(value = EnumType.STRING)
        val direction : TransactionDirection
)

interface GoldTransactionRepo : JpaRepository<GoldTransaction, Long>
