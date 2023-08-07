package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.StrategicBusinessUnitHead;

@Repository
public interface StrategicBusinessUnitHeadRepository extends JpaRepository<StrategicBusinessUnitHead, Long> {

	@Query("SELECT s from StrategicBusinessUnitHead s where s.strategicbusinessUnit.sbuId = ?1")
	List<StrategicBusinessUnitHead> findBySBUId(Long sbuId);

}
