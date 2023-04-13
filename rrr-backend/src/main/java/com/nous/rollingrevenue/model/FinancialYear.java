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
@Table(name = "financial_year")
@EntityListeners(AuditingEntityListener.class)
public class FinancialYear extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fy_id")
	private Long financialYearId;

	@Column(name = "fy_name")
	private String financialYearName;

	@Column(name = "fy_custom_name")
	private String financialYearCustomName;

	@Column(name = "starting_from")
	private LocalDate startingFrom;

	@Column(name = "ending_on")
	private LocalDate endingOn;


	public FinancialYear() {

	}

	public FinancialYear(Long financialYearId, String financialYearName, String financialYearCustomName,
			LocalDate startingFrom, LocalDate endingOn) {
		super();
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
		return "FinancialYear [financialYearId=" + financialYearId + ", financialYearName=" + financialYearName
				+ ", financialYearCustomName=" + financialYearCustomName + ", startingFrom=" + startingFrom
				+ ", endingOn=" + endingOn + "]";
	}

}
