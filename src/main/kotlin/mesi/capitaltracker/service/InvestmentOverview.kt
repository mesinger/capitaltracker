package mesi.capitaltracker.service

data class InvestmentOverview(
        val invested : Double,
        val current : Double
) {
    val changed : Double = current/invested
}