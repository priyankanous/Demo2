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
import com.nous.rollingrevenue.service.RegionReportService;
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;
import com.nous.rollingrevenue.vo.RegionOutDTO;
import com.nous.rollingrevenue.vo.RegionReportInDTO;
import com.nous.rollingrevenue.vo.RegionReportRequest;
import com.nous.rollingrevenue.vo.RegionResponse;

@Service
public class RegionReportServiceImpl implements RegionReportService {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private RevenueServiceImpl revenueServiceImpl;

	@Autowired
	private RevenueServiceTMCalculation tmCalculation;

	@Autowired
	private RevenueResourceEntryCustomRepository revenueResourceEntryCustomRepository;

	@Override
	public RegionResponse getRegionReportDetails(RegionReportRequest regionReportRequest,
			boolean isDisplayAdditionalQuarter) {
		RegionReportInDTO inDTO = regionReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		RegionResponse regionResponse = new RegionResponse();
		List<RegionOutDTO> outDTOList = null;
		List<RevenueResourceEntry> revenueResourceEntryList = revenueResourceEntryCustomRepository
				.findRevenueResourceDetailsByRegion(regionReportRequest);

		List<RevenueResourceEntry> narevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> eurevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> apacrevenueResourceEntryList = new ArrayList<>();

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			if (Constants.REGION_NA
					.equalsIgnoreCase(revenueResourceEntry.getRevenueEntry().getRegion().getRegionDisplayName())) {
				narevenueResourceEntryList.add(revenueResourceEntry);
			} else if (Constants.REGION_EU
					.equalsIgnoreCase(revenueResourceEntry.getRevenueEntry().getRegion().getRegionDisplayName())) {
				eurevenueResourceEntryList.add(revenueResourceEntry);
			} else if (Constants.REGION_APAC
					.equalsIgnoreCase(revenueResourceEntry.getRevenueEntry().getRegion().getRegionDisplayName())) {
				apacrevenueResourceEntryList.add(revenueResourceEntry);
			}
		}

		FinancialYearRevenue financialYearRevenueNA = calculatingBasedOnRegion(narevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueEU = calculatingBasedOnRegion(eurevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueAPAC = calculatingBasedOnRegion(apacrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetails = setQuarterlyDetails(fyStartDate);

		if ("Monthly".equalsIgnoreCase(regionReportRequest.getViewType())) {
			regionResponse.setLabels(listOfMonthsBetweenFinancialYear);
			outDTOList = setRegionDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueNA,
					financialYearRevenueEU, financialYearRevenueAPAC);
			regionResponse.setOutDTOList(outDTOList);
		} else {
			regionResponse.setLabels(quarterlyDetails);
			outDTOList = setRegionDetails(quarterlyDetails, financialYearRevenueNA, financialYearRevenueEU,
					financialYearRevenueAPAC);
			regionResponse.setOutDTOList(outDTOList);
		}
		regionResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return regionResponse;
	}

	private List<RegionOutDTO> setRegionDetails(List<String> list, FinancialYearRevenue financialYearRevenueNA,
			FinancialYearRevenue financialYearRevenueEU, FinancialYearRevenue financialYearRevenueAPAC) {
		List<RegionOutDTO> outDTOList = new ArrayList<>();
		List<BigInteger> na = getRevenueDetails(list, financialYearRevenueNA.getDataMap());
		RegionOutDTO outDTONA = new RegionOutDTO();
		outDTONA.setLabel("NA");
		outDTONA.setStack("bar1");
		outDTONA.setData(na);
		outDTOList.add(outDTONA);

		List<BigInteger> eu = getRevenueDetails(list, financialYearRevenueEU.getDataMap());
		RegionOutDTO outDTOEU = new RegionOutDTO();
		outDTOEU.setLabel("EU");
		outDTOEU.setStack("bar1");
		outDTOEU.setData(eu);
		outDTOList.add(outDTOEU);

		List<BigInteger> apac = getRevenueDetails(list, financialYearRevenueAPAC.getDataMap());
		RegionOutDTO outDTOAPAC = new RegionOutDTO();
		outDTOAPAC.setLabel("APAC");
		outDTOAPAC.setStack("bar1");
		outDTOAPAC.setData(apac);
		outDTOList.add(outDTOAPAC);
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

	private FinancialYearRevenue calculatingBasedOnRegion(List<RevenueResourceEntry> revenueResourceEntries,
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
}
