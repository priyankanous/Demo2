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
import com.nous.rollingrevenue.convertor.StrategicBusinessUnitConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.model.RevenueResourceEntry;
import com.nous.rollingrevenue.model.StrategicBusinessUnit;
import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.BusinessUnitRepository;
import com.nous.rollingrevenue.repository.RevenueResourceEntryRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitHeadRepository;
import com.nous.rollingrevenue.repository.StrategicBusinessUnitRepository;
import com.nous.rollingrevenue.service.StrategicBusinessUnitService;
import com.nous.rollingrevenue.vo.StrategicBusinessUnitVO;

@Service
@Transactional(readOnly = true)
public class StrategicBusinessUnitServiceImpl implements StrategicBusinessUnitService {

	@Autowired
	private StrategicBusinessUnitRepository sbuRepository;

	@Autowired
	private BusinessUnitRepository businessUnitRepository;

	@Autowired
	private StrategicBusinessUnitHeadRepository sbuHeadRepository;

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Autowired
	private RevenueResourceEntryRepository revenueResourceEntryRepository;

	@Override
	public List<StrategicBusinessUnitVO> getAllSBU() {
		List<StrategicBusinessUnitVO> sbuVO = new ArrayList<>();
		sbuRepository.findAll().stream()
				.forEach(sbu -> sbuVO.add(StrategicBusinessUnitConverter.convertSBUToSBUVO(sbu)));
		return sbuVO;
	}

	@Override
	@Transactional
	public void saveSBU(StrategicBusinessUnitVO sbuVO) {
		StrategicBusinessUnit sbu = StrategicBusinessUnitConverter.convertSBUVOToSBU(sbuVO);
		BusinessUnit businessUnit = businessUnitRepository.findById(sbuVO.getBusinessUnit().getBusinessUnitId())
				.orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "BusinessUnit not exist"));
		sbu.setBusinessUnit(businessUnit);
		sbuRepository.save(sbu);
	}

	@Override
	@Transactional
	public void deleteSBUById(Long sbuId) {
		List<StrategicBusinessUnitHead> sbuHeadList = sbuHeadRepository.findBySBUId(sbuId);
		if (!sbuHeadList.isEmpty()) {
			throw new RecordNotFoundException(Constants.SBU_IS_ALREADY_LINKED);
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findBySBUId(sbuId);
		if (!annualTargetEntryList.isEmpty()) {
			throw new RecordNotFoundException(Constants.SBU_IS_ALREADY_LINKED);
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository.findBySBUId(sbuId);
		if (!revenueResourceList.isEmpty()) {
			throw new RecordNotFoundException(Constants.SBU_IS_ALREADY_LINKED);
		}
		Optional<StrategicBusinessUnit> findById = sbuRepository.findById(sbuId);
		if (findById.isEmpty()) {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuId);
		} else {
			sbuRepository.deleteById(sbuId);
		}
	}

	@Override
	public StrategicBusinessUnitVO getSBUById(Long sbuId) {
		StrategicBusinessUnit sbu = sbuRepository.findById(sbuId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuId));
		return StrategicBusinessUnitConverter.convertSBUToSBUVO(sbu);
	}

	@Override
	@Transactional
	public void updateSBU(Long sbuId, StrategicBusinessUnitVO sbuVO) {
		StrategicBusinessUnit sbu = sbuRepository.findById(sbuId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuId));
		sbu.setSbuName(sbuVO.getSbuName());
		sbu.setSbuDisplayName(sbuVO.getSbuDisplayName());
		BusinessUnit businessUnit = businessUnitRepository.findById(sbuVO.getBusinessUnit().getBusinessUnitId())
				.orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "BusinessUnit not exist"));
		sbu.setBusinessUnit(businessUnit);
		sbuRepository.save(sbu);
	}

	@Override
	public List<StrategicBusinessUnitVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<StrategicBusinessUnitVO> strategicBusinessUnitVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<StrategicBusinessUnit> pageResult = sbuRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream()
					.forEach(e -> strategicBusinessUnitVOs.add(StrategicBusinessUnitConverter.convertSBUToSBUVO(e)));
			return strategicBusinessUnitVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long sbuId) {
		StrategicBusinessUnit sbu = sbuRepository.findById(sbuId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + sbuId));
		Optional<BusinessUnit> bu = businessUnitRepository.findById(sbu.getBusinessUnit().getBusinessUnitId());
		if (bu.isPresent()) {
			BusinessUnit businessUnit = bu.get();
			if (!sbu.isActive() && !businessUnit.isActive()) {
				throw new RecordNotFoundException("BU is not active and its already linked to SBU");
			}
		}
		List<StrategicBusinessUnitHead> sbuHeadList = sbuHeadRepository.findBySBUId(sbuId);
		for (StrategicBusinessUnitHead sbuHead : sbuHeadList) {
			if (sbu.isActive() && sbuHead.isActive()) {
				throw new RecordNotFoundException(Constants.SBU_IS_ALREADY_LINKED);
			}

		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findBySBUId(sbuId);
		for (AnnualTargetEntry targetEntry : annualTargetEntryList) {
			if (sbu.isActive() && targetEntry.isActive()) {
				throw new RecordNotFoundException(Constants.SBU_IS_ALREADY_LINKED);
			}
		}
		List<RevenueResourceEntry> revenueResourceList = revenueResourceEntryRepository.findBySBUId(sbuId);
		for (RevenueResourceEntry revenueResource : revenueResourceList) {
			if (sbu.isActive() && revenueResource.isActive()) {
				throw new RecordNotFoundException(Constants.SBU_IS_ALREADY_LINKED);
			}
		}
		sbu.setActive(!sbu.isActive());
		sbuRepository.save(sbu);
	}

}
