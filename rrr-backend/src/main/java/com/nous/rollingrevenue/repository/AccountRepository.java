package com.nous.rollingrevenue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByAccountName(String accountName);
<<<<<<< HEAD

	Optional<Account> findById(Long accountId);
=======
	
	@Query("SELECT a FROM Account a WHERE REPLACE(LOWER(a.accountName), ' ', '') LIKE TRIM(LOWER(concat(?1, '%')))")
	Optional<Account> findByAccountNameIgnoringCaseAndIgnoreWhiteSpaces(String accountName);
>>>>>>> aed21db5726bce4089c6440968f3d22a90b2b306
}
