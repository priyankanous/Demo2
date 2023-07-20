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
import com.nous.rollingrevenue.vo.BusinessTypeOutDTO;
import com.nous.rollingrevenue.vo.BusinessTypeResponse;
import com.nous.rollingrevenue.vo.ClientTypeReportInDTO;
import com.nous.rollingrevenue.vo.ClientTypeReportRequest;
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
	public BusinessTypeResponse getClientTypeReporDetails(ClientTypeReportRequest clientTypeReportRequest,
			boolean isDisplayAdditionalQuarter) {
		ClientTypeReportInDTO inDTO = clientTypeReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		BusinessTypeResponse businessTypeResponse = new BusinessTypeResponse();
		List<BusinessTypeOutDTO> outDTOList = new ArrayList<>();
		List<RevenueResourceEntry> revenueResourceList = new ArrayList<>();
		FinancialYearRevenue financialYearRevenue = null;
		Map<String, FinancialYearRevenue> clientMap = new HashMap<>();
		List<RevenueResourceEntry> revenueResourceEntryList = revenueResourceEntryCustomRepository
				.findRevenueResourceDetailsByClient(clientTypeReportRequest);

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			revenueResourceList.add(revenueResourceEntry);
			financialYearRevenue = calculatingRevenueDetails(revenueResourceList, financialYear,
					isDisplayAdditionalQuarter);
			clientMap.put(revenueResourceEntry.getRevenueEntry().getAccount().getAccountName(), financialYearRevenue);
		}

		Set<Entry<String, FinancialYearRevenue>> entrySet = clientMap.entrySet();

		DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy", Locale.ENGLISH);
		String year = yearFormatter.format(fyStartDate);
		int additionalQuarterYear = Integer.parseInt(year) + 1;

		Map<String, FinancialYearRevenue> collect = clientMap.entrySet().stream()
//				.sorted(Comparator.comparingBigInteger(FinancialYearRevenue::getDataMap.get("FYP " + additionalQuarterYear).reversed())
				.sorted(Map.Entry.<String, FinancialYearRevenue>comparingByValue(
						(o1, o2) -> o1.getDataMap().get("FYP " + additionalQuarterYear)
								.compareTo(o1.getDataMap().get("FYP " + additionalQuarterYear)))
						.reversed()).limit(9)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, // or throw an exception
						LinkedHashMap::new));

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetails = setQuarterlyDetails(fyStartDate);

		if ("Monthly".equalsIgnoreCase(clientTypeReportRequest.getViewType())) {
			businessTypeResponse.setLabels(listOfMonthsBetweenFinancialYear);
			outDTOList = setClientTypeDetails(listOfMonthsBetweenFinancialYear, collect);
			businessTypeResponse.setOutDTOList(outDTOList);
		} else {
			businessTypeResponse.setLabels(quarterlyDetails);
			outDTOList = setClientTypeDetails(quarterlyDetails, collect);
			businessTypeResponse.setOutDTOList(outDTOList);
		}
		businessTypeResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return businessTypeResponse;
	}

	private List<BusinessTypeOutDTO> setClientTypeDetails(List<String> list,
			Map<String, FinancialYearRevenue> collect) {
		List<BusinessTypeOutDTO> outDTOList = new ArrayList<>();
		for (Entry<String, FinancialYearRevenue> entrySet : collect.entrySet()) {
			List<BigInteger> revenue = getRevenueDetails(list, entrySet.getValue().getDataMap());
			BusinessTypeOutDTO outDTOECEB = new BusinessTypeOutDTO();
			outDTOECEB.setLabel(entrySet.getKey());
			outDTOECEB.setStack("bar1");
			outDTOECEB.setData(revenue);
			outDTOList.add(outDTOECEB);
		}
		return outDTOList;
	}

	private List<BigInteger> getRevenueDetails(List<String> list, Map<String, BigInteger> dataMap) {
		List<BigInteger> data = new ArrayList<>();
		for (String string : list) {
			for (String key : dataMap.keySet()) {
				if (string.equalsIgnoreCase(key)) {
					data.add(dataMap.get(key));
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
			if (entry.getKey()) {
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
		for (String key : dataMap.keySet()) {
			if (map.containsKey(key)) {
				map.put(key, map.get(key).add(dataMap.get(key)));
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

}
