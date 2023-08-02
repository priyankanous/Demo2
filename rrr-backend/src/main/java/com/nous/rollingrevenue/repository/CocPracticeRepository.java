package com.nous.rollingrevenue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.CocPractice;

@Repository
public interface CocPracticeRepository extends JpaRepository<CocPractice, Long> {

	@Query("SELECT c from CocPractice c where c.businessUnit.businessUnitId = ?1")
	Optional<CocPractice> findByBusinessUnitId(Long businessUnitId);

}
