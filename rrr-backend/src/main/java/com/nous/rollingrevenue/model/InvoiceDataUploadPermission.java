package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "invoice_data_upload_permission")
@EntityListeners(AuditingEntityListener.class)
public class InvoiceDataUploadPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_data_upload_permission_id")
	private Long invoiceDataUploadPermissionId;

	@Column(name = "invoice_data_upload_permission_all")
	private Boolean isInvoiceDataUploadPermissionAll;

	@Column(name = "view_list")
	private Boolean isViewListRequired;

	@Column(name = "upload_data")
	private Boolean isUploadDataRequired;

	@Column(name = "edit_or_reupload")
	private Boolean isEditOrReupload;

	@Column(name = "delete_data")
	private Boolean isDeleteDataRequired;

	@OneToMany(mappedBy = "invoiceDataUploadPermission")
	@JsonBackReference
	private List<RevenuePermission> revenuePermission = new ArrayList<>();

	public InvoiceDataUploadPermission() {

	}

	public InvoiceDataUploadPermission(Long invoiceDataUploadPermissionId, Boolean isInvoiceDataUploadPermissionAll,
			Boolean isViewListRequired, Boolean isUploadDataRequired, Boolean isEditOrReupload,
			Boolean isDeleteDataRequired, List<RevenuePermission> revenuePermission) {
		super();
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
		this.isInvoiceDataUploadPermissionAll = isInvoiceDataUploadPermissionAll;
		this.isViewListRequired = isViewListRequired;
		this.isUploadDataRequired = isUploadDataRequired;
		this.isEditOrReupload = isEditOrReupload;
		this.isDeleteDataRequired = isDeleteDataRequired;
		this.revenuePermission = revenuePermission;
	}

	public Long getInvoiceDataUploadPermissionId() {
		return invoiceDataUploadPermissionId;
	}

	public void setInvoiceDataUploadPermissionId(Long invoiceDataUploadPermissionId) {
		this.invoiceDataUploadPermissionId = invoiceDataUploadPermissionId;
	}

	public Boolean getIsInvoiceDataUploadPermissionAll() {
		return isInvoiceDataUploadPermissionAll;
	}

	public void setIsInvoiceDataUploadPermissionAll(Boolean isInvoiceDataUploadPermissionAll) {
		this.isInvoiceDataUploadPermissionAll = isInvoiceDataUploadPermissionAll;
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

	public Boolean getIsEditOrReupload() {
		return isEditOrReupload;
	}

	public void setIsEditOrReupload(Boolean isEditOrReupload) {
		this.isEditOrReupload = isEditOrReupload;
	}

	public Boolean getIsDeleteDataRequired() {
		return isDeleteDataRequired;
	}

	public void setIsDeleteDataRequired(Boolean isDeleteDataRequired) {
		this.isDeleteDataRequired = isDeleteDataRequired;
	}

	public List<RevenuePermission> getRevenuePermission() {
		return revenuePermission;
	}

	public void setRevenuePermission(List<RevenuePermission> revenuePermission) {
		this.revenuePermission = revenuePermission;
	}

}
