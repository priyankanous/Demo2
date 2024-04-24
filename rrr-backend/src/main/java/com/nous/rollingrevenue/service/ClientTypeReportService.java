package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.ClientTypeReportRequest;
import com.nous.rollingrevenue.vo.ClientTypeReportResponse;

public interface ClientTypeReportService {

	ClientTypeReportResponse getClientTypeReporDetails(ClientTypeReportRequest clientTypeReportRequest,
			boolean isDisplayAdditionalQuarter);

}
