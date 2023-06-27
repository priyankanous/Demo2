package com.nous.rollingrevenue.model;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "organization")
@EntityListeners(AuditingEntityListener.class)
public class Organization extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "org_id")
	private Long id;

	@Column(name = "org_display_name")
	private String orgDisplayName;

	@Column(name = "org_name")
	private String orgName;

	@OneToMany(mappedBy = "organization")
	@JsonBackReference
	private List<BusinessUnit> businessUnits = new ArrayList<>();


	public Organization() {

	}

	public Organization(Long id, String orgDisplayName, String orgName, List<BusinessUnit> businessUnits) {
		super();
		this.id = id;
		this.orgDisplayName = orgDisplayName;
		this.orgName = orgName;
		this.businessUnits = businessUnits;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgDisplayName() {
		return orgDisplayName;
	}

	public void setOrgDisplayName(String orgDisplayName) {
		this.orgDisplayName = orgDisplayName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<BusinessUnit> getBusinessUnits() {
		return businessUnits;
	}

	public void setBusinessUnits(List<BusinessUnit> businessUnits) {
		this.businessUnits = businessUnits;
	}


}
