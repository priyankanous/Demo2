package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.Currency;
import com.nous.rollingrevenue.vo.CurrencyVO;

@Component
public class CurrencyConverter {

	/**
	 * Convert CurrencyVO to Currency
	 * 
	 * @param CurrencyVO
	 * @return Currency
	 */

	public static Currency convertCurrencyVOToCurrency(CurrencyVO currencyVO) {
		Currency currency = new Currency();
		if (currencyVO != null) {
			currency.setCurrencyId(currencyVO.getCurrencyId());
			currency.setCurrency(currencyVO.getCurrency());
			currency.setCurrencyName(currencyVO.getCurrencyName());
			currency.setSymbol(currencyVO.getSymbol());
			currency.setConversionRate(currencyVO.getConversionRate());
			currency.setFinancialYear(FinancialYearConverter.convertFinancialYearVOToFinancialYear(currencyVO.getFinancialYear()));
			currency.setBaseCurrency(currencyVO.getBaseCurrency());
		}
		return currency;
	}

	/**
	 * Convert Currency to CurrencyVO
	 * 
	 * @param Currency
	 * @return CurrencyVO
	 */

	public static CurrencyVO convertCurrencyToCurrencyVO(Currency currency) {
		CurrencyVO currencyVO = new CurrencyVO();
		if (currency != null) {
			currencyVO.setCurrencyId(currency.getCurrencyId());
			currencyVO.setCurrency(currency.getCurrency());
			currencyVO.setCurrencyName(currency.getCurrencyName());
			currencyVO.setSymbol(currency.getSymbol());
			currencyVO.setConversionRate(currency.getConversionRate());
			currencyVO.setFinancialYear(FinancialYearConverter.convertFinancialYearToFinancialYearVO(currency.getFinancialYear()));
			currencyVO.setBaseCurrency(currency.getBaseCurrency());
			currencyVO.setActive(currency.isActive());
		}
		return currencyVO;
	}

}
