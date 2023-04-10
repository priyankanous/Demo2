package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.HolidayCalendar;

@Repository
public interface HolidayCalendarRepository extends JpaRepository<HolidayCalendar, Long> {

	List<HolidayCalendar> findByFinancialYear(String financialYear);

}
