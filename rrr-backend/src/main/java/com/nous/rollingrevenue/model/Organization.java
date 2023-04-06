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

	/**
	 * @param id
	 * @param orgDisplayName
	 * @param orgName
	 */
	public Organization(Long id, String orgDisplayName, String orgName) {
		this.id = id;
		this.orgDisplayName = orgDisplayName;
		this.orgName = orgName;
	}

	/**
	 * 
	 */
	public Organization() {

	}

	/**
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the orgDisplayName
	 */
	public String getorgDisplayName() {
		return orgDisplayName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setorgDisplayName(String orgDisplayName) {
		this.orgDisplayName = orgDisplayName;
	}

	/**
	 * @return the orgName
	 */
	public String getorgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setorgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String toString() {
		return "Organization [id =" + id + "   org disaply name=" + orgDisplayName + " org Name=" + orgName + "]";
	}

}
