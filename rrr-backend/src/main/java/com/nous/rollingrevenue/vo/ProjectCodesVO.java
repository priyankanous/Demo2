package com.nous.rollingrevenue.vo;

import java.io.Serializable;

public class ProjectCodesVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long projectCodeId;

	private String projectCode;

	private String opportunityName;

	private String pricingType;

	private String practice;

	private String cocPractice;

	private Long noOfResources;

	private Long leaveLossFactor;

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

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getCocPractice() {
		return cocPractice;
	}

	public void setCocPractice(String cocPractice) {
		this.cocPractice = cocPractice;
	}

	public Long getNoOfResources() {
		return noOfResources;
	}

	public void setNoOfResources(Long noOfResources) {
		this.noOfResources = noOfResources;
	}

	public Long getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(Long leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

}
