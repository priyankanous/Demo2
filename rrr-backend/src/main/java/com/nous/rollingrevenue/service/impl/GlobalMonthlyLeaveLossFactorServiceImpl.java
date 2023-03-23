package com.nous.rollingrevenue.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public GlobalMonthlyLeaveLossFactor addLeaveLossFactor(GlobalMonthlyLeaveLossFactor leaveLossFactor) {
		return globalMonthlyLeaveLossFactorRepository.save(leaveLossFactor);
	}

	@Override
	@Transactional
	@CachePut(value = "lossfactor", key = "#id")
	public GlobalMonthlyLeaveLossFactor updateLeaveLossFactor(Long id,
			GlobalMonthlyLeaveLossFactorVO leaveLossFactorVO) {
		GlobalMonthlyLeaveLossFactor leaveLossFactor = globalMonthlyLeaveLossFactorRepository.findById(id).orElseThrow(
				() -> new RecordNotFoundException("Global Monthly Leave Loss Factor not found for id:" + id));
		leaveLossFactor.setMonth(leaveLossFactorVO.getMonth());
		leaveLossFactor.setOffShore(leaveLossFactorVO.getOffShore());
		leaveLossFactor.setOnSite(leaveLossFactorVO.getOnSite());
		leaveLossFactor.setFinancialYear(leaveLossFactorVO.getFinancialYear());
		return globalMonthlyLeaveLossFactorRepository.save(leaveLossFactor);
	}

	@Override
	@Cacheable(value = "lossfactor", key = "#id")
	public GlobalMonthlyLeaveLossFactor getLeaveLossFactorById(Long id) {
		return globalMonthlyLeaveLossFactorRepository.findById(id).orElseThrow(
				() -> new RecordNotFoundException("Global Monthly Leave Loss Factor not found for id:" + id));
	}

	@Override
	@Transactional
	@CacheEvict(value = "lossfactor", key = "#id")
	public void deleteLeaveLossFactor(Long id) {
		globalMonthlyLeaveLossFactorRepository.findById(id).orElseThrow(
				() -> new RecordNotFoundException("Global Monthly Leave Loss Factor not found for id:" + id));
		globalMonthlyLeaveLossFactorRepository.deleteById(id);
	}

	@Override
	public List<GlobalMonthlyLeaveLossFactor> getLeaveLossFactors() {
		return globalMonthlyLeaveLossFactorRepository.findAll();
	}

}
