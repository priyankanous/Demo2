package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.GlobalLeaveLossFactorPermission;

@Repository
public interface GlobalLeaveLossFactorPermissionRepository
		extends JpaRepository<GlobalLeaveLossFactorPermission, Long> {

}
