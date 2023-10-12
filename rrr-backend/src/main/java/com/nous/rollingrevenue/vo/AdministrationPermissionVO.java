package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class AdministrationPermissionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long administrationPermissionId;

	private boolean administrationPermissionAll;

	private boolean isViewAllAdministrationPermission;

	private AccountPermissionVO accountPermissionVO;

	private OpportunityPermissionVO opportunityPermissionVO;

	private BusinessUnitPermissionVO businessUnitPermissionVO;

	private RegionPermissionVO regionPermissionVO;

	private SBUPermissionVO sbuPermissionVO;

	private SBUHeadPermissionVO sbuHeadPermissionVO;

	private LocationPermissionVO locationPermissionVO;

	private BDMPermissionVO bdmPermissionVO;

	private ProbabilityPermissionVO probabilityPermissionVO;

	private BusinessTypePermissionVO businessTypePermissionVO;

	private CocPracticePermissionVO cocPracticePermissionVO;

	private PricingTypePermissionVO pricingTypePermissionVO;

	private WorkOrderPermissionVO workOrderPermissionVO;

	private FinancialYearPermissionVO financialYearPermissionVO;

	private CurrencyPermissionVO currencyPermissionVO;

	private NotificationConfigPermissionVO notificationConfigPermissionVO;

	private GlobalLeaveLassFactorPermissionVO globalLeaveLassFactorPermissionVO;

	public Long getAdministrationPermissionId() {
		return administrationPermissionId;
	}

	public void setAdministrationPermissionId(Long administrationPermissionId) {
		this.administrationPermissionId = administrationPermissionId;
	}

	public boolean isAdministrationPermissionAll() {
		return administrationPermissionAll;
	}

	public void setAdministrationPermissionAll(boolean administrationPermissionAll) {
		this.administrationPermissionAll = administrationPermissionAll;
	}

	public boolean isViewAllAdministrationPermission() {
		return isViewAllAdministrationPermission;
	}

	public void setViewAllAdministrationPermission(boolean isViewAllAdministrationPermission) {
		this.isViewAllAdministrationPermission = isViewAllAdministrationPermission;
	}

	public AccountPermissionVO getAccountPermissionVO() {
		return accountPermissionVO;
	}

	public void setAccountPermissionVO(AccountPermissionVO accountPermissionVO) {
		this.accountPermissionVO = accountPermissionVO;
	}

	public OpportunityPermissionVO getOpportunityPermissionVO() {
		return opportunityPermissionVO;
	}

	public void setOpportunityPermissionVO(OpportunityPermissionVO opportunityPermissionVO) {
		this.opportunityPermissionVO = opportunityPermissionVO;
	}

	public BusinessUnitPermissionVO getBusinessUnitPermissionVO() {
		return businessUnitPermissionVO;
	}

	public void setBusinessUnitPermissionVO(BusinessUnitPermissionVO businessUnitPermissionVO) {
		this.businessUnitPermissionVO = businessUnitPermissionVO;
	}

	public RegionPermissionVO getRegionPermissionVO() {
		return regionPermissionVO;
	}

	public void setRegionPermissionVO(RegionPermissionVO regionPermissionVO) {
		this.regionPermissionVO = regionPermissionVO;
	}

	public SBUPermissionVO getSbuPermissionVO() {
		return sbuPermissionVO;
	}

	public void setSbuPermissionVO(SBUPermissionVO sbuPermissionVO) {
		this.sbuPermissionVO = sbuPermissionVO;
	}

	public SBUHeadPermissionVO getSbuHeadPermissionVO() {
		return sbuHeadPermissionVO;
	}

	public void setSbuHeadPermissionVO(SBUHeadPermissionVO sbuHeadPermissionVO) {
		this.sbuHeadPermissionVO = sbuHeadPermissionVO;
	}

	public LocationPermissionVO getLocationPermissionVO() {
		return locationPermissionVO;
	}

	public void setLocationPermissionVO(LocationPermissionVO locationPermissionVO) {
		this.locationPermissionVO = locationPermissionVO;
	}

	public BDMPermissionVO getBdmPermissionVO() {
		return bdmPermissionVO;
	}

	public void setBdmPermissionVO(BDMPermissionVO bdmPermissionVO) {
		this.bdmPermissionVO = bdmPermissionVO;
	}

	public ProbabilityPermissionVO getProbabilityPermissionVO() {
		return probabilityPermissionVO;
	}

	public void setProbabilityPermissionVO(ProbabilityPermissionVO probabilityPermissionVO) {
		this.probabilityPermissionVO = probabilityPermissionVO;
	}

	public BusinessTypePermissionVO getBusinessTypePermissionVO() {
		return businessTypePermissionVO;
	}

	public void setBusinessTypePermissionVO(BusinessTypePermissionVO businessTypePermissionVO) {
		this.businessTypePermissionVO = businessTypePermissionVO;
	}

	public CocPracticePermissionVO getCocPracticePermissionVO() {
		return cocPracticePermissionVO;
	}

	public void setCocPracticePermissionVO(CocPracticePermissionVO cocPracticePermissionVO) {
		this.cocPracticePermissionVO = cocPracticePermissionVO;
	}

	public PricingTypePermissionVO getPricingTypePermissionVO() {
		return pricingTypePermissionVO;
	}

	public void setPricingTypePermissionVO(PricingTypePermissionVO pricingTypePermissionVO) {
		this.pricingTypePermissionVO = pricingTypePermissionVO;
	}

	public WorkOrderPermissionVO getWorkOrderPermissionVO() {
		return workOrderPermissionVO;
	}

	public void setWorkOrderPermissionVO(WorkOrderPermissionVO workOrderPermissionVO) {
		this.workOrderPermissionVO = workOrderPermissionVO;
	}

	public FinancialYearPermissionVO getFinancialYearPermissionVO() {
		return financialYearPermissionVO;
	}

	public void setFinancialYearPermissionVO(FinancialYearPermissionVO financialYearPermissionVO) {
		this.financialYearPermissionVO = financialYearPermissionVO;
	}

	public CurrencyPermissionVO getCurrencyPermissionVO() {
		return currencyPermissionVO;
	}

	public void setCurrencyPermissionVO(CurrencyPermissionVO currencyPermissionVO) {
		this.currencyPermissionVO = currencyPermissionVO;
	}

	public NotificationConfigPermissionVO getNotificationConfigPermissionVO() {
		return notificationConfigPermissionVO;
	}

	public void setNotificationConfigPermissionVO(NotificationConfigPermissionVO notificationConfigPermissionVO) {
		this.notificationConfigPermissionVO = notificationConfigPermissionVO;
	}

	public GlobalLeaveLassFactorPermissionVO getGlobalLeaveLassFactorPermissionVO() {
		return globalLeaveLassFactorPermissionVO;
	}

	public void setGlobalLeaveLassFactorPermissionVO(
			GlobalLeaveLassFactorPermissionVO globalLeaveLassFactorPermissionVO) {
		this.globalLeaveLassFactorPermissionVO = globalLeaveLassFactorPermissionVO;
	}

}
