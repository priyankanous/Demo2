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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.convertor.HolidayCalendarVOToHolidayCalendar;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.HolidayCalendar;
import com.nous.rollingrevenue.repository.HolidayCalendarRepository;
import com.nous.rollingrevenue.service.HolidayCalendarService;
import com.nous.rollingrevenue.vo.HolidayCalendarVO;


@Service
public class HolidayCalendarServiceImpl implements HolidayCalendarService {

	@Autowired
	HolidayCalendarRepository holidayCalendarRepository;

	@Override
	public HolidayCalendar  addCalendar(HolidayCalendar holidayCalendar) {
		return holidayCalendarRepository.save(holidayCalendar);
	}

	@Override
	@CachePut(value = "holidaycalendar", key = "#holidayId")
	public HolidayCalendar updateHolidayCalendar(Long holidayId, HolidayCalendarVO holidayCalendarVO) {
		HolidayCalendar holidayCalendar = holidayCalendarRepository.findById(holidayId)
				.orElseThrow(() -> new RecordNotFoundException("Calendar not found for id:" + holidayId));
		holidayCalendar.setHolidayName(holidayCalendarVO.getHolidayName());
		holidayCalendar.setHolidayDate(holidayCalendarVO.getHolidayDate());
		holidayCalendar.setHolidayDay(holidayCalendarVO.getHolidayDay());
		return holidayCalendarRepository.save(holidayCalendar);
	}

	@Override
	@Cacheable(value = "holidaycalendar", key = "#holidayId")
	public HolidayCalendar getHolidayCalendar(Long holidayId) {
		Optional<HolidayCalendar> holidayCalendarOptional = holidayCalendarRepository.findById(holidayId);
		if (holidayCalendarOptional.isPresent()) {
			return holidayCalendarOptional.get();
		}
		throw new RecordNotFoundException("holidayCalendar not found for id:" + holidayId);
	}

	@Override
	@Transactional
	@CacheEvict(value = "holidaycalendar", key = "#holidayId") 
	public void deleteHolidayCalendar(Long holidayId) {
		Optional<HolidayCalendar> holidayCalendarOptional = holidayCalendarRepository.findById(holidayId);
		if (holidayCalendarOptional.isPresent()) {
			holidayCalendarRepository.deleteById(holidayId);
		} else {
			throw new RecordNotFoundException("HolidayCalendar not found for id:" + holidayId);
		}
	}

	@Override
	@Transactional
	public List<HolidayCalendar> getCalendars() {
		return holidayCalendarRepository.findAll();
	}
	
	@Override
	public List<HolidayCalendarVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<HolidayCalendarVO> holidayCalendarVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(sortBy));
		Page<HolidayCalendar> pageResult = holidayCalendarRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				holidayCalendarVOs.add(HolidayCalendarVOToHolidayCalendar.convertHolidayCalendarToHolidayCalendarVO(e));
			});
			return holidayCalendarVOs;
		}
		return Collections.emptyList();
	}
}
