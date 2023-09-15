package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

public class OpportunityEntryVO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Long opportunityId;

	private String projectCode;

	private String opportunityName;

	private String pricingType;

	private LocalDate projectStartDate;

	private LocalDate projectEndDate;

	private Integer noOfResources;

	private String leaveLossFactor;

	public OpportunityEntryVO() {

	}

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
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

	public Integer getNoOfResources() {
		return noOfResources;
	}

	public void setNoOfResources(Integer noOfResources) {
		this.noOfResources = noOfResources;
	}

	public String getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(String leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

}
