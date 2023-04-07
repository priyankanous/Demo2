package com.nous.rollingrevenue.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public Location() {

	}

	public Location(Long locationId, String locationName, String locationDisplayName, boolean isActive) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.locationDisplayName = locationDisplayName;
		this.isActive = isActive;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", locationName=" + locationName + ", locationDisplayName="
				+ locationDisplayName + ", isActive=" + isActive + "]";
	}

}
