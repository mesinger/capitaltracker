package mesi.capitaltracker.controller

import mesi.capitaltracker.dao.GoldTransaction
import mesi.capitaltracker.dao.StockTransaction
import mesi.capitaltracker.service.InvestmentOverview
import mesi.capitaltracker.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("transaction")
class TransactionController {

    @Autowired
    private lateinit var transactionService: TransactionService

    @RequestMapping(
            path = ["gold"],
            method = [RequestMethod.POST],
            consumes = ["application/json"],
            produces = ["text/plain"]
    )
    fun addGoldInvestment(@RequestParam("userid") userId : Long, @RequestBody transaction : GoldTransaction) {
        transactionService.gold.addTransactionForUser(userId, transaction)
    }

    @RequestMapping(
            path = ["gold/{userId}"],
            method = [RequestMethod.GET],
            produces = ["application/json"]
    )
    @ResponseBody
    fun getGoldInvestmentsByUser(@PathVariable("userId") userId: Long) : List<GoldTransaction> {
        return transactionService.gold.getTransactionsForUser(userId)
    }

    @RequestMapping(
            path = ["stock"],
            method = [RequestMethod.POST],
            consumes = ["application/json"],
            produces = ["text/plain"]
    )
    fun addStockInvestment(@RequestParam("userid") userId : Long, @RequestBody transaction : StockTransaction) {
        transactionService.stock.addTransactionForUser(userId, transaction)
    }
}