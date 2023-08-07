package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.CocPractice;

@Repository
public interface CocPracticeRepository extends JpaRepository<CocPractice, Long> {

	@Query("SELECT c from CocPractice c where c.businessUnit.businessUnitId = ?1")
	List<CocPractice> findByBusinessUnitId(Long businessUnitId);

}
