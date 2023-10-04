package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "settings_permission")
@EntityListeners(AuditingEntityListener.class)
public class SettingsPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "settings_permission_id")
	private Long settingsPermissionId;

	@Column(name = "settings_permission_all")
	private Boolean isSettingsPermissionAll;

	@Column(name = "view_all_entries")
	private Boolean isViewAllEntriesRequired;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "roles__permission_id", referencedColumnName = "roles__permission_id")
	private RolesPermission rolesPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "role_user_permission_id", referencedColumnName = "role_user_permission_id")
	private RoleUserPermission roleUserPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "explicit_permission_id", referencedColumnName = "explicit_permission_id")
	private ExplicitPermission explicitPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "annual_target_entry_permission_id", referencedColumnName = "annual_target_entry_permission_id")
	private AnnualTargetEntryPermission annualTargetEntryPermission;

	@OneToMany(mappedBy = "settingsPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

	public SettingsPermission() {

	}

	public SettingsPermission(Long settingsPermissionId, Boolean isSettingsPermissionAll,
			Boolean isViewAllEntriesRequired, RolesPermission rolesPermission, RoleUserPermission roleUserPermission,
			ExplicitPermission explicitPermission, AnnualTargetEntryPermission annualTargetEntryPermission,
			List<Roles> roles) {
		super();
		this.settingsPermissionId = settingsPermissionId;
		this.isSettingsPermissionAll = isSettingsPermissionAll;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.rolesPermission = rolesPermission;
		this.roleUserPermission = roleUserPermission;
		this.explicitPermission = explicitPermission;
		this.annualTargetEntryPermission = annualTargetEntryPermission;
		this.roles = roles;
	}

	public Long getSettingsPermissionId() {
		return settingsPermissionId;
	}

	public void setSettingsPermissionId(Long settingsPermissionId) {
		this.settingsPermissionId = settingsPermissionId;
	}

	public Boolean getIsSettingsPermissionAll() {
		return isSettingsPermissionAll;
	}

	public void setIsSettingsPermissionAll(Boolean isSettingsPermissionAll) {
		this.isSettingsPermissionAll = isSettingsPermissionAll;
	}

	public Boolean getIsViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setIsViewAllEntriesRequired(Boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public RolesPermission getRolesPermission() {
		return rolesPermission;
	}

	public void setRolesPermission(RolesPermission rolesPermission) {
		this.rolesPermission = rolesPermission;
	}

	public RoleUserPermission getRoleUserPermission() {
		return roleUserPermission;
	}

	public void setRoleUserPermission(RoleUserPermission roleUserPermission) {
		this.roleUserPermission = roleUserPermission;
	}

	public ExplicitPermission getExplicitPermission() {
		return explicitPermission;
	}

	public void setExplicitPermission(ExplicitPermission explicitPermission) {
		this.explicitPermission = explicitPermission;
	}

	public AnnualTargetEntryPermission getAnnualTargetEntryPermission() {
		return annualTargetEntryPermission;
	}

	public void setAnnualTargetEntryPermission(AnnualTargetEntryPermission annualTargetEntryPermission) {
		this.annualTargetEntryPermission = annualTargetEntryPermission;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}
