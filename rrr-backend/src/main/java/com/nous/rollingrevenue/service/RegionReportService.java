package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.RegionReportRequest;
import com.nous.rollingrevenue.vo.RegionResponse;

public interface RegionReportService {

	RegionResponse getRegionReportDetails(RegionReportRequest regionReportRequest, boolean isDisplayAdditionalQuarter);
}
