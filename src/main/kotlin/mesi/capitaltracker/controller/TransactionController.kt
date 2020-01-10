package mesi.capitaltracker.controller

import mesi.capitaltracker.dao.Transaction
import mesi.capitaltracker.service.InvestorService
import mesi.capitaltracker.service.TransactionService
import mesi.capitaltracker.utility.InvestorNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("transaction")
class TransactionController {

    @Autowired
    private lateinit var service : TransactionService

    @RequestMapping(
            method = [RequestMethod.POST],
            path = ["in"],
            consumes = ["application/json"],
            produces = ["text/plain"]
    )
    fun addIncomingTransaction(@RequestParam("userid") userId : Long, @RequestParam("investmentid") investmentId : Long, @RequestBody transaction: Transaction) {
        service.addIncomingTransaction(userId, investmentId, transaction)
    }
}