package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class FinancialYearPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long financialYearPermissionId;

	@NotNull(message = "isCreateRequired cannot be null or empty")
	private Boolean isCreateRequired;

	@NotNull(message = "isActivateOrDeactivateRequired cannot be null or empty")
	private Boolean isActivateOrDeactivateRequired;

	private boolean isActive;

	public FinancialYearPermissionVO() {

	}

	public FinancialYearPermissionVO(Long financialYearPermissionId, Boolean isCreateRequired,
			Boolean isActivateOrDeactivateRequired, boolean isActive) {
		super();
		this.financialYearPermissionId = financialYearPermissionId;
		this.isCreateRequired = isCreateRequired;
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
		this.isActive = isActive;
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
