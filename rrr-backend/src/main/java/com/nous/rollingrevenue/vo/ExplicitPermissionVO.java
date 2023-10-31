package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ExplicitPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long explicitPermissionId;

	private boolean explicitPermissionAll;

	private boolean createRequired;

	private boolean editRequired;

	private boolean viewRequired;

	private boolean deleteRequired;

	private boolean isActive;

	public Long getExplicitPermissionId() {
		return explicitPermissionId;
	}

	public void setExplicitPermissionId(Long explicitPermissionId) {
		this.explicitPermissionId = explicitPermissionId;
	}

	public boolean isExplicitPermissionAll() {
		return explicitPermissionAll;
	}

	public void setExplicitPermissionAll(boolean explicitPermissionAll) {
		this.explicitPermissionAll = explicitPermissionAll;
	}

	public boolean isCreateRequired() {
		return createRequired;
	}

	public void setCreateRequired(boolean createRequired) {
		this.createRequired = createRequired;
	}

	public boolean isEditRequired() {
		return editRequired;
	}

	public void setEditRequired(boolean editRequired) {
		this.editRequired = editRequired;
	}

	public boolean isViewRequired() {
		return viewRequired;
	}

	public void setViewRequired(boolean viewRequired) {
		this.viewRequired = viewRequired;
	}

	public boolean isDeleteRequired() {
		return deleteRequired;
	}

	public void setDeleteRequired(boolean deleteRequired) {
		this.deleteRequired = deleteRequired;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
