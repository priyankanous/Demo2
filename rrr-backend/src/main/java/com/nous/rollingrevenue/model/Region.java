package com.nous.rollingrevenue.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "region")
public class Region {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Long regionId;
	
	@Column(name = "region_name")
	private String regionName;
	
	@Column(name = "region_display_name")
	private String regionDisplayName;

	public Region() {

	}

	public Region(Long regionId, String regionName, String regionDisplayName) {
		this.regionId = regionId;
		this.regionName = regionName;
		this.regionDisplayName = regionDisplayName;
	}

	public Long getRegionId() {
		return regionId;
	}

	public void setRegionId(Long regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getRegionDisplayName() {
		return regionDisplayName;
	}

	public void setRegionDisplayName(String regionDisplayName) {
		this.regionDisplayName = regionDisplayName;
	}

	@Override
	public String toString() {
		return "Region [regionId=" + regionId + ", regionName=" + regionName + ", regionDisplayName="
				+ regionDisplayName + "]";
	}
	

}
