package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RevenueResourceEntryVO implements Serializable {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long revenueResourceEntryId;
	
	private StrategicBusinessUnitVO strategicBusinessUnit;
	
	private StrategicBusinessUnitHeadVO strategicBusinessUnitHead;
	
	private BusinessUnitVO businessUnit;
	
	private BusinessTypeVO businessType;
	
	private LocationVO location;
	
	private String resourceName;
	
	private String employeeId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate resourceStartDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate resourceEndDate;
	
	private CocPracticeVO cocPractice;
	
	private GlobalMonthlyLeaveLossFactorVO leaveLossFactor;
	
	private String billingRateType;
	
	private BigInteger billingRate;
	
	private BigInteger milestoneResourceRevenue;
	
	private Integer allocation;

	public RevenueResourceEntryVO() {

	}

	public RevenueResourceEntryVO(Long revenueResourceEntryId, StrategicBusinessUnitVO strategicBusinessUnit,
			StrategicBusinessUnitHeadVO strategicBusinessUnitHead, BusinessUnitVO businessUnit,
			BusinessTypeVO businessType, LocationVO location, String resourceName, String employeeId,
			LocalDate resourceStartDate, LocalDate resourceEndDate, CocPracticeVO cocPractice,
			GlobalMonthlyLeaveLossFactorVO leaveLossFactor, String billingRateType, BigInteger billingRate,
			BigInteger milestoneResourceRevenue, Integer allocation) {
		super();
		this.strategicBusinessUnit = strategicBusinessUnit;
		this.strategicBusinessUnitHead = strategicBusinessUnitHead;
		this.businessUnit = businessUnit;
		this.businessType = businessType;
		this.location = location;
		this.resourceName = resourceName;
		this.employeeId = employeeId;
		this.resourceStartDate = resourceStartDate;
		this.resourceEndDate = resourceEndDate;
		this.cocPractice = cocPractice;
		this.leaveLossFactor = leaveLossFactor;
		this.billingRateType = billingRateType;
		this.billingRate = billingRate;
		this.milestoneResourceRevenue = milestoneResourceRevenue;
		this.allocation = allocation;
	}
	

	public Long getRevenueResourceEntryId() {
		return revenueResourceEntryId;
	}

	public void setRevenueResourceEntryId(Long revenueResourceEntryId) {
		this.revenueResourceEntryId = revenueResourceEntryId;
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

	public BusinessUnitVO getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnitVO businessUnit) {
		this.businessUnit = businessUnit;
	}

	public BusinessTypeVO getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessTypeVO businessType) {
		this.businessType = businessType;
	}

	public LocationVO getLocation() {
		return location;
	}

	public void setLocation(LocationVO location) {
		this.location = location;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getResourceStartDate() {
		return resourceStartDate;
	}

	public void setResourceStartDate(LocalDate resourceStartDate) {
		this.resourceStartDate = resourceStartDate;
	}

	public LocalDate getResourceEndDate() {
		return resourceEndDate;
	}

	public void setResourceEndDate(LocalDate resourceEndDate) {
		this.resourceEndDate = resourceEndDate;
	}

	public CocPracticeVO getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(CocPracticeVO cocPractice) {
		this.cocPractice = cocPractice;
	}

	public GlobalMonthlyLeaveLossFactorVO getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(GlobalMonthlyLeaveLossFactorVO leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public String getBillingRateType() {
		return billingRateType;
	}

	public void setBillingRateType(String billingRateType) {
		this.billingRateType = billingRateType;
	}

	public BigInteger getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(BigInteger billingRate) {
		this.billingRate = billingRate;
	}

	public BigInteger getMilestoneResourceRevenue() {
		return milestoneResourceRevenue;
	}

	public void setMilestoneResourceRevenue(BigInteger milestoneResourceRevenue) {
		this.milestoneResourceRevenue = milestoneResourceRevenue;
	}

	public Integer getAllocation() {
		return allocation;
	}

	public void setAllocation(Integer allocation) {
		this.allocation = allocation;
	}


}
