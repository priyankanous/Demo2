package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class AnnualTargetEntryVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long annualTargetEntryId;

	@NotBlank(message = "FinancialYear cannot be null or empty")
	private String financialYear;

	@NotBlank(message = "BusinessUnit cannot be null or empty")
	private String businessUnit;

	@NotBlank(message = "StartegicBusinessUnit cannot be null or empty")
	private String startegicBusinessUnit;

	@NotBlank(message = "StrategicBusinessUnitHead cannot be null or empty")
	private String strategicBusinessUnitHead;

	@NotBlank(message = "Location cannot be null or empty")
	private String location;

	@NotBlank(message = "Region cannot be null or empty")
	private String region;

	@NotBlank(message = "Account cannot be null or empty")
	private String account;

	@NotBlank(message = "BusinessTypeName cannot be null or empty")
	private String businessType;

	@NotBlank(message = "COCPractice cannot be null or empty")
	private String cocPractice;

	@NotBlank(message = "BusinessDevelopmentManager cannot be null or empty")
	private String businessDevelopmentManager;

	private BigInteger Q1FYB;

	private BigInteger Q1FYS;

	private BigInteger Q1FYT;

	private BigInteger Q2FYB;

	private BigInteger Q2FYS;

	private BigInteger Q2FYT;

	private BigInteger Q3FYB;

	private BigInteger Q3FYS;

	private BigInteger Q3FYT;

	private BigInteger Q4FYB;

	private BigInteger Q4FYS;

	private BigInteger Q4FYT;

	private BigInteger FY;

	private boolean isActive;

	public AnnualTargetEntryVO() {

	}

	public AnnualTargetEntryVO(Long annualTargetEntryId, String financialYear, String businessUnit,
			String startegicBusinessUnit, String strategicBusinessUnitHead, String location, String region,
			String account, String businessType, String cocPractice, String businessDevelopmentManager,
			BigInteger q1fyb, BigInteger q1fys, BigInteger q1fyt, BigInteger q2fyb, BigInteger q2fys, BigInteger q2fyt,
			BigInteger q3fyb, BigInteger q3fys, BigInteger q3fyt, BigInteger q4fyb, BigInteger q4fys, BigInteger q4fyt,
			BigInteger fY, boolean isActive) {
		super();
		this.annualTargetEntryId = annualTargetEntryId;
		this.financialYear = financialYear;
		this.businessUnit = businessUnit;
		this.startegicBusinessUnit = startegicBusinessUnit;
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
		this.location = location;
		this.region = region;
		this.account = account;
		this.businessType = businessType;
		this.cocPractice = cocPractice;
		this.businessDevelopmentManager = businessDevelopmentManager;
		Q1FYB = q1fyb;
		Q1FYS = q1fys;
		Q1FYT = q1fyt;
		Q2FYB = q2fyb;
		Q2FYS = q2fys;
		Q2FYT = q2fyt;
		Q3FYB = q3fyb;
		Q3FYS = q3fys;
		Q3FYT = q3fyt;
		Q4FYB = q4fyb;
		Q4FYS = q4fys;
		Q4FYT = q4fyt;
		FY = fY;
		this.isActive = isActive;
	}

	public Long getAnnualTargetEntryId() {
		return annualTargetEntryId;
	}

	public void setAnnualTargetEntryId(Long annualTargetEntryId) {
		this.annualTargetEntryId = annualTargetEntryId;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	public String getStartegicBusinessUnit() {
		return startegicBusinessUnit;
	}

	public void setStartegicBusinessUnit(String startegicBusinessUnit) {
		this.startegicBusinessUnit = startegicBusinessUnit;
	}

	public String getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(String strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(String cocPractice) {
		this.cocPractice = cocPractice;
	}

	public String getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(String businessDevelopmentManager) {
		this.businessDevelopmentManager = businessDevelopmentManager;
	}

	public BigInteger getQ1FYB() {
		return Q1FYB;
	}

	public void setQ1FYB(BigInteger q1fyb) {
		Q1FYB = q1fyb;
	}

	public BigInteger getQ1FYS() {
		return Q1FYS;
	}

	public void setQ1FYS(BigInteger q1fys) {
		Q1FYS = q1fys;
	}

	public BigInteger getQ1FYT() {
		return Q1FYT;
	}

	public void setQ1FYT(BigInteger q1fyt) {
		Q1FYT = q1fyt;
	}

	public BigInteger getQ2FYB() {
		return Q2FYB;
	}

	public void setQ2FYB(BigInteger q2fyb) {
		Q2FYB = q2fyb;
	}

	public BigInteger getQ2FYS() {
		return Q2FYS;
	}

	public void setQ2FYS(BigInteger q2fys) {
		Q2FYS = q2fys;
	}

	public BigInteger getQ2FYT() {
		return Q2FYT;
	}

	public void setQ2FYT(BigInteger q2fyt) {
		Q2FYT = q2fyt;
	}

	public BigInteger getQ3FYB() {
		return Q3FYB;
	}

	public void setQ3FYB(BigInteger q3fyb) {
		Q3FYB = q3fyb;
	}

	public BigInteger getQ3FYS() {
		return Q3FYS;
	}

	public void setQ3FYS(BigInteger q3fys) {
		Q3FYS = q3fys;
	}

	public BigInteger getQ3FYT() {
		return Q3FYT;
	}

	public void setQ3FYT(BigInteger q3fyt) {
		Q3FYT = q3fyt;
	}

	public BigInteger getQ4FYB() {
		return Q4FYB;
	}

	public void setQ4FYB(BigInteger q4fyb) {
		Q4FYB = q4fyb;
	}

	public BigInteger getQ4FYS() {
		return Q4FYS;
	}

	public void setQ4FYS(BigInteger q4fys) {
		Q4FYS = q4fys;
	}

	public BigInteger getQ4FYT() {
		return Q4FYT;
	}

	public void setQ4FYT(BigInteger q4fyt) {
		Q4FYT = q4fyt;
	}

	public BigInteger getFY() {
		return FY;
	}

	public void setFY(BigInteger fY) {
		FY = fY;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "AnnualTargetEntryVO [annualTargetEntryId=" + annualTargetEntryId + ", financialYear=" + financialYear
				+ ", businessUnit=" + businessUnit + ", startegicBusinessUnit=" + startegicBusinessUnit
				+ ", strategicBusinessUnitHead=" + strategicBusinessUnitHead + ", location=" + location + ", region="
				+ region + ", account=" + account + ", businessType=" + businessType + ", cocPractice=" + cocPractice
				+ ", businessDevelopmentManager=" + businessDevelopmentManager + ", Q1FYB=" + Q1FYB + ", Q1FYS=" + Q1FYS
				+ ", Q1FYT=" + Q1FYT + ", Q2FYB=" + Q2FYB + ", Q2FYS=" + Q2FYS + ", Q2FYT=" + Q2FYT + ", Q3FYB=" + Q3FYB
				+ ", Q3FYS=" + Q3FYS + ", Q3FYT=" + Q3FYT + ", Q4FYB=" + Q4FYB + ", Q4FYS=" + Q4FYS + ", Q4FYT=" + Q4FYT
				+ ", FY=" + FY + ", isActive=" + isActive + "]";
	}
	

}
