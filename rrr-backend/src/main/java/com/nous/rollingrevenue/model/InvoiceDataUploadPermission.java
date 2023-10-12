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
	private boolean isInvoiceDataUploadPermissionAll;

	@Column(name = "view_list")
	private boolean isViewListRequired;

	@Column(name = "upload_data")
	private boolean isUploadDataRequired;

	@Column(name = "edit_or_reupload")
	private boolean isEditOrReupload;

	@Column(name = "delete_data")
	private boolean isDeleteDataRequired;

	@OneToMany(mappedBy = "invoiceDataUploadPermission")
	@JsonBackReference
	private List<RevenuePermission> revenuePermission = new ArrayList<>();

	public InvoiceDataUploadPermission() {

	}

	public InvoiceDataUploadPermission(Long invoiceDataUploadPermissionId, boolean isInvoiceDataUploadPermissionAll,
			boolean isViewListRequired, boolean isUploadDataRequired, boolean isEditOrReupload,
			boolean isDeleteDataRequired, List<RevenuePermission> revenuePermission) {
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

	public boolean isInvoiceDataUploadPermissionAll() {
		return isInvoiceDataUploadPermissionAll;
	}

	public void setInvoiceDataUploadPermissionAll(boolean isInvoiceDataUploadPermissionAll) {
		this.isInvoiceDataUploadPermissionAll = isInvoiceDataUploadPermissionAll;
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

	public boolean isEditOrReupload() {
		return isEditOrReupload;
	}

	public void setEditOrReupload(boolean isEditOrReupload) {
		this.isEditOrReupload = isEditOrReupload;
	}

	public boolean isDeleteDataRequired() {
		return isDeleteDataRequired;
	}

	public void setDeleteDataRequired(boolean isDeleteDataRequired) {
		this.isDeleteDataRequired = isDeleteDataRequired;
	}

	public List<RevenuePermission> getRevenuePermission() {
		return revenuePermission;
	}

	public void setRevenuePermission(List<RevenuePermission> revenuePermission) {
		this.revenuePermission = revenuePermission;
	}

}
