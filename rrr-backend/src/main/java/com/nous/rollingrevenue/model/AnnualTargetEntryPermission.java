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
	private Boolean annualTargetEntryPermissionAll;

	@Column(name = "view")
	private Boolean isViewRequired;

	@Column(name = "add_or_upload")
	private Boolean isAddOrUploadRequired;

	@Column(name = "reupload_or_edit")
	private Boolean isReuploadOrEditRequired;

	@Column(name = "delete")
	private Boolean isDeleteRequired;

	@OneToMany(mappedBy = "annualTargetEntryPermission")
	@JsonBackReference
	private List<SettingsPermission> settingsPermission = new ArrayList<>();

	public AnnualTargetEntryPermission() {

	}

	public AnnualTargetEntryPermission(Long annualTargetEntryPermissionId, Boolean annualTargetEntryPermissionAll,
			Boolean isViewRequired, Boolean isAddOrUploadRequired, Boolean isReuploadOrEditRequired,
			Boolean isDeleteRequired, List<SettingsPermission> settingsPermission) {
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

	public Boolean getAnnualTargetEntryPermissionAll() {
		return annualTargetEntryPermissionAll;
	}

	public void setAnnualTargetEntryPermissionAll(Boolean annualTargetEntryPermissionAll) {
		this.annualTargetEntryPermissionAll = annualTargetEntryPermissionAll;
	}

	public Boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setIsViewRequired(Boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public Boolean getIsAddOrUploadRequired() {
		return isAddOrUploadRequired;
	}

	public void setIsAddOrUploadRequired(Boolean isAddOrUploadRequired) {
		this.isAddOrUploadRequired = isAddOrUploadRequired;
	}

	public Boolean getIsReuploadOrEditRequired() {
		return isReuploadOrEditRequired;
	}

	public void setIsReuploadOrEditRequired(Boolean isReuploadOrEditRequired) {
		this.isReuploadOrEditRequired = isReuploadOrEditRequired;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	public List<SettingsPermission> getSettingsPermission() {
		return settingsPermission;
	}

	public void setSettingsPermission(List<SettingsPermission> settingsPermission) {
		this.settingsPermission = settingsPermission;
	}

}
