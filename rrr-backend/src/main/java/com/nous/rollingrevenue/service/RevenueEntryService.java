package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.vo.RollingRevenueVO;

import jakarta.validation.Valid;

public interface RevenueEntryService {

	String saveRollingRevenue(@Valid RollingRevenueVO rollingRevenueVO);

}
