package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class RolesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private String roleName;

	private String roleDisplayName;

	// TODO: Need to check with FrontEnd Team
	private boolean selectAllPermissions;

	private DashboardPermissionVO dashboardPermissionVO;

	private RevenuePermissionVO revenuePermissionVO;

	private ReportsPermissionVO reportsPermissionVO;

	private SettingsPermissionVO settingsPermissionVO;

	private CalendarPermissionVO calendarPermissionVO;

	private AdministrationPermissionVO administrationPermissionVO;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDisplayName() {
		return roleDisplayName;
	}

	public void setRoleDisplayName(String roleDisplayName) {
		this.roleDisplayName = roleDisplayName;
	}

	public boolean isSelectAllPermissions() {
		return selectAllPermissions;
	}

	public void setSelectAllPermissions(boolean selectAllPermissions) {
		this.selectAllPermissions = selectAllPermissions;
	}

	public DashboardPermissionVO getDashboardPermissionVO() {
		return dashboardPermissionVO;
	}

	public void setDashboardPermissionVO(DashboardPermissionVO dashboardPermissionVO) {
		this.dashboardPermissionVO = dashboardPermissionVO;
	}

	public RevenuePermissionVO getRevenuePermissionVO() {
		return revenuePermissionVO;
	}

	public void setRevenuePermissionVO(RevenuePermissionVO revenuePermissionVO) {
		this.revenuePermissionVO = revenuePermissionVO;
	}

	public ReportsPermissionVO getReportsPermissionVO() {
		return reportsPermissionVO;
	}

	public void setReportsPermissionVO(ReportsPermissionVO reportsPermissionVO) {
		this.reportsPermissionVO = reportsPermissionVO;
	}

	public SettingsPermissionVO getSettingsPermissionVO() {
		return settingsPermissionVO;
	}

	public void setSettingsPermissionVO(SettingsPermissionVO settingsPermissionVO) {
		this.settingsPermissionVO = settingsPermissionVO;
	}

	public CalendarPermissionVO getCalendarPermissionVO() {
		return calendarPermissionVO;
	}

	public void setCalendarPermissionVO(CalendarPermissionVO calendarPermissionVO) {
		this.calendarPermissionVO = calendarPermissionVO;
	}

	public AdministrationPermissionVO getAdministrationPermissionVO() {
		return administrationPermissionVO;
	}

	public void setAdministrationPermissionVO(AdministrationPermissionVO administrationPermissionVO) {
		this.administrationPermissionVO = administrationPermissionVO;
	}

}
