package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BusinessTypeViewPermission;

@Repository
public interface BusinessTypeViewPermissionRepository extends JpaRepository<BusinessTypeViewPermission, Long> {

}
