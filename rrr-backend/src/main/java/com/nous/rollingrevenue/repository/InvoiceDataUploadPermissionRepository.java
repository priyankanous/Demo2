package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.InvoiceDataUploadPermission;

@Repository
public interface InvoiceDataUploadPermissionRepository extends JpaRepository<InvoiceDataUploadPermission, Long> {

}
