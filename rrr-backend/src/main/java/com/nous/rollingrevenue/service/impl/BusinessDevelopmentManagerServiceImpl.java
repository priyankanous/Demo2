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
import com.nous.rollingrevenue.convertor.BusinessDevelopmentManagerConverter;
import com.nous.rollingrevenue.convertor.BusinessUnitConverter;
import com.nous.rollingrevenue.convertor.RegionConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.BDMMeeting;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.model.BusinessUnit;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.BDMMeetingRepository;
import com.nous.rollingrevenue.repository.BusinessDevelopmentManagerRepository;
import com.nous.rollingrevenue.repository.RevenueEntryRespository;
import com.nous.rollingrevenue.service.BusinessDevelopmentManagerService;
import com.nous.rollingrevenue.vo.BDMVO;

@Service
public class BusinessDevelopmentManagerServiceImpl implements BusinessDevelopmentManagerService {

	@Autowired
	BusinessDevelopmentManagerRepository businessDevelopmentManagerRepository;

	@Autowired
	private BDMMeetingRepository bdmMeetingRepository;

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Autowired
	private RevenueEntryRespository revenueEntryRespository;

	@Override
	@Transactional
	public void addBDMDetails(BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = BusinessDevelopmentManagerConverter.convertBdmVOToBdm(bdmVO);
		businessDevelopmentManagerRepository.save(bdm);
	}

	@Override
	@Transactional
	public void updateBDMDetails(Long bdmId, BDMVO bdmVO) {
		BusinessDevelopmentManager bdm = businessDevelopmentManagerRepository.findById(bdmId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmId));
		bdm.setBdmName(bdmVO.getBdmName());
		bdm.setBdmDisplayName(bdmVO.getBdmDisplayName());
		bdm.setActiveFrom(bdmVO.getActiveFrom());
		bdm.setActiveUntil(bdmVO.getActiveUntil());
		List<BusinessUnit> businessUnits = new ArrayList<>();
		bdmVO.getBusinessUnits().stream()
				.forEach(bdmVo -> businessUnits.add(BusinessUnitConverter.convertBusinessUnitVOToBusinessUnit(bdmVo)));
		bdm.setBusinessUnits(businessUnits);
		List<Region> regions = new ArrayList<>();
		bdmVO.getRegions().stream().forEach(regionVO -> regions.add(RegionConverter.convertRegionVOToRegion(regionVO)));
		bdm.setRegions(regions);
		businessDevelopmentManagerRepository.save(bdm);
	}

	@Override
	public BDMVO getBdmById(Long bdmId) {
		Optional<BusinessDevelopmentManager> bdmOptional = businessDevelopmentManagerRepository.findById(bdmId);
		if (bdmOptional.isPresent()) {
			return BusinessDevelopmentManagerConverter.convertBdmToBdmVO(bdmOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmId);
	}

	@Override
	@Transactional
	public void deleteBDM(Long bdmId) {
		Optional<BusinessDevelopmentManager> bdmOptional = businessDevelopmentManagerRepository.findById(bdmId);
		List<BDMMeeting> meetingList = bdmMeetingRepository.findByBDMId(bdmId);
		if (!meetingList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BDM_IS_ALREADY_LINKED);
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByBDMId(bdmId);
		if (!annualTargetEntryList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BDM_IS_ALREADY_LINKED);
		}
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByBDMId(bdmId);
		if (!revenueEntryList.isEmpty()) {
			throw new RecordNotFoundException(Constants.BDM_IS_ALREADY_LINKED);
		}
		if (bdmOptional.isPresent()) {
			businessDevelopmentManagerRepository.deleteById(bdmId);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmId);
		}
	}

	@Override
	public List<BDMVO> getBDM() {
		List<BDMVO> bdmVOs = new ArrayList<>();
		businessDevelopmentManagerRepository.findAll().stream()
				.forEach(bdm -> bdmVOs.add(BusinessDevelopmentManagerConverter.convertBdmToBdmVO(bdm)));
		return bdmVOs;
	}

	@Override
	public List<BDMVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<BDMVO> bdmVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<BusinessDevelopmentManager> pageResult = businessDevelopmentManagerRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream()
					.forEach(e -> bdmVOs.add(BusinessDevelopmentManagerConverter.convertBdmToBdmVO(e)));
			return bdmVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long bdmId) {
		BusinessDevelopmentManager bdm = businessDevelopmentManagerRepository.findById(bdmId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmId));
		List<BusinessUnit> businessUnits = bdm.getBusinessUnits();
		for (BusinessUnit bu : businessUnits) {
			if (!bu.isActive() && !bdm.isActive()) {
				throw new RecordNotFoundException("BU is not active and its already linked to BDM");
			}
		}
		List<Region> regionList = bdm.getRegions();
		for (Region region : regionList) {
			if (!region.isActive() && !bdm.isActive()) {
				throw new RecordNotFoundException("Region is not active and its already linked to BDM");
			}
		}
		isActiveValidationForBDM(bdm, bdmId);
		bdm.setActive(!bdm.isActive());
		businessDevelopmentManagerRepository.save(bdm);
	}

	private void isActiveValidationForBDM(BusinessDevelopmentManager bdm, Long bdmId) {
		List<BDMMeeting> meetingList = bdmMeetingRepository.findByBDMId(bdmId);
		for (BDMMeeting bdmMeeting : meetingList) {
			if (bdm.isActive() && bdmMeeting.isActive()) {
				throw new RecordNotFoundException(Constants.BDM_IS_ALREADY_LINKED);
			}
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByBDMId(bdmId);
		for (AnnualTargetEntry targetEntry : annualTargetEntryList) {
			if (bdm.isActive() && targetEntry.isActive()) {
				throw new RecordNotFoundException(Constants.BDM_IS_ALREADY_LINKED);
			}
		}
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByBDMId(bdmId);
		for (RevenueEntry revenueEntry : revenueEntryList) {
			if (bdm.isActive() && revenueEntry.isActive()) {
				throw new RecordNotFoundException(Constants.BDM_IS_ALREADY_LINKED);
			}
		}
	}

}
