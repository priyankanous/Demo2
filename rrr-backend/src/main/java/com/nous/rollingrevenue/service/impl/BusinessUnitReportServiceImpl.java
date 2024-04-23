
package com.nous.rollingrevenue.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.RevenueResourceEntryCustomRepository;
import com.nous.rollingrevenue.service.BusinessUnitReportService;
import com.nous.rollingrevenue.vo.BusinessUnitOutDTO;
import com.nous.rollingrevenue.vo.BusinessUnitReportInDTO;
import com.nous.rollingrevenue.vo.BusinessUnitReportRequest;
import com.nous.rollingrevenue.vo.BusinessUnitResponse;
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;

@Service
public class BusinessUnitReportServiceImpl implements BusinessUnitReportService {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private RevenueServiceImpl revenueServiceImpl;

	@Autowired
	private RevenueServiceTMCalculation tmCalculation;

	@Autowired
	private RevenueResourceEntryCustomRepository revenueResourceEntryCustomRepository;

	@Override
	public BusinessUnitResponse getBusinessUnitReportDetails(BusinessUnitReportRequest businessUnitReportRequest,
			boolean isDisplayAdditionalQuarter) {
		BusinessUnitReportInDTO inDTO = businessUnitReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		BusinessUnitResponse businessUnitResponse = new BusinessUnitResponse();
		List<BusinessUnitOutDTO> outDTOList = null;
		List<RevenueResourceEntry> revenueResourceEntryList = revenueResourceEntryCustomRepository
				.findRevenueResourceDetailsForBUOrSBU(businessUnitReportRequest);

		List<RevenueResourceEntry> gssrevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> testreerevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> vserverevenueResourceEntryList = new ArrayList<>();

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			if (Constants.BUSINESS_UNIT_GSS
					.equalsIgnoreCase(revenueResourceEntry.getBusinessUnit().getBusinessUnitDisplayName())) {
				gssrevenueResourceEntryList.add(revenueResourceEntry);
			} else if (Constants.BUSINESS_UNIT_TESTREE
					.equalsIgnoreCase(revenueResourceEntry.getBusinessUnit().getBusinessUnitDisplayName())) {
				testreerevenueResourceEntryList.add(revenueResourceEntry);
			} else if (Constants.BUSINESS_UNIT_VSERVE
					.equalsIgnoreCase(revenueResourceEntry.getBusinessUnit().getBusinessUnitDisplayName())) {
				vserverevenueResourceEntryList.add(revenueResourceEntry);
			}
		}

		FinancialYearRevenue financialYearRevenueGSS = calculatingBasedOnBusinessUnit(gssrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueTESTREE = calculatingBasedOnBusinessUnit(
				testreerevenueResourceEntryList, financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueVSERVE = calculatingBasedOnBusinessUnit(vserverevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueTotal = calculatingBasedOnBusinessUnitForGrandTotal(
				financialYearRevenueGSS, financialYearRevenueTESTREE, financialYearRevenueVSERVE, fyStartDate,
				fyEndDate);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetailsForChart = setQuarterlyDetailsForBusinessUnitForChart(fyStartDate);
		List<String> quarterlyDetailsForTabular = setQuarterlyDetailsForBusinessUnitForTabular(fyStartDate);

		if ("Chart".equalsIgnoreCase(businessUnitReportRequest.getOutPutType())) {
			if ("Monthly".equalsIgnoreCase(businessUnitReportRequest.getViewType())) {
				businessUnitResponse.setLabels(listOfMonthsBetweenFinancialYear);
				outDTOList = setBusinessUnitDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueGSS,
						financialYearRevenueTESTREE, financialYearRevenueVSERVE, null, false);
				businessUnitResponse.setOutDTOList(outDTOList);
			} else {
				businessUnitResponse.setLabels(quarterlyDetailsForChart);
				outDTOList = setBusinessUnitDetails(quarterlyDetailsForChart, financialYearRevenueGSS,
						financialYearRevenueTESTREE, financialYearRevenueVSERVE, null, false);
				businessUnitResponse.setOutDTOList(outDTOList);
			}
		} else {
			if ("Monthly".equalsIgnoreCase(businessUnitReportRequest.getViewType())) {
				businessUnitResponse.setLabels(this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyEndDate));
				outDTOList = setBusinessUnitDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueGSS,
						financialYearRevenueTESTREE, financialYearRevenueVSERVE, financialYearRevenueTotal, true);
				businessUnitResponse.setOutDTOList(outDTOList);
			} else {
				businessUnitResponse.setLabels(quarterlyDetailsForTabular);
				outDTOList = setBusinessUnitDetails(quarterlyDetailsForTabular, financialYearRevenueGSS,
						financialYearRevenueTESTREE, financialYearRevenueVSERVE, financialYearRevenueTotal, true);
				businessUnitResponse.setOutDTOList(outDTOList);
			}
		}
		businessUnitResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return businessUnitResponse;
	}

	private FinancialYearRevenue calculatingBasedOnBusinessUnitForGrandTotal(
			FinancialYearRevenue financialYearRevenueGSS, FinancialYearRevenue financialYearRevenueTESTREE,
			FinancialYearRevenue financialYearRevenueVSERVE, LocalDate fyStartDate, LocalDate fyEndDate) {
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();

		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();
		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		this.addQuarterFieldsForGrandTotal(listOfMonthsBetweenFinancialYear, fyStartDate, false);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));
		financialYearRevenue.setDataMap(fyRevenue);

		Map<String, BigInteger> mapGSS = financialYearRevenueGSS.getDataMap();
		Map<String, BigInteger> grandTotal = financialYearRevenue.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapGSS.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapGSS.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapTESTREE = financialYearRevenueTESTREE.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapTESTREE.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapTESTREE.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapVSERVE = financialYearRevenueVSERVE.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapVSERVE.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapVSERVE.get(entry.getKey())));
			}
		}
		financialYearRevenue.setDataMap(grandTotal);
		return financialYearRevenue;
	}

	private List<String> addQuarterFields(List<String> listOfMonthsBetweenFinancialYear, LocalDate fyStartDate) {
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyStartDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;

		listOfMonthsBetweenFinancialYear.add(3, "q1FYP " + year);
		listOfMonthsBetweenFinancialYear.add(4, "q1FYA " + year);

		listOfMonthsBetweenFinancialYear.add(8, "q2FYP " + year);
		listOfMonthsBetweenFinancialYear.add(9, "q2FYA " + year);

		listOfMonthsBetweenFinancialYear.add(13, "q3FYP " + year);
		listOfMonthsBetweenFinancialYear.add(14, "q3FYA " + year);

		listOfMonthsBetweenFinancialYear.add(18, "q4FYP " + additionalQuarterYear);
		listOfMonthsBetweenFinancialYear.add(19, "q4FYA " + additionalQuarterYear);

		listOfMonthsBetweenFinancialYear.add("FYP " + additionalQuarterYear);
		listOfMonthsBetweenFinancialYear.add("FYB " + additionalQuarterYear);
		return listOfMonthsBetweenFinancialYear;
	}

	private List<BusinessUnitOutDTO> setBusinessUnitDetails(List<String> list,
			FinancialYearRevenue financialYearRevenueGSS, FinancialYearRevenue financialYearRevenueTESTREE,
			FinancialYearRevenue financialYearRevenueVSERVE, FinancialYearRevenue financialYearRevenueTotal,
			boolean isTabular) {
		List<BusinessUnitOutDTO> outDTOList = new ArrayList<>();
		List<BigInteger> gss = getRevenueDetails(list, financialYearRevenueGSS.getDataMap());
		BusinessUnitOutDTO outDTOGSS = new BusinessUnitOutDTO();
		outDTOGSS.setLabel("GSS");
		outDTOGSS.setStack("bar1");
		outDTOGSS.setData(gss);
		outDTOList.add(outDTOGSS);

		List<BigInteger> testree = getRevenueDetails(list, financialYearRevenueTESTREE.getDataMap());
		BusinessUnitOutDTO outDTOTESTREE = new BusinessUnitOutDTO();
		outDTOTESTREE.setLabel("Testree");
		outDTOTESTREE.setStack("bar1");
		outDTOTESTREE.setData(testree);
		outDTOList.add(outDTOTESTREE);

		List<BigInteger> vserve = getRevenueDetails(list, financialYearRevenueVSERVE.getDataMap());
		BusinessUnitOutDTO outDTOVSERVE = new BusinessUnitOutDTO();
		outDTOVSERVE.setLabel("VServe");
		outDTOVSERVE.setStack("bar1");
		outDTOVSERVE.setData(vserve);
		outDTOList.add(outDTOVSERVE);

		if (isTabular) {
			List<BigInteger> grandTotal = getRevenueDetails(list, financialYearRevenueTotal.getDataMap());
			BusinessUnitOutDTO outDTOTotal = new BusinessUnitOutDTO();
			outDTOTotal.setLabel("Grand Total");
			outDTOTotal.setStack("bar1");
			outDTOTotal.setData(grandTotal);
			outDTOList.add(outDTOTotal);
		}

		return outDTOList;
	}

	private List<String> setQuarterlyDetails(LocalDate fyEndDate) {
		List<String> string = new ArrayList<>();
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyEndDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;
		string.add("q1FYP " + year);
		string.add("q1FYA " + year);
		string.add("q1FYB " + year);
		string.add("q1FYS " + year);
		string.add("q1FYT " + year);

		string.add("q2FYP " + year);
		string.add("q2FYA " + year);
		string.add("q2FYB " + year);
		string.add("q2FYS " + year);
		string.add("q2FYT " + year);

		string.add("q3FYP " + year);
		string.add("q3FYA " + year);
		string.add("q3FYB " + year);
		string.add("q3FYS " + year);
		string.add("q3FYT " + year);

		string.add("q4FYP " + additionalQuarterYear);
		string.add("q4FYA " + additionalQuarterYear);
		string.add("q4FYB " + additionalQuarterYear);
		string.add("q4FYS " + additionalQuarterYear);
		string.add("q4FYT " + additionalQuarterYear);

		string.add("FYP " + additionalQuarterYear);
		string.add("FYB " + additionalQuarterYear);
		string.add("FYS " + additionalQuarterYear);
		string.add("FYT " + additionalQuarterYear);
		string.add("DiFF-FY " + additionalQuarterYear);

		return string;
	}

	private List<String> setQuarterlyDetailsForBusinessUnitForChart(LocalDate fyEndDate) {
		List<String> string = new ArrayList<>();
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyEndDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;
		string.add("q1FYP " + year);
		string.add("q2FYP " + year);
		string.add("q3FYP " + year);
		string.add("q4FYP " + additionalQuarterYear);
		return string;
	}

	private List<String> setQuarterlyDetailsForBusinessUnitForTabular(LocalDate fyEndDate) {
		List<String> string = new ArrayList<>();
		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyEndDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;
		string.add("q1FYP " + year);
		string.add("q1FYA " + year);
		string.add("q2FYP " + year);
		string.add("q2FYA " + year);
		string.add("q3FYP " + year);
		string.add("q3FYA " + year);
		string.add("q4FYP " + additionalQuarterYear);
		string.add("q4FYA " + additionalQuarterYear);
		return string;
	}

	private List<BigInteger> getRevenueDetails(List<String> list, Map<String, BigInteger> dataMap) {
		List<BigInteger> data = new ArrayList<>();
		for (String string : list) {
			for (Map.Entry<String, BigInteger> entry : dataMap.entrySet()) {
				if (string.equalsIgnoreCase(entry.getKey())) {
					data.add(dataMap.get(entry.getKey()));
				}
			}
		}
		return data;
	}

	private List<String> getListOfMonthsBetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private FinancialYearRevenue calculatingBasedOnBusinessUnit(List<RevenueResourceEntry> revenueResourceEntries,
			FinancialYear financialYear, boolean isDisplayAdditionalQuarter) {
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();
		FinancialYearTMRevenue financialYearTMRevenue = new FinancialYearTMRevenue();
		Map<Boolean, List<RevenueResourceEntry>> partitionResourceEntriesByPricingType = this
				.getPartitionResourceEntriesByPricingType(revenueResourceEntries);

		Set<Entry<Boolean, List<RevenueResourceEntry>>> entrySet = partitionResourceEntriesByPricingType.entrySet();

		for (Entry<Boolean, List<RevenueResourceEntry>> entry : entrySet) {
			if (Boolean.TRUE.equals(entry.getKey())) {
				List<RevenueResourceEntry> revenueFPResourceEntries = entry.getValue();
				financialYearRevenue = revenueServiceImpl.calculateFPRevenue(revenueFPResourceEntries, financialYear,
						isDisplayAdditionalQuarter);
			} else {
				List<RevenueResourceEntry> revenueEntryList = entry.getValue();
				financialYearTMRevenue = tmCalculation.calculateTMRevenue(revenueEntryList, financialYear,
						isDisplayAdditionalQuarter);
			}
		}

		Map<String, BigInteger> map = financialYearRevenue.getDataMap();
		Map<String, BigInteger> dataMap = financialYearTMRevenue.getDataMap();
		for (Map.Entry<String, BigInteger> entry : dataMap.entrySet()) {
			if (map.containsKey(entry.getKey())) {
				map.put(entry.getKey(), map.get(entry.getKey()).add(dataMap.get(entry.getKey())));
			}
		}
		financialYearRevenue.setDataMap(map);
		return financialYearRevenue;
	}

	private Map<Boolean, List<RevenueResourceEntry>> getPartitionResourceEntriesByPricingType(
			List<RevenueResourceEntry> revenueResourceEntries) {
		return revenueResourceEntries.stream()
				.collect(Collectors.partitioningBy(revenueResourceEntry -> Constants.PRICING_TYPE_FP
						.equals(revenueResourceEntry.getRevenueEntry().getPricingType())));
	}

	private List<String> addQuarterFieldsForGrandTotal(List<String> listOfMonthsBetweenFinancialYear,
			LocalDate fyEndDate, boolean isDisplayAdditionalQuarter) {
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

}
