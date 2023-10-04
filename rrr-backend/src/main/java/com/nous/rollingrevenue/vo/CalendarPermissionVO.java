package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class CalendarPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long calendarPermissionId;

	private boolean calendarPermissionAll;

	private boolean viewAllCalendarPermission;

	private HolidayCalendarPermissionVO holidayCalendarPermissionVO;

	private FortnightlyMeetingPermissionVO fortnightlyMeetingPermissionVO;

	private BDMMeetingPermissionVO bdmMeetingPermissionVO;

}
