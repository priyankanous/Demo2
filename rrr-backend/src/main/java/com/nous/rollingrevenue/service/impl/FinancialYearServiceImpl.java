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
import com.nous.rollingrevenue.convertor.FinancialYearConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.service.FinancialYearService;
import com.nous.rollingrevenue.service.FortnightlyMeetingService;
import com.nous.rollingrevenue.vo.FinancialYearVO;

@Service
@Transactional(readOnly = true)
public class FinancialYearServiceImpl implements FinancialYearService {

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private FortnightlyMeetingService fortnightlyMeetingService;

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
		// Generate Fortnightly Meetings for the Respective FinancialYear.
		fortnightlyMeetingService.generateFortnightlyMeetingsOfFinancialYear(savedFinancialYearVO);
		return savedFinancialYearVO;
	}

	@Override
	@Transactional
	@CacheEvict(value = "financialyear", key = "#financialYearId")
	public void deleteFinancialYearById(Long financialYearId) {
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		fortnightlyMeetingService.deleteFortnightlyMeetingByFinancialYear(financialYear.getFinancialYearName());
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
		FinancialYearVO tempFinancialYearVO = null;
		FinancialYear financialYear = financialYearRepository.findById(financialYearId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + financialYearId));
		tempFinancialYearVO = FinancialYearConverter.convertFinancialYearToFinancialYearVO(financialYear);
		financialYear.setFinancialYearName(financialYearVO.getFinancialYearName());
		financialYear.setFinancialYearCustomName(financialYearVO.getFinancialYearCustomName());
		financialYear.setStartingFrom(financialYearVO.getStartingFrom());
		financialYear.setEndingOn(financialYearVO.getEndingOn());
		FinancialYearVO savedFinancialYearVO = FinancialYearConverter
				.convertFinancialYearToFinancialYearVO(financialYearRepository.save(financialYear));
		// Delete Existing and Generate Fortnightly Meetings for the Updated FinancialYear.
		if (!financialYearVO.getStartingFrom().equals(tempFinancialYearVO.getStartingFrom())
				| !financialYearVO.getEndingOn().equals(tempFinancialYearVO.getEndingOn())
				| !financialYearVO.getFinancialYearName().equals(tempFinancialYearVO.getFinancialYearName())) {
			fortnightlyMeetingService
					.deleteFortnightlyMeetingByFinancialYear(tempFinancialYearVO.getFinancialYearName());
			fortnightlyMeetingService.generateFortnightlyMeetingsOfFinancialYear(savedFinancialYearVO);
		}
		return savedFinancialYearVO;
	}
	
	@Override
	public List<FinancialYearVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<FinancialYearVO> financialYearVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(sortBy));
		Page<FinancialYear> pageResult = financialYearRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				financialYearVOs.add(FinancialYearConverter.convertFinancialYearToFinancialYearVO(e));
			});
			return financialYearVOs;
		}
		return Collections.emptyList();
	}

}
