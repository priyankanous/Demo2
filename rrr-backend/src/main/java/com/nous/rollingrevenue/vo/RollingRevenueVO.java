package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RollingRevenueVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long rollingRevenueId;

	@NotBlank(message = "Pricing Type cannot be null or empty")
	private String pricingType;

	@NotBlank(message = "Financial Year cannot be null or empty")
	private String financialYear;

	@NotBlank(message = "Business Unit cannot be null or empty")
	private String businessUnit;

	@NotBlank(message = "Strategic Business Unit cannot be null or empty")
	private String strategicBusinessUnit;

	@NotBlank(message = "Strategic Business Unit Head cannot be null or empty")
	private String strategicBusinessUnitHead;

	@NotBlank(message = "Account cannot be null or empty")
	private String account;

	@NotBlank(message = "Opportunity Name cannot be null or empty")
	private String opportunityName;

	@NotBlank(message = "Business Type cannot be null or empty")
	private String businessType;

	@NotBlank(message = "Project Code cannot be null or empty")
	private String projectCode;

	@NotBlank(message = "Project Start Date cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectStartDate;

	@NotBlank(message = "Project End Date cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectEndDate;

	@NotBlank(message = "Probability cannot be null or empty")
	private String probability;

	@NotBlank(message = "Business Development Manager cannot be null or empty")
	private String bdm;

	@NotBlank(message = "Region cannot be null or empty")
	private String region;

	private String cocPractice;

	@NotBlank(message = "Location cannot be null or empty")
	private String location;

	@NotBlank(message = "Currency cannot be null or empty")
	private String currency;

	private String workOrder;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate workOrderEndDate;

	private String workOrderStatus;

	@NotNull(message = "Leave Loss Factor cannot be null or empty")
	private Long leaveLossFactor;

	private String billingRateType;

	private Long billingRate;

	@NotNull(message = "Region cannot be null or empty")
	private Long noOfResources;

	private List<ResourcesDetailsVO> resourcesList;

	private Long remarks;

	private String status;

	public Long getRollingRevenueId() {
		return rollingRevenueId;
	}

	public void setRollingRevenueId(Long rollingRevenueId) {
		this.rollingRevenueId = rollingRevenueId;
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

	public String getWorkOrderStatus() {
		return workOrderStatus;
	}

	public void setWorkOrderStatus(String workOrderStatus) {
		this.workOrderStatus = workOrderStatus;
	}

	public LocalDate getWorkOrderEndDate() {
		return workOrderEndDate;
	}

	public void setWorkOrderEndDate(LocalDate workOrderEndDate) {
		this.workOrderEndDate = workOrderEndDate;
	}

	public Long getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(Long leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public String getBillingRateType() {
		return billingRateType;
	}

	public void setBillingRateType(String billingRateType) {
		this.billingRateType = billingRateType;
	}

	public Long getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Long billingRate) {
		this.billingRate = billingRate;
	}

	public Long getNoOfResources() {
		return noOfResources;
	}

	public void setNoOfResources(Long noOfResources) {
		this.noOfResources = noOfResources;
	}

	public List<ResourcesDetailsVO> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<ResourcesDetailsVO> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public Long getRemarks() {
		return remarks;
	}

	public void setRemarks(Long remarks) {
		this.remarks = remarks;
	}

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
