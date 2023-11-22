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
import lombok.Data;

@Entity
@Table(name = "settings_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class SettingsPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "settings_permission_id")
	private Long settingsPermissionId;

	@Column(name = "settings_permission_all")
	private boolean isSettingsPermissionAll;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "roles__permission_id", referencedColumnName = "roles__permission_id")
	private RolesPermission rolesPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "role_user_permission_id", referencedColumnName = "role_user_permission_id")
	private RoleUserPermission roleUserPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "explicit_permission_id", referencedColumnName = "explicit_permission_id")
	private ExplicitPermission explicitPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "annual_target_entry_permission_id", referencedColumnName = "annual_target_entry_permission_id")
	private AnnualTargetEntryPermission annualTargetEntryPermission;

	@OneToMany(mappedBy = "settingsPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

}
