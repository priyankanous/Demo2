package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RollingRevenueEntryPermissionVO implements Serializable {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rollingrevenueEntryPermissionId;

	private Boolean rollingrevenueEntryPermissionAll;

	private Boolean isViewAllEntriesRequired;

	private Boolean isAddRevenueEntryRequired;

	private Boolean isEditRevenueEntryRequired;

	private Boolean isDeleteRevenueEntryRequired;

	private Boolean isCopyRevenueEntryRequired;

	private Boolean isSubmitRevenueEntryRequired;

	private Boolean isExportRequired;

	private boolean isActive;

	public RollingRevenueEntryPermissionVO() {

	}

	public RollingRevenueEntryPermissionVO(Long rollingrevenueEntryPermissionId,
			Boolean rollingrevenueEntryPermissionAll, Boolean isViewAllEntriesRequired,
			Boolean isAddRevenueEntryRequired, Boolean isEditRevenueEntryRequired, Boolean isDeleteRevenueEntryRequired,
			Boolean isCopyRevenueEntryRequired, Boolean isSubmitRevenueEntryRequired, Boolean isExportRequired,
			boolean isActive) {
		super();
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
		this.rollingrevenueEntryPermissionAll = rollingrevenueEntryPermissionAll;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
		this.isExportRequired = isExportRequired;
		this.isActive = isActive;
	}

	public Boolean getRollingrevenueEntryPermissionAll() {
		return rollingrevenueEntryPermissionAll;
	}

	public void setRollingrevenueEntryPermissionAll(Boolean rollingrevenueEntryPermissionAll) {
		this.rollingrevenueEntryPermissionAll = rollingrevenueEntryPermissionAll;
	}

	public Long getRollingrevenueEntryPermissionId() {
		return rollingrevenueEntryPermissionId;
	}

	public void setRollingrevenueEntryPermissionId(Long rollingrevenueEntryPermissionId) {
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
	}

	public Boolean getIsViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setIsViewAllEntriesRequired(Boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public Boolean getIsAddRevenueEntryRequired() {
		return isAddRevenueEntryRequired;
	}

	public void setIsAddRevenueEntryRequired(Boolean isAddRevenueEntryRequired) {
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
	}

	public Boolean getIsEditRevenueEntryRequired() {
		return isEditRevenueEntryRequired;
	}

	public void setIsEditRevenueEntryRequired(Boolean isEditRevenueEntryRequired) {
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
	}

	public Boolean getIsDeleteRevenueEntryRequired() {
		return isDeleteRevenueEntryRequired;
	}

	public void setIsDeleteRevenueEntryRequired(Boolean isDeleteRevenueEntryRequired) {
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
	}

	public Boolean getIsCopyRevenueEntryRequired() {
		return isCopyRevenueEntryRequired;
	}

	public void setIsCopyRevenueEntryRequired(Boolean isCopyRevenueEntryRequired) {
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
	}

	public Boolean getIsSubmitRevenueEntryRequired() {
		return isSubmitRevenueEntryRequired;
	}

	public void setIsSubmitRevenueEntryRequired(Boolean isSubmitRevenueEntryRequired) {
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
	}

	public Boolean getIsExportRequired() {
		return isExportRequired;
	}

	public void setIsExportRequired(Boolean isExportRequired) {
		this.isExportRequired = isExportRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
