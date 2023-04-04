package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.GlobalMonthlyLeaveLossFactor;

@Repository
public interface GlobalMonthlyLeaveLossFactorRepository extends JpaRepository<GlobalMonthlyLeaveLossFactor, Long> {

	List<GlobalMonthlyLeaveLossFactor> findByFinancialYear(String financialYear);

}
