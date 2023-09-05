package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.DashboardPermission;

@Repository
public interface DashboardPermissionRepository extends JpaRepository<DashboardPermission, Long> {

}
