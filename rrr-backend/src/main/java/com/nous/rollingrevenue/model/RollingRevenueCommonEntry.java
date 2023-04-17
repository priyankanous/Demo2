package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "rolling_revenue_common_entry")
public class RollingRevenueCommonEntry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long commonId;

	@Column(name = "pricing_type")
	private String pricingType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sbu_id", referencedColumnName = "sbu_id")
	private StrategicBusinessUnit strategicBusinessUnit;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sbu_head_id", referencedColumnName = "sbu_head_id")
	private StrategicBusinessUnitHead strategicBusinessUnitHead;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Account account;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "opportunity_id", referencedColumnName = "opportunity_id")
	private Opportunity opportunity;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "business_type_id", referencedColumnName = "business_type_id")
	private BusinessType businessType;

	@Column(name = "project_code")
	private String projectCode;

	@Column(name = "project_start_date")
	private LocalDate projectStartDate;

	@Column(name = "project_end_date")
	private LocalDate projectEndDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "probability_type_id", referencedColumnName = "probability_type_id")
	private ProbabilityType probability;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bdm_id", referencedColumnName = "bdm_id")
	private BusinessDevelopmentManager bdm;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "region_id", referencedColumnName = "region_id")
	private Region region;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coc_practice_id", referencedColumnName = "coc_practice_id")
	private CocPractice cocPractice;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "currency_id", referencedColumnName = "currency_id")
	private Currency currency;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "work_order_id", referencedColumnName = "work_order_id")
	private WorkOrder workOrder;

	@Column(name = "work_order_end_date")
	private LocalDate workOrderEndDate;

	@Column(name = "work_order_status")
	private String workOrderStatus;

	@Column(name = "no_of_resources")
	private Long noOfResources;

	@Column(name = "remarks")
	private Long remarks;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	public Long getCommonId() {
		return commonId;
	}

	public void setCommonId(Long commonId) {
		this.commonId = commonId;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public StrategicBusinessUnit getStrategicBusinessUnit() {
		return strategicBusinessUnit;
	}

	public void setStrategicBusinessUnit(StrategicBusinessUnit strategicBusinessUnit) {
		this.strategicBusinessUnit = strategicBusinessUnit;
	}

	public StrategicBusinessUnitHead getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(StrategicBusinessUnitHead strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Opportunity getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(Opportunity opportunity) {
		this.opportunity = opportunity;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	public ProbabilityType getProbability() {
		return probability;
	}

	public void setProbability(ProbabilityType probability) {
		this.probability = probability;
	}

	public BusinessDevelopmentManager getBdm() {
		return bdm;
	}

	public void setBdm(BusinessDevelopmentManager bdm) {
		this.bdm = bdm;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public CocPractice getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(CocPractice cocPractice) {
		this.cocPractice = cocPractice;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public LocalDate getWorkOrderEndDate() {
		return workOrderEndDate;
	}

	public void setWorkOrderEndDate(LocalDate workOrderEndDate) {
		this.workOrderEndDate = workOrderEndDate;
	}

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public Long getNoOfResources() {
		return noOfResources;
	}

	public void setNoOfResources(Long noOfResources) {
		this.noOfResources = noOfResources;
	}

	public Long getRemarks() {
		return remarks;
	}

	public void setRemarks(Long remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

}
