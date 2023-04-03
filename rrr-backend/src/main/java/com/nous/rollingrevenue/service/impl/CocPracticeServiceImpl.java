package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
import com.nous.rollingrevenue.convertor.CocPracticeConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.repository.CocPracticeRepository;
import com.nous.rollingrevenue.service.CocPracticeService;
import com.nous.rollingrevenue.vo.CocPracticeVO;

@Service
@Transactional(readOnly = true)
public class CocPracticeServiceImpl implements CocPracticeService {

	@Autowired
	CocPracticeRepository cocpracticeRepository;

	@Override
	@Transactional
	public CocPracticeVO addCocPractice(CocPracticeVO cocpracticeVO) {
		CocPractice cocPractice = CocPracticeConverter.convertCocPracticeVOToCocPractice(cocpracticeVO);
		return CocPracticeConverter.convertCocPracticeToCocPracticeVO(cocpracticeRepository.save(cocPractice));
	}

	@Override
	@Cacheable(value = "cocpractice", key = "#id")
	public CocPracticeVO getCocPractice(Long id) {
		Optional<CocPractice> cocOptional = cocpracticeRepository.findById(id);

		if (cocOptional.isPresent()) {
			return CocPracticeConverter.convertCocPracticeToCocPracticeVO(cocOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);

	}

	@Override
	@Transactional
	@CacheEvict(value = "cocpractice", key = "#id")
	public void deleteCocPractice(Long id) {
		Optional<CocPractice> cocOptional = cocpracticeRepository.findById(id);

		if (cocOptional.isPresent()) {
			cocpracticeRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
		}
	}

	@Override
	public List<CocPracticeVO> getAllCocPractice() {
		List<CocPracticeVO> cocPracticeVOs = new ArrayList<>();
		cocpracticeRepository.findAll().stream().forEach(cocPractice -> {
			cocPracticeVOs.add(CocPracticeConverter.convertCocPracticeToCocPracticeVO(cocPractice));
		});
		return cocPracticeVOs;
	}

	@Override
	@Transactional
	@CachePut(value = "cocpractice", key = "#id")
	public CocPracticeVO updateCocPractice(Long id, CocPracticeVO cocpracticeVO) {
		CocPractice cocpractice = cocpracticeRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		cocpractice.setCocPracticeDisplayName(cocpracticeVO.getCocPracticeDisplayName());
		cocpractice.setCocPracticeName(cocpracticeVO.getCocPracticeName());
		cocpractice.setBuDisplayName(cocpracticeVO.getBuDisplayName());
		return CocPracticeConverter.convertCocPracticeToCocPracticeVO(cocpracticeRepository.save(cocpractice));
	}
	
	@Override
	public List<CocPracticeVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<CocPracticeVO> cocPracticeVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(sortBy));
		Page<CocPractice> pageResult = cocpracticeRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				cocPracticeVOs.add(CocPracticeConverter.convertCocPracticeToCocPracticeVO(e));
			});
			return cocPracticeVOs;
		}
		return Collections.emptyList();
	}
}
