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

	private boolean readDashboardRequired;

	private boolean editDashboardRequired;

	private boolean isActive;

	public DashboardPermissionVO() {

	}

	public DashboardPermissionVO(Long dashboardPermissionId, boolean dashboardPermissionAll,
			boolean readDashboardRequired, boolean editDashboardRequired, boolean isActive) {
		super();
		this.dashboardPermissionId = dashboardPermissionId;
		this.dashboardPermissionAll = dashboardPermissionAll;
		this.readDashboardRequired = readDashboardRequired;
		this.editDashboardRequired = editDashboardRequired;
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
		return readDashboardRequired;
	}

	public void setReadDashboardRequired(boolean readDashboardRequired) {
		this.readDashboardRequired = readDashboardRequired;
	}

	public boolean isEditDashboardRequired() {
		return editDashboardRequired;
	}

	public void setEditDashboardRequired(boolean editDashboardRequired) {
		this.editDashboardRequired = editDashboardRequired;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
