package org.unism.web.dao;

import org.springframework.stereotype.Repository;
import org.unism.web.domain.TestDomain;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class TestDomainDao extends HibernateBaseDao<TestDomain, String> {
	
}
