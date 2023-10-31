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
@Table(name = "probability_type_view_permission")
@EntityListeners(AuditingEntityListener.class)
public class ProbabilityTypeViewPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "probability_type_view_permission_id")
	private Long probabilityTypeViewPermissionId;

	@Column(name = "probability_type_view_permission_all")
	private boolean isProbabilityTypeViewPermissionAll;

	@OneToMany(mappedBy = "probabilityTypeViewPermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

	public ProbabilityTypeViewPermission() {

	}

	public ProbabilityTypeViewPermission(Long probabilityTypeViewPermissionId,
			boolean isProbabilityTypeViewPermissionAll, List<ReportsPermission> reportsPermission,
			ReportsCommonPermission reportsCommonPermission) {
		super();
		this.probabilityTypeViewPermissionId = probabilityTypeViewPermissionId;
		this.isProbabilityTypeViewPermissionAll = isProbabilityTypeViewPermissionAll;
		this.reportsPermission = reportsPermission;
		this.reportsCommonPermission = reportsCommonPermission;
	}

	public Long getProbabilityTypeViewPermissionId() {
		return probabilityTypeViewPermissionId;
	}

	public void setProbabilityTypeViewPermissionId(Long probabilityTypeViewPermissionId) {
		this.probabilityTypeViewPermissionId = probabilityTypeViewPermissionId;
	}

	public boolean isProbabilityTypeViewPermissionAll() {
		return isProbabilityTypeViewPermissionAll;
	}

	public void setProbabilityTypeViewPermissionAll(boolean isProbabilityTypeViewPermissionAll) {
		this.isProbabilityTypeViewPermissionAll = isProbabilityTypeViewPermissionAll;
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
