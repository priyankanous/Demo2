package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "opportunity")
@EntityListeners(AuditingEntityListener.class)
public class Opportunity extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "opportunity_id")
	private Long opportunityId;

	@Column(name = "opportunity_name")
	private String opportunityName;

	@Column(name = "child_of_account")
	private String childOfAccount;

	@Column(name = "project_code")
	private String projectCode;

	@Column(name = "project_start_date")
	private LocalDate projectStartDate;

	@Column(name = "project_end_date")
	private LocalDate projectEndDate;

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public Opportunity() {

	}

	public Opportunity(Long opportunityId, String opportunityName, String childOfAccount, String projectCode,
			LocalDate projectStartDate, LocalDate projectEndDate, boolean isActive) {
		super();
		this.opportunityId = opportunityId;
		this.opportunityName = opportunityName;
		this.childOfAccount = childOfAccount;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Opportunity [opportunityId=" + opportunityId + ", opportunityName=" + opportunityName
				+ ", childOfAccount=" + childOfAccount + ", projectCode=" + projectCode + ", projectStartDate="
				+ projectStartDate + ", projectEndDate=" + projectEndDate + ", isActive=" + isActive + "]";
	}

}
