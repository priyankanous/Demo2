package com.nous.rollingrevenue.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
import com.nous.rollingrevenue.model.Currency;
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
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.MilestoneEntryVO;
import com.nous.rollingrevenue.vo.RevenueEntryResponse;
import com.nous.rollingrevenue.vo.RevenueEntryVO;
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
					revenueResourceEntry.setRevenueEntry(savedRevenueEntry);
					revenueResourceEntryRepository.save(revenueResourceEntry);
				}
			}

		}

	}

	@Override
	public RevenueEntryResponse getRevenueEntries(String financialYearName) {

		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(financialYearName).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();

		Set<RevenueEntryVO> revenueEntriesVO = new HashSet<>();
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();
		RevenueEntryResponse revenueEntryResponse = new RevenueEntryResponse();

		List<RevenueResourceEntry> revenueResourceEntries = revenueResourceEntryRepository.findAll();

		Map<Boolean, List<RevenueResourceEntry>> partitionResourceEntriesByPricingType = this
				.getPartitionResourceEntriesByPricingType(revenueResourceEntries);

		Set<Entry<Boolean, List<RevenueResourceEntry>>> entrySet = partitionResourceEntriesByPricingType.entrySet();

		for (Entry<Boolean, List<RevenueResourceEntry>> entry : entrySet) {
			if (entry.getKey()) {

				List<RevenueResourceEntry> revenueFPResourceEntries = entry.getValue();
				financialYearRevenue = this.calculateFPRevenue(revenueFPResourceEntries, financialYearStartingFrom,
						financialYearEndingOn, financialYear);

			}
		}

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntries) {

			RevenueEntry revenueEntry = revenueResourceEntry.getRevenueEntry();

			RevenueEntryVO revenueEntryVO = new RevenueEntryVO();

			revenueEntryVO.setBusinessUnit(revenueResourceEntry.getBusinessUnit().getBusinessUnitName());
			revenueEntryVO.setStrategicBusinessUnit(revenueResourceEntry.getStrategicBusinessUnit().getSbuName());
			revenueEntryVO
					.setStrategicBusinessUnitHead(revenueResourceEntry.getStrategicBusinessUnitHead().getSbuHeadName());
			revenueEntryVO.setBusinessDevelopmentManager(revenueEntry.getBusinessDevelopmentManager().getBdmName());
			revenueEntryVO.setBusinessType(revenueResourceEntry.getBusinessType().getBusinessTypeName());
			revenueEntryVO.setAccount(revenueEntry.getAccount().getAccountName());
			revenueEntryVO.setRegion(revenueEntry.getRegion().getRegionName());
			revenueEntryVO.setLocation(revenueResourceEntry.getLocation().getLocationName());
			revenueEntryVO.setProbabilityType(revenueEntry.getProbabilityType().getProbabilityTypeName());
			revenueEntryVO.setCocPractice(
					Constants.NON_COC_BASED.equals(revenueResourceEntry.getCocPractice().getCocPracticeName())
							? Constants.NO
							: Constants.YES);
			revenueEntryVO.setStatus(revenueEntry.getStatus());

			revenueEntriesVO.add(revenueEntryVO);

		}

		revenueEntryResponse.setRevenueEntries(revenueEntriesVO);
		revenueEntryResponse.setFinancialYearRevenue(financialYearRevenue);

		return revenueEntryResponse;
	}

	private Map<Boolean, List<RevenueResourceEntry>> getPartitionResourceEntriesByPricingType(
			List<RevenueResourceEntry> revenueResourceEntries) {
		return revenueResourceEntries.stream()
				.collect(Collectors.partitioningBy(revenueResourceEntry -> Constants.PRICING_TYPE_FP
						.equals(revenueResourceEntry.getRevenueEntry().getPricingType())));
	}

	private FinancialYearRevenue calculateFPRevenue(List<RevenueResourceEntry> revenueFPResourceEntries,
			LocalDate financialYearStartingFrom, LocalDate financialYearEndingOn, FinancialYear financialYear) {

		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();

		List<RevenueResourceEntry> fyRevenueEntries = revenueFPResourceEntries.stream().filter(
				fpEntry -> !fpEntry.getMilestoneEntry().getMilestoneBillingDate().isBefore(financialYearStartingFrom)
						&& fpEntry.getMilestoneEntry().getMilestoneBillingDate().isBefore(financialYearEndingOn))
				.collect(Collectors.toList());

		for (RevenueResourceEntry revenueFPResourceEntry : fyRevenueEntries) {

			BigInteger resourceFPRevenue = revenueFPResourceEntry.getRevenue();
			List<Currency> currencies = financialYear.getCurrencies();
			Optional<Currency> baseCurrencyOfFinancialYear = currencies.stream()
					.filter(currency -> currency.isBaseCurrency() == true).findFirst();

			if (baseCurrencyOfFinancialYear.isPresent()) {
				Currency baseCurrency = baseCurrencyOfFinancialYear.get();
				if (!(baseCurrency.getCurrencyName()
						.equals(revenueFPResourceEntry.getRevenueEntry().getCurrency().getCurrencyName()))) {
					Currency resourceCurrencyType = currencies.stream().filter(currency -> revenueFPResourceEntry
							.getRevenueEntry().getCurrency().getCurrencyName().equals(currency.getCurrencyName()))
							.findFirst().get();
					resourceFPRevenue = new BigDecimal(resourceFPRevenue)
							.divide(resourceCurrencyType.getConversionRate(), RoundingMode.HALF_UP).toBigInteger();
				}
			}

			MilestoneEntry milestoneEntry = revenueFPResourceEntry.getMilestoneEntry();

			LocalDate milestoneBillingDate = milestoneEntry.getMilestoneBillingDate();

			switch (milestoneBillingDate.getMonth().toString()) {

			case "APRIL":
				financialYearRevenue.setApril(financialYearRevenue.getApril().add(resourceFPRevenue));
				break;

			case "MAY":
				financialYearRevenue.setMay(financialYearRevenue.getMay().add(resourceFPRevenue));
				break;

			case "JUNE":
				financialYearRevenue.setJune(financialYearRevenue.getJune().add(resourceFPRevenue));
				break;

			case "JULY":
				financialYearRevenue.setJuly(financialYearRevenue.getJuly().add(resourceFPRevenue));
				break;

			case "AUGUST":
				financialYearRevenue.setAugust(financialYearRevenue.getAugust().add(resourceFPRevenue));
				break;

			case "SEPTEMBER":
				financialYearRevenue.setSeptember(financialYearRevenue.getSeptember().add(resourceFPRevenue));
				break;

			case "OCTOBER":
				financialYearRevenue.setOctober(financialYearRevenue.getOctober().add(resourceFPRevenue));
				break;

			case "NOVEMBER":
				financialYearRevenue.setNovember(financialYearRevenue.getNovember().add(resourceFPRevenue));
				break;

			case "DECEMBER":
				financialYearRevenue.setDecember(financialYearRevenue.getDecember().add(resourceFPRevenue));
				break;

			case "JANUARY":
				financialYearRevenue.setJanuary(financialYearRevenue.getJanuary().add(resourceFPRevenue));
				break;

			case "FEBRUARY":
				financialYearRevenue.setFebruary(financialYearRevenue.getFebruary().add(resourceFPRevenue));
				break;

			case "MARCH":
				financialYearRevenue.setMarch(financialYearRevenue.getMarch().add(resourceFPRevenue));
				break;

			default:
				break;
			}
		}

		return financialYearRevenue;

	}

}
