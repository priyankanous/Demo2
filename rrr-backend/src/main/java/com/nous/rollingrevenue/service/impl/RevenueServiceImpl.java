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
import com.nous.rollingrevenue.vo.FPResourceEntryVO;
import com.nous.rollingrevenue.vo.FPRevenueEntryVO;
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;
import com.nous.rollingrevenue.vo.MilestoneEntryVO;
import com.nous.rollingrevenue.vo.OpportunityEntryResponse;
import com.nous.rollingrevenue.vo.OpportunityEntryVO;
import com.nous.rollingrevenue.vo.OpportunityRevenueRequest;
import com.nous.rollingrevenue.vo.ResourceEntryRequest;
import com.nous.rollingrevenue.vo.ResourceEntryResponse;
import com.nous.rollingrevenue.vo.ResourceRevenueRequest;
import com.nous.rollingrevenue.vo.ResourceRevenueResponse;
import com.nous.rollingrevenue.vo.RevenueEntryResponse;
import com.nous.rollingrevenue.vo.RevenueEntryVO;
import com.nous.rollingrevenue.vo.RevenueResourceEntryVO;
import com.nous.rollingrevenue.vo.RollingRevenueResponse;
import com.nous.rollingrevenue.vo.TandMResourceEntryVO;
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

	@Autowired
	private RevenueServiceTMCalculation tmCalculation;

	@Autowired
	private RevenueServiceTMCalculation revenueServiceTMCalculation;

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
				revenueResourceEntry.setLeaveLossFactor(revenueResourceEntryVO.getLeaveLossFactor());
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
		FinancialYearTMRevenue financialYearTMRevenue = new FinancialYearTMRevenue();
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
			} else {
				List<RevenueResourceEntry> revenueEntryList = entry.getValue();
				financialYearTMRevenue = tmCalculation.calculateTMRevenue(revenueEntryList, financialYear,
						isDisplayAdditionalQuarter);
			}
		}

		Map<String, BigInteger> map = financialYearRevenue.getDataMap();
		Map<String, BigInteger> dataMap = financialYearTMRevenue.getDataMap();
		for (String key : dataMap.keySet()) {
			if (map.containsKey(key)) {
				map.put(key, map.get(key).add(dataMap.get(key)));
			}
		}
		financialYearRevenue.setDataMap(map);

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntries) {

			RevenueEntry revenueEntry = revenueResourceEntry.getRevenueEntry();

			RevenueEntryVO revenueEntryVO = new RevenueEntryVO();

			revenueEntryVO.setRevenueEntryId(revenueEntry.getRevenueEntryId());

			revenueEntryVO.setBusinessUnit(revenueResourceEntry.getBusinessUnit().getBusinessUnitDisplayName());
			revenueEntryVO
					.setStrategicBusinessUnit(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName());
			revenueEntryVO.setStrategicBusinessUnitHead(
					revenueResourceEntry.getStrategicBusinessUnitHead().getSbuHeadDisplayName());
			revenueEntryVO
					.setBusinessDevelopmentManager(revenueEntry.getBusinessDevelopmentManager().getBdmDisplayName());
			revenueEntryVO.setBusinessType(revenueResourceEntry.getBusinessType().getBusinessTypeDisplayName());
			revenueEntryVO.setAccount(revenueEntry.getAccount().getAccountName());
			revenueEntryVO.setRegion(revenueEntry.getRegion().getRegionDisplayName());
			revenueEntryVO.setLocation(revenueResourceEntry.getLocation().getLocationDisplayName());
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
		revenueEntryResponse.setFinancialYearName(financialYearName);

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

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();

		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		if (isDisplayAdditionalQuarter) {
			fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 6, 30);
			financialYearEndingOn = fyEndDate;
		}

		List<RevenueResourceEntry> fyRevenueEntries = this.filterRevenueEntriesByStartDateAndEndDate(
				revenueFPResourceEntries, financialYearStartingFrom, financialYearEndingOn);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);

		listOfMonthsBetweenFinancialYear = this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyStartDate,
				isDisplayAdditionalQuarter);

		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));

		for (RevenueResourceEntry revenueFPResourceEntry : fyRevenueEntries) {

			BigInteger resourceFPRevenue = this.getResourceFPRevenueInFYBaseCurrency(revenueFPResourceEntry,
					financialYear);

			MilestoneEntry milestoneEntry = revenueFPResourceEntry.getMilestoneEntry();

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);

			String milestoneBillingDate = formatter.format(milestoneEntry.getMilestoneBillingDate());

			if (fyRevenue.containsKey(milestoneBillingDate)) {
				fyRevenue.put(milestoneBillingDate, fyRevenue.get(milestoneBillingDate).add(resourceFPRevenue));
			}

		}
		revenueServiceTMCalculation.setQuarterlyDetails(fyRevenue, isDisplayAdditionalQuarter);
		financialYearRevenue.setDataMap(fyRevenue);
		return financialYearRevenue;

	}

	private List<String> getListOfMonthsBetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private List<RevenueResourceEntry> filterRevenueEntriesByStartDateAndEndDate(
			List<RevenueResourceEntry> revenueFPResourceEntries, LocalDate financialYearStartingFrom,
			LocalDate financialYearEndingOn) {
		return revenueFPResourceEntries.stream()
				.filter(fpEntry -> !fpEntry.getMilestoneEntry().getMilestoneBillingDate()
						.isBefore(financialYearStartingFrom.minusDays(1))
						&& fpEntry.getMilestoneEntry().getMilestoneBillingDate()
								.isBefore(financialYearEndingOn.plusDays(1)))
				.collect(Collectors.toList());
	}

	private List<String> addQuarterFields(List<String> listOfMonthsBetweenFinancialYear, LocalDate fyEndDate,
			boolean isDisplayAdditionalQuarter) {
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyEndDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;

		listOfMonthsBetweenFinancialYear.add(3, "q1FYP " + year);
		listOfMonthsBetweenFinancialYear.add(4, "q1FYA " + year);
		listOfMonthsBetweenFinancialYear.add(5, "q1FYB " + year);
		listOfMonthsBetweenFinancialYear.add(6, "q1FYS " + year);
		listOfMonthsBetweenFinancialYear.add(7, "q1FYT " + year);

		listOfMonthsBetweenFinancialYear.add(11, "q2FYP " + year);
		listOfMonthsBetweenFinancialYear.add(12, "q2FYA " + year);
		listOfMonthsBetweenFinancialYear.add(13, "q2FYB " + year);
		listOfMonthsBetweenFinancialYear.add(14, "q2FYS " + year);
		listOfMonthsBetweenFinancialYear.add(15, "q2FYT " + year);

		listOfMonthsBetweenFinancialYear.add(19, "q3FYP " + year);
		listOfMonthsBetweenFinancialYear.add(20, "q3FYA " + year);
		listOfMonthsBetweenFinancialYear.add(21, "q3FYB " + year);
		listOfMonthsBetweenFinancialYear.add(22, "q3FYS " + year);
		listOfMonthsBetweenFinancialYear.add(23, "q3FYT " + year);

		listOfMonthsBetweenFinancialYear.add(27, "q4FYP " + additionalQuarterYear);
		listOfMonthsBetweenFinancialYear.add(28, "q4FYA " + additionalQuarterYear);
		listOfMonthsBetweenFinancialYear.add(29, "q4FYB " + additionalQuarterYear);
		listOfMonthsBetweenFinancialYear.add(30, "q4FYS " + additionalQuarterYear);
		listOfMonthsBetweenFinancialYear.add(31, "q4FYT " + additionalQuarterYear);

		if (isDisplayAdditionalQuarter) {
			listOfMonthsBetweenFinancialYear.add(35, "q5FYP " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add(36, "q5FYB " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add(37, "q5FYS " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add(38, "q5FYT " + additionalQuarterYear);

			listOfMonthsBetweenFinancialYear.add("FYP " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("FYB " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("FYS " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("FYT " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("DiFF-FY " + additionalQuarterYear);
		} else {
			listOfMonthsBetweenFinancialYear.add("FYP " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("FYB " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("FYS " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("FYT " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add("DiFF-FY " + additionalQuarterYear);
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

	@Override
	public OpportunityEntryResponse getOpportunities(OpportunityRevenueRequest opportunityRevenueRequest,
			boolean isDisplayAdditionalQuarter) {

		Set<OpportunityEntryVO> opportunityEntriesVO = new HashSet<>();
		OpportunityEntryResponse opportunityEntryResponse = new OpportunityEntryResponse();
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();
		FinancialYearTMRevenue financialYearTMRevenue = new FinancialYearTMRevenue();
		FinancialYear financialYear = financialYearRepository
				.findByFinancialYearName(opportunityRevenueRequest.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		List<RevenueResourceEntry> revenueResourceEntries = revenueResourceEntryRepository
				.getOpportunities(opportunityRevenueRequest);

		if (Constants.NO.equals(opportunityRevenueRequest.getCocPractice())) {
			revenueResourceEntries = revenueResourceEntries.stream()
					.filter(revenueResourceEntry -> Constants.NON_COC_BASED
							.equals(revenueResourceEntry.getCocPractice().getCocPracticeName()))
					.collect(Collectors.toList());
		} else {
			revenueResourceEntries = revenueResourceEntries.stream()
					.filter(revenueResourceEntry -> (!Constants.NON_COC_BASED
							.equals(revenueResourceEntry.getCocPractice().getCocPracticeName())))
					.collect(Collectors.toList());
		}

		Map<Boolean, List<RevenueResourceEntry>> partitionResourceEntriesByPricingType = this
				.getPartitionResourceEntriesByPricingType(revenueResourceEntries);

		Set<Entry<Boolean, List<RevenueResourceEntry>>> entrySet = partitionResourceEntriesByPricingType.entrySet();

		for (Entry<Boolean, List<RevenueResourceEntry>> entry : entrySet) {
			if (entry.getKey()) {

				List<RevenueResourceEntry> revenueFPResourceEntries = entry.getValue();
				financialYearRevenue = this.calculateFPRevenue(revenueFPResourceEntries, financialYear,
						isDisplayAdditionalQuarter);
			} else {
				List<RevenueResourceEntry> revenueEntryList = entry.getValue();
				financialYearTMRevenue = tmCalculation.calculateTMRevenue(revenueEntryList, financialYear,
						isDisplayAdditionalQuarter);
			}

		}

		Map<String, BigInteger> map = financialYearRevenue.getDataMap();
		Map<String, BigInteger> dataMap = financialYearTMRevenue.getDataMap();
		for (String key : dataMap.keySet()) {
			if (map.containsKey(key)) {
				map.put(key, map.get(key).add(dataMap.get(key)));
			}
		}
		financialYearRevenue.setDataMap(map);

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntries) {

			OpportunityEntryVO opportunityEntryVO = new OpportunityEntryVO();

			opportunityEntryVO
					.setOpportunityId(revenueResourceEntry.getRevenueEntry().getOpportunity().getOpportunityId());
			opportunityEntryVO.setProjectCode(revenueResourceEntry.getRevenueEntry().getOpportunity().getProjectCode());
			opportunityEntryVO
					.setOpportunityName(revenueResourceEntry.getRevenueEntry().getOpportunity().getOpportunityName());
			opportunityEntryVO
					.setProjectStartDate(revenueResourceEntry.getRevenueEntry().getOpportunity().getProjectStartDate());
			opportunityEntryVO
					.setProjectEndDate(revenueResourceEntry.getRevenueEntry().getOpportunity().getProjectEndDate());
			opportunityEntryVO.setPricingType(revenueResourceEntry.getRevenueEntry().getPricingType());
			opportunityEntryVO.setCocPractice(revenueResourceEntry.getCocPractice().getCocPracticeName());

			opportunityEntryVO.setNoOfResources(
					this.getNoOfResourcesForOpportunity(opportunityRevenueRequest, opportunityEntryVO));

			if (Constants.PRICING_TYPE_FP.equals(revenueResourceEntry.getRevenueEntry().getPricingType())) {
				opportunityEntryVO.setLeaveLossFactor("Not Applicable");
			} else {
				if ("Offshore".equalsIgnoreCase(revenueResourceEntry.getLocation().getLocationName())) {
					opportunityEntryVO.setLeaveLossFactor(revenueResourceEntry.getLeaveLossFactor().toString());
				} else {
					opportunityEntryVO.setLeaveLossFactor(revenueResourceEntry.getLeaveLossFactor().toString());
				}
			}

			opportunityEntriesVO.add(opportunityEntryVO);
		}

		opportunityEntryResponse.setOpportunities(opportunityEntriesVO);
		opportunityEntryResponse.setFinancialYearRevenue(financialYearRevenue);
		opportunityEntryResponse.setFinancialYearName(opportunityRevenueRequest.getFinancialYearName());

		return opportunityEntryResponse;
	}

	private int getNoOfResourcesForOpportunity(OpportunityRevenueRequest opportunityRevenueRequest,
			OpportunityEntryVO opportunityEntryVO) {

		ResourceEntryRequest resourceEntryRequest = new ResourceEntryRequest();
		resourceEntryRequest.setBusinessUnit(opportunityRevenueRequest.getBusinessUnit());
		resourceEntryRequest.setStrategicBusinessUnit(opportunityRevenueRequest.getStrategicBusinessUnit());
		resourceEntryRequest.setStrategicBusinessUnitHead(opportunityRevenueRequest.getStrategicBusinessUnitHead());
		resourceEntryRequest.setBusinessDevelopmentManager(opportunityRevenueRequest.getBusinessDevelopmentManager());
		resourceEntryRequest.setBusinessType(opportunityRevenueRequest.getBusinessType());
		resourceEntryRequest.setAccount(opportunityRevenueRequest.getAccount());
		resourceEntryRequest.setRegion(opportunityRevenueRequest.getRegion());
		resourceEntryRequest.setLocation(opportunityRevenueRequest.getLocation());
		resourceEntryRequest.setProbabilityType(opportunityRevenueRequest.getProbabilityType());
		resourceEntryRequest.setStatus(opportunityRevenueRequest.getStatus());
		resourceEntryRequest.setFinancialYearName(opportunityRevenueRequest.getFinancialYearName());
		resourceEntryRequest.setProjectCode(opportunityEntryVO.getProjectCode());
		resourceEntryRequest.setOpportunityName(opportunityEntryVO.getOpportunityName());
		resourceEntryRequest.setProjectStartDate(opportunityEntryVO.getProjectStartDate());
		resourceEntryRequest.setProjectEndDate(opportunityEntryVO.getProjectEndDate());
		resourceEntryRequest.setPricingType(opportunityEntryVO.getPricingType());
		resourceEntryRequest.setCocPractice(opportunityEntryVO.getCocPractice());

		List<RevenueResourceEntry> opportunityResources = revenueResourceEntryRepository
				.getResourcesByOpportunity(resourceEntryRequest);

		return opportunityResources.size();

	}

	@Override
	public ResourceEntryResponse getResourcesByOpportunity(ResourceEntryRequest resourceEntryRequest,
			boolean isDisplayAdditionalQuarter) {

		List<TandMResourceEntryVO> tmResourceEntriesVO = new ArrayList<>();
		List<FPResourceEntryVO> fpResourceEntriesVO = new ArrayList<>();
		ResourceEntryResponse resourceEntryResponse = new ResourceEntryResponse();
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();
		FinancialYearTMRevenue financialYearTMRevenue = new FinancialYearTMRevenue();

		FinancialYear financialYear = financialYearRepository
				.findByFinancialYearName(resourceEntryRequest.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		List<RevenueResourceEntry> revenueResourceEntries = revenueResourceEntryRepository
				.getResourcesByOpportunity(resourceEntryRequest);

		if (Constants.PRICING_TYPE_FP.equals(resourceEntryRequest.getPricingType())) {

			financialYearRevenue = this.calculateFPRevenue(revenueResourceEntries, financialYear,
					isDisplayAdditionalQuarter);

			for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntries) {

				FPResourceEntryVO fpResourceEntry = new FPResourceEntryVO();
				fpResourceEntry.setResourceStartDate(revenueResourceEntry.getResourceStartDate());
				fpResourceEntry.setResourceEndDate(revenueResourceEntry.getResourceEndDate());
				fpResourceEntry.setMilestoneNumber(revenueResourceEntry.getMilestoneEntry().getMilestoneNumber());
				fpResourceEntry
						.setWorkOrderNumber(revenueResourceEntry.getRevenueEntry().getWorkOrder().getWorkOrderNumber());
				fpResourceEntry.setEmployeeId(revenueResourceEntry.getEmployeeId());
				fpResourceEntry.setResourceName(revenueResourceEntry.getResourceName());
				fpResourceEntry
						.setMilestoneBillingDate(revenueResourceEntry.getMilestoneEntry().getMilestoneBillingDate());
				fpResourceEntry.setRevenue(revenueResourceEntry.getRevenue());
				fpResourceEntry.setAllocation(revenueResourceEntry.getAllocation());
				fpResourceEntry.setLeaveLossFactor(Constants.NOT_APPLICABLE);

				fpResourceEntriesVO.add(fpResourceEntry);
			}
			resourceEntryResponse.setFpResourceEntries(fpResourceEntriesVO);
			resourceEntryResponse.setFinancialYearRevenue(financialYearRevenue);

		} else {

			financialYearTMRevenue = tmCalculation.calculateTMRevenue(revenueResourceEntries, financialYear,
					isDisplayAdditionalQuarter);

			for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntries) {

				TandMResourceEntryVO tmResourceEntry = new TandMResourceEntryVO();
				tmResourceEntry.setResourceStartDate(revenueResourceEntry.getResourceStartDate());
				tmResourceEntry.setResourceEndDate(revenueResourceEntry.getResourceEndDate());
				tmResourceEntry
						.setWorkOrderNumber(revenueResourceEntry.getRevenueEntry().getWorkOrder().getWorkOrderNumber());
				tmResourceEntry.setEmployeeId(revenueResourceEntry.getEmployeeId());
				tmResourceEntry.setResourceName(revenueResourceEntry.getResourceName());
				tmResourceEntry.setBillingRate(revenueResourceEntry.getBillingRate());// Add billing rate conversion and
				tmResourceEntry.setAllocation(revenueResourceEntry.getAllocation());
				// test leave loss factor logic
				tmResourceEntry.setLeaveLossFactor(revenueResourceEntry.getLeaveLossFactor().toString());

				tmResourceEntriesVO.add(tmResourceEntry);
			}
			resourceEntryResponse.setTmResourceEntries(tmResourceEntriesVO);
			resourceEntryResponse.setFinancialYearTMRevenue(financialYearTMRevenue);
		}

		resourceEntryResponse.setFinancialYearName(resourceEntryRequest.getFinancialYearName());

		return resourceEntryResponse;

	}

	@Override
	public ResourceRevenueResponse getResourceRevenue(ResourceRevenueRequest resourceRevenueRequest,
			boolean isDisplayAdditionalQuarter) {
		ResourceRevenueResponse resourceRevenueResponse = new ResourceRevenueResponse();
		FinancialYearTMRevenue financialYearTMRevenue = new FinancialYearTMRevenue();
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();
		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();

		FinancialYear financialYear = financialYearRepository
				.findByFinancialYearName(resourceRevenueRequest.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		RevenueResourceEntry revenueResourceEntry = revenueResourceEntryRepository
				.getResourcesRevenue(resourceRevenueRequest);

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		String financialYearName = financialYear.getFinancialYearName();
		resourceRevenueResponse.setFinancialYearName(financialYearName);

		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		if (isDisplayAdditionalQuarter) {
			fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 6, 30);
			financialYearEndingOn = fyEndDate;
		}

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		listOfMonthsBetweenFinancialYear = this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyStartDate,
				isDisplayAdditionalQuarter);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));

		if (revenueResourceEntry != null)
			if (Constants.PRICING_TYPE_FP.equals(resourceRevenueRequest.getPricingType())) {
				BigInteger resourceFPRevenue = this.getResourceFPRevenueInFYBaseCurrency(revenueResourceEntry,
						financialYear);

				MilestoneEntry milestoneEntry = revenueResourceEntry.getMilestoneEntry();

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);

				String milestoneBillingDate = formatter.format(milestoneEntry.getMilestoneBillingDate());

				if (fyRevenue.containsKey(milestoneBillingDate)) {
					fyRevenue.put(milestoneBillingDate, fyRevenue.get(milestoneBillingDate).add(resourceFPRevenue));
				}
				revenueServiceTMCalculation.setQuarterlyDetails(fyRevenue, isDisplayAdditionalQuarter);
				financialYearRevenue.setDataMap(fyRevenue);
				resourceRevenueResponse.setFinancialYearRevenue(financialYearRevenue);

			} else {
				long leaveLossFactor = 0;
				if ("Offshore".equalsIgnoreCase(revenueResourceEntry.getLocation().getLocationName())) {
					leaveLossFactor = revenueResourceEntry.getLeaveLossFactor();
				} else {
					leaveLossFactor = revenueResourceEntry.getLeaveLossFactor();
				}

				BigInteger billingRate = revenueServiceTMCalculation.calculatingBillingRate(financialYear,
						revenueResourceEntry.getRevenueEntry().getCurrency().getCurrencyId(),
						revenueResourceEntry.getBillingRate());
				revenueServiceTMCalculation.monthlyBillingSeparation(financialYearName,
						revenueResourceEntry.getResourceStartDate(), revenueResourceEntry.getResourceEndDate(),
						revenueResourceEntry.getBillingRateType(), billingRate, leaveLossFactor,
						isDisplayAdditionalQuarter, fyRevenue);

				revenueServiceTMCalculation.setQuarterlyDetails(fyRevenue, isDisplayAdditionalQuarter);
				financialYearTMRevenue.setDataMap(fyRevenue);
				resourceRevenueResponse.setFinancialYearTMRevenue(financialYearTMRevenue);
			}
		return resourceRevenueResponse;
	}

	@Override
	@Transactional
	public void updateFPRevenueEntry(Long opportunityId, FPRevenueEntryVO fpRevenueEntry) {

		Opportunity opportunity = opportunityRepository.findById(opportunityId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + opportunityId));

		Opportunity opportunityVO = opportunityRepository.findById(fpRevenueEntry.getOpportunity().getOpportunityId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + opportunityId));

		opportunity.setOpportunityName(opportunityVO.getOpportunityName());
		opportunity.setProjectCode(opportunityVO.getProjectCode());
		opportunity.setProjectStartDate(opportunityVO.getProjectStartDate());
		opportunity.setProjectEndDate(opportunityVO.getProjectEndDate());
		opportunity = opportunityRepository.save(opportunity);

		List<RevenueEntry> revenueEntryList = opportunity.getRevenueEntry();

		for (RevenueEntry revenueEntry : revenueEntryList) {
			revenueEntry.setAccount(AccountConverter.convertAccountVOToAccount(fpRevenueEntry.getAccount()));

			revenueEntry.setOpportunity(opportunity);
			revenueEntry.setBusinessDevelopmentManager(BusinessDevelopmentManagerConverter
					.convertBdmVOToBdm(fpRevenueEntry.getBusinessDevelopmentManager()));
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

			List<MilestoneEntry> milestoneEntryList = revenueEntry.getMilestoneEntry();

			List<MilestoneEntryVO> milestoneEntriesVO = fpRevenueEntry.getMilestones();

			for (MilestoneEntryVO milestoneEntryVO : milestoneEntriesVO) {
				for (MilestoneEntry milestoneEntry : milestoneEntryList) {
					if (milestoneEntry.getMilestoneEntryId() == milestoneEntryVO.getMilestoneEntryId()) {
						milestoneEntry.setMilestoneNumber(milestoneEntryVO.getMilestoneNumber());
						milestoneEntry.setMilestoneBillingDate(milestoneEntryVO.getMilestoneBillingDate());
						milestoneEntry.setMilestoneRevenue(milestoneEntryVO.getMilestoneRevenue());
						milestoneEntry.setMilestoneResourceCount(milestoneEntryVO.getMilestoneResourceCount());
						milestoneEntry.setRevenueEntry(savedRevenueEntry);
						MilestoneEntry savedMilestoneEntry = milestoneEntryRepository.save(milestoneEntry);

						List<RevenueResourceEntryVO> revenueResourceEntriesVO = milestoneEntryVO
								.getRevenueResourceEntries();
						List<RevenueResourceEntry> revenueResourceEntryList = milestoneEntry.getRevenueResourceEntry();
						if (!revenueResourceEntriesVO.isEmpty()) {
							for (RevenueResourceEntryVO revenueResourceEntryVO : revenueResourceEntriesVO) {
								for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
									if (revenueResourceEntry.getRevenueResourceEntryId() == revenueResourceEntryVO
											.getRevenueResourceEntryId()) {
										revenueResourceEntry.setStrategicBusinessUnit(StrategicBusinessUnitConverter
												.convertSBUVOToSBU(revenueResourceEntryVO.getStrategicBusinessUnit()));
										revenueResourceEntry.setStrategicBusinessUnitHead(
												StrategicBusinessUnitHeadConverter.convertSBUHeadVOToSBUHead(
														revenueResourceEntryVO.getStrategicBusinessUnitHead()));
										revenueResourceEntry.setBusinessUnit(
												BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(
														revenueResourceEntryVO.getBusinessUnit()));
										revenueResourceEntry.setLocation(LocationConverter
												.convertLocationVOToLocation(revenueResourceEntryVO.getLocation()));
										revenueResourceEntry.setResourceName(revenueResourceEntryVO.getResourceName());
										revenueResourceEntry.setEmployeeId(revenueResourceEntryVO.getEmployeeId());
										revenueResourceEntry
												.setResourceStartDate(revenueResourceEntryVO.getResourceStartDate());
										revenueResourceEntry
												.setResourceEndDate(revenueResourceEntryVO.getResourceEndDate());
										revenueResourceEntry
												.setCocPractice(CocPracticeConverter.convertCocPracticeVOToCocPractice(
														revenueResourceEntryVO.getCocPractice()));
										revenueResourceEntry
												.setRevenue(revenueResourceEntryVO.getMilestoneResourceRevenue());
										revenueResourceEntry.setBusinessType(
												BusinessTypeConverter.convertBusinessTypeVOToBusinessType(
														revenueResourceEntryVO.getBusinessType()));
										revenueResourceEntry.setAllocation(revenueResourceEntryVO.getAllocation());
										revenueResourceEntry.setMilestoneEntry(savedMilestoneEntry);
										revenueResourceEntry.setRevenueEntry(savedRevenueEntry);
										revenueResourceEntryRepository.save(revenueResourceEntry);
									}
								}
							}
						}

					}
				}

			}
		}
	}

	@Override
	@Transactional
	public void updateTandMRevenueEntry(Long opportunityId, TandMRevenueEntryVO tandMRevenueEntry) {
		Opportunity opportunity = opportunityRepository.findById(opportunityId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + opportunityId));

		Opportunity opportunityVO = opportunityRepository
				.findById(tandMRevenueEntry.getOpportunity().getOpportunityId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + opportunityId));

		opportunity.setOpportunityName(opportunityVO.getOpportunityName());
		opportunity.setProjectCode(opportunityVO.getProjectCode());
		opportunity.setProjectStartDate(opportunityVO.getProjectStartDate());
		opportunity.setProjectEndDate(opportunityVO.getProjectEndDate());
		opportunity = opportunityRepository.save(opportunity);
		List<RevenueEntry> revenueEntryList = opportunity.getRevenueEntry();

		for (RevenueEntry revenueEntry : revenueEntryList) {
			revenueEntry.setAccount(AccountConverter.convertAccountVOToAccount(tandMRevenueEntry.getAccount()));
			revenueEntry.setOpportunity(opportunity);
			revenueEntry.setBusinessDevelopmentManager(BusinessDevelopmentManagerConverter
					.convertBdmVOToBdm(tandMRevenueEntry.getBusinessDevelopmentManager()));
			revenueEntry.setCurrency(CurrencyConverter.convertCurrencyVOToCurrency(tandMRevenueEntry.getCurrency()));
			revenueEntry.setProbabilityType(ProbabilityTypeConverter
					.convertProbabilityTypeVOToProbabilityType(tandMRevenueEntry.getProbabilityType()));
			revenueEntry.setRegion(RegionConverter.convertRegionVOToRegion(tandMRevenueEntry.getRegion()));
			revenueEntry
					.setWorkOrder(WorkOrderConverter.convertWorkOrderVOToWorkOrder(tandMRevenueEntry.getWorkOrder()));
			revenueEntry.setFinancialYear(
					FinancialYearConverter.convertFinancialYearVOToFinancialYear(tandMRevenueEntry.getFinancialYear()));
			revenueEntry.setPricingType(tandMRevenueEntry.getPricingType());
			revenueEntry.setRemarks(tandMRevenueEntry.getRemarks());
			revenueEntry.setStatus(tandMRevenueEntry.getStatus());
			RevenueEntry savedRevenueEntry = revenueEntryRespository.save(revenueEntry);
			List<RevenueResourceEntryVO> revenueResourceEntriesVO = tandMRevenueEntry.getRevenueResourceEntries();
			List<RevenueResourceEntry> revenueResourceEntryList = revenueEntry.getRevenueResourceEntry();
			if (!revenueResourceEntriesVO.isEmpty()) {
				for (RevenueResourceEntryVO revenueResourceEntryVO : revenueResourceEntriesVO) {
					for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
						if (revenueResourceEntry.getRevenueResourceEntryId() == revenueResourceEntryVO
								.getRevenueResourceEntryId()) {
							revenueResourceEntry.setStrategicBusinessUnit(StrategicBusinessUnitConverter
									.convertSBUVOToSBU(revenueResourceEntryVO.getStrategicBusinessUnit()));
							revenueResourceEntry.setStrategicBusinessUnitHead(StrategicBusinessUnitHeadConverter
									.convertSBUHeadVOToSBUHead(revenueResourceEntryVO.getStrategicBusinessUnitHead()));
							revenueResourceEntry.setBusinessUnit(BusinessUnitConverter
									.convertBusinessUnitVOToBusinessUnit(revenueResourceEntryVO.getBusinessUnit()));
							revenueResourceEntry.setLocation(LocationConverter
									.convertLocationVOToLocation(revenueResourceEntryVO.getLocation()));
							revenueResourceEntry.setResourceName(revenueResourceEntryVO.getResourceName());
							revenueResourceEntry.setEmployeeId(revenueResourceEntryVO.getEmployeeId());
							revenueResourceEntry.setResourceStartDate(revenueResourceEntryVO.getResourceStartDate());
							revenueResourceEntry.setResourceEndDate(revenueResourceEntryVO.getResourceEndDate());
							revenueResourceEntry.setCocPractice(CocPracticeConverter
									.convertCocPracticeVOToCocPractice(revenueResourceEntryVO.getCocPractice()));
							revenueResourceEntry.setBusinessType(BusinessTypeConverter
									.convertBusinessTypeVOToBusinessType(revenueResourceEntryVO.getBusinessType()));
							revenueResourceEntry.setAllocation(revenueResourceEntryVO.getAllocation());
							revenueResourceEntry.setRevenueEntry(savedRevenueEntry);
							revenueResourceEntryRepository.save(revenueResourceEntry);
						}
					}
				}
			}
		}
	}

	@Override
	public RollingRevenueResponse getRevenueEntryDetailsById(Long opportunityId) {
		var rollingRevenueResponse = new RollingRevenueResponse();
		Opportunity opportunity = opportunityRepository.findById(opportunityId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + opportunityId));

		List<RevenueEntry> revenueEntryList = opportunity.getRevenueEntry();

		for (RevenueEntry revenueEntry : revenueEntryList) {
			if (Constants.PRICING_TYPE_FP.equals(revenueEntry.getPricingType())) {
				FPRevenueEntryVO fpRevenueEntryVO = new FPRevenueEntryVO();
				fpRevenueEntryVO.setFpRevenueEntryId(revenueEntry.getRevenueEntryId());
				fpRevenueEntryVO.setAccount(AccountConverter.convertAccountToAccountVO(revenueEntry.getAccount()));
				fpRevenueEntryVO.setOpportunity(
						OpportunityConverter.convertOpportunityToOpportunityVO(revenueEntry.getOpportunity()));
				fpRevenueEntryVO.setProjectCode(revenueEntry.getOpportunity().getProjectCode());
				fpRevenueEntryVO.setProjectStartDate(revenueEntry.getOpportunity().getProjectStartDate());
				fpRevenueEntryVO.setProjectEndDate(revenueEntry.getOpportunity().getProjectEndDate());
				fpRevenueEntryVO.setBusinessDevelopmentManager(BusinessDevelopmentManagerConverter
						.convertBdmToBdmVO(revenueEntry.getBusinessDevelopmentManager()));
				fpRevenueEntryVO.setCurrency(CurrencyConverter.convertCurrencyToCurrencyVO(revenueEntry.getCurrency()));
				fpRevenueEntryVO.setProbabilityType(ProbabilityTypeConverter
						.convertProbabilityTypeToProbabilityTypeVO(revenueEntry.getProbabilityType()));
				fpRevenueEntryVO.setRegion(RegionConverter.convertRegionToRegionVO(revenueEntry.getRegion()));
				fpRevenueEntryVO
						.setWorkOrder(WorkOrderConverter.convertWorkOrderToWorkOrderVO(revenueEntry.getWorkOrder()));
				fpRevenueEntryVO.setWorkOrderEndDate(revenueEntry.getWorkOrder().getWorkOrderEndDate());
				fpRevenueEntryVO.setWorkOrderStatus(revenueEntry.getWorkOrder().getWoStatus());
				fpRevenueEntryVO.setFinancialYear(
						FinancialYearConverter.convertFinancialYearToFinancialYearVO(revenueEntry.getFinancialYear()));
				fpRevenueEntryVO.setMilestoneCount(revenueEntry.getMilestoneCount());
				fpRevenueEntryVO.setPricingType(revenueEntry.getPricingType());
				fpRevenueEntryVO.setRemarks(revenueEntry.getRemarks());
				fpRevenueEntryVO.setStatus(revenueEntry.getStatus());

				List<MilestoneEntryVO> milestones = new ArrayList<>();
				List<RevenueResourceEntryVO> revenueResourceEntries = new ArrayList<>();
				List<MilestoneEntry> milestoneEntryList = revenueEntry.getMilestoneEntry();
				if (!milestoneEntryList.isEmpty()) {
					for (MilestoneEntry milestoneEntry : milestoneEntryList) {
						MilestoneEntryVO milestoneEntryVO = new MilestoneEntryVO();
						milestoneEntryVO.setMilestoneEntryId(milestoneEntry.getMilestoneEntryId());
						milestoneEntryVO.setMilestoneNumber(milestoneEntry.getMilestoneNumber());
						milestoneEntryVO.setMilestoneBillingDate(milestoneEntry.getMilestoneBillingDate());
						milestoneEntryVO.setMilestoneRevenue(milestoneEntry.getMilestoneRevenue());
						milestoneEntryVO.setMilestoneResourceCount(milestoneEntry.getMilestoneResourceCount());

						List<RevenueResourceEntry> revenueResourceEntryList = milestoneEntry.getRevenueResourceEntry();
						for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
							RevenueResourceEntryVO revenueResourceEntryVO = new RevenueResourceEntryVO();
							revenueResourceEntryVO
									.setRevenueResourceEntryId(revenueResourceEntry.getRevenueResourceEntryId());
							revenueResourceEntryVO.setStrategicBusinessUnit(StrategicBusinessUnitConverter
									.convertSBUToSBUVO(revenueResourceEntry.getStrategicBusinessUnit()));
							revenueResourceEntryVO.setStrategicBusinessUnitHead(StrategicBusinessUnitHeadConverter
									.convertSBUHeadToSBUHeadVO(revenueResourceEntry.getStrategicBusinessUnitHead()));
							revenueResourceEntryVO.setBusinessUnit(BusinessUnitConverter
									.convertBusinessUnitToBusinessUnitVO(revenueResourceEntry.getBusinessUnit()));
							revenueResourceEntryVO.setBusinessType(BusinessTypeConverter
									.convertBusinessTypeToBusinessTypeVO(revenueResourceEntry.getBusinessType()));
							revenueResourceEntryVO.setLocation(
									LocationConverter.convertLocationToLocationVO(revenueResourceEntry.getLocation()));
							revenueResourceEntryVO.setResourceName(revenueResourceEntry.getResourceName());
							revenueResourceEntryVO.setEmployeeId(revenueResourceEntry.getEmployeeId());
							revenueResourceEntryVO.setResourceStartDate(revenueResourceEntry.getResourceStartDate());
							revenueResourceEntryVO.setResourceEndDate(revenueResourceEntry.getResourceEndDate());
							revenueResourceEntryVO.setMilestoneResourceRevenue(revenueResourceEntry.getBillingRate());
							revenueResourceEntryVO.setAllocation(revenueResourceEntry.getAllocation());
							revenueResourceEntries.add(revenueResourceEntryVO);
						}
						milestoneEntryVO.setRevenueResourceEntries(revenueResourceEntries);
						milestones.add(milestoneEntryVO);
					}
				}
				fpRevenueEntryVO.setMilestones(milestones);
				rollingRevenueResponse.setFpRevenueEntryVO(fpRevenueEntryVO);
			} else {
				TandMRevenueEntryVO tmRevenueEntryVO = new TandMRevenueEntryVO();
				tmRevenueEntryVO.setTmRevenueEntryId(revenueEntry.getRevenueEntryId());
				tmRevenueEntryVO.setAccount(AccountConverter.convertAccountToAccountVO(revenueEntry.getAccount()));
				tmRevenueEntryVO.setOpportunity(
						OpportunityConverter.convertOpportunityToOpportunityVO(revenueEntry.getOpportunity()));
				tmRevenueEntryVO.setProjectCode(revenueEntry.getOpportunity().getProjectCode());
				tmRevenueEntryVO.setProjectStartDate(revenueEntry.getOpportunity().getProjectStartDate());
				tmRevenueEntryVO.setProjectEndDate(revenueEntry.getOpportunity().getProjectEndDate());
				tmRevenueEntryVO.setBusinessDevelopmentManager(BusinessDevelopmentManagerConverter
						.convertBdmToBdmVO(revenueEntry.getBusinessDevelopmentManager()));
				tmRevenueEntryVO.setCurrency(CurrencyConverter.convertCurrencyToCurrencyVO(revenueEntry.getCurrency()));
				tmRevenueEntryVO.setProbabilityType(ProbabilityTypeConverter
						.convertProbabilityTypeToProbabilityTypeVO(revenueEntry.getProbabilityType()));
				tmRevenueEntryVO.setRegion(RegionConverter.convertRegionToRegionVO(revenueEntry.getRegion()));
				tmRevenueEntryVO
						.setWorkOrder(WorkOrderConverter.convertWorkOrderToWorkOrderVO(revenueEntry.getWorkOrder()));
				tmRevenueEntryVO.setWorkOrderEndDate(revenueEntry.getWorkOrder().getWorkOrderEndDate());
				tmRevenueEntryVO.setWorkOrderStatus(revenueEntry.getWorkOrder().getWoStatus());
				tmRevenueEntryVO.setFinancialYear(
						FinancialYearConverter.convertFinancialYearToFinancialYearVO(revenueEntry.getFinancialYear()));
				tmRevenueEntryVO.setResourceCount(revenueEntry.getResourceCount());
				tmRevenueEntryVO.setPricingType(revenueEntry.getPricingType());
				tmRevenueEntryVO.setRemarks(revenueEntry.getRemarks());
				tmRevenueEntryVO.setStatus(revenueEntry.getStatus());

				List<RevenueResourceEntry> revenueResourceEntryList = revenueEntry.getRevenueResourceEntry();
				List<RevenueResourceEntryVO> revenueResourceEntries = new ArrayList<>();
				for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
					RevenueResourceEntryVO revenueResourceEntryVO = new RevenueResourceEntryVO();
					revenueResourceEntryVO.setRevenueResourceEntryId(revenueResourceEntry.getRevenueResourceEntryId());
					revenueResourceEntryVO.setStrategicBusinessUnit(StrategicBusinessUnitConverter
							.convertSBUToSBUVO(revenueResourceEntry.getStrategicBusinessUnit()));
					revenueResourceEntryVO.setStrategicBusinessUnitHead(StrategicBusinessUnitHeadConverter
							.convertSBUHeadToSBUHeadVO(revenueResourceEntry.getStrategicBusinessUnitHead()));
					revenueResourceEntryVO.setBusinessUnit(BusinessUnitConverter
							.convertBusinessUnitToBusinessUnitVO(revenueResourceEntry.getBusinessUnit()));
					revenueResourceEntryVO.setBusinessType(BusinessTypeConverter
							.convertBusinessTypeToBusinessTypeVO(revenueResourceEntry.getBusinessType()));
					revenueResourceEntryVO.setLocation(
							LocationConverter.convertLocationToLocationVO(revenueResourceEntry.getLocation()));
					revenueResourceEntryVO.setResourceName(revenueResourceEntry.getResourceName());
					revenueResourceEntryVO.setEmployeeId(revenueResourceEntry.getEmployeeId());
					revenueResourceEntryVO.setResourceStartDate(revenueResourceEntry.getResourceStartDate());
					revenueResourceEntryVO.setResourceEndDate(revenueResourceEntry.getResourceEndDate());
					revenueResourceEntryVO.setCocPractice(CocPracticeConverter
							.convertCocPracticeToCocPracticeVO(revenueResourceEntry.getCocPractice()));
					revenueResourceEntryVO.setLeaveLossFactor(revenueResourceEntry.getLeaveLossFactor());
					revenueResourceEntryVO.setBillingRateType(revenueResourceEntry.getBillingRateType());
					revenueResourceEntryVO.setBillingRate(revenueResourceEntry.getBillingRate());
					revenueResourceEntryVO.setAllocation(revenueResourceEntry.getAllocation());
					revenueResourceEntries.add(revenueResourceEntryVO);
				}
				tmRevenueEntryVO.setRevenueResourceEntries(revenueResourceEntries);
				rollingRevenueResponse.setTmRevenueEntryVO(tmRevenueEntryVO);
			}
		}
		return rollingRevenueResponse;
	}
}
