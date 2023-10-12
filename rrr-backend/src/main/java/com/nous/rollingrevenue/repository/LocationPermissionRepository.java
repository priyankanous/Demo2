package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.LocationPermission;

@Repository
public interface LocationPermissionRepository extends JpaRepository<LocationPermission, Long> {

}
