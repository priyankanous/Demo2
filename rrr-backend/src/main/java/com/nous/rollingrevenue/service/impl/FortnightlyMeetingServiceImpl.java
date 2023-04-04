package com.nous.rollingrevenue.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.convertor.FortnightlyMeetingConverter;
import com.nous.rollingrevenue.model.FortnightlyMeeting;
import com.nous.rollingrevenue.repository.FortnightlyMeetingRepository;
import com.nous.rollingrevenue.service.FortnightlyMeetingService;
import com.nous.rollingrevenue.vo.FinancialYearVO;
import com.nous.rollingrevenue.vo.FortnightlyMeetingVO;

@Service
@Transactional(readOnly = true)
public class FortnightlyMeetingServiceImpl implements FortnightlyMeetingService {

	private static final Logger logger = LoggerFactory.getLogger(FortnightlyMeetingServiceImpl.class);
	
	@Autowired
	private FortnightlyMeetingRepository fortnightlyMeetingRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void generateFortnightlyMeetingsOfFinancialYear(FinancialYearVO financialYearVO) {
		
		LocalDate startDate = financialYearVO.getStartingFrom();
		LocalDate endDate = financialYearVO.getEndingOn();
		String financialYear = financialYearVO.getFinancialYearName();
		logger.info("generating recurring dates for start date"+startDate+" end date "+endDate);
		List<LocalDate> recurringDates = this.generateRecurringDates(startDate, endDate);
		List<FortnightlyMeeting> fortnightlyMeetings = new ArrayList<>();
		for (LocalDate recurringDate : recurringDates) {
			FortnightlyMeeting fortnightlyMeeting = new FortnightlyMeeting();
			fortnightlyMeeting.setMeetingDate(recurringDate);
			fortnightlyMeeting.setFinancialYear(financialYear);
			fortnightlyMeetings.add(fortnightlyMeeting);
		}
		logger.info("saving fortnightlymeetings");
		fortnightlyMeetingRepository.saveAll(fortnightlyMeetings);
	}

	
	@Override
	public List<FortnightlyMeetingVO> getFortnightlyMeetingsByFinancialYear(String financialYear) {
		List<FortnightlyMeetingVO> fortnightlyMeetingVOs = new ArrayList<>();
		fortnightlyMeetingRepository.findByFinancialYear(financialYear).stream()
				.forEach(fortnightlyMeeting -> fortnightlyMeetingVOs.add(FortnightlyMeetingConverter
						.convertFortnightlyMeetingToFortnightlyMeetingVO(fortnightlyMeeting)));
		return fortnightlyMeetingVOs;
	}
	

	@Override
	@Transactional
	public void deleteFortnightlyMeetingByFinancialYear(String financialYear) {
		fortnightlyMeetingRepository.deleteAll(fortnightlyMeetingRepository.findByFinancialYear(financialYear));
	}
	

	private List<LocalDate> generateRecurringDates(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> recurringDates = new ArrayList<>();
		LocalDate tempDate = startDate;
		int increment = 1;
		while (tempDate.isBefore(endDate)) {
			tempDate = tempDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
			if (increment % 2 == 0) {
				if (tempDate.isBefore(endDate)) {
					recurringDates.add(tempDate);
				}
			}
			increment++;
		}
		return recurringDates;
	}


}
