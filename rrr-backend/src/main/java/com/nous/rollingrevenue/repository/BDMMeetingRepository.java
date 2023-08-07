package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BDMMeeting;

@Repository
public interface BDMMeetingRepository extends JpaRepository<BDMMeeting, Long> {

	List<BDMMeeting> findByFinancialYear(String financialYear);

	@Query("SELECT b from BDMMeeting b where b.region.regionId = ?1")
	List<BDMMeeting> findByRegionId(Long regionId);

	@Query("SELECT b from BDMMeeting b where b.businessDevelopmentManager.bdmId = ?1")
	List<BDMMeeting> findByBDMId(Long bdmId);

	@Query("SELECT b from BDMMeeting b where b.financialYear.financialYearId = ?1")
	List<BDMMeeting> findByFinancialYearId(Long financialYearId);

}
