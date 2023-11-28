package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class RolesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String roleDisplayName;

	private boolean isActive;

	// TODO: Need to check with FrontEnd Team
	private boolean selectAllPermissions;

	private DashboardPermissionVO dashboardPermissionVO;

	private RevenuePermissionVO revenuePermissionVO;

	private ReportsPermissionVO reportsPermissionVO;

	private SettingsPermissionVO settingsPermissionVO;

	private CalendarPermissionVO calendarPermissionVO;

	private AdministrationPermissionVO administrationPermissionVO;

}
