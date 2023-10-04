package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvoiceDataUploadPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long invoiceDataUploadPermissionId;

	private Boolean invoiceDataUploadPermissionAll;

	private Boolean isViewListRequired;

	private Boolean isUploadDataRequired;

	private Boolean isEditableRequired;

	private Boolean isDeleteRequired;

	private boolean isActive;

	public InvoiceDataUploadPermissionVO() {

	}

	public Long getInvoiceDataUploadPermissionId() {
		return invoiceDataUploadPermissionId;
	}

	public void setInvoiceDataUploadPermissionId(Long invoiceDataUploadPermissionId) {
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
	}

	public Boolean getIsViewListRequired() {
		return isViewListRequired;
	}

	public void setIsViewListRequired(Boolean isViewListRequired) {
		this.isViewListRequired = isViewListRequired;
	}

	public Boolean getIsUploadDataRequired() {
		return isUploadDataRequired;
	}

	public void setIsUploadDataRequired(Boolean isUploadDataRequired) {
		this.isUploadDataRequired = isUploadDataRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getInvoiceDataUploadPermissionAll() {
		return invoiceDataUploadPermissionAll;
	}

	public void setInvoiceDataUploadPermissionAll(Boolean invoiceDataUploadPermissionAll) {
		this.invoiceDataUploadPermissionAll = invoiceDataUploadPermissionAll;
	}

	public Boolean getIsEditableRequired() {
		return isEditableRequired;
	}

	public void setIsEditableRequired(Boolean isEditableRequired) {
		this.isEditableRequired = isEditableRequired;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	public InvoiceDataUploadPermissionVO(Long invoiceDataUploadPermissionId, Boolean invoiceDataUploadPermissionAll,
			Boolean isViewListRequired, Boolean isUploadDataRequired, Boolean isEditableRequired,
			Boolean isDeleteRequired, boolean isActive) {
		super();
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
		this.invoiceDataUploadPermissionAll = invoiceDataUploadPermissionAll;
		this.isViewListRequired = isViewListRequired;
		this.isUploadDataRequired = isUploadDataRequired;
		this.isEditableRequired = isEditableRequired;
		this.isDeleteRequired = isDeleteRequired;
		this.isActive = isActive;
	}

}
