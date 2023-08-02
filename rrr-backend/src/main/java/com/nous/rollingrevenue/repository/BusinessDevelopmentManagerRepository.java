package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BusinessDevelopmentManager;

@Repository
public interface BusinessDevelopmentManagerRepository extends JpaRepository<BusinessDevelopmentManager, Long> {

	@Query("SELECT b from BusinessDevelopmentManager b left join fetch b.businessUnits u where u.businessUnitId = ?1")
	List<BusinessDevelopmentManager> findByBusinessUnitId(Long businessUnitId);
}
