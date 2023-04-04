package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.FortnightlyMeeting;

@Repository
public interface FortnightlyMeetingRepository extends JpaRepository<FortnightlyMeeting, Long>{

	List<FortnightlyMeeting> findByFinancialYear(String financialYear);

	void deleteAllByFinancialYear(String financialYear);

}
