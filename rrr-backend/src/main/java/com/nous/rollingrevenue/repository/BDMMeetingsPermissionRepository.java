package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BDMMeetingsPermission;

@Repository
public interface BDMMeetingsPermissionRepository extends JpaRepository<BDMMeetingsPermission, Long> {

}
