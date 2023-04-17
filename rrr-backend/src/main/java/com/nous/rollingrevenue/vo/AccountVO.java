package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class AccountVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long accountId;

	@NotBlank(message = "AccountName cannot be null or empty")
	private String accountName;

	@NotBlank(message = "AccountOrClientCode cannot be null or empty")
	private String accountOrClientCode;

	private List<LocationVO> locations = new ArrayList<>();

	private boolean isActive;

	public AccountVO() {

	}

	public AccountVO(Long accountId, String accountName, String accountOrClientCode, Set<String> location,
			List<LocationVO> locations, boolean isActive) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountOrClientCode = accountOrClientCode;
		this.locations = locations;
		this.isActive = isActive;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountOrClientCode() {
		return accountOrClientCode;
	}

	public void setAccountOrClientCode(String accountOrClientCode) {
		this.accountOrClientCode = accountOrClientCode;
	}

	public List<LocationVO> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationVO> locations) {
		this.locations = locations;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


}
