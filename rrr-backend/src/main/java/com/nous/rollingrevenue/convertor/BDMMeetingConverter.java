package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.BDMMeeting;
import com.nous.rollingrevenue.vo.BDMMeetingVO;

@Component
public class BDMMeetingConverter {

	/**
	 * Convert BDMMeetingVO to BDMMeeting
	 * 
	 * @param BDMMeetingVO
	 * @return BDMMeeting
	 */

	public static BDMMeeting convertBDMMeetingVOToBDMMeeting(BDMMeetingVO bdmMeetingVO) {
		BDMMeeting bdmMeeting = new BDMMeeting();
		if (bdmMeetingVO != null) {
			if (bdmMeetingVO.getBdmMeetingId() != null) {
				bdmMeeting.setBdmMeetingId(bdmMeetingVO.getBdmMeetingId());
				bdmMeeting.setActive(bdmMeetingVO.isActive());
			}
			bdmMeeting.setBdmName(bdmMeetingVO.getBdmName());
			bdmMeeting.setRegion(bdmMeetingVO.getRegion());
			bdmMeeting.setMeetingName(bdmMeetingVO.getMeetingName());
			bdmMeeting.setMeetingDate(bdmMeetingVO.getMeetingDate());
			bdmMeeting.setMeetingTime(bdmMeetingVO.getMeetingTime());
			bdmMeeting.setFinancialYear(bdmMeetingVO.getFinancialYear());
		}
		return bdmMeeting;
	}

	/**
	 * Convert BDMMeeting to BDMMeetingVO
	 * 
	 * @param BDMMeeting
	 * @return BDMMeetingVO
	 */

	public static BDMMeetingVO convertBDMMeetingToBDMMeetingVO(BDMMeeting bdmMeeting) {
		BDMMeetingVO bdmMeetingVO = new BDMMeetingVO();
		if (bdmMeeting != null) {
			bdmMeetingVO.setBdmMeetingId(bdmMeeting.getBdmMeetingId());
			bdmMeetingVO.setBdmName(bdmMeeting.getBdmName());
			bdmMeetingVO.setRegion(bdmMeeting.getRegion());
			bdmMeetingVO.setMeetingName(bdmMeeting.getMeetingName());
			bdmMeetingVO.setMeetingDate(bdmMeeting.getMeetingDate());
			bdmMeetingVO.setMeetingTime(bdmMeeting.getMeetingTime());
			bdmMeetingVO.setFinancialYear(bdmMeeting.getFinancialYear());
			bdmMeetingVO.setActive(bdmMeeting.isActive());
		}
		return bdmMeetingVO;
	}

}
