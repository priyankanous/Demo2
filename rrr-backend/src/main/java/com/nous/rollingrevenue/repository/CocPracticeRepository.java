package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.model.StrategicBusinessUnit;

@Repository
public interface CocPracticeRepository extends JpaRepository<CocPractice , Long> {

}
