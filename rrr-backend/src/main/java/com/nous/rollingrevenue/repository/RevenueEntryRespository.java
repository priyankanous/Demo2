package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RevenueEntry;

@Repository
public interface RevenueEntryRespository extends JpaRepository<RevenueEntry, Long> {

	@Query("SELECT r from RevenueEntry r where r.region.regionId = ?1")
	List<RevenueEntry> findByRegionId(Long regionId);

	@Query("SELECT r from RevenueEntry r where r.probabilityType.probabilityTypeId = ?1")
	List<RevenueEntry> findByProbabilityTypeId(Long probabilityTypeId);

	@Query("SELECT r from RevenueEntry r where r.businessDevelopmentManager.bdmId = ?1")
	List<RevenueEntry> findByBDMId(Long bdmId);

	@Query("SELECT r from RevenueEntry r where r.financialYear.financialYearId = ?1")
	List<RevenueEntry> findByFinancialYearId(Long financialYearId);

	@Query("SELECT r from RevenueEntry r where r.currency.currencyId = ?1")
	List<RevenueEntry> findByCurrencyId(Long currencyId);

	@Query("SELECT r from RevenueEntry r where r.account.accountId = ?1")
	List<RevenueEntry> findByAccountId(Long accountId);

	@Query("SELECT r from RevenueEntry r where r.opportunity.opportunityId = ?1")
	List<RevenueEntry> findByOpportunityId(Long opportunityId);

	@Modifying
	@Query("Update RevenueEntry r set r.resourceCount = ?1 where r.revenueEntryId = ?2")
	void updateRevenueEntryDetails(Integer resourceCount, Long revenueEntryId);

	@Modifying
	@Query("Update RevenueEntry r set r.workOrder.workOrderId = ?1 where r.revenueEntryId = ?2")
	void updateRevenueEntryDetailsToNull(String workOrderNumber, Long revenueEntryId);

}
