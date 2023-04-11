package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.FinancialYear;

@Repository
public interface FinancialYearRepository extends JpaRepository<FinancialYear, Long> {
	
	FinancialYear findByFinancialYearName(String financialYearName);

}
