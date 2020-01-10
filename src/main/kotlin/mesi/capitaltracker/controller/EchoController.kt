package mesi.capitaltracker.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("echo")
class EchoController {

    @GetMapping()
    @ResponseBody
    fun echo() : String = "echo"
}