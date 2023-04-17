
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

	private Long holidayId;

	@NotEmpty(message = "HolidayName must not be empty")
	private String holidayName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MMM/yyyy")
	private LocalDate holidayDate;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String holidayDay;

	private FinancialYearVO financialYear;
	
	private LocationVO location;

	private boolean isActive;

	public HolidayCalendarVO() {

	}

	public HolidayCalendarVO(Long holidayId, String holidayName, LocalDate holidayDate, String holidayDay, FinancialYearVO financialYear,
			LocationVO location, boolean isActive) {
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

	public FinancialYearVO getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYearVO financialYear) {
		this.financialYear = financialYear;
	}

	public LocationVO getLocation() {
		return location;
	}

	public void setLocation(LocationVO location) {
		this.location = location;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
