package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class FortnightlyMeetingPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long fortnightlyMeetingPermissionId;

	private boolean fortnightlyMeetingPermissionAll;

	private CommonCalendarPermissionVO commonCalendarPermissionVO;

	public Long getFortnightlyMeetingPermissionId() {
		return fortnightlyMeetingPermissionId;
	}

	public void setFortnightlyMeetingPermissionId(Long fortnightlyMeetingPermissionId) {
		this.fortnightlyMeetingPermissionId = fortnightlyMeetingPermissionId;
	}

	public boolean isFortnightlyMeetingPermissionAll() {
		return fortnightlyMeetingPermissionAll;
	}

	public void setFortnightlyMeetingPermissionAll(boolean fortnightlyMeetingPermissionAll) {
		this.fortnightlyMeetingPermissionAll = fortnightlyMeetingPermissionAll;
	}

	public CommonCalendarPermissionVO getCommonCalendarPermissionVO() {
		return commonCalendarPermissionVO;
	}

	public void setCommonCalendarPermissionVO(CommonCalendarPermissionVO commonCalendarPermissionVO) {
		this.commonCalendarPermissionVO = commonCalendarPermissionVO;
	}

}
