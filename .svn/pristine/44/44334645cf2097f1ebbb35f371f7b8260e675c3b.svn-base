package org.unism.gm.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_DevCtrl;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
@Repository
public class Gm_DevCtrlDao extends HibernateBaseDao<Gm_DevCtrl, String> {
	
	/**
	 * 根据设备ID数组查找控制设备ID
	 * @return
	 */
	public List<String> findDect_idByDev_id_arr(String dev_id_arr_str){
		String sql = "select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+")";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> lst = query.list();
		return lst;
	}
	
	/**
	 * 指定设备编号 模糊查询一组设备 ID
	 * @param dev_no
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDev_id_arrByDev_no(String dev_no){
		String sql = "select dev_id from gm_device where dev_state=1 and dev_no like '%"+dev_no+"%'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	public List<String> dect_id_by_desc_no(String queryName,String desc_no) {
		String sql = "select dect_id from gm_devctrl where dect_state=1 and "+queryName+" like '%"+desc_no+"%'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}

	public List<String> findDect_idByScene_id(String scene_id)
	{
		String sql = "select dect_id from gm_devctrl where scene_id='"+scene_id+"'";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.list();
	}

	public List<String> findDectIdByDect_no(String queryValue,
			List<String> dectIdList)
	{
		String sql = "select dect_id from gm_devctrl where dect_no like '%"+queryValue+"%' and dect_id in (";
		if(dectIdList != null && dectIdList.size() > 0){
			for (String dect_id : dectIdList)
			{
				sql += "'"+dect_id+"',";
			}
			sql = sql.substring(0,sql.length()-1);
		}
		sql += ")";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.list();
	}

	public List<String> findDectIdByscene_idList(List<String> scene_idList,
			List<String> dectIdList)
	{
		String sql = "select dect_id from gm_devctrl where scene_id in ('-1',";
		if(scene_idList != null){
			for (String scene_id : scene_idList)
			{
				sql += "'"+scene_id+"',";
			}
			sql = sql.substring(0,sql.length()-1);
		}
		sql += ") and dect_id in ('-1',";
		if(dectIdList != null){
			for (String dect_id : dectIdList)
			{
				sql += "'"+dect_id+"',";
			}
			sql = sql.substring(0,sql.length()-1);
		}
		sql += ")";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.list();
	}


		
}
