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
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
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
		FinancialYearVO savedFinancialYearVO = FinancialYearConverter
				.convertFinancialYearToFinancialYearVO(financialYear);
		return savedFinancialYearVO;
	}

	@Override
	@Transactional
	@CacheEvict(value = "financialyear", key = "#financialYearId")
	public void deleteFinancialYearById(Long financialYearId) {
		financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		financialYearRepository.deleteById(financialYearId);
	}

	@Override
	@Cacheable(value = "financialyear", key = "#financialYearId")
	public FinancialYearVO getFinancialYearById(Long financialYearId) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		return FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYear);
	}

	@Override
	@Transactional
	@CachePut(value = "financialyear", key = "#financialYearId")
	public FinancialYearVO updateFinancialYear(Long financialYearId, FinancialYearVO financialYearVO) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		financialYear.setFinancialYearName(financialYearVO.getFinancialYearName());
		financialYear.setFinancialYearCustomName(financialYearVO.getFinancialYearCustomName());
		financialYear.setStartingFrom(financialYearVO.getStartingFrom());
		financialYear.setEndingOn(financialYearVO.getEndingOn());
		FinancialYearVO savedFinancialYearVO = FinancialYearConverter
				.convertFinancialYearToFinancialYearVO(financialYearRepository.save(financialYear));
		return savedFinancialYearVO;
	}

	@Override
	public List<FinancialYearVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<FinancialYearVO> financialYearVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<FinancialYear> pageResult = financialYearRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				financialYearVOs.add(FinancialYearConverter.convertFinancialYearToFinancialYearVO(e));
			});
			return financialYearVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	@CachePut(value = "financialyear", key = "#financialYearId")
	public FinancialYearVO activateOrDeactivateById(Long financialYearId) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		financialYear.setActive(!financialYear.isActive());
		return FinancialYearConverter
				.convertFinancialYearToFinancialYearVO(financialYearRepository.save(financialYear));
	}

}
