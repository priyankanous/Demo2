package com.nous.rollingrevenue.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
		Optional<FinancialYear> findById = financialYearRepository
				.findById(fortnightlyMeetingVO.getFinancialYear().getFinancialYearId());
		if (findById.isPresent()) {
			FinancialYear findByfinancialYear = findById.get();
			if (findByfinancialYear != null) {
				FinancialYearVO financialYearVO = FinancialYearConverter
						.convertFinancialYearToFinancialYearVO(findByfinancialYear);
				List<HolidayCalendarVO> holidayCalendarVOs = holidayCalendarService
						.getHolidayCalendarByFinancialYear(findByfinancialYear.getFinancialYearName());
				if (!holidayCalendarVOs.isEmpty()) {
					LocalDate startDate = fortnightlyMeetingVO.getMeetingDate();
					LocalDate endDate = financialYearVO.getEndingOn();
					logger.info("generating recurring dates for start date" + startDate + " end date " + endDate);
					List<LocalDate> recurringDates = this.generateRecurringDates(startDate, endDate);
					List<FortnightlyMeeting> fortnightlyMeetings = new ArrayList<>();
					FortnightlyMeeting fortnightMeeting = new FortnightlyMeeting();
					fortnightMeeting.setMeetingDate(fortnightlyMeetingVO.getMeetingDate());
					fortnightMeeting.setMeetingDay(fortnightlyMeetingVO.getMeetingDate().getDayOfWeek().name());
					fortnightMeeting.setFinancialYear(findByfinancialYear);
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
						fortnightlyMeetings
								.add(setFortnightlyMeetingDetails(tempDate, findByfinancialYear, fortnightlyMeetingVO));
					}
					logger.info("saving fortnightlymeetings");
					fortnightlyMeetingRepository.saveAll(fortnightlyMeetings);
				}
			}
		} else {
			throw new RecordNotFoundException(
					ErrorConstants.RECORD_NOT_EXIST + fortnightlyMeetingVO.getFinancialYear().getFinancialYearId());
		}
	}

	private FortnightlyMeeting setFortnightlyMeetingDetails(LocalDate tempDate, FinancialYear findByfinancialYear,
			FortnightlyMeetingVO fortnightlyMeetingVO) {
		FortnightlyMeeting fortnightlyMeeting = new FortnightlyMeeting();
		fortnightlyMeeting.setMeetingDate(tempDate);
		fortnightlyMeeting.setMeetingDay(tempDate.getDayOfWeek().name());
		fortnightlyMeeting.setFinancialYear(findByfinancialYear);
		fortnightlyMeeting.setMeetingName1(fortnightlyMeetingVO.getMeetingName1());
		fortnightlyMeeting.setMeetingName2(fortnightlyMeetingVO.getMeetingName2());
		fortnightlyMeeting.setMeetingName3(fortnightlyMeetingVO.getMeetingName3());
		fortnightlyMeeting.setMeetingName4(fortnightlyMeetingVO.getMeetingName4());
		return fortnightlyMeeting;
	}

	@Override
	@Transactional
	public void updateFortnightlyMeetings(FortnightlyMeetingVO fortnightlyMeetingVO) {
		Optional<FinancialYear> findById = financialYearRepository
				.findById(fortnightlyMeetingVO.getFinancialYear().getFinancialYearId());
		if (findById.isPresent()) {
			List<FortnightlyMeeting> fortnightlyMeetings = findById.get().getFortnightlyMeetings();
			if (!fortnightlyMeetings.isEmpty()) {
				if (!fortnightlyMeetingVO.getMeetingDate()
						.equals(fortnightlyMeetings.stream().filter(FortnightlyMeeting::isActive)
								.sorted(Comparator.comparing(FortnightlyMeeting::getMeetingDate))
								.map(FortnightlyMeeting::getMeetingDate).findFirst().get())) {
					fortnightlyMeetings.stream().map(fortnightlyMeeting -> fortnightlyMeeting.getMeetingId())
							.forEach(id -> this.activateOrDeactivateById(id));
					this.generateFortnightlyMeetings(fortnightlyMeetingVO);
				} else {
					fortnightlyMeetings.stream().filter(FortnightlyMeeting::isActive).forEach(fortnightlyMeeting -> {
						fortnightlyMeeting.setMeetingName1(fortnightlyMeetingVO.getMeetingName1());
						fortnightlyMeeting.setMeetingName2(fortnightlyMeetingVO.getMeetingName2());
						fortnightlyMeeting.setMeetingName3(fortnightlyMeetingVO.getMeetingName3());
						fortnightlyMeeting.setMeetingName4(fortnightlyMeetingVO.getMeetingName4());
					});
					fortnightlyMeetingRepository.saveAll(fortnightlyMeetings);
				}
			}
		} else {
			throw new RecordNotFoundException(
					ErrorConstants.RECORD_NOT_EXIST + fortnightlyMeetingVO.getFinancialYear().getFinancialYearId());
		}
	}

	@Override
	public List<FortnightlyMeetingVO> getFortnightlyMeetingsByFinancialYear(String financialYear) {
		List<FortnightlyMeetingVO> fortnightlyMeetingVOs = new ArrayList<>();
		Optional<FinancialYear> findByFinancialYearName = financialYearRepository
				.findByFinancialYearName(financialYear);
		if (findByFinancialYearName.isPresent()) {
			findByFinancialYearName.get().getFortnightlyMeetings().stream()
					.filter(fortnightlyMeeting -> fortnightlyMeeting.isActive() == true)
					.forEach(fortnightlyMeeting -> fortnightlyMeetingVOs.add(FortnightlyMeetingConverter
							.convertFortnightlyMeetingToFortnightlyMeetingVO(fortnightlyMeeting)));
		}
		return fortnightlyMeetingVOs;
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long id) {
		FortnightlyMeeting fortnightlyMeeting = fortnightlyMeetingRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + id));
		Optional<FinancialYear> optional = financialYearRepository
				.findById(fortnightlyMeeting.getFinancialYear().getFinancialYearId());
		if (optional.isPresent()) {
			FinancialYear financialYear = optional.get();
			if (!financialYear.isActive() && !fortnightlyMeeting.isActive()) {
				throw new RecordNotFoundException(
						"FinancialYear is not active and its already linked to FortnightlyMeeting");
			}
		}
		fortnightlyMeeting.setActive(!fortnightlyMeeting.isActive());
		fortnightlyMeetingRepository.save(fortnightlyMeeting);
	}

	// Generate Recurring dates of Alternate Friday based on Start date and
	// End Date
	private List<LocalDate> generateRecurringDates(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> recurringDates = new ArrayList<>();
		LocalDate tempDateVal = startDate;
		int increment = 1;
		while (tempDateVal.isBefore(endDate)) {
			tempDateVal = tempDateVal.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
			if (increment % 2 == 0 && tempDateVal.isBefore(endDate)) {
				recurringDates.add(tempDateVal);
			}
			increment++;
		}
		return recurringDates;
	}

	@Override
	public FortnightlyMeetingVO getFortnightlyMeetingsById(Long meetingId) {
		FortnightlyMeeting fortnightlyMeeting = fortnightlyMeetingRepository.findById(meetingId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + meetingId));
		return FortnightlyMeetingConverter.convertFortnightlyMeetingToFortnightlyMeetingVO(fortnightlyMeeting);
	}

}
