package com.nous.rollingrevenue.service;

import java.util.List;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.vo.HolidayCalendarVO;

public interface HolidayCalendarService {

	/**
	 * Add Calendar to the database
	 * 
	 * @param HolidayCalendarVO
	 * @return The newly added HolidayCalendar details
	 */
	HolidayCalendarVO addCalendar(HolidayCalendarVO holidayCalendarVO);

	/**
	 * Update an holidayCalendar to the database
	 * 
	 * @param id, holidayCalendarVO
	 * @return The newly added holidayCalendar details
	 */
	public HolidayCalendarVO updateHolidayCalendar(Long id, HolidayCalendarVO holidayCalendarVO);

	/**
	 * Get the HolidayCalendar details
	 * 
	 * @param id The HolidayCalendar id for retrieving the details
	 * @return The HolidayCalendar details matching the HolidayCalendar id. Throws
	 *         {@link RecordNotFoundException} if no match is found
	 */
	public HolidayCalendarVO getHolidayCalendar(Long holidayId);

	/**
	 * Delete an HolidayCalendar record
	 * 
	 * @param id The id of the HolidayCalendar to be deleted
	 */
	public void deleteHolidayCalendar(Long holidayId);

	/**
	 * Get all the HolidayCalendar
	 * 
	 * @return List of all HolidayCalendar in the database
	 */
	public List<HolidayCalendarVO> getCalendars();

	List<HolidayCalendarVO> getPagination(int pagenumber, int pagesize, String sortBy);
	
	/**
	 * Activate or Deactivate the record
	 * @param id
	 */
	public HolidayCalendarVO activateOrDeactivateById(Long id);

}
