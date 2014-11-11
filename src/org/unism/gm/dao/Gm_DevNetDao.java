package org.unism.gm.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_DevNet;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Gm_DevNetDao extends HibernateBaseDao<Gm_DevNet, String>{
	
	public List<Gm_DevNet> findAllByNet_no(String net_no){
		String sql = "select * from gm_devnet as e where net_no like '"+net_no+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Gm_DevNet> list = query.list();
		if(list.size()>0){
			return list;
		}
		return null;
	}

	/**
	 * 指定网络信息，场景ID集合 查询与用户相关的场景中的设备关联的 在用的 下一级网络信息
	 * @return json
	 * 			[
	 * 				{net_id:'?',net_no:'?',net_addr:'?'},
	 * 				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String findChildList(Gm_DevNet gm_DevNet, List<String> scene_id_list){
		String sql = "select e.net_id,e.net_no,e.net_addr from gm_devnet as e ";
		sql += "where e.net_state=1 ";
		sql += "and e.net_depth="+(gm_DevNet.getNet_depth()+1)+" ";
		sql += "and e.net_pid='FF' ";
		sql += "and e.dev_id in(";
		sql += "select d.dev_id from gm_device as d ";
		sql += "where d.dev_state=1 ";
		sql += "and d.scene_id in(";
		sql += "select scene_id from op_scene as s ";
		sql += "where s.scene_state=1 ";
		sql += "and s.scene_id in('-1',";
		for(String scene_id : scene_id_list){
			sql += "'"+scene_id+"',";
		}		
		sql = sql.substring(0,sql.length()-1);
		sql += ")";
		sql += ")";
		sql += ")";
		try{
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Object[]> list = query.list();
			String json = "[";
			if(list.size()>0){
				for(Object[] row : list){
					json += "{net_id:'"+row[0]+"',net_no:'"+row[1]+"',net_addr:'"+row[2]+"'},";
				}				
				json = json.substring(0,(json.length()-1));
			}
			return json+"]";
		}catch(Exception ex){
			return "[]";
		}
	}
	
	/**
	 * 指定网络信息，场景ID集合 查询与用户相关的场景中的设备关联的 在用的 子网络信息
	 * @return json
	 * 			[
	 * 				{net_id:'?',net_no:'?',net_addr:'?'},
	 * 				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String findChildedList(Gm_DevNet gm_DevNet, List<String> scene_id_list){
		String sql = "select e.net_id,e.net_no,e.net_addr from gm_devnet as e ";
		sql += "where e.net_state=1 ";
		//sql += "and e.net_depth="+(gm_DevNet.getNet_depth()+1)+" ";
		sql += "and e.net_pid='"+gm_DevNet.getNet_id()+"' ";
		sql += "and e.dev_id in(";
		sql += "select d.dev_id from gm_device as d ";
		sql += "where d.dev_state=1 ";
		sql += "and d.scene_id in(";
		sql += "select scene_id from op_scene as s ";
		sql += "where s.scene_state=1 ";
		sql += "and s.scene_id in('-1',";
		for(String scene_id : scene_id_list){
			sql += "'"+scene_id+"',";
		}		
		sql = sql.substring(0,sql.length()-1);
		sql += ")";
		sql += ")";
		sql += ")";
		try{
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Object[]> list = query.list();
			String json = "[";
			if(list.size()>0){
				for(Object[] row : list){
					json += "{net_id:'"+row[0]+"',net_no:'"+row[1]+"',net_addr:'"+row[2]+"'},";
				}				
				json = json.substring(0,(json.length()-1));
			}
			return json+"]";
		}catch(Exception ex){
			return "[]";
		}
	}
	
	/**
	 * 查找父网络编号
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findNet_pno(String net_pid){
		String sql = "select net_no from gm_devnet where net_id='"+net_pid+"' and net_state=1";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> lst = query.list();
		if(lst!=null && lst.size()>0){
			return lst.get(0);
		}
		return null;
	}

	public Gm_DevNet findByNet_addr(String net_addr)
	{
		String hql = "FROM Gm_DevNet WHERE net_addr='"+net_addr+"'";
		Query query = getSession().createQuery(hql);
		List<Gm_DevNet> list = new ArrayList<Gm_DevNet>();
		list = query.list();
		for (Gm_DevNet gm_DevNet : list)
		{
			return gm_DevNet;
		}
		return null;
	}
}
