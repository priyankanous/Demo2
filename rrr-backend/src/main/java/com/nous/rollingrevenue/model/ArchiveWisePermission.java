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
@Table(name = "archive_wise_permission")
@EntityListeners(AuditingEntityListener.class)
public class ArchiveWisePermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "archive_wise_permission_id")
	private Long archiveWisePermissionId;

	@Column(name = "archive_wise_permission_all")
	private Boolean isArchiveWisePermissionAll;

	@OneToMany(mappedBy = "archiveWisePermission")
	@JsonBackReference
	private List<ReportsPermission> reportsPermission = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "reports_common_permission_id", referencedColumnName = "reports_common_permission_id")
	private ReportsCommonPermission reportsCommonPermission;

	public ArchiveWisePermission() {

	}

	public ArchiveWisePermission(Long archiveWisePermissionId, Boolean isArchiveWisePermissionAll,
			List<ReportsPermission> reportsPermission, ReportsCommonPermission reportsCommonPermission) {
		super();
		this.archiveWisePermissionId = archiveWisePermissionId;
		this.isArchiveWisePermissionAll = isArchiveWisePermissionAll;
		this.reportsPermission = reportsPermission;
		this.reportsCommonPermission = reportsCommonPermission;
	}

	public Long getArchiveWisePermissionId() {
		return archiveWisePermissionId;
	}

	public void setArchiveWisePermissionId(Long archiveWisePermissionId) {
		this.archiveWisePermissionId = archiveWisePermissionId;
	}

	public Boolean getIsArchiveWisePermissionAll() {
		return isArchiveWisePermissionAll;
	}

	public void setIsArchiveWisePermissionAll(Boolean isArchiveWisePermissionAll) {
		this.isArchiveWisePermissionAll = isArchiveWisePermissionAll;
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
