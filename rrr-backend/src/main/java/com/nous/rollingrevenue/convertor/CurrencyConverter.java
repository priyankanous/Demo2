package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.CurrencyEntity;
import com.nous.rollingrevenue.vo.CurrencyVO;

@Component
public class CurrencyConverter {

	private CurrencyConverter() {
		super();
	}

	/**
	 * Convert CurrencyVO to Currency
	 * 
	 * @param CurrencyVO
	 * @return Currency
	 */

	public static CurrencyEntity convertCurrencyVOToCurrency(CurrencyVO currencyVO) {
		CurrencyEntity currency = new CurrencyEntity();
		if (currencyVO != null) {
			currency.setCurrencyId(currencyVO.getCurrencyId());
			currency.setCurrency(currencyVO.getCurrency());
			currency.setCurrencyName(currencyVO.getCurrencyName());
			currency.setSymbol(currencyVO.getSymbol());
			currency.setConversionRate(currencyVO.getConversionRate());
			currency.setFinancialYear(
					FinancialYearConverter.convertFinancialYearVOToFinancialYear(currencyVO.getFinancialYear()));
			currency.setBaseCurrency(currencyVO.isBaseCurrency());
		}
		return currency;
	}

	/**
	 * Convert Currency to CurrencyVO
	 * 
	 * @param CurrencyEntity
	 * @return CurrencyVO
	 */

	public static CurrencyVO convertCurrencyToCurrencyVO(CurrencyEntity currency) {
		CurrencyVO currencyVO = new CurrencyVO();
		if (currency != null) {
			currencyVO.setCurrencyId(currency.getCurrencyId());
			currencyVO.setCurrency(currency.getCurrency());
			currencyVO.setCurrencyName(currency.getCurrencyName());
			currencyVO.setSymbol(currency.getSymbol());
			currencyVO.setConversionRate(currency.getConversionRate());
			currencyVO.setFinancialYear(
					FinancialYearConverter.convertFinancialYearToFinancialYearVO(currency.getFinancialYear()));
			currencyVO.setBaseCurrency(currency.isBaseCurrency());
			currencyVO.setActive(currency.isActive());
		}
		return currencyVO;
	}

}
