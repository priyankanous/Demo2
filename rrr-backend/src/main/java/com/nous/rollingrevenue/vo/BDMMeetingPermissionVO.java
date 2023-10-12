package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class BDMMeetingPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long bdmMeetingPermissionId;

	private boolean bdmMeetingPermissionAll;

	private CommonCalendarPermissionVO commonCalendarPermissionVO;

	public Long getBdmMeetingPermissionId() {
		return bdmMeetingPermissionId;
	}

	public void setBdmMeetingPermissionId(Long bdmMeetingPermissionId) {
		this.bdmMeetingPermissionId = bdmMeetingPermissionId;
	}

	public boolean isBdmMeetingPermissionAll() {
		return bdmMeetingPermissionAll;
	}

	public void setBdmMeetingPermissionAll(boolean bdmMeetingPermissionAll) {
		this.bdmMeetingPermissionAll = bdmMeetingPermissionAll;
	}

	public CommonCalendarPermissionVO getCommonCalendarPermissionVO() {
		return commonCalendarPermissionVO;
	}

	public void setCommonCalendarPermissionVO(CommonCalendarPermissionVO commonCalendarPermissionVO) {
		this.commonCalendarPermissionVO = commonCalendarPermissionVO;
	}

}
