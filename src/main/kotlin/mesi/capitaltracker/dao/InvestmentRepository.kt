package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository

interface InvestmentRepository : JpaRepository<Investment, Long>