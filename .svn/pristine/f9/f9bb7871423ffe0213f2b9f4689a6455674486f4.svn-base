package org.unism.op.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_DevCtrlType;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
@Repository
public class Op_DevCtrlTypeDao extends HibernateBaseDao<Op_DevCtrlType, String> {

	public Op_DevCtrlType findBydecttype_no(String decttype_no)
	{
		Search search = new Search();
		Filter filter = Filter.equal("decttype_no", decttype_no);
		search.addFilter(filter);
		List<Op_DevCtrlType> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
