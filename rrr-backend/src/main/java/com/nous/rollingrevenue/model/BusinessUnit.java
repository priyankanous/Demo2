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
@Table(name = "business_unit")
@EntityListeners(AuditingEntityListener.class)
public class BusinessUnit extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bu_id")
	private Long businessUnitId;

	@Column(name = "bu_name")
	private String businessUnitName;

	@Column(name = "bu_display_name")
	private String businessUnitDisplayName;

	@ManyToMany(mappedBy = "businessUnits")
	@JsonManagedReference
	private List<BusinessDevelopmentManager> businessDevlopmentManagers = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<StrategicBusinessUnit> strategicBusinessUnits = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<CocPractice> cocPractices = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "businessUnit")
	@JsonBackReference
	private List<RevenueResourceEntry> revenueResourceEntry = new ArrayList<>();

	public BusinessUnit() {

	}

	public BusinessUnit(Long businessUnitId, String businessUnitName, String businessUnitDisplayName,
			List<BusinessDevelopmentManager> businessDevlopmentManagers,
			List<StrategicBusinessUnit> strategicBusinessUnits, List<CocPractice> cocPractices,
			List<AnnualTargetEntry> annualTargetEntries) {
		super();
		this.businessUnitId = businessUnitId;
		this.businessUnitName = businessUnitName;
		this.businessUnitDisplayName = businessUnitDisplayName;
		this.businessDevlopmentManagers = businessDevlopmentManagers;
		this.strategicBusinessUnits = strategicBusinessUnits;
		this.cocPractices = cocPractices;
		this.annualTargetEntries = annualTargetEntries;
	}

	public Long getBusinessUnitId() {
		return businessUnitId;
	}

	public void setBusinessUnitId(Long businessUnitId) {
		this.businessUnitId = businessUnitId;
	}

	public String getBusinessUnitName() {
		return businessUnitName;
	}

	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}

	public String getBusinessUnitDisplayName() {
		return businessUnitDisplayName;
	}

	public void setBusinessUnitDisplayName(String businessUnitDisplayName) {
		this.businessUnitDisplayName = businessUnitDisplayName;
	}

	public List<BusinessDevelopmentManager> getBusinessDevlopmentManagers() {
		return businessDevlopmentManagers;
	}

	public void setBusinessDevlopmentManagers(List<BusinessDevelopmentManager> businessDevlopmentManagers) {
		this.businessDevlopmentManagers = businessDevlopmentManagers;
	}

	public List<StrategicBusinessUnit> getStrategicBusinessUnits() {
		return strategicBusinessUnits;
	}

	public void setStrategicBusinessUnits(List<StrategicBusinessUnit> strategicBusinessUnits) {
		this.strategicBusinessUnits = strategicBusinessUnits;
	}

	public List<CocPractice> getCocPractices() {
		return cocPractices;
	}

	public void setCocPractices(List<CocPractice> cocPractices) {
		this.cocPractices = cocPractices;
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
