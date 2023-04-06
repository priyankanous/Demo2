package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class FinancialYearVO implements Serializable {
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long financialYearId;
	
	@NotBlank(message = "FinancialYearName cannot be null or empty")
	private String financialYearName;
	
	@NotBlank(message = "FinancialYearCustomName cannot be null or empty")
	private String financialYearCustomName;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate startingFrom;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate endingOn;

	public FinancialYearVO() {

	}

	public FinancialYearVO(Long financialYearId, String financialYearName, String financialYearCustomName,
			LocalDate startingFrom, LocalDate endingOn) {
		this.financialYearId = financialYearId;
		this.financialYearName = financialYearName;
		this.financialYearCustomName = financialYearCustomName;
		this.startingFrom = startingFrom;
		this.endingOn = endingOn;
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

	@Override
	public String toString() {
		return "FinancialYearVO [financialYearId=" + financialYearId + ", financialYearName=" + financialYearName
				+ ", financialYearCustomName=" + financialYearCustomName + ", startingFrom=" + startingFrom
				+ ", endingOn=" + endingOn + "]";
	}
	

}
