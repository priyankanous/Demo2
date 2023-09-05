package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_data_upload_permission")
@EntityListeners(AuditingEntityListener.class)
public class InvoiceDataUploadPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_data_upload_permission_id")
	private Long invoiceDataUploadPermissionId;

	@Column(name = "is_view_list")
	private Boolean isViewListRequired;

	@Column(name = "is_upload_data")
	private Boolean isUploadDataRequired;

	@Column(name = "is_mark_mis_published")
	private Boolean isMarkMisPublishedRequired;

	public InvoiceDataUploadPermission() {

	}

	public InvoiceDataUploadPermission(Long invoiceDataUploadPermissionId, Boolean isViewListRequired,
			Boolean isUploadDataRequired, Boolean isMarkMisPublishedRequired) {
		super();
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
		this.isViewListRequired = isViewListRequired;
		this.isUploadDataRequired = isUploadDataRequired;
		this.isMarkMisPublishedRequired = isMarkMisPublishedRequired;
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

}
