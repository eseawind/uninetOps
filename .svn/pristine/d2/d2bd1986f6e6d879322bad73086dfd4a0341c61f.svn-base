package org.unism.gm.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.op.dao.Op_ChannelTypeDao;
import org.unism.op.dao.Op_SceneDao;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_Scene;
import org.unism.util.Chtype_for_charts;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
@Repository
public class Gm_ChannelDao extends HibernateBaseDao<Gm_Channel, String> {

	@Autowired Op_ChannelTypeDao op_ChannelTypeDao;
	@Autowired Op_SceneDao op_SceneDao;
	/**
	 * 根据场景ID查找场景下所有通道
	 * @param scene_id 场景ID
	 * @return 返回通道对象组成的List集合
	 */
		public List<Gm_Channel> findByScene_id(String scene_id){
			Search search = new Search();
			Filter filter = Filter.and(Filter.equal("scene_id.scene_id", scene_id),Filter.equal("ch_state", 1),Filter.equal("ch_offerSer", 1));
			search.addFilter(filter);
			return this.search(search);
		}
	
	/**
	 * 根据设备ID数组查找场景ID
	 * @return
	 */
	public List<String> findCh_idByDev_id_arr(String dev_id_arr_str){
		String sql = "select c.ch_id from gm_channel as c where c.dev_collectId in ("+dev_id_arr_str+") or c.dev_sensorId in ("+dev_id_arr_str+")";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> lst = query.list();
		return lst;
	}
	
	/**
	 * 指定通道ID 开始时间 结束时间 查询平均值，null=0
	 * @param ch_id
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findHisAvgByCh_idAndBeginTimeAndEndTime(String ch_id, String beginTime, String endTime) throws Exception{
		String json = "null";
		String sql = "select avg(hida_corrValue) from gm_historydata where ch_id='" + ch_id + "' and (hida_record_time>='" + beginTime + "' and hida_record_time<='" + endTime + "')";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Double> lst = query.list();
		if(lst.size()>0){
			if(lst.get(0) != null){
				Double avg = lst.get(0);			
				DecimalFormat f = new DecimalFormat("#.0");
				json = f.format(avg);
				if(json.substring(0,1).equals(".")){
					json = "0" + json;
				}else if(json.substring(0,1).equals("-") && json.substring(1,2).equals(".")){
					json = "-0" + json.substring(1);									
				}
			}
		}
		return json;
	}
	
	/**
	 * 曲线分析 指定场景id 查询对外提供服务的在用通道
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String json_findByScene_idAndCh_offerSer(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("ch_offerSer", 1),Filter.equal("scene_id.scene_id", scene_id));
		search.addFilter(filter);
		//search.addSortAsc("ch_no");
		List<Gm_Channel> list = this.search(search);
		String json = "[";
		if(list.size()>0){
			for(Gm_Channel gm_Channel : list){
				String chtype_no = "";
				if(gm_Channel.getChtype_id()!=null){
					chtype_no = gm_Channel.getChtype_id().getChtype_no();
				}
				json += "{ch_id:'"+gm_Channel.getCh_id()+"',ch_no:'"+gm_Channel.getCh_no()+"',ch_name:'"+gm_Channel.getCh_name()+"',chtype_no:'"+chtype_no+"',scene_name:'"+gm_Channel.getScene_id().getScene_name()+"'},";
			}
			json = json.substring(0,json.length()-1);
		}
		return json+"]";
	}
	
	/**
	 * 运维 曲线分析 指定场景id 查询在用通道
	 * @param scene_id
	 * @return
	 */
	public String json_findByScene_idAndCh_offerSer_run(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("scene_id.scene_id", scene_id));
		search.addFilter(filter);
		//search.addSortAsc("ch_no");
		List<Gm_Channel> list = this.search(search);
		String json = "[";
		if(list.size()>0){
			for(Gm_Channel gm_Channel : list){
				String chtype_no = "";
				if(gm_Channel.getChtype_id()!=null){
					chtype_no = gm_Channel.getChtype_id().getChtype_no();
				}
				json += "{ch_id:'"+gm_Channel.getCh_id()+"',ch_no:'"+gm_Channel.getCh_no()+"',ch_name:'"+gm_Channel.getCh_name()+"',chtype_no:'"+chtype_no+"',scene_name:'"+gm_Channel.getScene_id().getScene_name()+"'},";
			}
			json = json.substring(0,json.length()-1);
		}
		return json+"]";
	}
	
	/**
	 * 曲线分析 按设备查询通道
	 * @param dev_id_arr
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_showChannelByDevice_run(String[] dev_id_arr){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("dev_collectId.dev_id", dev_id_arr));
		search.addFilter(filter);
		//search.addSortAsc("ch_no");
		List<Gm_Channel> list = this.search(search);
		String json = "[";
		if(list.size()>0){
			for(Gm_Channel gm_Channel : list){
				String chtype_no = "";
				if(gm_Channel.getChtype_id()!=null){
					chtype_no = gm_Channel.getChtype_id().getChtype_no();
				}
				json += "{\"ch_id\":\""+gm_Channel.getCh_id()+"\",\"ch_no\":\""+gm_Channel.getCh_no()+"\",\"ch_name\":\""+gm_Channel.getCh_name()+"\",\"chtype_no\":\""+chtype_no+"\",\"scene_name\":\""+gm_Channel.getScene_id().getScene_name()+"\"},";
			}
			json = json.substring(0,json.length()-1);
		}
		return json+"]";
	}
	
	public List<String> findGm_ChannelByScene_id(String scene_id)
	{
		String sql = "select ch_id from gm_channel where scene_id='"+scene_id+"' and ch_state=1 and ch_offerSer=1";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		
		return query.list();
	}
	
	
	/**
	 * 曲线分析 指定场景id集合 查询对外提供服务的在用通道 并按通道应用类型分类
	 * @return json 
	 * [
	 * 		{
	 * 			chtype_id:'',
	 * 			chtype_no:'',
	 * 			chtype_name:'',
	 * 			channels:
	 * 					[
	 * 						{
	 * 							ch_id:'',
	 * 							ch_no:'',
	 * 							ch_name:''
	 * 						},
	 * 						...
	 * 					]
	 * 		},
	 * 		...
	 * ]
	 * @author Wang_Yuliang
	 */
	public String json_findAllByScene_idAndCh_offerSer(List<String> scene_id_list){
		String json = "[";
		String scene_id_list_str = "('-1',";
		for(String scene_id : scene_id_list){
			scene_id_list_str += "'"+scene_id+"',";
		}
		scene_id_list_str = scene_id_list_str.substring(0,scene_id_list_str.length()-1)+")";
		String sql_chtype = "select chtype_id from gm_channel where scene_id in"+scene_id_list_str+" and ch_state=1 and ch_offerSer=1 group by chtype_id order by chtype_id";
		SQLQuery query = getSession().createSQLQuery(sql_chtype);
		List<String> chtype_id_lst = query.list();
		if(chtype_id_lst.size()>0){
			for(String chtype_id : chtype_id_lst){
				if(chtype_id != null){
					Op_ChannelType op_ChannelType = this.op_ChannelTypeDao.findById(chtype_id);
					json += "{chtype_id:'"+op_ChannelType.getChtype_id()+"',chtype_no:'"+op_ChannelType.getChtype_no()+"',chtype_name:'"+op_ChannelType.getChtype_displayName()+"',channels:[";
					int c = 0;
					String sql_channels = "select ch_id,ch_no,ch_name,scene_id from gm_channel where scene_id in"+scene_id_list_str+" and ch_state=1 and ch_offerSer=1 and chtype_id='"+op_ChannelType.getChtype_id()+"'";
					query = getSession().createSQLQuery(sql_channels);
					List<Object[]> channels = query.list();
					if(channels.size()>0){
						for(Object[] channel : channels){
							if(channel[0] != null){
								String scene_name = "???";
								if(channel[3]!=null && !(channel[3]+"").equals("")){
									Op_Scene op_Scene = this.op_SceneDao.findById(channel[3]+"");
									if(op_Scene!=null){
										scene_name = op_Scene.getScene_name();										
									}
								}
								json += "{ch_id:'"+channel[0]+"',ch_no:'"+channel[1]+"',ch_name:'"+channel[2]+"',scene_name:'"+scene_name+"'},";
								c++;
							}
						}
					}
					if(c>0){
						json = json.substring(0,json.length()-1);
					}
					json +="]},";
				}				
			}
		}
		if(json.length()>1){
			json = json.substring(0,json.length()-1);
		}
		return json+"]";
	}
	
	/**
	 * 曲线分析 指定场景id集合 查询对外提供服务的在用通道 并按通道应用类型分类
	 * @return json 
	 * [
	 * 		{
	 * 			chtype_id:'',
	 * 			chtype_no:'',
	 * 			chtype_name:'',
	 * 			channels:
	 * 					[
	 * 						{
	 * 							ch_id:'',
	 * 							ch_no:'',
	 * 							ch_name:''
	 * 						},
	 * 						...
	 * 					]
	 * 		},
	 * 		...
	 * ]
	 * @author Wang_Yuliang
	 */
	public String json_findAllByScene_idAndCh_offerSer_run(List<String> scene_id_list){
		String json = "[";
		String scene_id_list_str = "('-1',";
		for(String scene_id : scene_id_list){
			scene_id_list_str += "'"+scene_id+"',";
		}
		scene_id_list_str = scene_id_list_str.substring(0,scene_id_list_str.length()-1)+")";
		String sql_chtype = "select chtype_id from gm_channel where scene_id in"+scene_id_list_str+" and ch_state=1 group by chtype_id order by chtype_id";
		SQLQuery query = getSession().createSQLQuery(sql_chtype);
		List<String> chtype_id_lst = query.list();
		if(chtype_id_lst.size()>0){
			for(String chtype_id : chtype_id_lst){
				if(chtype_id != null){
					Op_ChannelType op_ChannelType = this.op_ChannelTypeDao.findById(chtype_id);
					json += "{\"chtype_id\":\""+op_ChannelType.getChtype_id()+"\",\"chtype_no\":\""+op_ChannelType.getChtype_no()+"\",\"chtype_name\":\""+op_ChannelType.getChtype_displayName()+"\",\"channels\":[";
					int c = 0;
					String sql_channels = "select ch_id,ch_no,ch_name,scene_id from gm_channel where scene_id in"+scene_id_list_str+" and ch_state=1 and chtype_id='"+op_ChannelType.getChtype_id()+"'";
					query = getSession().createSQLQuery(sql_channels);
					List<Object[]> channels = query.list();
					if(channels.size()>0){
						for(Object[] channel : channels){
							if(channel[0] != null){
								String scene_name = "???";
								if(channel[3]!=null && !(channel[3]+"").equals("")){
									Op_Scene op_Scene = this.op_SceneDao.findById(channel[3]+"");
									if(op_Scene!=null){
										scene_name = op_Scene.getScene_name();										
									}
								}
								json += "{\"ch_id\":\""+channel[0]+"\",\"ch_no\":\""+channel[1]+"\",\"ch_name\":\""+channel[2]+"\",\"scene_name\":\""+scene_name+"\"},";
								c++;
							}
						}
					}
					if(c>0){
						json = json.substring(0,json.length()-1);
					}
					json +="]},";
				}				
			}
		}
		if(json.length()>1){
			json = json.substring(0,json.length()-1);
		}
		return json+"]";
	}
	
	/**
	 * 指定场景ID 通道应用类型编号 起止时间 查询平均值
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findAvgByGm_Channel_listAndChtype_noAndCheckTime(List<Gm_Channel> gm_Channel_list, String chtype_no, String beginTime, String endTime){
		String json = "???";
		String sql = "select avg(l.hida_corrValue) from gm_latestdata as l where l.ch_id in ('-1',";
			//if(gm_Channel_list.size() > 0){
				for(Gm_Channel gm_Channel : gm_Channel_list){
					sql += "'"+gm_Channel.getCh_id()+"',";
				}
				sql = sql.substring(0,sql.length()-1);
			//}			
		sql += ")";
		sql += " and (select c3.ch_offerSer from gm_channel as c3 where c3.ch_id=l.ch_id) in(1)";
		sql += " and (select c2.ch_state from gm_channel as c2 where c2.ch_id=l.ch_id) in(1)";
		sql += " and (select c.chtype_id from gm_channel as c where c.ch_id=l.ch_id) in(select t.chtype_id from op_channeltype as t where chtype_no='"+chtype_no+"')";
		sql +=" and (l.hida_record_time>='"+beginTime+"' and l.hida_record_time<='"+endTime+"')";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Double> lst = query.list();
		if(lst.size()>0){
			if(lst.get(0) != null){
				Double avg = lst.get(0);			
				DecimalFormat f = new DecimalFormat("#.0");
				json = f.format(avg);
				if(json.substring(0,1).equals(".")){
					json = "0" + json;
				}else if(json.substring(0,1).equals("-") && json.substring(1,2).equals(".")){
					json = "-0" + json.substring(1);									
				}
			}
		}
		return json;
	}

	/**
	 * 指定场景ID 通道应用类型编号 起止时间 查询最小值
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findMinByGm_Channel_listAndChtype_noAndCheckTime(List<Gm_Channel> gm_Channel_list, String chtype_no,String beginTime, String endTime) {
		String json = "???";
		String sql = "select min(l.hida_corrValue) from gm_latestdata as l where l.ch_id in ('-1',";
			//if(gm_Channel_list.size() > 0){
				for(Gm_Channel gm_Channel : gm_Channel_list){
					sql += "'"+gm_Channel.getCh_id()+"',";
				}
				sql = sql.substring(0,sql.length()-1);
			//}			
		sql += ")";
		sql += " and (select c3.ch_offerSer from gm_channel as c3 where c3.ch_id=l.ch_id) in(1)";
		sql += " and (select c2.ch_state from gm_channel as c2 where c2.ch_id=l.ch_id) in(1)";
		sql += " and (select c.chtype_id from gm_channel as c where c.ch_id=l.ch_id) in(select t.chtype_id from op_channeltype as t where chtype_no='"+chtype_no+"')";
		sql +=" and (l.hida_record_time>='"+beginTime+"' and l.hida_record_time<='"+endTime+"')";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Double> lst = query.list();
		if(lst.size()>0){
			if(lst.get(0) != null){
				Double avg = lst.get(0);			
				DecimalFormat f = new DecimalFormat("#.0");
				json = f.format(avg);
				if(json.substring(0,1).equals(".")){
					json = "0" + json;
				}else if(json.substring(0,1).equals("-") && json.substring(1,2).equals(".")){
					json = "-0" + json.substring(1);									
				}
			}
		}
		return json;
	}
	
	public String test(List<Gm_Channel> gm_Channel_list, List<String> chtype_no_list, String beginTime, String endTime){
		String json = "???";
		String sql = "select avg(l.hida_corrValue) from gm_latestdata as l where l.ch_id in ('-1',";
			//if(gm_Channel_list.size() > 0){
				for(Gm_Channel gm_Channel : gm_Channel_list){
					sql += "'"+gm_Channel.getCh_id()+"',";
				}
				sql = sql.substring(0,sql.length()-1);
			//}			
		sql += ")";
		sql += " and (select c3.ch_offerSer from gm_channel as c3 where c3.ch_id=l.ch_id) in(1)";
		sql += " and (select c2.ch_state from gm_channel as c2 where c2.ch_id=l.ch_id) in(1)";
		sql += " and (select c.chtype_id from gm_channel as c where c.ch_id=l.ch_id) in(select t.chtype_id from op_channeltype as t where chtype_no in (";
		if(chtype_no_list.size() > 0){
			for (String chtype_no : chtype_no_list)
			{
				sql += "'"+chtype_no+"',";
			}
			sql = sql.substring(0,sql.length()-1);
		}
		sql += ")";
		sql +=" and (l.hida_record_time>='"+beginTime+"' and l.hida_record_time<='"+endTime+"')";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Double> lst = query.list();
		if(lst.size()>0){
			if(lst.get(0) != null){
				Double avg = lst.get(0);			
				DecimalFormat f = new DecimalFormat("#.0");
				json = f.format(avg);
				if(json.substring(0,1).equals(".")){
					json = "0" + json;
				}else if(json.substring(0,1).equals("-") && json.substring(1,2).equals(".")){
					json = "-0" + json.substring(1);									
				}
			}
		}
		return json;
	}
	
	/**
	 * 指定一个通道ID的数据，将其按照通道应用类型编号分类，分成若干组，并封装到Chtype_for_charts对象中
	 * @author Wang_Yuliang
	 */
	@SuppressWarnings("unchecked")
	public List<Chtype_for_charts> findCh_id_arr_listGroupByChtype_no(String[] ch_id_arr){
		List<Chtype_for_charts> chtype_for_charts_list = new ArrayList<Chtype_for_charts>();
		String sql = "select chtype_id from gm_channel where ch_id in('-1',";
		for(String ch_id : ch_id_arr){
			sql += "'"+ch_id+"',";
		}
		sql = sql.substring(0,sql.length()-1);
		sql += ") group by chtype_id";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> lst = query.list();
		if(lst.size()>0){
			for(String chtype_id : lst){				
				if(chtype_id != null && !chtype_id.equals("")){
					Op_ChannelType op_ChannelType = this.op_ChannelTypeDao.findById(chtype_id);
					if(op_ChannelType != null){
						Chtype_for_charts chtype_for_charts = new Chtype_for_charts();
						chtype_for_charts.chtype_id = op_ChannelType.getChtype_id();
						chtype_for_charts.chtype_no = op_ChannelType.getChtype_no();
						chtype_for_charts.chtype_displayName = op_ChannelType.getChtype_displayName();
						Search search = new Search();
						Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("chtype_id.chtype_id", op_ChannelType.getChtype_id()),Filter.in("ch_id", ch_id_arr));
						search.addFilter(filter);
						chtype_for_charts.channel_list = this.search(search);
						chtype_for_charts_list.add(chtype_for_charts);
					}
				}
			}
		}
		return chtype_for_charts_list;
	}

	@SuppressWarnings("unchecked")
	public List<Gm_Channel> findBySceneIDandChtype(String scene_id,
			List<String> chtype_idList)
	{
		String hql = "FROM Gm_Channel WHERE scene_id='"+scene_id+"' and chtype_id in ('-1',";
		for (String chtype_id : chtype_idList)
		{
			hql += "'"+chtype_id+"',";
		}
		hql = hql.substring(0,hql.length()-1);
		hql += ")";
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Gm_Channel> findBySceneIDandChtype_id(String scene_id,
			String chtype_id)
	{
		String hql = "FROM Gm_Channel WHERE scene_id='"+scene_id+"' and chtype_id='"+chtype_id+"'";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	public List<Gm_Channel> findDev_idByCh_id(String ch_id_arr) {
		String hql = "FROM Gm_Channel WHERE ch_id in ("+ch_id_arr+")";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 加载状态通道编号
	 * @return
	 * @auther Wang_Yuliang
	 */
	public String json_jiazaizhuangtaitongdaobianhao(String dev_id, String zhuangtaitongdaobiaohao){
		String json = "[";
		String sql = "select c.ch_id,c.ch_no from gm_channel as c "; 
		sql += "where c.scene_id in(select d.scene_id from gm_device as d where d.dev_id='"+dev_id+"') ";
		sql += "and c.ch_no like '%"+zhuangtaitongdaobiaohao+"%'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> lst = query.list();
		if(lst.size()>0){
			if(lst.get(0) != null){
				for(Object[] row : lst){
					json += "{";
					json += "ch_id:'"+row[0]+"',";
					json += "ch_no:'"+row[1]+"'";
					json += "},";
				}
			}
		}
		if(json.length()>1)
		json = json.substring(0,(json.length()-1));
		return json+"]";
	}
	
	public List<Gm_Channel> findAllByAddrLikeBegin(String addr) {
		String hql = "FROM Gm_Channel WHERE ch_state=1 and ch_no like '"+addr+"-%'";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
}
