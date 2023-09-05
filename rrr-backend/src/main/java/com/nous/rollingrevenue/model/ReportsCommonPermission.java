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
@Table(name = "reports_common_permission")
@EntityListeners(AuditingEntityListener.class)
public class ReportsCommonPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reports_common_permission_id")
	private Long reportsCommonPermissionId;

	@Column(name = "is_view_all_data")
	private Boolean isViewAllDataRequired;

	@Column(name = "is_view")
	private Boolean isViewRequired;

	@Column(name = "is_print")
	private Boolean isPrintRequired;

	@Column(name = "is_save_report_view")
	private Boolean isSaveReportViewRequired;

	@Column(name = "is_export")
	private Boolean isExportRequired;

	@Column(name = "is_mail")
	private Boolean isMailRequired;

	public ReportsCommonPermission() {

	}

	public ReportsCommonPermission(Long reportsCommonPermissionId, Boolean isViewAllDataRequired,
			Boolean isViewRequired, Boolean isPrintRequired, Boolean isSaveReportViewRequired, Boolean isExportRequired,
			Boolean isMailRequired) {
		super();
		this.reportsCommonPermissionId = reportsCommonPermissionId;
		this.isViewAllDataRequired = isViewAllDataRequired;
		this.isViewRequired = isViewRequired;
		this.isPrintRequired = isPrintRequired;
		this.isSaveReportViewRequired = isSaveReportViewRequired;
		this.isExportRequired = isExportRequired;
		this.isMailRequired = isMailRequired;
	}

	public Long getReportsCommonPermissionId() {
		return reportsCommonPermissionId;
	}

	public void setReportsCommonPermissionId(Long reportsCommonPermissionId) {
		this.reportsCommonPermissionId = reportsCommonPermissionId;
	}

	public Boolean getIsViewAllDataRequired() {
		return isViewAllDataRequired;
	}

	public void setIsViewAllDataRequired(Boolean isViewAllDataRequired) {
		this.isViewAllDataRequired = isViewAllDataRequired;
	}

	public Boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setIsViewRequired(Boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public Boolean getIsPrintRequired() {
		return isPrintRequired;
	}

	public void setIsPrintRequired(Boolean isPrintRequired) {
		this.isPrintRequired = isPrintRequired;
	}

	public Boolean getIsSaveReportViewRequired() {
		return isSaveReportViewRequired;
	}

	public void setIsSaveReportViewRequired(Boolean isSaveReportViewRequired) {
		this.isSaveReportViewRequired = isSaveReportViewRequired;
	}

	public Boolean getIsExportRequired() {
		return isExportRequired;
	}

	public void setIsExportRequired(Boolean isExportRequired) {
		this.isExportRequired = isExportRequired;
	}

	public Boolean getIsMailRequired() {
		return isMailRequired;
	}

	public void setIsMailRequired(Boolean isMailRequired) {
		this.isMailRequired = isMailRequired;
	}

}
