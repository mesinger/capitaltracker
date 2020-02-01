package mesi.capitaltracker.util

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class UserNotFoundExceptionController {

    @ExceptionHandler(UserNotFoundException::class)
    fun onException() : ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Unknown user")
    }
}