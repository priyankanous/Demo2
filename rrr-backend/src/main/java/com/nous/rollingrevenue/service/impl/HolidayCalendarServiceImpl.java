package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.repository.HolidayCalendarRepository;
import com.nous.rollingrevenue.service.HolidayCalendarService;
import com.nous.rollingrevenue.vo.HolidayCalendarVO;

@Service
@Transactional(readOnly = true)
public class HolidayCalendarServiceImpl implements HolidayCalendarService {

	@Autowired
	HolidayCalendarRepository holidayCalendarRepository;

	@Override
	@Transactional
	public HolidayCalendarVO addCalendar(HolidayCalendarVO holidayCalendarVO) {
		HolidayCalendar holidayCalendar = HolidayCalendarConverter
				.convertHolidayCalendarVOToHolidayCalendar(holidayCalendarVO);
		return HolidayCalendarConverter
				.convertHolidayCalendarToHolidayCalendarVO(holidayCalendarRepository.save(holidayCalendar));
	}

	@Override
	@Transactional
	@CachePut(value = "holidaycalendar", key = "#holidayId")
	public HolidayCalendarVO updateHolidayCalendar(Long holidayId, HolidayCalendarVO holidayCalendarVO) {
		HolidayCalendar holidayCalendar = holidayCalendarRepository.findById(holidayId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + holidayId));
		holidayCalendar.setHolidayName(holidayCalendarVO.getHolidayName());
		holidayCalendar.setHolidayDate(holidayCalendarVO.getHolidayDate());
		holidayCalendar.setHolidayDay(holidayCalendarVO.getHolidayDay());
		return HolidayCalendarConverter
				.convertHolidayCalendarToHolidayCalendarVO(holidayCalendarRepository.save(holidayCalendar));
	}

	@Override
	@Cacheable(value = "holidaycalendar", key = "#holidayId")
	public HolidayCalendarVO getHolidayCalendar(Long holidayId) {
		Optional<HolidayCalendar> holidayCalendarOptional = holidayCalendarRepository.findById(holidayId);
		if (holidayCalendarOptional.isPresent()) {
			return HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(holidayCalendarOptional.get());
		}
		throw new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + holidayId);
	}

	@Override
	@Transactional
	@CacheEvict(value = "holidaycalendar", key = "#holidayId")
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
		holidayCalendarRepository.findAll().stream().forEach(holidayClaendar -> {
			holidayCalendarVOs.add(HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(holidayClaendar));
		});
		return holidayCalendarVOs;
	}

	@Override
	public List<HolidayCalendarVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<HolidayCalendarVO> holidayCalendarVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<HolidayCalendar> pageResult = holidayCalendarRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				holidayCalendarVOs.add(HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(e));
			});
			return holidayCalendarVOs;
		}
		return Collections.emptyList();
	}
	
	@Override
	public List<HolidayCalendarVO> getHolidayCalendarByFinancialYear(String financialyear) {
		List<HolidayCalendarVO> holidayCalendarVOs = new ArrayList<>();
		holidayCalendarRepository.findByFinancialYear(financialyear).stream().forEach(holidayClaendar -> {
			holidayCalendarVOs.add(HolidayCalendarConverter.convertHolidayCalendarToHolidayCalendarVO(holidayClaendar));
		});
		return holidayCalendarVOs;
	}
}
