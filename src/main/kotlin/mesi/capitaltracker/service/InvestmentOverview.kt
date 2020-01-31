package mesi.capitaltracker.service

data class InvestmentOverview(
        val name : String,
        val invested : Double,
        val current : Double
) {
    val changed : Double = current/invested * 100
}