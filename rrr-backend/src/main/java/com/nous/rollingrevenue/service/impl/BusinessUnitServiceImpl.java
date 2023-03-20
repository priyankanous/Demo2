package com.nous.rollingrevenue.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.repository.BusinessUnitRepository;
import com.nous.rollingrevenue.service.BusinessUnitService;
import com.nous.rollingrevenue.vo.BusinessUnitVO;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

//	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessUnitServiceImpl.class);

	@Autowired
	BusinessUnitRepository businessUnitRepository;

	@Override
	public BusinessUnit addBusinessUnit(BusinessUnit businessUnit) {
		return businessUnitRepository.save(businessUnit);
	}

	@Override
//	@Cacheable(value = "businessUnitCache", key = "#id")
	public BusinessUnit getBusinessUnit(Long id) {
		Optional<BusinessUnit> businessUnitOptional = businessUnitRepository.findById(id);

		if (businessUnitOptional.isPresent()) {
			return businessUnitOptional.get();
		}
		throw new RecordNotFoundException("Business Unit not found for id:" + id);
	}

	@Override
	@Transactional
//	@CacheEvict(value = "businessUnitCache", key = "#id") // remove cache entry
	public void deleteBusinessUnit(Long id) {
		Optional<BusinessUnit> businessUnitOptional = businessUnitRepository.findById(id);

		if (businessUnitOptional.isPresent()) {
			businessUnitRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("Business Unit not found for id:" + id);
		}
	}

	@Override
	@Transactional
	public List<BusinessUnit> getBusinessUnits() {
		return businessUnitRepository.findAll();
	}

	@Override
	public BusinessUnit updateBusinessUnit(Long id, BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = businessUnitRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("Business Unit not found for id:" + id));
		businessUnit.setBusinessUnitName(businessUnitVO.getBusinessUnitName());
		businessUnit.setBusinessUnitDisplayName(businessUnitVO.getBusinessUnitDisplayName());
		businessUnit.setChildOfOrg(businessUnitVO.getChildOfOrg());
		return businessUnitRepository.save(businessUnit);
	}

}
