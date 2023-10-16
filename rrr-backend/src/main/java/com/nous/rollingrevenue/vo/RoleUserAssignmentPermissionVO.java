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

	private boolean createOrUploadRequired;

	private boolean editOrReUploadRequired;

	private boolean viewRequired;

	private boolean deleteRequired;

	private boolean isActive;

	public RoleUserAssignmentPermissionVO() {

	}

	public RoleUserAssignmentPermissionVO(Long roleUserAssignmentPermissionId, boolean roleUserAssignmentPermissionAll,
			boolean createOrUploadRequired, boolean editOrReUploadRequired, boolean viewRequired,
			boolean deleteRequired, boolean isActive) {
		super();
		this.roleUserAssignmentPermissionId = roleUserAssignmentPermissionId;
		this.roleUserAssignmentPermissionAll = roleUserAssignmentPermissionAll;
		this.createOrUploadRequired = createOrUploadRequired;
		this.editOrReUploadRequired = editOrReUploadRequired;
		this.viewRequired = viewRequired;
		this.deleteRequired = deleteRequired;
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
		return createOrUploadRequired;
	}

	public void setCreateOrUploadRequired(boolean createOrUploadRequired) {
		this.createOrUploadRequired = createOrUploadRequired;
	}

	public boolean isEditOrReUploadRequired() {
		return editOrReUploadRequired;
	}

	public void setEditOrReUploadRequired(boolean editOrReUploadRequired) {
		this.editOrReUploadRequired = editOrReUploadRequired;
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
