package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class NotificationConfigPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long notificationConfigPermissionId;

	private boolean notificationConfigPermissionAll;

	private CommonAdministrationPermissionVO commonAdministrationPermissionVO;

	public Long getNotificationConfigPermissionId() {
		return notificationConfigPermissionId;
	}

	public void setNotificationConfigPermissionId(Long notificationConfigPermissionId) {
		this.notificationConfigPermissionId = notificationConfigPermissionId;
	}

	public boolean isNotificationConfigPermissionAll() {
		return notificationConfigPermissionAll;
	}

	public void setNotificationConfigPermissionAll(boolean notificationConfigPermissionAll) {
		this.notificationConfigPermissionAll = notificationConfigPermissionAll;
	}

	public CommonAdministrationPermissionVO getCommonAdministrationPermissionVO() {
		return commonAdministrationPermissionVO;
	}

	public void setCommonAdministrationPermissionVO(CommonAdministrationPermissionVO commonAdministrationPermissionVO) {
		this.commonAdministrationPermissionVO = commonAdministrationPermissionVO;
	}

}
