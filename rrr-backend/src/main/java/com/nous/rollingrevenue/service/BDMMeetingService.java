package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.BDMMeetingVO;

public interface BDMMeetingService {
	
	/**
	 * Add an BDMMeeting to the database
	 * 
	 * @param BDMMeetingVO
	 * 
	 */
	
	public void saveBDMMeeting(BDMMeetingVO bdmMeetingVO);
	
	/**
	 * Update an BDMMeeting to the database by given Id
	 * 
	 * @param bdmMeetingId, BDMMeetingVO
	 * 
	 */

	public  void updateBDMMeeting(Long bdmMeetingId, BDMMeetingVO bdmMeetingVO);
	
	/**
	 * Delete an BDMMeeting record by given Id
	 * 
	 * @param bdmMeetingId The bdmMeetingId of the BDMMeeting to be deleted. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */

	public void deleteBDMMeetingById(Long accountId);
	
	/**
	 * Get the BDMMeeting details by given FinancialYear
	 * 
	 * @param  financialYear The financialYear for retrieving the details
	 * @return The BDMMeetingVOs details matching the financialYear.
	 */

	public List<BDMMeetingVO> getBDMMeetingByFinancialYear(String financialYear);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public void activateOrDeactivateById(Long bdmMeetingid);

}
