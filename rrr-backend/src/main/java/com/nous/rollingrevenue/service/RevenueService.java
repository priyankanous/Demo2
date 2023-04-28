package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.FPRevenueEntryVO;
import com.nous.rollingrevenue.vo.TandMRevenueEntryVO;

public interface RevenueService {
	
	public void saveTandMRevenueEntry(TandMRevenueEntryVO tmRevenueEntry);
	
	public void saveFPRevenueEntry(FPRevenueEntryVO fpRevenueEntry);

}
