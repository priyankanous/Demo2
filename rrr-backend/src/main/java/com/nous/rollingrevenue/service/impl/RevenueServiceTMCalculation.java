package com.nous.rollingrevenue.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.CurrencyEntity;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.repository.CurrencyRepository;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;

@Component
public class RevenueServiceTMCalculation {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	public FinancialYearTMRevenue calculateTMRevenue(List<RevenueResourceEntry> revenueEntryList,
			FinancialYear financialYear, boolean isDisplayAdditionalQuarter) {
		FinancialYearTMRevenue financialYearTMRevenue = new FinancialYearTMRevenue();
		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		String financialYearName = financialYear.getFinancialYearName();

		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		if (isDisplayAdditionalQuarter) {
			fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 6, 30);
		}

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		listOfMonthsBetweenFinancialYear = this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyStartDate,
				isDisplayAdditionalQuarter);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));

		for (RevenueResourceEntry revenueTMResourceEntry : revenueEntryList) {
			if (revenueTMResourceEntry.getLeaveLossFactor() != null) {
				long leaveLossFactor = 0;
				if ("Offshore".equalsIgnoreCase(revenueTMResourceEntry.getLocation().getLocationName())
						|| "Onsite".equalsIgnoreCase(revenueTMResourceEntry.getLocation().getLocationName())) {
					leaveLossFactor = revenueTMResourceEntry.getLeaveLossFactor();
				}

				if (revenueTMResourceEntry.getBillingRate() != null
						|| revenueTMResourceEntry.getResourceStartDate() != null
						|| revenueTMResourceEntry.getResourceEndDate() != null
						|| revenueTMResourceEntry.getBillingRateType() != null) {
					BigInteger billingRate = calculatingBillingRate(financialYear,
							revenueTMResourceEntry.getRevenueEntry().getCurrency().getCurrencyId(),
							revenueTMResourceEntry.getBillingRate());
					monthlyBillingSeparation(financialYearName, revenueTMResourceEntry.getResourceStartDate(),
							revenueTMResourceEntry.getResourceEndDate(), revenueTMResourceEntry.getBillingRateType(),
							billingRate, leaveLossFactor, fyRevenue);
				}
			}
		}
		setQuarterlyDetails(fyRevenue, isDisplayAdditionalQuarter);
		financialYearTMRevenue.setDataMap(fyRevenue);
		return financialYearTMRevenue;
	}

	public BigInteger calculatingBillingRate(FinancialYear financialYear, long currencyId, BigInteger billingRate) {
		BigDecimal conversionRateValue = getConversionRateValue(financialYear, currencyId);
		return billingRate.divide(conversionRateValue.toBigInteger());
	}

	private BigDecimal getConversionRateValue(FinancialYear financialYear, long currencyId) {
		List<CurrencyEntity> currencyList = financialYear.getCurrencies();
		CurrencyEntity currency = currencyRepository.findById(currencyId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "Currency"));

		return currencyList.stream().filter(c -> currency.getCurrencyName().equalsIgnoreCase(c.getCurrencyName()))
				.map(CurrencyEntity::getConversionRate).findFirst().orElse(null);
	}

	public Map<String, BigInteger> setQuarterlyDetails(Map<String, BigInteger> fyRevenue,
			boolean isDisplayAdditionalQuarter) {
		int bigInteger = 0;
		int quarterOne = 0;
		int quarterTwo = 0;
		int quarterThree = 0;
		int quarterFour = 0;
		int quarterFive = 0;
		for (Map.Entry<String, BigInteger> entry : fyRevenue.entrySet()) {
			String key = entry.getKey();
			if (key.contains("April") || key.contains("May") || key.contains("June") || key.contains("q1FYP")) {
				bigInteger = bigInteger + fyRevenue.get(key).intValue();
				quarterOne = bigInteger;
				if (key.contains("q1FYP")) {
					fyRevenue.put(key, fyRevenue.get(key).add(BigInteger.valueOf(bigInteger)));
					bigInteger = 0;
				}
			} else if (key.contains("July") || key.contains("August") || key.contains("September")
					|| key.contains("q2FYP")) {
				bigInteger = bigInteger + fyRevenue.get(key).intValue();
				quarterTwo = bigInteger;
				if (key.contains("q2FYP")) {
					fyRevenue.put(key, fyRevenue.get(key).add(BigInteger.valueOf(bigInteger)));
					bigInteger = 0;
				}
			} else if (key.contains("October") || key.contains("November") || key.contains("December")
					|| key.contains("q3FYP")) {
				bigInteger = bigInteger + fyRevenue.get(key).intValue();
				quarterThree = bigInteger;
				if (key.contains("q3FYP")) {
					fyRevenue.put(key, fyRevenue.get(key).add(BigInteger.valueOf(bigInteger)));
					bigInteger = 0;
				}
			} else if (key.contains("January") || key.contains("February") || key.contains("March")
					|| key.contains("q4FYP")) {
				bigInteger = bigInteger + fyRevenue.get(key).intValue();
				quarterFour = bigInteger;
				if (key.contains("q4FYP")) {
					fyRevenue.put(key, fyRevenue.get(key).add(BigInteger.valueOf(bigInteger)));
					bigInteger = 0;
				}
			} else if (isDisplayAdditionalQuarter) {
				if (key.contains("April") || key.contains("May") || key.contains("June") || key.contains("q5FYP")) {
					bigInteger = bigInteger + fyRevenue.get(key).intValue();
					quarterFive = bigInteger;
					if (key.contains("q5FYP")) {
						fyRevenue.put(key, fyRevenue.get(key).add(BigInteger.valueOf(bigInteger)));
						bigInteger = 0;
					}
				}
			} else if (key.contains("FYP")) {
				int allQuartersSum = quarterOne + quarterTwo + quarterThree + quarterFour + quarterFive;
				fyRevenue.put(key, fyRevenue.get(key).add(BigInteger.valueOf(allQuartersSum)));
			}
		}
		return fyRevenue;
	}

	private List<String> getListOfMonthsBetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1))).map(date -> date.format(formatter))
				.toList();
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

	public Map<String, BigInteger> monthlyBillingSeparation(String financialYear, LocalDate startDate,
			LocalDate endDate, String billingRateType, BigInteger billingRate, Long leaveLossFactor,
			Map<String, BigInteger> fyRevenue) {
		List<String> projectMonthAndYear = getListOfMonthsBetweenDates(startDate, endDate);
		double monthlyBillRate = 0;
		for (String monthAndYear : projectMonthAndYear) {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
					.append(DateTimeFormatter.ofPattern("MMMM-yyyy")).toFormatter(Locale.ENGLISH);
			LocalDate localDate = YearMonth.parse(monthAndYear, formatter).atDay(1);
			Month month = localDate.getMonth();
			Integer year = localDate.getYear();
			monthlyBillRate = convertBillingTypeToMonthly(billingRateType, financialYear, month.getValue(),
					month.name(), billingRate, leaveLossFactor, year.toString());
			if (fyRevenue.containsKey(monthAndYear)) {
				fyRevenue.put(monthAndYear,
						fyRevenue.get(monthAndYear).add(BigDecimal.valueOf(monthlyBillRate).toBigInteger()));
			}
		}
		return fyRevenue;
	}

	private double convertBillingTypeToMonthly(String billingType, String financialYearName, int monthNumber,
			String monthName, BigInteger billingRate, Long leaveLossFact, String year) {
		double cal = 0;
		int daysInMonth = weekDaysInMonth(YearMonth.of(Integer.parseInt(year), monthNumber));
		Long monthlyHolidaysCount = getMonthlyHolidaysCount(financialYearName, monthName);

		long totalHours = (daysInMonth - monthlyHolidaysCount) * 8 * (1 - (leaveLossFact / 100));
		long billRate = billingRate.longValue();

		if (Constants.HOURLY.equalsIgnoreCase(billingType)) {
			cal = (double) billRate * totalHours;
		} else if (Constants.DAILY.equalsIgnoreCase(billingType)) {
			cal = billRate * (totalHours / 8);
		} else if (Constants.WEEKLY.equalsIgnoreCase(billingType)) {
			cal = billRate * (totalHours / 40);
		} else if (Constants.TWOWEEKS.equalsIgnoreCase(billingType)) {
			cal = billRate * (totalHours / 80);
		} else if (Constants.MONTHLY.equalsIgnoreCase(billingType)) {
			cal = billRate;
		} else if (Constants.TWO_MONTHS.equalsIgnoreCase(billingType)) {
			cal = (billRate / 2);
		} else if (Constants.QUARTERLY.equalsIgnoreCase(billingType)) {
			cal = (billRate / 3);
		} else {
			cal = (billRate / 6);
		}
		return cal;
	}

	private static int weekDaysInMonth(YearMonth yearMonth) {
		int len = yearMonth.lengthOfMonth(); // 28-31, supporting leap year
		int dow = yearMonth.atDay(1).getDayOfWeek().getValue(); // 1=Mon, 7=Sun
		return (dow <= 5 ? Math.min(len - 8, 26 - dow) : Math.max(len + dow - 16, 20));
	}

	private Long getMonthlyHolidaysCount(String financialYearName, String monthName) {
		Map<Month, Long> monthlyCounts = getMonthlyWorkingHours(financialYearName);
		for (Map.Entry<Month, Long> entry : monthlyCounts.entrySet()) {
			if (entry.getKey().toString().equals(monthName.toUpperCase())) {
				return entry.getValue();
			}
		}
		return 0l;
	}

	private Map<Month, Long> getMonthlyWorkingHours(String financialYearName) {
		List<HolidayCalendar> holidayCalendarList = null;
		Optional<FinancialYear> byFinancialYear = financialYearRepository.findByFinancialYearName(financialYearName);
		if (byFinancialYear.isPresent()) {
			holidayCalendarList = byFinancialYear.get().getHolidayCalendar();
		}
		return holidayCalendarList.stream().map(date -> date.getHolidayDate())
				.collect(Collectors.groupingBy(Month::from, Collectors.counting()));
	}

}
