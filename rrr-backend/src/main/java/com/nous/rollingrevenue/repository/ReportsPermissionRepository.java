package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.ReportsPermission;

@Repository
public interface ReportsPermissionRepository extends JpaRepository<ReportsPermission, Long> {

}
