package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.vo.OpportunityRevenueRequest;
import com.nous.rollingrevenue.vo.ResourceEntryRequest;

@Repository
public interface RevenueResourceEntryRepository extends JpaRepository<RevenueResourceEntry, Long> {

	@Query("FROM RevenueResourceEntry AS rre INNER JOIN RevenueEntry re ON rre.revenueEntry.revenueEntryId = re.revenueEntryId "
//			+ "INNER JOIN MilestoneEntry AS me ON rre.milestone_entry_id = me.milestoneEntryId "
			+ "INNER JOIN BusinessUnit AS bu ON rre.businessUnit.businessUnitId = bu.businessUnitId "
			+ "INNER JOIN StrategicBusinessUnit AS sbu ON rre.strategicBusinessUnit.sbuId = sbu.sbuId "
			+ "INNER JOIN StrategicBusinessUnitHead AS sbuhead ON rre.strategicBusinessUnitHead.sbuHeadId = sbuhead.sbuHeadId "
			+ "INNER JOIN BusinessDevelopmentManager AS bdm ON re.businessDevelopmentManager.bdmId = bdm.bdmId "
			+ "INNER JOIN BusinessType AS bt ON rre.businessType.businessTypeId = bt.businessTypeId "
			+ "INNER JOIN Account AS acc ON re.account.accountId = acc.accountId "
			+ "INNER JOIN Region AS reg ON re.region.regionId = reg.regionId "
			+ "INNER JOIN Location AS loc ON rre.location.locationId = loc.locationId "
			+ "INNER JOIN ProbabilityType AS pt ON re.probabilityType.probabilityTypeId = pt.probabilityTypeId "
			+ "INNER JOIN CocPractice AS coc ON rre.cocPractice.cocPracticeId = coc.cocPracticeId "
			+ "WHERE bu.businessUnitDisplayName = :#{#oppreq.businessUnit} AND "
			+ "sbu.sbuDisplayName = :#{#oppreq.strategicBusinessUnit} AND "
			+ "sbuhead.sbuHeadDisplayName = :#{#oppreq.strategicBusinessUnitHead} AND "
			+ "bdm.bdmDisplayName = :#{#oppreq.businessDevelopmentManager} AND "
			+ "bt.businessTypeDisplayName = :#{#oppreq.businessType} AND " + "acc.accountName = :#{#oppreq.account} AND "
			+ "reg.regionDisplayName = :#{#oppreq.region} AND " + "loc.locationDisplayName = :#{#oppreq.location} AND "
			+ "pt.probabilityTypeName = :#{#oppreq.probabilityType} AND " + "re.status = :#{#oppreq.status} ")
	public List<RevenueResourceEntry> getOpportunities(
			@Param("oppreq") OpportunityRevenueRequest opportunityRevenueRequest);

	@Query("FROM RevenueResourceEntry AS rre INNER JOIN RevenueEntry re ON rre.revenueEntry.revenueEntryId = re.revenueEntryId "
//			+ "INNER JOIN MilestoneEntry AS me ON rre.revenueResourceEntryId = me.milestoneEntryId "
			+ "INNER JOIN BusinessUnit AS bu ON rre.businessUnit.businessUnitId = bu.businessUnitId "
			+ "INNER JOIN StrategicBusinessUnit AS sbu ON rre.strategicBusinessUnit.sbuId = sbu.sbuId "
			+ "INNER JOIN StrategicBusinessUnitHead AS sbuhead ON rre.strategicBusinessUnitHead.sbuHeadId = sbuhead.sbuHeadId "
			+ "INNER JOIN BusinessDevelopmentManager AS bdm ON re.businessDevelopmentManager.bdmId = bdm.bdmId "
			+ "INNER JOIN BusinessType AS bt ON rre.businessType.businessTypeId = bt.businessTypeId "
			+ "INNER JOIN Account AS acc ON re.account.accountId = acc.accountId "
			+ "INNER JOIN Region AS reg ON re.region.regionId = reg.regionId "
			+ "INNER JOIN Location AS loc ON rre.location.locationId = loc.locationId "
			+ "INNER JOIN ProbabilityType AS pt ON re.probabilityType.probabilityTypeId = pt.probabilityTypeId "
			+ "INNER JOIN CocPractice AS coc ON rre.cocPractice.cocPracticeId = coc.cocPracticeId "
			+ "INNER JOIN Opportunity AS opp ON re.opportunity.opportunityId = opp.opportunityId "
			+ "WHERE bu.businessUnitDisplayName = :#{#resourcereq.businessUnit} AND "
			+ "sbu.sbuDisplayName = :#{#resourcereq.strategicBusinessUnit} AND "
			+ "sbuhead.sbuHeadDisplayName = :#{#resourcereq.strategicBusinessUnitHead} AND "
			+ "bdm.bdmDisplayName = :#{#resourcereq.businessDevelopmentManager} AND "
			+ "bt.businessTypeDisplayName = :#{#resourcereq.businessType} AND "
			+ "acc.accountName = :#{#resourcereq.account} AND " + "reg.regionDisplayName = :#{#resourcereq.region} AND "
			+ "loc.locationDisplayName = :#{#resourcereq.location} AND "
			+ "pt.probabilityTypeName = :#{#resourcereq.probabilityType} AND "
			+ "coc.cocPracticeName = :#{#resourcereq.cocPractice} AND " + "re.status = :#{#resourcereq.status} AND "
			+ "opp.projectCode = :#{#resourcereq.projectCode} AND "
			+ "opp.opportunityName = :#{#resourcereq.opportunityName} AND "
			+ "opp.projectStartDate = :#{#resourcereq.projectStartDate} AND "
			+ "opp.projectEndDate = :#{#resourcereq.projectEndDate} AND "
			+ "re.pricingType = :#{#resourcereq.pricingType} ")
	public List<RevenueResourceEntry> getResourcesByOpportunity(
			@Param("resourcereq") ResourceEntryRequest resourceRevenueRequest);

}
