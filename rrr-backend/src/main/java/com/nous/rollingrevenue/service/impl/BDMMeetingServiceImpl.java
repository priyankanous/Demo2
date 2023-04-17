package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.BDMMeetingConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BDMMeeting;
import com.nous.rollingrevenue.model.BusinessDevelopmentManager;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.repository.BDMMeetingRepository;
import com.nous.rollingrevenue.repository.BusinessDevelopmentManagerRepository;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.RegionRepository;
import com.nous.rollingrevenue.service.BDMMeetingService;
import com.nous.rollingrevenue.vo.BDMMeetingVO;

@Service
@Transactional(readOnly = true)
public class BDMMeetingServiceImpl implements BDMMeetingService {

	@Autowired
	private BDMMeetingRepository bdmMeetingRepository;

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private BusinessDevelopmentManagerRepository bdmRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Override
	@Transactional
	public BDMMeetingVO saveBDMMeeting(BDMMeetingVO bdmMeetingVO) {
		BDMMeeting bdmMeeting = bdmMeetingRepository
				.save(BDMMeetingConverter.convertBDMMeetingVOToBDMMeeting(bdmMeetingVO));
		BusinessDevelopmentManager bdm = bdmRepository.findById(bdmMeetingVO.getBusinessDevelopmentManager().getBdmId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "BusinessDevelopmentManager not exist"));
		bdmMeeting.setBusinessDevelopmentManager(bdm);
		Region region = regionRepository.findById(bdmMeetingVO.getRegion().getRegionId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "Region not exist"));
		bdmMeeting.setRegion(region);
		FinancialYear financialYear = financialYearRepository
				.findById(bdmMeetingVO.getFinancialYear().getFinancialYearId()).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "FinancialYear not exist"));
		bdmMeeting.setFinancialYear(financialYear);
		return BDMMeetingConverter.convertBDMMeetingToBDMMeetingVO(bdmMeeting);
	}

	@Override
	@Transactional
	public BDMMeetingVO updateBDMMeeting(Long bdmMeetingId, BDMMeetingVO bdmMeetingVO) {
		BDMMeeting bdmMeeting = bdmMeetingRepository.findById(bdmMeetingId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmMeetingId));
		BusinessDevelopmentManager bdm = bdmRepository.findById(bdmMeetingVO.getBusinessDevelopmentManager().getBdmId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "BusinessDevelopmentManager not exist"));
		bdmMeeting.setBusinessDevelopmentManager(bdm);
		Region region = regionRepository.findById(bdmMeetingVO.getRegion().getRegionId()).orElseThrow(
				() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "Region not exist"));
		bdmMeeting.setRegion(region);
		bdmMeeting.setMeetingName(bdmMeetingVO.getMeetingName());
		bdmMeeting.setMeetingDate(bdmMeetingVO.getMeetingDate());
		bdmMeeting.setMeetingTime(bdmMeetingVO.getMeetingTime());
		FinancialYear financialYear = financialYearRepository
				.findById(bdmMeetingVO.getFinancialYear().getFinancialYearId()).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "FinancialYear not exist"));
		bdmMeeting.setFinancialYear(financialYear);
		return BDMMeetingConverter.convertBDMMeetingToBDMMeetingVO(bdmMeetingRepository.save(bdmMeeting));
	}

	@Override
	@Transactional
	public void deleteBDMMeetingById(Long bdmMeetingId) {
		bdmMeetingRepository.findById(bdmMeetingId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmMeetingId));
		bdmMeetingRepository.deleteById(bdmMeetingId);
	}

	@Override
	public List<BDMMeetingVO> getBDMMeetingByFinancialYear(String financialYear) {
		List<BDMMeetingVO> bdmMeetingVOs = new ArrayList<>();
		Optional<FinancialYear> findByFinancialYearName = financialYearRepository.findByFinancialYearName(financialYear);
		if(findByFinancialYearName.isPresent()) {
			findByFinancialYearName.get().getBdmMeetings().stream().forEach(bdmMeeting -> {
				bdmMeetingVOs.add(BDMMeetingConverter.convertBDMMeetingToBDMMeetingVO(bdmMeeting));
			});
		}
		return bdmMeetingVOs;
	}

	@Override
	@Transactional
	public BDMMeetingVO activateOrDeactivateById(Long bdmMeetingid) {
		BDMMeeting bdmMeeting = bdmMeetingRepository.findById(bdmMeetingid)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmMeetingid));
		bdmMeeting.setActive(!bdmMeeting.isActive());
		return BDMMeetingConverter.convertBDMMeetingToBDMMeetingVO(bdmMeetingRepository.save(bdmMeeting));
	}

}
