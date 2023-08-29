package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.common.constant.ErrorConstants;
import com.nous.rollingrevenue.convertor.AccountConverter;
import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.Account;
import com.nous.rollingrevenue.model.AnnualTargetEntry;
import com.nous.rollingrevenue.model.Opportunity;
import com.nous.rollingrevenue.model.Region;
import com.nous.rollingrevenue.model.RevenueEntry;
import com.nous.rollingrevenue.model.WorkOrder;
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.repository.AnnualTargetEntryRepository;
import com.nous.rollingrevenue.repository.OpportunityRepository;
import com.nous.rollingrevenue.repository.RegionRepository;
import com.nous.rollingrevenue.repository.RevenueEntryRespository;
import com.nous.rollingrevenue.repository.WorkOrderRepository;
import com.nous.rollingrevenue.service.AccountService;
import com.nous.rollingrevenue.vo.AccountVO;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RegionRepository regionRepository;

	@Autowired
	private OpportunityRepository OpportunityRepository;

	@Autowired
	private WorkOrderRepository workOrderRepository;

	@Autowired
	private AnnualTargetEntryRepository annualTargetEntryRepository;

	@Autowired
	private RevenueEntryRespository revenueEntryRespository;

	@Override
	public List<AccountVO> getAllAccounts() {
		List<AccountVO> accountVOs = new ArrayList<>();
		accountRepository.findAll().stream()
				.forEach(account -> accountVOs.add(AccountConverter.convertAccountToAccountVO(account)));
		return accountVOs;
	}

	@Override
	@Transactional
	public void saveAccount(AccountVO accountVO) {
		Account account = AccountConverter.convertAccountVOToAccount(accountVO);
		Region region = regionRepository.findById(accountVO.getRegions().getRegionId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "Region not exist"));
		account.setRegions(region);
		accountRepository.save(account);
	}

	@Override
	@Transactional
	public void deleteAccountById(Long accountId) {
		List<WorkOrder> workOrderList = workOrderRepository.findByAccountId(accountId);
		if (!workOrderList.isEmpty()) {
			throw new RecordNotFoundException(
					"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
		}
		List<Opportunity> opportunityList = OpportunityRepository.findByAccountId(accountId);
		if (!opportunityList.isEmpty()) {
			throw new RecordNotFoundException(
					"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByBusinessUnitId(accountId);
		if (!annualTargetEntryList.isEmpty()) {
			throw new RecordNotFoundException(
					"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
		}
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByAccountId(accountId);
		if (!revenueEntryList.isEmpty()) {
			throw new RecordNotFoundException(
					"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
		}
		accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + accountId));
		accountRepository.deleteById(accountId);
	}

	@Override
	public AccountVO getAccountById(Long accountId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + accountId));
		return AccountConverter.convertAccountToAccountVO(account);
	}

	@Override
	@Transactional
	public void updateAccount(Long accountId, AccountVO accountVO) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + accountId));
		account.setAccountName(accountVO.getAccountName());
		Region region = regionRepository.findById(accountVO.getRegions().getRegionId())
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + "Region not exist"));
		account.setRegions(region);
		accountRepository.save(account);
	}

	@Override
	public List<AccountVO> getPagination(int pagenumber, int pagesize, String sortBy) {
		List<AccountVO> accountVOs = new ArrayList<>();
		Pageable paging = PageRequest.of(pagenumber, pagesize, Sort.by(Direction.DESC, sortBy));
		Page<Account> pageResult = accountRepository.findAll(paging);
		if (pageResult.hasContent()) {
			pageResult.getContent().stream().forEach(e -> {
				accountVOs.add(AccountConverter.convertAccountToAccountVO(e));
			});
			return accountVOs;
		}
		return Collections.emptyList();
	}

	@Override
	@Transactional
	public void activateOrDeactivateById(Long accountId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + accountId));
		Optional<Region> region = regionRepository.findById(account.getRegions().getRegionId());
		if (region.isPresent()) {
			Region region2 = region.get();
			if (!region2.isActive() && !account.isActive()) {
				throw new RecordNotFoundException("Region is not active and its already linked to Account");
			}
		}
		List<Opportunity> opportunityList = OpportunityRepository.findByAccountId(accountId);
		for (Opportunity opportunity : opportunityList) {
			if (account.isActive() && opportunity.isActive()) {
				throw new RecordNotFoundException(
						"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
			}
		}
		List<WorkOrder> workOrderList = workOrderRepository.findByAccountId(accountId);
		for (WorkOrder workOrder : workOrderList) {
			if (account.isActive() && workOrder.isActive()) {
				throw new RecordNotFoundException(
						"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
			}
		}
		List<AnnualTargetEntry> annualTargetEntryList = annualTargetEntryRepository.findByAccountId(accountId);
		for (AnnualTargetEntry annualTargetEntry : annualTargetEntryList) {
			if (account.isActive() && annualTargetEntry.isActive()) {
				throw new RecordNotFoundException(
						"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
			}
		}
		List<RevenueEntry> revenueEntryList = revenueEntryRespository.findByAccountId(accountId);
		for (RevenueEntry revenueEntry : revenueEntryList) {
			if (account.isActive() && revenueEntry.isActive()) {
				throw new RecordNotFoundException(
						"Account is already linked to Opportunity or WorkOrder or AnnualTargetEntry or RevenueEntry");
			}
		}
		account.setActive(!account.isActive());
		accountRepository.save(account);
	}

}
