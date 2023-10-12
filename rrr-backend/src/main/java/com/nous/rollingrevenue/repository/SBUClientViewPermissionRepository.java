package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.SBUClientViewPermission;

@Repository
public interface SBUClientViewPermissionRepository extends JpaRepository<SBUClientViewPermission, Long> {

}
