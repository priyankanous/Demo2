package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.GlobalMonthlyLeaveLossFactor;

@Repository
public interface GlobalMonthlyLeaveLossFactorRepository extends JpaRepository<GlobalMonthlyLeaveLossFactor, Long> {

	List<GlobalMonthlyLeaveLossFactor> findByFinancialYear(String financialYear);
	
	@Query("Select g from GlobalMonthlyLeaveLossFactor g where g.financialYear.financialYearName = ?1")
	GlobalMonthlyLeaveLossFactor getLeaveLossFactorByLocation(String financialYear);

}
