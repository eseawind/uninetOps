package org.unism.op.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_AlarmConfig;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Op_AlarmConfigDao extends HibernateBaseDao<Op_AlarmConfig, String>{

	public void deleteBySceneId(String sceneId) {
		String hql = "DELETE FROM Op_AlarmConfig WHERE scene_id=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, sceneId);
		query.executeUpdate();
	}

}
