package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nous.rollingrevenue.model.ReportsCommonPermission;

@Repository
public interface ReportsCommonPermissionRepository extends JpaRepository<ReportsCommonPermission, Long> {

}
