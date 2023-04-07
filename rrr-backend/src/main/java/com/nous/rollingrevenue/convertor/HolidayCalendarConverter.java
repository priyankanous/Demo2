package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.vo.HolidayCalendarVO;

@Component
public class HolidayCalendarConverter {

	/**
	 * Convert HolidayCalendarVO to HolidayCalendar
	 * 
	 * @param HolidayCalendarVO
	 * @return HolidayCalendarVO Jpa bean
	 */
	public static HolidayCalendar convertHolidayCalendarVOToHolidayCalendar(HolidayCalendarVO holidayCalendarVO) {
		HolidayCalendar holidayCalendar = new HolidayCalendar();
		if (holidayCalendarVO != null) {
			if (holidayCalendarVO.getHolidayId() != null) {
				holidayCalendar.setHolidayId(holidayCalendarVO.getHolidayId());
				holidayCalendar.setActive(holidayCalendarVO.isActive());
			}
			holidayCalendar.setHolidayName(holidayCalendarVO.getHolidayName());
			holidayCalendar.setHolidayDate(holidayCalendarVO.getHolidayDate());
			holidayCalendar.setHolidayDay(holidayCalendarVO.getHolidayDay());
			holidayCalendar.setYear(holidayCalendarVO.getYear());

		}
		return holidayCalendar;
	}

	/**
	 * Convert HolidayCalendar to HolidayCalendarVO
	 * 
	 * @param HolidayCalendar holidayCalendar
	 * @return HolidayCalendarVO
	 */
	public static HolidayCalendarVO convertHolidayCalendarToHolidayCalendarVO(HolidayCalendar holidayCalendar) {
		HolidayCalendarVO holidayCalendarVO = new HolidayCalendarVO();
		if (holidayCalendar != null) {
			holidayCalendarVO.setHolidayId(holidayCalendar.getHolidayId());
			holidayCalendarVO.setHolidayName(holidayCalendar.getHolidayName());
			holidayCalendarVO.setHolidayDate(holidayCalendar.getHolidayDate());
			holidayCalendarVO.setHolidayDay(holidayCalendar.getHolidayDay());
			holidayCalendarVO.setYear(holidayCalendar.getYear());
			holidayCalendarVO.setActive(holidayCalendar.isActive());
		}
		return holidayCalendarVO;
	}

}
