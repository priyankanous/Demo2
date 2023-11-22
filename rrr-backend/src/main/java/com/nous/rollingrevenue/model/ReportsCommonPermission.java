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
import lombok.Data;

@Entity
@Table(name = "reports_common_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
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

}
