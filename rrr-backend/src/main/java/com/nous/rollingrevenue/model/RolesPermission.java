package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles_permission")
@EntityListeners(AuditingEntityListener.class)
public class RolesPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roles__permission_id")
	private Long rolesPermissionId;

	@Column(name = "rolesPermissionAll")
	private boolean isRolesPermissionAll;

	@Column(name = "delete")
	private boolean isDeleteRequired;

	@Column(name = "create_role")
	private boolean isCreateRoleRequired;

	@Column(name = "edit_role")
	private boolean isEditRoleRequired;

	@OneToMany(mappedBy = "rolesPermission")
	@JsonBackReference
	private List<SettingsPermission> settingsPermission = new ArrayList<>();

	public RolesPermission() {

	}

	public RolesPermission(Long rolesPermissionId, boolean isRolesPermissionAll, boolean isDeleteRequired,
			boolean isCreateRoleRequired, boolean isEditRoleRequired, List<SettingsPermission> settingsPermission) {
		super();
		this.rolesPermissionId = rolesPermissionId;
		this.isRolesPermissionAll = isRolesPermissionAll;
		this.isDeleteRequired = isDeleteRequired;
		this.isCreateRoleRequired = isCreateRoleRequired;
		this.isEditRoleRequired = isEditRoleRequired;
		this.settingsPermission = settingsPermission;
	}

	public Long getRolesPermissionId() {
		return rolesPermissionId;
	}

	public void setRolesPermissionId(Long rolesPermissionId) {
		this.rolesPermissionId = rolesPermissionId;
	}

	public boolean isRolesPermissionAll() {
		return isRolesPermissionAll;
	}

	public void setRolesPermissionAll(boolean isRolesPermissionAll) {
		this.isRolesPermissionAll = isRolesPermissionAll;
	}

	public boolean isDeleteRequired() {
		return isDeleteRequired;
	}

	public void setDeleteRequired(boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
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

	public List<SettingsPermission> getSettingsPermission() {
		return settingsPermission;
	}

	public void setSettingsPermission(List<SettingsPermission> settingsPermission) {
		this.settingsPermission = settingsPermission;
	}

}
