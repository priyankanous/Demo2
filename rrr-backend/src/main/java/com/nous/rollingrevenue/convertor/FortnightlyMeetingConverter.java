package com.nous.rollingrevenue.convertor;

import org.springframework.stereotype.Component;

import com.nous.rollingrevenue.model.FortnightlyMeeting;
import com.nous.rollingrevenue.vo.FortnightlyMeetingVO;

@Component
public class FortnightlyMeetingConverter {
	
	/**
	 * Convert FortnightlyMeetingVO to FortnightlyMeeting
	 * 
	 * @param FortnightlyMeetingVO
	 * @return FortnightlyMeeting
	 */

	public static FortnightlyMeeting convertFortnightlyMeetingVOToFortnightlyMeeting(FortnightlyMeetingVO fortnightlyMeetingVO) {
		FortnightlyMeeting fortnightlyMeeting = new FortnightlyMeeting();
		if (fortnightlyMeetingVO != null) {
			if (fortnightlyMeetingVO.getMeetingId() != null) {
				fortnightlyMeeting.setMeetingId(fortnightlyMeetingVO.getMeetingId());
			}
			fortnightlyMeeting.setMeetingDate(fortnightlyMeetingVO.getMeetingDate());
			fortnightlyMeeting.setFinancialYear(fortnightlyMeetingVO.getFinancialYear());
		}
		return fortnightlyMeeting;
	}
	
	
	/**
	 * Convert FortnightlyMeeting to FortnightlyMeetingVO
	 * 
	 * @param FortnightlyMeeting
	 * @return FortnightlyMeetingVO
	 */

	public static FortnightlyMeetingVO convertFortnightlyMeetingToFortnightlyMeetingVO(FortnightlyMeeting fortnightlyMeeting) {
		FortnightlyMeetingVO fortnightlyMeetingVO = new FortnightlyMeetingVO();
		if (fortnightlyMeeting != null) {
			fortnightlyMeetingVO.setMeetingId(fortnightlyMeeting.getMeetingId());
			fortnightlyMeetingVO.setMeetingDate(fortnightlyMeeting.getMeetingDate());
			fortnightlyMeetingVO.setFinancialYear(fortnightlyMeeting.getFinancialYear());
		}
		return fortnightlyMeetingVO;
	}

}
