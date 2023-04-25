package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class ResourcesEntryVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LocalDate startDate;

	private LocalDate endDate;

//	private String opportunityName;

	private String workOrderNumber;

	private String resourceName;

	private Long billingRate;

	private Long allocation;

	private Long leaveLossFactor;

	private MonthlyFinancialYearVO monthlyFinancialYearVO;

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

	public Long getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Long billingRate) {
		this.billingRate = billingRate;
	}

	public Long getAllocation() {
		return allocation;
	}

	public void setAllocation(Long allocation) {
		this.allocation = allocation;
	}

	public Long getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(Long leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public MonthlyFinancialYearVO getMonthlyFinancialYearVO() {
		return monthlyFinancialYearVO;
	}

	public void setMonthlyFinancialYearVO(MonthlyFinancialYearVO monthlyFinancialYearVO) {
		this.monthlyFinancialYearVO = monthlyFinancialYearVO;
	}

}
