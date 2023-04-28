package com.nous.rollingrevenue.service;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.ResourcesEntryVO;
import com.nous.rollingrevenue.vo.RollingRevenueAccountVO;
import com.nous.rollingrevenue.vo.RollingRevenueOpportunityVO;
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

	/**
	 * Get the Rolling Revenue Details by Account level
	 * 
	 * @param id The Rolling Revenue id for retrieving the details
	 * @return The Rolling Revenue Details matching the Rolling Revenue id on
	 *         account level. Throws {@link RecordNotFoundException} if no match is
	 *         found
	 */
	public RollingRevenueAccountVO getRevenueByAccountLevel(Long id);

	/**
	 * Get the Rolling Revenue Details by Opportunity level
	 * 
	 * @param id The Rolling Revenue id for retrieving the details
	 * @return The Rolling Revenue Details matching the Rolling Revenue id on
	 *         opportunity level. Throws {@link RecordNotFoundException} if no match
	 *         is found
	 */
	public RollingRevenueOpportunityVO getRevenueByOpportunityLevel(Long id);

	/**
	 * Get the Rolling Revenue Details by Resource level
	 * 
	 * @param id The Rolling Revenue id for retrieving the details
	 * @return The Rolling Revenue Details matching the Rolling Revenue id on
	 *         resource level. Throws {@link RecordNotFoundException} if no match is
	 *         found
	 */
	public ResourcesEntryVO getRevenueByResourceLevel(Long id);

}
