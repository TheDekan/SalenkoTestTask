package com.salenko.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.salenko.model.TestTable;

@Repository("TestTableDao")
public class TestDaoImpl extends AbstractDao implements TestDao {

	/*@Override
	public void upsert(TestTable row) {
		getSession().saveOrUpdate(row);
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<TestTable> findAll() {
		Criteria criteria = getSession().createCriteria(TestTable.class);
		return (List<TestTable>) criteria.list();
	}

	@Override
	public void delete(Long id) {
		Query query = getSession().createQuery("delete from TestTable where id = :id");
		query.setLong("id", id);
		query.executeUpdate();
	}

	@Override
	public TestTable findById(Long id) {
		return (TestTable) findByField(TestTable.class, "id", id);
	}

	@Override
	public Long getCount() {
		Query query = getSession().createQuery("select count(*) from TestTable");
		return (Long) query.uniqueResult();
	}

	@Override
	public List<TestTable> sortedFind(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		Query query = 
				getSession().createQuery("SELECT p FROM TestTable p ORDER BY p." + sortFields + " "+ sortDirections);
		query.setFirstResult(startPosition);
		query.setMaxResults(maxResults);
		return query.list();
	}

	@Override
	public void update(TestTable row) {
		super.update(row);
	}
	
	

	@Override
	public TestTable insert(TestTable row) {
		return (TestTable) super.insert(row);
	}

}
