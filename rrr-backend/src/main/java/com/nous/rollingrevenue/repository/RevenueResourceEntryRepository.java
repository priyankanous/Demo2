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

	@Query("FROM RevenueResourceEntry AS rre INNER JOIN RevenueEntry re ON rre.revenueResourceEntryId = re.revenueEntryId "
			+ "INNER JOIN MilestoneEntry AS me ON rre.revenueResourceEntryId = me.milestoneEntryId "
			+ "INNER JOIN BusinessUnit AS bu ON rre.revenueResourceEntryId = bu.businessUnitId "
			+ "INNER JOIN StrategicBusinessUnit AS sbu ON rre.revenueResourceEntryId = sbu.sbuId "
			+ "INNER JOIN StrategicBusinessUnitHead AS sbuhead ON rre.revenueResourceEntryId = sbuhead.sbuHeadId "
			+ "INNER JOIN BusinessDevelopmentManager AS bdm ON rre.revenueResourceEntryId = bdm.bdmId "
			+ "INNER JOIN BusinessType AS bt ON rre.revenueResourceEntryId = bt.businessTypeId "
			+ "INNER JOIN Account AS acc ON rre.revenueResourceEntryId = acc.accountId "
			+ "INNER JOIN Region AS reg ON rre.revenueResourceEntryId = reg.regionId "
			+ "INNER JOIN Location AS loc ON rre.revenueResourceEntryId = loc.locationId "
			+ "INNER JOIN ProbabilityType AS pt ON rre.revenueResourceEntryId = pt.probabilityTypeId "
			+ "INNER JOIN CocPractice AS coc ON rre.revenueResourceEntryId = coc.cocPracticeId "
			+ "WHERE bu.businessUnitName = :#{#oppreq.businessUnit} AND "
			+ "sbu.sbuName = :#{#oppreq.strategicBusinessUnit} AND "
			+ "sbuhead.sbuHeadName = :#{#oppreq.strategicBusinessUnitHead} AND "
			+ "bdm.bdmName = :#{#oppreq.businessDevelopmentManager} AND "
			+ "bt.businessTypeName = :#{#oppreq.businessType} AND " + "acc.accountName = :#{#oppreq.account} AND "
			+ "reg.regionName = :#{#oppreq.region} AND " + "loc.locationName = :#{#oppreq.location} AND "
			+ "pt.probabilityTypeName = :#{#oppreq.probabilityType} AND " + "re.status = :#{#oppreq.status} ")
	public List<RevenueResourceEntry> getOpportunities(
			@Param("oppreq") OpportunityRevenueRequest opportunityRevenueRequest);

	@Query("FROM RevenueResourceEntry AS rre INNER JOIN RevenueEntry re ON rre.revenueResourceEntryId = re.revenueEntryId "
			+ "INNER JOIN MilestoneEntry AS me ON rre.revenueResourceEntryId = me.milestoneEntryId "
			+ "INNER JOIN BusinessUnit AS bu ON rre.revenueResourceEntryId = bu.businessUnitId "
			+ "INNER JOIN StrategicBusinessUnit AS sbu ON rre.revenueResourceEntryId = sbu.sbuId "
			+ "INNER JOIN StrategicBusinessUnitHead AS sbuhead ON rre.revenueResourceEntryId = sbuhead.sbuHeadId "
			+ "INNER JOIN BusinessDevelopmentManager AS bdm ON rre.revenueResourceEntryId = bdm.bdmId "
			+ "INNER JOIN BusinessType AS bt ON rre.revenueResourceEntryId = bt.businessTypeId "
			+ "INNER JOIN Account AS acc ON rre.revenueResourceEntryId = acc.accountId "
			+ "INNER JOIN Region AS reg ON rre.revenueResourceEntryId = reg.regionId "
			+ "INNER JOIN Location AS loc ON rre.revenueResourceEntryId = loc.locationId "
			+ "INNER JOIN ProbabilityType AS pt ON rre.revenueResourceEntryId = pt.probabilityTypeId "
			+ "INNER JOIN CocPractice AS coc ON rre.revenueResourceEntryId = coc.cocPracticeId "
			+ "INNER JOIN Opportunity AS opp ON rre.revenueResourceEntryId = opp.opportunityId "
			+ "WHERE bu.businessUnitName = :#{#resourcereq.businessUnit} AND "
			+ "sbu.sbuName = :#{#resourcereq.strategicBusinessUnit} AND "
			+ "sbuhead.sbuHeadName = :#{#resourcereq.strategicBusinessUnitHead} AND "
			+ "bdm.bdmName = :#{#resourcereq.businessDevelopmentManager} AND "
			+ "bt.businessTypeName = :#{#resourcereq.businessType} AND "
			+ "acc.accountName = :#{#resourcereq.account} AND " + "reg.regionName = :#{#resourcereq.region} AND "
			+ "loc.locationName = :#{#resourcereq.location} AND "
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
