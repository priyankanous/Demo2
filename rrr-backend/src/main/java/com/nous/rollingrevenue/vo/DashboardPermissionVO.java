package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DashboardPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long dashboardPermissionId;

	private boolean dashboardPermissionAll;

	private boolean isReadDashboardRequired;

	private boolean isEditDashboardRequired;

	private boolean isActive;

	public DashboardPermissionVO() {

	}

	public DashboardPermissionVO(Long dashboardPermissionId, boolean dashboardPermissionAll,
			boolean isReadDashboardRequired, boolean isEditDashboardRequired, boolean isActive) {
		super();
		this.dashboardPermissionId = dashboardPermissionId;
		this.dashboardPermissionAll = dashboardPermissionAll;
		this.isReadDashboardRequired = isReadDashboardRequired;
		this.isEditDashboardRequired = isEditDashboardRequired;
		this.isActive = isActive;
	}

	public Long getDashboardPermissionId() {
		return dashboardPermissionId;
	}

	public void setDashboardPermissionId(Long dashboardPermissionId) {
		this.dashboardPermissionId = dashboardPermissionId;
	}

	public boolean isDashboardPermissionAll() {
		return dashboardPermissionAll;
	}

	public void setDashboardPermissionAll(boolean dashboardPermissionAll) {
		this.dashboardPermissionAll = dashboardPermissionAll;
	}

	public boolean isReadDashboardRequired() {
		return isReadDashboardRequired;
	}

	public void setReadDashboardRequired(boolean isReadDashboardRequired) {
		this.isReadDashboardRequired = isReadDashboardRequired;
	}

	public boolean isEditDashboardRequired() {
		return isEditDashboardRequired;
	}

	public void setEditDashboardRequired(boolean isEditDashboardRequired) {
		this.isEditDashboardRequired = isEditDashboardRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
