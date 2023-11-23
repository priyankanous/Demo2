package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.BDMMeeting;
import com.nous.rollingrevenue.vo.BDMMeetingVO;

@Component
public class BDMMeetingConverter {

	private BDMMeetingConverter() {
		super();
	}

	/**
	 * Convert BDMMeetingVO to BDMMeeting
	 * 
	 * @param BDMMeetingVO
	 * @return BDMMeeting
	 */

	public static BDMMeeting convertBDMMeetingVOToBDMMeeting(BDMMeetingVO bdmMeetingVO) {
		BDMMeeting bdmMeeting = new BDMMeeting();
		if (bdmMeetingVO != null) {
			bdmMeeting.setBdmMeetingId(bdmMeetingVO.getBdmMeetingId());
			bdmMeeting.setBusinessDevelopmentManager(BusinessDevelopmentManagerConverter
					.convertBdmVOToBdm(bdmMeetingVO.getBusinessDevelopmentManager()));
			bdmMeeting.setRegion(RegionConverter.convertRegionVOToRegion(bdmMeetingVO.getRegion()));
			bdmMeeting.setMeetingName(bdmMeetingVO.getMeetingName());
			bdmMeeting.setMeetingDate(bdmMeetingVO.getMeetingDate());
			bdmMeeting.setMeetingTime(bdmMeetingVO.getMeetingTime());
			bdmMeeting.setFinancialYear(
					FinancialYearConverter.convertFinancialYearVOToFinancialYear(bdmMeetingVO.getFinancialYear()));
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
			bdmMeetingVO.setBusinessDevelopmentManager(
					BusinessDevelopmentManagerConverter.convertBdmToBdmVO(bdmMeeting.getBusinessDevelopmentManager()));
			bdmMeetingVO.setRegion(RegionConverter.convertRegionToRegionVO(bdmMeeting.getRegion()));
			bdmMeetingVO.setMeetingName(bdmMeeting.getMeetingName());
			bdmMeetingVO.setMeetingDate(bdmMeeting.getMeetingDate());
			bdmMeetingVO.setMeetingTime(bdmMeeting.getMeetingTime());
			bdmMeetingVO.setFinancialYear(
					FinancialYearConverter.convertFinancialYearToFinancialYearVO(bdmMeeting.getFinancialYear()));
			bdmMeetingVO.setActive(bdmMeeting.isActive());
		}
		return bdmMeetingVO;
	}

}
