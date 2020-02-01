package mesi.capitaltracker.service

import mesi.capitaltracker.dao.User
import mesi.capitaltracker.dao.UserRepo
import mesi.capitaltracker.util.UserNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserServiceDefault : UserService {

    @Autowired
    private lateinit var userRepo : UserRepo

    override fun add(email: String): User {
        return userRepo.save(User(0, email))
    }

    override fun get(id: Long): User {
        return userRepo.findById(id).orElseThrow { throw UserNotFoundException() }
    }

    override fun getByEmail(email: String): User {
        return userRepo.findByEmail(email).orElseThrow { throw UserNotFoundException() }
    }
}