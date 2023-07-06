package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@JsonManagedReference
	private List<Account> accounts = new ArrayList<>();

	@ManyToMany(mappedBy = "regions")
	@JsonManagedReference
	private List<BusinessDevelopmentManager> businessDevlopmentManagers = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	@JsonBackReference
	private List<BDMMeeting> bdmMeetings = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "region")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

	public Region() {

	}

	public Region(Long regionId, String regionName, String regionDisplayName, List<Account> accounts,
			List<BusinessDevelopmentManager> businessDevlopmentManagers, List<BDMMeeting> bdmMeetings,
			List<AnnualTargetEntry> annualTargetEntries, List<RevenueEntry> revenueEntry) {
		super();
		this.regionId = regionId;
		this.regionName = regionName;
		this.regionDisplayName = regionDisplayName;
		this.accounts = accounts;
		this.businessDevlopmentManagers = businessDevlopmentManagers;
		this.bdmMeetings = bdmMeetings;
		this.annualTargetEntries = annualTargetEntries;
		this.revenueEntry = revenueEntry;
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

	public List<AnnualTargetEntry> getAnnualTargetEntries() {
		return annualTargetEntries;
	}

	public void setAnnualTargetEntries(List<AnnualTargetEntry> annualTargetEntries) {
		this.annualTargetEntries = annualTargetEntries;
	}

	public List<RevenueEntry> getRevenueEntry() {
		return revenueEntry;
	}

	public void setRevenueEntry(List<RevenueEntry> revenueEntry) {
		this.revenueEntry = revenueEntry;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
