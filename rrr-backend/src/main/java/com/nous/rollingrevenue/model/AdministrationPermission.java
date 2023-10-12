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
@Table(name = "administration_permission")
@EntityListeners(AuditingEntityListener.class)
public class AdministrationPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "administration_permission_id")
	private Long administrationPermissionId;

	@Column(name = "administration_permission_all")
	private boolean isAdministrationPermissionAll;

	@Column(name = "view_all_entries")
	private boolean isViewAllEntriesRequired;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "account_permission_id", referencedColumnName = "account_permission_id")
	private AccountPermission accountPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "opportunity_permission_id", referencedColumnName = "opportunity_permission_id")
	private OpportunityPermission opportunityPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "business_unit_permission_id", referencedColumnName = "business_unit_permission_id")
	private BusinessUnitPermission businessUnitPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_permission_id", referencedColumnName = "region_permission_id")
	private RegionPermission regionPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_permission_id", referencedColumnName = "sbu_permission_id")
	private SBUPermission sbuPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_head_permission_id", referencedColumnName = "sbu_head_permission_id")
	private SBUHeadPermission sbuHeadPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "location_permission_id", referencedColumnName = "location_permission_id")
	private LocationPermission locationPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bdm_permission_id", referencedColumnName = "bdm_permission_id")
	private BDMPermission bdmPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "probability_type_permission_id", referencedColumnName = "probability_type_permission_id")
	private ProbabilityTypePermission probabilityTypePermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "business_type_permission_id", referencedColumnName = "business_type_permission_id")
	private BusinessTypePermission businessTypePermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "coc_practice_permission_id", referencedColumnName = "coc_practice_permission_id")
	private COCPracticePermission cocPracticePermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "pricing_type_permission_id", referencedColumnName = "pricing_type_permission_id")
	private PricingTypePermission pricingTypePermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "billing_type_permission_id", referencedColumnName = "billing_type_permission_id")
	private BillingTypePermission billingTypePermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "work_order_permission_id", referencedColumnName = "work_order_permission_id")
	private WorkOrderPermission workOrderPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "financial_year_permission_id", referencedColumnName = "financial_year_permission_id")
	private FinancialYearPermission financialYearPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "currency_permission_id", referencedColumnName = "currency_permission_id")
	private CurrencyPermission currencyPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "notification_configuration_permission_id", referencedColumnName = "notification_configuration_permission_id")
	private NotificationConfigurationPermission notificationConfigurationPermission;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "global_leave_loss_factor_permission_id", referencedColumnName = "global_leave_loss_factor_permission_id")
	private GlobalLeaveLossFactorPermission globalLeaveLossFactorPermission;

	@OneToMany(mappedBy = "administrationPermission")
	@JsonBackReference
	private List<Roles> roles = new ArrayList<>();

	public AdministrationPermission() {

	}

	public AdministrationPermission(Long administrationPermissionId, boolean isAdministrationPermissionAll,
			boolean isViewAllEntriesRequired, AccountPermission accountPermission,
			OpportunityPermission opportunityPermission, BusinessUnitPermission businessUnitPermission,
			RegionPermission regionPermission, SBUPermission sbuPermission, SBUHeadPermission sbuHeadPermission,
			LocationPermission locationPermission, BDMPermission bdmPermission,
			ProbabilityTypePermission probabilityTypePermission, BusinessTypePermission businessTypePermission,
			COCPracticePermission cocPracticePermission, PricingTypePermission pricingTypePermission,
			BillingTypePermission billingTypePermission, WorkOrderPermission workOrderPermission,
			FinancialYearPermission financialYearPermission, CurrencyPermission currencyPermission,
			NotificationConfigurationPermission notificationConfigurationPermission,
			GlobalLeaveLossFactorPermission globalLeaveLossFactorPermission, List<Roles> roles) {
		super();
		this.administrationPermissionId = administrationPermissionId;
		this.isAdministrationPermissionAll = isAdministrationPermissionAll;
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
		this.accountPermission = accountPermission;
		this.opportunityPermission = opportunityPermission;
		this.businessUnitPermission = businessUnitPermission;
		this.regionPermission = regionPermission;
		this.sbuPermission = sbuPermission;
		this.sbuHeadPermission = sbuHeadPermission;
		this.locationPermission = locationPermission;
		this.bdmPermission = bdmPermission;
		this.probabilityTypePermission = probabilityTypePermission;
		this.businessTypePermission = businessTypePermission;
		this.cocPracticePermission = cocPracticePermission;
		this.pricingTypePermission = pricingTypePermission;
		this.billingTypePermission = billingTypePermission;
		this.workOrderPermission = workOrderPermission;
		this.financialYearPermission = financialYearPermission;
		this.currencyPermission = currencyPermission;
		this.notificationConfigurationPermission = notificationConfigurationPermission;
		this.globalLeaveLossFactorPermission = globalLeaveLossFactorPermission;
		this.roles = roles;
	}

	public Long getAdministrationPermissionId() {
		return administrationPermissionId;
	}

	public void setAdministrationPermissionId(Long administrationPermissionId) {
		this.administrationPermissionId = administrationPermissionId;
	}

	public boolean isAdministrationPermissionAll() {
		return isAdministrationPermissionAll;
	}

	public void setAdministrationPermissionAll(boolean isAdministrationPermissionAll) {
		this.isAdministrationPermissionAll = isAdministrationPermissionAll;
	}

	public boolean isViewAllEntriesRequired() {
		return isViewAllEntriesRequired;
	}

	public void setViewAllEntriesRequired(boolean isViewAllEntriesRequired) {
		this.isViewAllEntriesRequired = isViewAllEntriesRequired;
	}

	public AccountPermission getAccountPermission() {
		return accountPermission;
	}

	public void setAccountPermission(AccountPermission accountPermission) {
		this.accountPermission = accountPermission;
	}

	public OpportunityPermission getOpportunityPermission() {
		return opportunityPermission;
	}

	public void setOpportunityPermission(OpportunityPermission opportunityPermission) {
		this.opportunityPermission = opportunityPermission;
	}

	public BusinessUnitPermission getBusinessUnitPermission() {
		return businessUnitPermission;
	}

	public void setBusinessUnitPermission(BusinessUnitPermission businessUnitPermission) {
		this.businessUnitPermission = businessUnitPermission;
	}

	public RegionPermission getRegionPermission() {
		return regionPermission;
	}

	public void setRegionPermission(RegionPermission regionPermission) {
		this.regionPermission = regionPermission;
	}

	public SBUPermission getSbuPermission() {
		return sbuPermission;
	}

	public void setSbuPermission(SBUPermission sbuPermission) {
		this.sbuPermission = sbuPermission;
	}

	public SBUHeadPermission getSbuHeadPermission() {
		return sbuHeadPermission;
	}

	public void setSbuHeadPermission(SBUHeadPermission sbuHeadPermission) {
		this.sbuHeadPermission = sbuHeadPermission;
	}

	public LocationPermission getLocationPermission() {
		return locationPermission;
	}

	public void setLocationPermission(LocationPermission locationPermission) {
		this.locationPermission = locationPermission;
	}

	public BDMPermission getBdmPermission() {
		return bdmPermission;
	}

	public void setBdmPermission(BDMPermission bdmPermission) {
		this.bdmPermission = bdmPermission;
	}

	public ProbabilityTypePermission getProbabilityTypePermission() {
		return probabilityTypePermission;
	}

	public void setProbabilityTypePermission(ProbabilityTypePermission probabilityTypePermission) {
		this.probabilityTypePermission = probabilityTypePermission;
	}

	public BusinessTypePermission getBusinessTypePermission() {
		return businessTypePermission;
	}

	public void setBusinessTypePermission(BusinessTypePermission businessTypePermission) {
		this.businessTypePermission = businessTypePermission;
	}

	public COCPracticePermission getCocPracticePermission() {
		return cocPracticePermission;
	}

	public void setCocPracticePermission(COCPracticePermission cocPracticePermission) {
		this.cocPracticePermission = cocPracticePermission;
	}

	public PricingTypePermission getPricingTypePermission() {
		return pricingTypePermission;
	}

	public void setPricingTypePermission(PricingTypePermission pricingTypePermission) {
		this.pricingTypePermission = pricingTypePermission;
	}

	public BillingTypePermission getBillingTypePermission() {
		return billingTypePermission;
	}

	public void setBillingTypePermission(BillingTypePermission billingTypePermission) {
		this.billingTypePermission = billingTypePermission;
	}

	public WorkOrderPermission getWorkOrderPermission() {
		return workOrderPermission;
	}

	public void setWorkOrderPermission(WorkOrderPermission workOrderPermission) {
		this.workOrderPermission = workOrderPermission;
	}

	public FinancialYearPermission getFinancialYearPermission() {
		return financialYearPermission;
	}

	public void setFinancialYearPermission(FinancialYearPermission financialYearPermission) {
		this.financialYearPermission = financialYearPermission;
	}

	public CurrencyPermission getCurrencyPermission() {
		return currencyPermission;
	}

	public void setCurrencyPermission(CurrencyPermission currencyPermission) {
		this.currencyPermission = currencyPermission;
	}

	public NotificationConfigurationPermission getNotificationConfigurationPermission() {
		return notificationConfigurationPermission;
	}

	public void setNotificationConfigurationPermission(
			NotificationConfigurationPermission notificationConfigurationPermission) {
		this.notificationConfigurationPermission = notificationConfigurationPermission;
	}

	public GlobalLeaveLossFactorPermission getGlobalLeaveLossFactorPermission() {
		return globalLeaveLossFactorPermission;
	}

	public void setGlobalLeaveLossFactorPermission(GlobalLeaveLossFactorPermission globalLeaveLossFactorPermission) {
		this.globalLeaveLossFactorPermission = globalLeaveLossFactorPermission;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}
