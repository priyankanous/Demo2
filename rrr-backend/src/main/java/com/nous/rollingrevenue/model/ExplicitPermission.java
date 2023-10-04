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
@Table(name = "ExplicitPermission")
@EntityListeners(AuditingEntityListener.class)
public class ExplicitPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "explicit_permission_id")
	private Long ExplicitPermissionId;

	@Column(name = "explicit_permission_all")
	private boolean isExplicitPermissionAll;

	@OneToMany(mappedBy = "explicitPermission")
	@JsonBackReference
	private List<SettingsPermission> settingsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "settings_common_permission_id", referencedColumnName = "settings_common_permission_id")
	private SettingsCommonPermission settingsCommonPermission;

	public ExplicitPermission() {

	}

	public ExplicitPermission(Long explicitPermissionId, boolean isExplicitPermissionAll,
			List<SettingsPermission> settingsPermission, SettingsCommonPermission settingsCommonPermission) {
		super();
		ExplicitPermissionId = explicitPermissionId;
		this.isExplicitPermissionAll = isExplicitPermissionAll;
		this.settingsPermission = settingsPermission;
		this.settingsCommonPermission = settingsCommonPermission;
	}

	public Long getExplicitPermissionId() {
		return ExplicitPermissionId;
	}

	public void setExplicitPermissionId(Long explicitPermissionId) {
		ExplicitPermissionId = explicitPermissionId;
	}

	public boolean isExplicitPermissionAll() {
		return isExplicitPermissionAll;
	}

	public void setExplicitPermissionAll(boolean isExplicitPermissionAll) {
		this.isExplicitPermissionAll = isExplicitPermissionAll;
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
