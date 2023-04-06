package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class OpportunityVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long opportunityId;

	@NotBlank(message = "OpportunityName cannot be null or empty")
	private String opportunityName;

	@NotBlank(message = "ChildOfAccount cannot be null or empty")
	private String childOfAccount;

	@NotBlank(message = "ProjectCode cannot be null or empty")
	private String projectCode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectEndDate;

	public OpportunityVO() {

	}

	public OpportunityVO(Long opportunityId, String opportunityName, String childOfAccount, String projectCode,
			LocalDate projectStartDate, LocalDate projectEndDate) {
		this.opportunityId = opportunityId;
		this.opportunityName = opportunityName;
		this.childOfAccount = childOfAccount;
		this.projectCode = projectCode;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
	}

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getOpportunityName() {
		return opportunityName;
	}

	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}

	public String getChildOfAccount() {
		return childOfAccount;
	}

	public void setChildOfAccount(String childOfAccount) {
		this.childOfAccount = childOfAccount;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
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

	@Override
	public String toString() {
		return "OpportunityVO [opportunityId=" + opportunityId + ", opportunityName=" + opportunityName
				+ ", childOfAccount=" + childOfAccount + ", projectCode=" + projectCode + ", projectStartDate="
				+ projectStartDate + ", projectEndDate=" + projectEndDate + "]";
	}
	

}
