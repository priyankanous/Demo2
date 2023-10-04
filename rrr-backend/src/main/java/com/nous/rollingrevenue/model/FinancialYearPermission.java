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
@Table(name = "financial_year_permission")
@EntityListeners(AuditingEntityListener.class)
public class FinancialYearPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "financial_year_permission_id")
	private Long financialYearPermissionId;

	@Column(name = "financial_year_permission_all")
	private Boolean isFinancialYearPermissionAll;

	@Column(name = "view")
	private Boolean isViewRequired;

	@Column(name = "add")
	private Boolean isAddRequired;

	@Column(name = "edit")
	private Boolean isEditRequired;

	@Column(name = "activate_or_deactivate")
	private Boolean isActivateOrDeactivateRequired;

	@OneToMany(mappedBy = "financialYearPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	public FinancialYearPermission() {

	}

	public FinancialYearPermission(Long financialYearPermissionId, Boolean isFinancialYearPermissionAll,
			Boolean isViewRequired, Boolean isAddRequired, Boolean isEditRequired,
			Boolean isActivateOrDeactivateRequired, List<AdministrationPermission> administrationPermission) {
		super();
		this.financialYearPermissionId = financialYearPermissionId;
		this.isFinancialYearPermissionAll = isFinancialYearPermissionAll;
		this.isViewRequired = isViewRequired;
		this.isAddRequired = isAddRequired;
		this.isEditRequired = isEditRequired;
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
		this.administrationPermission = administrationPermission;
	}

	public Long getFinancialYearPermissionId() {
		return financialYearPermissionId;
	}

	public void setFinancialYearPermissionId(Long financialYearPermissionId) {
		this.financialYearPermissionId = financialYearPermissionId;
	}

	public Boolean getIsFinancialYearPermissionAll() {
		return isFinancialYearPermissionAll;
	}

	public void setIsFinancialYearPermissionAll(Boolean isFinancialYearPermissionAll) {
		this.isFinancialYearPermissionAll = isFinancialYearPermissionAll;
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
