package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.HolidayCalendarPermission;

@Repository
public interface HolidayCalendarPermissionRepository extends JpaRepository<HolidayCalendarPermission, Long> {

}
