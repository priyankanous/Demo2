package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.vo.FortnightlyMeetingVO;

public interface FortnightlyMeetingService {
	
	/**
	 * Generate an FortnightlyMeetings and add to the database
	 * 
	 */
	public void generateFortnightlyMeetings(FortnightlyMeetingVO fortnightlyMeetingVO);
	
	/**
	 * Generate an FortnightlyMeetings and add to the database
	 * 
	 */
	public void updateFortnightlyMeetings(FortnightlyMeetingVO fortnightlyMeetingVO);
	
	/**
	 * Get all the FortnightlyMeetings by FinancialYear
	 * 
	 * @return List of all FortnightlyMeetings by FinancialYear in the database
	 */
	public List<FortnightlyMeetingVO> getFortnightlyMeetingsByFinancialYear(String financialYear);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public void activateOrDeactivateById(Long id);
	
	/**
	* Get the FortnightlyMeetings details by given Id
	*
	* @param meetingId The meetingId for retrieving the details
	* @return The FortnightlyMeetings details matching the FortnightlyMeetings id. Throws
	* {@link RecordNotFoundException} if no match is found
	*/

	public FortnightlyMeetingVO getFortnightlyMeetingsById(Long meetingId);
	

}
