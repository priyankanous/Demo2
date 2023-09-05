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

	public AnnualTargetEntryPermissionVO() {
		super();
	}

	public AnnualTargetEntryPermissionVO(Long annualTargetEntryPermissionId,
			@NotNull(message = "isAddAnnualTargetEntryRequired cannot be null or empty") Boolean isAddAnnualTargetEntryRequired) {
		super();
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
		this.isAddAnnualTargetEntryRequired = isAddAnnualTargetEntryRequired;
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
	
	
	
	
}


