package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RoleUserPermission;

@Repository
public interface RoleUserPermissionRepository extends JpaRepository<RoleUserPermission, Long> {

}
