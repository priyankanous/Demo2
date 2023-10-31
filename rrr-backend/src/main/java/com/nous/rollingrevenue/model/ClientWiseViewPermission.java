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
@Table(name = "client_wise_view_permission")
@EntityListeners(AuditingEntityListener.class)
public class ClientWiseViewPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "client_wise_View_permission_id")
	private Long clientWiseViewPermissionId;

	@Column(name = "client_wise_view_permission_all")
	private boolean isClientWiseViewPermissionAll;

	@OneToMany(mappedBy = "clientWiseViewPermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

	public ClientWiseViewPermission() {

	}

	public ClientWiseViewPermission(Long clientWiseViewPermissionId, boolean isClientWiseViewPermissionAll,
			List<ReportsPermission> reportsPermission, ReportsCommonPermission reportsCommonPermission) {
		super();
		this.clientWiseViewPermissionId = clientWiseViewPermissionId;
		this.isClientWiseViewPermissionAll = isClientWiseViewPermissionAll;
		this.reportsPermission = reportsPermission;
		this.reportsCommonPermission = reportsCommonPermission;
	}

	public Long getClientWiseViewPermissionId() {
		return clientWiseViewPermissionId;
	}

	public void setClientWiseViewPermissionId(Long clientWiseViewPermissionId) {
		this.clientWiseViewPermissionId = clientWiseViewPermissionId;
	}

	public boolean isClientWiseViewPermissionAll() {
		return isClientWiseViewPermissionAll;
	}

	public void setClientWiseViewPermissionAll(boolean isClientWiseViewPermissionAll) {
		this.isClientWiseViewPermissionAll = isClientWiseViewPermissionAll;
	}

	public List<ReportsPermission> getReportsPermission() {
		return reportsPermission;
	}

	public void setReportsPermission(List<ReportsPermission> reportsPermission) {
		this.reportsPermission = reportsPermission;
	}

	public ReportsCommonPermission getReportsCommonPermission() {
		return reportsCommonPermission;
	}

	public void setReportsCommonPermission(ReportsCommonPermission reportsCommonPermission) {
		this.reportsCommonPermission = reportsCommonPermission;
	}

}
