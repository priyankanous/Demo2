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
@Table(name = "administration_common_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class AdministrationCommonPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "administration_common_permission_id")
	private Long administrationCommonPermissionId;

	@Column(name = "view")
	private boolean isViewRequired;

	@Column(name = "add")
	private boolean isAddRequired;

	@Column(name = "edit")
	private boolean isEditRequired;

	@Column(name = "activate_or_deactivate")
	private boolean isActivateOrDeactivateRequired;

	@Column(name = "delete")
	private boolean isDeleteRequired;

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<AccountPermission> accountPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<OpportunityPermission> opportunityPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<BusinessUnitPermission> businessUnitPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<RegionPermission> regionPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<SBUPermission> sbuPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<SBUHeadPermission> sbuHeadPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<LocationPermission> locationPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<BDMPermission> bdmPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<ProbabilityTypePermission> probabilityTypePermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<BusinessTypePermission> businessTypePermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<COCPracticePermission> cocPracticePermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<PricingTypePermission> pricingTypePermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<BillingTypePermission> billingTypePermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<WorkOrderPermission> workOrderPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<FinancialYearPermission> financialYearPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<GlobalLeaveLossFactorPermission> globalLeaveLossFactorPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<CurrencyPermission> currencyPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<NotificationConfigurationPermission> notificationConfigurationPermission = new ArrayList<>();

}
