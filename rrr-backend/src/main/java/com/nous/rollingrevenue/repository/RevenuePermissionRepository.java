package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RevenuePermission;

@Repository
public interface RevenuePermissionRepository extends JpaRepository<RevenuePermission, Long> {

}
