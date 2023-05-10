package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class ResourceEntryRequest implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String businessUnit;

	private String strategicBusinessUnit;

	private String strategicBusinessUnitHead;

	private String businessDevelopmentManager;

	private String businessType;

	private String account;

	private String region;

	private String location;

	private String probabilityType;

	private String status;

	private String financialYearName;

	private String projectCode;

	private String opportunityName;

	private LocalDate projectStartDate;

	private LocalDate projectEndDate;

	private String pricingType;

	private String cocPractice;

	public ResourceEntryRequest() {

	}

	public ResourceEntryRequest(String businessUnit, String strategicBusinessUnit, String strategicBusinessUnitHead,
			String businessDevelopmentManager, String businessType, String account, String region, String location,
			String probabilityType,String status, String financialYearName, String projectCode,
			String opportunityName, LocalDate projectStartDate, LocalDate projectEndDate, String pricingType,
			String cocPractice) {
		super();
		this.businessUnit = businessUnit;
		this.strategicBusinessUnit = strategicBusinessUnit;
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
		this.businessDevelopmentManager = businessDevelopmentManager;
		this.businessType = businessType;
		this.account = account;
		this.region = region;
		this.location = location;
		this.probabilityType = probabilityType;
		this.status = status;
		this.financialYearName = financialYearName;
		this.projectCode = projectCode;
		this.opportunityName = opportunityName;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.pricingType = pricingType;
		this.cocPractice = cocPractice;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getStrategicBusinessUnit() {
		return strategicBusinessUnit;
	}

	public void setStrategicBusinessUnit(String strategicBusinessUnit) {
		this.strategicBusinessUnit = strategicBusinessUnit;
	}

	public String getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(String strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public String getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(String businessDevelopmentManager) {
		this.businessDevelopmentManager = businessDevelopmentManager;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getProbabilityType() {
		return probabilityType;
	}

	public void setProbabilityType(String probabilityType) {
		this.probabilityType = probabilityType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public LocalDate getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(LocalDate projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public LocalDate getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(LocalDate projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
	}

	public String getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(String cocPractice) {
		this.cocPractice = cocPractice;
	}

}
