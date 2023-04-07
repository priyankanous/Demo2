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
@Table(name = "ORGANIZATION")
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

	@Column(name = "is_active")
	private boolean isActive = Boolean.TRUE;

	public Organization() {

	}

	public Organization(Long id, String orgDisplayName, String orgName, boolean isActive) {
		super();
		this.id = id;
		this.orgDisplayName = orgDisplayName;
		this.orgName = orgName;
		this.isActive = isActive;
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

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", orgDisplayName=" + orgDisplayName + ", orgName=" + orgName + ", isActive="
				+ isActive + "]";
	}

}
