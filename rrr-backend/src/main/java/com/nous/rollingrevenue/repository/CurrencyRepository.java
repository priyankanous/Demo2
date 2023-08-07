package com.nous.rollingrevenue.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	List<Currency> findByFinancialYear(String financialYear);

	@Query("SELECT c from Currency c where c.financialYear.financialYearId = ?1")
	List<Currency> findByFinancialYearId(Long financialYearId);

}
