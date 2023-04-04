package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.vo.FinancialYearVO;
import com.nous.rollingrevenue.vo.FortnightlyMeetingVO;

public interface FortnightlyMeetingService {
	
	/**
	 * Generate an FortnightlyMeetings and add to the database
	 * 
	 */
	public void generateFortnightlyMeetingsOfFinancialYear(FinancialYearVO financialYearVO);
	
	/**
	 * Get all the FortnightlyMeetings by FinancialYear
	 * 
	 * @return List of all FortnightlyMeetings by FinancialYear in the database
	 */
	public List<FortnightlyMeetingVO> getFortnightlyMeetingsByFinancialYear(String financialYear);

	/**
	 * Delete all the FortnightlyMeetings by FinancialYear
	 * 
	 */
	void deleteFortnightlyMeetingByFinancialYear(String financialYear);
	

}
