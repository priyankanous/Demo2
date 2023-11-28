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
import lombok.Data;

@Entity
@Table(name = "financial_year")
@EntityListeners(AuditingEntityListener.class)
@Data
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
	private List<CurrencyEntity> currencies = new ArrayList<>();

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

}
