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

	private Boolean isCreateOrUploadRequired;

	private Boolean isEditOrReUploadRequired;

	private Boolean isViewRequired;

	private Boolean isDeleteRequired;

	private boolean isActive;

	public RoleUserAssignmentPermissionVO() {

	}

	public RoleUserAssignmentPermissionVO(Long roleUserAssignmentPermissionId, boolean roleUserAssignmentPermissionAll,
			Boolean isCreateOrUploadRequired, Boolean isEditOrReUploadRequired, Boolean isViewRequired,
			Boolean isDeleteRequired, boolean isActive) {
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isRoleUserAssignmentPermissionAll() {
		return roleUserAssignmentPermissionAll;
	}

	public void setRoleUserAssignmentPermissionAll(boolean roleUserAssignmentPermissionAll) {
		this.roleUserAssignmentPermissionAll = roleUserAssignmentPermissionAll;
	}

	public Boolean getIsCreateOrUploadRequired() {
		return isCreateOrUploadRequired;
	}

	public void setIsCreateOrUploadRequired(Boolean isCreateOrUploadRequired) {
		this.isCreateOrUploadRequired = isCreateOrUploadRequired;
	}

	public Boolean getIsEditOrReUploadRequired() {
		return isEditOrReUploadRequired;
	}

	public void setIsEditOrReUploadRequired(Boolean isEditOrReUploadRequired) {
		this.isEditOrReUploadRequired = isEditOrReUploadRequired;
	}

	public Boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setIsViewRequired(Boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

}
