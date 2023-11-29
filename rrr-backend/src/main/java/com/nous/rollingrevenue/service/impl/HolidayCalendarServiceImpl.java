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

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.HolidayCalendarConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.FinancialYear;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.model.Location;
import com.nous.rollingrevenue.repository.FinancialYearRepository;
import com.nous.rollingrevenue.repository.HolidayCalendarRepository;
import com.nous.rollingrevenue.repository.LocationRepository;
import com.nous.rollingrevenue.service.HolidayCalendarService;
import com.nous.rollingrevenue.vo.HolidayCalendarVO;

@Service
@Transactional(readOnly = true)
public class HolidayCalendarServiceImpl implements HolidayCalendarService {

	@Autowired
	HolidayCalendarRepository holidayCalendarRepository;

	@Autowired
	FinancialYearRepository financialYearRepository;

	@Autowired
	LocationRepository locationRepository;

	@Override
	@Transactional
	public void addCalendar(HolidayCalendarVO holidayCalendarVO) {
		HolidayCalendar holidayCalendar = HolidayCalendarConverter
				.convertHolidayCalendarVOToHolidayCalendar(holidayCalendarVO);
		FinancialYear financialYear = financialYearRepository
				.findById(holidayCalendarVO.getFinancialYear().getFinancialYearId()).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "FinancialYear not exist"));
		holidayCalendar.setFinancialYear(financialYear);
		Location location = locationRepository.findById(holidayCalendarVO.getLocation().getLocationId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "Location not exist"));
		holidayCalendar.setLocation(location);
		holidayCalendar.setHolidayDay(holidayCalendar.getHolidayDate().getDayOfWeek().name());
		holidayCalendarRepository.save(holidayCalendar);
	}

	@Override
	@Transactional
	public void updateHolidayCalendar(Long holidayId, HolidayCalendarVO holidayCalendarVO) {
		HolidayCalendar holidayCalendar = holidayCalendarRepository.findById(holidayId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + holidayId));
		holidayCalendar.setHolidayName(holidayCalendarVO.getHolidayName());
		holidayCalendar.setHolidayDate(holidayCalendarVO.getHolidayDate());
		FinancialYear financialYear = financialYearRepository
				.findById(holidayCalendarVO.getFinancialYear().getFinancialYearId()).orElseThrow(
						() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "FinancialYear not exist"));
		holidayCalendar.setFinancialYear(financialYear);
		Location location = locationRepository.findById(holidayCalendarVO.getLocation().getLocationId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "Location not exist"));
		holidayCalendar.setLocation(location);
		holidayCalendar.setHolidayDay(holidayCalendarVO.getHolidayDate().getDayOfWeek().name());
		holidayCalendarRepository.save(holidayCalendar);
	}

	@Override
	public HolidayCalendarVO getHolidayCalendar(Long holidayId) {
		Optional<HolidayCalendar> holidayCalendarOptional = holidayCalendarRepository.findById(holidayId);
		if (holidayCalendarOptional.isPresent()) {
			return HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(holidayCalendarOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + holidayId);
	}

	@Override
	@Transactional
	public void deleteHolidayCalendar(Long holidayId) {
		Optional<HolidayCalendar> holidayCalendarOptional = holidayCalendarRepository.findById(holidayId);
		if (holidayCalendarOptional.isPresent()) {
			holidayCalendarRepository.deleteById(holidayId);
		} else {
			throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + holidayId);
		}
	}

	@Override
	public List<HolidayCalendarVO> getCalendars() {
		List<HolidayCalendarVO> holidayCalendarVOs = new ArrayList<>();
		holidayCalendarRepository.findAll().stream().forEach(holidayClaendar -> holidayCalendarVOs
				.add(HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(holidayClaendar)));
		return holidayCalendarVOs;
	}

	@Override
	public List<HolidayCalendarVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<HolidayCalendarVO> holidayCalendarVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<HolidayCalendar> pageResult = holidayCalendarRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(
					e -> holidayCalendarVOs.add(HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(e)));
			return holidayCalendarVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long holidayId) {
		HolidayCalendar holidayCalendar = holidayCalendarRepository.findById(holidayId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + holidayId));
		Optional<Location> optional = locationRepository.findById(holidayCalendar.getLocation().getLocationId());
		if (optional.isPresent()) {
			Location location = optional.get();
			if (!location.isActive() && !holidayCalendar.isActive()) {
				throw new RecordNotFoundException("Location is not active and its already linked to HolidayCalendar");
			}
		}
		Optional<FinancialYear> findbyId = financialYearRepository
				.findById(holidayCalendar.getFinancialYear().getFinancialYearId());
		if (findbyId.isPresent()) {
			FinancialYear financialYear = findbyId.get();
			if (!financialYear.isActive() && !holidayCalendar.isActive()) {
				throw new RecordNotFoundException(
						"FinancialYear is not active and its already linked to HolidayCalendar");
			}
		}
		holidayCalendar.setActive(!holidayCalendar.isActive());
		holidayCalendarRepository.save(holidayCalendar);
	}

	@Override
	public List<HolidayCalendarVO> getHolidayCalendarByFinancialYear(String financialYear) {
		List<HolidayCalendarVO> holidayCalendarVOs = new ArrayList<>();
		Optional<FinancialYear> findFinancialYearById = financialYearRepository.findByFinancialYearName(financialYear);
		if (findFinancialYearById.isPresent()) {
			findFinancialYearById.get().getHolidayCalendar().stream().forEach(holidayClaendar -> holidayCalendarVOs
					.add(HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(holidayClaendar)));
		}
		return holidayCalendarVOs;
	}
}
