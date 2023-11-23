package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.FortnightlyMeeting;
import com.nous.rollingrevenue.vo.FortnightlyMeetingVO;

@Component
public class FortnightlyMeetingConverter {

	private FortnightlyMeetingConverter() {
		super();
	}

	/**
	 * Convert FortnightlyMeetingVO to FortnightlyMeeting
	 * 
	 * @param FortnightlyMeetingVO
	 * @return FortnightlyMeeting
	 */

	public static FortnightlyMeeting convertFortnightlyMeetingVOToFortnightlyMeeting(
			FortnightlyMeetingVO fortnightlyMeetingVO) {
		FortnightlyMeeting fortnightlyMeeting = new FortnightlyMeeting();
		if (fortnightlyMeetingVO != null) {
			fortnightlyMeeting.setMeetingId(fortnightlyMeetingVO.getMeetingId());
			fortnightlyMeeting.setMeetingDate(fortnightlyMeetingVO.getMeetingDate());
			fortnightlyMeeting.setMeetingName1(fortnightlyMeetingVO.getMeetingName1());
			fortnightlyMeeting.setMeetingName2(fortnightlyMeetingVO.getMeetingName2());
			fortnightlyMeeting.setMeetingName3(fortnightlyMeetingVO.getMeetingName3());
			fortnightlyMeeting.setMeetingName4(fortnightlyMeetingVO.getMeetingName4());
			fortnightlyMeeting.setFinancialYear(FinancialYearConverter
					.convertFinancialYearVOToFinancialYear(fortnightlyMeetingVO.getFinancialYear()));
		}
		return fortnightlyMeeting;
	}

	/**
	 * Convert FortnightlyMeeting to FortnightlyMeetingVO
	 * 
	 * @param FortnightlyMeeting
	 * @return FortnightlyMeetingVO
	 */

	public static FortnightlyMeetingVO convertFortnightlyMeetingToFortnightlyMeetingVO(
			FortnightlyMeeting fortnightlyMeeting) {
		FortnightlyMeetingVO fortnightlyMeetingVO = new FortnightlyMeetingVO();
		if (fortnightlyMeeting != null) {
			fortnightlyMeetingVO.setMeetingId(fortnightlyMeeting.getMeetingId());
			fortnightlyMeetingVO.setMeetingDate(fortnightlyMeeting.getMeetingDate());
			fortnightlyMeetingVO.setMeetingDay(fortnightlyMeetingVO.getMeetingDay());
			fortnightlyMeetingVO.setFinancialYear(FinancialYearConverter
					.convertFinancialYearToFinancialYearVO(fortnightlyMeeting.getFinancialYear()));
			fortnightlyMeetingVO.setMeetingName1(fortnightlyMeeting.getMeetingName1());
			fortnightlyMeetingVO.setMeetingName2(fortnightlyMeeting.getMeetingName2());
			fortnightlyMeetingVO.setMeetingName3(fortnightlyMeeting.getMeetingName3());
			fortnightlyMeetingVO.setMeetingName4(fortnightlyMeeting.getMeetingName4());
			fortnightlyMeetingVO.setActive(fortnightlyMeeting.isActive());
		}
		return fortnightlyMeetingVO;
	}

}
