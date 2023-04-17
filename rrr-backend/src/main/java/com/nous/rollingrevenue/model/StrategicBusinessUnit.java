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
@Table(name = "strategic_business_unit")
@EntityListeners(AuditingEntityListener.class)
public class StrategicBusinessUnit extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sbu_id")
	private Long sbuId;

	@Column(name = "sbu_name")
	private String sbuName;

	@Column(name = "sbu_display_name")
	private String sbuDisplayName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bu_id", referencedColumnName = "bu_id")
	private BusinessUnit businessUnit;
	
	@OneToMany(mappedBy = "strategicbusinessUnit")
	private List<StrategicBusinessUnitHead> strategicBusinessUnitHeads = new ArrayList<>();


	public StrategicBusinessUnit() {

	}

	public StrategicBusinessUnit(Long sbuId, String sbuName, String sbuDisplayName, BusinessUnit businessUnit, List<StrategicBusinessUnitHead> strategicBusinessUnitHeads) {
		super();
		this.sbuId = sbuId;
		this.sbuName = sbuName;
		this.sbuDisplayName = sbuDisplayName;
		this.businessUnit = businessUnit;
		this.strategicBusinessUnitHeads = strategicBusinessUnitHeads;
	}

	public Long getSbuId() {
		return sbuId;
	}

	public void setSbuId(Long sbuId) {
		this.sbuId = sbuId;
	}

	public String getSbuName() {
		return sbuName;
	}

	public void setSbuName(String sbuName) {
		this.sbuName = sbuName;
	}

	public String getSbuDisplayName() {
		return sbuDisplayName;
	}

	public void setSbuDisplayName(String sbuDisplayName) {
		this.sbuDisplayName = sbuDisplayName;
	}

	public BusinessUnit getBusinessUnit() {
		return businessUnit;
	}

	public void setBusinessUnit(BusinessUnit businessUnit) {
		this.businessUnit = businessUnit;
	}

	public List<StrategicBusinessUnitHead> getStrategicBusinessUnitHeads() {
		return strategicBusinessUnitHeads;
	}

	public void setStrategicBusinessUnitHeads(List<StrategicBusinessUnitHead> strategicBusinessUnitHeads) {
		this.strategicBusinessUnitHeads = strategicBusinessUnitHeads;
	}


}
