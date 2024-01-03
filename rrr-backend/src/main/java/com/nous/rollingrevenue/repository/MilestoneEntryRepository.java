package com.nous.rollingrevenue.repository;

import java.math.BigInteger;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.MilestoneEntry;

@Repository
public interface MilestoneEntryRepository extends JpaRepository<MilestoneEntry, Long> {

	@Modifying
	@Query("Update MilestoneEntry r set r.milestoneResourceCount = ?1 where r.milestoneEntryId = ?2")
	void updateMilestoneEntryDetails(Integer mileStoneResourceCount, Long milestoneEntryId);

	@Modifying
	@Query("Update MilestoneEntry r set r.milestoneResourceCount = ?1, r.milestoneNumber = ?2, "
			+ "r.milestoneRevenue = ?3 where r.milestoneEntryId = ?4")
	void updateMilestoneEntryDetailsTONull(Integer mileStoneResourceCount, String milestoneNumber,
			BigInteger milestoneRevenue, Long milestoneEntryId);

}
