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

	private boolean viewAllEntriesRequired;

	private boolean addRevenueEntryRequired;

	private boolean editRevenueEntryRequired;

	private boolean deleteRevenueEntryRequired;

	private boolean copyRevenueEntryRequired;

	private boolean submitRevenueEntryRequired;

	private boolean exportRequired;

	private boolean isActive;

	public RollingRevenueEntryPermissionVO() {

	}

	public RollingRevenueEntryPermissionVO(Long rollingrevenueEntryPermissionId,
			boolean rollingrevenueEntryPermissionAll, boolean viewAllEntriesRequired, boolean addRevenueEntryRequired,
			boolean editRevenueEntryRequired, boolean deleteRevenueEntryRequired, boolean copyRevenueEntryRequired,
			boolean submitRevenueEntryRequired, boolean exportRequired, boolean isActive) {
		super();
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
		this.rollingrevenueEntryPermissionAll = rollingrevenueEntryPermissionAll;
		this.viewAllEntriesRequired = viewAllEntriesRequired;
		this.addRevenueEntryRequired = addRevenueEntryRequired;
		this.editRevenueEntryRequired = editRevenueEntryRequired;
		this.deleteRevenueEntryRequired = deleteRevenueEntryRequired;
		this.copyRevenueEntryRequired = copyRevenueEntryRequired;
		this.submitRevenueEntryRequired = submitRevenueEntryRequired;
		this.exportRequired = exportRequired;
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
		return viewAllEntriesRequired;
	}

	public void setViewAllEntriesRequired(boolean viewAllEntriesRequired) {
		this.viewAllEntriesRequired = viewAllEntriesRequired;
	}

	public boolean isAddRevenueEntryRequired() {
		return addRevenueEntryRequired;
	}

	public void setAddRevenueEntryRequired(boolean addRevenueEntryRequired) {
		this.addRevenueEntryRequired = addRevenueEntryRequired;
	}

	public boolean isEditRevenueEntryRequired() {
		return editRevenueEntryRequired;
	}

	public void setEditRevenueEntryRequired(boolean editRevenueEntryRequired) {
		this.editRevenueEntryRequired = editRevenueEntryRequired;
	}

	public boolean isDeleteRevenueEntryRequired() {
		return deleteRevenueEntryRequired;
	}

	public void setDeleteRevenueEntryRequired(boolean deleteRevenueEntryRequired) {
		this.deleteRevenueEntryRequired = deleteRevenueEntryRequired;
	}

	public boolean isCopyRevenueEntryRequired() {
		return copyRevenueEntryRequired;
	}

	public void setCopyRevenueEntryRequired(boolean copyRevenueEntryRequired) {
		this.copyRevenueEntryRequired = copyRevenueEntryRequired;
	}

	public boolean isSubmitRevenueEntryRequired() {
		return submitRevenueEntryRequired;
	}

	public void setSubmitRevenueEntryRequired(boolean submitRevenueEntryRequired) {
		this.submitRevenueEntryRequired = submitRevenueEntryRequired;
	}

	public boolean isExportRequired() {
		return exportRequired;
	}

	public void setExportRequired(boolean exportRequired) {
		this.exportRequired = exportRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
