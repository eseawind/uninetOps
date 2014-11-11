package org.unism.op.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.op.domain.Op_DevCtrlSts;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Search;

@SuppressWarnings("unchecked")
@Repository
public class Op_DevCtrlStsDao extends HibernateBaseDao<Op_DevCtrlSts, String> {

	public List<Op_DevCtrlSts> findByDevCtrl(List<Gm_DevCtrl> gm_DevCtrl_list) {
		List<String> list = new ArrayList<String>();
		list.add("-1");
		for (Gm_DevCtrl gm_DevCtrl : gm_DevCtrl_list) {
			list.add(gm_DevCtrl.getDect_id());
		}
		String hql = "from Op_DevCtrlSts where dect_id.dect_id in (:devCtrl)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("devCtrl", list);
		return query.list();
	}

}
