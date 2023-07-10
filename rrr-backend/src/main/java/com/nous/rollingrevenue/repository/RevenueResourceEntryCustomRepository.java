package com.nous.rollingrevenue.repository;

import java.util.List;

import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.vo.BusinessTypeReportRequest;
import com.nous.rollingrevenue.vo.ClientTypeReportRequest;
import com.nous.rollingrevenue.vo.RegionReportRequest;

public interface RevenueResourceEntryCustomRepository {

	List<RevenueResourceEntry> findRevenueResourceDetails(BusinessTypeReportRequest businessTypeReportRequest);
	
	List<RevenueResourceEntry> findRevenueResourceDetailsByRegion(RegionReportRequest regionReportRequest);
	
	List<RevenueResourceEntry> findRevenueResourceDetailsByClient(ClientTypeReportRequest clientTypeReportRequest);

	

}
