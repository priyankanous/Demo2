package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.FPRevenueEntryVO;
import com.nous.rollingrevenue.vo.OpportunityEntryResponse;
import com.nous.rollingrevenue.vo.RevenueEntryResponse;
import com.nous.rollingrevenue.vo.TandMRevenueEntryVO;

public interface RevenueService {

	public void saveTandMRevenueEntry(TandMRevenueEntryVO tmRevenueEntry);

	public void saveFPRevenueEntry(FPRevenueEntryVO fpRevenueEntry);

	public RevenueEntryResponse getRevenueEntries(String financialYearName, boolean isDisplayAdditionalQuarter);

	public OpportunityEntryResponse getOpportunities(Long oppId);

}
