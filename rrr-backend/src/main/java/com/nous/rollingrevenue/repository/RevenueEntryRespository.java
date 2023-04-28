package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.RevenueEntry;

@Repository
public interface RevenueEntryRespository extends JpaRepository<RevenueEntry, Long>{

}
