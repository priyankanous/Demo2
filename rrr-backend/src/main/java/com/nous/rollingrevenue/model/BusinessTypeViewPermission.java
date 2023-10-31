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
@Table(name = "business_type_view_permission")
@EntityListeners(AuditingEntityListener.class)
public class BusinessTypeViewPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_type_view_permission_id")
	private Long businessTypeViewPermissionId;

	@Column(name = "business_type_view_permission_all")
	private boolean isBusinessTypeViewPermissionAll;

	@OneToMany(mappedBy = "businessTypeViewPermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

	public BusinessTypeViewPermission() {

	}

	public BusinessTypeViewPermission(Long businessTypeViewPermissionId, boolean isBusinessTypeViewPermissionAll,
			List<ReportsPermission> reportsPermission, ReportsCommonPermission reportsCommonPermission) {
		super();
		this.businessTypeViewPermissionId = businessTypeViewPermissionId;
		this.isBusinessTypeViewPermissionAll = isBusinessTypeViewPermissionAll;
		this.reportsPermission = reportsPermission;
		this.reportsCommonPermission = reportsCommonPermission;
	}

	public Long getBusinessTypeViewPermissionId() {
		return businessTypeViewPermissionId;
	}

	public void setBusinessTypeViewPermissionId(Long businessTypeViewPermissionId) {
		this.businessTypeViewPermissionId = businessTypeViewPermissionId;
	}

	public boolean isBusinessTypeViewPermissionAll() {
		return isBusinessTypeViewPermissionAll;
	}

	public void setBusinessTypeViewPermissionAll(boolean isBusinessTypeViewPermissionAll) {
		this.isBusinessTypeViewPermissionAll = isBusinessTypeViewPermissionAll;
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
