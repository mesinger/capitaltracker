package mesi.capitaltracker.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class FinanceApiService {

    @Autowired
    lateinit var forex : ForexService

    @Autowired
    lateinit var gold : GoldPriceService

    @Autowired
    lateinit var stock : StockPriceService
}