package com.nous.rollingrevenue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nous.rollingrevenue.repository.BillingTypeRepository;
import com.nous.rollingrevenue.service.BillingTypeService;

@Service
public class BillingTypeServiceImpl implements BillingTypeService {

	@Autowired
	BillingTypeRepository billingTypeRepository;

}
