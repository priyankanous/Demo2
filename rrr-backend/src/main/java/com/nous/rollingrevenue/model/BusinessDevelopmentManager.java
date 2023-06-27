package com.nous.rollingrevenue.model;

import java.time.LocalDate;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "business_development_manager")
@EntityListeners(AuditingEntityListener.class)
public class BusinessDevelopmentManager extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bdm_id")
	private Long bdmId;

	@Column(name = "bdm_name")
	private String bdmName;

	@Column(name = "bdm_display_name")
	private String bdmDisplayName;

	@Column(name = "active_from")
	private LocalDate activeFrom;

	@Column(name = "active_until")
	private LocalDate activeUntil;

	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "business_development_manager_to_business_unit", joinColumns = @JoinColumn(name = "bdm_id"), inverseJoinColumns = @JoinColumn(name = "bu_id"))
	private List<BusinessUnit> businessUnits = new ArrayList<>();

	@ManyToMany
	@JsonBackReference
	@JoinTable(name = "business_development_manager_to_region", joinColumns = @JoinColumn(name = "bdm_id"), inverseJoinColumns = @JoinColumn(name = "region_id"))
	private List<Region> regions = new ArrayList<>();

	@OneToMany(mappedBy = "businessDevelopmentManager")
	@JsonBackReference
	private List<BDMMeeting> bdmMeetings = new ArrayList<>();

	@OneToMany(mappedBy = "businessDevelopmentManager")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "businessDevelopmentManager")
	@JsonBackReference
	private List<RevenueEntry> revenueEntry = new ArrayList<>();

	public BusinessDevelopmentManager() {

	}

	public BusinessDevelopmentManager(Long bdmId, String bdmName, String bdmDisplayName, LocalDate activeFrom,
			LocalDate activeUntil, List<BusinessUnit> businessUnits, List<Region> regions, List<BDMMeeting> bdmMeetings,
			List<AnnualTargetEntry> annualTargetEntries) {
		super();
		this.bdmId = bdmId;
		this.bdmName = bdmName;
		this.bdmDisplayName = bdmDisplayName;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.businessUnits = businessUnits;
		this.regions = regions;
		this.bdmMeetings = bdmMeetings;
		this.annualTargetEntries = annualTargetEntries;
	}

	public Long getBdmId() {
		return bdmId;
	}

	public void setBdmId(Long bdmId) {
		this.bdmId = bdmId;
	}

	public String getBdmName() {
		return bdmName;
	}

	public void setBdmName(String bdmName) {
		this.bdmName = bdmName;
	}

	public String getBdmDisplayName() {
		return bdmDisplayName;
	}

	public void setBdmDisplayName(String bdmDisplayName) {
		this.bdmDisplayName = bdmDisplayName;
	}

	public LocalDate getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(LocalDate activeFrom) {
		this.activeFrom = activeFrom;
	}

	public LocalDate getActiveUntil() {
		return activeUntil;
	}

	public void setActiveUntil(LocalDate activeUntil) {
		this.activeUntil = activeUntil;
	}

	public List<BusinessUnit> getBusinessUnits() {
		return businessUnits;
	}

	public void setBusinessUnits(List<BusinessUnit> businessUnits) {
		this.businessUnits = businessUnits;
	}

	public List<Region> getRegions() {
		return regions;
	}

	public void setRegions(List<Region> regions) {
		this.regions = regions;
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

}
