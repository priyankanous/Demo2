package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.AnnualTargetEntryPermission;

@Repository
public interface AnnualTargetEntryPermissionRepository extends JpaRepository<AnnualTargetEntryPermission, Long> {

}
