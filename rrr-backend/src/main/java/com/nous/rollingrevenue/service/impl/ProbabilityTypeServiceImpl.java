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
import com.nous.rollingrevenue.convertor.ProbabilityTypeConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.ProbabilityType;
import com.nous.rollingrevenue.repository.ProbabilityTypeRepository;
import com.nous.rollingrevenue.service.ProbabilityTypeService;
import com.nous.rollingrevenue.vo.ProbabilityTypeVO;

@Service
@Transactional(readOnly = true)
public class ProbabilityTypeServiceImpl implements ProbabilityTypeService {

	@Autowired
	private ProbabilityTypeRepository probabilityTypeRepository;

	@Override
	public List<ProbabilityTypeVO> getAllProbabilityType() {
		List<ProbabilityTypeVO> probabilityTypeVO = new ArrayList<>();
		probabilityTypeRepository.findAll().stream().forEach(probabilityType -> probabilityTypeVO
				.add(ProbabilityTypeConverter.convertProbabilityTypeToProbabilityTypeVO(probabilityType)));
		return probabilityTypeVO;
	}

	@Override
	@Transactional
	public ProbabilityTypeVO saveProbabilityType(ProbabilityTypeVO probabilityTypeVO) {
		ProbabilityType probabilityType = probabilityTypeRepository
				.save(ProbabilityTypeConverter.convertProbabilityTypeVOToProbabilityType(probabilityTypeVO));
		return ProbabilityTypeConverter.convertProbabilityTypeToProbabilityTypeVO(probabilityType);
	}

	@Override
	@Transactional
	@CacheEvict(value = "probabilitytype", key = "#probabilityTypeId")
	public void deleteProbabilityTypeById(Long probabilityTypeId) {
		probabilityTypeRepository.findById(probabilityTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + probabilityTypeId));
		probabilityTypeRepository.deleteById(probabilityTypeId);
	}

	@Override
	@Cacheable(value = "probabilitytype", key = "#probabilityTypeId")
	public ProbabilityTypeVO getProbabilityTypeById(Long probabilityTypeId) {
		ProbabilityType probabilityType = probabilityTypeRepository.findById(probabilityTypeId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + probabilityTypeId));
		return ProbabilityTypeConverter.convertProbabilityTypeToProbabilityTypeVO(probabilityType);
	}

	@Override
	@Transactional
	@CachePut(value = "probabilitytype", key = "#probabilityTypeId")
	public ProbabilityTypeVO updateProbabilityType(Long probabilityTypeId, ProbabilityTypeVO probabilityTypeVO) {
		ProbabilityType probabilityType = probabilityTypeRepository.findById(probabilityTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + probabilityTypeId));
		probabilityType.setProbabilityTypeName(probabilityTypeVO.getProbabilityTypeName());
		probabilityType.setPercentage(probabilityTypeVO.getPercentage());
		return ProbabilityTypeConverter.convertProbabilityTypeToProbabilityTypeVO(probabilityTypeRepository.save(probabilityType));
	}
	
	@Override
	public List<ProbabilityTypeVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<ProbabilityTypeVO> probabilityTypeVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<ProbabilityType> pageResult = probabilityTypeRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				probabilityTypeVOs.add(ProbabilityTypeConverter.convertProbabilityTypeToProbabilityTypeVO(e));
			});
			return probabilityTypeVOs;
		}
		return Collections.emptyList();
	}


}
