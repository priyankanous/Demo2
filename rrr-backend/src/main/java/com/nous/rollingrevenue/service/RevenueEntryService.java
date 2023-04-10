package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.RollingRevenueVO;

import jakarta.validation.Valid;

public interface RevenueEntryService {

	/**
	 * Add Rolling Revenue Details to the database
	 * 
	 * @param rollingRevenueVO
	 * @return The newly added rolling revenue details
	 */
	String saveRollingRevenue(@Valid RollingRevenueVO rollingRevenueVO);

}
