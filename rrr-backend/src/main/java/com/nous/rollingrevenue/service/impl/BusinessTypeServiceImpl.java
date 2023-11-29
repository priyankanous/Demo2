package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.Constants;
import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.BusinessTypeConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.BusinessType;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.BusinessTypeRepository;
import com.nous.rollingrevenue.repository.RevenueResourceEntryRepository;
import com.nous.rollingrevenue.service.BusinessTypeService;
import com.nous.rollingrevenue.vo.BusinessTypeVO;

@Service
@Transactional(readOnly = true)
public class BusinessTypeServiceImpl implements BusinessTypeService {

	@Autowired
	private BusinessTypeRepository businessTypeRepository;

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Autowired
	private RevenueResourceEntryRepository revenueResourceEntryRepository;

	@Override
	public List<BusinessTypeVO> getAllBusinessType() {
		List<BusinessTypeVO> businessTypeVO = new ArrayList<>();
		businessTypeRepository.findAll().stream().forEach(businessType -> businessTypeVO
				.add(BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(businessType)));
		return businessTypeVO;
	}

	@Override
	@Transactional
	public void saveBusinessType(BusinessTypeVO businessTypeVO) {
		businessTypeRepository.save(BusinessTypeConverter.convertBusinessTypeVOToBusinessType(businessTypeVO));
	}

	@Override
	@Transactional
	public void deleteBusinessTypeById(Long businessTypeId) {
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository
				.findByBusinessTypeId(businessTypeId);
		if (!annualTargetEntryList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BUSINESSTYPE_IS_ALREADY_LINKED);
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository
				.findByBusinessTypeId(businessTypeId);
		if (!revenueResourceList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BUSINESSTYPE_IS_ALREADY_LINKED);
		}
		Optional<BusinessType> findById = businessTypeRepository.findById(businessTypeId);
		if (findById.isEmpty()) {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId);
		} else {
			businessTypeRepository.deleteById(businessTypeId);
		}
	}

	@Override
	public BusinessTypeVO getBusinessTypeById(Long businessTypeId) {
		BusinessType businessType = businessTypeRepository.findById(businessTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId));
		return BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(businessType);
	}

	@Override
	@Transactional
	public void updateBusinessType(Long businessTypeId, BusinessTypeVO businessTypeVO) {
		BusinessType businessType = businessTypeRepository.findById(businessTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId));
		businessType.setBusinessTypeName(businessTypeVO.getBusinessTypeName());
		businessType.setBusinessTypeDisplayName(businessTypeVO.getBusinessTypeDisplayName());
		businessTypeRepository.save(businessType);
	}

	@Override
	public List<BusinessTypeVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<BusinessTypeVO> businessTypeVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<BusinessType> pageResult = businessTypeRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream()
					.forEach(e -> businessTypeVOs.add(BusinessTypeConverter.convertBusinessTypeToBusinessTypeVO(e)));
			return businessTypeVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long businessTypeId) {
		BusinessType businessType = businessTypeRepository.findById(businessTypeId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + businessTypeId));
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository
				.findByBusinessTypeId(businessTypeId);
		for (AnnualTargetEntry targetEntry : annualTargetEntryList) {
			if (businessType.isActive() && targetEntry.isActive()) {
				throw new RecordNotFoundException(Constants.BUSINESSTYPE_IS_ALREADY_LINKED);
			}
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository
				.findByBusinessTypeId(businessTypeId);
		for (RevenueResourceEntry revenueResource : revenueResourceList) {
			if (businessType.isActive() && revenueResource.isActive()) {
				throw new RecordNotFoundException(Constants.BUSINESSTYPE_IS_ALREADY_LINKED);
			}
		}
		businessType.setActive(!businessType.isActive());
		businessTypeRepository.save(businessType);
	}

}
