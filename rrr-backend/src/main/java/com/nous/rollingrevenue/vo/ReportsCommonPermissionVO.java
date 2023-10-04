package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportsCommonPermissionVO implements Serializable {
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long reportsCommonPermissionId;

	private boolean viewAll;

	private boolean setFilter;

	private boolean export;

	private boolean isActive;

	public ReportsCommonPermissionVO() {

	}

	public ReportsCommonPermissionVO(Long reportsCommonPermissionId, boolean viewAll, boolean setFilter, boolean export,
			boolean isActive) {
		super();
		this.reportsCommonPermissionId = reportsCommonPermissionId;
		this.viewAll = viewAll;
		this.setFilter = setFilter;
		this.export = export;
		this.isActive = isActive;
	}

	public Long getReportsCommonPermissionId() {
		return reportsCommonPermissionId;
	}

	public void setReportsCommonPermissionId(Long reportsCommonPermissionId) {
		this.reportsCommonPermissionId = reportsCommonPermissionId;
	}

	public boolean isViewAll() {
		return viewAll;
	}

	public void setViewAll(boolean viewAll) {
		this.viewAll = viewAll;
	}

	public boolean isSetFilter() {
		return setFilter;
	}

	public void setSetFilter(boolean setFilter) {
		this.setFilter = setFilter;
	}

	public boolean isExport() {
		return export;
	}

	public void setExport(boolean export) {
		this.export = export;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
