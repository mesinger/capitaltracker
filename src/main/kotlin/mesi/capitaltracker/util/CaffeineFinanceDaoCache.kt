package mesi.capitaltracker.util

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class CaffeineFinanceDaoCache : FinanceDaoCache {

    private val cache : Cache<String, Double> = Caffeine.newBuilder().expireAfterWrite(15, TimeUnit.MINUTES).maximumSize(1000).build()

    override fun put(key: String, value: Double) {
        cache.put(key, value)
    }

    override fun get(key: String): Double? {
        return cache.getIfPresent(key)
    }
}