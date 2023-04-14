package com.nous.rollingrevenue.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.nous.rollingrevenue.repository.AccountRepository;
import com.nous.rollingrevenue.service.AccountService;
import com.nous.rollingrevenue.vo.AccountVO;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public List<AccountVO> getAllAccounts() {
		List<AccountVO> accountVOs = new ArrayList<>();
		accountRepository.findAll().stream()
				.forEach(account -> accountVOs.add(AccountConverter.convertAccountToAccountVO(account)));
		return accountVOs;
	}
	
	@Override
	@Transactional
	public AccountVO saveAccount(AccountVO accountVO) {
		Account account = AccountConverter.convertAccountVOToAccount(accountVO);
		return AccountConverter.convertAccountToAccountVO(accountRepository.save(account));
	}

	@Override
	@Transactional
	public void deleteAccountById(Long accountId) {
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
	public AccountVO updateAccount(Long accountId, AccountVO accountVO) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + accountId));
		account.setAccountName(accountVO.getAccountName());
		account.setAccountOrClientCode(accountVO.getAccountOrClientCode());
		account.setLocation(accountVO.getLocation());
		return AccountConverter.convertAccountToAccountVO(accountRepository.save(account));
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
	public AccountVO activateOrDeactivateById(Long accountId) {
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new RecordNotFoundException(ErrorConstants.RECORD_NOT_EXIST + accountId));
		account.setActive(!account.isActive());
		return AccountConverter.convertAccountToAccountVO(accountRepository.save(account));
	}

}
