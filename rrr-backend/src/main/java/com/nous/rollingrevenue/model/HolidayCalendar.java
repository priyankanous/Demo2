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
	
	@Column(name = "year")
	private String year;


	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public HolidayCalendar() {

	}

	public HolidayCalendar(Long holidayId, String holidayName, LocalDate holidayDate, String holidayDay,
			String year, boolean isActive) {
		super();
		this.holidayId = holidayId;
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
		this.holidayDay = holidayDay;
		this.year = year;
		this.isActive = isActive;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "HolidayCalendar [holidayId=" + holidayId + ", holidayName=" + holidayName + ", holidayDate="
				+ holidayDate + ", holidayDay=" + holidayDay + ", year=" + year + ", isActive=" + isActive + "]";
	}


}
