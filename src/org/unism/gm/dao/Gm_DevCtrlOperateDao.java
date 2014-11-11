package org.unism.gm.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_DevCtrlOperate;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Gm_DevCtrlOperateDao extends HibernateBaseDao<Gm_DevCtrlOperate, String>{

	public Gm_DevCtrlOperate findBydect_id(String dect_id)
	{
		String hql = "from Gm_DevCtrlOperate where dect_id = '"+dect_id+"'";
		return null;
	}



}
