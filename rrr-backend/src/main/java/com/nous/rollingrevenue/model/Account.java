package com.nous.rollingrevenue.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
public class Account extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long accountId;

	@Column(name = "account_name")
	private String accountName;

	@Column(name = "account_or_clientcode")
	private String accountOrClientCode;

	@ManyToMany
	@JoinTable(name="accounts_to_location",
	joinColumns = @JoinColumn(name="account_id"),
	inverseJoinColumns = @JoinColumn(name="location_id"))
	private List<Location> locations = new ArrayList<>();

	@OneToMany(mappedBy = "account")
	private List<Opportunity> opportunities = new ArrayList<>();


	public Account() {

	}

	public Account(Long accountId, String accountName, String accountOrClientCode, List<Location> locations, List<Opportunity> opportunities) {
		super();
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountOrClientCode = accountOrClientCode;
		this.locations = locations;
		this.opportunities = opportunities;
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

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
	public List<Opportunity> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<Opportunity> opportunities) {
		this.opportunities = opportunities;
	}
	
}
