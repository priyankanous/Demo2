package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BDMPermission;

@Repository
public interface BDMPermissionRepository extends JpaRepository<BDMPermission, Long> {

}
