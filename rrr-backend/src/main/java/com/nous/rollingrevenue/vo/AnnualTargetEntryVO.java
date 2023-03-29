package com.nous.rollingrevenue.vo;

import java.io.Serializable;

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

	private Long Q1FYB;

	private Long Q1FYS;

	private Long Q1FYT;

	private Long Q2FYB;

	private Long Q2FYS;

	private Long Q2FYT;
	
	private Long Q3FYB;

	private Long Q3FYS;

	private Long Q3FYT;
	
	private Long Q4FYB;

	private Long Q4FYS;

	private Long Q4FYT;

	private Long FY;

	public AnnualTargetEntryVO() {

	}

	public AnnualTargetEntryVO(Long annualTargetEntryId,
			@NotBlank(message = "FinancialYear cannot be null or empty") String financialYear,
			@NotBlank(message = "BusinessUnit cannot be null or empty") String businessUnit,
			@NotBlank(message = "StartegicBusinessUnit cannot be null or empty") String startegicBusinessUnit,
			@NotBlank(message = "StrategicBusinessUnitHead cannot be null or empty") String strategicBusinessUnitHead,
			@NotBlank(message = "Location cannot be null or empty") String location,
			@NotBlank(message = "Region cannot be null or empty") String region,
			@NotBlank(message = "Account cannot be null or empty") String account,
			@NotBlank(message = "BusinessTypeName cannot be null or empty") String businessType,
			@NotBlank(message = "COCPractice cannot be null or empty") String cocPractice,
			@NotBlank(message = "BusinessDevelopmentManager cannot be null or empty") String businessDevelopmentManager,
			Long q1fyb, Long q1fys, Long q1fyt, Long q2fyb, Long q2fys, Long q2fyt, Long q3fyb, Long q3fys, Long q3fyt,
			Long q4fyb, Long q4fys, Long q4fyt, Long fY) {
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

	public Long getQ1FYB() {
		return Q1FYB;
	}

	public void setQ1FYB(Long q1fyb) {
		Q1FYB = q1fyb;
	}

	public Long getQ1FYS() {
		return Q1FYS;
	}

	public void setQ1FYS(Long q1fys) {
		Q1FYS = q1fys;
	}

	public Long getQ1FYT() {
		return Q1FYT;
	}

	public void setQ1FYT(Long q1fyt) {
		Q1FYT = q1fyt;
	}

	public Long getQ2FYB() {
		return Q2FYB;
	}

	public void setQ2FYB(Long q2fyb) {
		Q2FYB = q2fyb;
	}

	public Long getQ2FYS() {
		return Q2FYS;
	}

	public void setQ2FYS(Long q2fys) {
		Q2FYS = q2fys;
	}

	public Long getQ2FYT() {
		return Q2FYT;
	}

	public void setQ2FYT(Long q2fyt) {
		Q2FYT = q2fyt;
	}

	public Long getQ3FYB() {
		return Q3FYB;
	}

	public void setQ3FYB(Long q3fyb) {
		Q3FYB = q3fyb;
	}

	public Long getQ3FYS() {
		return Q3FYS;
	}

	public void setQ3FYS(Long q3fys) {
		Q3FYS = q3fys;
	}

	public Long getQ3FYT() {
		return Q3FYT;
	}

	public void setQ3FYT(Long q3fyt) {
		Q3FYT = q3fyt;
	}

	public Long getQ4FYB() {
		return Q4FYB;
	}

	public void setQ4FYB(Long q4fyb) {
		Q4FYB = q4fyb;
	}

	public Long getQ4FYS() {
		return Q4FYS;
	}

	public void setQ4FYS(Long q4fys) {
		Q4FYS = q4fys;
	}

	public Long getQ4FYT() {
		return Q4FYT;
	}

	public void setQ4FYT(Long q4fyt) {
		Q4FYT = q4fyt;
	}

	public Long getFY() {
		return FY;
	}

	public void setFY(Long fY) {
		FY = fY;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
				+ ", FY=" + FY + "]";
	}

	
}
