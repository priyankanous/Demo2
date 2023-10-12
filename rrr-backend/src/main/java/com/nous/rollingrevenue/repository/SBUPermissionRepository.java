package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.SBUPermission;

@Repository
public interface SBUPermissionRepository extends JpaRepository<SBUPermission, Long> {

}
