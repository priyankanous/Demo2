package com.nous.rollingrevenue.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
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

	@JsonProperty(value = "isActive", access = JsonProperty.Access.READ_ONLY)
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
