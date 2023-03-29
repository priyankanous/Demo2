package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
	public PricingTypeVO savePricingType(PricingTypeVO pricingTypeVO) {
		PricingType pricingType = pricingTypeRepository
				.save(PricingTypeConverter.convertPricingTypeVOToPricingType(pricingTypeVO));
		return PricingTypeConverter.convertPricingTypeToPricingTypeVO(pricingType);
	}

	@Override
	@Transactional
	@CacheEvict(value = "pricingtype", key = "#pricingTypeId")
	public void deletePricingTypeById(Long pricingTypeId) {
		pricingTypeRepository.findById(pricingTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + pricingTypeId));
		pricingTypeRepository.deleteById(pricingTypeId);

	}

	@Override
	@Cacheable(value = "pricingtype", key = "#pricingTypeId")
	public PricingTypeVO getPricingTypeById(Long pricingTypeId) {
		PricingType pricingType = pricingTypeRepository.findById(pricingTypeId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + pricingTypeId));
		return PricingTypeConverter.convertPricingTypeToPricingTypeVO(pricingType);
	}

	@Override
	@Transactional
	@CachePut(value = "pricingtype", key = "#pricingTypeId")
	public PricingTypeVO updatePricingType(Long pricingTypeId, PricingTypeVO pricingTypeVO) {
		PricingType pricingType = pricingTypeRepository.findById(pricingTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + pricingTypeId));
		pricingType.setPricingTypeName(pricingTypeVO.getPricingTypeName());
		pricingType.setPricingTypeDisplayName(pricingTypeVO.getPricingTypeDisplayName());
		return PricingTypeConverter.convertPricingTypeToPricingTypeVO(pricingTypeRepository.save(pricingType));
	}

}
