package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class AccountVO implements Serializable {
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long accountId;
	
	@NotBlank(message = "AccountName cannot be null or empty")
	private String accountName;
	
	@NotBlank(message = "AccountOrClientCode cannot be null or empty")
	private String accountOrClientCode;
	
	@NotEmpty(message = "Atleast one Locaiton is required")
	private Set<String> location = new HashSet<>();

	public AccountVO() {

	}

	public AccountVO(Long accountId, String accountName, String accountOrClientCode, Set<String> location) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountOrClientCode = accountOrClientCode;
		this.location = location;
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

	public Set<String> getLocation() {
		return location;
	}

	public void setLocation(Set<String> location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "AccountVO [accountId=" + accountId + ", accountName=" + accountName + ", accountOrClientCode="
				+ accountOrClientCode + ", location=" + location + "]";
	}
	

}
