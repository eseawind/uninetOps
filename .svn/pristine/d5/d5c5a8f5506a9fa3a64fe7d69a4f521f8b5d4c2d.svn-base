package org.unism.gm.dao;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.engine.query.HQLQueryPlan;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_Historydata;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Gm_HistorydataDao extends HibernateBaseDao<Gm_Historydata, String>{
	
	/**
	 * 曲线分析 指定采集通道id 起止时间 这里处理的比较简单了，但可以满足页面的需求，即起止时间差>1000*60*60*24*7 显示日均线，否则显示日均线
	 * @return json for Highcharts data 
	 * 			[[毫秒数,value],[毫秒数,value],[毫秒数,value],[毫秒数,value],[毫秒数,value][毫秒数,value],[毫秒数,value]]
	 * @author Wang_Yuliang
	 * 2011-05-19 11:15 弃用
	 */
	public String json_findHistoryByCh_idAndTime(String ch_id, String beginTime, String endTime){
		String data = "[]";
		try{
			String sub = "10";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!((df.parse(endTime).getTime() - df.parse(beginTime).getTime()) > (1000l*60l*60l*24*7l))){
				sub = "13";				
			}
			String sql = "";
			if((df.parse(endTime).getTime() - df.parse(beginTime).getTime()) > 604799000L){
				sql = "select hida_record_time,avg(hida_corrValue) from gm_historydata where ch_id='"+ch_id+"' and (hida_record_time>='"+beginTime+"' and hida_record_time<='"+endTime+"') group by SUBSTRING(hida_record_time,1,"+sub+")";
			}else {
				sql = "select hida_record_time,hida_corrValue from gm_historydata where ch_id='"+ch_id+"' and (hida_record_time>='"+beginTime+"' and hida_record_time<='"+endTime+"') order by hida_record_time";
			}
			
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Object[]> list = query.list();
			data = "[";
			if(list.size()>0){
				for(Object[] row : list){
					data += "["+df.parse(row[0]+"").getTime()+","+row[1]+"],";
				}
				data = data.substring(0,data.length()-1);
			}
			data = data+"]";
		}catch(Exception ex){ex.printStackTrace();return "[]";}
		return data;
	}
	
	/**
	 * 数据查询
	 * @param ch_id
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public Double findHistoryByCh_idAndTime(String ch_id, String beginTime, String endTime){		
		try{
			String sub = "10";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!((df.parse(endTime).getTime() - df.parse(beginTime).getTime()) > (1000l*60l*60l*24*7l))){
				sub = "13";				
			}
			String sql = "select avg(hida_corrValue) from gm_historydata where ch_id='"+ch_id+"' and (hida_record_time>='"+beginTime+"' and hida_record_time<='"+endTime+"') group by SUBSTRING(hida_record_time,1,"+sub+")";
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Double> list = query.list();
			if(list.size()> 0 &&list!=null){
				return list.get(0);
			}			
		}catch(Exception ex){ex.printStackTrace();}	
		return null;
	}
	/**
	 * 场景监控曲线图 测试
	 * @return
	 */
	public String tt(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = "[]";
		try{
			String sql = "select hida_record_time,hida_corrValue from gm_historydata order by hida_record_time desc limit 0,1";
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Object[]> list = query.list();
			data = "[";
			if(list.size()>0){
					data += ""+df.parse(list.get(0)[0]+"").getTime()+","+list.get(0)[1]+"";
			}
			data = data+"]";
		}catch(Exception ex){ex.printStackTrace();return "[]";}
		return data;
	}
	
	/**
	 * 指定通道id 查找最近144条数据
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findLimit144ByCh_id(String ch_id){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = "[]";
		try{
			String sql = "select hida_record_time,hida_corrValue from gm_historydata where ch_id='"+ch_id+"' order by hida_record_time desc limit 0,144";
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Object[]> list = query.list();
			data = "[";
			if(list.size()>0){
				for(Object[] row : list){
					data += "["+df.parse(row[0]+"").getTime()+","+row[1]+"],";
				}
				data = data.substring(0,data.length()-1);
			}
			data = data+"]";
		}catch(Exception ex){ex.printStackTrace();return "[]";}
		return data;		
	}

	public BigInteger findCountByCh_IdAndTime(String beginTime, String endTime,
			String ch_id) {
		try {

			String sql="SELECT count(*) FROM gm_historydata where ch_id='"+ch_id+"' and gm_historydata.hida_record_time>='"+beginTime+"' and gm_historydata.hida_record_time<='"+endTime+"'";
//			SQLQuery query=this.getSession().createSQLQuery(sql);
//			List<Object[]> list = query.list();
//			if(list.size()>0){
//				for(Object[] con : list){
//					count=Integer.parseInt(con[0].toString());//上报个数
//				}
//			}
			SQLQuery query = getSession().createSQLQuery(sql);
			return (BigInteger)query.uniqueResult();
//			List<Integer> list = query.list();
//			if(list.size()> 0 &&list!=null){
//				return list.get(0);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Gm_Historydata> findHistoryByChIdAndTimeStartingPosition(String chId, String beginTime, String endTime, int dataBegin, List<String> glList) {
		StringBuffer buf = new StringBuffer();
		if(!glList.isEmpty()){//hida_origValue
			for (String glV : glList) {
				buf.append(" and ").append("hida_corrValue!=").append(glV).append(" and ").append("hida_origValue!=").append(glV);
			}
		}
		
		String hql = "FROM Gm_Historydata WHERE ch_id=? AND hida_record_time >=? AND hida_record_time<=?";
		if(buf.length() > 1){
			hql += buf.toString();
		}
		hql += " ORDER BY hida_record_time ASC";
		
		Query query = getSession().createQuery(hql);
		query.setString(0, chId);
		query.setString(1, beginTime);
		query.setString(2, endTime);
		int start = dataBegin*10000;
		query.setFirstResult(start);
		int step = start+10000;
		query.setMaxResults(step);
		return query.list();
	}

	public BigInteger findCountByCh_IdAndTime(String beginTime, String endTime,
			String[] chIds) {
		String chId = "";
		for (String ch_Id : chIds) {
			chId+="'"+ch_Id+"',";
		}
		if(chId.length() > 1){
			chId = chId.substring(0,chId.length()-1);
		}
		String sql="SELECT count(*) FROM gm_historydata where ch_id in ('-1',"+chId+") and gm_historydata.hida_record_time>='"+beginTime+"' and gm_historydata.hida_record_time<='"+endTime+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		
		return (BigInteger)query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> findTimeAndDataByChidAndTime(String chid,Date startTime, Date endTime){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sqlData="select hida_record_time,hida_corrValue from gm_historydata "+
		"where ch_id='"+chid+"' and (hida_record_time>='"+
		format.format(startTime)+"' and hida_record_time<='"+
		format.format(endTime)+"') order by hida_record_time asc";
		//System.out.println(sqlData);
		return (List<Object[]>)getSession().createSQLQuery(sqlData).list();
	}
}
