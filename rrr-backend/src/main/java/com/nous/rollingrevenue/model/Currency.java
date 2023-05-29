package com.nous.rollingrevenue.model;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "currency")
@EntityListeners(AuditingEntityListener.class)
public class Currency extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "currency_id")
	private Long currencyId;

	@Column(name = "currency")
	private String currency;

	@Column(name = "currency_name")
	private String currencyName;

	@Column(name = "symbol")
	private String symbol;

	@Column(name = "conversion_rate")
	private BigDecimal conversionRate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fy_id", referencedColumnName = "fy_id")
	private FinancialYear financialYear;

	@Column(name = "base_currency")
	private boolean baseCurrency;

	public Currency() {

	}

	public Currency(Long currencyId, String currency, String currencyName, String symbol, BigDecimal conversionRate,
			FinancialYear financialYear, boolean baseCurrency) {
		super();
		this.currencyId = currencyId;
		this.currency = currency;
		this.currencyName = currencyName;
		this.symbol = symbol;
		this.conversionRate = conversionRate;
		this.financialYear = financialYear;
		this.baseCurrency = baseCurrency;
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getConversionRate() {
		return conversionRate;
	}

	public void setConversionRate(BigDecimal conversionRate) {
		this.conversionRate = conversionRate;
	}

	public FinancialYear getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYear financialYear) {
		this.financialYear = financialYear;
	}

	public boolean isBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(boolean baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

}
