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
@Table(name = "region_wise_view_permission")
@EntityListeners(AuditingEntityListener.class)
public class RegionWiseViewPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_wise_view_permission_id")
	private Long regionWiseViewPermissionId;

	@Column(name = "region_wise_view_permission_all")
	private boolean isRegionWiseViewPermissionAll;

	@OneToMany(mappedBy = "regionWiseViewPermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

	public RegionWiseViewPermission() {

	}

	public RegionWiseViewPermission(Long regionWiseViewPermissionId, boolean isRegionWiseViewPermissionAll,
			List<ReportsPermission> reportsPermission, ReportsCommonPermission reportsCommonPermission) {
		super();
		this.regionWiseViewPermissionId = regionWiseViewPermissionId;
		this.isRegionWiseViewPermissionAll = isRegionWiseViewPermissionAll;
		this.reportsPermission = reportsPermission;
		this.reportsCommonPermission = reportsCommonPermission;
	}

	public Long getRegionWiseViewPermissionId() {
		return regionWiseViewPermissionId;
	}

	public void setRegionWiseViewPermissionId(Long regionWiseViewPermissionId) {
		this.regionWiseViewPermissionId = regionWiseViewPermissionId;
	}

	public boolean isRegionWiseViewPermissionAll() {
		return isRegionWiseViewPermissionAll;
	}

	public void setRegionWiseViewPermissionAll(boolean isRegionWiseViewPermissionAll) {
		this.isRegionWiseViewPermissionAll = isRegionWiseViewPermissionAll;
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
