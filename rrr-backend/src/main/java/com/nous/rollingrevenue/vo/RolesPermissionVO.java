package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RolesPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rolesPermissionId;

	private Boolean rolesPermissionAll;

	private Boolean isCreateRoleRequired;

	private Boolean isEditRoleRequired;

	private Boolean isDeleteRequired;

	private boolean isActive;

	public RolesPermissionVO() {

	}

	public RolesPermissionVO(Long rolesPermissionId, Boolean rolesPermissionAll, Boolean isCreateRoleRequired,
			Boolean isEditRoleRequired, Boolean isDeleteRequired, boolean isActive) {
		super();
		this.rolesPermissionId = rolesPermissionId;
		this.rolesPermissionAll = rolesPermissionAll;
		this.isCreateRoleRequired = isCreateRoleRequired;
		this.isEditRoleRequired = isEditRoleRequired;
		this.isDeleteRequired = isDeleteRequired;
		this.isActive = isActive;
	}

	public Long getRolesPermissionId() {
		return rolesPermissionId;
	}

	public void setRolesPermissionId(Long rolesPermissionId) {
		this.rolesPermissionId = rolesPermissionId;
	}

	public Boolean getIsCreateRoleRequired() {
		return isCreateRoleRequired;
	}

	public void setIsCreateRoleRequired(Boolean isCreateRoleRequired) {
		this.isCreateRoleRequired = isCreateRoleRequired;
	}

	public Boolean getIsEditRoleRequired() {
		return isEditRoleRequired;
	}

	public void setIsEditRoleRequired(Boolean isEditRoleRequired) {
		this.isEditRoleRequired = isEditRoleRequired;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getRolesPermissionAll() {
		return rolesPermissionAll;
	}

	public void setRolesPermissionAll(Boolean rolesPermissionAll) {
		this.rolesPermissionAll = rolesPermissionAll;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

}