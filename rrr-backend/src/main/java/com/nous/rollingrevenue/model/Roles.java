package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
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
import lombok.Data;

@Entity
@Table(name = "Roles")
@EntityListeners(AuditingEntityListener.class)
@Data
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

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "dashboard_permission_id", referencedColumnName = "dashboard_permission_id")
	private DashboardPermission dashboardPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "revenue_permission_id", referencedColumnName = "revenue_permission_id")
	private RevenuePermission revenuePermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "reports_permission_id", referencedColumnName = "reports_permission_id")
	private ReportsPermission reportsPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "settings_permission_id", referencedColumnName = "settings_permission_id")
	private SettingsPermission settingsPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "calendar_permission_id", referencedColumnName = "calendar_permission_id")
	private CalendarPermission calendarPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "administration_permission_id", referencedColumnName = "administration_permission_id")
	private AdministrationPermission administrationPermission;

}
