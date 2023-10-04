package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnualTargetEntryPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long annualTargetEntryPermissionId;

	private Boolean annualTargetEntryPermissionAll;

	private Boolean isCreateRequired;

	private Boolean isEditRequired;

	private Boolean isViewRequired;

	private Boolean isDeleteRequired;

	private boolean isActive;

	public AnnualTargetEntryPermissionVO() {

	}

	public AnnualTargetEntryPermissionVO(Long annualTargetEntryPermissionId, Boolean annualTargetEntryPermissionAll,
			Boolean isCreateRequired, Boolean isEditRequired, Boolean isViewRequired, Boolean isDeleteRequired,
			boolean isActive) {
		super();
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
		this.annualTargetEntryPermissionAll = annualTargetEntryPermissionAll;
		this.isCreateRequired = isCreateRequired;
		this.isEditRequired = isEditRequired;
		this.isViewRequired = isViewRequired;
		this.isDeleteRequired = isDeleteRequired;
		this.isActive = isActive;
	}

	public Long getAnnualTargetEntryPermissionId() {
		return annualTargetEntryPermissionId;
	}

	public void setAnnualTargetEntryPermissionId(Long annualTargetEntryPermissionId) {
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getAnnualTargetEntryPermissionAll() {
		return annualTargetEntryPermissionAll;
	}

	public void setAnnualTargetEntryPermissionAll(Boolean annualTargetEntryPermissionAll) {
		this.annualTargetEntryPermissionAll = annualTargetEntryPermissionAll;
	}

	public Boolean getIsCreateRequired() {
		return isCreateRequired;
	}

	public void setIsCreateRequired(Boolean isCreateRequired) {
		this.isCreateRequired = isCreateRequired;
	}

	public Boolean getIsEditRequired() {
		return isEditRequired;
	}

	public void setIsEditRequired(Boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public Boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setIsViewRequired(Boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

}
