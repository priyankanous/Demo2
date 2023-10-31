package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "work_order_permission")
@EntityListeners(AuditingEntityListener.class)
public class WorkOrderPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_order_permission_id")
	private Long workOrderPermissionId;

	@Column(name = "work_order_permission_all")
	private boolean isWorkOrderPermissionAll;

	@OneToMany(mappedBy = "workOrderPermission")
	@JsonBackReference
	private List<AdministrationPermission> administrationPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "administration_common_permission_id", referencedColumnName = "administration_common_permission_id")
	private AdministrationCommonPermission administrationCommonPermission;

	public WorkOrderPermission() {

	}

	public WorkOrderPermission(Long workOrderPermissionId, boolean isWorkOrderPermissionAll,
			List<AdministrationPermission> administrationPermission,
			AdministrationCommonPermission administrationCommonPermission) {
		super();
		this.workOrderPermissionId = workOrderPermissionId;
		this.isWorkOrderPermissionAll = isWorkOrderPermissionAll;
		this.administrationPermission = administrationPermission;
		this.administrationCommonPermission = administrationCommonPermission;
	}

	public Long getWorkOrderPermissionId() {
		return workOrderPermissionId;
	}

	public void setWorkOrderPermissionId(Long workOrderPermissionId) {
		this.workOrderPermissionId = workOrderPermissionId;
	}

	public boolean isWorkOrderPermissionAll() {
		return isWorkOrderPermissionAll;
	}

	public void setWorkOrderPermissionAll(boolean isWorkOrderPermissionAll) {
		this.isWorkOrderPermissionAll = isWorkOrderPermissionAll;
	}

	public List<AdministrationPermission> getAdministrationPermission() {
		return administrationPermission;
	}

	public void setAdministrationPermission(List<AdministrationPermission> administrationPermission) {
		this.administrationPermission = administrationPermission;
	}

	public AdministrationCommonPermission getAdministrationCommonPermission() {
		return administrationCommonPermission;
	}

	public void setAdministrationCommonPermission(AdministrationCommonPermission administrationCommonPermission) {
		this.administrationCommonPermission = administrationCommonPermission;
	}

}
