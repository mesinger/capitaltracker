package mesi.capitaltracker.controller

import mesi.capitaltracker.service.InvestmentOverview
import mesi.capitaltracker.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("investment")
class InvestmentController {

    @Autowired
    private lateinit var transactionService: TransactionService

    @RequestMapping(
            path = ["gold/{userId}"],
            method = [RequestMethod.GET],
            produces = ["application/json"]
    )
    @ResponseBody
    fun getGoldInvestmentOverviewByUser(@PathVariable("userId") userId: Long) : InvestmentOverview {
        return transactionService.gold.getInvestmentOverviewForUser(userId)
    }
}