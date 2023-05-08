package com.nous.rollingrevenue.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public RevenueEntryResponse getRevenueEntries(String financialYearName, boolean isDisplayAdditionalQuarter) {

		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(financialYearName).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

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
				financialYearRevenue = this.calculateFPRevenue(revenueFPResourceEntries, financialYear,
						isDisplayAdditionalQuarter);

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
			FinancialYear financialYear, boolean isDisplayAdditionalQuarter) {

		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();
		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();

		LocalDate fyStartDate = LocalDate.of(financialYear.getStartingFrom().getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYear.getEndingOn().getYear(), 3, 31);
		if (isDisplayAdditionalQuarter) {
			fyEndDate = LocalDate.of(financialYear.getEndingOn().getYear(), 6, 30);
		}

		List<RevenueResourceEntry> fyRevenueEntries = this
				.filterRevenueEntriesByStartDateAndEndDate(revenueFPResourceEntries, fyStartDate, fyEndDate);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);

		listOfMonthsBetweenFinancialYear = this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyEndDate,
				isDisplayAdditionalQuarter);

		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));

		for (RevenueResourceEntry revenueFPResourceEntry : fyRevenueEntries) {

			BigInteger resourceFPRevenue = this.getResourceFPRevenueInFYBaseCurrency(revenueFPResourceEntry,
					financialYear);

			MilestoneEntry milestoneEntry = revenueFPResourceEntry.getMilestoneEntry();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy", Locale.ENGLISH);

			String milestoneBillingDate = formatter.format(milestoneEntry.getMilestoneBillingDate());

			if (fyRevenue.containsKey(milestoneBillingDate)) {
				fyRevenue.put(milestoneBillingDate, fyRevenue.get(milestoneBillingDate).add(resourceFPRevenue));
			}

			financialYearRevenue.setDataMap(fyRevenue);
		}

		return financialYearRevenue;

	}

	private List<String> getListOfMonthsBetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy", Locale.ENGLISH);
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private List<RevenueResourceEntry> filterRevenueEntriesByStartDateAndEndDate(
			List<RevenueResourceEntry> revenueFPResourceEntries, LocalDate fyStartDate, LocalDate fyEndDate) {
		return revenueFPResourceEntries.stream()
				.filter(fpEntry -> !fpEntry.getMilestoneEntry().getMilestoneBillingDate().isBefore(fyStartDate)
						&& fpEntry.getMilestoneEntry().getMilestoneBillingDate().isBefore(fyEndDate))
				.collect(Collectors.toList());
	}

	private List<String> addQuarterFields(List<String> listOfMonthsBetweenFinancialYear, LocalDate fyEndDate,
			boolean isDisplayAdditionalQuarter) {
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyEndDate);

		listOfMonthsBetweenFinancialYear.add(3, "q1FYB " + year);
		listOfMonthsBetweenFinancialYear.add(4, "q1FYS " + year);
		listOfMonthsBetweenFinancialYear.add(5, "q1FYT " + year);

		listOfMonthsBetweenFinancialYear.add(9, "q2FYB " + year);
		listOfMonthsBetweenFinancialYear.add(10, "q2FYS " + year);
		listOfMonthsBetweenFinancialYear.add(11, "q2FYT " + year);

		listOfMonthsBetweenFinancialYear.add(15, "q3FYB " + year);
		listOfMonthsBetweenFinancialYear.add(16, "q3FYS " + year);
		listOfMonthsBetweenFinancialYear.add(17, "q3FYT " + year);

		listOfMonthsBetweenFinancialYear.add(21, "q4FYB " + year);
		listOfMonthsBetweenFinancialYear.add(22, "q4FYS " + year);
		listOfMonthsBetweenFinancialYear.add(23, "q4FYT " + year);

		if (isDisplayAdditionalQuarter) {
			listOfMonthsBetweenFinancialYear.add(27, "q5FYB " + year);
			listOfMonthsBetweenFinancialYear.add(28, "q5FYS " + year);
			listOfMonthsBetweenFinancialYear.add(29, "q5FYT " + year);
			listOfMonthsBetweenFinancialYear.add("FYB " + year);
			listOfMonthsBetweenFinancialYear.add("FYS " + year);
			listOfMonthsBetweenFinancialYear.add("FYT " + year);
			listOfMonthsBetweenFinancialYear.add("DiFF-FY " + year);
		} else {
			listOfMonthsBetweenFinancialYear.add(24, "FYB " + year);
			listOfMonthsBetweenFinancialYear.add(25, "FYS " + year);
			listOfMonthsBetweenFinancialYear.add(26, "FYT " + year);
			listOfMonthsBetweenFinancialYear.add(27, "DiFF-FY " + year);
		}
		return listOfMonthsBetweenFinancialYear;

	}

	private BigInteger getResourceFPRevenueInFYBaseCurrency(RevenueResourceEntry revenueFPResourceEntry,
			FinancialYear financialYear) {
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
		return resourceFPRevenue;
	}

}
