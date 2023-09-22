package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Objects;

public class FPResourceEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate resourceStartDate;

	private LocalDate resourceEndDate;

	private String milestoneNumber;

	private String workOrderNumber;

	private String cocPractice;

	private String employeeId;

	private String resourceName;

	private LocalDate milestoneBillingDate;

	private BigInteger revenue = BigInteger.ZERO;

	private Integer allocation;

	private String leaveLossFactor;

	public FPResourceEntryVO() {

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

	public String getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(String cocPractice) {
		this.cocPractice = cocPractice;
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

	public Integer getAllocation() {
		return allocation;
	}

	public void setAllocation(Integer allocation) {
		this.allocation = allocation;
	}

	public String getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(String leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(allocation, cocPractice, employeeId, leaveLossFactor, milestoneBillingDate, milestoneNumber,
				resourceEndDate, resourceName, resourceStartDate, revenue, workOrderNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FPResourceEntryVO other = (FPResourceEntryVO) obj;
		return Objects.equals(allocation, other.allocation) && Objects.equals(cocPractice, other.cocPractice)
				&& Objects.equals(employeeId, other.employeeId)
				&& Objects.equals(leaveLossFactor, other.leaveLossFactor)
				&& Objects.equals(milestoneBillingDate, other.milestoneBillingDate)
				&& Objects.equals(milestoneNumber, other.milestoneNumber)
				&& Objects.equals(resourceEndDate, other.resourceEndDate)
				&& Objects.equals(resourceName, other.resourceName)
				&& Objects.equals(resourceStartDate, other.resourceStartDate) && Objects.equals(revenue, other.revenue)
				&& Objects.equals(workOrderNumber, other.workOrderNumber);
	}

}
