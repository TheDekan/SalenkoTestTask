package com.salenko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_table")
public class TestTable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "parameter_text")
	private String parametertext;

	@Column(name = "parameter_int")
	private Integer parameterint;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getParametertext() {
		return parametertext;
	}

	public void setParametertext(String parametertext) {
		this.parametertext = parametertext;
	}

	public Integer getParameterint() {
		return parameterint;
	}

	public void setParameterint(Integer parameterint) {
		this.parameterint = parameterint;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TestTable tb = (TestTable) o;

		return id.equals(tb.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
