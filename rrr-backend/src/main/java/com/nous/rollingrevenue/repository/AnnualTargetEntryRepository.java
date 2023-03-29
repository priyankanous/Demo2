package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.AnnualTargetEntry;

@Repository
public interface AnnualTargetEntryRepository extends JpaRepository<AnnualTargetEntry, Long>{

}
