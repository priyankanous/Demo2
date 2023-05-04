package com.nous.rollingrevenue.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.AccountConverter;
import com.nous.rollingrevenue.convertor.BusinessDevelopmentManagerConverter;
import com.nous.rollingrevenue.convertor.BusinessTypeConverter;
import com.nous.rollingrevenue.convertor.BusinessUnitConverter;
import com.nous.rollingrevenue.convertor.CocPracticeConverter;
import com.nous.rollingrevenue.convertor.CurrencyConverter;
import com.nous.rollingrevenue.convertor.FinancialYearConverter;
import com.nous.rollingrevenue.convertor.LeaveLossFactorConverter;
import com.nous.rollingrevenue.convertor.LocationConverter;
import com.nous.rollingrevenue.convertor.OpportunityConverter;
import com.nous.rollingrevenue.convertor.ProbabilityTypeConverter;
import com.nous.rollingrevenue.convertor.RegionConverter;
import com.nous.rollingrevenue.convertor.StrategicBusinessUnitConverter;
import com.nous.rollingrevenue.convertor.StrategicBusinessUnitHeadConverter;
import com.nous.rollingrevenue.convertor.WorkOrderConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.MilestoneEntry;
import com.nous.rollingrevenue.model.Opportunity;
import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.MilestoneEntryRepository;
import com.nous.rollingrevenue.repository.OpportunityRepository;
import com.nous.rollingrevenue.repository.RevenueEntryRespository;
import com.nous.rollingrevenue.repository.RevenueResourceEntryRepository;
import com.nous.rollingrevenue.service.RevenueService;
import com.nous.rollingrevenue.vo.FPRevenueEntryVO;
import com.nous.rollingrevenue.vo.MilestoneEntryVO;
import com.nous.rollingrevenue.vo.RevenueEntryResponse;
import com.nous.rollingrevenue.vo.RevenueResourceEntryVO;
import com.nous.rollingrevenue.vo.TandMRevenueEntryVO;

@Service
@Transactional(readOnly = true)
public class RevenueServiceImpl implements RevenueService {

	@Autowired
	private RevenueEntryRespository revenueEntryRespository;

	@Autowired
	private RevenueResourceEntryRepository revenueResourceEntryRepository;

	@Autowired
	private MilestoneEntryRepository milestoneEntryRepository;

	@Autowired
	private OpportunityRepository opportunityRepository;

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Override
	@Transactional
	public void saveTandMRevenueEntry(TandMRevenueEntryVO tmRevenueEntry) {

		RevenueEntry revenueEntry = new RevenueEntry();

		revenueEntry.setAccount(AccountConverter.convertAccountVOToAccount(tmRevenueEntry.getAccount()));

		Opportunity opportunity = OpportunityConverter
				.convertOpportunityVOToOpportunity(tmRevenueEntry.getOpportunity());

		if (Objects.isNull(opportunity.getOpportunityId())) {
			opportunity = opportunityRepository.save(opportunity);
		}
		revenueEntry.setOpportunity(opportunity);

		revenueEntry.setBusinessDevelopmentManager(
				BusinessDevelopmentManagerConverter.convertBdmVOToBdm(tmRevenueEntry.getBusinessDevelopmentManager()));
		revenueEntry.setCurrency(CurrencyConverter.convertCurrencyVOToCurrency(tmRevenueEntry.getCurrency()));
		revenueEntry.setProbabilityType(ProbabilityTypeConverter
				.convertProbabilityTypeVOToProbabilityType(tmRevenueEntry.getProbabilityType()));
		revenueEntry.setRegion(RegionConverter.convertRegionVOToRegion(tmRevenueEntry.getRegion()));
		revenueEntry.setWorkOrder(WorkOrderConverter.convertWorkOrderVOToWorkOrder(tmRevenueEntry.getWorkOrder()));
		revenueEntry.setFinancialYear(
				FinancialYearConverter.convertFinancialYearVOToFinancialYear(tmRevenueEntry.getFinancialYear()));
		revenueEntry.setResourceCount(tmRevenueEntry.getResourceCount());
		revenueEntry.setPricingType(tmRevenueEntry.getPricingType());
		revenueEntry.setRemarks(tmRevenueEntry.getRemarks());
		revenueEntry.setStatus(tmRevenueEntry.getStatus());

		RevenueEntry savedRevenueEntry = revenueEntryRespository.save(revenueEntry);

		List<RevenueResourceEntryVO> revenueResourceEntriesVO = tmRevenueEntry.getRevenueResourceEntries();

		List<RevenueResourceEntry> revenueResourceEntries = new ArrayList<>();

		if (!revenueResourceEntriesVO.isEmpty()) {

			for (RevenueResourceEntryVO revenueResourceEntryVO : revenueResourceEntriesVO) {

				RevenueResourceEntry revenueResourceEntry = new RevenueResourceEntry();

				revenueResourceEntry.setStrategicBusinessUnit(StrategicBusinessUnitConverter
						.convertSBUVOToSBU(revenueResourceEntryVO.getStrategicBusinessUnit()));
				revenueResourceEntry.setStrategicBusinessUnitHead(StrategicBusinessUnitHeadConverter
						.convertSBUHeadVOToSBUHead(revenueResourceEntryVO.getStrategicBusinessUnitHead()));
				revenueResourceEntry.setBusinessUnit(BusinessUnitConverter
						.convertBusinessUnitVOToBusinessUnit(revenueResourceEntryVO.getBusinessUnit()));
				revenueResourceEntry.setLocation(
						LocationConverter.convertLocationVOToLocation(revenueResourceEntryVO.getLocation()));
				revenueResourceEntry.setResourceName(revenueResourceEntryVO.getResourceName());
				revenueResourceEntry.setEmployeeId(revenueResourceEntryVO.getEmployeeId());
				revenueResourceEntry.setResourceStartDate(revenueResourceEntryVO.getResourceStartDate());
				revenueResourceEntry.setResourceEndDate(revenueResourceEntryVO.getResourceEndDate());
				revenueResourceEntry.setCocPractice(CocPracticeConverter
						.convertCocPracticeVOToCocPractice(revenueResourceEntryVO.getCocPractice()));
				revenueResourceEntry.setBillingRateType(revenueResourceEntryVO.getBillingRateType());
				revenueResourceEntry.setBillingRate(revenueResourceEntryVO.getBillingRate());
				revenueResourceEntry.setLeaveLossFactor(LeaveLossFactorConverter
						.convertLeaveLossFactorVOToLeaveLossFactor(revenueResourceEntryVO.getLeaveLossFactor()));
				revenueResourceEntry.setBusinessType(BusinessTypeConverter
						.convertBusinessTypeVOToBusinessType(revenueResourceEntryVO.getBusinessType()));
				revenueResourceEntry.setAllocation(revenueResourceEntryVO.getAllocation());
				revenueResourceEntry.setRevenueEntry(savedRevenueEntry);
				revenueResourceEntries.add(revenueResourceEntry);
			}
		}

		revenueResourceEntryRepository.saveAll(revenueResourceEntries);

	}

	@Override
	@Transactional
	public void saveFPRevenueEntry(FPRevenueEntryVO fpRevenueEntry) {

		RevenueEntry revenueEntry = new RevenueEntry();

		revenueEntry.setAccount(AccountConverter.convertAccountVOToAccount(fpRevenueEntry.getAccount()));

		Opportunity opportunity = OpportunityConverter
				.convertOpportunityVOToOpportunity(fpRevenueEntry.getOpportunity());

		if (Objects.isNull(opportunity.getOpportunityId())) {
			opportunity = opportunityRepository.save(opportunity);
		}

		revenueEntry.setOpportunity(opportunity);
		revenueEntry.setBusinessDevelopmentManager(
				BusinessDevelopmentManagerConverter.convertBdmVOToBdm(fpRevenueEntry.getBusinessDevelopmentManager()));
		revenueEntry.setCurrency(CurrencyConverter.convertCurrencyVOToCurrency(fpRevenueEntry.getCurrency()));
		revenueEntry.setProbabilityType(ProbabilityTypeConverter
				.convertProbabilityTypeVOToProbabilityType(fpRevenueEntry.getProbabilityType()));
		revenueEntry.setRegion(RegionConverter.convertRegionVOToRegion(fpRevenueEntry.getRegion()));
		revenueEntry.setWorkOrder(WorkOrderConverter.convertWorkOrderVOToWorkOrder(fpRevenueEntry.getWorkOrder()));
		revenueEntry.setFinancialYear(
				FinancialYearConverter.convertFinancialYearVOToFinancialYear(fpRevenueEntry.getFinancialYear()));
		revenueEntry.setMilestoneCount(fpRevenueEntry.getMilestoneCount());
		revenueEntry.setPricingType(fpRevenueEntry.getPricingType());
		revenueEntry.setRemarks(fpRevenueEntry.getRemarks());
		revenueEntry.setStatus(fpRevenueEntry.getStatus());

		RevenueEntry savedRevenueEntry = revenueEntryRespository.save(revenueEntry);

		List<MilestoneEntryVO> milestoneEntriesVO = fpRevenueEntry.getMilestones();

		for (MilestoneEntryVO milestoneEntryVO : milestoneEntriesVO) {

			MilestoneEntry milestoneEntry = new MilestoneEntry();

			milestoneEntry.setMilestoneNumber(milestoneEntryVO.getMilestoneNumber());
			milestoneEntry.setMilestoneBillingDate(milestoneEntryVO.getMilestoneBillingDate());
			milestoneEntry.setMilestoneRevenue(milestoneEntryVO.getMilestoneRevenue());
			milestoneEntry.setMilestoneResourceCount(milestoneEntryVO.getMilestoneResourceCount());
			milestoneEntry.setRevenueEntry(savedRevenueEntry);
			MilestoneEntry savedMilestoneEntry = milestoneEntryRepository.save(milestoneEntry);

			List<RevenueResourceEntryVO> revenueResourceEntriesVO = milestoneEntryVO.getRevenueResourceEntries();

			if (!revenueResourceEntriesVO.isEmpty()) {
				for (RevenueResourceEntryVO revenueResourceEntryVO : revenueResourceEntriesVO) {

					RevenueResourceEntry revenueResourceEntry = new RevenueResourceEntry();

					revenueResourceEntry.setStrategicBusinessUnit(StrategicBusinessUnitConverter
							.convertSBUVOToSBU(revenueResourceEntryVO.getStrategicBusinessUnit()));
					revenueResourceEntry.setStrategicBusinessUnitHead(StrategicBusinessUnitHeadConverter
							.convertSBUHeadVOToSBUHead(revenueResourceEntryVO.getStrategicBusinessUnitHead()));
					revenueResourceEntry.setBusinessUnit(BusinessUnitConverter
							.convertBusinessUnitVOToBusinessUnit(revenueResourceEntryVO.getBusinessUnit()));
					revenueResourceEntry.setLocation(
							LocationConverter.convertLocationVOToLocation(revenueResourceEntryVO.getLocation()));
					revenueResourceEntry.setResourceName(revenueResourceEntryVO.getResourceName());
					revenueResourceEntry.setEmployeeId(revenueResourceEntryVO.getEmployeeId());
					revenueResourceEntry.setResourceStartDate(revenueResourceEntryVO.getResourceStartDate());
					revenueResourceEntry.setResourceEndDate(revenueResourceEntryVO.getResourceEndDate());
					revenueResourceEntry.setCocPractice(CocPracticeConverter
							.convertCocPracticeVOToCocPractice(revenueResourceEntryVO.getCocPractice()));
					revenueResourceEntry.setRevenue(revenueResourceEntryVO.getMilestoneResourceRevenue());
					revenueResourceEntry.setBusinessType(BusinessTypeConverter
							.convertBusinessTypeVOToBusinessType(revenueResourceEntryVO.getBusinessType()));
					revenueResourceEntry.setAllocation(revenueResourceEntryVO.getAllocation());
					revenueResourceEntry.setMilestoneEntry(savedMilestoneEntry);
					revenueResourceEntryRepository.save(revenueResourceEntry);
				}
			}

		}

	}

	@Override
	public Set<RevenueEntryResponse> getRevenueEntries(String financialYearName) {
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(financialYearName).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));
		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();

		Set<RevenueEntryResponse> revenueEntriesResponse = new HashSet<>();

		List<RevenueResourceEntry> revenueResourceEntries = revenueResourceEntryRepository.findAll();

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntries) {

			RevenueEntry revenueEntry = revenueResourceEntry.getRevenueEntry();

			RevenueEntryResponse revenueEntryResponse = new RevenueEntryResponse();

			revenueEntryResponse.setBusinessUnit(revenueResourceEntry.getBusinessUnit().getBusinessUnitName());
			revenueEntryResponse.setStrategicBusinessUnit(revenueResourceEntry.getStrategicBusinessUnit().getSbuName());
			revenueEntryResponse
					.setStrategicBusinessUnitHead(revenueResourceEntry.getStrategicBusinessUnitHead().getSbuHeadName());
			revenueEntryResponse
					.setBusinessDevelopmentManager(revenueEntry.getBusinessDevelopmentManager().getBdmName());
			revenueEntryResponse.setBusinessType(revenueResourceEntry.getBusinessType().getBusinessTypeName());
			revenueEntryResponse.setAccount(revenueEntry.getAccount().getAccountName());
			revenueEntryResponse.setRegion(revenueEntry.getRegion().getRegionName());
			revenueEntryResponse.setLocation(revenueResourceEntry.getLocation().getLocationName());
			revenueEntryResponse.setProbabilityType(revenueEntry.getProbabilityType().getProbabilityTypeName());
			revenueEntryResponse.setCocPractice(
					Constants.NON_COC_BASED.equals(revenueResourceEntry.getCocPractice().getCocPracticeName())
							? Constants.NO
							: Constants.YES);
			revenueEntryResponse.setStatus(revenueEntry.getStatus());

			revenueEntriesResponse.add(revenueEntryResponse);

		}

		return revenueEntriesResponse;
	}

}
