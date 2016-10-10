package com.salenko.rest;

import java.util.List;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salenko.model.TestTable;
import com.salenko.pagination.Paginator;
import com.salenko.sevice.TestService;

@Controller
@RequestMapping("/resources")
public class TestController {

	@Autowired
	private TestService service;

	private Long listCount() {
		return service.getCount();
	}

	@SuppressWarnings("unchecked")
	private List<TestTable> findRow(int startPosition, int maxResults,
			String sortFields, String sortDirections) {
		return service.sortedFind(startPosition, maxResults, sortFields, sortDirections);
	}

	private Paginator findRow(Paginator wrapper) {
		wrapper.setTotalResults(listCount());
		int start = (wrapper.getCurrentPage() - 1) * wrapper.getPageSize();
		wrapper.setList(findRow(start, wrapper.getPageSize(),
				wrapper.getSortFields(), wrapper.getSortDirections()));
		return wrapper;
	}

	@RequestMapping(value = "/tests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON )
	public @ResponseBody Paginator listMyTestProj(
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "sortFields", defaultValue = "id") String sortFields,
			@RequestParam(value = "sortDirections", defaultValue = "asc") String sortDirections) {
		Paginator paginator = new Paginator();
		paginator.setCurrentPage(page);
		paginator.setSortFields(sortFields);
		paginator.setSortDirections(sortDirections);
		paginator.setPageSize(10);
		return findRow(paginator);
	}

	@RequestMapping(value = "/tests/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody TestTable getById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}
	
	@RequestMapping(value = "/tests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	public @ResponseBody TestTable save(@RequestBody TestTable row) {
		if (row.getId() == null) {
			TestTable rowToinsert = new TestTable();
			rowToinsert.setParametertext(row.getParametertext());
			rowToinsert.setParameterint(row.getParameterint());
			row = service.insert(rowToinsert);
        } else {
        	TestTable rowToUpdate = service.findById(row.getId());
    		rowToUpdate.setParametertext(row.getParametertext());
    		rowToUpdate.setParameterint(row.getParameterint());
    		service.update(rowToUpdate);
        }
		return row;
	}

	@RequestMapping(value = "/tests/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void delete(@PathVariable("id") Long id) {
		service.delete(id);
	}

}
