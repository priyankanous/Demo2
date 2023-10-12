package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RolesPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rolesPermissionId;

	private boolean rolesPermissionAll;

	private boolean isCreateRoleRequired;

	private boolean isEditRoleRequired;

	private boolean isDeleteRequired;

	private boolean isActive;

	public RolesPermissionVO() {

	}

	public Long getRolesPermissionId() {
		return rolesPermissionId;
	}

	public void setRolesPermissionId(Long rolesPermissionId) {
		this.rolesPermissionId = rolesPermissionId;
	}

	public boolean isRolesPermissionAll() {
		return rolesPermissionAll;
	}

	public void setRolesPermissionAll(boolean rolesPermissionAll) {
		this.rolesPermissionAll = rolesPermissionAll;
	}

	public boolean isCreateRoleRequired() {
		return isCreateRoleRequired;
	}

	public void setCreateRoleRequired(boolean isCreateRoleRequired) {
		this.isCreateRoleRequired = isCreateRoleRequired;
	}

	public boolean isEditRoleRequired() {
		return isEditRoleRequired;
	}

	public void setEditRoleRequired(boolean isEditRoleRequired) {
		this.isEditRoleRequired = isEditRoleRequired;
	}

	public boolean isDeleteRequired() {
		return isDeleteRequired;
	}

	public void setDeleteRequired(boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}