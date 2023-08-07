package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.repository.ProbabilityTypeRepository;
import com.nous.rollingrevenue.repository.RevenueEntryRespository;
import com.nous.rollingrevenue.service.ProbabilityTypeService;
import com.nous.rollingrevenue.vo.ProbabilityTypeVO;

@Service
@Transactional(readOnly = true)
public class ProbabilityTypeServiceImpl implements ProbabilityTypeService {

	@Autowired
	private ProbabilityTypeRepository probabilityTypeRepository;

	@Autowired
	private RevenueEntryRespository revenueEntryRespository;

	@Override
	public List<ProbabilityTypeVO> getAllProbabilityType() {
		List<ProbabilityTypeVO> probabilityTypeVO = new ArrayList<>();
		probabilityTypeRepository.findAll().stream().forEach(probabilityType -> probabilityTypeVO
				.add(ProbabilityTypeConverter.convertProbabilityTypeToProbabilityTypeVO(probabilityType)));
		return probabilityTypeVO;
	}

	@Override
	@Transactional
	public void saveProbabilityType(ProbabilityTypeVO probabilityTypeVO) {
		probabilityTypeRepository
				.save(ProbabilityTypeConverter.convertProbabilityTypeVOToProbabilityType(probabilityTypeVO));
	}

	@Override
	@Transactional
	public void deleteProbabilityTypeById(Long probabilityTypeId) {
		probabilityTypeRepository.findById(probabilityTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + probabilityTypeId));
		probabilityTypeRepository.deleteById(probabilityTypeId);
	}

	@Override
	public ProbabilityTypeVO getProbabilityTypeById(Long probabilityTypeId) {
		ProbabilityType probabilityType = probabilityTypeRepository.findById(probabilityTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + probabilityTypeId));
		return ProbabilityTypeConverter.convertProbabilityTypeToProbabilityTypeVO(probabilityType);
	}

	@Override
	@Transactional
	public void updateProbabilityType(Long probabilityTypeId, ProbabilityTypeVO probabilityTypeVO) {
		ProbabilityType probabilityType = probabilityTypeRepository.findById(probabilityTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + probabilityTypeId));
		probabilityType.setProbabilityTypeName(probabilityTypeVO.getProbabilityTypeName());
		probabilityType.setPercentage(probabilityTypeVO.getPercentage());
		probabilityTypeRepository.save(probabilityType);
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

	@Override
	@Transactional
	public void activateOrDeactivateById(Long probabilityTypeId) {
		ProbabilityType probabilityType = probabilityTypeRepository.findById(probabilityTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + probabilityTypeId));
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByProbabilityTypeId(probabilityTypeId);
		for (RevenueEntry revenueEntry : revenueEntryList) {
			if (probabilityType.isActive() && revenueEntry.isActive()) {
				throw new RecordNotFoundException("ProbabilityType is already linked to RevenueEntry");
			}
		}
		probabilityType.setActive(!probabilityType.isActive());
		probabilityTypeRepository.save(probabilityType);
	}

}
