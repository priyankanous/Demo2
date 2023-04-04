package com.nous.rollingrevenue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nous.rollingrevenue.common.rest.RestMessage;
import com.nous.rollingrevenue.common.rest.WSResponse;
import com.nous.rollingrevenue.service.AccountService;
import com.nous.rollingrevenue.vo.AccountVO;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Operation(summary = "Get All Accounts")
	@GetMapping
	public WSResponse<List<AccountVO>> getAllAccounts() {
		List<AccountVO> accountVOs = accountService.getAllAccounts();
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, accountVOs);
	}

	@Operation(summary = "Save Account")
	@PostMapping
	public WSResponse<AccountVO> saveAccount(@RequestBody @Valid AccountVO accountVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, accountService.saveAccount(accountVO));
	}

	@Operation(summary = "Get Account by Id")
	@GetMapping(path = "{accountId}")
	public WSResponse<AccountVO> getAccountById(@PathVariable Long accountId) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS, accountService.getAccountById(accountId));
	}

	@Operation(summary = "Update Account by Id")
	@PutMapping(path = "{accountId}")
	public WSResponse<AccountVO> updateAccount(@PathVariable Long accountId, @RequestBody @Valid AccountVO accountVO) {
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS,
				accountService.updateAccount(accountId, accountVO));
	}

	@Operation(summary = "Delete Account by Id")
	@DeleteMapping(path = "{accountId}")
	public WSResponse<String> deleteAccount(@PathVariable Long accountId) {
		accountService.deleteAccountById(accountId);
		return WSResponse.buildWSResponse(HttpStatus.OK, RestMessage.SUCCESS);
	}
	
	@Operation(summary = "Get Account By Pagination")
	@GetMapping("/page")
	public WSResponse<List<AccountVO>> getAccountByPagination(
			@RequestParam(defaultValue = "1") int pagenumber, @RequestParam(defaultValue = "10") int pagesize,
			@RequestParam(defaultValue = "accountId", required = false) String sortBy) {
		return WSResponse.buildWSResponse(RestMessage.SUCCESS, accountService.getPagination(pagenumber, pagesize, sortBy));
	}

}
