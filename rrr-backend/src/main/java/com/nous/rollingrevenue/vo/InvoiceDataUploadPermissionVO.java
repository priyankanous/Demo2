package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class InvoiceDataUploadPermissionVO implements Serializable{

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	private Long invoiceDataUploadPermissionId;
	
	@NotNull(message = "isViewListRequired cannot be null or empty")
	private Boolean isViewListRequired;

	@NotNull(message = "isUploadDataRequired cannot be null or empty")
	private Boolean isUploadDataRequired;

	@NotNull(message = "isMarkMisPublishedRequired cannot be null or empty")
	private Boolean isMarkMisPublishedRequired;
	
	private boolean isActive;


	public InvoiceDataUploadPermissionVO() {

	}

	public InvoiceDataUploadPermissionVO(Long invoiceDataUploadPermissionId,Boolean isViewListRequired,
			Boolean isUploadDataRequired, Boolean isMarkMisPublishedRequired,boolean isActive) {
		super();
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
		this.isViewListRequired = isViewListRequired;
		this.isUploadDataRequired = isUploadDataRequired;
		this.isMarkMisPublishedRequired = isMarkMisPublishedRequired;
		this.isActive = isActive;
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

	public Boolean getIsMarkMisPublishedRequired() {
		return isMarkMisPublishedRequired;
	}

	public void setIsMarkMisPublishedRequired(Boolean isMarkMisPublishedRequired) {
		this.isMarkMisPublishedRequired = isMarkMisPublishedRequired;
	}
	
	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
