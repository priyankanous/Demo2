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
import com.nous.rollingrevenue.convertor.BusinessUnitConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.repository.BusinessUnitRepository;
import com.nous.rollingrevenue.service.BusinessUnitService;
import com.nous.rollingrevenue.vo.BusinessUnitVO;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

	@Autowired
	BusinessUnitRepository businessUnitRepository;

	@Override
	@Transactional
	public BusinessUnitVO addBusinessUnit(BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(businessUnitVO);
		return BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(businessUnitRepository.save(businessUnit));
	}

	@Override
	@Cacheable(value = "businessUnitCache", key = "#id")
	public BusinessUnitVO getBusinessUnitById(Long id) {
		Optional<BusinessUnit> businessUnitOptional = businessUnitRepository.findById(id);
		if (businessUnitOptional.isPresent()) {
			return BusinessUnitConverter
					.convertBusinessUnitToBusinessUnitVO(businessUnitRepository.save(businessUnitOptional.get()));
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "businessUnitCache", key = "#id") // remove cache entry
	public void deleteBusinessUnit(Long id) {
		Optional<BusinessUnit> businessUnitOptional = businessUnitRepository.findById(id);

		if (businessUnitOptional.isPresent()) {
			businessUnitRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
		}
	}

	@Override
	public List<BusinessUnitVO> getBusinessUnits() {
		List<BusinessUnitVO> businessUnitsVOs = new ArrayList<>();
		businessUnitRepository.findAll().stream().forEach(e -> {
			businessUnitsVOs.add(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(e));
		});
		return businessUnitsVOs;
	}

	@Override
	@Transactional
	@CachePut(value = "businessUnitCache", key = "#id")
	public BusinessUnitVO updateBusinessUnit(Long id, BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = businessUnitRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		businessUnit.setBusinessUnitName(businessUnitVO.getBusinessUnitName());
		businessUnit.setBusinessUnitDisplayName(businessUnitVO.getBusinessUnitDisplayName());
		businessUnit.setChildOfOrg(businessUnitVO.getChildOfOrg());
		return BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(businessUnitRepository.save(businessUnit));
	}

	@Override
	public List<BusinessUnitVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<BusinessUnitVO> businessUnitsVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(sortBy));
		Page<BusinessUnit> pageResult = businessUnitRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				businessUnitsVOs.add(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(e));
			});
			return businessUnitsVOs;
		}
		return Collections.emptyList();
	}

}
