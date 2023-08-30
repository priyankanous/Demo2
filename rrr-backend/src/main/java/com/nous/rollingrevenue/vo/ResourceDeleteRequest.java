package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ResourceDeleteRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "OpportunityId cannot be null or empty")
	private Long opportunityId;

	@NotBlank(message = "EmployeeId cannot be null or empty")
	private String employeeId;

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate resourceStartDate;

	public Long getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(Long opportunityId) {
		this.opportunityId = opportunityId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getResourceStartDate() {
		return resourceStartDate;
	}

	public void setResourceStartDate(LocalDate resourceStartDate) {
		this.resourceStartDate = resourceStartDate;
	}

}
