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
import com.nous.rollingrevenue.service.SBUClientTypeReportService;
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;
import com.nous.rollingrevenue.vo.SBUClientTypeReportInDTO;
import com.nous.rollingrevenue.vo.SBUClientTypeReportOutDTO;
import com.nous.rollingrevenue.vo.SBUClientTypeReportRequest;
import com.nous.rollingrevenue.vo.SBUClientTypeReportResponse;

@Service
public class SBUClientTypeReportServiceImpl implements SBUClientTypeReportService {
	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private RevenueServiceImpl revenueServiceImpl;

	@Autowired
	private RevenueServiceTMCalculation tmCalculation;

	@Autowired
	private RevenueResourceEntryCustomRepository revenueResourceEntryCustomRepository;

	@Override
	public SBUClientTypeReportResponse getSBUClientTypeReportDetails(
			SBUClientTypeReportRequest sbuClientTypeReportRequest, boolean isDisplayAdditionalQuarter) {
		SBUClientTypeReportInDTO inDTO = sbuClientTypeReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		SBUClientTypeReportResponse sbuclientTypeResponse = new SBUClientTypeReportResponse();
		List<SBUClientTypeReportOutDTO> outDTOList = null;
		List<RevenueResourceEntry> revenueResourceEntryList = revenueResourceEntryCustomRepository
				.findRevenueResourceDetailsForSBUClient(sbuClientTypeReportRequest);

		List<RevenueResourceEntry> amurevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> apac1revenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> eurasiausrtm1revenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> usbfsierevenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> usfitchrtm3revenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> ushlcemvrtm2revenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> testree1revenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> testree2revenueResourceEntryList = new ArrayList<>();
		List<RevenueResourceEntry> vserverevenueResourceEntryList = new ArrayList<>();

		for (RevenueResourceEntry revenueResourceEntry : revenueResourceEntryList) {
			if (Constants.SBU_CLIENT_TYPE_AMU
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				amurevenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_APAC1
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				apac1revenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_EURASIAUSRTM1
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				eurasiausrtm1revenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_USBFSIE
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				usbfsierevenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_USFITCHRTM3
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				usfitchrtm3revenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_USHLCEMVRTM2
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				ushlcemvrtm2revenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_TESTREE1
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				testree1revenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_TESTREE2
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				testree2revenueResourceEntryList.add(revenueResourceEntry);

			} else if (Constants.SBU_CLIENT_TYPE_VSERVE
					.equalsIgnoreCase(revenueResourceEntry.getStrategicBusinessUnit().getSbuDisplayName())) {
				vserverevenueResourceEntryList.add(revenueResourceEntry);

			}

		}

		FinancialYearRevenue financialYearRevenueAMU = calculatingBasedOnBusinessType(amurevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueAPAC1 = calculatingBasedOnBusinessType(apac1revenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueEURASIAUSRTM1 = calculatingBasedOnBusinessType(
				eurasiausrtm1revenueResourceEntryList, financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueUSBFSIE = calculatingBasedOnBusinessType(
				usbfsierevenueResourceEntryList, financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueUSFITCHRTM3 = calculatingBasedOnBusinessType(
				usfitchrtm3revenueResourceEntryList, financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueUSHLCEMVRTM2 = calculatingBasedOnBusinessType(
				ushlcemvrtm2revenueResourceEntryList, financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueTESTREE1 = calculatingBasedOnBusinessType(
				testree1revenueResourceEntryList, financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueTESTREE2 = calculatingBasedOnBusinessType(
				testree2revenueResourceEntryList, financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueVSERVE = calculatingBasedOnBusinessType(vserverevenueResourceEntryList,
				financialYear, isDisplayAdditionalQuarter);

		FinancialYearRevenue financialYearRevenueTotal = calculatingBasedOnSBUClientForGrandTotal(
				financialYearRevenueAMU, financialYearRevenueAPAC1, financialYearRevenueEURASIAUSRTM1,
				financialYearRevenueUSBFSIE, financialYearRevenueUSFITCHRTM3, financialYearRevenueUSHLCEMVRTM2,
				financialYearRevenueTESTREE1, financialYearRevenueTESTREE2, financialYearRevenueVSERVE, fyStartDate,
				fyEndDate);

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetailsForChart = setQuarterlyDetailsForSBUClientForChart(fyStartDate);
		List<String> quarterlyDetailsForTabular = setQuarterlyDetailsForSBUClientForTabular(fyStartDate);

		if ("Chart".equalsIgnoreCase(sbuClientTypeReportRequest.getOutPutType())) {
			if ("Monthly".equalsIgnoreCase(sbuClientTypeReportRequest.getViewType())) {
				sbuclientTypeResponse.setLabels(listOfMonthsBetweenFinancialYear);
				outDTOList = setSBUClientTypeDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueAMU,
						financialYearRevenueAPAC1, financialYearRevenueEURASIAUSRTM1, financialYearRevenueUSBFSIE,
						financialYearRevenueUSFITCHRTM3, financialYearRevenueUSHLCEMVRTM2, financialYearRevenueTESTREE1,
						financialYearRevenueTESTREE2, financialYearRevenueVSERVE, null, false);
				sbuclientTypeResponse.setOutDTOList(outDTOList);
			} else {
				sbuclientTypeResponse.setLabels(quarterlyDetailsForChart);
				outDTOList = setSBUClientTypeDetails(quarterlyDetailsForChart, financialYearRevenueAMU,
						financialYearRevenueAPAC1, financialYearRevenueEURASIAUSRTM1, financialYearRevenueUSBFSIE,
						financialYearRevenueUSFITCHRTM3, financialYearRevenueUSHLCEMVRTM2, financialYearRevenueTESTREE1,
						financialYearRevenueTESTREE2, financialYearRevenueVSERVE, null, false);
				sbuclientTypeResponse.setOutDTOList(outDTOList);
			}
		} else {
			if ("Monthly".equalsIgnoreCase(sbuClientTypeReportRequest.getViewType())) {
				sbuclientTypeResponse.setLabels(this.addQuarterFields(listOfMonthsBetweenFinancialYear, fyStartDate));
				outDTOList = setSBUClientTypeDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueAMU,
						financialYearRevenueAPAC1, financialYearRevenueEURASIAUSRTM1, financialYearRevenueUSBFSIE,
						financialYearRevenueUSFITCHRTM3, financialYearRevenueUSHLCEMVRTM2, financialYearRevenueTESTREE1,
						financialYearRevenueTESTREE2, financialYearRevenueVSERVE, financialYearRevenueTotal, true);
				sbuclientTypeResponse.setOutDTOList(outDTOList);
			} else {
				sbuclientTypeResponse.setLabels(quarterlyDetailsForTabular);
				outDTOList = setSBUClientTypeDetails(quarterlyDetailsForTabular, financialYearRevenueAMU,
						financialYearRevenueAPAC1, financialYearRevenueEURASIAUSRTM1, financialYearRevenueUSBFSIE,
						financialYearRevenueUSFITCHRTM3, financialYearRevenueUSHLCEMVRTM2, financialYearRevenueTESTREE1,
						financialYearRevenueTESTREE2, financialYearRevenueVSERVE, financialYearRevenueTotal, true);

				sbuclientTypeResponse.setOutDTOList(outDTOList);
			}
		}
		sbuclientTypeResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return sbuclientTypeResponse;
	}

	private FinancialYearRevenue calculatingBasedOnSBUClientForGrandTotal(FinancialYearRevenue financialYearRevenueAMU,
			FinancialYearRevenue financialYearRevenueAPAC1, FinancialYearRevenue financialYearRevenueEURASIAUSRTM1,
			FinancialYearRevenue financialYearRevenueUSBFSIE, FinancialYearRevenue financialYearRevenueUSFITCHRTM3,
			FinancialYearRevenue financialYearRevenueUSHLCEMVRTM2, FinancialYearRevenue financialYearRevenueTESTREE1,
			FinancialYearRevenue financialYearRevenueTESTREE2, FinancialYearRevenue financialYearRevenueVSERVE,
			LocalDate fyStartDate, LocalDate fyEndDate) {
		FinancialYearRevenue financialYearRevenue = new FinancialYearRevenue();

		Map<String, BigInteger> fyRevenue = new LinkedHashMap<>();
		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		this.addQuarterFieldsForGrandTotal(listOfMonthsBetweenFinancialYear, fyStartDate, false);
		listOfMonthsBetweenFinancialYear.stream().forEach(monthYear -> fyRevenue.put(monthYear, BigInteger.ZERO));
		financialYearRevenue.setDataMap(fyRevenue);

		Map<String, BigInteger> mapAMU = financialYearRevenueAMU.getDataMap();
		Map<String, BigInteger> grandTotal = financialYearRevenue.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapAMU.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapAMU.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapAPAC1 = financialYearRevenueAPAC1.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapAPAC1.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapAPAC1.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapEURASIAUSRTM1 = financialYearRevenueEURASIAUSRTM1.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapEURASIAUSRTM1.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(),
						grandTotal.get(entry.getKey()).add(mapEURASIAUSRTM1.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapUSBFSIE = financialYearRevenueUSBFSIE.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapUSBFSIE.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapUSBFSIE.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapUSFITCHRTM3 = financialYearRevenueUSFITCHRTM3.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapUSFITCHRTM3.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapUSFITCHRTM3.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapUSHLCEMVRTM2 = financialYearRevenueUSHLCEMVRTM2.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapUSHLCEMVRTM2.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapUSHLCEMVRTM2.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapTESTREE1 = financialYearRevenueTESTREE1.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapTESTREE1.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapTESTREE1.get(entry.getKey())));
			}
		}

		Map<String, BigInteger> mapTESTREE2 = financialYearRevenueTESTREE2.getDataMap();
		for (Map.Entry<String, BigInteger> entry : mapTESTREE2.entrySet()) {
			if (grandTotal.containsKey(entry.getKey())) {
				grandTotal.put(entry.getKey(), grandTotal.get(entry.getKey()).add(mapTESTREE2.get(entry.getKey())));
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

	private List<SBUClientTypeReportOutDTO> setSBUClientTypeDetails(List<String> list,
			FinancialYearRevenue financialYearRevenueAMU, FinancialYearRevenue financialYearRevenueAPAC1,
			FinancialYearRevenue financialYearRevenueEURASIAUSRTM1, FinancialYearRevenue financialYearRevenueUSBFSIE,
			FinancialYearRevenue financialYearRevenueUSFITCHRTM3, FinancialYearRevenue financialYearRevenueUSHLCEMVRTM2,
			FinancialYearRevenue financialYearRevenueTESTREE1, FinancialYearRevenue financialYearRevenueTESTREE2,
			FinancialYearRevenue financialYearRevenueVSERVE, FinancialYearRevenue financialYearRevenueTotal,
			boolean isTabular) {

		List<SBUClientTypeReportOutDTO> outDTOList = new ArrayList<>();
		List<BigInteger> amu = getRevenueDetails(list, financialYearRevenueAMU.getDataMap());
		SBUClientTypeReportOutDTO outDTOAMU = new SBUClientTypeReportOutDTO();
		outDTOAMU.setLabel("AMU");
		outDTOAMU.setStack("bar1");
		outDTOAMU.setData(amu);
		outDTOList.add(outDTOAMU);

		List<BigInteger> apac1 = getRevenueDetails(list, financialYearRevenueAPAC1.getDataMap());
		SBUClientTypeReportOutDTO outDTOAPAC1 = new SBUClientTypeReportOutDTO();
		outDTOAPAC1.setLabel("APAC1");
		outDTOAPAC1.setStack("bar1");
		outDTOAPAC1.setData(apac1);
		outDTOList.add(outDTOAPAC1);

		List<BigInteger> eurasiausrtm1 = getRevenueDetails(list, financialYearRevenueEURASIAUSRTM1.getDataMap());
		SBUClientTypeReportOutDTO outEURASIAUSRTM1 = new SBUClientTypeReportOutDTO();
		outEURASIAUSRTM1.setLabel("EURASIAUSRTM1");
		outEURASIAUSRTM1.setStack("bar1");
		outEURASIAUSRTM1.setData(eurasiausrtm1);
		outDTOList.add(outEURASIAUSRTM1);

		List<BigInteger> usbfsie = getRevenueDetails(list, financialYearRevenueUSBFSIE.getDataMap());
		SBUClientTypeReportOutDTO outUSBFSIE = new SBUClientTypeReportOutDTO();
		outUSBFSIE.setLabel("USBFSIE");
		outUSBFSIE.setStack("bar1");
		outUSBFSIE.setData(usbfsie);
		outDTOList.add(outUSBFSIE);

		List<BigInteger> usfitchrtm3 = getRevenueDetails(list, financialYearRevenueUSFITCHRTM3.getDataMap());
		SBUClientTypeReportOutDTO outUSFITCHRTM3 = new SBUClientTypeReportOutDTO();
		outUSFITCHRTM3.setLabel("USFITCHRTM3");
		outUSFITCHRTM3.setStack("bar1");
		outUSFITCHRTM3.setData(usfitchrtm3);
		outDTOList.add(outUSFITCHRTM3);

		List<BigInteger> ushlcemvrtm2 = getRevenueDetails(list, financialYearRevenueUSHLCEMVRTM2.getDataMap());
		SBUClientTypeReportOutDTO outushlcemvrtm2 = new SBUClientTypeReportOutDTO();
		outushlcemvrtm2.setLabel("USHLCEMVRTM2");
		outushlcemvrtm2.setStack("bar1");
		outushlcemvrtm2.setData(ushlcemvrtm2);
		outDTOList.add(outushlcemvrtm2);

		List<BigInteger> testree1 = getRevenueDetails(list, financialYearRevenueTESTREE1.getDataMap());
		SBUClientTypeReportOutDTO outTESTREE1 = new SBUClientTypeReportOutDTO();
		outTESTREE1.setLabel("Testree1");
		outTESTREE1.setStack("bar1");
		outTESTREE1.setData(testree1);
		outDTOList.add(outTESTREE1);

		List<BigInteger> testree2 = getRevenueDetails(list, financialYearRevenueTESTREE2.getDataMap());
		SBUClientTypeReportOutDTO outTESTREE2 = new SBUClientTypeReportOutDTO();
		outTESTREE2.setLabel("Testree2");
		outTESTREE2.setStack("bar1");
		outTESTREE2.setData(testree2);
		outDTOList.add(outTESTREE2);

		List<BigInteger> vserve = getRevenueDetails(list, financialYearRevenueVSERVE.getDataMap());
		SBUClientTypeReportOutDTO outVSERVE = new SBUClientTypeReportOutDTO();
		outVSERVE.setLabel("Vserve");
		outVSERVE.setStack("bar1");
		outVSERVE.setData(vserve);
		outDTOList.add(outVSERVE);

		if (isTabular) {
			List<BigInteger> grandTotal = getRevenueDetails(list, financialYearRevenueTotal.getDataMap());
			SBUClientTypeReportOutDTO outDTOTotal = new SBUClientTypeReportOutDTO();
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

	private List<String> setQuarterlyDetailsForSBUClientForTabular(LocalDate fyEndDate) {
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

	private List<String> setQuarterlyDetailsForSBUClientForChart(LocalDate fyEndDate) {
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
