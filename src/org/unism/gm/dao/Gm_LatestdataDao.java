package org.unism.gm.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.util.DecimalUtils;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Gm_LatestdataDao extends HibernateBaseDao<Gm_Latestdata, String>{

	/**
	 * 指定通道集合 查询当前最小值
	 * @param gm_Channel_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findMinByGm_Channel_list(List<Gm_Channel> gm_Channel_list){
		String value = "";
		try{
			String sql = "select min(hida_corrValue) from gm_latestdata where ch_id in('-1',";
			for(Gm_Channel gm_Channel : gm_Channel_list){
				sql += "'"+gm_Channel.getCh_id()+"',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += ")";
			
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Double> list = query.list();
			if(list.size()>0){
				if(list.get(0)!=null){
					value = list.get(0)+"";
				}
			}
		}catch(Exception ex){ex.printStackTrace();return "";}
		return value;
	}
	
	/**
	 * 指定通道集合 查询当前最大值
	 * @param gm_Channel_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findMaxByGm_Channel_list(List<Gm_Channel> gm_Channel_list){
		String value = "";
		try{
			String sql = "select max(hida_corrValue) from gm_latestdata where ch_id in('-1',";
			for(Gm_Channel gm_Channel : gm_Channel_list){
				sql += "'"+gm_Channel.getCh_id()+"',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += ")";
			
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Double> list = query.list();
			if(list.size()>0){
				if(list.get(0)!=null){
					value = list.get(0)+"";
				}
			}
		}catch(Exception ex){ex.printStackTrace();return "";}
		return value;
	}
	
	/**
	 * 指定通道集合 查询当前平均值 时间检查-24 +1
	 * @param gm_Channel_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findAvgByGm_Channel_list(List<Gm_Channel> gm_Channel_list){
		String value = "";
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currDate = new Date();
			String beginTime = df.format(currDate.getTime()-1000l*60l*60l*24l);
			String endTime = df.format(currDate.getTime()+1000l*60l*60l*1l);
			String sql = "select avg(hida_corrValue) from gm_latestdata where ch_id in('-1',";
			for(Gm_Channel gm_Channel : gm_Channel_list){
				sql += "'"+gm_Channel.getCh_id()+"',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += ") ";
			sql += "and (hida_record_time>='"+beginTime+"' and hida_record_time<='"+endTime+"')";
			
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Double> list = query.list();
			if(list.size()>0){
				if(list.get(0)!=null){
					value = DecimalUtils.subDecimal(list.get(0));
				}
			}
		}catch(Exception ex){ex.printStackTrace();return "";}
		return value;
	}
	
	/**
	 * 指定场景ID集合 通道应用类型 查询符合条件的通道(且对外提供服务的)的平均值 时间检查-24 +1
	 * @param scene_id_list
	 * @param chtppy_id
	 * @return
	 * @author Wang_Yuliang
	 * 0624 弃用 Wang_Yuliang
	 */
	public Double findAvgByScene_id_listAndChtype_noAndCh_offerSer(List<String> scene_id_list, String chtype_no){
		Double value = null;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currDate = new Date();
			String beginTime = df.format(currDate.getTime()-1000l*60l*60l*24l);
			String endTime = df.format(currDate.getTime()+1000l*60l*60l*1l);
			String sql = "select avg(l.hida_corrValue) from gm_latestdata as l where l.ch_id in(select c.ch_id from gm_channel as c where c.ch_state=1 and c.ch_offerSer=1 and c.chtype_id=(select t.chtype_id from op_channeltype as t where t.chtype_no='"+chtype_no+"' limit 0,1) and scene_id in('-1',";
			for(String scene_id : scene_id_list){
				sql += "'"+scene_id+"',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += ")) and (hida_record_time>='"+beginTime+"' and hida_record_time<='"+endTime+"')";

			SQLQuery query = getSession().createSQLQuery(sql);
			List<Double> list = query.list();
			if(list.size()>0){
				if(list.get(0)!=null){
					value = list.get(0);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return value;
	}
	
	/**
	 * 指定场景ID集合 通道应用类型 查询符合条件的通道(且对外提供服务的)的最小值 时间检查-24 +1
	 * @param scene_id_list
	 * @param chtppy_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public Double findMinByScene_id_listAndChtype_noAndCh_offerSer(List<String> scene_id_list, String chtype_no){
		Double value = null;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currDate = new Date();
			String beginTime = df.format(currDate.getTime()-1000l*60l*60l*24l);
			String endTime = df.format(currDate.getTime()+1000l*60l*60l*1l);
			String sql = "select min(l.hida_corrValue) from gm_latestdata as l where l.ch_id in(select c.ch_id from gm_channel as c where c.ch_state=1 and c.ch_offerSer=1 and c.chtype_id=(select t.chtype_id from op_channeltype as t where t.chtype_no='"+chtype_no+"' limit 0,1) and scene_id in('-1',";
			for(String scene_id : scene_id_list){
				sql += "'"+scene_id+"',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += ")) and (hida_record_time>='"+beginTime+"' and hida_record_time<='"+endTime+"')";

			SQLQuery query = getSession().createSQLQuery(sql);
			List<Double> list = query.list();
			if(list.size()>0){
				if(list.get(0)!=null){
					value = list.get(0);
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
		return value;
	}
	
	public List<Gm_Latestdata> findGm_LatestdataByCh_id(List<String> ch_idList,String endTime,String beginTime)
	{
		String hql = "FROM Gm_Latestdata WHERE ch_id in ('-1',";
			for (String ch_id : ch_idList)
			{
				hql += "'"+ch_id+"',";
			}
			hql = hql.substring(0,hql.length()-1);
		hql += ") and hida_record_time>='"+beginTime+"' and hida_record_time<='"+endTime+"')";
		
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}

	public List<Gm_Latestdata> findLatestdataByCh_id(List<Gm_Channel> gm_Channel_list)
	{
		String hql = "FROM Gm_Latestdata WHERE ch_id in ('-1',";
			for (Gm_Channel gm_Channel : gm_Channel_list)
			{
				hql += "'"+gm_Channel.getCh_id()+"',";
			}
		hql = hql.substring(0,hql.length()-1);
		hql += ") and hida_corrValue is not null order by hida_corrValue asc";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	public List<Gm_Latestdata> findAllByChtypeandScene(String scene_id)
	{
		return null;
	}

	public List<Gm_Latestdata> findByNet_id(List<String> net_id)
	{
		String sql = "SELECT ch_id FROM gm_channel where dev_collectId in (select dev_id from gm_devnet where net_id in ('-1',";
		if(net_id.size()>0){
			for (String netId : net_id)
			{
				sql += "'"+netId+"',";
			}
			sql = sql.substring(0,sql.length()-1);
			sql += "))";
		}
		SQLQuery sqlquery = getSession().createSQLQuery(sql);
		List<String> ch_idList = sqlquery.list();
		String hql = "from Gm_Latestdata where ch_id in ('-1',";
		if(ch_idList.size() > 0){
			for (String ch_id : ch_idList)
			{
				hql += "'"+ch_id+"',";
			}
			hql = hql.substring(0,hql.length()-1);
			hql += ") and hida_corrValue is not null and hida_record_time is not null and hida_reportTime is not null";
		}
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}

	public List<String[]> findAvgDateBy_Scene_Id(String scene_id, String chtype1) {
		String sql = "select avg(l.hida_corrValue),l.hida_record_time from gm_latestdata as l,gm_channel as cl,op_channeltype as c where cl.ch_id=l.ch_id and  cl.chtype_id=c.chtype_id and cl.scene_id='"+scene_id+"'  and c.chtype_no='"+chtype1+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String[]> list = query.list();
		return list;
	}

	public List<Object[]> findByScene(String[] sceneIdArr) {
		String hql = "SELECT scene.scene_id,scene.scene_name,scene.scene_no,chtype.chtype_no,chtype.chtype_displayName,chtype.ch_corrUnit,lates.hida_corrValue,lates.hida_record_time FROM Op_Scene as scene,Op_ChannelType as chtype,Gm_Latestdata as lates,Gm_Channel as channel WHERE scene.scene_id IN ('-1',";
		for (String string : sceneIdArr) {
			hql+="'"+string+"',";
		}
		hql = hql.substring(0,hql.length()-1);
		hql+=") AND scene.scene_id = channel.scene_id.scene_id AND channel.ch_offerSer = 1 AND channel.ch_state = 1 AND channel.ch_id = lates.ch_id AND channel.chtype_id.chtype_id = chtype.chtype_id ORDER BY scene.scene_no";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
