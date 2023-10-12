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
@Table(name = "global_leave_loss_factor_permission")
@EntityListeners(AuditingEntityListener.class)
public class GlobalLeaveLossFactorPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "global_leave_loss_factor_permission_id")
	private Long globalLeaveLossFactorPermissionId;

	@Column(name = "global_leave_loss_factor_permission_all")
	private boolean isGlobalLeaveLossFactorPermissionAll;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	@OneToMany(mappedBy = "globalLeaveLossFactorPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	public GlobalLeaveLossFactorPermission() {

	}

	public GlobalLeaveLossFactorPermission(Long globalLeaveLossFactorPermissionId,
			boolean isGlobalLeaveLossFactorPermissionAll, AdministrationCommonPermission administrationCommonPermission,
			List<AdministrationPermission> administrationPermission) {
		super();
		this.globalLeaveLossFactorPermissionId = globalLeaveLossFactorPermissionId;
		this.isGlobalLeaveLossFactorPermissionAll = isGlobalLeaveLossFactorPermissionAll;
		this.administrationCommonPermission = administrationCommonPermission;
		this.administrationPermission = administrationPermission;
	}

	public Long getGlobalLeaveLossFactorPermissionId() {
		return globalLeaveLossFactorPermissionId;
	}

	public void setGlobalLeaveLossFactorPermissionId(Long globalLeaveLossFactorPermissionId) {
		this.globalLeaveLossFactorPermissionId = globalLeaveLossFactorPermissionId;
	}

	public boolean isGlobalLeaveLossFactorPermissionAll() {
		return isGlobalLeaveLossFactorPermissionAll;
	}

	public void setGlobalLeaveLossFactorPermissionAll(boolean isGlobalLeaveLossFactorPermissionAll) {
		this.isGlobalLeaveLossFactorPermissionAll = isGlobalLeaveLossFactorPermissionAll;
	}

	public AdministrationCommonPermission getAdministrationCommonPermission() {
		return administrationCommonPermission;
	}

	public void setAdministrationCommonPermission(AdministrationCommonPermission administrationCommonPermission) {
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public List<AdministrationPermission> getAdministrationPermission() {
		return administrationPermission;
	}

	public void setAdministrationPermission(List<AdministrationPermission> administrationPermission) {
		this.administrationPermission = administrationPermission;
	}

}
