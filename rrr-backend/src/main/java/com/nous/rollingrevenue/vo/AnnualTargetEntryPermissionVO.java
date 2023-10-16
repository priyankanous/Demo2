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

	private boolean createRequired;

	private boolean editRequired;

	private boolean viewRequired;

	private boolean deleteRequired;

	private boolean isActive;

	public AnnualTargetEntryPermissionVO() {

	}

	public AnnualTargetEntryPermissionVO(Long annualTargetEntryPermissionId, boolean annualTargetEntryPermissionAll,
			boolean createRequired, boolean editRequired, boolean viewRequired, boolean deleteRequired,
			boolean isActive) {
		super();
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
		this.annualTargetEntryPermissionAll = annualTargetEntryPermissionAll;
		this.createRequired = createRequired;
		this.editRequired = editRequired;
		this.viewRequired = viewRequired;
		this.deleteRequired = deleteRequired;
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
		return createRequired;
	}

	public void setCreateRequired(boolean createRequired) {
		this.createRequired = createRequired;
	}

	public boolean isEditRequired() {
		return editRequired;
	}

	public void setEditRequired(boolean editRequired) {
		this.editRequired = editRequired;
	}

	public boolean isViewRequired() {
		return viewRequired;
	}

	public void setViewRequired(boolean viewRequired) {
		this.viewRequired = viewRequired;
	}

	public boolean isDeleteRequired() {
		return deleteRequired;
	}

	public void setDeleteRequired(boolean deleteRequired) {
		this.deleteRequired = deleteRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
