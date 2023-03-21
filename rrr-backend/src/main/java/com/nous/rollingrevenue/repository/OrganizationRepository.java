package com.nous.rollingrevenue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nous.rollingrevenue.model.Organization;


@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}
