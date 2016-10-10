package com.salenko.sevice;

import java.util.List;

import com.salenko.model.TestTable;

public interface TestService {

	void update(TestTable row);

	TestTable insert(TestTable row);
	
	List<TestTable> findAll();

	void delete(Long id);

	TestTable findById(Long id);
	
	List<TestTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections);

	Long getCount();

	// void getRows(int startPosition, int maxResults, String sortFields, String
	// sortDirections);
}
