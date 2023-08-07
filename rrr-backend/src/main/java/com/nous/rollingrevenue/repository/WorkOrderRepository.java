package com.nous.rollingrevenue.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.WorkOrder;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

	public Optional<WorkOrder> findByWorkOrderNumber(String workOrderNumber);

	@Query("SELECT o from WorkOrder o where o.account.accountId = ?1")
	List<WorkOrder> findByAccountId(Long accountId);

}
