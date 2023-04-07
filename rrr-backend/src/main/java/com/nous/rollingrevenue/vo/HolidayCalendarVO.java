
package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

public class HolidayCalendarVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long holidayId;

	@NotEmpty(message = "HolidayName must not be empty")
	private String holidayName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate holidayDate;

	@NotEmpty(message = "HolidayDay must not be empty")
	private String holidayDay;

	@NotEmpty(message = "Year must not be empty")
	private String year;

	private boolean isActive;

	public HolidayCalendarVO() {

	}

	public HolidayCalendarVO(Long holidayId, String holidayName, LocalDate holidayDate, String holidayDay, String year,
			boolean isActive) {
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
		return "HolidayCalendarVO [holidayId=" + holidayId + ", holidayName=" + holidayName + ", holidayDate="
				+ holidayDate + ", holidayDay=" + holidayDay + ", year=" + year + ", isActive=" + isActive + "]";
	}
	

}
