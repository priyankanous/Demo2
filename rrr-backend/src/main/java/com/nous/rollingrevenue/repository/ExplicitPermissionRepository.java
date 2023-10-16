package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.ExplicitPermission;

@Repository
public interface ExplicitPermissionRepository extends JpaRepository<ExplicitPermission, Long> {

}
