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
@Table(name = "rolling_revenue_entry_permission")
@EntityListeners(AuditingEntityListener.class)
public class RollingRevenueEntryPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rolling_revenue_entry_permission_id")
	private Long rollingrevenueEntryPermissionId;

	@Column(name = "rolling_revenue_entry_permission_all")
	private boolean isRollingRevenueEntryPermissionAll;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@Column(name = "add_revenue_entry")
	private boolean isAddRevenueEntryRequired;

	@Column(name = "edit_revenue_entry")
	private boolean isEditRevenueEntryRequired;

	@Column(name = "delete_revenue_entry")
	private boolean isDeleteRevenueEntryRequired;

	@Column(name = "copy_revenue_entry")
	private boolean isCopyRevenueEntryRequired;

	@Column(name = "submit_revenue_entry")
	private boolean isSubmitRevenueEntryRequired;

	@Column(name = "export")
	private boolean isExportRequired;

	@OneToMany(mappedBy = "rollingRevenueEntryPermission")
	@JsonBackReference
	private List<RevenuePermission> revenuePermission = new ArrayList<>();

	public RollingRevenueEntryPermission() {

	}

	public RollingRevenueEntryPermission(Long rollingrevenueEntryPermissionId,
			boolean isRollingRevenueEntryPermissionAll, boolean isViewAllEntriesRequired,
			boolean isAddRevenueEntryRequired, boolean isEditRevenueEntryRequired, boolean isDeleteRevenueEntryRequired,
			boolean isCopyRevenueEntryRequired, boolean isSubmitRevenueEntryRequired, boolean isExportRequired,
			List<RevenuePermission> revenuePermission) {
		super();
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
		this.isRollingRevenueEntryPermissionAll = isRollingRevenueEntryPermissionAll;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
		this.isExportRequired = isExportRequired;
		this.revenuePermission = revenuePermission;
	}

	public Long getRollingrevenueEntryPermissionId() {
		return rollingrevenueEntryPermissionId;
	}

	public void setRollingrevenueEntryPermissionId(Long rollingrevenueEntryPermissionId) {
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
	}

	public boolean isRollingRevenueEntryPermissionAll() {
		return isRollingRevenueEntryPermissionAll;
	}

	public void setRollingRevenueEntryPermissionAll(boolean isRollingRevenueEntryPermissionAll) {
		this.isRollingRevenueEntryPermissionAll = isRollingRevenueEntryPermissionAll;
	}

	public boolean isViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setViewAllEntriesRequired(boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public boolean isAddRevenueEntryRequired() {
		return isAddRevenueEntryRequired;
	}

	public void setAddRevenueEntryRequired(boolean isAddRevenueEntryRequired) {
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
	}

	public boolean isEditRevenueEntryRequired() {
		return isEditRevenueEntryRequired;
	}

	public void setEditRevenueEntryRequired(boolean isEditRevenueEntryRequired) {
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
	}

	public boolean isDeleteRevenueEntryRequired() {
		return isDeleteRevenueEntryRequired;
	}

	public void setDeleteRevenueEntryRequired(boolean isDeleteRevenueEntryRequired) {
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
	}

	public boolean isCopyRevenueEntryRequired() {
		return isCopyRevenueEntryRequired;
	}

	public void setCopyRevenueEntryRequired(boolean isCopyRevenueEntryRequired) {
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
	}

	public boolean isSubmitRevenueEntryRequired() {
		return isSubmitRevenueEntryRequired;
	}

	public void setSubmitRevenueEntryRequired(boolean isSubmitRevenueEntryRequired) {
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
	}

	public boolean isExportRequired() {
		return isExportRequired;
	}

	public void setExportRequired(boolean isExportRequired) {
		this.isExportRequired = isExportRequired;
	}

	public List<RevenuePermission> getRevenuePermission() {
		return revenuePermission;
	}

	public void setRevenuePermission(List<RevenuePermission> revenuePermission) {
		this.revenuePermission = revenuePermission;
	}

}
