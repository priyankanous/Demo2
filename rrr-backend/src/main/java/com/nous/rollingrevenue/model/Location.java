package com.nous.rollingrevenue.model;

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
@Table(name = "location")
@EntityListeners(AuditingEntityListener.class)
public class Location extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	private Long locationId;

	@Column(name = "location_name")
	private String locationName;

	@Column(name = "location_display_name")
	private String locationDisplayName;

	@OneToMany(mappedBy = "location")
	@JsonBackReference
	private List<HolidayCalendar> holidayCalendar = new ArrayList<>();

	@OneToMany(mappedBy = "location")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "location")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

	public Location() {

	}

	public Location(Long locationId, String locationName, String locationDisplayName,
			List<HolidayCalendar> holidayCalendar, List<AnnualTargetEntry> annualTargetEntries) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationDisplayName = locationDisplayName;
		this.holidayCalendar = holidayCalendar;
		this.annualTargetEntries = annualTargetEntries;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationDisplayName() {
		return locationDisplayName;
	}

	public void setLocationDisplayName(String locationDisplayName) {
		this.locationDisplayName = locationDisplayName;
	}

	public List<HolidayCalendar> getHolidayCalendar() {
		return holidayCalendar;
	}

	public void setHolidayCalendar(List<HolidayCalendar> holidayCalendar) {
		this.holidayCalendar = holidayCalendar;
	}

	public List<AnnualTargetEntry> getAnnualTargetEntries() {
		return annualTargetEntries;
	}

	public void setAnnualTargetEntries(List<AnnualTargetEntry> annualTargetEntries) {
		this.annualTargetEntries = annualTargetEntries;
	}

	public List<RevenueResourceEntry> getRevenueResourceEntry() {
		return revenueResourceEntry;
	}

	public void setRevenueResourceEntry(List<RevenueResourceEntry> revenueResourceEntry) {
		this.revenueResourceEntry = revenueResourceEntry;
	}

}
