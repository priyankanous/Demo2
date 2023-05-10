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
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;

@Component
public class RevenueServiceTMCalculation {

	@Autowired
	private FinancialYearRepository financialYearRepository;

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
			financialYearEndingOn = fyEndDate;
		}

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		listOfMonthsBetweenFinancialYear = this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyEndDate,
				isDisplayAdditionalQuarter);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));

		for (RevenueResourceEntry revenueTMResourceEntry : revenueEntryList) {
			long leaveLossFactor = 0;
			if ("Offshore".equalsIgnoreCase(revenueTMResourceEntry.getLocation().getLocationName())) {
				leaveLossFactor = revenueTMResourceEntry.getLeaveLossFactor().getOffShore();
			} else {
				leaveLossFactor = revenueTMResourceEntry.getLeaveLossFactor().getOnSite();
			}
			monthlyBillingSeparation(financialYearName, revenueTMResourceEntry.getResourceStartDate(),
					revenueTMResourceEntry.getResourceEndDate(), revenueTMResourceEntry.getBillingRateType(),
					revenueTMResourceEntry.getBillingRate(), leaveLossFactor, isDisplayAdditionalQuarter, fyRevenue);
		}
		financialYearTMRevenue.setDataMap(fyRevenue);
		return financialYearTMRevenue;
	}

	private List<String> getListOfMonthsBetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private List<String> addQuarterFields(List<String> listOfMonthsBetweenFinancialYear, LocalDate fyEndDate,
			boolean isDisplayAdditionalQuarter) {
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyEndDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;

		listOfMonthsBetweenFinancialYear.add(3, "q1FYP " + year);
		listOfMonthsBetweenFinancialYear.add(4, "q1FYB " + year);
		listOfMonthsBetweenFinancialYear.add(5, "q1FYS " + year);
		listOfMonthsBetweenFinancialYear.add(6, "q1FYT " + year);

		listOfMonthsBetweenFinancialYear.add(10, "q2FYP " + year);
		listOfMonthsBetweenFinancialYear.add(11, "q2FYB " + year);
		listOfMonthsBetweenFinancialYear.add(12, "q2FYS " + year);
		listOfMonthsBetweenFinancialYear.add(13, "q2FYT " + year);

		listOfMonthsBetweenFinancialYear.add(17, "q3FYP " + year);
		listOfMonthsBetweenFinancialYear.add(18, "q3FYB " + year);
		listOfMonthsBetweenFinancialYear.add(19, "q3FYS " + year);
		listOfMonthsBetweenFinancialYear.add(20, "q3FYT " + year);

		listOfMonthsBetweenFinancialYear.add(24, "q4FYP " + year);
		listOfMonthsBetweenFinancialYear.add(25, "q4FYB " + year);
		listOfMonthsBetweenFinancialYear.add(26, "q4FYS " + year);
		listOfMonthsBetweenFinancialYear.add(27, "q4FYT " + year);

		if (isDisplayAdditionalQuarter) {
			listOfMonthsBetweenFinancialYear.add(31, "q1FYP " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add(32, "q1FYB " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add(33, "q1FYS " + additionalQuarterYear);
			listOfMonthsBetweenFinancialYear.add(34, "q1FYT " + additionalQuarterYear);

			listOfMonthsBetweenFinancialYear.add("FYB " + year);
			listOfMonthsBetweenFinancialYear.add("FYS " + year);
			listOfMonthsBetweenFinancialYear.add("FYT " + year);
			listOfMonthsBetweenFinancialYear.add("DiFF-FY " + year);
		} else {
			listOfMonthsBetweenFinancialYear.add("FYB " + year);
			listOfMonthsBetweenFinancialYear.add("FYS " + year);
			listOfMonthsBetweenFinancialYear.add("FYT " + year);
			listOfMonthsBetweenFinancialYear.add("DiFF-FY " + year);
		}
		return listOfMonthsBetweenFinancialYear;

	}

	private Map<String, BigInteger> monthlyBillingSeparation(String financialYear, LocalDate startDate,
			LocalDate endDate, String billingRateType, BigInteger billingRate, Long leaveLossFactor,
			boolean isDisplayAdditionalQuarter, Map<String, BigInteger> fyRevenue) {
		List<String> projectMonthAndYear = getListOfMonthsBetweenDates(startDate, endDate);

		double monthlyBillRate = 0;

		for (String monthAndYear : projectMonthAndYear) {
			DateTimeFormatter formatter = new DateTimeFormatterBuilder().parseCaseInsensitive()
					.append(DateTimeFormatter.ofPattern("MMMM-yyyy")).toFormatter(Locale.ENGLISH);
			LocalDate localDate = YearMonth.parse(monthAndYear, formatter).atDay(1);
			Month month = localDate.getMonth();
			Integer year = localDate.getYear();
			monthlyBillRate = convertBillingTypeToMonthly(billingRateType, financialYear, month.getValue(),
					month.name().toString(), billingRate, leaveLossFactor, year.toString());
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
			cal = billRate * totalHours;
		} else if (Constants.DAILY.equalsIgnoreCase(billingType)) {
			cal = billRate * (totalHours / 8);
		} else if (Constants.MONTHLY.equalsIgnoreCase(billingType)) {
			cal = billRate;
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
