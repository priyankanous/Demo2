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
@Table(name = "region")
@EntityListeners(AuditingEntityListener.class)
public class Region extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "region_id")
	private Long regionId;

	@Column(name = "region_name")
	private String regionName;

	@Column(name = "region_display_name")
	private String regionDisplayName;

	@ManyToMany(mappedBy = "regions")
	private List<BusinessDevelopmentManager> businessDevlopmentManagers = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	private List<BDMMeeting> bdmMeetings = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	private List<RollingRevenueCommonEntry> rollingRevenueCommonEntry = new ArrayList<>();

	public Region() {

	}

	public Region(Long regionId, String regionName, String regionDisplayName,
			List<BusinessDevelopmentManager> businessDevlopmentManagers, List<BDMMeeting> bdmMeetings,
			List<AnnualTargetEntry> annualTargetEntries, List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.regionDisplayName = regionDisplayName;
		this.businessDevlopmentManagers = businessDevlopmentManagers;
		this.bdmMeetings = bdmMeetings;
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
		this.annualTargetEntries = annualTargetEntries;
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

	public List<BusinessDevelopmentManager> getBusinessDevlopmentManagers() {
		return businessDevlopmentManagers;
	}

	public void setBusinessDevlopmentManagers(List<BusinessDevelopmentManager> businessDevlopmentManagers) {
		this.businessDevlopmentManagers = businessDevlopmentManagers;
	}

	public List<BDMMeeting> getBdmMeetings() {
		return bdmMeetings;
	}

	public void setBdmMeetings(List<BDMMeeting> bdmMeetings) {
		this.bdmMeetings = bdmMeetings;
	}

	public List<RollingRevenueCommonEntry> getRollingRevenueCommonEntry() {
		return rollingRevenueCommonEntry;
	}

	public void setRollingRevenueCommonEntry(List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
	}

	public List<AnnualTargetEntry> getAnnualTargetEntries() {
		return annualTargetEntries;
	}

	public void setAnnualTargetEntries(List<AnnualTargetEntry> annualTargetEntries) {
		this.annualTargetEntries = annualTargetEntries;
	}

}
