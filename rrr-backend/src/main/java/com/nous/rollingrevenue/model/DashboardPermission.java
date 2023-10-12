package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "DashboardPermission")
@EntityListeners(AuditingEntityListener.class)
public class DashboardPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dashboard_permission_id")
	private Long dashboardPermissionId;

	@Column(name = "dashboard_permission_all")
	private boolean dashboardPermissionAll;

	@Column(name = "read_dashboard")
	private boolean readDashboardRequired;

	@Column(name = "edit_dashboard")
	private boolean editDashboardRequired;

	@OneToMany(mappedBy = "dashboardPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

	public DashboardPermission() {

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

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public DashboardPermission(Long dashboardPermissionId, boolean dashboardPermissionAll,
			boolean readDashboardRequired, boolean editDashboardRequired, List<Roles> roles) {
		super();
		this.dashboardPermissionId = dashboardPermissionId;
		this.dashboardPermissionAll = dashboardPermissionAll;
		this.readDashboardRequired = readDashboardRequired;
		this.editDashboardRequired = editDashboardRequired;
		this.roles = roles;
	}

}
