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

	private boolean isViewListRequired;

	private boolean isUploadDataRequired;

	private boolean isEditableRequired;

	private boolean isDeleteRequired;

	private boolean isActive;

	public InvoiceDataUploadPermissionVO() {

	}

	public InvoiceDataUploadPermissionVO(Long invoiceDataUploadPermissionId, boolean invoiceDataUploadPermissionAll,
			boolean isViewListRequired, boolean isUploadDataRequired, boolean isEditableRequired,
			boolean isDeleteRequired, boolean isActive) {
		super();
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
		this.invoiceDataUploadPermissionAll = invoiceDataUploadPermissionAll;
		this.isViewListRequired = isViewListRequired;
		this.isUploadDataRequired = isUploadDataRequired;
		this.isEditableRequired = isEditableRequired;
		this.isDeleteRequired = isDeleteRequired;
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
		return isViewListRequired;
	}

	public void setViewListRequired(boolean isViewListRequired) {
		this.isViewListRequired = isViewListRequired;
	}

	public boolean isUploadDataRequired() {
		return isUploadDataRequired;
	}

	public void setUploadDataRequired(boolean isUploadDataRequired) {
		this.isUploadDataRequired = isUploadDataRequired;
	}

	public boolean isEditableRequired() {
		return isEditableRequired;
	}

	public void setEditableRequired(boolean isEditableRequired) {
		this.isEditableRequired = isEditableRequired;
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
