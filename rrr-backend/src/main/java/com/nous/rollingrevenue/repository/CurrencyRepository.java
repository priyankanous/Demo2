package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.CurrencyEntity;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

	List<CurrencyEntity> findByFinancialYear(String financialYear);

	@Query("SELECT c from CurrencyEntity c where c.financialYear.financialYearId = ?1")
	List<CurrencyEntity> findByFinancialYearId(Long financialYearId);

}
