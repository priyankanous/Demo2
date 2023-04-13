package com.nous.rollingrevenue.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.FinancialYearConverter;
import com.nous.rollingrevenue.convertor.FortnightlyMeetingConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.FortnightlyMeeting;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.FortnightlyMeetingRepository;
import com.nous.rollingrevenue.service.FortnightlyMeetingService;
import com.nous.rollingrevenue.service.HolidayCalendarService;
import com.nous.rollingrevenue.vo.FinancialYearVO;
import com.nous.rollingrevenue.vo.FortnightlyMeetingVO;
import com.nous.rollingrevenue.vo.HolidayCalendarVO;

@Service
@Transactional(readOnly = true)
public class FortnightlyMeetingServiceImpl implements FortnightlyMeetingService {

	private static final Logger logger = LoggerFactory.getLogger(FortnightlyMeetingServiceImpl.class);

	@Autowired
	private FortnightlyMeetingRepository fortnightlyMeetingRepository;

	@Autowired
	private FinancialYearRepository financialYearRepository;

	@Autowired
	private HolidayCalendarService holidayCalendarService;

	private LocalDate tempDate = null;

	@Override
	@Transactional
	public void generateFortnightlyMeetings(FortnightlyMeetingVO fortnightlyMeetingVO) {
		FinancialYear findByfinancialYear = financialYearRepository
				.findByFinancialYearName(fortnightlyMeetingVO.getFinancialYear());
		if (findByfinancialYear != null) {
			FinancialYearVO financialYearVO = FinancialYearConverter
					.convertFinancialYearToFinancialYearVO(findByfinancialYear);
			List<HolidayCalendarVO> holidayCalendarVOs = holidayCalendarService
					.getHolidayCalendarByFinancialYear(fortnightlyMeetingVO.getFinancialYear());
			if (!holidayCalendarVOs.isEmpty()) {
				LocalDate startDate = fortnightlyMeetingVO.getMeetingDate();
				LocalDate endDate = financialYearVO.getEndingOn();
				String financialYear = fortnightlyMeetingVO.getFinancialYear();
				logger.info("generating recurring dates for start date" + startDate + " end date " + endDate
						+ "FinancialYear" + financialYear);
				List<LocalDate> recurringDates = this.generateRecurringDates(startDate, endDate);
				List<FortnightlyMeeting> fortnightlyMeetings = new ArrayList<>();
				FortnightlyMeeting fortnightMeeting = new FortnightlyMeeting();
				fortnightMeeting.setMeetingDate(fortnightlyMeetingVO.getMeetingDate());
				fortnightMeeting.setMeetingDay(fortnightlyMeetingVO.getMeetingDate().getDayOfWeek().name());
				fortnightMeeting.setFinancialYear(fortnightlyMeetingVO.getFinancialYear());
				fortnightMeeting.setMeetingName1(fortnightlyMeetingVO.getMeetingName1());
				fortnightMeeting.setMeetingName2(fortnightlyMeetingVO.getMeetingName2());
				fortnightMeeting.setMeetingName3(fortnightlyMeetingVO.getMeetingName3());
				fortnightMeeting.setMeetingName4(fortnightlyMeetingVO.getMeetingName4());
				fortnightlyMeetings.add(fortnightMeeting);
				for (LocalDate recurringDate : recurringDates) {
					tempDate = recurringDate;
					while (holidayCalendarVOs.stream()
							.anyMatch(holidayCalendar -> holidayCalendar.getHolidayDate().isEqual(tempDate))) {
						tempDate = tempDate.minusDays(1);
					}
					FortnightlyMeeting fortnightlyMeeting = new FortnightlyMeeting();
					fortnightlyMeeting.setMeetingDate(tempDate);
					fortnightlyMeeting.setMeetingDay(tempDate.getDayOfWeek().name());
					fortnightlyMeeting.setFinancialYear(financialYear);
					fortnightlyMeeting.setMeetingName1(fortnightlyMeetingVO.getMeetingName1());
					fortnightlyMeeting.setMeetingName2(fortnightlyMeetingVO.getMeetingName2());
					fortnightlyMeeting.setMeetingName3(fortnightlyMeetingVO.getMeetingName3());
					fortnightlyMeeting.setMeetingName4(fortnightlyMeetingVO.getMeetingName4());
					fortnightlyMeetings.add(fortnightlyMeeting);
				}
				logger.info("saving fortnightlymeetings");
				fortnightlyMeetingRepository.saveAll(fortnightlyMeetings);
			}
		}
	}

	@Override
	@Transactional
	public void updateFortnightlyMeetings(FortnightlyMeetingVO fortnightlyMeetingVO) {
		List<FortnightlyMeeting> findByFinancialYear = fortnightlyMeetingRepository
				.findByFinancialYear(fortnightlyMeetingVO.getFinancialYear());
		if (!findByFinancialYear.isEmpty()) {
			if (!fortnightlyMeetingVO.getMeetingDate()
					.equals(findByFinancialYear.stream().filter(fortnightlyMeeting -> fortnightlyMeeting.isActive()==true)
							.sorted(Comparator.comparing(FortnightlyMeeting::getMeetingDate))
							.map(FortnightlyMeeting::getMeetingDate).findFirst().get())) {
				findByFinancialYear.stream().map(fortnightlyMeeting -> fortnightlyMeeting.getMeetingId())
						.forEach(id -> this.activateOrDeactivateById(id));
				this.generateFortnightlyMeetings(fortnightlyMeetingVO);
			} else {
				findByFinancialYear.stream().filter(fortnightlyMeeting -> fortnightlyMeeting.isActive()==true)
				.forEach(fortnightlyMeeting -> {
					fortnightlyMeeting.setMeetingName1(fortnightlyMeetingVO.getMeetingName1());
					fortnightlyMeeting.setMeetingName2(fortnightlyMeetingVO.getMeetingName2());
					fortnightlyMeeting.setMeetingName3(fortnightlyMeetingVO.getMeetingName3());
					fortnightlyMeeting.setMeetingName4(fortnightlyMeetingVO.getMeetingName4());
				});
				fortnightlyMeetingRepository.saveAll(findByFinancialYear);
			}
		}
	}

	@Override
	public List<FortnightlyMeetingVO> getFortnightlyMeetingsByFinancialYear(String financialYear) {
		List<FortnightlyMeetingVO> fortnightlyMeetingVOs = new ArrayList<>();
		fortnightlyMeetingRepository.findByFinancialYear(financialYear).stream().filter(fortnightlyMeeting -> fortnightlyMeeting.isActive()==true)
				.forEach(fortnightlyMeeting -> fortnightlyMeetingVOs.add(FortnightlyMeetingConverter
						.convertFortnightlyMeetingToFortnightlyMeetingVO(fortnightlyMeeting)));
		return fortnightlyMeetingVOs;
	}

	@Override
	@Transactional
	public FortnightlyMeetingVO activateOrDeactivateById(Long id) {
		FortnightlyMeeting fortnightlyMeeting = fortnightlyMeetingRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		fortnightlyMeeting.setActive(!fortnightlyMeeting.isActive());
		return FortnightlyMeetingConverter
				.convertFortnightlyMeetingToFortnightlyMeetingVO(fortnightlyMeetingRepository.save(fortnightlyMeeting));
	}

	// Generate Recurring dates of Alternate Friday based on FinancialYear Start and
	// End Date
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
