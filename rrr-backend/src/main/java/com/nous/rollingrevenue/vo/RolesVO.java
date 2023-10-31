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

	private boolean isActive;

	// TODO: Need to check with FrontEnd Team
	private boolean selectAllPermissions;

	private DashboardPermissionVO dashboardPermissionVO;

	private RevenuePermissionVO revenuePermissionVO;

	private ReportsPermissionVO reportsPermissionVO;

	private SettingsPermissionVO settingsPermissionVO;

	private CalendarPermissionVO calendarPermissionVO;

	private AdministrationPermissionVO administrationPermissionVO;

	public RolesVO() {

	}

	public RolesVO(Long roleId, String roleName, String roleDisplayName, boolean isActive, boolean selectAllPermissions,
			DashboardPermissionVO dashboardPermissionVO, RevenuePermissionVO revenuePermissionVO,
			ReportsPermissionVO reportsPermissionVO, SettingsPermissionVO settingsPermissionVO,
			CalendarPermissionVO calendarPermissionVO, AdministrationPermissionVO administrationPermissionVO) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDisplayName = roleDisplayName;
		this.isActive = isActive;
		this.selectAllPermissions = selectAllPermissions;
		this.dashboardPermissionVO = dashboardPermissionVO;
		this.revenuePermissionVO = revenuePermissionVO;
		this.reportsPermissionVO = reportsPermissionVO;
		this.settingsPermissionVO = settingsPermissionVO;
		this.calendarPermissionVO = calendarPermissionVO;
		this.administrationPermissionVO = administrationPermissionVO;
	}

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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
