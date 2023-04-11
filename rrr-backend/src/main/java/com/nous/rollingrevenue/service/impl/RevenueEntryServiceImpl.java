package com.nous.rollingrevenue.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.model.Currency;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.model.Opportunity;
import com.nous.rollingrevenue.model.ResourcesEntry;
import com.nous.rollingrevenue.model.RollingRevenueCommonEntry;
import com.nous.rollingrevenue.model.TandMRevenueEntry;
import com.nous.rollingrevenue.repository.CurrencyRepository;
import com.nous.rollingrevenue.repository.HolidayCalendarRepository;
import com.nous.rollingrevenue.repository.OpportunityRepository;
import com.nous.rollingrevenue.repository.ResourceEntryRepository;
import com.nous.rollingrevenue.repository.RollingRevenueCommonRepository;
import com.nous.rollingrevenue.repository.TandMRevenueRepository;
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
	private CurrencyRepository currencyRepository;

	@Autowired
	private HolidayCalendarRepository holidayCalendarRepository;

	@Autowired
	private OpportunityRepository opportunityRepository;

	@Override
	public String saveRollingRevenue(RollingRevenueVO rollingRevenueVO) {

		if (rollingRevenueVO.getPricingType() != null
				&& Constants.PRICING_TYPE_TM.equalsIgnoreCase(rollingRevenueVO.getPricingType())) {

			RollingRevenueCommonEntry rollingRevenueCommonEntry = new RollingRevenueCommonEntry();
			rollingRevenueCommonEntry.setBusinessUnit(rollingRevenueVO.getBusinessUnit());
			rollingRevenueCommonEntry.setStrategicBusinessUnit(rollingRevenueVO.getStrategicBusinessUnit());
			rollingRevenueCommonEntry.setStrategicBusinessUnitHead(rollingRevenueVO.getStrategicBusinessUnitHead());
			rollingRevenueCommonEntry.setAccount(rollingRevenueVO.getAccount());
			rollingRevenueCommonEntry.setOpportunityName(rollingRevenueVO.getOpportunityName());
			rollingRevenueCommonEntry.setBusinessType(rollingRevenueVO.getBusinessType());
			rollingRevenueCommonEntry.setProjectCode(rollingRevenueVO.getProjectCode());
			rollingRevenueCommonEntry.setProjectStartDate(rollingRevenueVO.getProjectStartDate());
			rollingRevenueCommonEntry.setProjectEndDate(rollingRevenueVO.getProjectEndDate());
			rollingRevenueCommonEntry.setProbability(rollingRevenueVO.getProbability());
			rollingRevenueCommonEntry.setBdm(rollingRevenueVO.getBdm());
			rollingRevenueCommonEntry.setRegion(rollingRevenueVO.getRegion());
			rollingRevenueCommonEntry.setCocPractice(rollingRevenueVO.getCocPractice());
			rollingRevenueCommonEntry.setLocation(rollingRevenueVO.getLocation());

			rollingRevenueCommonEntry.setCurrency(rollingRevenueVO.getCurrency());

			rollingRevenueCommonEntry.setWorkOrder(rollingRevenueVO.getWorkOrder());
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
			RollingRevenueCommonEntry commonEntry = rollingRevenueCommonRepository.save(rollingRevenueCommonEntry);
			tmMRevenueEntry.setCommonEntry(commonEntry);
			tmMRevenueEntry.setResourcesEntries(set);
			tmRevenueRepository.save(tmMRevenueEntry);
			return "Sucessfully record saved";
		}
		return "Please enter valid Pricing Type";
	}

	private BigDecimal calculatingBillingRate(RollingRevenueVO rollingRevenueVO) {
		BigDecimal conversionRateValue = getConversionRateValue(rollingRevenueVO);
		return conversionRateValue.multiply(BigDecimal.valueOf(rollingRevenueVO.getBillingRate()));
	}

	private BigDecimal getConversionRateValue(RollingRevenueVO rollingRevenueVO) {
		String financialYear = rollingRevenueVO.getFinancialYear();
		List<Currency> currencyList = currencyRepository.findByFinancialYear(financialYear);

		return currencyList.stream().filter(c -> rollingRevenueVO.getCurrency().equalsIgnoreCase(c.getCurrencyName()))
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

			rollingRevenueAccountVO.setBusinessUnit(revenueCommonEntry.getBusinessUnit());
			rollingRevenueAccountVO.setStrategicBusinessUnit(revenueCommonEntry.getStrategicBusinessUnit());
			rollingRevenueAccountVO.setStrategicBusinessUnitHead(revenueCommonEntry.getStrategicBusinessUnitHead());
			rollingRevenueAccountVO.setBdm(revenueCommonEntry.getBdm());
			rollingRevenueAccountVO.setBusinessType(revenueCommonEntry.getBusinessType());
			rollingRevenueAccountVO.setAccount(revenueCommonEntry.getAccount());
			rollingRevenueAccountVO.setRegion(revenueCommonEntry.getRegion());
			rollingRevenueAccountVO.setLocation(revenueCommonEntry.getLocation());
			rollingRevenueAccountVO.setProbability(revenueCommonEntry.getProbability());

			if (Constants.NON_COC_BASED.equalsIgnoreCase(revenueCommonEntry.getCocPractice())) {
				rollingRevenueAccountVO.setCoc(Constants.NO);
			} else {
				rollingRevenueAccountVO.setCoc(Constants.YES);
			}
			rollingRevenueAccountVO.setStatus(revenueCommonEntry.getStatus());

			List<Opportunity> listOfOpportunities = opportunityRepository
					.findByChildOfAccount(revenueCommonEntry.getAccount());
			for (Opportunity opportunity : listOfOpportunities) {
				ProjectCodesVO projectCodesVO = new ProjectCodesVO();

				projectCodesVO.setProjectCodeId(opportunity.getOpportunityId());
				projectCodesVO.setProjectCode(opportunity.getProjectCode());
				projectCodesVO.setOpportunityName(opportunity.getOpportunityName());
				projectCodesVO.setPricingType(revenueCommonEntry.getPricingType());
				projectCodesVO.setCocPractice(revenueCommonEntry.getCocPractice());
				projectCodesVO.setNoOfResources(revenueCommonEntry.getNoOfResources());
				projectCodesVO.setLeaveLossFactor(tmRevenueEntry.getLeaveLossFactor());

				projectCodeList.add(projectCodesVO);
			}
			rollingRevenueAccountVO.setProjectCodeList(projectCodeList);

		}

		return rollingRevenueAccountVO;
	}

	private List<String> getListOfMonthsBetweenDates(RollingRevenueCommonEntry revenueCommonEntry) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		LocalDate startDate = revenueCommonEntry.getProjectStartDate();
		LocalDate endDate = revenueCommonEntry.getProjectEndDate();
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(2))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private static int weekDaysInMonth(YearMonth yearMonth) {
		int len = yearMonth.lengthOfMonth(); // 28-31, supporting leap year
		int dow = yearMonth.atDay(1).getDayOfWeek().getValue(); // 1=Mon, 7=Sun
		return (dow <= 5 ? Math.min(len - 8, 26 - dow) : Math.max(len + dow - 16, 20));
	}

	private void getMonthlyWorkingHours(RollingRevenueVO rollingRevenueVO) {
		String financialYear = rollingRevenueVO.getFinancialYear();
		List<HolidayCalendar> holidayCalendarList = holidayCalendarRepository.findByFinancialYear(financialYear);

	}

	private void monthlyBillingSeparation(RollingRevenueCommonEntry revenueCommonEntry) {
		List<String> list = getListOfMonthsBetweenDates(revenueCommonEntry);

		int currentYear = LocalDate.now().getYear();
		int nextYear = LocalDate.now().plusYears(1).getYear();

		MonthlyFinancialYearVO monthlyFinancialYearVO = new MonthlyFinancialYearVO();

		for (String monthAndYear : list) {
			String[] split = monthAndYear.split("-");

			if (Integer.parseInt(split[1]) == currentYear) {

				switch (split[0]) {

				case "April":

					monthlyFinancialYearVO.setApril(monthAndYear);
					break;

				case "May":
					System.out.println("10");
					break;

				case "June":
					System.out.println("10");
					break;

				case "July":
					System.out.println("10");
					break;

				case "August":
					System.out.println("10");
					break;

				case "September":
					System.out.println("10");
					break;

				case "October":
					System.out.println("10");
					break;

				case "November":
					System.out.println("10");
					break;

				case "December":
					System.out.println("10");
					break;

				}

			} else {

			}

		}
	}

}
