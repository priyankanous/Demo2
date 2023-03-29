package com.nous.rollingrevenue.model;

import java.util.HashSet;
import java.util.Set;

import com.nous.rollingrevenue.model.converter.StringSetToStringConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_or_clientcode")
	private String accountOrClientCode;

	@Column(name = "location")
	@Convert(converter = StringSetToStringConverter.class)
	private Set<String> location = new HashSet<>();

	public Account() {

	}

	public Account(Long accountId, String accountName, String accountOrClientCode, Set<String> location) {
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
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", accountOrClientCode="
				+ accountOrClientCode + ", location=" + location + "]";
	}

}
