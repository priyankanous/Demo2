package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.FortnightlyMeetingsPermission;

@Repository
public interface FortnightlyMeetingsPermissionRepository extends JpaRepository<FortnightlyMeetingsPermission, Long> {

}
