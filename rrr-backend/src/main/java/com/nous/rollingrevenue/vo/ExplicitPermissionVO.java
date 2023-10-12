package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ExplicitPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long explicitPermissionId;

	private boolean explicitPermissionAll;

	private boolean isCreateRequired;

	private boolean isEditRequired;

	private boolean isViewRequired;

	private boolean isDeleteRequired;

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
		return isCreateRequired;
	}

	public void setCreateRequired(boolean isCreateRequired) {
		this.isCreateRequired = isCreateRequired;
	}

	public boolean isEditRequired() {
		return isEditRequired;
	}

	public void setEditRequired(boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public boolean isViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean isDeleteRequired() {
		return isDeleteRequired;
	}

	public void setDeleteRequired(boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}
	
	

}
