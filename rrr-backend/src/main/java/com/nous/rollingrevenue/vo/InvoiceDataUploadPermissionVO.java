package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDataUploadPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long invoiceDataUploadPermissionId;

	private boolean invoiceDataUploadPermissionAll;

	private boolean viewListRequired;

	private boolean uploadDataRequired;

	private boolean editableRequired;

	private boolean deleteRequired;

	private boolean isActive;

	public InvoiceDataUploadPermissionVO() {

	}

	public InvoiceDataUploadPermissionVO(Long invoiceDataUploadPermissionId, boolean invoiceDataUploadPermissionAll,
			boolean viewListRequired, boolean uploadDataRequired, boolean editableRequired, boolean deleteRequired,
			boolean isActive) {
		super();
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
		this.invoiceDataUploadPermissionAll = invoiceDataUploadPermissionAll;
		this.viewListRequired = viewListRequired;
		this.uploadDataRequired = uploadDataRequired;
		this.editableRequired = editableRequired;
		this.deleteRequired = deleteRequired;
		this.isActive = isActive;
	}

	public Long getInvoiceDataUploadPermissionId() {
		return invoiceDataUploadPermissionId;
	}

	public void setInvoiceDataUploadPermissionId(Long invoiceDataUploadPermissionId) {
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
	}

	public boolean isInvoiceDataUploadPermissionAll() {
		return invoiceDataUploadPermissionAll;
	}

	public void setInvoiceDataUploadPermissionAll(boolean invoiceDataUploadPermissionAll) {
		this.invoiceDataUploadPermissionAll = invoiceDataUploadPermissionAll;
	}

	public boolean isViewListRequired() {
		return viewListRequired;
	}

	public void setViewListRequired(boolean viewListRequired) {
		this.viewListRequired = viewListRequired;
	}

	public boolean isUploadDataRequired() {
		return uploadDataRequired;
	}

	public void setUploadDataRequired(boolean uploadDataRequired) {
		this.uploadDataRequired = uploadDataRequired;
	}

	public boolean isEditableRequired() {
		return editableRequired;
	}

	public void setEditableRequired(boolean editableRequired) {
		this.editableRequired = editableRequired;
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
