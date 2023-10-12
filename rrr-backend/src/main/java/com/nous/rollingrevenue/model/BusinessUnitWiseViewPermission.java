package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "business_unit_wise_view_permission")
@EntityListeners(AuditingEntityListener.class)
public class BusinessUnitWiseViewPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_unit_wise_view_permission_id")
	private Long businessUnitWiseViewPermissionId;

	@Column(name = "business_unit_wise_view_permission_all")
	private boolean isBusinessUnitWiseViewPermissionAll;

	@OneToMany(mappedBy = "businessUnitWiseViewPermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

	public BusinessUnitWiseViewPermission() {

	}

	public BusinessUnitWiseViewPermission(Long businessUnitWiseViewPermissionId,
			boolean isBusinessUnitWiseViewPermissionAll, List<ReportsPermission> reportsPermission,
			ReportsCommonPermission reportsCommonPermission) {
		super();
		this.businessUnitWiseViewPermissionId = businessUnitWiseViewPermissionId;
		this.isBusinessUnitWiseViewPermissionAll = isBusinessUnitWiseViewPermissionAll;
		this.reportsPermission = reportsPermission;
		this.reportsCommonPermission = reportsCommonPermission;
	}

	public Long getBusinessUnitWiseViewPermissionId() {
		return businessUnitWiseViewPermissionId;
	}

	public void setBusinessUnitWiseViewPermissionId(Long businessUnitWiseViewPermissionId) {
		this.businessUnitWiseViewPermissionId = businessUnitWiseViewPermissionId;
	}

	public boolean isBusinessUnitWiseViewPermissionAll() {
		return isBusinessUnitWiseViewPermissionAll;
	}

	public void setBusinessUnitWiseViewPermissionAll(boolean isBusinessUnitWiseViewPermissionAll) {
		this.isBusinessUnitWiseViewPermissionAll = isBusinessUnitWiseViewPermissionAll;
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
