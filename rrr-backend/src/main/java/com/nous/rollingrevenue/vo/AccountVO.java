package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class AccountVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long accountId;

	@NotBlank(message = "AccountName cannot be null or empty")
	private String accountName;

	private RegionVO regions;

	private boolean isActive;

	public AccountVO() {

	}

	public AccountVO(Long accountId, String accountName, RegionVO regions, boolean isActive) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.regions = regions;
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

	public RegionVO getRegions() {
		return regions;
	}

	public void setRegions(RegionVO regions) {
		this.regions = regions;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
