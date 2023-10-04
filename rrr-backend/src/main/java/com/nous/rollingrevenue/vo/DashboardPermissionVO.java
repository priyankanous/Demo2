package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;

public class DashboardPermissionVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long dashboardPermissionId;

	private boolean dashboardPermissionAll;

	@NotNull(message = "isReadDashboardRequired cannot be null or empty")
	private boolean isReadDashboardRequired;

	@NotNull(message = "isEditDashboardRequired cannot be null or empty")
	private boolean isEditDashboardRequired;

	private boolean isActive;

	public DashboardPermissionVO() {

	}

	public DashboardPermissionVO(Long dashboardPermissionId, boolean isReadDashboardRequired,
			boolean isEditDashboardRequired, boolean isActive) {
		super();
		this.dashboardPermissionId = dashboardPermissionId;
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

	public boolean getIsReadDashboardRequired() {
		return isReadDashboardRequired;
	}

	public void setIsReadDashboardRequired(boolean isReadDashboardRequired) {
		this.isReadDashboardRequired = isReadDashboardRequired;
	}

	public boolean getIsEditDashboardRequired() {
		return isEditDashboardRequired;
	}

	public void setIsEditDashboardRequired(boolean isEditDashboardRequired) {
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
