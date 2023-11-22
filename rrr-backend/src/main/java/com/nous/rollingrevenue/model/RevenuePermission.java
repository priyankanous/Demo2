package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import lombok.Data;

@Entity
@Table(name = "revenue_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class RevenuePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "revenue_permission_id")
	private Long revenuePermissionId;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@Column(name = "revenue_all")
	private boolean revenuePermissionAll;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "rolling_revenue_entry_permission_id", referencedColumnName = "rolling_revenue_entry_permission_id")
	private RollingRevenueEntryPermission rollingRevenueEntryPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "invoice_data_upload_permission_id", referencedColumnName = "invoice_data_upload_permission_id")
	private InvoiceDataUploadPermission invoiceDataUploadPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "review_and_publish_permission_id", referencedColumnName = "review_and_publish_permission_id")
	private ReviewandPublishPermission reviewandPublishPermission;

	@OneToMany(mappedBy = "revenuePermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

}
