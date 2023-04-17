package com.nous.rollingrevenue.model;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "holiday_calendar")
@EntityListeners(AuditingEntityListener.class)
public class HolidayCalendar extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "holiday_id")
	private Long holidayId;

	@Column(name = "holiday_name")
	private String holidayName;

	@Column(name = "holiday_date")
	private LocalDate holidayDate;

	@Column(name = "holiday_day")
	private String holidayDay;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "location_id", referencedColumnName = "location_id")
	private Location location;


	public HolidayCalendar() {

	}

	public HolidayCalendar(Long holidayId, String holidayName, LocalDate holidayDate, String holidayDay,
			FinancialYear financialYear, Location location) {
		super();
		this.holidayId = holidayId;
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
		this.holidayDay = holidayDay;
		this.financialYear = financialYear;
		this.location = location;
	}

	public Long getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public LocalDate getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(LocalDate holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayDay() {
		return holidayDay;
	}

	public void setHolidayDay(String holidayDay) {
		this.holidayDay = holidayDay;
	}

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}


}
