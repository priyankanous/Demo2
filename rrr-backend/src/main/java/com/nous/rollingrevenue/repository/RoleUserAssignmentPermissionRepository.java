package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RoleUserAssignmentPermission;

@Repository
public interface RoleUserAssignmentPermissionRepository extends JpaRepository<RoleUserAssignmentPermission, Long>{

}
