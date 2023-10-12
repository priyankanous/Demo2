package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleUserAssignmentPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long roleUserAssignmentPermissionId;

	private boolean roleUserAssignmentPermissionAll;

	private boolean isCreateOrUploadRequired;

	private boolean isEditOrReUploadRequired;

	private boolean isViewRequired;

	private boolean isDeleteRequired;

	private boolean isActive;

	public RoleUserAssignmentPermissionVO() {

	}

	public RoleUserAssignmentPermissionVO(Long roleUserAssignmentPermissionId, boolean roleUserAssignmentPermissionAll,
			boolean isCreateOrUploadRequired, boolean isEditOrReUploadRequired, boolean isViewRequired,
			boolean isDeleteRequired, boolean isActive) {
		super();
		this.roleUserAssignmentPermissionId = roleUserAssignmentPermissionId;
		this.roleUserAssignmentPermissionAll = roleUserAssignmentPermissionAll;
		this.isCreateOrUploadRequired = isCreateOrUploadRequired;
		this.isEditOrReUploadRequired = isEditOrReUploadRequired;
		this.isViewRequired = isViewRequired;
		this.isDeleteRequired = isDeleteRequired;
		this.isActive = isActive;
	}

	public Long getRoleUserAssignmentPermissionId() {
		return roleUserAssignmentPermissionId;
	}

	public void setRoleUserAssignmentPermissionId(Long roleUserAssignmentPermissionId) {
		this.roleUserAssignmentPermissionId = roleUserAssignmentPermissionId;
	}

	public boolean isRoleUserAssignmentPermissionAll() {
		return roleUserAssignmentPermissionAll;
	}

	public void setRoleUserAssignmentPermissionAll(boolean roleUserAssignmentPermissionAll) {
		this.roleUserAssignmentPermissionAll = roleUserAssignmentPermissionAll;
	}

	public boolean isCreateOrUploadRequired() {
		return isCreateOrUploadRequired;
	}

	public void setCreateOrUploadRequired(boolean isCreateOrUploadRequired) {
		this.isCreateOrUploadRequired = isCreateOrUploadRequired;
	}

	public boolean isEditOrReUploadRequired() {
		return isEditOrReUploadRequired;
	}

	public void setEditOrReUploadRequired(boolean isEditOrReUploadRequired) {
		this.isEditOrReUploadRequired = isEditOrReUploadRequired;
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
