package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "financial_year_permission")
@EntityListeners(AuditingEntityListener.class)
public class FinancialYearPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "financial_year_permission_id")
	private Long financialYearPermissionId;

	@Column(name = "is_create")
	private Boolean isCreateRequired;

	@Column(name = "is_activate_or_deactivate")
	private Boolean isActivateOrDeactivateRequired;

	public FinancialYearPermission() {

	}

	public FinancialYearPermission(Long financialYearPermissionId, Boolean isCreateRequired,
			Boolean isActivateOrDeactivateRequired) {
		super();
		this.financialYearPermissionId = financialYearPermissionId;
		this.isCreateRequired = isCreateRequired;
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
	}

	public Long getFinancialYearPermissionId() {
		return financialYearPermissionId;
	}

	public void setFinancialYearPermissionId(Long financialYearPermissionId) {
		this.financialYearPermissionId = financialYearPermissionId;
	}

	public Boolean getIsCreateRequired() {
		return isCreateRequired;
	}

	public void setIsCreateRequired(Boolean isCreateRequired) {
		this.isCreateRequired = isCreateRequired;
	}

	public Boolean getIsActivateOrDeactivateRequired() {
		return isActivateOrDeactivateRequired;
	}

	public void setIsActivateOrDeactivateRequired(Boolean isActivateOrDeactivateRequired) {
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
	}

}
