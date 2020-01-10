package mesi.capitaltracker.dao

import org.springframework.data.jpa.repository.JpaRepository

interface InvestorRepository : JpaRepository<Investor, Long>