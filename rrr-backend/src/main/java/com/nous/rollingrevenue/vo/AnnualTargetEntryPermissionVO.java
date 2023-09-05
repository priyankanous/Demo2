package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

public class AnnualTargetEntryPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long annualTargetEntryPermissionId;
	
	@NotNull(message = "isAddAnnualTargetEntryRequired cannot be null or empty")
	private Boolean isAddAnnualTargetEntryRequired;
	
	private boolean isActive;

	public AnnualTargetEntryPermissionVO() {
		super();
	}

	public AnnualTargetEntryPermissionVO(Long annualTargetEntryPermissionId,
			Boolean isAddAnnualTargetEntryRequired,
			boolean isActive) {
		super();
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
		this.isAddAnnualTargetEntryRequired = isAddAnnualTargetEntryRequired;
		this.isActive = isActive;
	}

	public Long getAnnualTargetEntryPermissionId() {
		return annualTargetEntryPermissionId;
	}

	public void setAnnualTargetEntryPermissionId(Long annualTargetEntryPermissionId) {
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
	}

	public Boolean getIsAddAnnualTargetEntryRequired() {
		return isAddAnnualTargetEntryRequired;
	}

	public void setIsAddAnnualTargetEntryRequired(Boolean isAddAnnualTargetEntryRequired) {
		this.isAddAnnualTargetEntryRequired = isAddAnnualTargetEntryRequired;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}


