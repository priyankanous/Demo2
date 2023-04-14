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

	private Long opportunityId;

	@NotBlank(message = "OpportunityName cannot be null or empty")
	private String opportunityName;

	private AccountVO accountVO;

	@NotBlank(message = "ProjectCode cannot be null or empty")
	private String projectCode;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectStartDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectEndDate;

	private boolean isActive;

	public OpportunityVO() {

	}

	public OpportunityVO(Long opportunityId,
			@NotBlank(message = "OpportunityName cannot be null or empty") String opportunityName, AccountVO accountVO,
			@NotBlank(message = "ProjectCode cannot be null or empty") String projectCode, LocalDate projectStartDate,
			LocalDate projectEndDate, boolean isActive) {
		super();
		this.opportunityId = opportunityId;
		this.opportunityName = opportunityName;
		this.accountVO = accountVO;
		this.projectCode = projectCode;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.isActive = isActive;
	}


	public AccountVO getAccountVO() {
		return accountVO;
	}

	public void setAccountVO(AccountVO accountVO) {
		this.accountVO = accountVO;
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

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "OpportunityVO [opportunityId=" + opportunityId + ", opportunityName=" + opportunityName + ", accountVO="
				+ accountVO + ", projectCode=" + projectCode + ", projectStartDate=" + projectStartDate
				+ ", projectEndDate=" + projectEndDate + ", isActive=" + isActive + "]";
	}



}
