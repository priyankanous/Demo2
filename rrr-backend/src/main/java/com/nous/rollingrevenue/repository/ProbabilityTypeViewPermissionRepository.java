package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.ProbabilityTypeViewPermission;

@Repository
public interface ProbabilityTypeViewPermissionRepository extends JpaRepository<ProbabilityTypeViewPermission, Long> {

}
