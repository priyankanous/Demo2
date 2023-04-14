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

	private FinancialYearVO financialYearVO;

	@NotBlank(message = "BaseCurrency cannot be null or empty")
	private String baseCurrency;

	private boolean isActive;

	public CurrencyVO() {

	}

	public CurrencyVO(Long currencyId, String currency, String currencyName, String symbol, BigDecimal conversionRate,
			FinancialYearVO financialYearVO, String baseCurrency, boolean isActive) {
		super();
		this.currencyId = currencyId;
		this.currency = currency;
		this.currencyName = currencyName;
		this.symbol = symbol;
		this.conversionRate = conversionRate;
		this.financialYearVO = financialYearVO;
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

	public FinancialYearVO getFinancialYearVO() {
		return financialYearVO;
	}

	public void setFinancialYearVO(FinancialYearVO financialYearVO) {
		this.financialYearVO = financialYearVO;
	}

	public String getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	@JsonProperty(value="isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "CurrencyVO [currencyId=" + currencyId + ", currency=" + currency + ", currencyName=" + currencyName
				+ ", symbol=" + symbol + ", conversionRate=" + conversionRate + ", financialYearVO=" + financialYearVO
				+ ", baseCurrency=" + baseCurrency + ", isActive=" + isActive + "]";
	}


}
