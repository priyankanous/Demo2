package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class OpportunityEntryResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long projectCodeId;

	private String projectCode;

	private String opportunityName;

	private String pricingType;

	private LocalDate projectStartDate;

	private LocalDate projectEndDate;

	private String cocPractice;

	private Integer noOfResources;

	private Long leaveLossFactor;

	private List<ResourcesEntryResponse> resourcesList;

	private List<MileStoneEntryResponse> mileStoneList;

	private FinancialYearRevenue financialYearRevenue;

	public Long getProjectCodeId() {
		return projectCodeId;
	}

	public void setProjectCodeId(Long projectCodeId) {
		this.projectCodeId = projectCodeId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public String getPricingType() {
		return pricingType;
	}

	public void setPricingType(String pricingType) {
		this.pricingType = pricingType;
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

	public String getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(String cocPractice) {
		this.cocPractice = cocPractice;
	}

	public Integer getNoOfResources() {
		return noOfResources;
	}

	public void setNoOfResources(Integer noOfResources) {
		this.noOfResources = noOfResources;
	}

	public Long getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(Long leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

	public List<ResourcesEntryResponse> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(List<ResourcesEntryResponse> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public List<MileStoneEntryResponse> getMileStoneList() {
		return mileStoneList;
	}

	public void setMileStoneList(List<MileStoneEntryResponse> mileStoneList) {
		this.mileStoneList = mileStoneList;
	}

	public FinancialYearRevenue getFinancialYearRevenue() {
		return financialYearRevenue;
	}

	public void setFinancialYearRevenue(FinancialYearRevenue financialYearRevenue) {
		this.financialYearRevenue = financialYearRevenue;
	}

}
