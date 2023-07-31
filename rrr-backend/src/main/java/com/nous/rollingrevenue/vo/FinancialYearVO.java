package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FinancialYearVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long financialYearId;

	@NotBlank(message = "FinancialYearName cannot be null or empty")
	private String financialYearName;

	@NotBlank(message = "FinancialYearCustomName cannot be null or empty")
	private String financialYearCustomName;

	@NotNull(message = "startingFrom cannot be null or empty")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate startingFrom;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate endingOn;

	private boolean isActive;

	public FinancialYearVO() {

	}

	public FinancialYearVO(Long financialYearId, String financialYearName, String financialYearCustomName,
			LocalDate startingFrom, LocalDate endingOn, boolean isActive) {
		super();
		this.financialYearId = financialYearId;
		this.financialYearName = financialYearName;
		this.financialYearCustomName = financialYearCustomName;
		this.startingFrom = startingFrom;
		this.endingOn = endingOn;
		this.isActive = isActive;
	}

	public Long getFinancialYearId() {
		return financialYearId;
	}

	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}

	public String getFinancialYearName() {
		return financialYearName;
	}

	public void setFinancialYearName(String financialYearName) {
		this.financialYearName = financialYearName;
	}

	public String getFinancialYearCustomName() {
		return financialYearCustomName;
	}

	public void setFinancialYearCustomName(String financialYearCustomName) {
		this.financialYearCustomName = financialYearCustomName;
	}

	public LocalDate getStartingFrom() {
		return startingFrom;
	}

	public void setStartingFrom(LocalDate startingFrom) {
		this.startingFrom = startingFrom;
	}

	public LocalDate getEndingOn() {
		return endingOn;
	}

	public void setEndingOn(LocalDate endingOn) {
		this.endingOn = endingOn;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
