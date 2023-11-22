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
import lombok.Data;

@Entity
@Table(name = "reports_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ReportsPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reports_permission_id")
	private Long reportsPermissionId;

	@Column(name = "reports_permission_all")
	private boolean isReportsPermissionAll;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "business_type_view_permission_id", referencedColumnName = "business_type_view_permission_id")
	private BusinessTypeViewPermission businessTypeViewPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "sbu_client_view_permission_id", referencedColumnName = "sbu_client_view_permission_id")
	private SBUClientViewPermission sbuClientViewPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "probability_type_view_permission_id", referencedColumnName = "probability_type_view_permission_id")
	private ProbabilityTypeViewPermission probabilityTypeViewPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "region_wise_view_permission_id", referencedColumnName = "region_wise_view_permission_id")
	private RegionWiseViewPermission regionWiseViewPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "business_unit_wise_view_permission_id", referencedColumnName = "business_unit_wise_view_permission_id")
	private BusinessUnitWiseViewPermission businessUnitWiseViewPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "client_wise_View_permission_id", referencedColumnName = "client_wise_View_permission_id")
	private ClientWiseViewPermission clientWiseViewPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "archive_wise_permission_id", referencedColumnName = "archive_wise_permission_id")
	private ArchiveWisePermission archiveWisePermission;

	@OneToMany(mappedBy = "reportsPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

}
