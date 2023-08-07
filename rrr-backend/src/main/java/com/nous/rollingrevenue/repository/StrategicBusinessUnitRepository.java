package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.StrategicBusinessUnit;

@Repository
public interface StrategicBusinessUnitRepository extends JpaRepository<StrategicBusinessUnit, Long> {

	@Query("SELECT s from StrategicBusinessUnit s where s.businessUnit.businessUnitId = ?1")
	List<StrategicBusinessUnit> findByBusinessUnitId(Long businessUnitId);

}
