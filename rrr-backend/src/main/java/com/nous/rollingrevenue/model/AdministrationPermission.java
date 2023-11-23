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
@Table(name = "administration_permission")
@EntityListeners(AuditingEntityListener.class)
@Data
public class AdministrationPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "administration_permission_id")
	private Long administrationPermissionId;

	@Column(name = "administration_permission_all")
	private boolean isAdministrationPermissionAll;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "account_permission_id", referencedColumnName = "account_permission_id")
	private AccountPermission accountPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "opportunity_permission_id", referencedColumnName = "opportunity_permission_id")
	private OpportunityPermission opportunityPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "business_unit_permission_id", referencedColumnName = "business_unit_permission_id")
	private BusinessUnitPermission businessUnitPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "region_permission_id", referencedColumnName = "region_permission_id")
	private RegionPermission regionPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "sbu_permission_id", referencedColumnName = "sbu_permission_id")
	private SBUPermission sbuPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "sbu_head_permission_id", referencedColumnName = "sbu_head_permission_id")
	private SBUHeadPermission sbuHeadPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "location_permission_id", referencedColumnName = "location_permission_id")
	private LocationPermission locationPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "bdm_permission_id", referencedColumnName = "bdm_permission_id")
	private BDMPermission bdmPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "probability_type_permission_id", referencedColumnName = "probability_type_permission_id")
	private ProbabilityTypePermission probabilityTypePermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "business_type_permission_id", referencedColumnName = "business_type_permission_id")
	private BusinessTypePermission businessTypePermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "coc_practice_permission_id", referencedColumnName = "coc_practice_permission_id")
	private COCPracticePermission cocPracticePermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "pricing_type_permission_id", referencedColumnName = "pricing_type_permission_id")
	private PricingTypePermission pricingTypePermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "billing_type_permission_id", referencedColumnName = "billing_type_permission_id")
	private BillingTypePermission billingTypePermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "work_order_permission_id", referencedColumnName = "work_order_permission_id")
	private WorkOrderPermission workOrderPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "financial_year_permission_id", referencedColumnName = "financial_year_permission_id")
	private FinancialYearPermission financialYearPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "currency_permission_id", referencedColumnName = "currency_permission_id")
	private CurrencyPermission currencyPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "notification_configuration_permission_id", referencedColumnName = "notification_configuration_permission_id")
	private NotificationConfigurationPermission notificationConfigurationPermission;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "global_leave_loss_factor_permission_id", referencedColumnName = "global_leave_loss_factor_permission_id")
	private GlobalLeaveLossFactorPermission globalLeaveLossFactorPermission;

	@OneToMany(mappedBy = "administrationPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

}
