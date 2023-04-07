package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

	List<Currency> findByFinancialYear(String financialYear);

}
