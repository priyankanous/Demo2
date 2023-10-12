package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RegionWiseViewPermission;

@Repository
public interface RegionWiseViewPermissionRepository extends JpaRepository<RegionWiseViewPermission, Long> {

}
