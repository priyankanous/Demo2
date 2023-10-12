package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RollingRevenueEntryPermissionVO implements Serializable {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long rollingrevenueEntryPermissionId;

	private boolean rollingrevenueEntryPermissionAll;

	private boolean isViewAllEntriesRequired;

	private boolean isAddRevenueEntryRequired;

	private boolean isEditRevenueEntryRequired;

	private boolean isDeleteRevenueEntryRequired;

	private boolean isCopyRevenueEntryRequired;

	private boolean isSubmitRevenueEntryRequired;

	private boolean isExportRequired;

	private boolean isActive;

	public RollingRevenueEntryPermissionVO() {

	}

	public RollingRevenueEntryPermissionVO(Long rollingrevenueEntryPermissionId,
			boolean rollingrevenueEntryPermissionAll, boolean isViewAllEntriesRequired,
			boolean isAddRevenueEntryRequired, boolean isEditRevenueEntryRequired, boolean isDeleteRevenueEntryRequired,
			boolean isCopyRevenueEntryRequired, boolean isSubmitRevenueEntryRequired, boolean isExportRequired,
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

	public Long getRollingrevenueEntryPermissionId() {
		return rollingrevenueEntryPermissionId;
	}

	public void setRollingrevenueEntryPermissionId(Long rollingrevenueEntryPermissionId) {
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
	}

	public boolean isRollingrevenueEntryPermissionAll() {
		return rollingrevenueEntryPermissionAll;
	}

	public void setRollingrevenueEntryPermissionAll(boolean rollingrevenueEntryPermissionAll) {
		this.rollingrevenueEntryPermissionAll = rollingrevenueEntryPermissionAll;
	}

	public boolean isViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setViewAllEntriesRequired(boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public boolean isAddRevenueEntryRequired() {
		return isAddRevenueEntryRequired;
	}

	public void setAddRevenueEntryRequired(boolean isAddRevenueEntryRequired) {
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
	}

	public boolean isEditRevenueEntryRequired() {
		return isEditRevenueEntryRequired;
	}

	public void setEditRevenueEntryRequired(boolean isEditRevenueEntryRequired) {
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
	}

	public boolean isDeleteRevenueEntryRequired() {
		return isDeleteRevenueEntryRequired;
	}

	public void setDeleteRevenueEntryRequired(boolean isDeleteRevenueEntryRequired) {
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
	}

	public boolean isCopyRevenueEntryRequired() {
		return isCopyRevenueEntryRequired;
	}

	public void setCopyRevenueEntryRequired(boolean isCopyRevenueEntryRequired) {
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
	}

	public boolean isSubmitRevenueEntryRequired() {
		return isSubmitRevenueEntryRequired;
	}

	public void setSubmitRevenueEntryRequired(boolean isSubmitRevenueEntryRequired) {
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
	}

	public boolean isExportRequired() {
		return isExportRequired;
	}

	public void setExportRequired(boolean isExportRequired) {
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
