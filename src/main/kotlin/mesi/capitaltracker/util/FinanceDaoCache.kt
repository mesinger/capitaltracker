package mesi.capitaltracker.util

interface FinanceDaoCache {
    fun put(key : String, value : Double)
    fun get(key : String) : Double?
}
