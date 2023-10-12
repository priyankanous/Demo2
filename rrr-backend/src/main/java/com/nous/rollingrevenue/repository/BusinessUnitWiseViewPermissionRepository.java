package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BusinessUnitWiseViewPermission;

@Repository
public interface BusinessUnitWiseViewPermissionRepository extends JpaRepository<BusinessUnitWiseViewPermission, Long> {

}
