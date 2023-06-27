package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.vo.BusinessTypeReportRequest;
import com.nous.rollingrevenue.vo.BusinessTypeResponse;

public interface BusinessTypeReportService {

	BusinessTypeResponse getBusinessTypeReportDetails(BusinessTypeReportRequest businessTypeReportRequest,
			boolean isDisplayAdditionalQuarter);

	List<RevenueResourceEntry> getRevenueResourceEntryFilter(BusinessTypeReportRequest businessTypeReportRequest,
			boolean isDisplayAdditionalQuarter);

}
