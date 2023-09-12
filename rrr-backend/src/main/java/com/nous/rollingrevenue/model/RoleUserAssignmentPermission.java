package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RoleUserAssignmentPermission")
@EntityListeners(AuditingEntityListener.class)
public class RoleUserAssignmentPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_user_assignment_permission_id")
	private Long roleUserAssignmentPermissionId;

	@Column(name = "is_assign_or_modify_users_to_roles")
	private boolean isAssignOrModifyUsersToRolesRequired;

	@Column(name = "is_activate_or_deactivate_notification")
	private boolean isActivateOrDeactivateNotificationRequired;

	public RoleUserAssignmentPermission() {

	}

	public RoleUserAssignmentPermission(Long roleUserAssignmentPermissionId,
			boolean isAssignOrModifyUsersToRolesRequired, boolean isActivateOrDeactivateNotificationRequired) {
		super();
		this.roleUserAssignmentPermissionId = roleUserAssignmentPermissionId;
		this.isAssignOrModifyUsersToRolesRequired = isAssignOrModifyUsersToRolesRequired;
		this.isActivateOrDeactivateNotificationRequired = isActivateOrDeactivateNotificationRequired;
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

}
