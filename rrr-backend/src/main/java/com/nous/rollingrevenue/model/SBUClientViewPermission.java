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
@Table(name = "sbu_client_view_permission")
@EntityListeners(AuditingEntityListener.class)
public class SBUClientViewPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_client_view_permission_id")
	private Long sbuClientViewPermissionId;

	@Column(name = "sbu_client_view_permission_all")
	private boolean isSbuClientViewPermissionAll;

	@OneToMany(mappedBy = "sbuClientViewPermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

	public SBUClientViewPermission() {

	}

	public SBUClientViewPermission(Long sbuClientViewPermissionId, boolean isSbuClientViewPermissionAll,
			List<ReportsPermission> reportsPermission, ReportsCommonPermission reportsCommonPermission) {
		super();
		this.sbuClientViewPermissionId = sbuClientViewPermissionId;
		this.isSbuClientViewPermissionAll = isSbuClientViewPermissionAll;
		this.reportsPermission = reportsPermission;
		this.reportsCommonPermission = reportsCommonPermission;
	}

	public Long getSbuClientViewPermissionId() {
		return sbuClientViewPermissionId;
	}

	public void setSbuClientViewPermissionId(Long sbuClientViewPermissionId) {
		this.sbuClientViewPermissionId = sbuClientViewPermissionId;
	}

	public boolean isSbuClientViewPermissionAll() {
		return isSbuClientViewPermissionAll;
	}

	public void setSbuClientViewPermissionAll(boolean isSbuClientViewPermissionAll) {
		this.isSbuClientViewPermissionAll = isSbuClientViewPermissionAll;
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
