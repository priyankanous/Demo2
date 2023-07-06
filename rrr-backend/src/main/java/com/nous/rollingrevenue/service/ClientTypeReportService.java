package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.BusinessTypeResponse;
import com.nous.rollingrevenue.vo.ClientTypeReportRequest;

public interface ClientTypeReportService {

	BusinessTypeResponse getClientTypeReporDetails(ClientTypeReportRequest clientTypeReportRequest,
			boolean isDisplayAdditionalQuarter);

}
