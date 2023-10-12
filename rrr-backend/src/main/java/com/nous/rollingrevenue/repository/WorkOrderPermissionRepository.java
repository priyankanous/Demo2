package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.WorkOrderPermission;

@Repository
public interface WorkOrderPermissionRepository extends JpaRepository<WorkOrderPermission, Long> {

}
