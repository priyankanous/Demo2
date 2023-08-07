package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.AnnualTargetEntry;

@Repository
public interface AnnualTargetEntryRepository extends JpaRepository<AnnualTargetEntry, Long> {

	@Query("SELECT a from AnnualTargetEntry a where a.businessUnit.businessUnitId = ?1")
	List<AnnualTargetEntry> findByBusinessUnitId(Long businessUnitId);

	@Query("SELECT a from AnnualTargetEntry a where a.region.regionId = ?1")
	List<AnnualTargetEntry> findByRegionId(Long regionId);

	@Query("SELECT a from AnnualTargetEntry a where a.startegicBusinessUnit.sbuId = ?1")
	List<AnnualTargetEntry> findBySBUId(Long sbuId);

	@Query("SELECT a from AnnualTargetEntry a where a.strategicBusinessUnitHead.sbuHeadId = ?1")
	List<AnnualTargetEntry> findBySBUHeadId(Long sbuId);

	@Query("SELECT a from AnnualTargetEntry a where a.location.locationId = ?1")
	List<AnnualTargetEntry> findByLocationId(Long locationId);

	@Query("SELECT a from AnnualTargetEntry a where a.businessDevelopmentManager.bdmId = ?1")
	List<AnnualTargetEntry> findByBDMId(Long bdmId);

	@Query("SELECT a from AnnualTargetEntry a where a.businessType.businessTypeId = ?1")
	List<AnnualTargetEntry> findByBusinessTypeId(Long businessTypeId);

	@Query("SELECT a from AnnualTargetEntry a where a.cocPractice.cocPracticeId = ?1")
	List<AnnualTargetEntry> findByCocPracticeId(Long id);

	@Query("SELECT a from AnnualTargetEntry a where a.financialYear.financialYearId = ?1")
	List<AnnualTargetEntry> findByFinancialYearId(Long financialYearId);

	@Query("SELECT a from AnnualTargetEntry a where a.account.accountId = ?1")
	List<AnnualTargetEntry> findByAccountId(Long accountId);

}
