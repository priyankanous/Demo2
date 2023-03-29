package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nous.rollingrevenue.model.HolidayCalendar;

public interface HolidayCalendarRepository  extends JpaRepository<HolidayCalendar, Long>{

}
