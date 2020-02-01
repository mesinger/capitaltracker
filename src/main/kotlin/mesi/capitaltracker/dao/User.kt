package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "investors")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,

        @Column(nullable = false, unique = true)
        val email : String
)

interface UserRepo : JpaRepository<User, Long> {
        fun findByEmail(email : String) : Optional<User>
}
