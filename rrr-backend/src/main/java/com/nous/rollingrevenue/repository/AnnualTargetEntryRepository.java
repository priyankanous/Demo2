package com.nous.rollingrevenue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.AnnualTargetEntry;

@Repository
public interface AnnualTargetEntryRepository extends JpaRepository<AnnualTargetEntry, Long> {

	@Query("SELECT a from AnnualTargetEntry a where a.businessUnit.businessUnitId = ?1")
	Optional<AnnualTargetEntry> findByBusinessUnitId(Long businessUnitId);

}
