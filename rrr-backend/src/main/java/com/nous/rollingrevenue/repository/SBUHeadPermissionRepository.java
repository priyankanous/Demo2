package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.SBUHeadPermission;

@Repository
public interface SBUHeadPermissionRepository extends JpaRepository<SBUHeadPermission, Long> {

}
