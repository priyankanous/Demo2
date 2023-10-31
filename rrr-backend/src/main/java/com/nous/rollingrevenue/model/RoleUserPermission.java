package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
@Table(name = "role_user_permission")
@EntityListeners(AuditingEntityListener.class)
public class RoleUserPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_user_permission_id")
	private Long roleUserPermissionId;

	@Column(name = "role_user_permission_all")
	private boolean isRoleUserPermissionAll;

	@OneToMany(mappedBy = "roleUserPermission")
	@JsonBackReference
	private List<SettingsPermission> settingsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "settings_common_permission_id", referencedColumnName = "settings_common_permission_id")
	private SettingsCommonPermission settingsCommonPermission;

	public RoleUserPermission() {

	}

	public RoleUserPermission(Long roleUserPermissionId, boolean isRoleUserPermissionAll,
			List<SettingsPermission> settingsPermission, SettingsCommonPermission settingsCommonPermission) {
		super();
		this.roleUserPermissionId = roleUserPermissionId;
		this.isRoleUserPermissionAll = isRoleUserPermissionAll;
		this.settingsPermission = settingsPermission;
		this.settingsCommonPermission = settingsCommonPermission;
	}

	public Long getRoleUserPermissionId() {
		return roleUserPermissionId;
	}

	public void setRoleUserPermissionId(Long roleUserPermissionId) {
		this.roleUserPermissionId = roleUserPermissionId;
	}

	public boolean isRoleUserPermissionAll() {
		return isRoleUserPermissionAll;
	}

	public void setRoleUserPermissionAll(boolean isRoleUserPermissionAll) {
		this.isRoleUserPermissionAll = isRoleUserPermissionAll;
	}

	public List<SettingsPermission> getSettingsPermission() {
		return settingsPermission;
	}

	public void setSettingsPermission(List<SettingsPermission> settingsPermission) {
		this.settingsPermission = settingsPermission;
	}

	public SettingsCommonPermission getSettingsCommonPermission() {
		return settingsCommonPermission;
	}

	public void setSettingsCommonPermission(SettingsCommonPermission settingsCommonPermission) {
		this.settingsCommonPermission = settingsCommonPermission;
	}

}
