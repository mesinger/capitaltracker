package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository

interface GoldRepository : JpaRepository<GoldInvestment, Long>