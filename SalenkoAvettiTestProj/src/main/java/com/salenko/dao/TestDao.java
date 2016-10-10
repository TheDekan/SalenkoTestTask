package com.salenko.dao;

import java.util.List;

import com.salenko.model.TestTable;

public interface TestDao {

	void update(TestTable row);
	
	TestTable insert(TestTable row);

	List<TestTable> findAll();

	List<TestTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	void delete(Long id);

	TestTable findById(Long id);

	Long getCount();

}
