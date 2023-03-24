package com.nous.rollingrevenue.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public BusinessUnit addBusinessUnit(BusinessUnit businessUnit) {
		return businessUnitRepository.save(businessUnit);
	}

	@Override
	@Cacheable(value = "businessUnitCache", key = "#id")
	public BusinessUnit getBusinessUnitById(Long id) {
		Optional<BusinessUnit> businessUnitOptional = businessUnitRepository.findById(id);

		if (businessUnitOptional.isPresent()) {
			return businessUnitOptional.get();
		}
		throw new RecordNotFoundException("Business Unit not found for id:" + id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "businessUnitCache", key = "#id") // remove cache entry
	public void deleteBusinessUnit(Long id) {
		Optional<BusinessUnit> businessUnitOptional = businessUnitRepository.findById(id);

		if (businessUnitOptional.isPresent()) {
			businessUnitRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("Business Unit not found for id:" + id);
		}
	}

	@Override
	public List<BusinessUnit> getBusinessUnits() {
		return businessUnitRepository.findAll();
	}

	@Override
	@Transactional
	@CachePut(value = "businessUnitCache", key = "#id")
	public BusinessUnit updateBusinessUnit(Long id, BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = businessUnitRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Business Unit not found for id:" + id));
		businessUnit.setBusinessUnitName(businessUnitVO.getBusinessUnitName());
		businessUnit.setBusinessUnitDisplayName(businessUnitVO.getBusinessUnitDisplayName());
		businessUnit.setChildOfOrg(businessUnitVO.getChildOfOrg());
		return businessUnitRepository.save(businessUnit);
	}

}
