package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.FPRevenueEntryVO;
import com.nous.rollingrevenue.vo.OpportunityEntryResponse;
import com.nous.rollingrevenue.vo.OpportunityRevenueRequest;
import com.nous.rollingrevenue.vo.ResourceEntryRequest;
import com.nous.rollingrevenue.vo.ResourceEntryResponse;
import com.nous.rollingrevenue.vo.ResourceRevenueRequest;
import com.nous.rollingrevenue.vo.ResourceRevenueResponse;
import com.nous.rollingrevenue.vo.RevenueEntryResponse;
import com.nous.rollingrevenue.vo.RollingRevenueResponse;
import com.nous.rollingrevenue.vo.TandMRevenueEntryVO;

public interface RevenueService {

	public void saveTandMRevenueEntry(TandMRevenueEntryVO tmRevenueEntry);

	public void saveFPRevenueEntry(FPRevenueEntryVO fpRevenueEntry);

	public RevenueEntryResponse getRevenueEntries(String financialYearName, boolean isDisplayAdditionalQuarter);

	public OpportunityEntryResponse getOpportunities(OpportunityRevenueRequest opportunityRevenueRequest,
			boolean isDisplayAdditionalQuarter);

	public ResourceEntryResponse getResourcesByOpportunity(ResourceEntryRequest resourceEntryRequest,
			boolean isDisplayAdditionalQuarter);

	public ResourceRevenueResponse getResourceRevenue(ResourceRevenueRequest resourceRevenueRequest,
			boolean isDisplayAdditionalQuarter);

	public void updateFPRevenueEntry(Long revenueEntryId, FPRevenueEntryVO fpRevenueEntry);

	public void updateTandMRevenueEntry(Long revenueEntryId, TandMRevenueEntryVO tandMRevenueEntry);

	public RollingRevenueResponse getRevenueEntryDetailsById(Long revenueEntryId);

}
