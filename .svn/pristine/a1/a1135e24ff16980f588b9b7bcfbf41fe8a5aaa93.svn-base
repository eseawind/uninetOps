package org.unism.op.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_Alarmargument;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Op_AlarmargumentDao extends HibernateBaseDao<Op_Alarmargument, String>{

	public void deleteBySceneId(String sceneId) {
		String hql = "DELETE FROM Op_Alarmargument WHERE scene_id=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, sceneId);
		query.executeUpdate();
	}
}
