package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.CurrencyConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Currency;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.repository.CurrencyRepository;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.RevenueEntryRespository;
import com.nous.rollingrevenue.service.CurrencyService;
import com.nous.rollingrevenue.vo.CurrencyVO;

@Service
@Transactional(readOnly = true)
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private RevenueEntryRespository revenueEntryRespository;

	@Override
	public List<CurrencyVO> getAllCurrency() {
		List<CurrencyVO> currencyVOs = new ArrayList<>();
		currencyRepository.findAll().stream()
				.forEach(currency -> currencyVOs.add(CurrencyConverter.convertCurrencyToCurrencyVO(currency)));
		return currencyVOs;
	}

	@Override
	@Transactional
	public void saveCurrency(CurrencyVO currencyVO) {
		Currency currency = CurrencyConverter.convertCurrencyVOToCurrency(currencyVO);
		FinancialYear financialYear = financialYearRepository
				.findById(currencyVO.getFinancialYear().getFinancialYearId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "FinancialYear"));
		currency.setFinancialYear(financialYear);
		currencyRepository.save(currency);
	}

	@Override
	@Transactional
	public void deleteCurrencyById(Long currencyId) {
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByCurrencyId(currencyId);
		if (!revenueEntryList.isEmpty()) {
			throw new RecordNotFoundException("Currency is already linked to RevenueEntry");
		}
		currencyRepository.findById(currencyId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyId));
		currencyRepository.deleteById(currencyId);
	}

	@Override
	public CurrencyVO getCurrencyById(Long currencyId) {
		Currency currency = currencyRepository.findById(currencyId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyId));
		return CurrencyConverter.convertCurrencyToCurrencyVO(currency);
	}

	@Override
	@Transactional
	public void updateCurrency(Long currencyId, CurrencyVO currencyVO) {
		Currency currency = currencyRepository.findById(currencyId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyId));
		currency.setCurrency(currencyVO.getCurrency());
		currency.setCurrencyName(currencyVO.getCurrencyName());
		currency.setSymbol(currencyVO.getSymbol());
		currency.setConversionRate(currencyVO.getConversionRate());
		FinancialYear financialYear = financialYearRepository
				.findById(currencyVO.getFinancialYear().getFinancialYearId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_DOES_NOT_EXIST + "FinancialYear"));
		currency.setFinancialYear(financialYear);
		currency.setBaseCurrency(currencyVO.isBaseCurrency());
		currencyRepository.save(currency);
	}

	@Override
	public List<CurrencyVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<CurrencyVO> currencyVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<Currency> pageResult = currencyRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				currencyVOs.add(CurrencyConverter.convertCurrencyToCurrencyVO(e));
			});
			return currencyVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long currencyId) {
		Currency currency = currencyRepository.findById(currencyId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + currencyId));
		Optional<FinancialYear> optional = financialYearRepository
				.findById(currency.getFinancialYear().getFinancialYearId());
		if (optional.isPresent()) {
			FinancialYear financialYear = optional.get();
			if (!financialYear.isActive() && !currency.isActive()) {
				throw new RecordNotFoundException("FinancialYear is not active and its already linked to Currency");
			}
		}
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByCurrencyId(currencyId);
		for (RevenueEntry revenueEntry : revenueEntryList) {
			if (currency.isActive() && revenueEntry.isActive()) {
				throw new RecordNotFoundException("Currency is already linked to RevenueEntry");
			}
		}
		currency.setActive(!currency.isActive());
		currencyRepository.save(currency);
	}

	@Override
	public List<CurrencyVO> getCurrencyByFinancialYear(String financialYear) {
		List<CurrencyVO> currencyVOs = new ArrayList<>();
		Optional<FinancialYear> findByFinancialYearName = financialYearRepository
				.findByFinancialYearName(financialYear);
		if (findByFinancialYearName.isPresent()) {
			findByFinancialYearName.get().getCurrencies().stream()
					.forEach(currency -> currencyVOs.add(CurrencyConverter.convertCurrencyToCurrencyVO(currency)));
		}
		return currencyVOs;
	}

	@Override
	@Transactional
	public void saveListOfCurrency(List<CurrencyVO> currencyVOs) {
		List<Currency> currencies = new ArrayList<>();
		currencyVOs.stream()
				.forEach(currencyVO -> currencies.add(CurrencyConverter.convertCurrencyVOToCurrency(currencyVO)));
		currencyRepository.saveAll(currencies);

	}
}
