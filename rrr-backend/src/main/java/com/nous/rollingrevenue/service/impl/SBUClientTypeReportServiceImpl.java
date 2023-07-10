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
import com.nous.rollingrevenue.service.SBUClientTypeReportService;
import com.nous.rollingrevenue.vo.BusinessTypeOutDTO;
import com.nous.rollingrevenue.vo.BusinessTypeResponse;
import com.nous.rollingrevenue.vo.FinancialYearRevenue;
import com.nous.rollingrevenue.vo.FinancialYearTMRevenue;
import com.nous.rollingrevenue.vo.SBUClientTypeReportInDTO;
import com.nous.rollingrevenue.vo.SBUClientTypeReportRequest;

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
	public BusinessTypeResponse getSBUClientTypeReportDetails(SBUClientTypeReportRequest sbuClientTypeReportRequest,
			boolean isDisplayAdditionalQuarter) {
		SBUClientTypeReportInDTO inDTO = sbuClientTypeReportRequest.getData();
		FinancialYear financialYear = financialYearRepository.findByFinancialYearName(inDTO.getFinancialYearName())
				.orElseThrow(() -> new RecordNotFoundException(
						ErrorConstants.RECORD_NOT_EXIST + "financialYearName not exist"));

		LocalDate financialYearStartingFrom = financialYear.getStartingFrom();
		LocalDate financialYearEndingOn = financialYear.getEndingOn();
		LocalDate fyStartDate = LocalDate.of(financialYearStartingFrom.getYear(), 4, 1);
		LocalDate fyEndDate = LocalDate.of(financialYearEndingOn.getYear(), 3, 31);

		BusinessTypeResponse BusinessTypeResponse = new BusinessTypeResponse();
		List<BusinessTypeOutDTO> outDTOList = new ArrayList<>();
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

		List<String> listOfMonthsBetweenFinancialYear = this.getListOfMonthsBetweenDates(fyStartDate, fyEndDate);
		List<String> quarterlyDetails = setQuarterlyDetails(fyStartDate);

		if ("Monthly".equalsIgnoreCase(sbuClientTypeReportRequest.getViewType())) {
			BusinessTypeResponse.setLabels(listOfMonthsBetweenFinancialYear);
			outDTOList = setBusinessTypeDetails(listOfMonthsBetweenFinancialYear, financialYearRevenueAMU,
					financialYearRevenueAPAC1, financialYearRevenueEURASIAUSRTM1, financialYearRevenueUSBFSIE,
					financialYearRevenueUSFITCHRTM3, financialYearRevenueUSHLCEMVRTM2, financialYearRevenueTESTREE1,
					financialYearRevenueTESTREE2, financialYearRevenueVSERVE);

			BusinessTypeResponse.setOutDTOList(outDTOList);
		} else {
			BusinessTypeResponse.setLabels(quarterlyDetails);
			outDTOList = setBusinessTypeDetails(quarterlyDetails, financialYearRevenueAMU, financialYearRevenueAPAC1,
					financialYearRevenueEURASIAUSRTM1, financialYearRevenueUSBFSIE, financialYearRevenueUSFITCHRTM3,
					financialYearRevenueUSHLCEMVRTM2, financialYearRevenueTESTREE1, financialYearRevenueTESTREE2,
					financialYearRevenueVSERVE);

			BusinessTypeResponse.setOutDTOList(outDTOList);
		}
		BusinessTypeResponse.setFinancialYearName(financialYear.getFinancialYearName());
		return BusinessTypeResponse;
	}

	private List<BusinessTypeOutDTO> setBusinessTypeDetails(List<String> list,
			FinancialYearRevenue financialYearRevenueAMU, FinancialYearRevenue financialYearRevenueAPAC1,
			FinancialYearRevenue financialYearRevenueEURASIAUSRTM1, FinancialYearRevenue financialYearRevenueUSBFSIE,
			FinancialYearRevenue financialYearRevenueUSFITCHRTM3, FinancialYearRevenue financialYearRevenueUSHLCEMVRTM2,
			FinancialYearRevenue financialYearRevenueTESTREE1, FinancialYearRevenue financialYearRevenueTESTREE2,
			FinancialYearRevenue financialYearRevenueVSERVE) {

		List<BusinessTypeOutDTO> outDTOList = new ArrayList<>();
		List<BigInteger> amu = getRevenueDetails(list, financialYearRevenueAMU.getDataMap());
		BusinessTypeOutDTO outDTOAMU = new BusinessTypeOutDTO();
		outDTOAMU.setLabel("AMU");
		outDTOAMU.setStack("bar1");
		outDTOAMU.setData(amu);
		outDTOList.add(outDTOAMU);

		List<BigInteger> apac1 = getRevenueDetails(list, financialYearRevenueAPAC1.getDataMap());
		BusinessTypeOutDTO outDTOAPAC1 = new BusinessTypeOutDTO();
		outDTOAPAC1.setLabel("APAC1");
		outDTOAPAC1.setStack("bar1");
		outDTOAPAC1.setData(apac1);
		outDTOList.add(outDTOAPAC1);

		List<BigInteger> eurasiausrtm1 = getRevenueDetails(list, financialYearRevenueEURASIAUSRTM1.getDataMap());
		BusinessTypeOutDTO outEURASIAUSRTM1 = new BusinessTypeOutDTO();
		outEURASIAUSRTM1.setLabel("EURASIAUSRTM1");
		outEURASIAUSRTM1.setStack("bar1");
		outEURASIAUSRTM1.setData(eurasiausrtm1);
		outDTOList.add(outEURASIAUSRTM1);

		List<BigInteger> usbfsie = getRevenueDetails(list, financialYearRevenueUSBFSIE.getDataMap());
		BusinessTypeOutDTO outUSBFSIE = new BusinessTypeOutDTO();
		outUSBFSIE.setLabel("USBFSIE ");
		outUSBFSIE.setStack("bar1");
		outUSBFSIE.setData(usbfsie);
		outDTOList.add(outUSBFSIE);

		List<BigInteger> usfitchrtm3 = getRevenueDetails(list, financialYearRevenueUSFITCHRTM3.getDataMap());
		BusinessTypeOutDTO outUSFITCHRTM3 = new BusinessTypeOutDTO();
		outUSFITCHRTM3.setLabel("USFITCHRTM3 ");
		outUSFITCHRTM3.setStack("bar1");
		outUSFITCHRTM3.setData(usfitchrtm3);
		outDTOList.add(outUSFITCHRTM3);

		List<BigInteger> ushlcemvrtm2 = getRevenueDetails(list, financialYearRevenueUSHLCEMVRTM2.getDataMap());
		BusinessTypeOutDTO outushlcemvrtm2 = new BusinessTypeOutDTO();
		outushlcemvrtm2.setLabel("USHLCEMVRTM2 ");
		outushlcemvrtm2.setStack("bar1");
		outushlcemvrtm2.setData(ushlcemvrtm2);
		outDTOList.add(outushlcemvrtm2);

		List<BigInteger> testree1 = getRevenueDetails(list, financialYearRevenueTESTREE1.getDataMap());
		BusinessTypeOutDTO outTESTREE1 = new BusinessTypeOutDTO();
		outTESTREE1.setLabel("TESTREE1  ");
		outTESTREE1.setStack("bar1");
		outTESTREE1.setData(testree1);
		outDTOList.add(outTESTREE1);

		List<BigInteger> testree2 = getRevenueDetails(list, financialYearRevenueTESTREE2.getDataMap());
		BusinessTypeOutDTO outTESTREE2 = new BusinessTypeOutDTO();
		outTESTREE2.setLabel("TESTREE2 ");
		outTESTREE2.setStack("bar1");
		outTESTREE2.setData(testree2);
		outDTOList.add(outTESTREE2);

		List<BigInteger> vserve = getRevenueDetails(list, financialYearRevenueVSERVE.getDataMap());
		BusinessTypeOutDTO outVSERVE = new BusinessTypeOutDTO();
		outVSERVE.setLabel("VSERVE");
		outVSERVE.setStack("bar1");
		outVSERVE.setData(vserve);
		outDTOList.add(outVSERVE);

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

}
