package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.BusinessDevelopmentManager;

@Repository
public interface BusinessDevelopmentManagerRepository extends JpaRepository<BusinessDevelopmentManager, Long> {

}
