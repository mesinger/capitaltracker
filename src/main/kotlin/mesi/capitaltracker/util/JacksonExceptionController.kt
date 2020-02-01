package mesi.capitaltracker.util

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.ValueInstantiationException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class JacksonExceptionController {

    @ExceptionHandler(JsonParseException::class, ValueInstantiationException::class)
    fun onParseException() : ResponseEntity<String> {
        return ResponseEntity.badRequest().body("Invalid request body")
    }
}