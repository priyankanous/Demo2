package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class CommonCalendarPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long commonCalendarPermissionId;

	private boolean isViewRequired;

	private boolean isAddRequired;

	private boolean isEditRequired;

	private boolean isDeleteRequired;

	public Long getCommonCalendarPermissionId() {
		return commonCalendarPermissionId;
	}

	public void setCommonCalendarPermissionId(Long commonCalendarPermissionId) {
		this.commonCalendarPermissionId = commonCalendarPermissionId;
	}

	public boolean isViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean isAddRequired() {
		return isAddRequired;
	}

	public void setAddRequired(boolean isAddRequired) {
		this.isAddRequired = isAddRequired;
	}

	public boolean isEditRequired() {
		return isEditRequired;
	}

	public void setEditRequired(boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public boolean isDeleteRequired() {
		return isDeleteRequired;
	}

	public void setDeleteRequired(boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

}
