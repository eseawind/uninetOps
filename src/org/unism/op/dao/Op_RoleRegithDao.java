package org.unism.op.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_RoleRegith;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
@Repository
public class Op_RoleRegithDao extends HibernateBaseDao<Op_RoleRegith, String> {

	public List<Op_RoleRegith> findByRole_idOrderByNode_id(String role_id){
		String hql = "from Op_RoleRegith rr where rr.role_id.role_id=? order by rr.node_id.node_sequence asc";
//		Search search = new Search();
//		Filter filter = Filter.equal("role_id.role_id",role_id);
//		search.addFilter(filter);
//		search.addSortAsc("node_id.node_sequence");
//		return this.search(search);
		
		return this.findByHql(hql, role_id);
	}
}
