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
	private Boolean isRollingRevenueEntryPermissionAll;

	@Column(name = "view_all_entries")
	private Boolean isViewAllEntriesRequired;

	@Column(name = "add_revenue_entry")
	private Boolean isAddRevenueEntryRequired;

	@Column(name = "edit_revenue_entry")
	private Boolean isEditRevenueEntryRequired;

	@Column(name = "delete_revenue_entry")
	private Boolean isDeleteRevenueEntryRequired;

	@Column(name = "copy_revenue_entry")
	private Boolean isCopyRevenueEntryRequired;

	@Column(name = "submit_revenue_entry")
	private Boolean isSubmitRevenueEntryRequired;

	@Column(name = "export")
	private Boolean isExportRequired;

	@OneToMany(mappedBy = "rollingRevenueEntryPermission")
	@JsonBackReference
	private List<RevenuePermission> revenuePermission = new ArrayList<>();

	public RollingRevenueEntryPermission() {

	}

	public RollingRevenueEntryPermission(Long rollingrevenueEntryPermissionId,
			Boolean isRollingRevenueEntryPermissionAll, Boolean isViewAllEntriesRequired,
			Boolean isAddRevenueEntryRequired, Boolean isEditRevenueEntryRequired, Boolean isDeleteRevenueEntryRequired,
			Boolean isCopyRevenueEntryRequired, Boolean isSubmitRevenueEntryRequired, Boolean isExportRequired,
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

	public Boolean getIsRollingRevenueEntryAll() {
		return isRollingRevenueEntryPermissionAll;
	}

	public void setIsRollingRevenueEntryAll(Boolean isRollingRevenueEntryAll) {
		this.isRollingRevenueEntryPermissionAll = isRollingRevenueEntryAll;
	}

	public Boolean getIsViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setIsViewAllEntriesRequired(Boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public Boolean getIsAddRevenueEntryRequired() {
		return isAddRevenueEntryRequired;
	}

	public void setIsAddRevenueEntryRequired(Boolean isAddRevenueEntryRequired) {
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
	}

	public Boolean getIsEditRevenueEntryRequired() {
		return isEditRevenueEntryRequired;
	}

	public void setIsEditRevenueEntryRequired(Boolean isEditRevenueEntryRequired) {
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
	}

	public Boolean getIsDeleteRevenueEntryRequired() {
		return isDeleteRevenueEntryRequired;
	}

	public void setIsDeleteRevenueEntryRequired(Boolean isDeleteRevenueEntryRequired) {
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
	}

	public Boolean getIsCopyRevenueEntryRequired() {
		return isCopyRevenueEntryRequired;
	}

	public void setIsCopyRevenueEntryRequired(Boolean isCopyRevenueEntryRequired) {
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
	}

	public Boolean getIsSubmitRevenueEntryRequired() {
		return isSubmitRevenueEntryRequired;
	}

	public void setIsSubmitRevenueEntryRequired(Boolean isSubmitRevenueEntryRequired) {
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
	}

	public Boolean getIsExportRequired() {
		return isExportRequired;
	}

	public void setIsExportRequired(Boolean isExportRequired) {
		this.isExportRequired = isExportRequired;
	}

	public List<RevenuePermission> getRevenuePermission() {
		return revenuePermission;
	}

	public void setRevenuePermission(List<RevenuePermission> revenuePermission) {
		this.revenuePermission = revenuePermission;
	}

}
