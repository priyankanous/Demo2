package com.nous.rollingrevenue.model;

import java.math.BigInteger;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
import jakarta.persistence.Table;

@Entity
@Table(name = "annual_target_entry")
@EntityListeners(AuditingEntityListener.class)
public class AnnualTargetEntry extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "annual_target_entry_id")
	private Long annualTargetEntryId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_id", referencedColumnName = "sbu_id")
	private StrategicBusinessUnit startegicBusinessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "sbu_head_id", referencedColumnName = "sbu_head_id")
	private StrategicBusinessUnitHead strategicBusinessUnitHead;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "business_type_id", referencedColumnName = "business_type_id")
	private BusinessType businessType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JsonManagedReference
	@JoinColumn(name = "coc_practice_id", referencedColumnName = "coc_practice_id")
	private CocPractice cocPractice;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bdm_id", referencedColumnName = "bdm_id")
	private BusinessDevelopmentManager businessDevelopmentManager;

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

	public AnnualTargetEntry(Long annualTargetEntryId, FinancialYear financialYear, BusinessUnit businessUnit,
			StrategicBusinessUnit startegicBusinessUnit, StrategicBusinessUnitHead strategicBusinessUnitHead, Location location, Region region,
			Account account, BusinessType businessType, CocPractice cocPractice, BusinessDevelopmentManager businessDevelopmentManager,
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

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public StrategicBusinessUnit getStartegicBusinessUnit() {
		return startegicBusinessUnit;
	}

	public void setStartegicBusinessUnit(StrategicBusinessUnit startegicBusinessUnit) {
		this.startegicBusinessUnit = startegicBusinessUnit;
	}

	public StrategicBusinessUnitHead getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(StrategicBusinessUnitHead strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public CocPractice getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(CocPractice cocPractice) {
		this.cocPractice = cocPractice;
	}

	public BusinessDevelopmentManager getBusinessDevelopmentManager() {
		return businessDevelopmentManager;
	}

	public void setBusinessDevelopmentManager(BusinessDevelopmentManager businessDevelopmentManager) {
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


}
