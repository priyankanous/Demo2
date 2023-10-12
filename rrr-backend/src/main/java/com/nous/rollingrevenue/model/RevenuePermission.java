package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "revenue_permission")
@EntityListeners(AuditingEntityListener.class)
public class RevenuePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "revenue_permission_id")
	private Long revenuePermissionId;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@Column(name = "revenue_all")
	private boolean revenuePermissionAll;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "rolling_revenue_entry_permission_id", referencedColumnName = "rolling_revenue_entry_permission_id")
	private RollingRevenueEntryPermission rollingRevenueEntryPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "invoice_data_upload_permission_id", referencedColumnName = "invoice_data_upload_permission_id")
	private InvoiceDataUploadPermission invoiceDataUploadPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "review_and_publish_permission_id", referencedColumnName = "review_and_publish_permission_id")
	private ReviewandPublishPermission reviewandPublishPermission;

	@OneToMany(mappedBy = "revenuePermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

	public RevenuePermission() {

	}

	public RevenuePermission(Long revenuePermissionId, boolean isViewAllEntriesRequired, boolean revenuePermissionAll,
			RollingRevenueEntryPermission rollingRevenueEntryPermission,
			InvoiceDataUploadPermission invoiceDataUploadPermission,
			ReviewandPublishPermission reviewandPublishPermission, List<Roles> roles) {
		super();
		this.revenuePermissionId = revenuePermissionId;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.revenuePermissionAll = revenuePermissionAll;
		this.rollingRevenueEntryPermission = rollingRevenueEntryPermission;
		this.invoiceDataUploadPermission = invoiceDataUploadPermission;
		this.reviewandPublishPermission = reviewandPublishPermission;
		this.roles = roles;
	}

	public Long getRevenuePermissionId() {
		return revenuePermissionId;
	}

	public void setRevenuePermissionId(Long revenuePermissionId) {
		this.revenuePermissionId = revenuePermissionId;
	}

	public boolean isViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setViewAllEntriesRequired(boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public boolean isRevenuePermissionAll() {
		return revenuePermissionAll;
	}

	public void setRevenuePermissionAll(boolean revenuePermissionAll) {
		this.revenuePermissionAll = revenuePermissionAll;
	}

	public RollingRevenueEntryPermission getRollingRevenueEntryPermission() {
		return rollingRevenueEntryPermission;
	}

	public void setRollingRevenueEntryPermission(RollingRevenueEntryPermission rollingRevenueEntryPermission) {
		this.rollingRevenueEntryPermission = rollingRevenueEntryPermission;
	}

	public InvoiceDataUploadPermission getInvoiceDataUploadPermission() {
		return invoiceDataUploadPermission;
	}

	public void setInvoiceDataUploadPermission(InvoiceDataUploadPermission invoiceDataUploadPermission) {
		this.invoiceDataUploadPermission = invoiceDataUploadPermission;
	}

	public ReviewandPublishPermission getReviewandPublishPermission() {
		return reviewandPublishPermission;
	}

	public void setReviewandPublishPermission(ReviewandPublishPermission reviewandPublishPermission) {
		this.reviewandPublishPermission = reviewandPublishPermission;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}
