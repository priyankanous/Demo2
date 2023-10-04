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
	private Boolean isRolesPermissionAll;

	@Column(name = "delete")
	private Boolean isDeleteRequired;

	@Column(name = "create_role")
	private Boolean isCreateRoleRequired;

	@Column(name = "edit_role")
	private Boolean isEditRoleRequired;

	@OneToMany(mappedBy = "rolesPermission")
	@JsonBackReference
	private List<SettingsPermission> settingsPermission = new ArrayList<>();

	public RolesPermission() {

	}

	public RolesPermission(Long rolesPermissionId, Boolean isRolesPermissionAll, Boolean isDeleteRequired,
			Boolean isCreateRoleRequired, Boolean isEditRoleRequired, List<SettingsPermission> settingsPermission) {
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

	public Boolean getIsRolesPermissionAll() {
		return isRolesPermissionAll;
	}

	public void setIsRolesPermissionAll(Boolean isRolesPermissionAll) {
		this.isRolesPermissionAll = isRolesPermissionAll;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
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

	public List<SettingsPermission> getSettingsPermission() {
		return settingsPermission;
	}

	public void setSettingsPermission(List<SettingsPermission> settingsPermission) {
		this.settingsPermission = settingsPermission;
	}

}
