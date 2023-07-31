package com.nous.rollingrevenue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.service.BillingTypeService;

@RestController
@RequestMapping("/api/v1/billing-type")
@CrossOrigin(origins = "*")
public class BillingTypeController {

	@Autowired
	BillingTypeService billingTypeService;

}
