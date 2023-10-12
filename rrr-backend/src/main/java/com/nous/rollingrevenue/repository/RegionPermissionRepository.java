package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RegionPermission;

@Repository
public interface RegionPermissionRepository extends JpaRepository<RegionPermission, Long> {

}
