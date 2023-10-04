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
@Table(name = "reports_permission")
@EntityListeners(AuditingEntityListener.class)
public class ReportsPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reports_permission_id")
	private Long reportsPermissionId;

	@Column(name = "reports_permission_all")
	private Boolean isReportsPermissionAll;

	@Column(name = "view_all_entries")
	private Boolean isViewAllEntriesRequired;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "business_type_view_permission_id", referencedColumnName = "business_type_view_permission_id")
	private BusinessTypeViewPermission businessTypeViewPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_client_view_permission_id", referencedColumnName = "sbu_client_view_permission_id")
	private SBUClientViewPermission sbuClientViewPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "probability_type_view_permission_id", referencedColumnName = "probability_type_view_permission_id")
	private ProbabilityTypeViewPermission probabilityTypeViewPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_wise_view_permission_id", referencedColumnName = "region_wise_view_permission_id")
	private RegionWiseViewPermission regionWiseViewPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "business_unit_wise_view_permission_id", referencedColumnName = "business_unit_wise_view_permission_id")
	private BusinessUnitWiseViewPermission businessUnitWiseViewPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "client_wise_View_permission_id", referencedColumnName = "client_wise_View_permission_id")
	private ClientWiseViewPermission clientWiseViewPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "archive_wise_permission_id", referencedColumnName = "archive_wise_permission_id")
	private ArchiveWisePermission archiveWisePermission;

	@OneToMany(mappedBy = "reportsPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

	public ReportsPermission() {

	}

	public ReportsPermission(Long reportsPermissionId, Boolean isReportsPermissionAll, Boolean isViewAllEntriesRequired,
			BusinessTypeViewPermission businessTypeViewPermission, SBUClientViewPermission sbuClientViewPermission,
			ProbabilityTypeViewPermission probabilityTypeViewPermission,
			RegionWiseViewPermission regionWiseViewPermission,
			BusinessUnitWiseViewPermission businessUnitWiseViewPermission,
			ClientWiseViewPermission clientWiseViewPermission, ArchiveWisePermission archiveWisePermission,
			List<Roles> roles) {
		super();
		this.reportsPermissionId = reportsPermissionId;
		this.isReportsPermissionAll = isReportsPermissionAll;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.businessTypeViewPermission = businessTypeViewPermission;
		this.sbuClientViewPermission = sbuClientViewPermission;
		this.probabilityTypeViewPermission = probabilityTypeViewPermission;
		this.regionWiseViewPermission = regionWiseViewPermission;
		this.businessUnitWiseViewPermission = businessUnitWiseViewPermission;
		this.clientWiseViewPermission = clientWiseViewPermission;
		this.archiveWisePermission = archiveWisePermission;
		this.roles = roles;
	}

	public Long getReportsPermissionId() {
		return reportsPermissionId;
	}

	public void setReportsPermissionId(Long reportsPermissionId) {
		this.reportsPermissionId = reportsPermissionId;
	}

	public Boolean getIsReportsPermissionAll() {
		return isReportsPermissionAll;
	}

	public void setIsReportsPermissionAll(Boolean isReportsPermissionAll) {
		this.isReportsPermissionAll = isReportsPermissionAll;
	}

	public Boolean getIsViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setIsViewAllEntriesRequired(Boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public BusinessTypeViewPermission getBusinessTypeViewPermission() {
		return businessTypeViewPermission;
	}

	public void setBusinessTypeViewPermission(BusinessTypeViewPermission businessTypeViewPermission) {
		this.businessTypeViewPermission = businessTypeViewPermission;
	}

	public SBUClientViewPermission getSbuClientViewPermission() {
		return sbuClientViewPermission;
	}

	public void setSbuClientViewPermission(SBUClientViewPermission sbuClientViewPermission) {
		this.sbuClientViewPermission = sbuClientViewPermission;
	}

	public ProbabilityTypeViewPermission getProbabilityTypeViewPermission() {
		return probabilityTypeViewPermission;
	}

	public void setProbabilityTypeViewPermission(ProbabilityTypeViewPermission probabilityTypeViewPermission) {
		this.probabilityTypeViewPermission = probabilityTypeViewPermission;
	}

	public RegionWiseViewPermission getRegionWiseViewPermission() {
		return regionWiseViewPermission;
	}

	public void setRegionWiseViewPermission(RegionWiseViewPermission regionWiseViewPermission) {
		this.regionWiseViewPermission = regionWiseViewPermission;
	}

	public BusinessUnitWiseViewPermission getBusinessUnitWiseViewPermission() {
		return businessUnitWiseViewPermission;
	}

	public void setBusinessUnitWiseViewPermission(BusinessUnitWiseViewPermission businessUnitWiseViewPermission) {
		this.businessUnitWiseViewPermission = businessUnitWiseViewPermission;
	}

	public ClientWiseViewPermission getClientWiseViewPermission() {
		return clientWiseViewPermission;
	}

	public void setClientWiseViewPermission(ClientWiseViewPermission clientWiseViewPermission) {
		this.clientWiseViewPermission = clientWiseViewPermission;
	}

	public ArchiveWisePermission getArchiveWisePermission() {
		return archiveWisePermission;
	}

	public void setArchiveWisePermission(ArchiveWisePermission archiveWisePermission) {
		this.archiveWisePermission = archiveWisePermission;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}
