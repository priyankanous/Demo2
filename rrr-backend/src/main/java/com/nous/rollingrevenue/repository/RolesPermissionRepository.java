package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RolesPermission;

@Repository
public interface RolesPermissionRepository extends JpaRepository<RolesPermission, Long> {

}