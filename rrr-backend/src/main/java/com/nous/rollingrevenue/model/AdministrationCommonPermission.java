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
@Table(name = "administration_common_permission")
@EntityListeners(AuditingEntityListener.class)
public class AdministrationCommonPermission extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "administration_common_permission_id")
	private Long administrationCommonPermissionId;

	@Column(name = "view")
	private Boolean isViewRequired;

	@Column(name = "add")
	private Boolean isAddRequired;

	@Column(name = "edit")
	private Boolean isEditRequired;

	@Column(name = "activate_or_deactivate")
	private Boolean isActivateOrDeactivateRequired;

	@Column(name = "delete")
	private Boolean isDeleteRequired;

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
	private List<COCPractisePermission> cocPractisePermission = new ArrayList<>();

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
	private List<CurrencyPermission> currencyPermission = new ArrayList<>();

	@OneToMany(mappedBy = "administrationCommonPermission")
	@JsonBackReference
	private List<NotificationConfigurationPermission> notificationConfigurationPermission = new ArrayList<>();

	public AdministrationCommonPermission() {

	}

	public AdministrationCommonPermission(Long administrationCommonPermissionId, Boolean isViewRequired,
			Boolean isAddRequired, Boolean isEditRequired, Boolean isActivateOrDeactivateRequired,
			Boolean isDeleteRequired, List<AccountPermission> accountPermission,
			List<OpportunityPermission> opportunityPermission, List<BusinessUnitPermission> businessUnitPermission,
			List<RegionPermission> regionPermission, List<SBUPermission> sbuPermission,
			List<SBUHeadPermission> sbuHeadPermission, List<LocationPermission> locationPermission,
			List<BDMPermission> bdmPermission, List<ProbabilityTypePermission> probabilityTypePermission,
			List<BusinessTypePermission> businessTypePermission, List<COCPractisePermission> cocPractisePermission,
			List<PricingTypePermission> pricingTypePermission, List<BillingTypePermission> billingTypePermission,
			List<WorkOrderPermission> workOrderPermission, List<CurrencyPermission> currencyPermission,
			List<NotificationConfigurationPermission> notificationConfigurationPermission) {
		super();
		this.administrationCommonPermissionId = administrationCommonPermissionId;
		this.isViewRequired = isViewRequired;
		this.isAddRequired = isAddRequired;
		this.isEditRequired = isEditRequired;
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
		this.isDeleteRequired = isDeleteRequired;
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
		this.cocPractisePermission = cocPractisePermission;
		this.pricingTypePermission = pricingTypePermission;
		this.billingTypePermission = billingTypePermission;
		this.workOrderPermission = workOrderPermission;
		this.currencyPermission = currencyPermission;
		this.notificationConfigurationPermission = notificationConfigurationPermission;
	}

	public Long getAdministrationCommonPermissionId() {
		return administrationCommonPermissionId;
	}

	public void setAdministrationCommonPermissionId(Long administrationCommonPermissionId) {
		this.administrationCommonPermissionId = administrationCommonPermissionId;
	}

	public Boolean getIsViewRequired() {
		return isViewRequired;
	}

	public void setIsViewRequired(Boolean isViewRequired) {
		this.isViewRequired = isViewRequired;
	}

	public Boolean getIsAddRequired() {
		return isAddRequired;
	}

	public void setIsAddRequired(Boolean isAddRequired) {
		this.isAddRequired = isAddRequired;
	}

	public Boolean getIsEditRequired() {
		return isEditRequired;
	}

	public void setIsEditRequired(Boolean isEditRequired) {
		this.isEditRequired = isEditRequired;
	}

	public Boolean getIsActivateOrDeactivateRequired() {
		return isActivateOrDeactivateRequired;
	}

	public void setIsActivateOrDeactivateRequired(Boolean isActivateOrDeactivateRequired) {
		this.isActivateOrDeactivateRequired = isActivateOrDeactivateRequired;
	}

	public Boolean getIsDeleteRequired() {
		return isDeleteRequired;
	}

	public void setIsDeleteRequired(Boolean isDeleteRequired) {
		this.isDeleteRequired = isDeleteRequired;
	}

	public List<AccountPermission> getAccountPermission() {
		return accountPermission;
	}

	public void setAccountPermission(List<AccountPermission> accountPermission) {
		this.accountPermission = accountPermission;
	}

	public List<OpportunityPermission> getOpportunityPermission() {
		return opportunityPermission;
	}

	public void setOpportunityPermission(List<OpportunityPermission> opportunityPermission) {
		this.opportunityPermission = opportunityPermission;
	}

	public List<BusinessUnitPermission> getBusinessUnitPermission() {
		return businessUnitPermission;
	}

	public void setBusinessUnitPermission(List<BusinessUnitPermission> businessUnitPermission) {
		this.businessUnitPermission = businessUnitPermission;
	}

	public List<RegionPermission> getRegionPermission() {
		return regionPermission;
	}

	public void setRegionPermission(List<RegionPermission> regionPermission) {
		this.regionPermission = regionPermission;
	}

	public List<SBUPermission> getSbuPermission() {
		return sbuPermission;
	}

	public void setSbuPermission(List<SBUPermission> sbuPermission) {
		this.sbuPermission = sbuPermission;
	}

	public List<SBUHeadPermission> getSbuHeadPermission() {
		return sbuHeadPermission;
	}

	public void setSbuHeadPermission(List<SBUHeadPermission> sbuHeadPermission) {
		this.sbuHeadPermission = sbuHeadPermission;
	}

	public List<LocationPermission> getLocationPermission() {
		return locationPermission;
	}

	public void setLocationPermission(List<LocationPermission> locationPermission) {
		this.locationPermission = locationPermission;
	}

	public List<BDMPermission> getBdmPermission() {
		return bdmPermission;
	}

	public void setBdmPermission(List<BDMPermission> bdmPermission) {
		this.bdmPermission = bdmPermission;
	}

	public List<ProbabilityTypePermission> getProbabilityTypePermission() {
		return probabilityTypePermission;
	}

	public void setProbabilityTypePermission(List<ProbabilityTypePermission> probabilityTypePermission) {
		this.probabilityTypePermission = probabilityTypePermission;
	}

	public List<BusinessTypePermission> getBusinessTypePermission() {
		return businessTypePermission;
	}

	public void setBusinessTypePermission(List<BusinessTypePermission> businessTypePermission) {
		this.businessTypePermission = businessTypePermission;
	}

	public List<COCPractisePermission> getCocPractisePermission() {
		return cocPractisePermission;
	}

	public void setCocPractisePermission(List<COCPractisePermission> cocPractisePermission) {
		this.cocPractisePermission = cocPractisePermission;
	}

	public List<PricingTypePermission> getPricingTypePermission() {
		return pricingTypePermission;
	}

	public void setPricingTypePermission(List<PricingTypePermission> pricingTypePermission) {
		this.pricingTypePermission = pricingTypePermission;
	}

	public List<BillingTypePermission> getBillingTypePermission() {
		return billingTypePermission;
	}

	public void setBillingTypePermission(List<BillingTypePermission> billingTypePermission) {
		this.billingTypePermission = billingTypePermission;
	}

	public List<WorkOrderPermission> getWorkOrderPermission() {
		return workOrderPermission;
	}

	public void setWorkOrderPermission(List<WorkOrderPermission> workOrderPermission) {
		this.workOrderPermission = workOrderPermission;
	}

	public List<CurrencyPermission> getCurrencyPermission() {
		return currencyPermission;
	}

	public void setCurrencyPermission(List<CurrencyPermission> currencyPermission) {
		this.currencyPermission = currencyPermission;
	}

	public List<NotificationConfigurationPermission> getNotificationConfigurationPermission() {
		return notificationConfigurationPermission;
	}

	public void setNotificationConfigurationPermission(
			List<NotificationConfigurationPermission> notificationConfigurationPermission) {
		this.notificationConfigurationPermission = notificationConfigurationPermission;
	}

}
