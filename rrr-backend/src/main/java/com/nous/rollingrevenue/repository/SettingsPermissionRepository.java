package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.SettingsPermission;

@Repository
public interface SettingsPermissionRepository extends JpaRepository<SettingsPermission, Long> {

}
