package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;

@Repository
public interface StrategicBusinessUnitHeadRepository extends JpaRepository<StrategicBusinessUnitHead, Long> {

}
