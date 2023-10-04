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
@Table(name = "global_leave_loss_factor_permission")
@EntityListeners(AuditingEntityListener.class)
public class GlobalLeaveLossFactorPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "global_leave_loss_factor_permission_id")
	private Long globalLeaveLossFactorPermissionId;

	@Column(name = "global_leave_loss_factor_permission_all")
	private Boolean isGlobalLeaveLossFactorPermissionAll;

	@Column(name = "view")
	private Boolean isViewRequired;

	@Column(name = "add")
	private Boolean isAddRequired;

	@Column(name = "edit")
	private Boolean isEditRequired;

	@Column(name = "activate_or_deactivate")
	private Boolean isActivateOrDeactivateRequired;

	@OneToMany(mappedBy = "globalLeaveLossFactorPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	public GlobalLeaveLossFactorPermission() {

	}

	public GlobalLeaveLossFactorPermission(Long globalLeaveLossFactorPermissionId,
			Boolean isGlobalLeaveLossFactorPermissionAll, Boolean isViewRequired, Boolean isAddRequired,
			Boolean isEditRequired, Boolean isActivateOrDeactivateRequired,
			List<AdministrationPermission> administrationPermission) {
		super();
		this.globalLeaveLossFactorPermissionId = globalLeaveLossFactorPermissionId;
		this.isGlobalLeaveLossFactorPermissionAll = isGlobalLeaveLossFactorPermissionAll;
		this.isViewRequired = isViewRequired;
		this.isAddRequired = isAddRequired;
		this.isEditRequired = isEditRequired;
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
		this.administrationPermission = administrationPermission;
	}

	public Long getGlobalLeaveLossFactorPermissionId() {
		return globalLeaveLossFactorPermissionId;
	}

	public void setGlobalLeaveLossFactorPermissionId(Long globalLeaveLossFactorPermissionId) {
		this.globalLeaveLossFactorPermissionId = globalLeaveLossFactorPermissionId;
	}

	public Boolean getIsGlobalLeaveLossFactorPermissionAll() {
		return isGlobalLeaveLossFactorPermissionAll;
	}

	public void setIsGlobalLeaveLossFactorPermissionAll(Boolean isGlobalLeaveLossFactorPermissionAll) {
		this.isGlobalLeaveLossFactorPermissionAll = isGlobalLeaveLossFactorPermissionAll;
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

	public Boolean getIsActivateOrDeactivateRequired() {
		return isActivateOrDeactivateRequired;
	}

	public void setIsActivateOrDeactivateRequired(Boolean isActivateOrDeactivateRequired) {
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
	}

	public List<AdministrationPermission> getAdministrationPermission() {
		return administrationPermission;
	}

	public void setAdministrationPermission(List<AdministrationPermission> administrationPermission) {
		this.administrationPermission = administrationPermission;
	}

}
