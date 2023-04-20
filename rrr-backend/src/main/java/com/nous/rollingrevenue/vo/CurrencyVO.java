package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class CurrencyVO implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = 1L;

	private Long currencyId;

	@NotBlank(message = "Currency cannot be null or empty")
	private String currency;

	@NotBlank(message = "CurrencyName cannot be null or empty")
	private String currencyName;

	@NotBlank(message = "Symbol cannot be null or empty")
	private String symbol;

	private BigDecimal conversionRate;

	private FinancialYearVO financialYear;

	private boolean baseCurrency;

	private boolean isActive;

	public CurrencyVO() {

	}

	public CurrencyVO(Long currencyId, @NotBlank(message = "Currency cannot be null or empty") String currency,
			@NotBlank(message = "CurrencyName cannot be null or empty") String currencyName,
			@NotBlank(message = "Symbol cannot be null or empty") String symbol, BigDecimal conversionRate,
			FinancialYearVO financialYear, boolean baseCurrency, boolean isActive) {
		super();
		this.currencyId = currencyId;
		this.currency = currency;
		this.currencyName = currencyName;
		this.symbol = symbol;
		this.conversionRate = conversionRate;
		this.financialYear = financialYear;
		this.baseCurrency = baseCurrency;
		this.isActive = isActive;
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

	public FinancialYearVO getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(FinancialYearVO financialYear) {
		this.financialYear = financialYear;
	}

	public boolean isBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(boolean baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
