package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DashboardPermission")
@EntityListeners(AuditingEntityListener.class)
public class DashboardPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dashboard_permission_id")
	private Long dashboardPermissionId;

	@Column(name = "is_read_dashboard")
	private boolean isReadDashboardRequired;

	@Column(name = "is_edit_dashboard")
	private boolean isEditDashboardRequired;

	public DashboardPermission() {

	}

	public DashboardPermission(Long dashboardPermissionId, boolean isReadDashboardRequired,
			boolean isEditDashboardRequired) {
		super();
		this.dashboardPermissionId = dashboardPermissionId;
		this.isReadDashboardRequired = isReadDashboardRequired;
		this.isEditDashboardRequired = isEditDashboardRequired;
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

}
