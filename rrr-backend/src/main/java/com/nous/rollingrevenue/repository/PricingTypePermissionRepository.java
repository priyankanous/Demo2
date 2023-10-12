package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.PricingTypePermission;

@Repository
public interface PricingTypePermissionRepository extends JpaRepository<PricingTypePermission, Long> {

}
