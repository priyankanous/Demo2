package com.nous.rollingrevenue.model;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	@Column(name = "financial_year")
	private String financialYear;

	@Column(name = "base_currency")
	private String baseCurrency;

	public Currency() {

	}

	public Currency(Long currencyId, String currency, String currencyName, String symbol, BigDecimal conversionRate,
			String financialYear, String baseCurrency) {
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

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@Override
	public String toString() {
		return "Currency [currencyId=" + currencyId + ", currency=" + currency + ", currencyName=" + currencyName
				+ ", symbol=" + symbol + ", conversionRate=" + conversionRate + ", financialYear=" + financialYear
				+ ", baseCurrency=" + baseCurrency + "]";
	}
	

}
