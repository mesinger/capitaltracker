package mesi.capitaltracker.controller

import mesi.capitaltracker.dao.GoldTransaction
import mesi.capitaltracker.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("transaction")
class TransactionController {

    @Autowired
    private lateinit var service : TransactionService

    @RequestMapping(
            method = [RequestMethod.POST],
            path = ["in/gold"],
            consumes = ["application/json"],
            produces = ["text/plain"]
    )
    fun addIncomingGoldTransaction(@RequestParam("userid") userId : Long, @RequestParam("investmentid") investmentId : Long, @RequestBody transaction: GoldTransaction) {
        service.addIncomingTransaction(userId, investmentId, transaction)
    }

    @RequestMapping(
            method = [RequestMethod.GET],
            path = ["gold/{userId}"]
    )
    @ResponseBody
    fun getAccountGold(@PathVariable("userId") userId: Long) : Double {
        return service.getAccountGoldBalance(userId)
    }

    @RequestMapping(
            method = [RequestMethod.GET],
            path = ["gold/in/{userId}"]
    )
    @ResponseBody
    fun getInvestedGoldBalance(@PathVariable("userId") userId: Long) : Double {
        return service.getIncommingAccountGoldBalance(userId)
    }
}