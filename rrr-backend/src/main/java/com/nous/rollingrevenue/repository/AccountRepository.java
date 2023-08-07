package com.nous.rollingrevenue.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountName(String accountName);

	Optional<Account> findById(Long accountId);

	@Query("SELECT a FROM Account a WHERE REPLACE(LOWER(a.accountName), ' ', '') LIKE TRIM(LOWER(concat(?1, '%')))")
	Optional<Account> findByAccountNameIgnoringCaseAndIgnoreWhiteSpaces(String accountName);

	@Query("SELECT a from Account a where a.regions.regionId = ?1")
	List<Account> findByRegionId(Long regionId);

}
