package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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

	private String cocPractice;

	private Integer noOfResources;

	private String leaveLossFactor;

	public OpportunityEntryVO() {

	}

	public OpportunityEntryVO(String projectCode, String opportunityName, String pricingType,
			LocalDate projectStartDate, LocalDate projectEndDate, String cocPractice, Integer noOfResources) {
		super();
		this.projectCode = projectCode;
		this.opportunityName = opportunityName;
		this.pricingType = pricingType;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.cocPractice = cocPractice;
		this.noOfResources = noOfResources;
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

	@Override
	public int hashCode() {
		return Objects.hash(cocPractice, noOfResources, opportunityName, pricingType, projectCode, projectEndDate,
				projectStartDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpportunityEntryVO other = (OpportunityEntryVO) obj;
		return Objects.equals(cocPractice, other.cocPractice) && Objects.equals(noOfResources, other.noOfResources)
				&& Objects.equals(opportunityName, other.opportunityName)
				&& Objects.equals(pricingType, other.pricingType) && Objects.equals(projectCode, other.projectCode)
				&& Objects.equals(projectEndDate, other.projectEndDate)
				&& Objects.equals(projectStartDate, other.projectStartDate);
	}

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getLeaveLossFactor() {
		return leaveLossFactor;
	}

	public void setLeaveLossFactor(String leaveLossFactor) {
		this.leaveLossFactor = leaveLossFactor;
	}

}
