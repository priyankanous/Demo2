package com.nous.rollingrevenue.service.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import com.nous.rollingrevenue.service.BusinessTypeReportService;
import com.nous.rollingrevenue.vo.BusinessTypeOutDTO;
import com.nous.rollingrevenue.vo.BusinessTypeReportInDTO;
import com.nous.rollingrevenue.vo.BusinessTypeReportRequest;
import com.nous.rollingrevenue.vo.BusinessTypeResponse;
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;

@Service
public class BusinessTypeReportServiceImpl implements BusinessTypeReportService {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private RevenueServiceImpl revenueServiceImpl;

	@Autowired
	private RevenueServiceTMCalculation tmCalculation;

	@Autowired
	private RevenueResourceEntryCustomRepository revenueResourceEntryCustomRepository;

	@Override
	public BusinessTypeResponse getBusinessTypeReportDetails(BusinessTypeReportRequest businessTypeReportRequest,
			boolean isDisplayAdditionalQuarter) {
		BusinessTypeReportInDTO InDTO = businessTypeReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(InDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		BusinessTypeResponse businessTypeResponse = new BusinessTypeResponse();
		List<BusinessTypeOutDTO> outDTOList = new ArrayList<>();
		List<RevenueResourceEntry> revenueResourceEntryList = revenueResourceEntryCustomRepository
				.findRevenueResourceDetails(businessTypeReportRequest);

		List<RevenueResourceEntry> ecebrevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> ecnbrevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> ncnbrevenueResourceEntryList = new ArrayList<>();

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			if (Constants.BUSINESS_TYPE_ECEB
					.equalsIgnoreCase(revenueResourceEntry.getBusinessType().getBusinessTypeDisplayName())) {
				ecebrevenueResourceEntryList.add(revenueResourceEntry);
			} else if (Constants.BUSINESS_TYPE_ECNB
					.equalsIgnoreCase(revenueResourceEntry.getBusinessType().getBusinessTypeDisplayName())) {
				ecnbrevenueResourceEntryList.add(revenueResourceEntry);
			} else if (Constants.BUSINESS_TYPE_NCNB
					.equalsIgnoreCase(revenueResourceEntry.getBusinessType().getBusinessTypeDisplayName())) {
				ncnbrevenueResourceEntryList.add(revenueResourceEntry);
			}
		}

		FinancialYearRevenue financialYearRevenueECEB = calculatingBasedOnBusinessType(ecebrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueECNB = calculatingBasedOnBusinessType(ecnbrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueNCNB = calculatingBasedOnBusinessType(ncnbrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetails = setQuarterlyDetails(fyStartDate);

		if ("Monthly".equalsIgnoreCase(businessTypeReportRequest.getViewType())) {
			businessTypeResponse.setLabels(listOfMonthsBetweenFinancialYear);
			outDTOList = setBusinessTypeDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueECEB,
					financialYearRevenueECNB, financialYearRevenueNCNB);
			businessTypeResponse.setOutDTOList(outDTOList);
		} else {
			businessTypeResponse.setLabels(quarterlyDetails);
			outDTOList = setBusinessTypeDetails(quarterlyDetails, financialYearRevenueECEB, financialYearRevenueECNB,
					financialYearRevenueNCNB);
			businessTypeResponse.setOutDTOList(outDTOList);
		}
		businessTypeResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return businessTypeResponse;
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

	private List<BusinessTypeOutDTO> setBusinessTypeDetails(List<String> list,
			FinancialYearRevenue financialYearRevenueECEB, FinancialYearRevenue financialYearRevenueECNB,
			FinancialYearRevenue financialYearRevenueNCNB) {
		List<BusinessTypeOutDTO> outDTOList = new ArrayList<>();
		List<BigInteger> eceb = getRevenueDetails(list, financialYearRevenueECEB.getDataMap());
		BusinessTypeOutDTO outDTOECEB = new BusinessTypeOutDTO();
		outDTOECEB.setLabel("ECEB");
		outDTOECEB.setStack("bar1");
		outDTOECEB.setData(eceb);
		outDTOList.add(outDTOECEB);

		List<BigInteger> ecnb = getRevenueDetails(list, financialYearRevenueECNB.getDataMap());
		BusinessTypeOutDTO outDTOECNB = new BusinessTypeOutDTO();
		outDTOECNB.setLabel("ECNB");
		outDTOECNB.setStack("bar1");
		outDTOECNB.setData(ecnb);
		outDTOList.add(outDTOECNB);

		List<BigInteger> ncnb = getRevenueDetails(list, financialYearRevenueNCNB.getDataMap());
		BusinessTypeOutDTO outDTONCNB = new BusinessTypeOutDTO();
		outDTONCNB.setLabel("NCNB");
		outDTONCNB.setStack("bar1");
		outDTONCNB.setData(ncnb);
		outDTOList.add(outDTONCNB);

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

	private List<String> getListOfMonthsBetweenDates(LocalDate startDate, LocalDate endDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-yyyy", Locale.ENGLISH);
		return Stream.iterate(startDate.withDayOfMonth(1), date -> date.plusMonths(1))
				.limit(ChronoUnit.MONTHS.between(startDate, endDate.plusMonths(1))).map(date -> date.format(formatter))
				.collect(Collectors.toList());
	}

	private FinancialYearRevenue calculatingBasedOnBusinessType(List<RevenueResourceEntry> revenueResourceEntries,
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

	@Override
	public List<RevenueResourceEntry> getRevenueResourceEntryFilter(BusinessTypeReportRequest businessTypeReportRequest,
			boolean isDisplayAdditionalQuarter) {
		return revenueResourceEntryCustomRepository.findRevenueResourceDetails(businessTypeReportRequest);
	}

}
