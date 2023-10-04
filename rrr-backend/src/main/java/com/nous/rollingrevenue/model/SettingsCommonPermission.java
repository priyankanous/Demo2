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
@Table(name = "settings_common_permission")
@EntityListeners(AuditingEntityListener.class)
public class SettingsCommonPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "settings_common_permission_id")
	private Long settingsCommonPermissionId;

	@Column(name = "view")
	private Boolean isViewRequired;

	@Column(name = "add")
	private Boolean isAddRequired;

	@Column(name = "edit")
	private Boolean isEditRequired;

	@Column(name = "delete")
	private Boolean isDeleteRequired;

	@OneToMany(mappedBy = "settingsCommonPermission")
	@JsonBackReference
	private List<RoleUserPermission> roleUserPermission = new ArrayList<>();

	@OneToMany(mappedBy = "settingsCommonPermission")
	@JsonBackReference
	private List<ExplicitPermission> explicitPermission = new ArrayList<>();

	public SettingsCommonPermission() {

	}

	public SettingsCommonPermission(Long settingsCommonPermissionId, Boolean isViewRequired, Boolean isAddRequired,
			Boolean isEditRequired, Boolean isDeleteRequired, List<RoleUserPermission> roleUserPermission,
			List<ExplicitPermission> explicitPermission) {
		super();
		this.settingsCommonPermissionId = settingsCommonPermissionId;
		this.isViewRequired = isViewRequired;
		this.isAddRequired = isAddRequired;
		this.isEditRequired = isEditRequired;
		this.isDeleteRequired = isDeleteRequired;
		this.roleUserPermission = roleUserPermission;
		this.explicitPermission = explicitPermission;
	}

	public Long getSettingsCommonPermissionId() {
		return settingsCommonPermissionId;
	}

	public void setSettingsCommonPermissionId(Long settingsCommonPermissionId) {
		this.settingsCommonPermissionId = settingsCommonPermissionId;
	}

	public Boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setIsViewRequired(Boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public Boolean getIsAddRequired() {
		return isAddRequired;
	}

	public void setIsAddRequired(Boolean isAddRequired) {
		this.isAddRequired = isAddRequired;
	}

	public Boolean getIsEditRequired() {
		return isEditRequired;
	}

	public void setIsEditRequired(Boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	public List<RoleUserPermission> getRoleUserPermission() {
		return roleUserPermission;
	}

	public void setRoleUserPermission(List<RoleUserPermission> roleUserPermission) {
		this.roleUserPermission = roleUserPermission;
	}

	public List<ExplicitPermission> getExplicitPermission() {
		return explicitPermission;
	}

	public void setExplicitPermission(List<ExplicitPermission> explicitPermission) {
		this.explicitPermission = explicitPermission;
	}

}
