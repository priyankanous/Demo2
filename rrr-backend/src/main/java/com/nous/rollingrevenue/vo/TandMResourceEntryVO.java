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

	private String employeeId;

	private String resourceName;

	private BigInteger billingRate;

	private String leaveLossFactor;

	public TandMResourceEntryVO() {

	}

	public TandMResourceEntryVO(LocalDate resourceStartDate, LocalDate resourceEndDate, String workOrderNumber,
			String employeeId, String resourceName, BigInteger billingRate, String leaveLossFactor) {
		super();
		this.resourceStartDate = resourceStartDate;
		this.resourceEndDate = resourceEndDate;
		this.workOrderNumber = workOrderNumber;
		this.employeeId = employeeId;
		this.resourceName = resourceName;
		this.billingRate = billingRate;
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

	public BigInteger getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(BigInteger billingRate) {
		this.billingRate = billingRate;
	}

	public String getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(String leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

}
