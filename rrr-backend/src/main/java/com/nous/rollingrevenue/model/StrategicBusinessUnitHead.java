package com.nous.rollingrevenue.model;

import java.time.LocalDate;
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
@Table(name = "strategic_business_unit_head")
@EntityListeners(AuditingEntityListener.class)
public class StrategicBusinessUnitHead extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_head_id")
	private Long sbuHeadId;

	@Column(name = "sbu_head_name")
	private String sbuHeadName;

	@Column(name = "sbu_head_display_name")
	private String sbuHeadDisplayName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sbu_id", referencedColumnName = "sbu_id")
	private StrategicBusinessUnit strategicbusinessUnit;

	@Column(name = "active_from")
	private LocalDate activeFrom;

	@Column(name = "active_until")
	private LocalDate activeUntil;

	@OneToMany(mappedBy = "strategicBusinessUnitHead")
	private List<RollingRevenueCommonEntry> rollingRevenueCommonEntry = new ArrayList<>();

	public StrategicBusinessUnitHead() {

	}

	public StrategicBusinessUnitHead(Long sbuHeadId, String sbuHeadName, String sbuHeadDisplayName,
			StrategicBusinessUnit strategicbusinessUnit, LocalDate activeFrom, LocalDate activeUntil,
			List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		super();
		this.sbuHeadId = sbuHeadId;
		this.sbuHeadName = sbuHeadName;
		this.sbuHeadDisplayName = sbuHeadDisplayName;
		this.strategicbusinessUnit = strategicbusinessUnit;
		this.activeFrom = activeFrom;
		this.activeUntil = activeUntil;
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
	}

	public Long getSbuHeadId() {
		return sbuHeadId;
	}

	public void setSbuHeadId(Long sbuHeadId) {
		this.sbuHeadId = sbuHeadId;
	}

	public String getSbuHeadName() {
		return sbuHeadName;
	}

	public void setSbuHeadName(String sbuHeadName) {
		this.sbuHeadName = sbuHeadName;
	}

	public String getSbuHeadDisplayName() {
		return sbuHeadDisplayName;
	}

	public void setSbuHeadDisplayName(String sbuHeadDisplayName) {
		this.sbuHeadDisplayName = sbuHeadDisplayName;
	}

	public StrategicBusinessUnit getStrategicbusinessUnit() {
		return strategicbusinessUnit;
	}

	public void setStrategicbusinessUnit(StrategicBusinessUnit strategicbusinessUnit) {
		this.strategicbusinessUnit = strategicbusinessUnit;
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

	public List<RollingRevenueCommonEntry> getRollingRevenueCommonEntry() {
		return rollingRevenueCommonEntry;
	}

	public void setRollingRevenueCommonEntry(List<RollingRevenueCommonEntry> rollingRevenueCommonEntry) {
		this.rollingRevenueCommonEntry = rollingRevenueCommonEntry;
	}

}
