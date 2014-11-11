package org.unism.pro.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.pro.domain.Pro_forecast;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Pro_forecastDao  extends HibernateBaseDao<Pro_forecast, String> {
	
	public void test() {
		String sql = "select x.a1, x.a2 from x";     //1  5 /   2   5
		SQLQuery query = this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		for(Object[] objs : list) {
			
		}
		
		
		String hql = "select x from Pro_forecast inner join channel c where x.ch_id = c.";
		Query query2 = this.getSession().createQuery(hql);
		List<?> list2 = query2.list();
		
		
	}

	/**
	 * 根据场景类型细类查询出符合条件的场景ID
	 * @param scene_gtype
	 * @return 场景ID的List
	 */
	public List<Object[]> findScene_idBy_Scene_gtype(Integer scene_gtype) {
		String sql="select op_scene.scene_id,op_scene.scene_name from op_scene where op_scene.scene_gtype="+scene_gtype;
		SQLQuery query=this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	/**
	 * 根据场景ID和通道应用类型查询出符合条件的通道ID
	 * @param scene_gtype
	 * @return 通道ID的List
	 */
	public List<Object[]> findCh_idBy_Scene_idAnd_Chtype_id(String scene_id,String chtype_no) {
		String sql="select gm_channel.ch_id,gm_channel.ch_name  from op_scene,gm_channel,op_channeltype where op_scene.scene_id=gm_channel.scene_id and gm_channel.chtype_id=op_channeltype.chtype_id and gm_channel.scene_id='"+scene_id+"'  and op_channeltype.chtype_no='"+chtype_no+"'";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	/**
	 * 根据通道ID、24小时之内的时间查询出符合条件的平均数据
	 * @param scene_gtype
	 * @return 通道ID的List
	 */
	public List<Object[]> findAvgDataBy_Ch_idAnd_Time(String ch_idStr1,String beginTime, String endTime,int M) {
		String sql="select ch_id,avg(hida_corrValue),min(hida_record_time)  from gm_historydata where ch_id  in ("+ch_idStr1+") and hida_record_time<='"+endTime+"' group by SUBSTRING(hida_record_time,1,13)  order by hida_record_time desc  limit "+M+"";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	/**
	 * 根据通道ID、24小时之内的时间查询出符合条件的数据
	 * @param scene_gtype
	 * @return 通道ID的List
	 */
	public List<Object[]> findDataBy_Ch_idAnd_Time(String ch_idStr1,
			String beginTime, String endTime,int M) {
		String sql="select ch_id,hida_corrValue,min(hida_record_time)  from gm_historydata where ch_id="+ch_idStr1+" and hida_record_time<='"+endTime+"' group by SUBSTRING(hida_record_time,1,13)  order by hida_record_time desc  limit "+M+"";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	/**
	 * 查询出离当前时间最近的整点数据
	 * @param time
	 * @param count
	 * @return
	 */
	public List<Object[]> findHourDataByHistoryData(String time, int count) {
		String sql="select hida_corrValue,min(hida_record_time)  from gm_historydata where  hida_record_time<='"+time+"' group by SUBSTRING(hida_record_time,1,13)  order by hida_record_time desc  limit "+count+"";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		List<Object[]> list = query.list();
		return list;
	}

	
	
}
