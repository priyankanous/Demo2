package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class CommonCalendarPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long commonCalendarPermissionId;

	private boolean viewRequired;

	private boolean addRequired;

	private boolean editRequired;

	private boolean deleteRequired;

	public Long getCommonCalendarPermissionId() {
		return commonCalendarPermissionId;
	}

	public void setCommonCalendarPermissionId(Long commonCalendarPermissionId) {
		this.commonCalendarPermissionId = commonCalendarPermissionId;
	}

	public boolean isViewRequired() {
		return viewRequired;
	}

	public void setViewRequired(boolean viewRequired) {
		this.viewRequired = viewRequired;
	}

	public boolean isAddRequired() {
		return addRequired;
	}

	public void setAddRequired(boolean addRequired) {
		this.addRequired = addRequired;
	}

	public boolean isEditRequired() {
		return editRequired;
	}

	public void setEditRequired(boolean editRequired) {
		this.editRequired = editRequired;
	}

	public boolean isDeleteRequired() {
		return deleteRequired;
	}

	public void setDeleteRequired(boolean deleteRequired) {
		this.deleteRequired = deleteRequired;
	}

}
