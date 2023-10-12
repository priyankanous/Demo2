package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.CalendarPermission;

@Repository
public interface CalendarPermissionRepository extends JpaRepository<CalendarPermission, Long> {

}
