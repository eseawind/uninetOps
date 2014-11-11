package org.unism.web.action;

import java.util.List;

import org.apache.struts2.util.StrutsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.web.dao.TestDomainDao;
import org.unism.web.domain.TestDomain;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.common.collect.Lists;


public class TestAction {
	
	@Autowired TestDomainDao testDomainDao;
		
	private String name;
	private TestDomain testDomain;
	private List<TestDomain> list = Lists.newArrayList();
	
	public String test1() {		
		Struts2Utils.getRequest();
		list = testDomainDao.findAll();
		return "success";
	}
	
	
	public String test2() {		
		return "success";
	}


	public TestDomain getTestDomain() {
		return testDomain;
	}


	public void setTestDomain(TestDomain testDomain) {
		this.testDomain = testDomain;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<TestDomain> getList() {
		return list;
	}


	public void setList(List<TestDomain> list) {
		this.list = list;
	}
	
}
