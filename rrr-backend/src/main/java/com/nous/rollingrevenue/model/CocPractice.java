package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "coc_practice")
@EntityListeners(AuditingEntityListener.class)
public class CocPractice extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coc_practice_id")
	private Long cocPracticeId;

	@Column(name = "coc_practice_name")
	private String cocPracticeName;

	@Column(name = "coc_practice_display_name")
	private String cocPracticeDisplayName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;

	@OneToMany(mappedBy = "cocPractice")
	private List<AnnualTargetEntry> annualTargetEntries = new ArrayList<>();

	@OneToMany(mappedBy = "cocPractice")
	private List<RollingRevenueCommonEntry> rollingRevenueCommonEntry = new ArrayList<>();

	public CocPractice() {

	}

	public CocPractice(Long cocPracticeId, String cocPracticeName, String cocPracticeDisplayName,
			BusinessUnit businessUnit, List<AnnualTargetEntry> annualTargetEntries,
			List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		super();
		this.cocPracticeId = cocPracticeId;
		this.cocPracticeName = cocPracticeName;
		this.cocPracticeDisplayName = cocPracticeDisplayName;
		this.businessUnit = businessUnit;
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
		this.annualTargetEntries = annualTargetEntries;
	}

	public Long getCocPracticeId() {
		return cocPracticeId;
	}

	public void setCocPracticeId(Long cocPracticeId) {
		this.cocPracticeId = cocPracticeId;
	}

	public String getCocPracticeName() {
		return cocPracticeName;
	}

	public void setCocPracticeName(String cocPracticeName) {
		this.cocPracticeName = cocPracticeName;
	}

	public String getCocPracticeDisplayName() {
		return cocPracticeDisplayName;
	}

	public void setCocPracticeDisplayName(String cocPracticeDisplayName) {
		this.cocPracticeDisplayName = cocPracticeDisplayName;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
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
