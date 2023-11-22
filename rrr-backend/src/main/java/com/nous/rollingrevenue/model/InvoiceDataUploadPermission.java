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
import lombok.Data;

@Entity
@Table(name = "invoice_data_upload_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
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

}
