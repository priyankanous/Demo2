package com.nous.rollingrevenue.repository;

import java.util.List;

import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.vo.BusinessTypeReportRequest;

public interface RevenueResourceEntryCustomRepository {

	List<RevenueResourceEntry> findRevenueResourceDetails(BusinessTypeReportRequest businessTypeReportRequest);

}
