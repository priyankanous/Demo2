package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.AdministrationPermission;

@Repository
public interface AdministrationPermissionRepository extends JpaRepository<AdministrationPermission, Long> {

}
