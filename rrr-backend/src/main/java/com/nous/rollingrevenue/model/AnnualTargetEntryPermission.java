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
@Table(name = "annual_target_entry_permission")
@EntityListeners(AuditingEntityListener.class)
public class AnnualTargetEntryPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "annual_target_entry_permission_id")
	private Long annualTargetEntryPermissionId;

	@Column(name = "annual_target_entry_permission_all")
	private boolean annualTargetEntryPermissionAll;

	@Column(name = "view")
	private boolean isViewRequired;

	@Column(name = "add_or_upload")
	private boolean isAddOrUploadRequired;

	@Column(name = "reupload_or_edit")
	private boolean isReuploadOrEditRequired;

	@Column(name = "delete")
	private boolean isDeleteRequired;

	@OneToMany(mappedBy = "annualTargetEntryPermission")
	@JsonBackReference
	private List<SettingsPermission> settingsPermission = new ArrayList<>();

	public AnnualTargetEntryPermission() {

	}

	public AnnualTargetEntryPermission(Long annualTargetEntryPermissionId, boolean annualTargetEntryPermissionAll,
			boolean isViewRequired, boolean isAddOrUploadRequired, boolean isReuploadOrEditRequired,
			boolean isDeleteRequired, List<SettingsPermission> settingsPermission) {
		super();
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
		this.annualTargetEntryPermissionAll = annualTargetEntryPermissionAll;
		this.isViewRequired = isViewRequired;
		this.isAddOrUploadRequired = isAddOrUploadRequired;
		this.isReuploadOrEditRequired = isReuploadOrEditRequired;
		this.isDeleteRequired = isDeleteRequired;
		this.settingsPermission = settingsPermission;
	}

	public Long getAnnualTargetEntryPermissionId() {
		return annualTargetEntryPermissionId;
	}

	public void setAnnualTargetEntryPermissionId(Long annualTargetEntryPermissionId) {
		this.annualTargetEntryPermissionId = annualTargetEntryPermissionId;
	}

	public boolean isAnnualTargetEntryPermissionAll() {
		return annualTargetEntryPermissionAll;
	}

	public void setAnnualTargetEntryPermissionAll(boolean annualTargetEntryPermissionAll) {
		this.annualTargetEntryPermissionAll = annualTargetEntryPermissionAll;
	}

	public boolean isViewRequired() {
		return isViewRequired;
	}

	public void setViewRequired(boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public boolean isAddOrUploadRequired() {
		return isAddOrUploadRequired;
	}

	public void setAddOrUploadRequired(boolean isAddOrUploadRequired) {
		this.isAddOrUploadRequired = isAddOrUploadRequired;
	}

	public boolean isReuploadOrEditRequired() {
		return isReuploadOrEditRequired;
	}

	public void setReuploadOrEditRequired(boolean isReuploadOrEditRequired) {
		this.isReuploadOrEditRequired = isReuploadOrEditRequired;
	}

	public boolean isDeleteRequired() {
		return isDeleteRequired;
	}

	public void setDeleteRequired(boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	public List<SettingsPermission> getSettingsPermission() {
		return settingsPermission;
	}

	public void setSettingsPermission(List<SettingsPermission> settingsPermission) {
		this.settingsPermission = settingsPermission;
	}

}
