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
	private FinancialYearVO financialYear;

	@NotBlank(message = "Business Unit cannot be null or empty")
	private BusinessUnitVO businessUnit;

	@NotBlank(message = "Strategic Business Unit cannot be null or empty")
	private StrategicBusinessUnitVO strategicBusinessUnit;

	@NotBlank(message = "Strategic Business Unit Head cannot be null or empty")
	private StrategicBusinessUnitHeadVO strategicBusinessUnitHead;

	@NotBlank(message = "Account cannot be null or empty")
	private AccountVO account;

	@NotBlank(message = "Opportunity Name cannot be null or empty")
	private OpportunityVO opportunityName;

	@NotBlank(message = "Business Type cannot be null or empty")
	private BusinessTypeVO businessType;

	@NotBlank(message = "Project Code cannot be null or empty")
	private String projectCode;

	@NotBlank(message = "Project Start Date cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectStartDate;

	@NotBlank(message = "Project End Date cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectEndDate;

	@NotBlank(message = "Probability cannot be null or empty")
	private ProbabilityTypeVO probability;

	@NotBlank(message = "Business Development Manager cannot be null or empty")
	private BDMVO bdm;

	@NotBlank(message = "Region cannot be null or empty")
	private RegionVO region;

	private CocPracticeVO cocPractice;

	@NotBlank(message = "Location cannot be null or empty")
	private LocationVO location;

	@NotBlank(message = "Currency cannot be null or empty")
	private CurrencyVO currency;

	private WorkOrderVO workOrder;

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

	public StrategicBusinessUnitVO getStrategicBusinessUnit() {
		return strategicBusinessUnit;
	}

	public void setStrategicBusinessUnit(StrategicBusinessUnitVO strategicBusinessUnit) {
		this.strategicBusinessUnit = strategicBusinessUnit;
	}

	public StrategicBusinessUnitHeadVO getStrategicBusinessUnitHead() {
		return strategicBusinessUnitHead;
	}

	public void setStrategicBusinessUnitHead(StrategicBusinessUnitHeadVO strategicBusinessUnitHead) {
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
	}

	public AccountVO getAccount() {
		return account;
	}

	public void setAccount(AccountVO account) {
		this.account = account;
	}

	public OpportunityVO getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(OpportunityVO opportunityName) {
		this.opportunityName = opportunityName;
	}

	public BusinessTypeVO getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypeVO businessType) {
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

	public ProbabilityTypeVO getProbability() {
		return probability;
	}

	public void setProbability(ProbabilityTypeVO probability) {
		this.probability = probability;
	}

	public BDMVO getBdm() {
		return bdm;
	}

	public void setBdm(BDMVO bdm) {
		this.bdm = bdm;
	}

	public RegionVO getRegion() {
		return region;
	}

	public void setRegion(RegionVO region) {
		this.region = region;
	}

	public CocPracticeVO getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(CocPracticeVO cocPractice) {
		this.cocPractice = cocPractice;
	}

	public LocationVO getLocation() {
		return location;
	}

	public void setLocation(LocationVO location) {
		this.location = location;
	}

	public CurrencyVO getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyVO currency) {
		this.currency = currency;
	}

	public WorkOrderVO getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(WorkOrderVO workOrder) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
