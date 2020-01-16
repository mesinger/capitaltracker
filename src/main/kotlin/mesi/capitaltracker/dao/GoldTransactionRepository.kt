package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository

interface GoldTransactionRepository : JpaRepository<GoldTransaction, Long>