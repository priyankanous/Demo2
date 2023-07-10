package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.BusinessTypeResponse;
import com.nous.rollingrevenue.vo.SBUClientTypeReportRequest;

public interface SBUClientTypeReportService {
	BusinessTypeResponse getSBUClientTypeReportDetails(SBUClientTypeReportRequest SBUClientTypeReportRequest,
			boolean isDisplayAdditionalQuarter);

}
