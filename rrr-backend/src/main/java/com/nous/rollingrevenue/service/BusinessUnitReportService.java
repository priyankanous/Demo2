package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.BusinessUnitReportRequest;
import com.nous.rollingrevenue.vo.BusinessUnitResponse;

public interface BusinessUnitReportService {

	BusinessUnitResponse getBusinessUnitReportDetails(BusinessUnitReportRequest businessUnitReportRequest,
			boolean isDisplayAdditionalQuarter);
}
