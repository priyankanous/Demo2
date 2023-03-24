package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.convertor.FinancialYearConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.service.FinancialYearService;
import com.nous.rollingrevenue.vo.FinancialYearVO;

@Service
@Transactional(readOnly = true)
public class FinancialYearServiceImpl implements FinancialYearService {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Override
	public List<FinancialYearVO> getAllFinancialYear() {
		List<FinancialYearVO> financialYearVO = new ArrayList<>();
		financialYearRepository.findAll().stream().forEach(financialYear -> financialYearVO
				.add(FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYear)));
		return financialYearVO;
	}

	@Override
	@Transactional
	public FinancialYearVO saveFinancialYear(FinancialYearVO financialYearVO) {
		FinancialYear financialYear = financialYearRepository
				.save(FinancialYearConverter.convertFinancialYearVOToFinancialYear(financialYearVO));
		return FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYear);
	}

	@Override
	@Transactional
	@CacheEvict(value = "financialyear", key = "#financialYearId")
	public void deleteFinancialYearById(Long financialYearId) {
		financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException("FinancialYear not exist with id:" + financialYearId));
		financialYearRepository.deleteById(financialYearId);
	}

	@Override
	@Cacheable(value = "financialyear", key = "#financialYearId")
	public FinancialYearVO getFinancialYearById(Long financialYearId) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
                .orElseThrow(() -> new RecordNotFoundException("FinancialYear not exist with id:" + financialYearId));
		return FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYear);
	}

	@Override
	@Transactional
	@CachePut(value = "financialyear", key = "#financialYearId")
	public FinancialYearVO updateFinancialYear(Long financialYearId, FinancialYearVO financialYearVO) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException("FinancialYear not exist with id:" + financialYearId));
		financialYear.setFinancialYearName(financialYearVO.getFinancialYearName());
		financialYear.setFinancialYearCustomName(financialYearVO.getFinancialYearCustomName());
		financialYear.setStartingFrom(financialYearVO.getStartingFrom());
		financialYear.setEndingOn(financialYearVO.getEndingOn());
		return FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYearRepository.save(financialYear));
	}

}
