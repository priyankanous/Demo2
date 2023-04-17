package com.nous.rollingrevenue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.FinancialYear;

@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYear, Long> {
	
	Optional<FinancialYear> findByFinancialYearName(String financialYearName);

}
