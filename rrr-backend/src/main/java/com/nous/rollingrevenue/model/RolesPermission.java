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
@Table(name = "roles_permission")
@EntityListeners(AuditingEntityListener.class)
public class RolesPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roles__permission_id")
	private Long rolesPermissionId;

	@Column(name = "is_create_role_required")
	private Boolean isCreateRoleRequired;

	@Column(name = "is_copy_role_required")
	private Boolean isCopyRoleRequired;

	@Column(name = "is_edit_role_required")
	private Boolean isEditRoleRequired;

	@Column(name = "is_delete_or_deactivate_required")
	private Boolean isDeleteOrDeactivateRequired;

	public RolesPermission() {

	}

	public RolesPermission(Long rolesPermissionId, Boolean isCreateRoleRequired, Boolean isCopyRoleRequired,
			Boolean isEditRoleRequired, Boolean isDeleteOrDeactivateRequired) {
		super();
		this.rolesPermissionId = rolesPermissionId;
		this.isCreateRoleRequired = isCreateRoleRequired;
		this.isCopyRoleRequired = isCopyRoleRequired;
		this.isEditRoleRequired = isEditRoleRequired;
		this.isDeleteOrDeactivateRequired = isDeleteOrDeactivateRequired;
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

}
