package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.Objects;

public class RevenueEntryResponse implements Serializable {

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

	private String cocPractice;

	private String status;

	private FinancialYearRevenue financialYearRevenue;

	public RevenueEntryResponse() {

	}

	public RevenueEntryResponse(String businessUnit, String strategicBusinessUnit, String strategicBusinessUnitHead,
			String businessDevelopmentManager, String businessType, String account, String region, String location,
			String probabilityType, String cocPractice, String status, FinancialYearRevenue financialYearRevenue) {
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
		this.cocPractice = cocPractice;
		this.status = status;
		this.financialYearRevenue = financialYearRevenue;
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

	public String getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(String cocPractice) {
		this.cocPractice = cocPractice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FinancialYearRevenue getFinancialYearRevenue() {
		return financialYearRevenue;
	}

	public void setFinancialYearRevenue(FinancialYearRevenue financialYearRevenue) {
		this.financialYearRevenue = financialYearRevenue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, businessDevelopmentManager, businessType, businessUnit, cocPractice, location,
				probabilityType, region, status, strategicBusinessUnit, strategicBusinessUnitHead);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RevenueEntryResponse other = (RevenueEntryResponse) obj;
		return Objects.equals(account, other.account)
				&& Objects.equals(businessDevelopmentManager, other.businessDevelopmentManager)
				&& Objects.equals(businessType, other.businessType) && Objects.equals(businessUnit, other.businessUnit)
				&& Objects.equals(cocPractice, other.cocPractice) && Objects.equals(location, other.location)
				&& Objects.equals(probabilityType, other.probabilityType) && Objects.equals(region, other.region)
				&& Objects.equals(status, other.status)
				&& Objects.equals(strategicBusinessUnit, other.strategicBusinessUnit)
				&& Objects.equals(strategicBusinessUnitHead, other.strategicBusinessUnitHead);
	}

}
