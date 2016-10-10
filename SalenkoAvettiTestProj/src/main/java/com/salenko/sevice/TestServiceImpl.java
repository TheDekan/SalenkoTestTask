package com.salenko.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salenko.dao.TestDao;
import com.salenko.model.TestTable;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao dao;

	@Override
	public void update(TestTable row) {
		dao.update(row);
	}
	
	@Override
	public TestTable insert(TestTable row) {
		return dao.insert(row);
	}

	@Override
	public List<TestTable> findAll() {
		return dao.findAll();
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
	}

	@Override
	public TestTable findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public Long getCount() {
		return dao.getCount();
	}

	@Override
	public List<TestTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return dao.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}
}
