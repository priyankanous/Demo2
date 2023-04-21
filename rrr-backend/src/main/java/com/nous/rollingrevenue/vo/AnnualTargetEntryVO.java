package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnualTargetEntryVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long annualTargetEntryId;

	private FinancialYearVO financialYear;

	private BusinessUnitVO businessUnit;

	private StrategicBusinessUnitVO startegicBusinessUnit;

	private StrategicBusinessUnitHeadVO strategicBusinessUnitHead;

	private LocationVO location;

	private RegionVO region;

	private AccountVO account;

	private BusinessTypeVO businessType;

	private CocPracticeVO cocPractice;

	private BDMVO businessDevelopmentManager;

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

	public AnnualTargetEntryVO(Long annualTargetEntryId, FinancialYearVO financialYear, BusinessUnitVO businessUnit,
			StrategicBusinessUnitVO startegicBusinessUnit, StrategicBusinessUnitHeadVO strategicBusinessUnitHead, LocationVO location, RegionVO region,
			AccountVO account, BusinessTypeVO businessType, CocPracticeVO cocPractice, BDMVO businessDevelopmentManager,
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

	public FinancialYearVO getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYearVO financialYear) {
		this.financialYear = financialYear;
	}

	public BusinessUnitVO getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnitVO businessUnit) {
		this.businessUnit = businessUnit;
	}

	public StrategicBusinessUnitVO getStartegicBusinessUnit() {
		return startegicBusinessUnit;
	}

	public void setStartegicBusinessUnit(StrategicBusinessUnitVO startegicBusinessUnit) {
		this.startegicBusinessUnit = startegicBusinessUnit;
	}

	public StrategicBusinessUnitHeadVO getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(StrategicBusinessUnitHeadVO strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public LocationVO getLocation() {
		return location;
	}

	public void setLocation(LocationVO location) {
		this.location = location;
	}

	public RegionVO getRegion() {
		return region;
	}

	public void setRegion(RegionVO region) {
		this.region = region;
	}

	public AccountVO getAccount() {
		return account;
	}

	public void setAccount(AccountVO account) {
		this.account = account;
	}

	public BusinessTypeVO getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypeVO businessType) {
		this.businessType = businessType;
	}

	public CocPracticeVO getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(CocPracticeVO cocPractice) {
		this.cocPractice = cocPractice;
	}

	public BDMVO getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(BDMVO businessDevelopmentManager) {
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
	

}
