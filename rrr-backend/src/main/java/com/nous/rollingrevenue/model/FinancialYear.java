package com.nous.rollingrevenue.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@OneToMany(mappedBy = "financialYear")
	@JsonBackReference
	private List<Currency> currencies = new ArrayList<>();

	@OneToMany(mappedBy = "financialYear")
	@JsonBackReference
	private List<GlobalMonthlyLeaveLossFactor> leaveLossFactors = new ArrayList<>();

	@OneToMany(mappedBy = "financialYear")
	@JsonBackReference
	private List<HolidayCalendar> holidayCalendar = new ArrayList<>();

	@OneToMany(mappedBy = "financialYear")
	@JsonBackReference
	private List<BDMMeeting> bdmMeetings = new ArrayList<>();

	@OneToMany(mappedBy = "financialYear")
	@JsonBackReference
	private List<FortnightlyMeeting> fortnightlyMeetings = new ArrayList<>();

	@OneToMany(mappedBy = "financialYear")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "financialYear")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

	public FinancialYear() {

	}

	public FinancialYear(Long financialYearId, String financialYearName, String financialYearCustomName,
			LocalDate startingFrom, LocalDate endingOn, List<Currency> currencies,
			List<GlobalMonthlyLeaveLossFactor> leaveLossFactors, List<HolidayCalendar> holidayCalendar,
			List<BDMMeeting> bdmMeetings, List<FortnightlyMeeting> fortnightlyMeetings,
			List<AnnualTargetEntry> annualTargetEntries) {

		super();
		this.financialYearId = financialYearId;
		this.financialYearName = financialYearName;
		this.financialYearCustomName = financialYearCustomName;
		this.startingFrom = startingFrom;
		this.endingOn = endingOn;
		this.currencies = currencies;
		this.leaveLossFactors = leaveLossFactors;
		this.holidayCalendar = holidayCalendar;
		this.bdmMeetings = bdmMeetings;
		this.fortnightlyMeetings = fortnightlyMeetings;
		this.annualTargetEntries = annualTargetEntries;
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

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

	public List<GlobalMonthlyLeaveLossFactor> getLeaveLossFactors() {
		return leaveLossFactors;
	}

	public void setLeaveLossFactors(List<GlobalMonthlyLeaveLossFactor> leaveLossFactors) {
		this.leaveLossFactors = leaveLossFactors;
	}

	public List<HolidayCalendar> getHolidayCalendar() {
		return holidayCalendar;
	}

	public void setHolidayCalendar(List<HolidayCalendar> holidayCalendar) {
		this.holidayCalendar = holidayCalendar;
	}

	public List<BDMMeeting> getBdmMeetings() {
		return bdmMeetings;
	}

	public void setBdmMeetings(List<BDMMeeting> bdmMeetings) {
		this.bdmMeetings = bdmMeetings;
	}

	public List<FortnightlyMeeting> getFortnightlyMeetings() {
		return fortnightlyMeetings;
	}

	public void setFortnightlyMeetings(List<FortnightlyMeeting> fortnightlyMeetings) {
		this.fortnightlyMeetings = fortnightlyMeetings;
	}

	public List<AnnualTargetEntry> getAnnualTargetEntries() {
		return annualTargetEntries;
	}

	public void setAnnualTargetEntries(List<AnnualTargetEntry> annualTargetEntries) {
		this.annualTargetEntries = annualTargetEntries;
	}

	public List<RevenueEntry> getRevenueEntry() {
		return revenueEntry;
	}

	public void setRevenueEntry(List<RevenueEntry> revenueEntry) {
		this.revenueEntry = revenueEntry;
	}

}
