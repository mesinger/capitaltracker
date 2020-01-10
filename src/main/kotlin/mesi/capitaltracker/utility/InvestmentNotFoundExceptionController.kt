package mesi.capitaltracker.utility

import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class InvestmentNotFoundExceptionController {

    @ExceptionHandler(InvestmentNotFoundException::class)
    fun onException() : ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Unknown investment")
    }
}