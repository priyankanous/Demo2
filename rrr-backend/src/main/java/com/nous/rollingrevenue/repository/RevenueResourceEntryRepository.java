package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RevenueResourceEntry;

@Repository
public interface RevenueResourceEntryRepository extends JpaRepository<RevenueResourceEntry, Long>{

}
