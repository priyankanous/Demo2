package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.SBUClientTypeReportRequest;
import com.nous.rollingrevenue.vo.SBUClientTypeReportResponse;

public interface SBUClientTypeReportService {
	SBUClientTypeReportResponse getSBUClientTypeReportDetails(SBUClientTypeReportRequest sbuClientTypeReportRequest,
			boolean isDisplayAdditionalQuarter);

}
