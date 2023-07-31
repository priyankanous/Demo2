package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OpportunityVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long opportunityId;

	@NotBlank(message = "OpportunityName cannot be null or empty")
	private String opportunityName;

	private AccountVO account;

	@NotBlank(message = "ProjectCode cannot be null or empty")
	private String projectCode;

	@NotNull(message = "projectStartDate cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectStartDate;
	
	@NotNull(message = "projectEndDate cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate projectEndDate;

	private boolean isActive;

	public OpportunityVO() {

	}

	public OpportunityVO(Long opportunityId, String opportunityName, AccountVO account, String projectCode,
			LocalDate projectStartDate, LocalDate projectEndDate, boolean isActive) {
		super();
		this.opportunityId = opportunityId;
		this.opportunityName = opportunityName;
		this.account = account;
		this.projectCode = projectCode;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.isActive = isActive;
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

	public AccountVO getAccount() {
		return account;
	}

	public void setAccount(AccountVO account) {
		this.account = account;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
