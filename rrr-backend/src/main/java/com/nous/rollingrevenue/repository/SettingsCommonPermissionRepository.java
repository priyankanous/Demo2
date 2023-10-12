package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.SettingsCommonPermission;

@Repository
public interface SettingsCommonPermissionRepository extends JpaRepository<SettingsCommonPermission, Long> {

}
