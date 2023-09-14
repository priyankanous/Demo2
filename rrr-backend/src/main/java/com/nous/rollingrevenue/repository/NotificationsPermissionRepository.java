package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.NotificationsPermission;

@Repository
public interface NotificationsPermissionRepository extends JpaRepository<NotificationsPermission, Long> {

}
