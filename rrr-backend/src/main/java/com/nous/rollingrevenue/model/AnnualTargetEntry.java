package com.nous.rollingrevenue.model;

import java.math.BigInteger;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "annual_target_entry")
@EntityListeners(AuditingEntityListener.class)
public class AnnualTargetEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "annual_target_entry_id")
	private Long annualTargetEntryId;

	@Column(name = "financial_year")
	private String financialYear;

	@Column(name = "bu")
	private String businessUnit;

	@Column(name = "sbu")
	private String startegicBusinessUnit;

	@Column(name = "sbu_head")
	private String strategicBusinessUnitHead;

	@Column(name = "location")
	private String location;

	@Column(name = "region")
	private String region;

	@Column(name = "account")
	private String account;

	@Column(name = "business_type")
	private String businessType;

	@Column(name = "coc_practice")
	private String cocPractice;

	@Column(name = "bdm")
	private String businessDevelopmentManager;

	@Column(name = "q1fy_s")
	private BigInteger Q1FYS;

	@Column(name = "q1fy_b")
	private BigInteger Q1FYB;

	@Column(name = "q1fy_t")
	private BigInteger Q1FYT;

	@Column(name = "q2fy_s")
	private BigInteger Q2FYS;

	@Column(name = "q2fy_b")
	private BigInteger Q2FYB;

	@Column(name = "q2fy_t")
	private BigInteger Q2FYT;

	@Column(name = "q3fy_s")
	private BigInteger Q3FYS;

	@Column(name = "q3fy_b")
	private BigInteger Q3FYB;

	@Column(name = "q3fy_t")
	private BigInteger Q3FYT;

	@Column(name = "q4fy_s")
	private BigInteger Q4FYS;

	@Column(name = "q4fy_b")
	private BigInteger Q4FYB;

	@Column(name = "q4fy_t")
	private BigInteger Q4FYT;

	@Column(name = "fy")
	private BigInteger FY;
	

	public AnnualTargetEntry() {

	}

	public AnnualTargetEntry(Long annualTargetEntryId, String financialYear, String businessUnit,
			String startegicBusinessUnit, String strategicBusinessUnitHead, String location, String region,
			String account, String businessType, String cocPractice, String businessDevelopmentManager,
			BigInteger q1fys, BigInteger q1fyb, BigInteger q1fyt, BigInteger q2fys, BigInteger q2fyb, BigInteger q2fyt,
			BigInteger q3fys, BigInteger q3fyb, BigInteger q3fyt, BigInteger q4fys, BigInteger q4fyb, BigInteger q4fyt,
			BigInteger fY) {
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
		Q1FYS = q1fys;
		Q1FYB = q1fyb;
		Q1FYT = q1fyt;
		Q2FYS = q2fys;
		Q2FYB = q2fyb;
		Q2FYT = q2fyt;
		Q3FYS = q3fys;
		Q3FYB = q3fyb;
		Q3FYT = q3fyt;
		Q4FYS = q4fys;
		Q4FYB = q4fyb;
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

	public BigInteger getQ1FYS() {
		return Q1FYS;
	}

	public void setQ1FYS(BigInteger q1fys) {
		Q1FYS = q1fys;
	}

	public BigInteger getQ1FYB() {
		return Q1FYB;
	}

	public void setQ1FYB(BigInteger q1fyb) {
		Q1FYB = q1fyb;
	}

	public BigInteger getQ1FYT() {
		return Q1FYT;
	}

	public void setQ1FYT(BigInteger q1fyt) {
		Q1FYT = q1fyt;
	}

	public BigInteger getQ2FYS() {
		return Q2FYS;
	}

	public void setQ2FYS(BigInteger q2fys) {
		Q2FYS = q2fys;
	}

	public BigInteger getQ2FYB() {
		return Q2FYB;
	}

	public void setQ2FYB(BigInteger q2fyb) {
		Q2FYB = q2fyb;
	}

	public BigInteger getQ2FYT() {
		return Q2FYT;
	}

	public void setQ2FYT(BigInteger q2fyt) {
		Q2FYT = q2fyt;
	}

	public BigInteger getQ3FYS() {
		return Q3FYS;
	}

	public void setQ3FYS(BigInteger q3fys) {
		Q3FYS = q3fys;
	}

	public BigInteger getQ3FYB() {
		return Q3FYB;
	}

	public void setQ3FYB(BigInteger q3fyb) {
		Q3FYB = q3fyb;
	}

	public BigInteger getQ3FYT() {
		return Q3FYT;
	}

	public void setQ3FYT(BigInteger q3fyt) {
		Q3FYT = q3fyt;
	}

	public BigInteger getQ4FYS() {
		return Q4FYS;
	}

	public void setQ4FYS(BigInteger q4fys) {
		Q4FYS = q4fys;
	}

	public BigInteger getQ4FYB() {
		return Q4FYB;
	}

	public void setQ4FYB(BigInteger q4fyb) {
		Q4FYB = q4fyb;
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


	@Override
	public String toString() {
		return "AnnualTargetEntry [annualTargetEntryId=" + annualTargetEntryId + ", financialYear=" + financialYear
				+ ", businessUnit=" + businessUnit + ", startegicBusinessUnit=" + startegicBusinessUnit
				+ ", strategicBusinessUnitHead=" + strategicBusinessUnitHead + ", location=" + location + ", region="
				+ region + ", account=" + account + ", businessType=" + businessType + ", cocPractice=" + cocPractice
				+ ", businessDevelopmentManager=" + businessDevelopmentManager + ", Q1FYS=" + Q1FYS + ", Q1FYB=" + Q1FYB
				+ ", Q1FYT=" + Q1FYT + ", Q2FYS=" + Q2FYS + ", Q2FYB=" + Q2FYB + ", Q2FYT=" + Q2FYT + ", Q3FYS=" + Q3FYS
				+ ", Q3FYB=" + Q3FYB + ", Q3FYT=" + Q3FYT + ", Q4FYS=" + Q4FYS + ", Q4FYB=" + Q4FYB + ", Q4FYT=" + Q4FYT
				+ ", FY=" + FY + "]";
	}

}
