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
@Table(name = "rolling_revenue_entry_permission")
@EntityListeners(AuditingEntityListener.class)
public class RollingRevenueEntryPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rolling_revenue_entry_permission_id")
	private Long rollingrevenueEntryPermissionId;

	@Column(name = "is_view_all_entries")
	private Boolean isViewAllEntriesRequired;

	@Column(name = "is_add_revenue_entry")
	private Boolean isAddRevenueEntryRequired;

	@Column(name = "is_edit_revenue_entry")
	private Boolean isEditRevenueEntryRequired;

	@Column(name = "is_delete_revenue_entry")
	private Boolean isDeleteRevenueEntryRequired;

	@Column(name = "is_copy_revenue_entry")
	private Boolean isCopyRevenueEntryRequired;

	@Column(name = "is_submit_revenue_entry")
	private Boolean isSubmitRevenueEntryRequired;

	@Column(name = "is_all_entries")
	private Boolean isAllEntriesRequired;

	@Column(name = "is_export")
	private Boolean isExportRequired;

	@Column(name = "is_only_if_creator")
	private Boolean isOnlyIfCreatorRequired;

	@Column(name = "is_print")
	private Boolean isPrintRequired;

	public RollingRevenueEntryPermission() {

	}

	public RollingRevenueEntryPermission(Long rollingrevenueEntryPermissionId, Boolean isViewAllEntriesRequired,
			Boolean isAddRevenueEntryRequired, Boolean isEditRevenueEntryRequired, Boolean isDeleteRevenueEntryRequired,
			Boolean isCopyRevenueEntryRequired, Boolean isSubmitRevenueEntryRequired, Boolean isAllEntriesRequired,
			Boolean isExportRequired, Boolean isOnlyIfCreatorRequired, Boolean isPrintRequired) {
		super();
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.isAddRevenueEntryRequired = isAddRevenueEntryRequired;
		this.isEditRevenueEntryRequired = isEditRevenueEntryRequired;
		this.isDeleteRevenueEntryRequired = isDeleteRevenueEntryRequired;
		this.isCopyRevenueEntryRequired = isCopyRevenueEntryRequired;
		this.isSubmitRevenueEntryRequired = isSubmitRevenueEntryRequired;
		this.isAllEntriesRequired = isAllEntriesRequired;
		this.isExportRequired = isExportRequired;
		this.isOnlyIfCreatorRequired = isOnlyIfCreatorRequired;
		this.isPrintRequired = isPrintRequired;
	}

	public Long getRollingrevenueEntryPermissionId() {
		return rollingrevenueEntryPermissionId;
	}

	public void setRollingrevenueEntryPermissionId(Long rollingrevenueEntryPermissionId) {
		this.rollingrevenueEntryPermissionId = rollingrevenueEntryPermissionId;
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

	public Boolean getIsAllEntriesRequired() {
		return isAllEntriesRequired;
	}

	public void setIsAllEntriesRequired(Boolean isAllEntriesRequired) {
		this.isAllEntriesRequired = isAllEntriesRequired;
	}

	public Boolean getIsExportRequired() {
		return isExportRequired;
	}

	public void setIsExportRequired(Boolean isExportRequired) {
		this.isExportRequired = isExportRequired;
	}

	public Boolean getIsOnlyIfCreatorRequired() {
		return isOnlyIfCreatorRequired;
	}

	public void setIsOnlyIfCreatorRequired(Boolean isOnlyIfCreatorRequired) {
		this.isOnlyIfCreatorRequired = isOnlyIfCreatorRequired;
	}

	public Boolean getIsPrintRequired() {
		return isPrintRequired;
	}

	public void setIsPrintRequired(Boolean isPrintRequired) {
		this.isPrintRequired = isPrintRequired;
	}

}
