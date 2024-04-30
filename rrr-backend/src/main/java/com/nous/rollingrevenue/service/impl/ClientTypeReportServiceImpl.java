package com.nous.rollingrevenue.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.nous.rollingrevenue.service.ClientTypeReportService;
import com.nous.rollingrevenue.vo.ClientTypeReportInDTO;
import com.nous.rollingrevenue.vo.ClientTypeReportOutDTO;
import com.nous.rollingrevenue.vo.ClientTypeReportRequest;
import com.nous.rollingrevenue.vo.ClientTypeReportResponse;
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;

@Service
public class ClientTypeReportServiceImpl implements ClientTypeReportService {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private RevenueServiceImpl revenueServiceImpl;

	@Autowired
	private RevenueServiceTMCalculation tmCalculation;

	@Autowired
	private RevenueResourceEntryCustomRepository revenueResourceEntryCustomRepository;

	@Override
	public ClientTypeReportResponse getClientTypeReporDetails(ClientTypeReportRequest clientTypeReportRequest,
			boolean isDisplayAdditionalQuarter) {
		ClientTypeReportInDTO inDTO = clientTypeReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		ClientTypeReportResponse clientTypeReportResponse = new ClientTypeReportResponse();
		List<ClientTypeReportOutDTO> outDTOList = null;
		List<RevenueResourceEntry> revenueResourceList = new ArrayList<>();
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();
		Map<String, FinancialYearRevenue> clientMap = new HashMap<>();
		List<RevenueResourceEntry> revenueResourceEntryList = revenueResourceEntryCustomRepository
				.findRevenueResourceDetailsByClient(clientTypeReportRequest);

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			revenueResourceList.add(revenueResourceEntry);
			financialYearRevenue = calculatingRevenueDetails(revenueResourceList, financialYear,
					isDisplayAdditionalQuarter);
			clientMap.put(revenueResourceEntry.getRevenueEntry().getAccount().getAccountName(), financialYearRevenue);
		}

		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyStartDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;

		Map<String, FinancialYearRevenue> collect = clientMap.entrySet().stream().sorted((entry1, entry2) -> {
			BigInteger value1 = entry1.getValue().getDataMap().get("FYP " + additionalQuarterYear);
			BigInteger value2 = entry2.getValue().getDataMap().get("FYP " + additionalQuarterYear);
			return value2.compareTo(value1); // descending order
		}).limit(9)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));

		// Exclude the top 5 records from the original map.
		Map<String, FinancialYearRevenue> excludeTopRecords = clientMap.entrySet().stream()
				.filter(entry -> !collect.containsKey(entry.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		Map<String, BigInteger> resultMap = new HashMap<>();
		Map<String, BigInteger> fyRevenueMap = new LinkedHashMap<>();
		if (excludeTopRecords.entrySet().isEmpty()) {
			List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
			this.addQuarterFieldsForGrandTotal(listOfMonthsBetweenFinancialYear, fyStartDate, false);
			listOfMonthsBetweenFinancialYear.stream()
					.forEach(monthYear -> fyRevenueMap.put(monthYear, BigInteger.ZERO));
			financialYearRevenue.setDataMap(fyRevenueMap);
			Map<String, BigInteger> dataMap = financialYearRevenue.getDataMap();
			for (Map.Entry<String, BigInteger> entryMap : dataMap.entrySet()) {
				if (resultMap.containsKey(entryMap.getKey())) {
					BigInteger currentSum = resultMap.get(entryMap.getKey());
					resultMap.put(entryMap.getKey(), currentSum.add(entryMap.getValue()));
				} else {
					resultMap.put(entryMap.getKey(), entryMap.getValue());
				}
			}
		} else {
			for (Map.Entry<String, FinancialYearRevenue> entry : excludeTopRecords.entrySet()) {
				FinancialYearRevenue yearRevenue = entry.getValue();
				Map<String, BigInteger> dataMap = yearRevenue.getDataMap();
				for (Map.Entry<String, BigInteger> entryMap : dataMap.entrySet()) {
					if (resultMap.containsKey(entryMap.getKey())) {
						BigInteger currentSum = resultMap.get(entryMap.getKey());
						resultMap.put(entryMap.getKey(), currentSum.add(entryMap.getValue()));
					} else {
						resultMap.put(entryMap.getKey(), entryMap.getValue());
					}
				}
			}
		}
		FinancialYearRevenue fyRevenue = new FinancialYearRevenue();
		List<String> listOfMonths = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		fyRevenue.setDataMap(resultMap);
		collect.put("Others", fyRevenue);
		FinancialYearRevenue financialYearRevenueTotal = calculatingBasedOnClientForGrandTotal(collect, fyStartDate,
				fyEndDate);

		List<String> quarterlyDetailsForChart = setQuarterlyDetailsForClientTypeForChart(fyStartDate);
		List<String> quarterlyDetailsForTabular = setQuarterlyDetailsForClientTypeForTabular(fyStartDate);

		if ("Chart".equalsIgnoreCase(clientTypeReportRequest.getOutPutType())) {
			if ("Monthly".equalsIgnoreCase(clientTypeReportRequest.getViewType())) {
				clientTypeReportResponse.setLabels(listOfMonths);
				outDTOList = setClientTypeDetails(listOfMonths, collect, null, false);
				clientTypeReportResponse.setOutDTOList(outDTOList);
			} else {
				clientTypeReportResponse.setLabels(quarterlyDetailsForChart);
				outDTOList = setClientTypeDetails(quarterlyDetailsForChart, collect, null, false);
				clientTypeReportResponse.setOutDTOList(outDTOList);
			}
		} else {
			if ("Monthly".equalsIgnoreCase(clientTypeReportRequest.getViewType())) {
				clientTypeReportResponse.setLabels(this.addQuarterFields(listOfMonths, fyStartDate));
				outDTOList = setClientTypeDetails(listOfMonths, collect, financialYearRevenueTotal, true);
				clientTypeReportResponse.setOutDTOList(outDTOList);
			} else {
				clientTypeReportResponse.setLabels(quarterlyDetailsForTabular);
				outDTOList = setClientTypeDetails(quarterlyDetailsForTabular, collect, financialYearRevenueTotal, true);
				clientTypeReportResponse.setOutDTOList(outDTOList);
			}
		}
		clientTypeReportResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return clientTypeReportResponse;
	}

	private List<ClientTypeReportOutDTO> setClientTypeDetails(List<String> list,
			Map<String, FinancialYearRevenue> collect, FinancialYearRevenue financialYearRevenueTotal,
			boolean isTabular) {
		List<ClientTypeReportOutDTO> outDTOList = new ArrayList<>();
		for (Entry<String, FinancialYearRevenue> entrySet : collect.entrySet()) {
			List<BigInteger> revenue = getRevenueDetails(list, entrySet.getValue().getDataMap());
			ClientTypeReportOutDTO outDTOECEB = new ClientTypeReportOutDTO();
			outDTOECEB.setLabel(entrySet.getKey());
			outDTOECEB.setStack("bar1");
			outDTOECEB.setData(revenue);
			outDTOList.add(outDTOECEB);
		}
		if (isTabular) {
			List<BigInteger> grandTotal = getRevenueDetails(list, financialYearRevenueTotal.getDataMap());
			ClientTypeReportOutDTO outDTOTotal = new ClientTypeReportOutDTO();
			outDTOTotal.setLabel("Grand Total");
			outDTOTotal.setStack("bar1");
			outDTOTotal.setData(grandTotal);
			outDTOList.add(outDTOTotal);
		}
		return outDTOList;
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

	private List<String> setQuarterlyDetailsForClientType(LocalDate fyEndDate) {
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

	private List<String> getListOfMonthsBetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private FinancialYearRevenue calculatingRevenueDetails(List<RevenueResourceEntry> revenueResourceEntries,
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

	private FinancialYearRevenue calculatingBasedOnClientForGrandTotal(Map<String, FinancialYearRevenue> collect,
			LocalDate fyStartDate, LocalDate fyEndDate) {
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();

		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();
		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		this.addQuarterFieldsForGrandTotal(listOfMonthsBetweenFinancialYear, fyStartDate, false);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));
		financialYearRevenue.setDataMap(fyRevenue);

		Map<String, BigInteger> resultMap = new HashMap<>();
		for (Map.Entry<String, FinancialYearRevenue> entry : collect.entrySet()) {
			FinancialYearRevenue yearRevenue = entry.getValue();
			Map<String, BigInteger> dataMap = yearRevenue.getDataMap();
			for (Map.Entry<String, BigInteger> entryMap : dataMap.entrySet()) {
				if (resultMap.containsKey(entryMap.getKey())) {
					BigInteger currentSum = resultMap.get(entryMap.getKey());
					resultMap.put(entryMap.getKey(), currentSum.add(entryMap.getValue()));
				} else {
					resultMap.put(entryMap.getKey(), entryMap.getValue());
				}
			}
		}
		financialYearRevenue.setDataMap(resultMap);
		return financialYearRevenue;
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

	private List<String> setQuarterlyDetailsForClientTypeForChart(LocalDate fyEndDate) {
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

	private List<String> setQuarterlyDetailsForClientTypeForTabular(LocalDate fyEndDate) {
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

}
