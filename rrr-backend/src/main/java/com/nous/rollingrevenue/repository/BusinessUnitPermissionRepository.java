package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BusinessUnitPermission;

@Repository
public interface BusinessUnitPermissionRepository extends JpaRepository<BusinessUnitPermission, Long> {

}
