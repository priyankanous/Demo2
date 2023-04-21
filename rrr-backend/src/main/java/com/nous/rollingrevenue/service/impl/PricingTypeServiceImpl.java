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
import com.nous.rollingrevenue.convertor.PricingTypeConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.PricingType;
import com.nous.rollingrevenue.repository.PricingTypeRepository;
import com.nous.rollingrevenue.service.PricingTypeService;
import com.nous.rollingrevenue.vo.PricingTypeVO;

@Service
@Transactional(readOnly = true)
public class PricingTypeServiceImpl implements PricingTypeService {

	@Autowired
	private PricingTypeRepository pricingTypeRepository;

	@Override
	public List<PricingTypeVO> getAllPricingType() {
		List<PricingTypeVO> pricingTypeVO = new ArrayList<>();
		pricingTypeRepository.findAll().stream().forEach(
				pricingType -> pricingTypeVO.add(PricingTypeConverter.convertPricingTypeToPricingTypeVO(pricingType)));
		return pricingTypeVO;
	}

	@Override
	@Transactional
	public void savePricingType(PricingTypeVO pricingTypeVO) {
		pricingTypeRepository.save(PricingTypeConverter.convertPricingTypeVOToPricingType(pricingTypeVO));
	}

	@Override
	@Transactional
	public void deletePricingTypeById(Long pricingTypeId) {
		pricingTypeRepository.findById(pricingTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + pricingTypeId));
		pricingTypeRepository.deleteById(pricingTypeId);

	}

	@Override
	public PricingTypeVO getPricingTypeById(Long pricingTypeId) {
		PricingType pricingType = pricingTypeRepository.findById(pricingTypeId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + pricingTypeId));
		return PricingTypeConverter.convertPricingTypeToPricingTypeVO(pricingType);
	}

	@Override
	@Transactional
	public void updatePricingType(Long pricingTypeId, PricingTypeVO pricingTypeVO) {
		PricingType pricingType = pricingTypeRepository.findById(pricingTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + pricingTypeId));
		pricingType.setPricingTypeName(pricingTypeVO.getPricingTypeName());
		pricingType.setPricingTypeDisplayName(pricingTypeVO.getPricingTypeDisplayName());
		pricingTypeRepository.save(pricingType);
	}
	
	@Override
	public List<PricingTypeVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<PricingTypeVO> pricingTypeVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<PricingType> pageResult = pricingTypeRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				pricingTypeVOs.add(PricingTypeConverter.convertPricingTypeToPricingTypeVO(e));
			});
			return pricingTypeVOs;
		}
		return Collections.emptyList();
	}
	
	@Override
	@Transactional
	public void activateOrDeactivateById(Long pricingTypeId) {
		PricingType pricingType = pricingTypeRepository.findById(pricingTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + pricingTypeId));
		pricingType.setActive(!pricingType.isActive());
		pricingTypeRepository.save(pricingType);
	}

}
