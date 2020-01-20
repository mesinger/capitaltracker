package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
@Table(name = "investors")
data class Investor(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @Column(length = 50, nullable = false)
        val name : String
)

interface InvestorRepo : JpaRepository<Investor, Long>
