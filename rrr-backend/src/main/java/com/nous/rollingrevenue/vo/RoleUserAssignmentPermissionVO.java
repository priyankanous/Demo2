package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class RoleUserAssignmentPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long roleUserAssignmentPermissionId;

	@NotNull(message = "isAssignOrModifyUsersToRolesRequired cannot be null or empty")
	private boolean isAssignOrModifyUsersToRolesRequired;

	@NotNull(message = "isActivateOrDeactivateNotificationRequired cannot be null or empty")
	private boolean isActivateOrDeactivateNotificationRequired;

	private boolean isActive;

	public RoleUserAssignmentPermissionVO() {

	}

	public RoleUserAssignmentPermissionVO(Long roleUserAssignmentPermissionId,
			boolean isAssignOrModifyUsersToRolesRequired, boolean isActivateOrDeactivateNotificationRequired,
			boolean isActive) {
		super();
		this.roleUserAssignmentPermissionId = roleUserAssignmentPermissionId;
		this.isAssignOrModifyUsersToRolesRequired = isAssignOrModifyUsersToRolesRequired;
		this.isActivateOrDeactivateNotificationRequired = isActivateOrDeactivateNotificationRequired;
		this.isActive = isActive;
	}

	public Long getRoleUserAssignmentPermissionId() {
		return roleUserAssignmentPermissionId;
	}

	public void setRoleUserAssignmentPermissionId(Long roleUserAssignmentPermissionId) {
		this.roleUserAssignmentPermissionId = roleUserAssignmentPermissionId;
	}

	public boolean getIsAssignOrModifyUsersToRolesRequired() {
		return isAssignOrModifyUsersToRolesRequired;
	}

	public void setAssignOrModifyUsersToRolesRequired(boolean isAssignOrModifyUsersToRolesRequired) {
		this.isAssignOrModifyUsersToRolesRequired = isAssignOrModifyUsersToRolesRequired;
	}

	public boolean getIsActivateOrDeactivateNotificationRequired() {
		return isActivateOrDeactivateNotificationRequired;
	}

	public void setActivateOrDeactivateNotificationRequired(boolean isActivateOrDeactivateNotificationRequired) {
		this.isActivateOrDeactivateNotificationRequired = isActivateOrDeactivateNotificationRequired;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
