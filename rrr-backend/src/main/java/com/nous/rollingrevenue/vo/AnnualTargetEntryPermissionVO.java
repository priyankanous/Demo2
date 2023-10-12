package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnualTargetEntryPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long annualTargetEntryPermissionId;

	private boolean annualTargetEntryPermissionAll;

	private boolean isCreateRequired;

	private boolean isEditRequired;

	private boolean isViewRequired;

	private boolean isDeleteRequired;

	private boolean isActive;

	public AnnualTargetEntryPermissionVO() {

	}

	public AnnualTargetEntryPermissionVO(Long annualTargetEntryPermissionId, boolean annualTargetEntryPermissionAll,
			boolean isCreateRequired, boolean isEditRequired, boolean isViewRequired, boolean isDeleteRequired,
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

	public boolean isAnnualTargetEntryPermissionAll() {
		return annualTargetEntryPermissionAll;
	}

	public void setAnnualTargetEntryPermissionAll(boolean annualTargetEntryPermissionAll) {
		this.annualTargetEntryPermissionAll = annualTargetEntryPermissionAll;
	}

	public boolean isCreateRequired() {
		return isCreateRequired;
	}

	public void setCreateRequired(boolean isCreateRequired) {
		this.isCreateRequired = isCreateRequired;
	}

	public boolean isEditRequired() {
		return isEditRequired;
	}

	public void setEditRequired(boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public boolean isViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean isDeleteRequired() {
		return isDeleteRequired;
	}

	public void setDeleteRequired(boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
