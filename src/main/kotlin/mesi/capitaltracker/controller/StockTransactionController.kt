package mesi.capitaltracker.controller

import mesi.capitaltracker.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transaction/stock")
class StockTransactionController {

    @Autowired
    private lateinit var stockTransactionService: StockTransactionService

    @RequestMapping(
            path = [""],
            method = [RequestMethod.POST],
            consumes = ["application/json"]
    )
    fun add(@RequestBody data : StockTransactionInputData) {
        stockTransactionService.add(data)
    }

    @RequestMapping(
            path = ["/{userId}"],
            method = [RequestMethod.GET],
            produces = ["application/json"]
    )
    @ResponseBody
    fun getAllByUser(@PathVariable("userId") userId : Long) : List<UserTransaction> {
        return stockTransactionService.getAllByUser(userId)
    }
}