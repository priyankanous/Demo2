package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class HolidayCalendarPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long holidayCalendarPermissionId;

	private boolean holidayCalendarPermissionAll;

	private CommonCalendarPermissionVO commonCalendarPermissionVO;

	public Long getHolidayCalendarPermissionId() {
		return holidayCalendarPermissionId;
	}

	public void setHolidayCalendarPermissionId(Long holidayCalendarPermissionId) {
		this.holidayCalendarPermissionId = holidayCalendarPermissionId;
	}

	public boolean isHolidayCalendarPermissionAll() {
		return holidayCalendarPermissionAll;
	}

	public void setHolidayCalendarPermissionAll(boolean holidayCalendarPermissionAll) {
		this.holidayCalendarPermissionAll = holidayCalendarPermissionAll;
	}

	public CommonCalendarPermissionVO getCommonCalendarPermissionVO() {
		return commonCalendarPermissionVO;
	}

	public void setCommonCalendarPermissionVO(CommonCalendarPermissionVO commonCalendarPermissionVO) {
		this.commonCalendarPermissionVO = commonCalendarPermissionVO;
	}

}
