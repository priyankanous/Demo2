package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.BDMMeetingConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.BDMMeeting;
import com.nous.rollingrevenue.repository.BDMMeetingRepository;
import com.nous.rollingrevenue.service.BDMMeetingService;
import com.nous.rollingrevenue.vo.BDMMeetingVO;

@Service
@Transactional(readOnly = true)
public class BDMMeetingServiceImpl implements BDMMeetingService {

	@Autowired
	private BDMMeetingRepository bdmMeetingRepository;

	@Override
	@Transactional
	public BDMMeetingVO saveBDMMeeting(BDMMeetingVO bdmMeetingVO) {
		BDMMeeting bdmMeeting = bdmMeetingRepository
				.save(BDMMeetingConverter.convertBDMMeetingVOToBDMMeeting(bdmMeetingVO));
		return BDMMeetingConverter.convertBDMMeetingToBDMMeetingVO(bdmMeeting);
	}

	@Override
	@Transactional
	public BDMMeetingVO updateBDMMeeting(Long bdmMeetingId, BDMMeetingVO bdmMeetingVO) {
		BDMMeeting bdmMeeting = bdmMeetingRepository.findById(bdmMeetingId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + bdmMeetingId));
		bdmMeeting.setBdmName(bdmMeetingVO.getBdmName());
		bdmMeeting.setRegion(bdmMeetingVO.getRegion());
		bdmMeeting.setMeetingName(bdmMeetingVO.getMeetingName());
		bdmMeeting.setMeetingDate(bdmMeetingVO.getMeetingDate());
		bdmMeeting.setMeetingTime(bdmMeetingVO.getMeetingTime());
		bdmMeeting.setFinancialYear(bdmMeetingVO.getFinancialYear());
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
		bdmMeetingRepository.findByFinancialYear(financialYear).stream().forEach(bdmMeeting -> {
			bdmMeetingVOs.add(BDMMeetingConverter.convertBDMMeetingToBDMMeetingVO(bdmMeeting));
		});
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
