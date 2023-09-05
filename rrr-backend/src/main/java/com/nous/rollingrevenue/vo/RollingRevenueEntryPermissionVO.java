package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class RollingRevenueEntryPermissionVO implements Serializable {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rollingrevenueEntryPermissionId;

	@NotNull(message = "isViewAllEntriesRequired cannot be null or empty")
	private Boolean isViewAllEntriesRequired;

	@NotNull(message = "isAddRevenueEntryRequired cannot be null or empty")
	private Boolean isAddRevenueEntryRequired;

	@NotNull(message = "isEditRevenueEntryRequired cannot be null or empty")
	private Boolean isEditRevenueEntryRequired;

	@NotNull(message = "isDeleteRevenueEntryRequired cannot be null or empty")
	private Boolean isDeleteRevenueEntryRequired;

	@NotNull(message = "isCopyRevenueEntryRequired cannot be null or empty")
	private Boolean isCopyRevenueEntryRequired;

	@NotNull(message = "isSubmitRevenueEntryRequired cannot be null or empty")
	private Boolean isSubmitRevenueEntryRequired;

	@NotNull(message = "isAllEntriesRequired cannot be null or empty")
	private Boolean isAllEntriesRequired;

	@NotNull(message = "isExportRequired cannot be null or empty")
	private Boolean isExportRequired;

	@NotNull(message = "isOnlyIfCreatorRequired cannot be null or empty")
	private Boolean isOnlyIfCreatorRequired;

	@NotNull(message = "isPrintRequired cannot be null or empty")
	private Boolean isPrintRequired;

	private boolean isActive;

	public RollingRevenueEntryPermissionVO() {

	}

	public RollingRevenueEntryPermissionVO(Long rollingrevenueEntryPermissionId, Boolean isViewAllEntriesRequired,
			Boolean isAddRevenueEntryRequired, Boolean isEditRevenueEntryRequired, Boolean isDeleteRevenueEntryRequired,
			Boolean isCopyRevenueEntryRequired, Boolean isSubmitRevenueEntryRequired, Boolean isAllEntriesRequired,
			Boolean isExportRequired, Boolean isOnlyIfCreatorRequired, Boolean isPrintRequired, boolean isActive) {
		super();
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
		this.isAllEntriesRequired = isAllEntriesRequired;
		this.isExportRequired = isExportRequired;
		this.isOnlyIfCreatorRequired = isOnlyIfCreatorRequired;
		this.isPrintRequired = isPrintRequired;
		this.isActive = isActive;
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

	public Boolean getIsAllEntriesRequired() {
		return isAllEntriesRequired;
	}

	public void setIsAllEntriesRequired(Boolean isAllEntriesRequired) {
		this.isAllEntriesRequired = isAllEntriesRequired;
	}

	public Boolean getIsExportRequired() {
		return isExportRequired;
	}

	public void setIsExportRequired(Boolean isExportRequired) {
		this.isExportRequired = isExportRequired;
	}

	public Boolean getIsOnlyIfCreatorRequired() {
		return isOnlyIfCreatorRequired;
	}

	public void setIsOnlyIfCreatorRequired(Boolean isOnlyIfCreatorRequired) {
		this.isOnlyIfCreatorRequired = isOnlyIfCreatorRequired;
	}

	public Boolean getIsPrintRequired() {
		return isPrintRequired;
	}

	public void setIsPrintRequired(Boolean isPrintRequired) {
		this.isPrintRequired = isPrintRequired;
	}
	
	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
