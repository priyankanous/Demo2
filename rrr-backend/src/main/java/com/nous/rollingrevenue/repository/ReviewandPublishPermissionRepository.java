package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.ReviewandPublishPermission;

@Repository
public interface ReviewandPublishPermissionRepository extends JpaRepository<ReviewandPublishPermission, Long> {

}
