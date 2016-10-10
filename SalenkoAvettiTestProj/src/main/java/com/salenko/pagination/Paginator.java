package com.salenko.pagination;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.salenko.model.TestTable;

@XmlRootElement
public class Paginator implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer currentPage;
	private Integer pageSize;
	private Long totalResults;

	private String sortFields;
	private String sortDirections;
	@XmlElement
	private List<TestTable> list;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public String getSortFields() {
		return sortFields;
	}

	public void setSortFields(String sortFields) {
		this.sortFields = sortFields;
	}

	public String getSortDirections() {
		return sortDirections;
	}

	public void setSortDirections(String sortDirections) {
		this.sortDirections = sortDirections;
	}

	public List<TestTable> getList() {
		return list;
	}

	public void setList(List<TestTable> list) {
		this.list = list;
	}
}
