package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Roles")
@EntityListeners(AuditingEntityListener.class)
public class Roles extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_display_name")
	private String roleDisplayName;

	@Column(name = "select_all")
	private boolean selectAll;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "dashboard_permission_id", referencedColumnName = "dashboard_permission_id")
	private DashboardPermission dashboardPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "revenue_permission_id", referencedColumnName = "revenue_permission_id")
	private RevenuePermission revenuePermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "reports_permission_id", referencedColumnName = "reports_permission_id")
	private ReportsPermission reportsPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "settings_permission_id", referencedColumnName = "settings_permission_id")
	private SettingsPermission settingsPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "calendar_permission_id", referencedColumnName = "calendar_permission_id")
	private CalendarPermission calendarPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "administration_permission_id", referencedColumnName = "administration_permission_id")
	private AdministrationPermission administrationPermission;

	public Roles() {

	}

	public Roles(Long roleId, String roleName, String roleDisplayName, boolean selectAll,
			DashboardPermission dashboardPermission, RevenuePermission revenuePermission,
			ReportsPermission reportsPermission, SettingsPermission settingsPermission,
			CalendarPermission calendarPermission, AdministrationPermission administrationPermission) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleDisplayName = roleDisplayName;
		this.selectAll = selectAll;
		this.dashboardPermission = dashboardPermission;
		this.revenuePermission = revenuePermission;
		this.reportsPermission = reportsPermission;
		this.settingsPermission = settingsPermission;
		this.calendarPermission = calendarPermission;
		this.administrationPermission = administrationPermission;
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

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public DashboardPermission getDashboardPermission() {
		return dashboardPermission;
	}

	public void setDashboardPermission(DashboardPermission dashboardPermission) {
		this.dashboardPermission = dashboardPermission;
	}

	public RevenuePermission getRevenuePermission() {
		return revenuePermission;
	}

	public void setRevenuePermission(RevenuePermission revenuePermission) {
		this.revenuePermission = revenuePermission;
	}

	public ReportsPermission getReportsPermission() {
		return reportsPermission;
	}

	public void setReportsPermission(ReportsPermission reportsPermission) {
		this.reportsPermission = reportsPermission;
	}

	public SettingsPermission getSettingsPermission() {
		return settingsPermission;
	}

	public void setSettingsPermission(SettingsPermission settingsPermission) {
		this.settingsPermission = settingsPermission;
	}

	public CalendarPermission getCalendarPermission() {
		return calendarPermission;
	}

	public void setCalendarPermission(CalendarPermission calendarPermission) {
		this.calendarPermission = calendarPermission;
	}

	public AdministrationPermission getAdministrationPermission() {
		return administrationPermission;
	}

	public void setAdministrationPermission(AdministrationPermission administrationPermission) {
		this.administrationPermission = administrationPermission;
	}

}
