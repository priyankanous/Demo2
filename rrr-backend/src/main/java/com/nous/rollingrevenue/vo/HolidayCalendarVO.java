
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

	@NotEmpty(message = "FinancialYear must not be empty")
	private String financialYear;
	
	@NotEmpty(message = "Location must not be empty")
	private String location;

	private boolean isActive;

	public HolidayCalendarVO() {

	}

	public HolidayCalendarVO(Long holidayId, String holidayName, LocalDate holidayDate, String holidayDay, String financialYear,
			String location, boolean isActive) {
		super();
		this.holidayId = holidayId;
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
		this.holidayDay = holidayDay;
		this.financialYear = financialYear;
		this.location = location;
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

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "HolidayCalendarVO [holidayId=" + holidayId + ", holidayName=" + holidayName + ", holidayDate="
				+ holidayDate + ", holidayDay=" + holidayDay + ", financialYear=" + financialYear + ", location="
				+ location + ", isActive=" + isActive + "]";
	}
	

}
