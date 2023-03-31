package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;

/**
 * @author Nous Infosystems
 *
 */
public class OrganizationVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 2204495143644490674L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;

	@NotEmpty(message = "oganization name must not be empty")
	private String orgName;
	@NotEmpty(message = "organization display name must not be empty")
	private String orgDisplayName;
	/**
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
	 * @return the orgName
	 */
	public String getorgName() {
		return orgName;
	}
	/**
	 * @param id the id to set
	 */
	public void setorgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the orgName
	 */
	public String getorgDisplayName() {
		return orgDisplayName;
	}

	/**
	 * @param orgDisplayName the orgDisplayName to set
	 */
	public void setorgDisplayName(String orgDisplayName) {
		this.orgDisplayName = orgDisplayName;
	}

	/**
	 * toString
	 */
	@Override
	public String toString() {
		return "Organization [id =" + id + "   org name=" + orgName + " org Display Name=" + orgDisplayName + "]";
	}

}