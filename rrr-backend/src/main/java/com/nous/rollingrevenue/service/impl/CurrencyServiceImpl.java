package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.CurrencyConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Currency;
import com.nous.rollingrevenue.repository.CurrencyRepository;
import com.nous.rollingrevenue.service.CurrencyService;
import com.nous.rollingrevenue.vo.CurrencyVO;

@Service
@Transactional(readOnly = true)
public class CurrencyServiceImpl implements CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	public List<CurrencyVO> getAllCurrency() {
		List<CurrencyVO> CurrencyVOs = new ArrayList<>();
		currencyRepository.findAll().stream()
				.forEach(currency -> CurrencyVOs.add(CurrencyConverter.convertCurrencyToCurrencyVO(currency)));
		return CurrencyVOs;
	}

	@Override
	@Transactional
	public CurrencyVO saveCurrency(CurrencyVO currencyVO) {
		Currency currency = currencyRepository.save(CurrencyConverter.convertCurrencyVOToCurrency(currencyVO));
		return CurrencyConverter.convertCurrencyToCurrencyVO(currency);
	}

	@Override
	@Transactional
	@CacheEvict(value = "currency", key= "#currencyId")
	public void deleteCurrencyById(Long currencyId) {
		currencyRepository.findById(currencyId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyId));
		currencyRepository.deleteById(currencyId);
	}

	@Override
	@Cacheable(value = "currency", key = "#currencyId")
	public CurrencyVO getCurrencyById(Long currencyId) {
		Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyId));
		return CurrencyConverter.convertCurrencyToCurrencyVO(currency);
	}

	@Override
	@Transactional
	@CachePut(value = "currency", key = "#currencyId")
	public CurrencyVO updateCurrency(Long currencyId, CurrencyVO currencyVO) {
		Currency currency = currencyRepository.findById(currencyId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyId));
		currency.setCurrency(currencyVO.getCurrency());
		currency.setCurrencyName(currencyVO.getCurrencyName());
		currency.setSymbol(currencyVO.getSymbol());
		currency.setConversionRate(currencyVO.getConversionRate());
		currency.setFinancialYear(currencyVO.getFinancialYear());
		currency.setBaseCurrency(currencyVO.getBaseCurrency());
		return CurrencyConverter.convertCurrencyToCurrencyVO(currencyRepository.save(currency));
	}

}
