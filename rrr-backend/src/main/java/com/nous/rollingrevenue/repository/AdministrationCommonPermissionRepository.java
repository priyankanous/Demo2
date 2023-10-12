package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.AdministrationCommonPermission;

@Repository
public interface AdministrationCommonPermissionRepository extends JpaRepository<AdministrationCommonPermission, Long> {

}
