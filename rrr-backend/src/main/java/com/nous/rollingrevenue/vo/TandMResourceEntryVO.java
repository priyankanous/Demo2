package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

public class TandMResourceEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate resourceStartDate;

	private LocalDate resourceEndDate;

	private String workOrderNumber;

	private String cocPractice;

	private String employeeId;

	private String resourceName;

	private BigInteger billingRate;

	private Integer allocation;

	private String leaveLossFactor;

	public TandMResourceEntryVO() {

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

	public BigInteger getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(BigInteger billingRate) {
		this.billingRate = billingRate;
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

}
