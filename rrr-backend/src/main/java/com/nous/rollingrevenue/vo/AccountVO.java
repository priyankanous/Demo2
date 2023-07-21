package com.nous.rollingrevenue.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class AccountVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long accountId;

	@NotBlank(message = "AccountName cannot be null or empty")
	private String accountName;

<<<<<<< Updated upstream
	@NotBlank(message = "AccountOrClientCode cannot be null or empty")
	private String accountOrClientCode;

	private RegionVO regions;
=======
	private List<RegionVO> regions = new ArrayList<>();
>>>>>>> Stashed changes

	private boolean isActive;

	public AccountVO() {

	}

	public AccountVO(Long accountId, @NotBlank(message = "AccountName cannot be null or empty") String accountName,
<<<<<<< Updated upstream
			@NotBlank(message = "AccountOrClientCode cannot be null or empty") String accountOrClientCode,
			RegionVO regions, boolean isActive) {
=======
			List<RegionVO> regions, boolean isActive) {
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
	public String getAccountOrClientCode() {
		return accountOrClientCode;
	}

	public void setAccountOrClientCode(String accountOrClientCode) {
		this.accountOrClientCode = accountOrClientCode;
	}

	public RegionVO getRegions() {
=======
	public List<RegionVO> getRegions() {
>>>>>>> Stashed changes
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
