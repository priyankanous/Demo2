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
import com.nous.rollingrevenue.convertor.BusinessUnitConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.model.StrategicBusinessUnit;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.BusinessDevelopmentManagerRepository;
import com.nous.rollingrevenue.repository.BusinessUnitRepository;
import com.nous.rollingrevenue.repository.CocPracticeRepository;
import com.nous.rollingrevenue.repository.RevenueResourceEntryRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitRepository;
import com.nous.rollingrevenue.service.BusinessUnitService;
import com.nous.rollingrevenue.vo.BusinessUnitVO;

@Service
public class BusinessUnitServiceImpl implements BusinessUnitService {

	@Autowired
	BusinessUnitRepository businessUnitRepository;

	@Autowired
	private StrategicBusinessUnitRepository sbuRepository;

	@Autowired
	private BusinessDevelopmentManagerRepository bdmRepository;

	@Autowired
	private CocPracticeRepository cocRepository;

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Autowired
	private RevenueResourceEntryRepository revenueResourceEntryRepository;

	@Override
	@Transactional
	public void addBusinessUnit(BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(businessUnitVO);
		businessUnitRepository.save(businessUnit);
	}

	@Override
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
	public void deleteBusinessUnit(Long id) {
		Optional<BusinessUnit> businessUnitOptional = businessUnitRepository.findById(id);
		List<StrategicBusinessUnit> sbuList = sbuRepository.findByBusinessUnitId(id);
		if (!sbuList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
		}
		List<BusinessDevelopmentManager> bdmList = bdmRepository.findByBusinessUnitId(id);
		if (!bdmList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
		}
		List<CocPractice> cocList = cocRepository.findByBusinessUnitId(id);
		if (!cocList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByBusinessUnitId(id);
		if (!annualTargetEntryList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository.findByBusinessUnitId(id);
		if (!revenueResourceList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
		}
		if (businessUnitOptional.isPresent()) {
			businessUnitRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id);
		}
	}

	@Override
	public List<BusinessUnitVO> getBusinessUnits() {
		List<BusinessUnitVO> businessUnitsVOs = new ArrayList<>();
		businessUnitRepository.findAll().stream()
				.forEach(e -> businessUnitsVOs.add(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(e)));
		return businessUnitsVOs;
	}

	@Override
	@Transactional
	public void updateBusinessUnit(Long id, BusinessUnitVO businessUnitVO) {
		BusinessUnit businessUnit = businessUnitRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		businessUnit.setBusinessUnitName(businessUnitVO.getBusinessUnitName());
		businessUnit.setBusinessUnitDisplayName(businessUnitVO.getBusinessUnitDisplayName());
		businessUnitRepository.save(businessUnit);
	}

	@Override
	public List<BusinessUnitVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<BusinessUnitVO> businessUnitsVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<BusinessUnit> pageResult = businessUnitRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream()
					.forEach(e -> businessUnitsVOs.add(BusinessUnitConverter.convertBusinessUnitToBusinessUnitVO(e)));
			return businessUnitsVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long id) {
		BusinessUnit businessUnit = businessUnitRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		List<StrategicBusinessUnit> sbuList = sbuRepository.findByBusinessUnitId(id);
		for (StrategicBusinessUnit sbu : sbuList) {
			if (businessUnit.isActive() && sbu.isActive()) {
				throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
			}
		}
		List<BusinessDevelopmentManager> bdmList = bdmRepository.findByBusinessUnitId(id);
		for (BusinessDevelopmentManager bdm : bdmList) {
			if (businessUnit.isActive() && bdm.isActive()) {
				throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
			}
		}
		isActiveValidationForBusinessUnit(businessUnit, id);
		businessUnit.setActive(!businessUnit.isActive());
		businessUnitRepository.save(businessUnit);
	}

	private void isActiveValidationForBusinessUnit(BusinessUnit businessUnit, Long id) {
		List<CocPractice> cocList = cocRepository.findByBusinessUnitId(id);
		for (CocPractice coc : cocList) {
			if (businessUnit.isActive() && coc.isActive()) {
				throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
			}
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByBusinessUnitId(id);
		for (AnnualTargetEntry targetEntry : annualTargetEntryList) {
			if (businessUnit.isActive() && targetEntry.isActive()) {
				throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
			}
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository.findByBusinessUnitId(id);
		for (RevenueResourceEntry revenueResource : revenueResourceList) {
			if (businessUnit.isActive() && revenueResource.isActive()) {
				throw new RecordNotFoundException(Constants.BU_IS_ALREADY_LINKED);
			}
		}
	}

}
