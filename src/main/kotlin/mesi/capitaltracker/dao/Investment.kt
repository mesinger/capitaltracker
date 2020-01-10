package mesi.capitaltracker.dao

import javax.persistence.*

@Entity
@Table(name = "investments")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "investment_type")
class Investment(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @Column(length = 50, nullable = false)
        val prettyName : String,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val targetCurrency: Currency
)


