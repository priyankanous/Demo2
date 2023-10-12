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

@Entity
@Table(name = "reports_common_permission")
@EntityListeners(AuditingEntityListener.class)
public class ReportsCommonPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reports_common_permission_id")
	private Long reportsCommonPermissionId;

	@Column(name = "view_all")
	private boolean isViewAllDataRequired;

	@Column(name = "set_filter")
	private boolean isSetFilterRequired;

	@Column(name = "export")
	private boolean isExportRequired;

	@OneToMany(mappedBy = "reportsCommonPermission")
	@JsonBackReference
	private List<BusinessTypeViewPermission> businessTypeViewPermission = new ArrayList<>();

	@OneToMany(mappedBy = "reportsCommonPermission")
	@JsonBackReference
	private List<SBUClientViewPermission> sbuClientViewPermission = new ArrayList<>();

	@OneToMany(mappedBy = "reportsCommonPermission")
	@JsonBackReference
	private List<ProbabilityTypeViewPermission> probabilityTypeViewPermission = new ArrayList<>();

	@OneToMany(mappedBy = "reportsCommonPermission")
	@JsonBackReference
	private List<RegionWiseViewPermission> regionWiseViewPermission = new ArrayList<>();

	@OneToMany(mappedBy = "reportsCommonPermission")
	@JsonBackReference
	private List<BusinessUnitWiseViewPermission> businessUnitWiseViewPermission = new ArrayList<>();

	@OneToMany(mappedBy = "reportsCommonPermission")
	@JsonBackReference
	private List<ClientWiseViewPermission> clientWiseViewPermission = new ArrayList<>();

	@OneToMany(mappedBy = "reportsCommonPermission")
	@JsonBackReference
	private List<ArchiveWisePermission> archiveWisePermission = new ArrayList<>();

	public ReportsCommonPermission() {

	}

	public ReportsCommonPermission(Long reportsCommonPermissionId, boolean isViewAllDataRequired,
			boolean isSetFilterRequired, boolean isExportRequired,
			List<BusinessTypeViewPermission> businessTypeViewPermission,
			List<SBUClientViewPermission> sbuClientViewPermission,
			List<ProbabilityTypeViewPermission> probabilityTypeViewPermission,
			List<RegionWiseViewPermission> regionWiseViewPermission,
			List<BusinessUnitWiseViewPermission> businessUnitWiseViewPermission,
			List<ClientWiseViewPermission> clientWiseViewPermission,
			List<ArchiveWisePermission> archiveWisePermission) {
		super();
		this.reportsCommonPermissionId = reportsCommonPermissionId;
		this.isViewAllDataRequired = isViewAllDataRequired;
		this.isSetFilterRequired = isSetFilterRequired;
		this.isExportRequired = isExportRequired;
		this.businessTypeViewPermission = businessTypeViewPermission;
		this.sbuClientViewPermission = sbuClientViewPermission;
		this.probabilityTypeViewPermission = probabilityTypeViewPermission;
		this.regionWiseViewPermission = regionWiseViewPermission;
		this.businessUnitWiseViewPermission = businessUnitWiseViewPermission;
		this.clientWiseViewPermission = clientWiseViewPermission;
		this.archiveWisePermission = archiveWisePermission;
	}

	public Long getReportsCommonPermissionId() {
		return reportsCommonPermissionId;
	}

	public void setReportsCommonPermissionId(Long reportsCommonPermissionId) {
		this.reportsCommonPermissionId = reportsCommonPermissionId;
	}

	public boolean isViewAllDataRequired() {
		return isViewAllDataRequired;
	}

	public void setViewAllDataRequired(boolean isViewAllDataRequired) {
		this.isViewAllDataRequired = isViewAllDataRequired;
	}

	public boolean isSetFilterRequired() {
		return isSetFilterRequired;
	}

	public void setSetFilterRequired(boolean isSetFilterRequired) {
		this.isSetFilterRequired = isSetFilterRequired;
	}

	public boolean isExportRequired() {
		return isExportRequired;
	}

	public void setExportRequired(boolean isExportRequired) {
		this.isExportRequired = isExportRequired;
	}

	public List<BusinessTypeViewPermission> getBusinessTypeViewPermission() {
		return businessTypeViewPermission;
	}

	public void setBusinessTypeViewPermission(List<BusinessTypeViewPermission> businessTypeViewPermission) {
		this.businessTypeViewPermission = businessTypeViewPermission;
	}

	public List<SBUClientViewPermission> getSbuClientViewPermission() {
		return sbuClientViewPermission;
	}

	public void setSbuClientViewPermission(List<SBUClientViewPermission> sbuClientViewPermission) {
		this.sbuClientViewPermission = sbuClientViewPermission;
	}

	public List<ProbabilityTypeViewPermission> getProbabilityTypeViewPermission() {
		return probabilityTypeViewPermission;
	}

	public void setProbabilityTypeViewPermission(List<ProbabilityTypeViewPermission> probabilityTypeViewPermission) {
		this.probabilityTypeViewPermission = probabilityTypeViewPermission;
	}

	public List<RegionWiseViewPermission> getRegionWiseViewPermission() {
		return regionWiseViewPermission;
	}

	public void setRegionWiseViewPermission(List<RegionWiseViewPermission> regionWiseViewPermission) {
		this.regionWiseViewPermission = regionWiseViewPermission;
	}

	public List<BusinessUnitWiseViewPermission> getBusinessUnitWiseViewPermission() {
		return businessUnitWiseViewPermission;
	}

	public void setBusinessUnitWiseViewPermission(List<BusinessUnitWiseViewPermission> businessUnitWiseViewPermission) {
		this.businessUnitWiseViewPermission = businessUnitWiseViewPermission;
	}

	public List<ClientWiseViewPermission> getClientWiseViewPermission() {
		return clientWiseViewPermission;
	}

	public void setClientWiseViewPermission(List<ClientWiseViewPermission> clientWiseViewPermission) {
		this.clientWiseViewPermission = clientWiseViewPermission;
	}

	public List<ArchiveWisePermission> getArchiveWisePermission() {
		return archiveWisePermission;
	}

	public void setArchiveWisePermission(List<ArchiveWisePermission> archiveWisePermission) {
		this.archiveWisePermission = archiveWisePermission;
	}

}
