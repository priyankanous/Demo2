package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RollingRevenueCommonEntry;

@Repository
public interface RollingRevenueCommonRepository extends JpaRepository<RollingRevenueCommonEntry, Long> {

}
