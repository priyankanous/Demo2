package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.LeaveLossFactorConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.GlobalMonthlyLeaveLossFactor;
import com.nous.rollingrevenue.repository.GlobalMonthlyLeaveLossFactorRepository;
import com.nous.rollingrevenue.service.GlobalMonthlyLeaveLossFactorService;
import com.nous.rollingrevenue.vo.GlobalMonthlyLeaveLossFactorVO;

@Service
public class GlobalMonthlyLeaveLossFactorServiceImpl implements GlobalMonthlyLeaveLossFactorService {

	@Autowired
	private GlobalMonthlyLeaveLossFactorRepository globalMonthlyLeaveLossFactorRepository;

	@Override
	@Transactional
	public GlobalMonthlyLeaveLossFactorVO addLeaveLossFactor(GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = LeaveLossFactorConverter
				.convertLeaveLossFactorVOToLeaveLossFactor(leaveLossFactorVO);
		return LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(
				globalMonthlyLeaveLossFactorRepository.save(leaveLossFactor));
	}

	@Override
	@Transactional
	@CachePut(value = "lossfactor", key = "#id")
	public GlobalMonthlyLeaveLossFactorVO updateLeaveLossFactor(Long id,
			GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = globalMonthlyLeaveLossFactorRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		leaveLossFactor.setMonth(leaveLossFactorVO.getMonth());
		leaveLossFactor.setOffShore(leaveLossFactorVO.getOffShore());
		leaveLossFactor.setOnSite(leaveLossFactorVO.getOnSite());
		leaveLossFactor.setFinancialYear(leaveLossFactorVO.getFinancialYear());
		return LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(
				globalMonthlyLeaveLossFactorRepository.save(leaveLossFactor));
	}

	@Override
	@Cacheable(value = "lossfactor", key = "#id")
	public GlobalMonthlyLeaveLossFactorVO getLeaveLossFactorById(Long id) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = globalMonthlyLeaveLossFactorRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		return LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor);

	}

	@Override
	@Transactional
	@CacheEvict(value = "lossfactor", key = "#id")
	public void deleteLeaveLossFactor(Long id) {
		globalMonthlyLeaveLossFactorRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		globalMonthlyLeaveLossFactorRepository.deleteById(id);
	}

	@Override
	public List<GlobalMonthlyLeaveLossFactorVO> getLeaveLossFactors() {
		List<GlobalMonthlyLeaveLossFactorVO> leaveLossFactorVOs = new ArrayList<>();
		globalMonthlyLeaveLossFactorRepository.findAll().stream().forEach(leaveLossFactor -> {
			leaveLossFactorVOs.add(LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor));
		});
		return leaveLossFactorVOs;
	}

	@Override
	public List<GlobalMonthlyLeaveLossFactorVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<GlobalMonthlyLeaveLossFactorVO> globalMonthlyLeaveLossFactorVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(sortBy));
		Page<GlobalMonthlyLeaveLossFactor> pageResult = globalMonthlyLeaveLossFactorRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				globalMonthlyLeaveLossFactorVOs
						.add(LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(e));
			});
			return globalMonthlyLeaveLossFactorVOs;
		}
		return Collections.emptyList();
	}

	@Override
	public List<GlobalMonthlyLeaveLossFactorVO> getLeaveLossFactorByFinancialYear(String financialYear) {
		List<GlobalMonthlyLeaveLossFactorVO> leaveLossFactorVOs = new ArrayList<>();
		globalMonthlyLeaveLossFactorRepository.findByFinancialYear(financialYear).stream().forEach(leaveLossFactor -> {
			leaveLossFactorVOs.add(LeaveLossFactorConverter.convertLeaveLossFactorToLeaveLossFactorVO(leaveLossFactor));
		});
		return leaveLossFactorVOs;
	}

}
