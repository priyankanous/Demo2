package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

public class MileStoneEntryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6419161728814181670L;

	private LocalDate startDate;

	private LocalDate endDate;

	private String milestoneNumber;

	private String workOrderNumber;

	private String resourceName;

	private Integer allocation;

	private LocalDate milestoneBillingDate;

	private BigInteger milestoneRevenue;

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
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

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Integer getAllocation() {
		return allocation;
	}

	public void setAllocation(Integer allocation) {
		this.allocation = allocation;
	}

	public LocalDate getMilestoneBillingDate() {
		return milestoneBillingDate;
	}

	public void setMilestoneBillingDate(LocalDate milestoneBillingDate) {
		this.milestoneBillingDate = milestoneBillingDate;
	}

	public BigInteger getMilestoneRevenue() {
		return milestoneRevenue;
	}

	public void setMilestoneRevenue(BigInteger milestoneRevenue) {
		this.milestoneRevenue = milestoneRevenue;
	}

}
