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
		BusinessTypeReportInDTO inDTO = businessTypeReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		BusinessTypeResponse businessTypeResponse = new BusinessTypeResponse();
		List<RevenueResourceEntry> revenueResourceEntryList = revenueResourceEntryCustomRepository
				.findRevenueResourceDetails(businessTypeReportRequest);

		List<RevenueResourceEntry> ecebrevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> ecnbrevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> ncnbrevenueResourceEntryList = new ArrayList<>();

		List<RevenueResourceEntry> ecebConfirmedList = new ArrayList<>();
		List<RevenueResourceEntry> ecebExpectedList = new ArrayList<>();
		List<RevenueResourceEntry> ecebUpsideList = new ArrayList<>();
		List<RevenueResourceEntry> ecebHighUpsideList = new ArrayList<>();

		List<RevenueResourceEntry> ecnbConfirmedList = new ArrayList<>();
		List<RevenueResourceEntry> ecnbExpectedList = new ArrayList<>();
		List<RevenueResourceEntry> ecnbUpsideList = new ArrayList<>();
		List<RevenueResourceEntry> ecnbHighUpsideList = new ArrayList<>();

		List<RevenueResourceEntry> ncnbConfirmedList = new ArrayList<>();
		List<RevenueResourceEntry> ncnbExpectedList = new ArrayList<>();
		List<RevenueResourceEntry> ncnbUpsideList = new ArrayList<>();
		List<RevenueResourceEntry> ncnbHighUpsideList = new ArrayList<>();

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			if (Constants.BUSINESS_TYPE_ECEB
					.equalsIgnoreCase(revenueResourceEntry.getBusinessType().getBusinessTypeDisplayName())) {
				checkECEBBusinessTypeReport(revenueResourceEntry, ecebrevenueResourceEntryList, ecebConfirmedList,
						ecebExpectedList, ecebUpsideList, ecebHighUpsideList);
			} else if (Constants.BUSINESS_TYPE_ECNB
					.equalsIgnoreCase(revenueResourceEntry.getBusinessType().getBusinessTypeDisplayName())) {
				checkECNBBusinessTypeReport(revenueResourceEntry, ecnbrevenueResourceEntryList, ecnbConfirmedList,
						ecnbExpectedList, ecnbUpsideList, ecnbHighUpsideList);
			} else if (Constants.BUSINESS_TYPE_NCNB
					.equalsIgnoreCase(revenueResourceEntry.getBusinessType().getBusinessTypeDisplayName())) {
				checkNCNBBusinessTypeReport(revenueResourceEntry, ncnbrevenueResourceEntryList, ncnbConfirmedList,
						ncnbExpectedList, ncnbUpsideList, ncnbHighUpsideList);
			}
		}

		FinancialYearRevenue financialYearRevenueECEB = calculatingBasedOnBusinessType(ecebrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue ecebConfirmed = calculatingBasedOnBusinessType(ecebConfirmedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ecebExpected = calculatingBasedOnBusinessType(ecebExpectedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ecebUpside = calculatingBasedOnBusinessType(ecebUpsideList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ecebHighUpside = calculatingBasedOnBusinessType(ecebHighUpsideList, financialYear,
				isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueECNB = calculatingBasedOnBusinessType(ecnbrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue ecnbConfirmed = calculatingBasedOnBusinessType(ecnbConfirmedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ecnbExpected = calculatingBasedOnBusinessType(ecnbExpectedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ecnbUpside = calculatingBasedOnBusinessType(ecnbUpsideList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ecnbHighUpside = calculatingBasedOnBusinessType(ecnbHighUpsideList, financialYear,
				isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueNCNB = calculatingBasedOnBusinessType(ncnbrevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);
		FinancialYearRevenue ncnbConfirmed = calculatingBasedOnBusinessType(ncnbConfirmedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ncnbExpected = calculatingBasedOnBusinessType(ncnbExpectedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ncnbUpside = calculatingBasedOnBusinessType(ncnbUpsideList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue ncnbHighUpside = calculatingBasedOnBusinessType(ncnbHighUpsideList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueTotal = calculatingBasedOnBusinessTypeForGrandTotal(
				financialYearRevenueECEB, financialYearRevenueECNB, financialYearRevenueNCNB, fyStartDate, fyEndDate);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetailsForChart = setQuarterlyDetailsForBussinessForChart(fyStartDate);
		List<String> quarterlyDetailsForTabular = setQuarterlyDetailsForBussinessForTabular(fyStartDate);

		List<BusinessTypeOutDTO> outDTOList = null;
		if ("Chart".equalsIgnoreCase(businessTypeReportRequest.getOutPutType())) {
			if ("Monthly".equalsIgnoreCase(businessTypeReportRequest.getViewType())) {
				businessTypeResponse.setLabels(listOfMonthsBetweenFinancialYear);
				outDTOList = setBusinessTypeDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueECEB,
						financialYearRevenueECNB, financialYearRevenueNCNB, null, false);
				setProbabilityTypesForECEB(listOfMonthsBetweenFinancialYear, ecebConfirmed, ecebExpected, ecebUpside,
						ecebHighUpside, outDTOList);
				setProbabilityTypesForECNB(listOfMonthsBetweenFinancialYear, ecnbConfirmed, ecnbExpected, ecnbUpside,
						ecnbHighUpside, outDTOList);
				setProbabilityTypesForNCNB(listOfMonthsBetweenFinancialYear, ncnbConfirmed, ncnbExpected, ncnbUpside,
						ncnbHighUpside, outDTOList);
				businessTypeResponse.setOutDTOList(outDTOList);
			} else {
				businessTypeResponse.setLabels(quarterlyDetailsForChart);
				outDTOList = setBusinessTypeDetails(quarterlyDetailsForChart, financialYearRevenueECEB,
						financialYearRevenueECNB, financialYearRevenueNCNB, null, false);
				setProbabilityTypesForECEB(quarterlyDetailsForChart, ecebConfirmed, ecebExpected, ecebUpside,
						ecebHighUpside, outDTOList);
				setProbabilityTypesForECNB(quarterlyDetailsForChart, ecnbConfirmed, ecnbExpected, ecnbUpside,
						ecnbHighUpside, outDTOList);
				setProbabilityTypesForNCNB(quarterlyDetailsForChart, ncnbConfirmed, ncnbExpected, ncnbUpside,
						ncnbHighUpside, outDTOList);
				businessTypeResponse.setOutDTOList(outDTOList);
			}
		} else {
			if ("Monthly".equalsIgnoreCase(businessTypeReportRequest.getViewType())) {
				businessTypeResponse.setLabels(this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyStartDate));
				outDTOList = setBusinessTypeDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueECEB,
						financialYearRevenueECNB, financialYearRevenueNCNB, financialYearRevenueTotal, true);
				setProbabilityTypesForECEB(listOfMonthsBetweenFinancialYear, ecebConfirmed, ecebExpected, ecebUpside,
						ecebHighUpside, outDTOList);
				setProbabilityTypesForECNB(listOfMonthsBetweenFinancialYear, ecnbConfirmed, ecnbExpected, ecnbUpside,
						ecnbHighUpside, outDTOList);
				setProbabilityTypesForNCNB(listOfMonthsBetweenFinancialYear, ncnbConfirmed, ncnbExpected, ncnbUpside,
						ncnbHighUpside, outDTOList);
				businessTypeResponse.setOutDTOList(outDTOList);
			} else {
				businessTypeResponse.setLabels(quarterlyDetailsForTabular);
				outDTOList = setBusinessTypeDetails(quarterlyDetailsForTabular, financialYearRevenueECEB,
						financialYearRevenueECNB, financialYearRevenueNCNB, financialYearRevenueTotal, true);
				setProbabilityTypesForECEB(quarterlyDetailsForTabular, ecebConfirmed, ecebExpected, ecebUpside,
						ecebHighUpside, outDTOList);
				setProbabilityTypesForECNB(quarterlyDetailsForTabular, ecnbConfirmed, ecnbExpected, ecnbUpside,
						ecnbHighUpside, outDTOList);
				setProbabilityTypesForNCNB(quarterlyDetailsForTabular, ncnbConfirmed, ncnbExpected, ncnbUpside,
						ncnbHighUpside, outDTOList);
				businessTypeResponse.setOutDTOList(outDTOList);
			}
		}

		businessTypeResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return businessTypeResponse;
	}

	private void checkNCNBBusinessTypeReport(RevenueResourceEntry revenueResourceEntry,
			List<RevenueResourceEntry> ncnbrevenueResourceEntryList, List<RevenueResourceEntry> ncnbConfirmedList,
			List<RevenueResourceEntry> ncnbExpectedList, List<RevenueResourceEntry> ncnbUpsideList,
			List<RevenueResourceEntry> ncnbHighUpsideList) {
		ncnbrevenueResourceEntryList.add(revenueResourceEntry);
		if (Constants.PROBABILITY_TYPE_CONFIRMED.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ncnbConfirmedList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_EXCEPTED.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ncnbExpectedList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_UPSIDE.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ncnbUpsideList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_HIGH_UPSIDE.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ncnbHighUpsideList.add(revenueResourceEntry);
		}
	}

	private void checkECNBBusinessTypeReport(RevenueResourceEntry revenueResourceEntry,
			List<RevenueResourceEntry> ecnbrevenueResourceEntryList, List<RevenueResourceEntry> ecnbConfirmedList,
			List<RevenueResourceEntry> ecnbExpectedList, List<RevenueResourceEntry> ecnbUpsideList,
			List<RevenueResourceEntry> ecnbHighUpsideList) {
		ecnbrevenueResourceEntryList.add(revenueResourceEntry);
		if (Constants.PROBABILITY_TYPE_CONFIRMED.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecnbConfirmedList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_EXCEPTED.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecnbExpectedList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_UPSIDE.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecnbUpsideList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_HIGH_UPSIDE.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecnbHighUpsideList.add(revenueResourceEntry);
		}
	}

	private void checkECEBBusinessTypeReport(RevenueResourceEntry revenueResourceEntry,
			List<RevenueResourceEntry> ecebrevenueResourceEntryList, List<RevenueResourceEntry> ecebConfirmedList,
			List<RevenueResourceEntry> ecebExpectedList, List<RevenueResourceEntry> ecebUpsideList,
			List<RevenueResourceEntry> ecebHighUpsideList) {
		ecebrevenueResourceEntryList.add(revenueResourceEntry);
		if (Constants.PROBABILITY_TYPE_CONFIRMED.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecebConfirmedList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_EXCEPTED.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecebExpectedList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_UPSIDE.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecebUpsideList.add(revenueResourceEntry);
		} else if (Constants.PROBABILITY_TYPE_HIGH_UPSIDE.equalsIgnoreCase(
				revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
			ecebHighUpsideList.add(revenueResourceEntry);
		}

	}

	private void setProbabilityTypesForECEB(List<String> list, FinancialYearRevenue confirmedRevenue,
			FinancialYearRevenue expectedRevenue, FinancialYearRevenue upsideRevenue,
			FinancialYearRevenue highUpsideRevenue, List<BusinessTypeOutDTO> outDTOList) {
		List<BigInteger> confirmed = getRevenueDetails(list, confirmedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOConfirmed = new BusinessTypeOutDTO();
		outDTOConfirmed.setLabel("Confirmed-ECEB");
		outDTOConfirmed.setStack("bar2");
		outDTOConfirmed.setData(confirmed);
		outDTOList.add(outDTOConfirmed);

		List<BigInteger> expected = getRevenueDetails(list, expectedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOExpected = new BusinessTypeOutDTO();
		outDTOExpected.setLabel("Excepted-ECEB");
		outDTOExpected.setStack("bar2");
		outDTOExpected.setData(expected);
		outDTOList.add(outDTOExpected);

		List<BigInteger> upside = getRevenueDetails(list, upsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOUpside = new BusinessTypeOutDTO();
		outDTOUpside.setLabel("Upside-ECEB");
		outDTOUpside.setStack("bar2");
		outDTOUpside.setData(upside);
		outDTOList.add(outDTOUpside);

		List<BigInteger> high = getRevenueDetails(list, highUpsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOHigh = new BusinessTypeOutDTO();
		outDTOHigh.setLabel("High-Upside-ECEB");
		outDTOHigh.setStack("bar2");
		outDTOHigh.setData(high);
		outDTOList.add(outDTOHigh);
	}

	private void setProbabilityTypesForECNB(List<String> list, FinancialYearRevenue confirmedRevenue,
			FinancialYearRevenue expectedRevenue, FinancialYearRevenue upsideRevenue,
			FinancialYearRevenue highUpsideRevenue, List<BusinessTypeOutDTO> outDTOList) {
		List<BigInteger> confirmed = getRevenueDetails(list, confirmedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOConfirmed = new BusinessTypeOutDTO();
		outDTOConfirmed.setLabel("Confirmed-ECNB");
		outDTOConfirmed.setStack("bar2");
		outDTOConfirmed.setData(confirmed);
		outDTOList.add(outDTOConfirmed);

		List<BigInteger> expected = getRevenueDetails(list, expectedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOExpected = new BusinessTypeOutDTO();
		outDTOExpected.setLabel("Excepted-ECNB");
		outDTOExpected.setStack("bar2");
		outDTOExpected.setData(expected);
		outDTOList.add(outDTOExpected);

		List<BigInteger> upside = getRevenueDetails(list, upsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOUpside = new BusinessTypeOutDTO();
		outDTOUpside.setLabel("Upside-ECNB");
		outDTOUpside.setStack("bar2");
		outDTOUpside.setData(upside);
		outDTOList.add(outDTOUpside);

		List<BigInteger> high = getRevenueDetails(list, highUpsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOHigh = new BusinessTypeOutDTO();
		outDTOHigh.setLabel("High-Upside-ECNB");
		outDTOHigh.setStack("bar2");
		outDTOHigh.setData(high);
		outDTOList.add(outDTOHigh);
	}

	private void setProbabilityTypesForNCNB(List<String> list, FinancialYearRevenue confirmedRevenue,
			FinancialYearRevenue expectedRevenue, FinancialYearRevenue upsideRevenue,
			FinancialYearRevenue highUpsideRevenue, List<BusinessTypeOutDTO> outDTOList) {
		List<BigInteger> confirmed = getRevenueDetails(list, confirmedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOConfirmed = new BusinessTypeOutDTO();
		outDTOConfirmed.setLabel("Confirmed-NCNB");
		outDTOConfirmed.setStack("bar2");
		outDTOConfirmed.setData(confirmed);
		outDTOList.add(outDTOConfirmed);

		List<BigInteger> expected = getRevenueDetails(list, expectedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOExpected = new BusinessTypeOutDTO();
		outDTOExpected.setLabel("Excepted-NCNB");
		outDTOExpected.setStack("bar2");
		outDTOExpected.setData(expected);
		outDTOList.add(outDTOExpected);

		List<BigInteger> upside = getRevenueDetails(list, upsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOUpside = new BusinessTypeOutDTO();
		outDTOUpside.setLabel("Upside-NCNB");
		outDTOUpside.setStack("bar2");
		outDTOUpside.setData(upside);
		outDTOList.add(outDTOUpside);

		List<BigInteger> high = getRevenueDetails(list, highUpsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOHigh = new BusinessTypeOutDTO();
		outDTOHigh.setLabel("High-Upside-NCNB");
		outDTOHigh.setStack("bar2");
		outDTOHigh.setData(high);
		outDTOList.add(outDTOHigh);
	}

	private List<BusinessTypeOutDTO> setProbabilityTypes(List<String> list, FinancialYearRevenue confirmedRevenue,
			FinancialYearRevenue expectedRevenue, FinancialYearRevenue upsideRevenue,
			FinancialYearRevenue highUpsideRevenue, FinancialYearRevenue financialYearRevenueTotal, boolean isTabular) {
		List<BusinessTypeOutDTO> outDTOList = new ArrayList<>();
		List<BigInteger> confirmed = getRevenueDetails(list, confirmedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOConfirmed = new BusinessTypeOutDTO();
		outDTOConfirmed.setLabel("Confirmed");
		outDTOConfirmed.setStack("bar2");
		outDTOConfirmed.setData(confirmed);
		outDTOList.add(outDTOConfirmed);

		List<BigInteger> expected = getRevenueDetails(list, expectedRevenue.getDataMap());
		BusinessTypeOutDTO outDTOExpected = new BusinessTypeOutDTO();
		outDTOExpected.setLabel("Excepted");
		outDTOExpected.setStack("bar2");
		outDTOExpected.setData(expected);
		outDTOList.add(outDTOExpected);

		List<BigInteger> upside = getRevenueDetails(list, upsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOUpside = new BusinessTypeOutDTO();
		outDTOUpside.setLabel("Upside");
		outDTOUpside.setStack("bar2");
		outDTOUpside.setData(upside);
		outDTOList.add(outDTOUpside);

		List<BigInteger> high = getRevenueDetails(list, highUpsideRevenue.getDataMap());
		BusinessTypeOutDTO outDTOHigh = new BusinessTypeOutDTO();
		outDTOHigh.setLabel("High-Upside");
		outDTOHigh.setStack("bar2");
		outDTOHigh.setData(high);
		outDTOList.add(outDTOHigh);

		if (isTabular) {
			List<BigInteger> grandTotal = getRevenueDetails(list, financialYearRevenueTotal.getDataMap());
			BusinessTypeOutDTO outDTOTotal = new BusinessTypeOutDTO();
			outDTOTotal.setLabel("Grand Total");
			outDTOTotal.setStack("bar1");
			outDTOTotal.setData(grandTotal);
			outDTOList.add(outDTOTotal);
		}
		return outDTOList;
	}

	private List<BusinessTypeOutDTO> setBusinessTypeDetails(List<String> list,
			FinancialYearRevenue financialYearRevenueECEB, FinancialYearRevenue financialYearRevenueECNB,
			FinancialYearRevenue financialYearRevenueNCNB, FinancialYearRevenue financialYearRevenueTotal,
			boolean isTabular) {
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

	private List<String> setOnlyFewQuarterlyDetails(LocalDate fyEndDate) {
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

	private List<String> setQuarterlyDetailsForBussinessForTabular(LocalDate fyEndDate) {
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

	private List<String> setQuarterlyDetailsForBussinessForChart(LocalDate fyEndDate) {
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

	private FinancialYearRevenue calculatingBasedOnBusinessType(List<RevenueResourceEntry> revenueResourceEntries,
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

	@Override
	public List<RevenueResourceEntry> getRevenueResourceEntryFilter(BusinessTypeReportRequest businessTypeReportRequest,
			boolean isDisplayAdditionalQuarter) {
		return revenueResourceEntryCustomRepository.findRevenueResourceDetails(businessTypeReportRequest);
	}

	@Override
	public BusinessTypeResponse getProbabilityTypeReportDetails(BusinessTypeReportRequest businessTypeReportRequest,
			boolean isDisplayAdditionalQuarter) {
		BusinessTypeReportInDTO inDTO = businessTypeReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
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

		List<RevenueResourceEntry> confirmedList = new ArrayList<>();
		List<RevenueResourceEntry> expectedList = new ArrayList<>();
		List<RevenueResourceEntry> upsideList = new ArrayList<>();
		List<RevenueResourceEntry> highUpsideList = new ArrayList<>();

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			if (Constants.PROBABILITY_TYPE_CONFIRMED.equalsIgnoreCase(
					revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
				confirmedList.add(revenueResourceEntry);
			} else if (Constants.PROBABILITY_TYPE_EXCEPTED.equalsIgnoreCase(
					revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
				expectedList.add(revenueResourceEntry);
			} else if (Constants.PROBABILITY_TYPE_UPSIDE.equalsIgnoreCase(
					revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
				upsideList.add(revenueResourceEntry);
			} else if (Constants.PROBABILITY_TYPE_HIGH_UPSIDE.equalsIgnoreCase(
					revenueResourceEntry.getRevenueEntry().getProbabilityType().getProbabilityTypeName())) {
				highUpsideList.add(revenueResourceEntry);
			}
		}

		FinancialYearRevenue confirmed = calculatingBasedOnBusinessType(confirmedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue expected = calculatingBasedOnBusinessType(expectedList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue upside = calculatingBasedOnBusinessType(upsideList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue highUpside = calculatingBasedOnBusinessType(highUpsideList, financialYear,
				isDisplayAdditionalQuarter);
		FinancialYearRevenue financialYearRevenueTotal = calculatingBasedOnProbabilityForGrandTotal(confirmed, expected,
				upside, highUpside, fyStartDate, fyEndDate);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetailsForChart = setQuarterlyDetailsForProbabilityForChart(fyStartDate);
		List<String> quarterlyDetailsForTabular = setQuarterlyDetailsForProbabilityForTabular(fyStartDate);

		if ("Chart".equalsIgnoreCase(businessTypeReportRequest.getOutPutType())) {
			if ("Monthly".equalsIgnoreCase(businessTypeReportRequest.getViewType())) {
				businessTypeResponse.setLabels(listOfMonthsBetweenFinancialYear);
				outDTOList = setProbabilityTypes(listOfMonthsBetweenFinancialYear, confirmed, expected, upside,
						highUpside, null, false);
				businessTypeResponse.setOutDTOList(outDTOList);
			} else {
				businessTypeResponse.setLabels(quarterlyDetailsForChart);
				outDTOList = setProbabilityTypes(quarterlyDetailsForChart, confirmed, expected, upside, highUpside,
						null, false);
				businessTypeResponse.setOutDTOList(outDTOList);
			}
		} else {
			if ("Monthly".equalsIgnoreCase(businessTypeReportRequest.getViewType())) {
				businessTypeResponse.setLabels(this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyStartDate));
				outDTOList = setProbabilityTypes(listOfMonthsBetweenFinancialYear, confirmed, expected, upside,
						highUpside, financialYearRevenueTotal, true);
				businessTypeResponse.setOutDTOList(outDTOList);
			} else {
				businessTypeResponse.setLabels(quarterlyDetailsForTabular);
				outDTOList = setProbabilityTypes(quarterlyDetailsForTabular, confirmed, expected, upside, highUpside,
						financialYearRevenueTotal, true);
				businessTypeResponse.setOutDTOList(outDTOList);
			}
		}
		businessTypeResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return businessTypeResponse;
	}

	private FinancialYearRevenue calculatingBasedOnProbabilityForGrandTotal(
			FinancialYearRevenue financialYearRevenueConfirmed, FinancialYearRevenue financialYearRevenueExpected,
			FinancialYearRevenue financialYearRevenueUpside, FinancialYearRevenue financialYearRevenueHighUpside,
			LocalDate fyStartDate, LocalDate fyEndDate) {
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();

		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();
		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		this.addQuarterFieldsForGrandTotal(listOfMonthsBetweenFinancialYear, fyStartDate, false);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));
		financialYearRevenue.setDataMap(fyRevenue);

		Map<String, BigInteger> mapConfirmed = financialYearRevenueConfirmed.getDataMap();
		Map<String, BigInteger> grandTotal = financialYearRevenue.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapConfirmed.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapConfirmed.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapExpected = financialYearRevenueExpected.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapExpected.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapExpected.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapUpside = financialYearRevenueUpside.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapUpside.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapUpside.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapHighUpside = financialYearRevenueHighUpside.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapHighUpside.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapHighUpside.get(entry.getKey())));
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

	private List<String> setQuarterlyDetailsForProbabilityForTabular(LocalDate fyEndDate) {
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

	private List<String> setQuarterlyDetailsForProbabilityForChart(LocalDate fyEndDate) {
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

	private FinancialYearRevenue calculatingBasedOnBusinessTypeForGrandTotal(
			FinancialYearRevenue financialYearRevenueNA, FinancialYearRevenue financialYearRevenueEU,
			FinancialYearRevenue financialYearRevenueAPAC, LocalDate fyStartDate, LocalDate fyEndDate) {
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();

		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();
		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		this.addQuarterFieldsForGrandTotal(listOfMonthsBetweenFinancialYear, fyStartDate, false);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));
		financialYearRevenue.setDataMap(fyRevenue);

		Map<String, BigInteger> mapNA = financialYearRevenueNA.getDataMap();
		Map<String, BigInteger> grandTotal = financialYearRevenue.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapNA.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapNA.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapEU = financialYearRevenueEU.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapEU.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapEU.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapAPAC = financialYearRevenueAPAC.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapAPAC.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapAPAC.get(entry.getKey())));
			}
		}
		financialYearRevenue.setDataMap(grandTotal);
		return financialYearRevenue;
	}
}
