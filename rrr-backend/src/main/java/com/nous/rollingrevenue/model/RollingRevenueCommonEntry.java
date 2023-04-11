package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Column(name = "business_unit")
	private String businessUnit;

	@Column(name = "sbu")
	private String strategicBusinessUnit;

	@Column(name = "sbu_head")
	private String strategicBusinessUnitHead;

	@Column(name = "account")
	private String account;

	@Column(name = "opportunity_name")
	private String opportunityName;

	@Column(name = "business_type")
	private String businessType;

	@Column(name = "project_code")
	private String projectCode;

	@Column(name = "project_start_date")
	private LocalDate projectStartDate;

	@Column(name = "project_end_date")
	private LocalDate projectEndDate;

	@Column(name = "probability")
	private String probability;

	@Column(name = "bdm")
	private String bdm;

	@Column(name = "region")
	private String region;

	@Column(name = "coc_practice")
	private String cocPractice;

	@Column(name = "location")
	private String location;

	@Column(name = "currency")
	private String currency;

	@Column(name = "work_order")
	private String workOrder;

	@Column(name = "work_order_end_date")
	private LocalDate workOrderEndDate;

	@Column(name = "work_order_status")
	private String workOrderStatus;

	@Column(name = "no_of_resources")
	private Long NoOfResources;

	@Column(name = "remarks")
	private Long remarks;

	@Column(name = "status")
	private String status;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
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

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public String getBdm() {
		return bdm;
	}

	public void setBdm(String bdm) {
		this.bdm = bdm;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(String cocPractice) {
		this.cocPractice = cocPractice;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(String workOrder) {
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
		return NoOfResources;
	}

	public void setNoOfResources(Long noOfResources) {
		NoOfResources = noOfResources;
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

}
