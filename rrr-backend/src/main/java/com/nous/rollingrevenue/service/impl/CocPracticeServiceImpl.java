package com.nous.rollingrevenue.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nous.rollingrevenue.exception.RecordNotFoundException;
import com.nous.rollingrevenue.model.CocPractice;
import com.nous.rollingrevenue.repository.CocPracticeRepository;
import com.nous.rollingrevenue.service.CocPracticeService;
import com.nous.rollingrevenue.vo.CocPracticeVO;

@Service
@Transactional(readOnly = true)
public class CocPracticeServiceImpl implements CocPracticeService {

	@Autowired
	CocPracticeRepository cocpracticeRepository;

	@Override
	@Transactional
	public CocPractice addCocPractice(CocPractice cocpractice) {
		return cocpracticeRepository.save(cocpractice);
	}

	@Override
	@Cacheable(value = "cocpractice", key = "#id")
	public CocPractice getCocPractice(Long id) {
		Optional<CocPractice> cocOptional = cocpracticeRepository.findById(id);

		if (cocOptional.isPresent()) {
			return cocOptional.get();
		}
		throw new RecordNotFoundException("CocPractice not found for id:" + id);

	}

	@Override
	@Transactional
	@CacheEvict(value = "cocpractice", key = "#id")
	public void deleteCocPractice(Long id) {
		Optional<CocPractice> cocOptional = cocpracticeRepository.findById(id);

		if (cocOptional.isPresent()) {
			cocpracticeRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("cocpractice not found for id:" + id);
		}
	}

	@Override
	public List<CocPractice> getAllCocPractice() {
		return cocpracticeRepository.findAll();
	}

	@Override
	@Transactional
	@CachePut(value = "cocpractice", key = "#id")
	public CocPractice updateCocPractice(Long id, CocPracticeVO cocpracticeVO) {
		CocPractice cocpractice = cocpracticeRepository.findById(id)
				.orElseThrow(() -> new RecordNotFoundException("CocPractice not found for id:" + id));
		cocpractice.setCocPracticeDisplayName(cocpracticeVO.getCocPracticeDisplayName());
		cocpractice.setCocPracticeName(cocpracticeVO.getCocPracticeName());
		cocpractice.setBuDisplayName(cocpracticeVO.getBuDisplayName());
		return cocpracticeRepository.save(cocpractice);
	}
}
