package com.nous.rollingrevenue.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.WorkOrder;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

	WorkOrder findByWorkOrderNumber(Long workOrderNumber);
	
	List<WorkOrder> findByAccountName(String accountName);
	

}
