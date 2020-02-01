package mesi.capitaltracker.service

import mesi.capitaltracker.dao.User

interface UserService {
    fun add(email : String) : User
    fun get(id : Long) : User
    fun getByEmail(email : String) : User
}