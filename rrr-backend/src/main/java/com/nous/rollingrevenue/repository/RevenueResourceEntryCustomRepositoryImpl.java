package com.nous.rollingrevenue.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.model.BusinessType;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.Location;
import com.nous.rollingrevenue.model.ProbabilityType;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.model.StrategicBusinessUnit;
import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;
import com.nous.rollingrevenue.vo.BusinessTypeReportInDTO;
import com.nous.rollingrevenue.vo.BusinessTypeReportRequest;
import com.nous.rollingrevenue.vo.BusinessUnitReportInDTO;
import com.nous.rollingrevenue.vo.BusinessUnitReportRequest;
import com.nous.rollingrevenue.vo.ClientTypeReportInDTO;
import com.nous.rollingrevenue.vo.ClientTypeReportRequest;
import com.nous.rollingrevenue.vo.RegionReportInDTO;
import com.nous.rollingrevenue.vo.RegionReportRequest;
import com.nous.rollingrevenue.vo.SBUClientTypeReportInDTO;
import com.nous.rollingrevenue.vo.SBUClientTypeReportRequest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class RevenueResourceEntryCustomRepositoryImpl implements RevenueResourceEntryCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<RevenueResourceEntry> findRevenueResourceDetails(BusinessTypeReportRequest businessTypeReportRequest) {

		// Create query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RevenueResourceEntry> criteriaQuery = criteriaBuilder.createQuery(RevenueResourceEntry.class);
		// Define FROM clause
		Root<RevenueResourceEntry> root = criteriaQuery.from(RevenueResourceEntry.class);
		Join<RevenueResourceEntry, RevenueEntry> qualityJoin = root.join(Constants.REVENUE_ENTRY, JoinType.INNER);
		Join<RevenueEntry, FinancialYear> fin = qualityJoin.join(Constants.FINANCIAL_YEAR, JoinType.INNER);
		Join<RevenueEntry, Region> region = qualityJoin.join(Constants.REGION, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessUnit> business = root.join(Constants.BUSINESS_UNIT, JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnit> sbu = root.join(Constants.STRATEGIC_BUSINESS_UNIT,
				JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnitHead> sbuHead = root
				.join(Constants.STRATEGIC_BUSINESS_UNIT_HEAD, JoinType.INNER);
		Join<RevenueResourceEntry, Location> location = root.join(Constants.LOCATION, JoinType.INNER);
		Join<RevenueEntry, Account> account = qualityJoin.join(Constants.ACCOUNT, JoinType.INNER);
		Join<RevenueEntry, BusinessDevelopmentManager> bdm = qualityJoin.join(Constants.BUSINESS_DEVELOPMENT_MANAGER,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();
		BusinessTypeReportInDTO inDTO = businessTypeReportRequest.getData();
		if (inDTO.getFinancialYearName() != null)
			predicates.add(criteriaBuilder.equal(fin.get(Constants.FINANCIAL_YEAR_NAME), inDTO.getFinancialYearName()));
		if (inDTO.getRegionId() != null)
			predicates.add(criteriaBuilder.equal(region.get(Constants.REGION_ID), inDTO.getRegionId()));
		if (inDTO.getBusinessUnitId() != null)
			predicates.add(criteriaBuilder.equal(business.get(Constants.BUSINESS_UNIT_ID), inDTO.getBusinessUnitId()));
		if (inDTO.getSbuHeadId() != null)
			predicates.add(criteriaBuilder.equal(sbuHead.get(Constants.SBU_HEAD_ID), inDTO.getSbuHeadId()));
		if (inDTO.getSbuId() != null)
			predicates.add(criteriaBuilder.equal(sbu.get(Constants.SBU_ID), inDTO.getSbuId()));
		if (inDTO.getLocationId() != null)
			predicates.add(criteriaBuilder.equal(location.get(Constants.LOCATION_ID), inDTO.getLocationId()));
		if (inDTO.getAccountId() != null)
			predicates.add(criteriaBuilder.equal(account.get(Constants.ACCOUNT_ID), inDTO.getAccountId()));
		if (inDTO.getBdmId() != null)
			predicates.add(criteriaBuilder.equal(bdm.get(Constants.BDM_ID), inDTO.getBdmId()));

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<RevenueResourceEntry> findRevenueResourceDetailsByRegion(RegionReportRequest regionReportRequest) {

		// Create query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RevenueResourceEntry> criteriaQuery = criteriaBuilder.createQuery(RevenueResourceEntry.class);
		// Define FROM clause
		Root<RevenueResourceEntry> root = criteriaQuery.from(RevenueResourceEntry.class);
		Join<RevenueResourceEntry, RevenueEntry> qualityJoin = root.join(Constants.REVENUE_ENTRY, JoinType.INNER);
		Join<RevenueEntry, FinancialYear> fin = qualityJoin.join(Constants.FINANCIAL_YEAR, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessUnit> business = root.join(Constants.BUSINESS_UNIT, JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnit> sbu = root.join(Constants.STRATEGIC_BUSINESS_UNIT,
				JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnitHead> sbuHead = root
				.join(Constants.STRATEGIC_BUSINESS_UNIT_HEAD, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessType> businessType = root.join(Constants.BUSINESS_TYPE, JoinType.INNER);
		Join<RevenueEntry, ProbabilityType> probabilityType = qualityJoin.join(Constants.PROBABILITY_TYPE,
				JoinType.INNER);
		Join<RevenueResourceEntry, Location> location = root.join(Constants.LOCATION, JoinType.INNER);
		Join<RevenueEntry, Account> account = qualityJoin.join(Constants.ACCOUNT, JoinType.INNER);
		Join<RevenueEntry, BusinessDevelopmentManager> bdm = qualityJoin.join(Constants.BUSINESS_DEVELOPMENT_MANAGER,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();
		RegionReportInDTO inDTO = regionReportRequest.getData();
		if (inDTO.getFinancialYearName() != null)
			predicates.add(criteriaBuilder.equal(fin.get(Constants.FINANCIAL_YEAR_NAME), inDTO.getFinancialYearName()));
		if (inDTO.getBusinessUnitId() != null)
			predicates.add(criteriaBuilder.equal(business.get(Constants.BUSINESS_UNIT_ID), inDTO.getBusinessUnitId()));
		if (inDTO.getSbuHeadId() != null)
			predicates.add(criteriaBuilder.equal(sbuHead.get(Constants.SBU_HEAD_ID), inDTO.getSbuHeadId()));
		if (inDTO.getSbuId() != null)
			predicates.add(criteriaBuilder.equal(sbu.get(Constants.SBU_ID), inDTO.getSbuId()));
		if (inDTO.getBusinessTypeId() != null)
			predicates.add(
					criteriaBuilder.equal(businessType.get(Constants.BUSINESS_TYPE_ID), inDTO.getBusinessTypeId()));
		if (inDTO.getProbabilityTypeId() != null)
			predicates.add(criteriaBuilder.equal(probabilityType.get(Constants.PROBABILITY_TYPE_ID),
					inDTO.getProbabilityTypeId()));
		if (inDTO.getLocationId() != null)
			predicates.add(criteriaBuilder.equal(location.get(Constants.LOCATION_ID), inDTO.getLocationId()));
		if (inDTO.getAccountId() != null)
			predicates.add(criteriaBuilder.equal(account.get(Constants.ACCOUNT_ID), inDTO.getAccountId()));
		if (inDTO.getBdmId() != null)
			predicates.add(criteriaBuilder.equal(bdm.get(Constants.BDM_ID), inDTO.getBdmId()));

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<RevenueResourceEntry> findRevenueResourceDetailsByClient(
			ClientTypeReportRequest clientTypeReportRequest) {

		// Create query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RevenueResourceEntry> criteriaQuery = criteriaBuilder.createQuery(RevenueResourceEntry.class);
		// Define FROM clause
		Root<RevenueResourceEntry> root = criteriaQuery.from(RevenueResourceEntry.class);
		Join<RevenueResourceEntry, RevenueEntry> qualityJoin = root.join(Constants.REVENUE_ENTRY, JoinType.INNER);
		Join<RevenueEntry, FinancialYear> fin = qualityJoin.join(Constants.FINANCIAL_YEAR, JoinType.INNER);
		Join<RevenueEntry, Region> region = qualityJoin.join(Constants.REGION, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessUnit> business = root.join(Constants.BUSINESS_UNIT, JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnit> sbu = root.join(Constants.STRATEGIC_BUSINESS_UNIT,
				JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnitHead> sbuHead = root
				.join(Constants.STRATEGIC_BUSINESS_UNIT_HEAD, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessType> businessType = root.join(Constants.BUSINESS_TYPE, JoinType.INNER);
		Join<RevenueEntry, ProbabilityType> probabilityType = qualityJoin.join(Constants.PROBABILITY_TYPE,
				JoinType.INNER);
		Join<RevenueResourceEntry, Location> location = root.join(Constants.LOCATION, JoinType.INNER);
		Join<RevenueEntry, Account> account = qualityJoin.join(Constants.ACCOUNT, JoinType.INNER);
		Join<RevenueEntry, BusinessDevelopmentManager> bdm = qualityJoin.join(Constants.BUSINESS_DEVELOPMENT_MANAGER,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();
		ClientTypeReportInDTO inDTO = clientTypeReportRequest.getData();
		if (inDTO.getFinancialYearName() != null)
			predicates.add(criteriaBuilder.equal(fin.get(Constants.FINANCIAL_YEAR_NAME), inDTO.getFinancialYearName()));
		if (inDTO.getRegionId() != null)
			predicates.add(criteriaBuilder.equal(region.get(Constants.REGION_ID), inDTO.getRegionId()));
		if (inDTO.getBusinessUnitId() != null)
			predicates.add(criteriaBuilder.equal(business.get(Constants.BUSINESS_UNIT_ID), inDTO.getBusinessUnitId()));
		if (inDTO.getSbuHeadId() != null)
			predicates.add(criteriaBuilder.equal(sbuHead.get(Constants.SBU_HEAD_ID), inDTO.getSbuHeadId()));
		if (inDTO.getSbuId() != null)
			predicates.add(criteriaBuilder.equal(sbu.get(Constants.SBU_ID), inDTO.getSbuId()));
		if (inDTO.getBusinessTypeId() != null)
			predicates.add(
					criteriaBuilder.equal(businessType.get(Constants.BUSINESS_TYPE_ID), inDTO.getBusinessTypeId()));
		if (inDTO.getProbabilityTypeId() != null)
			predicates.add(criteriaBuilder.equal(probabilityType.get(Constants.PROBABILITY_TYPE_ID),
					inDTO.getProbabilityTypeId()));
		if (inDTO.getLocationId() != null)
			predicates.add(criteriaBuilder.equal(location.get(Constants.LOCATION_ID), inDTO.getLocationId()));
		if (inDTO.getAccountId() != null)
			predicates.add(criteriaBuilder.equal(account.get(Constants.ACCOUNT_ID), inDTO.getAccountId()));
		if (inDTO.getBdmId() != null)
			predicates.add(criteriaBuilder.equal(bdm.get(Constants.BDM_ID), inDTO.getBdmId()));

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<RevenueResourceEntry> findRevenueResourceDetailsForBUOrSBU(
			BusinessUnitReportRequest businessUnitReportRequest) {

		// Create query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RevenueResourceEntry> criteriaQuery = criteriaBuilder.createQuery(RevenueResourceEntry.class);
		// Define FROM clause
		Root<RevenueResourceEntry> root = criteriaQuery.from(RevenueResourceEntry.class);
		Join<RevenueResourceEntry, RevenueEntry> qualityJoin = root.join(Constants.REVENUE_ENTRY, JoinType.INNER);
		Join<RevenueEntry, FinancialYear> fin = qualityJoin.join(Constants.FINANCIAL_YEAR, JoinType.INNER);
		Join<RevenueEntry, Region> region = qualityJoin.join(Constants.REGION, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessUnit> business = root.join(Constants.BUSINESS_UNIT, JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnitHead> sbuHead = root
				.join(Constants.STRATEGIC_BUSINESS_UNIT_HEAD, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessType> bt = root.join(Constants.BUSINESS_TYPE, JoinType.INNER);
		Join<RevenueEntry, ProbabilityType> pt = qualityJoin.join(Constants.PROBABILITY_TYPE, JoinType.INNER);
		Join<RevenueResourceEntry, Location> location = root.join(Constants.LOCATION, JoinType.INNER);
		Join<RevenueEntry, BusinessDevelopmentManager> bdm = qualityJoin.join(Constants.BUSINESS_DEVELOPMENT_MANAGER,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();
		BusinessUnitReportInDTO inDTO = businessUnitReportRequest.getData();
		if (inDTO.getFinancialYearName() != null)
			predicates.add(criteriaBuilder.equal(fin.get(Constants.FINANCIAL_YEAR_NAME), inDTO.getFinancialYearName()));
		if (inDTO.getRegionId() != null)
			predicates.add(criteriaBuilder.equal(region.get(Constants.REGION_ID), inDTO.getRegionId()));
		if (inDTO.getBusinessUnitId() != null)
			predicates.add(criteriaBuilder.equal(business.get(Constants.BUSINESS_UNIT_ID), inDTO.getBusinessUnitId()));
		if (inDTO.getSbuHeadId() != null)
			predicates.add(criteriaBuilder.equal(sbuHead.get(Constants.SBU_HEAD_ID), inDTO.getSbuHeadId()));
		if (inDTO.getBusinessTypeId() != null)
			predicates.add(criteriaBuilder.equal(bt.get(Constants.BUSINESS_TYPE_ID), inDTO.getBusinessTypeId()));
		if (inDTO.getProbabilityTypeId() != null)
			predicates.add(criteriaBuilder.equal(pt.get(Constants.PROBABILITY_TYPE_ID), inDTO.getProbabilityTypeId()));
		if (inDTO.getLocationId() != null)
			predicates.add(criteriaBuilder.equal(location.get(Constants.LOCATION_ID), inDTO.getLocationId()));
		if (inDTO.getBdmId() != null)
			predicates.add(criteriaBuilder.equal(bdm.get(Constants.BDM_ID), inDTO.getBdmId()));

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override

	public List<RevenueResourceEntry> findRevenueResourceDetailsForSBUClient(
			SBUClientTypeReportRequest sbuClientTypeReportRequest) {
		// Create query
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<RevenueResourceEntry> criteriaQuery = criteriaBuilder.createQuery(RevenueResourceEntry.class);
		// Define FROM clause
		Root<RevenueResourceEntry> root = criteriaQuery.from(RevenueResourceEntry.class);
		Join<RevenueResourceEntry, RevenueEntry> qualityJoin = root.join(Constants.REVENUE_ENTRY, JoinType.INNER);
		Join<RevenueEntry, FinancialYear> fin = qualityJoin.join(Constants.FINANCIAL_YEAR, JoinType.INNER);
		Join<RevenueEntry, Region> region = qualityJoin.join(Constants.REGION, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessUnit> business = root.join(Constants.BUSINESS_UNIT, JoinType.INNER);
		Join<RevenueResourceEntry, StrategicBusinessUnitHead> sbuHead = root
				.join(Constants.STRATEGIC_BUSINESS_UNIT_HEAD, JoinType.INNER);
		Join<RevenueResourceEntry, BusinessType> bt = root.join(Constants.BUSINESS_TYPE, JoinType.INNER);
		Join<RevenueEntry, ProbabilityType> pt = qualityJoin.join(Constants.PROBABILITY_TYPE, JoinType.INNER);
		Join<RevenueResourceEntry, Location> location = root.join(Constants.LOCATION, JoinType.INNER);
		Join<RevenueEntry, BusinessDevelopmentManager> bdm = qualityJoin.join(Constants.BUSINESS_DEVELOPMENT_MANAGER,
				JoinType.INNER);

		List<Predicate> predicates = new ArrayList<>();
		SBUClientTypeReportInDTO inDTO = sbuClientTypeReportRequest.getData();

		if (inDTO.getFinancialYearName() != null)
			predicates.add(criteriaBuilder.equal(fin.get(Constants.FINANCIAL_YEAR_NAME), inDTO.getFinancialYearName()));
		if (inDTO.getRegionId() != null)
			predicates.add(criteriaBuilder.equal(region.get(Constants.REGION_ID), inDTO.getRegionId()));
		if (inDTO.getBusinessUnitId() != null)
			predicates.add(criteriaBuilder.equal(business.get(Constants.BUSINESS_UNIT_ID), inDTO.getBusinessUnitId()));
		if (inDTO.getSbuHeadId() != null)
			predicates.add(criteriaBuilder.equal(sbuHead.get(Constants.SBU_HEAD_ID), inDTO.getSbuHeadId()));
		if (inDTO.getBussinessTypeId() != null)
			predicates.add(criteriaBuilder.equal(bt.get("bussinessTypeId"), inDTO.getBussinessTypeId()));
		if (inDTO.getProbabilityTypeId() != null)
			predicates.add(criteriaBuilder.equal(pt.get("probabilityTypeId"), inDTO.getProbabilityTypeId()));
		if (inDTO.getLocationId() != null)
			predicates.add(criteriaBuilder.equal(location.get(Constants.LOCATION_ID), inDTO.getLocationId()));
		if (inDTO.getBdmId() != null)
			predicates.add(criteriaBuilder.equal(bdm.get(Constants.BDM_ID), inDTO.getBdmId()));

		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}

}
