package org.unism.gm.dao;

import java.text.DecimalFormat;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_Devsts;
import org.unism.util.DecimalUtils;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Search;

@Repository
public class Gm_DevstsDao extends HibernateBaseDao<Gm_Devsts, String> {
	/**
	 * 根据设备地址查设备状态
	 * @param devAddr 设备地址
	 * @return
	 */
	public Gm_Devsts findByDevAddr(String devAddr) {
		Search search = new Search();
		search.addFilterEqual("dev_addr", devAddr);
		List<Gm_Devsts> list = search(search);
		if(!list.isEmpty()&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 分页查询
	 * @return json
	 * 		{
	 * 			count:'?',
	 * 			no:'?',
	 * 			size:'12',
	 * 			result:
	 * 				[
	 * 					{
	 * 						dev_name:'?',
	 * 						net_addr:'?',
	 * 						dest_regSts:'?',
	 * 						dest_lastCommTime:'?',
	 * 						dest_curTime:'?',
	 * 						dest_timeSts:'?',
	 * 						dest_workSts:'?',
	 * 						dest_sigStg:'?',
	 * 						dest_commQuaily:'?',
	 * 						dest_linkSts:'?',
	 * 						st_commCyc:'?',
	 * 						dest_repCyc:'?',
	 * 						dest_vol:'?',
	 * 						dest_resetNum:'?',
	 * 						dest_recData:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 * 0628 弃用 Wang_Yuliang
	 */
	public String page(List<String> scene_id_arr, Integer no, Integer size){
		try{			
			String sql1 = "select ";
			sql1 += "d.dev_name,";
			sql1 += "(select e.net_addr from gm_devnet as e where e.net_state=1 and  e.dev_id=d.dev_id limit 0,1) as net_addr,";
			sql1 += "(select s.dest_regSts from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_regSts,";
			sql1 += "(select s.dest_lastCommTime from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_lastCommTime,";
			sql1 += "(select s.dest_curTime from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_curTime,";
			sql1 += "(select s.dest_timeSts from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_timeSts,";
			sql1 += "(select s.dest_workSts from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_workSts,";
			sql1 += "(select s.dest_sigStg from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_sigStg,";
			sql1 += "(select s.dest_commQuaily from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_commQuaily,";
			sql1 += "(select s.dest_linkSts from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_linkSts,";
			sql1 += "(select s.dest_commCyc from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as st_commCyc,";
			sql1 += "(select s.dest_repCyc from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_repCyc,";
			sql1 += "(select s.dest_vol from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_vol,";
			sql1 += "(select s.dest_resetNum from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_resetNum,";
			sql1 += "(select s.dest_recData from gm_devsts as s where s.dev_id=d.dev_id limit 0,1) as dest_recData ";
			sql1 += "from gm_device as d ";
			sql1 += "where d.dev_state=1 ";
			sql1 += "and d.scene_id in('-1',";
			for(String scene_id : scene_id_arr){
				sql1 += "'"+scene_id+"',";
			}
			sql1 = sql1.substring(0,(sql1.length()-1));
			sql1 += ") ";
			sql1 += "and d.dev_btype=0 ";
			sql1 += "limit "+((no-1)*size)+","+size;	
			String sql2 = "select count(d.dev_id)";
			sql2 += "from gm_device as d ";
			sql2 += "where d.dev_state=1 ";
			sql2 += "and d.scene_id in('-1',";
			for(String scene_id : scene_id_arr){
				sql2 += "'"+scene_id+"',";
			}
			sql2 = sql2.substring(0,(sql2.length()-1));
			sql2 += ") ";
			sql2 += "and d.dev_btype=0";	
			SQLQuery query = getSession().createSQLQuery(sql2);
			String count = "0";
			List<String> listc = query.list();
			if(listc.size()>0){
				if(listc.get(0)!=null){
					count = String.valueOf(listc.get(0))+"";
				}
			}
			query = getSession().createSQLQuery(sql1);
			List<Object[]> listr = query.list();
			DecimalFormat ff = new DecimalFormat("0.00");
			String result = "[";
			if(listr.size()>0){
				for(Object[] row : listr){
					result += "{dev_name:'"+row[0]+"',";
					result += "net_addr:'"+row[1]+"',";
					result += "dest_regSts:'"+row[2]+"',";
					result += "dest_lastCommTime:'"+row[3]+"',";
					result += "dest_curTime:'"+row[4]+"',";
					result += "dest_timeSts:'"+row[5]+"',";
					result += "dest_workSts:'"+row[6]+"',";
					result += "dest_sigStg:'"+row[7]+"',";
					String dest_commQuaily = "";
					if(row[8]!=null){
						dest_commQuaily = ff.format(row[8]);
					}
					result += "dest_commQuaily:'"+dest_commQuaily+"',";
					result += "dest_linkSts:'"+row[9]+"',";
					result += "dest_commCyc:'"+row[10]+"',";
					result += "dest_repCyc:'"+row[11]+"',";
					String dest_vol = "";
					if(row[12]!=null){
						dest_vol = ff.format(row[12]);
					}
					result += "dest_vol:'"+dest_vol+"',";
					result += "dest_resetNum:'"+row[13]+"',";
					result += "dest_recData:'"+row[14]+"'},";
				}
			}
			if(result.length()>1){
				result = result.substring(0,(result.length()-1));
			}
			result += "]";
			String json = "{";
			json += "count:"+count+",";
			json += "no:"+no+",";
			json += "size:"+size+",";
			json += "result:"+result;
			json += "}";
			return json;
		}catch(Exception ex){ex.printStackTrace();return "{count:0,no:1,size:12,result:[]}";}
	}
	
	/**
	 * 分页查询 (由王振智提供的sql脚本)
	 * @return json
	 * 		{
	 * 			count:'?',
	 * 			no:'?',
	 * 			size:'12',
	 * 			result:
	 * 				[
	 * 					{
	 * 						dev_name:'?',
	 * 						net_addr:'?',
	 * 						dest_regSts:'?',
	 * 						dest_lastCommTime:'?',
	 * 						dest_curTime:'?',
	 * 						dest_timeSts:'?',
	 * 						dest_workSts:'?',
	 * 						dest_sigStg:'?',
	 * 						dest_commQuaily:'?',
	 * 						dest_linkSts:'?',
	 * 						st_commCyc:'?',
	 * 						dest_repCyc:'?',
	 * 						dest_vol:'?',
	 * 						dest_resetNum:'?',
	 * 						dest_recData:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 */
	public String page_wangzz(List<String> scene_id_arr, Integer no, Integer size){
		try{
			String sql1 = "SELECT gm_device.dev_name, " +
					"gm_devsts.dev_addr," +
					"gm_devsts.dest_regSts," +
					"gm_devsts.dest_lastCommTime," +
					"gm_devsts.dest_curTime," +
					"gm_devsts.dest_timeSts," +					
					"gm_devsts.dest_workSts," +
					"gm_devsts.dest_sigStg," +
					"gm_devsts.dest_commQuaily," +
					"gm_devsts.dest_linkSts," +
					"gm_devsts.dest_commCyc," +					
					"gm_devsts.dest_repCyc," +
					"gm_devsts.dest_vol," +
					"gm_devsts.dest_resetNum," +
					"gm_devsts.dest_recData," +
					"gm_devsts.dest_norepData," +
					"gm_devsts.dest_collectCyc," +
					"gm_devsts.dest_storageCyc," +
					"gm_devsts.dest_softVersion," +
					"gm_devsts.dest_hardwareVersion," +	
					"gm_devsts.dev_id" +
					" FROM gm_devsts " +
					"inner join gm_device where gm_devsts.dev_id = gm_device.dev_id " +
					"and gm_device.scene_id in ('-1',";
			for(String scene_id : scene_id_arr){
				sql1 += "'"+scene_id+"',";
			}
			sql1 = sql1.substring(0,(sql1.length()-1));		
			sql1 += ") ";
			sql1 += "and gm_device.dev_state=1 ";
			sql1 += "and gm_device.dev_btype=0 ";
			sql1 += "limit "+((no-1)*size)+","+size;	
			
			String sql2 = "SELECT count(gm_device.dev_id) " +
				" FROM gm_devsts " +
			"inner join gm_device where gm_devsts.dev_id = gm_device.dev_id " +
			"and gm_device.scene_id in ('-1',";
			for(String scene_id : scene_id_arr){
				sql2 += "'"+scene_id+"',";
			}
			sql2 = sql2.substring(0,(sql2.length()-1));		
			sql2 += ") ";
			sql2 += "and gm_device.dev_state=1 ";
			sql2 += "and gm_device.dev_btype=0";
			SQLQuery query = getSession().createSQLQuery(sql2);
			String count = "0";
			List<String> listc = query.list();
			if(listc.size()>0){
				if(listc.get(0)!=null){
					count = String.valueOf(listc.get(0))+"";
				}
			}
			query = getSession().createSQLQuery(sql1);
			List<Object[]> listr = query.list();
			DecimalFormat ff = new DecimalFormat("0.00");
			String result = "[";
			if(listr.size()>0){
				for(Object[] row : listr){
					result += "{";	
					String _dev_name = "";
					if(row[0]!=null){
						_dev_name = String.valueOf(row[0]);
					}
					result += "dev_name:'"+_dev_name+"',";
					String _net_addr = "";
					if(row[1]!=null){
						_net_addr = String.valueOf(row[1]);
					}
					result += "net_addr:'"+_net_addr+"',";
					String _dest_regSts = "";
					if(row[2]!=null){
						_dest_regSts = String.valueOf(row[2]);
					}
					result += "dest_regSts:'"+_dest_regSts+"',";
					String _dest_lastCommTime = "";
					if(row[3]!=null){
						_dest_lastCommTime = String.valueOf(row[3]);
						_dest_lastCommTime = _dest_lastCommTime.substring(0,19);
					}
					result += "dest_lastCommTime:'"+_dest_lastCommTime+"',";
					String _dest_curTime = "";
					if(row[4]!=null){
						_dest_curTime = String.valueOf(row[4]);
						_dest_curTime = _dest_curTime.substring(0,19);
					}
					result += "dest_curTime:'"+_dest_curTime+"',";
					String _dest_timeSts = "";
					if(row[5]!=null){
						_dest_timeSts = String.valueOf(row[5]);
					}
					result += "dest_timeSts:'"+_dest_timeSts+"',";
					String _dest_workSts = "";
					if(row[6]!=null){
						_dest_workSts = String.valueOf(row[6]);
					}
					result += "dest_workSts:'"+_dest_workSts+"',";
					String _dest_sigStg = "";
					if(row[7]!=null){
						_dest_sigStg = String.valueOf(row[7]);
					}
					result += "dest_sigStg:'"+_dest_sigStg+"',";
					String dest_commQuaily = "";
					if(row[8]!=null){
						dest_commQuaily = ff.format(row[8]);
					}
					result += "dest_commQuaily:'"+dest_commQuaily+"',";
					String _dest_linkSts = "";
					if(row[9]!=null){
						_dest_linkSts = String.valueOf(row[9]);
					}
					result += "dest_linkSts:'"+_dest_linkSts+"',";
					String _dest_commCyc = "";
					if(row[10]!=null){
						_dest_commCyc = String.valueOf(row[10]);
					}
					result += "dest_commCyc:'"+_dest_commCyc+"',";
					String _dest_repCyc = "";
					if(row[11]!=null){
						_dest_repCyc = String.valueOf(row[11]);
					}
					result += "dest_repCyc:'"+_dest_repCyc+"',";
					String _dest_vol = "";
					if(row[12]!=null){
						_dest_vol = DecimalUtils.subDecimal(Double.parseDouble(String.valueOf(row[12])),0);
					}
					result += "dest_vol:'"+_dest_vol+"',";
					String _dest_resetNum = "";
					if(row[13]!=null){
						_dest_resetNum = String.valueOf(row[13]);
					}
					result += "dest_resetNum:'"+_dest_resetNum+"',";
					String _dest_recData = "";
					if(row[14]!=null){
						_dest_recData = String.valueOf(row[14]);
					}
					result += "dest_recData:'"+_dest_recData+"',";
					String _dest_norepData = "";
					if(row[15]!=null){
						_dest_norepData = String.valueOf(row[15]);
					}
					result += "dest_norepData:'"+_dest_norepData+"',";
					String _dest_collectCyc = "";
					if(row[16]!=null){
						_dest_collectCyc = String.valueOf(row[16]);
					}
					result += "dest_collectCyc:'"+_dest_collectCyc+"',";
					String _dest_storageCyc = "";
					if(row[17]!=null){
						_dest_storageCyc = String.valueOf(row[17]);
					}
					result += "dest_storageCyc:'"+_dest_storageCyc+"',";
					String _dest_softVersion = "";
					if(row[18]!=null){
						_dest_softVersion = String.valueOf(row[18]);
					}
					result += "dest_softVersion:'"+_dest_softVersion+"',";
					String _dest_hardwareVersion = "";
					if(row[19]!=null){
						_dest_hardwareVersion = String.valueOf(row[19]);
					}
					result += "dest_hardwareVersion:'"+_dest_hardwareVersion+"',";
					String _dev_id = "";
					if(row[20]!=null){
						_dev_id = String.valueOf(row[20]);
					}
					result += "dev_id:'"+_dev_id+"'},";
				}
			}
			if(result.length()>1){
				result = result.substring(0,(result.length()-1));
			}
			result += "]";
			String json = "{";
			json += "count:"+count+",";
			json += "no:"+no+",";
			json += "size:"+size+",";
			json += "result:"+result;
			json += "}";
			return json;
		}catch(Exception ex){ex.printStackTrace();return "{count:0,no:1,size:12,result:[]}";}
	}
	
	/**
	 * 分页查询 (由王振智提供的sql脚本)
	 * @return json
	 * 		{
	 * 			count:'?',
	 * 			no:'?',
	 * 			size:'12',
	 * 			result:
	 * 				[
	 * 					{
	 * 						dev_name:'?',
	 * 						net_addr:'?',
	 * 						dest_regSts:'?',
	 * 						dest_lastCommTime:'?',
	 * 						dest_curTime:'?',
	 * 						dest_timeSts:'?',
	 * 						dest_workSts:'?',
	 * 						dest_sigStg:'?',
	 * 						dest_commQuaily:'?',
	 * 						dest_linkSts:'?',
	 * 						st_commCyc:'?',
	 * 						dest_repCyc:'?',
	 * 						dest_vol:'?',
	 * 						dest_resetNum:'?',
	 * 						dest_recData:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 * 这个是根据 查询按钮触发的查询 更改了查询条件 Wang_Yuliang 2011-07-20 10:28
	 */
	public String page_search(List<String> scene_id_list, String hid_condition, String hid_value, Integer no, Integer size){
		try{
			String sql_condition = "and 1=1 ";
			if(hid_condition.equals("dev_addr")){
				sql_condition = "and gm_devsts.dev_addr like '%"+hid_value+"%' ";
			}else if(hid_condition.equals("dest_workSts") || hid_condition.equals("dest_regSts")){
				sql_condition = "and gm_devsts."+hid_condition+"="+hid_value+" ";				
			}
			String sql1 = "SELECT gm_device.dev_name, " +
					"gm_devsts.dev_addr," +
					"gm_devsts.dest_regSts," +
					"gm_devsts.dest_lastCommTime," +
					"gm_devsts.dest_curTime," +
					"gm_devsts.dest_timeSts," +					
					"gm_devsts.dest_workSts," +
					"gm_devsts.dest_sigStg," +
					"gm_devsts.dest_commQuaily," +
					"gm_devsts.dest_linkSts," +
					"gm_devsts.dest_commCyc," +					
					"gm_devsts.dest_repCyc," +
					"gm_devsts.dest_vol," +
					"gm_devsts.dest_resetNum," +
					"gm_devsts.dest_recData," +
					"gm_devsts.dest_norepData," +
					"gm_devsts.dest_collectCyc," +
					"gm_devsts.dest_storageCyc," +
					"gm_devsts.dest_softVersion," +
					"gm_devsts.dest_hardwareVersion," +	
					"gm_devsts.dev_id" +
					" FROM gm_devsts " +
					"inner join gm_device where gm_devsts.dev_id = gm_device.dev_id " +
					"and gm_device.scene_id in ('-1',";
			for(String scene_id : scene_id_list){
				sql1 += "'"+scene_id+"',";
			}
			sql1 = sql1.substring(0,(sql1.length()-1));		
			sql1 += ") ";
			sql1 += sql_condition;
			sql1 += "and gm_device.dev_state=1 ";
			sql1 += "and gm_device.dev_btype=0 ";
			sql1 += "limit "+((no-1)*size)+","+size;	
			
			String sql2 = "SELECT count(gm_device.dev_id) " +
				" FROM gm_devsts " +
			"inner join gm_device where gm_devsts.dev_id = gm_device.dev_id " +
			"and gm_device.scene_id in ('-1',";
			for(String scene_id : scene_id_list){
				sql2 += "'"+scene_id+"',";
			}
			sql2 = sql2.substring(0,(sql2.length()-1));		
			sql2 += ") ";
			sql2 += sql_condition;
			sql2 += "and gm_device.dev_state=1 ";
			sql2 += "and gm_device.dev_btype=0";
			SQLQuery query = getSession().createSQLQuery(sql2);
			String count = "0";
			List<String> listc = query.list();
			if(listc.size()>0){
				if(listc.get(0)!=null){
					count = String.valueOf(listc.get(0))+"";
				}
			}
			query = getSession().createSQLQuery(sql1);
			List<Object[]> listr = query.list();
			DecimalFormat ff = new DecimalFormat("0.00");
			String result = "[";
			if(listr.size()>0){
				for(Object[] row : listr){
					result += "{";	
					String _dev_name = "";
					if(row[0]!=null){
						_dev_name = String.valueOf(row[0]);
					}
					result += "dev_name:'"+_dev_name+"',";
					String _net_addr = "";
					if(row[1]!=null){
						_net_addr = String.valueOf(row[1]);
					}
					result += "net_addr:'"+_net_addr+"',";
					String _dest_regSts = "";
					if(row[2]!=null){
						_dest_regSts = String.valueOf(row[2]);
					}
					result += "dest_regSts:'"+_dest_regSts+"',";
					String _dest_lastCommTime = "";
					if(row[3]!=null){
						_dest_lastCommTime = String.valueOf(row[3]);
						_dest_lastCommTime = _dest_lastCommTime.substring(0,19);
					}
					result += "dest_lastCommTime:'"+_dest_lastCommTime+"',";
					String _dest_curTime = "";
					if(row[4]!=null){
						_dest_curTime = String.valueOf(row[4]);
						_dest_curTime = _dest_curTime.substring(0,19);
					}
					result += "dest_curTime:'"+_dest_curTime+"',";
					String _dest_timeSts = "";
					if(row[5]!=null){
						_dest_timeSts = String.valueOf(row[5]);
					}
					result += "dest_timeSts:'"+_dest_timeSts+"',";
					String _dest_workSts = "";
					if(row[6]!=null){
						_dest_workSts = String.valueOf(row[6]);
					}
					result += "dest_workSts:'"+_dest_workSts+"',";
					String _dest_sigStg = "";
					if(row[7]!=null){
						_dest_sigStg = String.valueOf(row[7]);
					}
					result += "dest_sigStg:'"+_dest_sigStg+"',";
					String dest_commQuaily = "";
					if(row[8]!=null){
						dest_commQuaily = ff.format(row[8]);
					}
					result += "dest_commQuaily:'"+dest_commQuaily+"',";
					String _dest_linkSts = "";
					if(row[9]!=null){
						_dest_linkSts = String.valueOf(row[9]);
					}
					result += "dest_linkSts:'"+_dest_linkSts+"',";
					String _dest_commCyc = "";
					if(row[10]!=null){
						_dest_commCyc = String.valueOf(row[10]);
					}
					result += "dest_commCyc:'"+_dest_commCyc+"',";
					String _dest_repCyc = "";
					if(row[11]!=null){
						_dest_repCyc = String.valueOf(row[11]);
					}
					result += "dest_repCyc:'"+_dest_repCyc+"',";
					String _dest_vol = "";
					if(row[12]!=null){
						_dest_vol = DecimalUtils.subDecimal(Double.parseDouble(String.valueOf(row[12])),0);
					}
					result += "dest_vol:'"+_dest_vol+"',";
					String _dest_resetNum = "";
					if(row[13]!=null){
						_dest_resetNum = String.valueOf(row[13]);
					}
					result += "dest_resetNum:'"+_dest_resetNum+"',";
					String _dest_recData = "";
					if(row[14]!=null){
						_dest_recData = String.valueOf(row[14]);
					}
					result += "dest_recData:'"+_dest_recData+"',";
					String _dest_norepData = "";
					if(row[15]!=null){
						_dest_norepData = String.valueOf(row[15]);
					}
					result += "dest_norepData:'"+_dest_norepData+"',";
					String _dest_collectCyc = "";
					if(row[16]!=null){
						_dest_collectCyc = String.valueOf(row[16]);
					}
					result += "dest_collectCyc:'"+_dest_collectCyc+"',";
					String _dest_storageCyc = "";
					if(row[17]!=null){
						_dest_storageCyc = String.valueOf(row[17]);
					}
					result += "dest_storageCyc:'"+_dest_storageCyc+"',";
					String _dest_softVersion = "";
					if(row[18]!=null){
						_dest_softVersion = String.valueOf(row[18]);
					}
					result += "dest_softVersion:'"+_dest_softVersion+"',";
					String _dest_hardwareVersion = "";
					if(row[19]!=null){
						_dest_hardwareVersion = String.valueOf(row[19]);
					}
					result += "dest_hardwareVersion:'"+_dest_hardwareVersion+"',";
					String _dev_id = "";
					if(row[20]!=null){
						_dev_id = String.valueOf(row[20]);
					}
					result += "dev_id:'"+_dev_id+"'},";
				}
			}
			if(result.length()>1){
				result = result.substring(0,(result.length()-1));
			}
			result += "]";
			String json = "{";
			json += "count:"+count+",";
			json += "no:"+no+",";
			json += "size:"+size+",";
			json += "result:"+result;
			json += "}";
			return json;
		}catch(Exception ex){ex.printStackTrace();return "{count:0,no:1,size:12,result:[]}";}
	}

	public List<Object[]> findDest_WorkStsListByUserIdAndDevaddr(String userId,
			String hid_condition, String hid_value, String scene_id_selectAll) {
		String sql="";		
	    if(scene_id_selectAll==null || "".equals(scene_id_selectAll)){
	    	sql="SELECT op_scene.scene_id,op_scene.scene_name,gm_devsts.dest_workSts,gm_devsts.dest_workStsInfo "+
			" FROM op_userinfo_scene INNER JOIN op_scene ON op_userinfo_scene.scene_id = op_scene.scene_id"+
			" INNER JOIN gm_device ON op_scene.scene_id = gm_device.scene_id"+
			" INNER JOIN gm_devsts ON gm_device.dev_id = gm_devsts.dev_id"+
			" where op_userinfo_scene.user_id='"+userId+"'";
	    	if(!"".equals(hid_condition)&&hid_condition!=null&&!"".equals(hid_value)&&hid_value!=null){
	    		sql+=" and gm_devsts."+hid_condition+"='"+hid_value+"'";
	    	}
	    	sql+=" group by op_scene.scene_id";
	    }else{
	    	sql="SELECT op_scene.scene_id,op_scene.scene_name,gm_devsts.dest_workSts,gm_devsts.dest_workStsInfo "+
	    	" FROM op_userinfo_scene INNER JOIN op_scene ON op_userinfo_scene.scene_id = op_scene.scene_id"+
			" INNER JOIN gm_device ON op_scene.scene_id = gm_device.scene_id"+
			" INNER JOIN gm_devsts ON gm_device.dev_id = gm_devsts.dev_id"+
			" where op_userinfo_scene.user_id='"+userId+"' and op_userinfo_scene.scene_id in ('"+scene_id_selectAll+"')";
			if(!"".equals(hid_condition)&&hid_condition!=null&&!"".equals(hid_value)&&hid_value!=null){
	    		sql+=" and gm_devsts."+hid_condition+"='"+hid_value+"'";
	    	}
			sql+=" group by op_scene.scene_id";
	    }
		SQLQuery query=this.getSession().createSQLQuery(sql);
		return query.list();
	}
}
