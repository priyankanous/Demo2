package com.nous.rollingrevenue.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.Currency;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.model.Opportunity;
import com.nous.rollingrevenue.model.ResourcesEntry;
import com.nous.rollingrevenue.model.RollingRevenueCommonEntry;
import com.nous.rollingrevenue.model.TandMRevenueEntry;
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.repository.BusinessDevelopmentManagerRepository;
import com.nous.rollingrevenue.repository.BusinessTypeRepository;
import com.nous.rollingrevenue.repository.BusinessUnitRepository;
import com.nous.rollingrevenue.repository.CocPracticeRepository;
import com.nous.rollingrevenue.repository.CurrencyRepository;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.HolidayCalendarRepository;
import com.nous.rollingrevenue.repository.LocationRepository;
import com.nous.rollingrevenue.repository.OpportunityRepository;
import com.nous.rollingrevenue.repository.ProbabilityTypeRepository;
import com.nous.rollingrevenue.repository.RegionRepository;
import com.nous.rollingrevenue.repository.ResourceEntryRepository;
import com.nous.rollingrevenue.repository.RollingRevenueCommonRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitHeadRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitRepository;
import com.nous.rollingrevenue.repository.TandMRevenueRepository;
import com.nous.rollingrevenue.repository.WorkOrderRepository;
import com.nous.rollingrevenue.service.RevenueEntryService;
import com.nous.rollingrevenue.vo.MonthlyFinancialYearVO;
import com.nous.rollingrevenue.vo.ProjectCodesVO;
import com.nous.rollingrevenue.vo.ResourcesDetailsVO;
import com.nous.rollingrevenue.vo.RollingRevenueAccountVO;
import com.nous.rollingrevenue.vo.RollingRevenueVO;

@Service
public class RevenueEntryServiceImpl implements RevenueEntryService {

	@Autowired
	private RollingRevenueCommonRepository rollingRevenueCommonRepository;

	@Autowired
	private ResourceEntryRepository resourceEntryRepository;

	@Autowired
	private TandMRevenueRepository tmRevenueRepository;

	@Autowired
	private BusinessUnitRepository businessUnitRepository;

	@Autowired
	private BusinessDevelopmentManagerRepository bdmRepository;

	@Autowired
	private BusinessTypeRepository businessTypeRepository;

	@Autowired
	private CocPracticeRepository cocPracticeRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private HolidayCalendarRepository holidayCalendarRepository;

	@Autowired
	private OpportunityRepository opportunityRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private ProbabilityTypeRepository probabilityTypeRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private StrategicBusinessUnitRepository sbuRepository;

	@Autowired
	private StrategicBusinessUnitHeadRepository sbuHeadRepository;

	@Autowired
	private WorkOrderRepository workOrderRepository;

	@Override
	public String saveRollingRevenue(RollingRevenueVO rollingRevenueVO) {

		if (rollingRevenueVO.getPricingType() != null
				&& Constants.PRICING_TYPE_TM.equalsIgnoreCase(rollingRevenueVO.getPricingType())) {

			RollingRevenueCommonEntry rollingRevenueCommonEntry = new RollingRevenueCommonEntry();
			rollingRevenueCommonEntry.setPricingType(Constants.PRICING_TYPE_TM);
			rollingRevenueCommonEntry.setBusinessUnit(
					businessUnitRepository.findById(rollingRevenueVO.getBusinessUnit().getBusinessUnitId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BusinessUnit")));
			rollingRevenueCommonEntry.setStrategicBusinessUnit(
					sbuRepository.findById(rollingRevenueVO.getStrategicBusinessUnit().getSbuId())
							.orElseThrow(() -> new RecordNotFoundException(
									ErrorConstants.RECORD_DOES_NOT_EXIST + "StrategicBusinessUnit")));
			rollingRevenueCommonEntry.setStrategicBusinessUnitHead(
					sbuHeadRepository.findById(rollingRevenueVO.getStrategicBusinessUnitHead().getSbuHeadId())
							.orElseThrow(() -> new RecordNotFoundException(
									ErrorConstants.RECORD_DOES_NOT_EXIST + "StrategicBusinessUnitHead")));
			rollingRevenueCommonEntry
					.setAccount(accountRepository.findById(rollingRevenueVO.getAccount().getAccountId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Account")));

			rollingRevenueCommonEntry = conditionTocheckOpportunityName(rollingRevenueVO, rollingRevenueCommonEntry);

			rollingRevenueCommonEntry.setBusinessType(
					businessTypeRepository.findById(rollingRevenueVO.getBusinessType().getBusinessTypeId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BusinessType")));
			rollingRevenueCommonEntry.setProjectCode(rollingRevenueVO.getProjectCode());
			rollingRevenueCommonEntry.setProjectStartDate(rollingRevenueVO.getProjectStartDate());
			rollingRevenueCommonEntry.setProjectEndDate(rollingRevenueVO.getProjectEndDate());
			rollingRevenueCommonEntry.setProbability(
					probabilityTypeRepository.findById(rollingRevenueVO.getProbability().getProbabilityTypeId())
							.orElseThrow(() -> new RecordNotFoundException(
									ErrorConstants.RECORD_DOES_NOT_EXIST + "ProbabilityType")));
			rollingRevenueCommonEntry.setBdm(bdmRepository.findById(rollingRevenueVO.getBdm().getBdmId())
					.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "BDM")));
			rollingRevenueCommonEntry.setRegion(regionRepository.findById(rollingRevenueVO.getRegion().getRegionId())
					.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Region")));
			rollingRevenueCommonEntry.setCocPractice(
					cocPracticeRepository.findById(rollingRevenueVO.getCocPractice().getCocPracticeId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "CocPractice")));
			rollingRevenueCommonEntry.setLocation(
					locationRepository.findById(rollingRevenueVO.getLocation().getLocationId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Location")));
			rollingRevenueCommonEntry.setFinancialYear(financialYearRepository
					.findById(rollingRevenueVO.getFinancialYear().getFinancialYearId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "FinancialYear")));
			rollingRevenueCommonEntry.setCurrency(
					currencyRepository.findById(rollingRevenueVO.getCurrency().getCurrencyId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Currency")));
			rollingRevenueCommonEntry.setWorkOrder(
					workOrderRepository.findById(rollingRevenueVO.getWorkOrder().getWorkOrderId()).orElseThrow(
							() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "WorkOrder")));
			rollingRevenueCommonEntry.setWorkOrderEndDate(rollingRevenueVO.getWorkOrderEndDate());
			rollingRevenueCommonEntry.setWorkOrderStatus(rollingRevenueVO.getWorkOrderStatus());
			rollingRevenueCommonEntry.setNoOfResources(rollingRevenueVO.getNoOfResources());
			rollingRevenueCommonEntry.setRemarks(rollingRevenueVO.getRemarks());

			if (Constants.SAVE_AS_DRAFT.equalsIgnoreCase(rollingRevenueVO.getStatus())) {
				rollingRevenueCommonEntry.setStatus(Constants.SAVE_AS_DRAFT);
			} else {
				rollingRevenueCommonEntry.setStatus(Constants.SUBMITTED);
			}

			TandMRevenueEntry tmMRevenueEntry = new TandMRevenueEntry();
			tmMRevenueEntry.setLeaveLossFactor(rollingRevenueVO.getLeaveLossFactor());

			// TODO: need to check which billing rate type required to save the data.
			tmMRevenueEntry.setBillingRateType(rollingRevenueVO.getBillingRateType());

			tmMRevenueEntry.setBillingRate(calculatingBillingRate(rollingRevenueVO).longValue());

			ResourcesEntry resourcesEntry = new ResourcesEntry();
			Set<ResourcesEntry> set = new HashSet<>();
			if (!rollingRevenueVO.getResourcesList().isEmpty()) {
				for (ResourcesDetailsVO resourcesDetailsVO : rollingRevenueVO.getResourcesList()) {
					resourcesEntry.setEmployeeId(resourcesDetailsVO.getEmployeeId());
					resourcesEntry.setResourceName(resourcesDetailsVO.getResourceName());
					resourcesEntry.setResourceStartDate(resourcesDetailsVO.getResourceStartDate());
					resourcesEntry.setResourceEndDate(resourcesDetailsVO.getResourceEndDate());
					resourcesEntry.setLeaveLossFactor(resourcesDetailsVO.getLeaveLossFactor());
					resourcesEntry.setBillingRate(calculatingBillingRate(rollingRevenueVO).longValue());
					ResourcesEntry entry = resourceEntryRepository.save(resourcesEntry);
					set.add(entry);
				}
			}

			Optional<FinancialYear> findByFinancialYearName = financialYearRepository
					.findByFinancialYearName(rollingRevenueVO.getFinancialYear().getFinancialYearName());
			if (findByFinancialYearName.isPresent()) {
				List<Currency> currencies = findByFinancialYearName.get().getCurrencies();
				Currency currency = currencies.stream().filter(cur -> cur.isBaseCurrency() == true).findFirst().get();
				rollingRevenueCommonEntry.setCurrency(currency);
			}

			RollingRevenueCommonEntry commonEntry = rollingRevenueCommonRepository.save(rollingRevenueCommonEntry);
			tmMRevenueEntry.setCommonEntry(commonEntry);
			tmMRevenueEntry.setResourcesEntries(set);
			tmRevenueRepository.save(tmMRevenueEntry);
			return "Sucessfully record saved";
		}
		return "Please enter valid Pricing Type";
	}

	private RollingRevenueCommonEntry saveOrFetchOpportunities(RollingRevenueVO rollingRevenueVO, boolean flag,
			RollingRevenueCommonEntry rollingRevenueCommonEntry) {
		Opportunity opportunity = new Opportunity();
		if (flag) {
			Optional<Opportunity> oppOptional = opportunityRepository
					.findById(rollingRevenueVO.getOpportunity().getOpportunityId());
			opportunity = oppOptional.get();
			rollingRevenueCommonEntry.setOpportunity(opportunity);
			rollingRevenueCommonEntry.setProjectCode(opportunity.getProjectCode());
			rollingRevenueCommonEntry.setProjectStartDate(opportunity.getProjectStartDate());
			rollingRevenueCommonEntry.setProjectEndDate(opportunity.getProjectEndDate());
		} else {
			opportunity.setAccount(accountRepository.findById(rollingRevenueVO.getAccount().getAccountId())
					.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Account")));
			opportunity.setOpportunityName(rollingRevenueVO.getOpportunity().getOpportunityName());
			opportunity.setProjectCode(rollingRevenueVO.getProjectCode());
			opportunity.setProjectStartDate(rollingRevenueVO.getProjectStartDate());
			opportunity.setProjectEndDate(rollingRevenueVO.getProjectEndDate());
			opportunityRepository.save(opportunity);

			rollingRevenueCommonEntry.setOpportunity(opportunity);
			rollingRevenueCommonEntry.setProjectCode(rollingRevenueVO.getProjectCode());
			rollingRevenueCommonEntry.setProjectStartDate(rollingRevenueVO.getProjectStartDate());
			rollingRevenueCommonEntry.setProjectEndDate(rollingRevenueVO.getProjectEndDate());
		}
		return rollingRevenueCommonEntry;
	}

	private RollingRevenueCommonEntry conditionTocheckOpportunityName(RollingRevenueVO rollingRevenueVO,
			RollingRevenueCommonEntry rollingRevenueCommonEntry) {
		List<Opportunity> listOfOpportunities = null;
		boolean flag = false;
		Optional<Account> account = accountRepository.findByAccountName(rollingRevenueVO.getAccount().getAccountName());
		if (account.isPresent()) {
			listOfOpportunities = account.get().getOpportunities();
			for (Opportunity opportunity : listOfOpportunities) {
				if (rollingRevenueVO.getOpportunity() != null
						&& rollingRevenueVO.getOpportunity().getOpportunityName() != null
						&& opportunity.getOpportunityName()
								.equalsIgnoreCase(rollingRevenueVO.getOpportunity().getOpportunityName())) {
					flag = true;
				}
			}
		}

		return saveOrFetchOpportunities(rollingRevenueVO, flag, rollingRevenueCommonEntry);
	}

	private BigDecimal calculatingBillingRate(RollingRevenueVO rollingRevenueVO) {
		BigDecimal conversionRateValue = getConversionRateValue(rollingRevenueVO);
		return conversionRateValue.multiply(BigDecimal.valueOf(rollingRevenueVO.getBillingRate()));
	}

	private BigDecimal getConversionRateValue(RollingRevenueVO rollingRevenueVO) {
		FinancialYear financialYear = financialYearRepository
				.findById(rollingRevenueVO.getFinancialYear().getFinancialYearId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "FinancialYear"));
		String year = financialYear.getFinancialYearName();
		List<Currency> currencyList = currencyRepository.findByFinancialYear(year);

		Currency currency = currencyRepository.findById(rollingRevenueVO.getCurrency().getCurrencyId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Currency"));

		return currencyList.stream().filter(c -> currency.getCurrency().equalsIgnoreCase(c.getCurrencyName()))
				.map(Currency::getConversionRate).findFirst().orElse(null);

	}

	@Override
	public RollingRevenueAccountVO getRevenueByAccountLevel(Long id) {
		RollingRevenueAccountVO rollingRevenueAccountVO = new RollingRevenueAccountVO();

		List<ProjectCodesVO> projectCodeList = new ArrayList<>();

		Optional<TandMRevenueEntry> optional = tmRevenueRepository.findById(id);
		if (optional.isPresent()) {
			TandMRevenueEntry tmRevenueEntry = optional.get();
			RollingRevenueCommonEntry revenueCommonEntry = tmRevenueEntry.getCommonEntry();

			rollingRevenueAccountVO.setBusinessUnit(revenueCommonEntry.getBusinessUnit().getBusinessUnitDisplayName());
			rollingRevenueAccountVO
					.setStrategicBusinessUnit(revenueCommonEntry.getStrategicBusinessUnit().getSbuDisplayName());
			rollingRevenueAccountVO.setStrategicBusinessUnitHead(
					revenueCommonEntry.getStrategicBusinessUnitHead().getSbuHeadDisplayName());
			rollingRevenueAccountVO.setBdm(revenueCommonEntry.getBdm().getBdmDisplayName());
			rollingRevenueAccountVO.setBusinessType(revenueCommonEntry.getBusinessType().getBusinessTypeDisplayName());
			rollingRevenueAccountVO.setAccount(revenueCommonEntry.getAccount().getAccountName());
			rollingRevenueAccountVO.setRegion(revenueCommonEntry.getRegion().getRegionDisplayName());
			rollingRevenueAccountVO.setLocation(revenueCommonEntry.getLocation().getLocationDisplayName());
			rollingRevenueAccountVO.setProbability(revenueCommonEntry.getProbability().getProbabilityTypeName());
			if (Constants.NON_COC_BASED
					.equalsIgnoreCase(revenueCommonEntry.getCocPractice().getCocPracticeDisplayName())) {
				rollingRevenueAccountVO.setCoc(Constants.NO);
			} else {
				rollingRevenueAccountVO.setCoc(Constants.YES);
			}
			rollingRevenueAccountVO.setStatus(revenueCommonEntry.getStatus());

			List<MonthlyFinancialYearVO> list = new ArrayList<>();
			MonthlyFinancialYearVO monthlyFinancialYearVO = null;

			List<Opportunity> listOfOpportunities = null;
			Optional<Account> account = accountRepository
					.findByAccountName(revenueCommonEntry.getAccount().getAccountName());
			if (account.isPresent()) {
				listOfOpportunities = account.get().getOpportunities();
			}

			for (Opportunity opportunity : listOfOpportunities) {

				ProjectCodesVO projectCodesVO = new ProjectCodesVO();
				projectCodesVO.setProjectCodeId(opportunity.getOpportunityId());
				projectCodesVO.setProjectCode(opportunity.getProjectCode());
				projectCodesVO.setOpportunityName(opportunity.getOpportunityName());
				projectCodesVO.setPricingType(revenueCommonEntry.getPricingType());
				projectCodesVO.setCocPractice(revenueCommonEntry.getCocPractice().getCocPracticeDisplayName());
				projectCodesVO.setNoOfResources(revenueCommonEntry.getNoOfResources());
				projectCodesVO.setLeaveLossFactor(tmRevenueEntry.getLeaveLossFactor());
				projectCodeList.add(projectCodesVO);

				monthlyFinancialYearVO = monthlyBillingSeparation(tmRevenueEntry, revenueCommonEntry, opportunity);
				list.add(monthlyFinancialYearVO);
			}
			rollingRevenueAccountVO.setProjectCodeList(projectCodeList);

			monthlyFinancialYearVO = calculatingMultipleProjectRevenueDetails(list);

			rollingRevenueAccountVO.setMonthlyFinancialYearVO(setQuarterlyDetails(monthlyFinancialYearVO));
		}

		return rollingRevenueAccountVO;
	}

	private MonthlyFinancialYearVO calculatingMultipleProjectRevenueDetails(List<MonthlyFinancialYearVO> list) {
		MonthlyFinancialYearVO financialYearVO = new MonthlyFinancialYearVO();
		double aprilTemp = 0;
		double mayTemp = 0;
		double juneTemp = 0;
		double julyTemp = 0;
		double augustTemp = 0;
		double septemberTemp = 0;
		double octoberTemp = 0;
		double novemberTemp = 0;
		double decemberTemp = 0;
		double januaryTemp = 0;
		double februaryTemp = 0;
		double marchTemp = 0;
		if (!list.isEmpty()) {
			for (MonthlyFinancialYearVO monthlyFinancialYearVO : list) {
				financialYearVO.setApril(aprilTemp + monthlyFinancialYearVO.getApril());
				financialYearVO.setMay(mayTemp + monthlyFinancialYearVO.getMay());
				financialYearVO.setJune(juneTemp + monthlyFinancialYearVO.getJune());
				financialYearVO.setJuly(julyTemp + monthlyFinancialYearVO.getJuly());
				financialYearVO.setAugust(augustTemp + monthlyFinancialYearVO.getAugust());
				financialYearVO.setSeptember(septemberTemp + monthlyFinancialYearVO.getSeptember());
				financialYearVO.setOctober(octoberTemp + monthlyFinancialYearVO.getOctober());
				financialYearVO.setNovember(novemberTemp + monthlyFinancialYearVO.getNovember());
				financialYearVO.setDecember(decemberTemp + monthlyFinancialYearVO.getDecember());
				financialYearVO.setJanuary(januaryTemp + monthlyFinancialYearVO.getJanuary());
				financialYearVO.setFebruary(februaryTemp + monthlyFinancialYearVO.getFebruary());
				financialYearVO.setMarch(marchTemp + monthlyFinancialYearVO.getMarch());
			}
		}
		return financialYearVO;
	}

	private List<String> getListOfMonthsBetweenDates(Opportunity opportunity) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		LocalDate startDate = opportunity.getProjectStartDate();
		LocalDate endDate = opportunity.getProjectEndDate();
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(2))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private static int weekDaysInMonth(YearMonth yearMonth) {
		int len = yearMonth.lengthOfMonth(); // 28-31, supporting leap year
		int dow = yearMonth.atDay(1).getDayOfWeek().getValue(); // 1=Mon, 7=Sun
		return (dow <= 5 ? Math.min(len - 8, 26 - dow) : Math.max(len + dow - 16, 20));
	}

	private Map<Month, Long> getMonthlyWorkingHours(String financialYear) {
		List<HolidayCalendar> holidayCalendarList = holidayCalendarRepository.findByFinancialYear(financialYear);
		return holidayCalendarList.stream().map(date -> date.getHolidayDate())
				.collect(Collectors.groupingBy(Month::from, Collectors.counting()));
	}

	private Long getMonthlyHolidaysCount(String financialYear, String monthName) {
		Map<Month, Long> monthlyCounts = getMonthlyWorkingHours(financialYear);
		return monthlyCounts.entrySet().stream().filter(k -> k.getKey().equals(monthName)).map(Map.Entry::getValue)
				.findFirst().orElse(null);
	}

	private double convertBillingTypeToMonthly(String billingType, String year, int monthNumber, String monthName,
			Long billingRate, Long noOfResources, Long leaveLossFact) {
		double cal = 0;
		int daysInMonth = weekDaysInMonth(YearMonth.of(Integer.parseInt(year), monthNumber));
		Long monthlyHolidaysCount = getMonthlyHolidaysCount(year, monthName);

		long totalHours = (daysInMonth - monthlyHolidaysCount) * 8 * (1 - (leaveLossFact / 100));

		if (Constants.HOURLY.equalsIgnoreCase(billingType)) {
			cal = billingRate * totalHours;
		} else if (Constants.DAILY.equalsIgnoreCase(billingType)) {
			cal = billingRate * (totalHours / 8);
		} else if (Constants.MONTHLY.equalsIgnoreCase(billingType)) {
			cal = billingRate;
		} else if (Constants.QUARTERLY.equalsIgnoreCase(billingType)) {
			cal = (billingRate / 3);
		} else {
			cal = (billingRate / 6);
		}
		return cal;
	}

	private MonthlyFinancialYearVO setQuarterlyDetails(MonthlyFinancialYearVO monthlyFinancialYearVO) {

		monthlyFinancialYearVO.setQ1FYP(
				monthlyFinancialYearVO.getApril() + monthlyFinancialYearVO.getMay() + monthlyFinancialYearVO.getJune());

		monthlyFinancialYearVO.setQ2FYP(monthlyFinancialYearVO.getJuly() + monthlyFinancialYearVO.getAugust()
				+ monthlyFinancialYearVO.getSeptember());

		monthlyFinancialYearVO.setQ3FYP(monthlyFinancialYearVO.getOctober() + monthlyFinancialYearVO.getNovember()
				+ monthlyFinancialYearVO.getDecember());

		monthlyFinancialYearVO.setQ4FYP(monthlyFinancialYearVO.getJanuary() + monthlyFinancialYearVO.getFebruary()
				+ monthlyFinancialYearVO.getMarch());

		return monthlyFinancialYearVO;
	}

	private MonthlyFinancialYearVO monthlyBillingSeparation(TandMRevenueEntry tmRevenueEntry,
			RollingRevenueCommonEntry revenueCommonEntry, Opportunity opportunity) {
		MonthlyFinancialYearVO monthlyFinancialYearVO = new MonthlyFinancialYearVO();
		String financialYear = revenueCommonEntry.getFinancialYear().getFinancialYearCustomName();
		String[] finaYear = financialYear.replace("FY'", "").split(financialYear);
		List<String> projectMonthAndYear = getListOfMonthsBetweenDates(opportunity);

		double monthlyBillRate = 0;

		for (String monthAndYear : projectMonthAndYear) {
			String[] split = monthAndYear.split("-");

			if (Integer.parseInt(split[1]) == Integer.parseInt(finaYear[0])) {

				switch (split[0]) {

				case "April":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 4,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setApril(monthlyBillRate);
					break;

				case "May":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 5,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setMay(monthlyBillRate);
					break;

				case "June":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 6,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setJune(monthlyBillRate);
					break;

				case "July":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 7,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setJuly(monthlyBillRate);
					break;

				case "August":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 8,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setAugust(monthlyBillRate);
					break;

				case "September":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 9,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setSeptember(monthlyBillRate);
					break;

				case "October":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 10,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setOctober(monthlyBillRate);
					break;

				case "November":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 11,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setNovember(monthlyBillRate);
					break;

				case "December":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 12,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setDecember(monthlyBillRate);
					break;

				}

			} else if (Integer.parseInt(split[1]) == Integer.parseInt(finaYear[1])) {

				switch (split[0]) {

				case "January":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 4,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setJanuary(monthlyBillRate);
					break;

				case "February":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 5,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setFebruary(monthlyBillRate);
					break;

				case "March":
					monthlyBillRate = convertBillingTypeToMonthly(tmRevenueEntry.getBillingRateType(), finaYear[0], 6,
							split[0], tmRevenueEntry.getBillingRate(), revenueCommonEntry.getNoOfResources(),
							tmRevenueEntry.getLeaveLossFactor());
					monthlyFinancialYearVO.setMarch(monthlyBillRate);
					break;

				}

			}
		}
		return monthlyFinancialYearVO;
	}

}
