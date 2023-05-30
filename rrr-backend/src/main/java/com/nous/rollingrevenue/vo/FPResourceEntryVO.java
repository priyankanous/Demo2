package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

public class FPResourceEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate resourceStartDate;

	private LocalDate resourceEndDate;

	private String milestoneNumber;

	private String workOrderNumber;

	private String employeeId;

	private String resourceName;

	private LocalDate milestoneBillingDate;

	private BigInteger revenue = BigInteger.ZERO;

	private Integer allocation;

	private String leaveLossFactor;

	public FPResourceEntryVO() {

	}

	public FPResourceEntryVO(LocalDate resourceStartDate, LocalDate resourceEndDate, String milestoneNumber,
			String workOrderNumber, String employeeId, String resourceName, LocalDate milestoneBillingDate,
			BigInteger revenue, String leaveLossFactor) {
		super();
		this.resourceStartDate = resourceStartDate;
		this.resourceEndDate = resourceEndDate;
		this.milestoneNumber = milestoneNumber;
		this.workOrderNumber = workOrderNumber;
		this.employeeId = employeeId;
		this.resourceName = resourceName;
		this.milestoneBillingDate = milestoneBillingDate;
		this.revenue = revenue;
		this.leaveLossFactor = leaveLossFactor;
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

	public String getMilestoneNumber() {
		return milestoneNumber;
	}

	public void setMilestoneNumber(String milestoneNumber) {
		this.milestoneNumber = milestoneNumber;
	}

	public String getWorkOrderNumber() {
		return workOrderNumber;
	}

	public void setWorkOrderNumber(String workOrderNumber) {
		this.workOrderNumber = workOrderNumber;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public LocalDate getMilestoneBillingDate() {
		return milestoneBillingDate;
	}

	public void setMilestoneBillingDate(LocalDate milestoneBillingDate) {
		this.milestoneBillingDate = milestoneBillingDate;
	}

	public BigInteger getRevenue() {
		return revenue;
	}

	public void setRevenue(BigInteger revenue) {
		this.revenue = revenue;
	}

	public String getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(String leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public Integer getAllocation() {
		return allocation;
	}

	public void setAllocation(Integer allocation) {
		this.allocation = allocation;
	}

}
