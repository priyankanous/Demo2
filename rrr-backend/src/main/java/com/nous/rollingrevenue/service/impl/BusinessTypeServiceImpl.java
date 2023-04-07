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
import com.nous.rollingrevenue.convertor.BusinessTypeConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BusinessType;
import com.nous.rollingrevenue.repository.BusinessTypeRepository;
import com.nous.rollingrevenue.service.BusinessTypeService;
import com.nous.rollingrevenue.vo.BusinessTypeVO;

@Service
@Transactional(readOnly = true)
public class BusinessTypeServiceImpl implements BusinessTypeService {

	@Autowired
	private BusinessTypeRepository businessTypeRepository;

	@Override
	public List<BusinessTypeVO> getAllBusinessType() {
		List<BusinessTypeVO> businessTypeVO = new ArrayList<>();
		businessTypeRepository.findAll().stream().forEach(businessType -> businessTypeVO
				.add(BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(businessType)));
		return businessTypeVO;
	}

	@Override
	@Transactional
	public BusinessTypeVO saveBusinessType(BusinessTypeVO businessTypeVO) {
		BusinessType businessType = businessTypeRepository
				.save(BusinessTypeConverter.convertBusinessTypeVOToBusinessType(businessTypeVO));
		return BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(businessType);
	}

	@Override
	@Transactional
	@CacheEvict(value = "businesstype", key= "#businessTypeId")
	public void deleteBusinessTypeById(Long businessTypeId) {
		businessTypeRepository.findById(businessTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId));
		businessTypeRepository.deleteById(businessTypeId);
	}

	@Override
	@Cacheable(value = "businesstype", key = "#businessTypeId")
	public BusinessTypeVO getBusinessTypeById(Long businessTypeId) {
		BusinessType businessType = businessTypeRepository.findById(businessTypeId)
                .orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId));
		return BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(businessType);
	}

	@Override
	@Transactional
	@CachePut(value = "businesstype", key = "#businessTypeId")
	public BusinessTypeVO updateBusinessType(Long businessTypeId, BusinessTypeVO businessTypeVO) {
		BusinessType businessType = businessTypeRepository.findById(businessTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId));
		businessType.setBusinessTypeName(businessTypeVO.getBusinessTypeName());
		businessType.setBusinessTypeDisplayName(businessTypeVO.getBusinessTypeDisplayName());
		return BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(businessTypeRepository.save(businessType));
	}

	@Override
	public List<BusinessTypeVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<BusinessTypeVO> businessTypeVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<BusinessType> pageResult = businessTypeRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				businessTypeVOs.add(BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(e));
			});
			return businessTypeVOs;
		}
		return Collections.emptyList();
	}
	
	@Override
	@Transactional
	@CachePut(value = "businesstype", key = "#businessTypeId")
	public BusinessTypeVO activateOrDeactivateById(Long businessTypeId) {
		BusinessType businessType = businessTypeRepository.findById(businessTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId));
		businessType.setActive(!businessType.isActive());
		return BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(businessTypeRepository.save(businessType));
	}

}
