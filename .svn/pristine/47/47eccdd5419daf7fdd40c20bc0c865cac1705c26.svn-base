package org.unism.gm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_DevFault;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Gm_DevFaultDao extends HibernateBaseDao<Gm_DevFault, String>
{

	public Gm_DevFault findBydefId(String def_id)
	{
		String hql = "FROM Gm_DevFault WHERE def_id='"+def_id+"'";
		Query query = getSession().createQuery(hql);
		List<Gm_DevFault> list = query.list();
		for (Gm_DevFault gm_DevFault : list)
		{
			return gm_DevFault;
		}
		return null;
	}

	public List<Object[] > findDevFaultTypeListByUserIdAndTime(
			String userId, String beginTime, String endTime, String scene_id_selectAll) {
		String sql="";		
	    if(scene_id_selectAll==null || "".equals(scene_id_selectAll)){
	    	sql="SELECT op_scene.scene_id,op_scene.scene_name,gm_devnet.net_addr,gm_devfault.def_object,count(gm_devfault.def_object) as co"+
			" FROM op_userinfo_scene INNER JOIN op_scene ON op_userinfo_scene.scene_id = op_scene.scene_id"+
			" INNER JOIN gm_device ON op_scene.scene_id = gm_device.scene_id"+
			" INNER JOIN gm_devnet ON gm_device.dev_id = gm_devnet.dev_id"+
			" INNER JOIN gm_devfault ON gm_devnet.dev_id = gm_devfault.dev_id"+
			" where gm_devfault.def_occurTime>='"+beginTime+"'  and gm_devfault.def_occurTime<='"+endTime+"'"+
			" and op_userinfo_scene.user_id='"+userId+"'"+
			" group by op_scene.scene_id,gm_devfault.def_object";
	    }else{
	    	sql="SELECT op_scene.scene_id,op_scene.scene_name,gm_devnet.net_addr,gm_devfault.def_object,count(gm_devfault.def_object) as co"+
			" FROM op_userinfo_scene INNER JOIN op_scene ON op_userinfo_scene.scene_id = op_scene.scene_id"+
			" INNER JOIN gm_device ON op_scene.scene_id = gm_device.scene_id"+
			" INNER JOIN gm_devnet ON gm_device.dev_id = gm_devnet.dev_id"+
			" INNER JOIN gm_devfault ON gm_devnet.dev_id = gm_devfault.dev_id"+
			" where gm_devfault.def_occurTime>='"+beginTime+"'  and gm_devfault.def_occurTime<='"+endTime+"'"+
			" and op_userinfo_scene.user_id='"+userId+"'"+
			" and op_userinfo_scene.scene_id in ('"+scene_id_selectAll+"')"+
			" group by op_scene.scene_id,gm_devfault.def_object";
	    }
		SQLQuery query=this.getSession().createSQLQuery(sql);
		return query.list();
	}
//	public List<Object[] > findDevFaultTypeListByUserIdAndTime(
//			String userId, String beginTime, String endTime, String scene_id_selectAll) {
//		String sql="";		
//	    if(scene_id_selectAll==null || "".equals(scene_id_selectAll)){
//	    	sql="SELECT op_scene.scene_id,op_scene.scene_name,gm_devnet.net_addr,gm_devfault.def_type,count(gm_devfault.def_type) as co"+
//			" FROM op_userinfo_scene INNER JOIN op_scene ON op_userinfo_scene.scene_id = op_scene.scene_id"+
//			" INNER JOIN gm_device ON op_scene.scene_id = gm_device.scene_id"+
//			" INNER JOIN gm_devnet ON gm_device.dev_id = gm_devnet.dev_id"+
//			" INNER JOIN gm_devfault ON gm_devnet.dev_id = gm_devfault.dev_id"+
//			" where gm_devfault.def_occurTime>='"+beginTime+"'  and gm_devfault.def_occurTime<='"+endTime+"'"+
//			" and op_userinfo_scene.user_id='"+userId+"'"+
//			" group by op_scene.scene_id,gm_devfault.def_type";
//	    }else{
//	    	sql="SELECT op_scene.scene_id,op_scene.scene_name,gm_devnet.net_addr,gm_devfault.def_type,count(gm_devfault.def_type) as co"+
//			" FROM op_userinfo_scene INNER JOIN op_scene ON op_userinfo_scene.scene_id = op_scene.scene_id"+
//			" INNER JOIN gm_device ON op_scene.scene_id = gm_device.scene_id"+
//			" INNER JOIN gm_devnet ON gm_device.dev_id = gm_devnet.dev_id"+
//			" INNER JOIN gm_devfault ON gm_devnet.dev_id = gm_devfault.dev_id"+
//			" where gm_devfault.def_occurTime>='"+beginTime+"'  and gm_devfault.def_occurTime<='"+endTime+"'"+
//			" and op_userinfo_scene.user_id='"+userId+"'"+
//			" and op_userinfo_scene.scene_id in ('"+scene_id_selectAll+"')"+
//			" group by op_scene.scene_id,gm_devfault.def_type";
//	    }
//		SQLQuery query=this.getSession().createSQLQuery(sql);
//		return query.list();
//	}
}
