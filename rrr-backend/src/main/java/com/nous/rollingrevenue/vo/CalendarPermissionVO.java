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

	public Long getCalendarPermissionId() {
		return calendarPermissionId;
	}

	public void setCalendarPermissionId(Long calendarPermissionId) {
		this.calendarPermissionId = calendarPermissionId;
	}

	public boolean isCalendarPermissionAll() {
		return calendarPermissionAll;
	}

	public void setCalendarPermissionAll(boolean calendarPermissionAll) {
		this.calendarPermissionAll = calendarPermissionAll;
	}

	public boolean isViewAllCalendarPermission() {
		return viewAllCalendarPermission;
	}

	public void setViewAllCalendarPermission(boolean viewAllCalendarPermission) {
		this.viewAllCalendarPermission = viewAllCalendarPermission;
	}

	public HolidayCalendarPermissionVO getHolidayCalendarPermissionVO() {
		return holidayCalendarPermissionVO;
	}

	public void setHolidayCalendarPermissionVO(HolidayCalendarPermissionVO holidayCalendarPermissionVO) {
		this.holidayCalendarPermissionVO = holidayCalendarPermissionVO;
	}

	public FortnightlyMeetingPermissionVO getFortnightlyMeetingPermissionVO() {
		return fortnightlyMeetingPermissionVO;
	}

	public void setFortnightlyMeetingPermissionVO(FortnightlyMeetingPermissionVO fortnightlyMeetingPermissionVO) {
		this.fortnightlyMeetingPermissionVO = fortnightlyMeetingPermissionVO;
	}

	public BDMMeetingPermissionVO getBdmMeetingPermissionVO() {
		return bdmMeetingPermissionVO;
	}

	public void setBdmMeetingPermissionVO(BDMMeetingPermissionVO bdmMeetingPermissionVO) {
		this.bdmMeetingPermissionVO = bdmMeetingPermissionVO;
	}

}
