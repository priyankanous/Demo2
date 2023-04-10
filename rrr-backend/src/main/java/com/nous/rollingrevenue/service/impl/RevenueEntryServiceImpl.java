package com.nous.rollingrevenue.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.model.Currency;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.model.ResourcesEntry;
import com.nous.rollingrevenue.model.RollingRevenueCommonEntry;
import com.nous.rollingrevenue.model.TandMRevenueEntry;
import com.nous.rollingrevenue.repository.CurrencyRepository;
import com.nous.rollingrevenue.repository.HolidayCalendarRepository;
import com.nous.rollingrevenue.repository.ResourceEntryRepository;
import com.nous.rollingrevenue.repository.RollingRevenueCommonRepository;
import com.nous.rollingrevenue.repository.TandMRevenueRepository;
import com.nous.rollingrevenue.service.RevenueEntryService;
import com.nous.rollingrevenue.vo.ResourcesDetailsVO;
import com.nous.rollingrevenue.vo.RollingRevenueVO;

@Service
public class RevenueEntryServiceImpl implements RevenueEntryService {

	@Autowired
	private RollingRevenueCommonRepository rollingRevenueCommonRepository;

	@Autowired
	private ResourceEntryRepository resourceEntryRepository;

	@Autowired
	private TandMRevenueRepository tmRevenueRepository;

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private HolidayCalendarRepository holidayCalendarRepository;

	@Override
	public String saveRollingRevenue(RollingRevenueVO rollingRevenueVO) {

		if (rollingRevenueVO.getPricingType() != null
				&& Constants.PRICING_TYPE_TM.equalsIgnoreCase(rollingRevenueVO.getPricingType())) {

			RollingRevenueCommonEntry rollingRevenueCommonEntry = new RollingRevenueCommonEntry();
			rollingRevenueCommonEntry.setBusinessUnit(rollingRevenueVO.getBusinessUnit());
			rollingRevenueCommonEntry.setStrategicBusinessUnit(rollingRevenueVO.getStrategicBusinessUnit());
			rollingRevenueCommonEntry.setStrategicBusinessUnitHead(rollingRevenueVO.getStrategicBusinessUnitHead());
			rollingRevenueCommonEntry.setAccount(rollingRevenueVO.getAccount());
			rollingRevenueCommonEntry.setOpportunityName(rollingRevenueVO.getOpportunityName());
			rollingRevenueCommonEntry.setBusinessType(rollingRevenueVO.getBusinessType());
			rollingRevenueCommonEntry.setProjectCode(rollingRevenueVO.getProjectCode());
			rollingRevenueCommonEntry.setProjectStartDate(rollingRevenueVO.getProjectStartDate());
			rollingRevenueCommonEntry.setProjectEndDate(rollingRevenueVO.getProjectEndDate());
			rollingRevenueCommonEntry.setProbability(rollingRevenueVO.getProbability());
			rollingRevenueCommonEntry.setBdm(rollingRevenueVO.getBdm());
			rollingRevenueCommonEntry.setRegion(rollingRevenueVO.getRegion());
			rollingRevenueCommonEntry.setCocPractice(rollingRevenueVO.getCocPractice());
			rollingRevenueCommonEntry.setLocation(rollingRevenueVO.getLocation());

			// TODO: need to check which currency calculation required to save the data.
			rollingRevenueCommonEntry.setCurrency(rollingRevenueVO.getCurrency());

			rollingRevenueCommonEntry.setWorkOrder(rollingRevenueVO.getWorkOrder());
			rollingRevenueCommonEntry.setWorkOrderEndDate(rollingRevenueVO.getWorkOrderEndDate());
			rollingRevenueCommonEntry.setWorkOrderStatus(rollingRevenueVO.getWorkOrderStatus());
			rollingRevenueCommonEntry.setNoOfResources(rollingRevenueVO.getNoOfResources());
			rollingRevenueCommonEntry.setRemarks(rollingRevenueVO.getRemarks());

			TandMRevenueEntry tmMRevenueEntry = new TandMRevenueEntry();
			tmMRevenueEntry.setLeaveLossFactor(rollingRevenueVO.getLeaveLossFactor());

			// TODO: need to check which billing rate type required to save the data.
			tmMRevenueEntry.setBillingRateType(rollingRevenueVO.getBillingRateType());

			tmMRevenueEntry.setBillingRate(rollingRevenueVO.getBillingRate());

			ResourcesEntry resourcesEntry = new ResourcesEntry();
			Set<ResourcesEntry> set = new HashSet<>();
			if (!rollingRevenueVO.getResourcesList().isEmpty()) {
				for (ResourcesDetailsVO resourcesDetailsVO : rollingRevenueVO.getResourcesList()) {
					resourcesEntry.setEmployeeId(resourcesDetailsVO.getEmployeeId());
					resourcesEntry.setResourceName(resourcesDetailsVO.getResourceName());
					resourcesEntry.setResourceStartDate(resourcesDetailsVO.getResourceStartDate());
					resourcesEntry.setResourceEndDate(resourcesDetailsVO.getResourceEndDate());
					resourcesEntry.setLeaveLossFactor(resourcesDetailsVO.getLeaveLossFactor());
					resourcesEntry.setBillingRate(resourcesDetailsVO.getBillingRate());
					ResourcesEntry entry = resourceEntryRepository.save(resourcesEntry);
					set.add(entry);
				}
			}
			RollingRevenueCommonEntry commonEntry = rollingRevenueCommonRepository.save(rollingRevenueCommonEntry);
			tmMRevenueEntry.setCommonEntry(commonEntry);
			tmMRevenueEntry.setResourcesEntries(set);
			tmRevenueRepository.save(tmMRevenueEntry);
			return "Sucessfully record saved";
		}
		return "Please enter valid Pricing Type";
	}

	private void rollingRevenueCalculations(RollingRevenueVO rollingRevenueVO) {
		String financialYear = rollingRevenueVO.getFinancialYear();
		List<Currency> currencyList = currencyRepository.findByFinancialYear(financialYear);

		BigDecimal currencyConversionRate = currencyList.stream()
				.filter(c -> rollingRevenueVO.getCurrency().equalsIgnoreCase(c.getCurrencyName()))
				.map(Currency::getConversionRate).findFirst().orElse(null);

	}

	private void getMonthlyWorkingHours(RollingRevenueVO rollingRevenueVO) {
		String financialYear = rollingRevenueVO.getFinancialYear();
		List<HolidayCalendar> holidayCalendarList = holidayCalendarRepository.findByFinancialYear(financialYear);

	}

}
