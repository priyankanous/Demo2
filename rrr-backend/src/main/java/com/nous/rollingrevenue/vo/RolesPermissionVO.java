package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public class RolesPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rolesPermissionId;

	@NotNull(message = "isCreateRoleRequired cannot be null or empty")
	private Boolean isCreateRoleRequired;

	@NotNull(message = "isCopyRoleRequired cannot be null or empty")
	private Boolean isCopyRoleRequired;

	@NotNull(message = "isEditRoleRequired cannot be null or empty")
	private Boolean isEditRoleRequired;

	@NotNull(message = "isDeleteOrDeactivateRequired cannot be null or empty")
	private Boolean isDeleteOrDeactivateRequired;

	private boolean isActive;

	public RolesPermissionVO() {

	}

	public RolesPermissionVO(Long rolesPermissionId, Boolean isCreateRoleRequired, Boolean isCopyRoleRequired,
			Boolean isEditRoleRequired, Boolean isDeleteOrDeactivateRequired, boolean isActive) {
		super();
		this.rolesPermissionId = rolesPermissionId;
		this.isCreateRoleRequired = isCreateRoleRequired;
		this.isCopyRoleRequired = isCopyRoleRequired;
		this.isEditRoleRequired = isEditRoleRequired;
		this.isDeleteOrDeactivateRequired = isDeleteOrDeactivateRequired;
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

	public Boolean getIsCopyRoleRequired() {
		return isCopyRoleRequired;
	}

	public void setIsCopyRoleRequired(Boolean isCopyRoleRequired) {
		this.isCopyRoleRequired = isCopyRoleRequired;
	}

	public Boolean getIsEditRoleRequired() {
		return isEditRoleRequired;
	}

	public void setIsEditRoleRequired(Boolean isEditRoleRequired) {
		this.isEditRoleRequired = isEditRoleRequired;
	}

	public Boolean getIsDeleteOrDeactivateRequired() {
		return isDeleteOrDeactivateRequired;
	}

	public void setIsDeleteOrDeactivateRequired(Boolean isDeleteOrDeactivateRequired) {
		this.isDeleteOrDeactivateRequired = isDeleteOrDeactivateRequired;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}