package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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

	@ManyToMany(mappedBy = "locations")
	private List<Account> accounts = new ArrayList<>();

	@OneToMany(mappedBy = "location")
	private List<HolidayCalendar> holidayCalendar = new ArrayList<>();

	@OneToMany(mappedBy = "location")
	private List<RollingRevenueCommonEntry> rollingRevenueCommonEntry = new ArrayList<>();

	public Location() {

	}

	public Location(Long locationId, String locationName, String locationDisplayName, List<Account> accounts,
			List<HolidayCalendar> holidayCalendar, List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationDisplayName = locationDisplayName;
		this.accounts = accounts;
		this.holidayCalendar = holidayCalendar;
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
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

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<HolidayCalendar> getHolidayCalendar() {
		return holidayCalendar;
	}

	public void setHolidayCalendar(List<HolidayCalendar> holidayCalendar) {
		this.holidayCalendar = holidayCalendar;
	}

	public List<RollingRevenueCommonEntry> getRollingRevenueCommonEntry() {
		return rollingRevenueCommonEntry;
	}

	public void setRollingRevenueCommonEntry(List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
	}

}
