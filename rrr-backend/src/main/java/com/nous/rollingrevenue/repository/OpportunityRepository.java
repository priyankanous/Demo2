package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.Opportunity;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

	@Query("SELECT o from Opportunity o where o.account.accountId = ?1")
	List<Opportunity> findByAccountId(Long accountId);

}
