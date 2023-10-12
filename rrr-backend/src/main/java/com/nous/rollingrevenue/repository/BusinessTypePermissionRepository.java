package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BusinessTypePermission;

@Repository
public interface BusinessTypePermissionRepository extends JpaRepository<BusinessTypePermission, Long> {

}
