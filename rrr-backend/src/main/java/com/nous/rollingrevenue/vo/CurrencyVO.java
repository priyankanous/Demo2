package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class CurrencyVO implements Serializable{
	
	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long currencyId;
	
	@NotBlank(message = "Currency cannot be null or empty")
	private String currency;
	
	@NotBlank(message = "CurrencyName cannot be null or empty")
	private String currencyName;
	
	@NotBlank(message = "Symbol cannot be null or empty")
	private String symbol;
	
	private BigDecimal conversionRate;
	
	@NotBlank(message = "FinancialYear cannot be null or empty")
	private String financialYear;
	
	@NotBlank(message = "BaseCurrency cannot be null or empty")
	private String baseCurrency;

	public CurrencyVO() {

	}

	public CurrencyVO(Long currencyId, String currency, String currencyName, String symbol, BigDecimal conversionRate,
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
		return "CurrencyVO [currencyId=" + currencyId + ", currency=" + currency + ", currencyName=" + currencyName
				+ ", symbol=" + symbol + ", conversionRate=" + conversionRate + ", financialYear=" + financialYear
				+ ", baseCurrency=" + baseCurrency + "]";
	}
	

}
