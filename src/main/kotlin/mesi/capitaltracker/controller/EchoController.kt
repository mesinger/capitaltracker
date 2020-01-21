package mesi.capitaltracker.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("echo")
class EchoController {

    @GetMapping
    @ResponseBody
    fun echo() = "echo"
}