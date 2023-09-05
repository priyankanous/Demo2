package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class ReportsCommonPermissionVO implements Serializable {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long reportsCommonPermissionId;

	@NotNull(message = "isViewAllDataRequired cannot be null or empty")
	private Boolean isViewAllDataRequired;

	@NotNull(message = "isViewRequired cannot be null or empty")
	private Boolean isViewRequired;

	@NotNull(message = "isPrintRequired cannot be null or empty")
	private Boolean isPrintRequired;

	@NotNull(message = "isSaveReportViewRequired cannot be null or empty")
	private Boolean isSaveReportViewRequired;

	@NotNull(message = "isExportRequired cannot be null or empty")
	private Boolean isExportRequired;

	@NotNull(message = "isMailRequired cannot be null or empty")
	private Boolean isMailRequired;

	private boolean isActive;

	public ReportsCommonPermissionVO() {

	}

	public ReportsCommonPermissionVO(Long reportsCommonPermissionId, Boolean isViewAllDataRequired,
			Boolean isViewRequired, Boolean isPrintRequired, Boolean isSaveReportViewRequired, Boolean isExportRequired,
			Boolean isMailRequired, boolean isActive) {
		super();
		this.reportsCommonPermissionId = reportsCommonPermissionId;
		this.isViewAllDataRequired = isViewAllDataRequired;
		this.isViewRequired = isViewRequired;
		this.isPrintRequired = isPrintRequired;
		this.isSaveReportViewRequired = isSaveReportViewRequired;
		this.isExportRequired = isExportRequired;
		this.isMailRequired = isMailRequired;
		this.isActive = isActive;
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
	
	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
