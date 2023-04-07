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

	private boolean isActive;

	public OrganizationVO() {

	}

	public OrganizationVO(Long id, String orgName, String orgDisplayName, boolean isActive) {
		super();
		this.id = id;
		this.orgName = orgName;
		this.orgDisplayName = orgDisplayName;
		this.isActive = isActive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDisplayName() {
		return orgDisplayName;
	}

	public void setOrgDisplayName(String orgDisplayName) {
		this.orgDisplayName = orgDisplayName;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "OrganizationVO [id=" + id + ", orgName=" + orgName + ", orgDisplayName=" + orgDisplayName
				+ ", isActive=" + isActive + "]";
	}
	

}