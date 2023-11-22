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
import lombok.Data;

@Entity
@Table(name = "DashboardPermission")
@EntityListeners(AuditingEntityListener.class)
@Data
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

}
