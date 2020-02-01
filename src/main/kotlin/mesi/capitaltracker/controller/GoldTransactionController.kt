package mesi.capitaltracker.controller

import mesi.capitaltracker.service.GoldTransactionInputData
import mesi.capitaltracker.service.GoldTransactionService
import mesi.capitaltracker.service.UserTransaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction/gold")
class GoldTransactionController {

    @Autowired
    private lateinit var goldTransactionService: GoldTransactionService

    @RequestMapping(
            path = [""],
            method = [RequestMethod.POST],
            consumes = ["application/json"]
    )
    fun add(@RequestBody data : GoldTransactionInputData) {
        goldTransactionService.add(data)
    }

    @RequestMapping(
            path = ["/{userId}"],
            method = [RequestMethod.GET],
            produces = ["application/json"]
    )
    @ResponseBody
    fun getAllByUser(@PathVariable("userId") userId : Long) : List<UserTransaction> {
        return goldTransactionService.getAllByUser(userId)
    }
}