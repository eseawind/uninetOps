package org.unism.gm.dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.domain.Gm_Device;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

@Repository
public class Gm_DeviceDao extends HibernateBaseDao<Gm_Device, String>{

	/**
	 * 最新数据 删除
	 */
	
	public void gm_Latestdata_del(String ch_id_arr) throws Exception{
		//删除Gm_Latestdata 
		String sql = "";
		sql = "delete from gm_latestdata ";
		sql += "where ch_id in ("+ch_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 历史数据 删除
	 */
	
	public void gm_Historydata_del(String ch_id_arr) throws Exception{			
		//删除Gm_Historydata
		String sql = "";	
		sql = "delete from gm_historydata ";
		sql += "where ch_id in ("+ch_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 设备上报通道配置 删除
	 */
	
	public void gm_DevChannel_del(String dev_id_arr_str,String ch_id_arr) throws Exception{
		//删除Gm_DevChannel
		String sql = "";
		sql = "delete from gm_devchannel ";
		sql += "where dev_addr in(select n.net_addr from gm_devnet as n where n.dev_id in ("+dev_id_arr_str+")) ";
		sql += "or ch_id in ("+ch_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 故障信息 删除
	 */
	
	public void gm_DevFault_del(String dev_id_arr_str,String ch_id_arr) throws Exception{
		//删除Gm_DevFault
		String sql = "";
		sql = "delete from gm_devfault ";
		sql += "where ch_id in ("+ch_id_arr+") "; 
		sql += "or dev_id in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 报警配置参数 删除
	 */
	
	public void op_alarmargument_del(String ch_id_arr) throws Exception{
		//删除op_alarmargument	
		String sql = "";
		sql = "delete from op_alarmargument ";
		sql += "where ch_id in ("+ch_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 控制设备状态配置 删除
	 */
	
	public void op_DevCtrlSts_del(String ch_id_arr,String dect_id_arr) throws Exception{
		//删除Op_DevCtrlSts	
		String sql = "";
		sql = "delete from op_devctrlsts ";
		sql += "where dect_id in("+dect_id_arr+") ";
		sql += "or ch_id in ("+ch_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 控制设备按钮配置 删除
	 */
	
	public void op_DevCtrlBtn_del(String dev_id_arr_str,String dect_id_arr) throws Exception{
		//删除Op_DevCtrlBtn	
		String sql = "";
		sql = "delete from op_devctrlbtn ";
		sql += "where dect_id in ("+dect_id_arr+") "; 
		sql += "and dev_id in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 控制设备操作 删除
	 */
	
	public void gm_DevCtrlOperate_del(String dect_id_arr) throws Exception{
		//删除Gm_DevCtrlOperate		
		String sql = "";
		sql = "delete from gm_devctrloperate ";
		sql += "where dect_id in ("+dect_id_arr+")";
		this.executeUpdate(sql);
	}	
	
	/**
	 * 控制设备操作记录 删除
	 */
	
	public void gm_DevCtrlOperateHistory_del(String dect_id_arr) throws Exception{
		//删除Gm_DevCtrlOperateHistory	
		String sql = "";
		sql = "delete from gm_devctrloperatehistory ";
		sql += "where dect_id in ("+dect_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 手机控制设备 删除
	 */
	
	public void phone_DevCtrl_del(String dect_id_arr) throws Exception{		
		//删除Phone_DevCtrl
		String sql = "";
		sql = "delete from phone_devctrl ";
		sql += "where dect_id in ("+dect_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 控制设备状态 删除
	 */
	
	public void gm_DevCtrlSts_del(String dect_id_arr) throws Exception{
		//删除Gm_DevCtrlSts
		String sql = "";
		sql = "delete from gm_devctrlsts ";
		sql += "where dect_id in ("+dect_id_arr+") ";
		this.executeUpdate(sql);
	}
	
	/**
	 * 控制设备状态历史 删除
	 */
	
	public void gm_DevCtrlStsHistory_del(String dect_id_arr) throws Exception{
		//删除Gm_DevCtrlStsHistory
		String sql = "";
		sql = "delete from gm_devctrlstshistory ";
		sql += "where dect_id in ("+dect_id_arr+")";
		this.executeUpdate(sql);
	}

	/**
	 * 水产应用配置 删除
	 */
	
	public void pro_fisheries_del(String dect_id_arr) throws Exception{
		//更新pro_fisheries
		String sql = "";
		sql = "update pro_fisheries set dect_id=null ";
		sql += "where dect_id in ("+dect_id_arr+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 采集通道信息 删除
	 */
	
	public void gm_Channel_del(String dev_id_arr_str) throws Exception{
		//删除Gm_Channel		
		String sql = "";
		sql = "delete from gm_channel ";
		sql += "where dev_collectId in ("+dev_id_arr_str+") or dev_sensorId in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 控制设备信息 删除
	 */
	
	public void gm_DevCtrl_del(String dev_id_arr_str) throws Exception{
		//删除Gm_DevCtrl		
		String sql = "";
		sql = "delete from gm_devctrl ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);
	}
	
	/**
	 * 网络信息 删除
	 */
	
	public void gm_Devnet_del(String dev_id_arr_str) throws Exception{
		//删除Gm_Devnet
		String sql = "";
		sql = "delete from gm_devnet ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);		
	}
	
	/**
	 * 智能设备状态 删除
	 */
	
	public void gm_Devsts_del(String dev_id_arr_str) throws Exception{
		//删除Gm_Devsts
		String sql = "";
		sql = "delete from gm_devsts ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);		
	}
	
	/**
	 * 智能设备状态历史 删除
	 */
	
	public void gm_DevstsHis_del(String dev_id_arr_str) throws Exception{
		//删除Gm_DevstsHis
		String sql = "";
		sql = "delete from gm_devstshis ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);	
	}
	
	/**
	 * 设备信息表 删除
	 */
	
	public void gm_Device_del(String dev_id_arr_str) throws Exception{
		//删除Gm_Device	
		String sql = "";
		sql = "delete from gm_device ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.executeUpdate(sql);	
	}
	
	/**
	 * 查找在网设备的ID
	 * @return
	 */
	public List<String> findDev_idFromDevNet(){
		String sql = "select dev_id from gm_devnet where net_state=1";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> lst = query.list();
		lst.add("-1");
		return lst;
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
	 * 						dest_recData:'?',
	 * 						dev_state:'?',
	 * 						dev_id:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 */
	public String page_wangzz(List<String> scene_id_arr, Integer no, Integer size, String dev_addr){
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
					"gm_device.dev_state," +
					"gm_device.dev_id" +
					" FROM gm_devsts " +
					"inner join gm_device where gm_devsts.dev_id = gm_device.dev_id " +
					"and gm_device.scene_id in ('-1',";
			for(String scene_id : scene_id_arr){
				sql1 += "'"+scene_id+"',";
			}
			sql1 = sql1.substring(0,(sql1.length()-1));		
			sql1 += ") ";
			//sql1 += "and gm_device.dev_state=1 ";
			sql1 += "and gm_device.dev_btype=0 ";
			sql1 += "and gm_devsts.dev_addr like '%"+dev_addr+"%' ";
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
			//sql2 += "and gm_device.dev_state=1 ";
			sql2 += "and gm_device.dev_btype=0 ";
			sql2 += "and gm_devsts.dev_addr like '%"+dev_addr+"%'";
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
						_dest_vol = ff.format(row[12]);
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
					String _dev_state = "";
					if(row[15]!=null){
						_dev_state = String.valueOf(row[15]);
					}
					result += "dev_state:'"+_dev_state+"',";
					String _dev_id = "";
					if(row[16]!=null){
						_dev_id = String.valueOf(row[16]);
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
	 * 判断用户是否有操作指定设备信息的权限
	 * @param user_id
	 * @param dev_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isHasDevice(String user_id, String dev_id){
		boolean isHas = false;
		String sql = "select count(d.dev_id) from gm_device as d where d.scene_id in (select c.scene_id from op_userinfo_scene as c where user_id='"+user_id+"' and (select s.scene_state from op_scene as s where s.scene_id=c.scene_id)=1) and d.dev_id='"+dev_id+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Integer> list = query.list();
		if(list.size()>0){
			if(list.get(0)!=null){
				isHas = true;
			}
		}
		return isHas;
	}
	
	/**
	 * 修改指定设备ID 的设备名称 为测试事务回滚
	 * @param dev_id
	 * @param dev_name
	 * @return
	 * @author Wang_Yuliang
	 */
	public void upDevNameForTest(String dev_id,String dev_name){
		String sql = "update gm_device set dev_name='"+dev_name+"' where dev_id='"+dev_id+"'";
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	/**
	 * 指定sql脚本 执行更新
	 * @param sql
	 * @author Wang_Yuliang
	 */
	public void executeUpdate(String sql){
		SQLQuery query = getSession().createSQLQuery(sql);
		query.executeUpdate();
	}
	
	/**
	 * 指定设备编号 模糊查询设备ID数组
	 * @param dev_no
	 * @return
	 */
	public List<String> findDev_id_arrLikeDev_no(String dev_no){
		String sql = "select dev_id from gm_device where dev_state=1 ";
		sql += "and dev_no like '%"+dev_no+"%'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> list = query.list();
		return list;
	}
	
	/**
	 * 据场景ID 查找在用设备ID
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDev_id_listByScene_id(String scene_id){
		String sql = "select dev_id from gm_device where dev_state=1 ";
		sql += "and scene_id='"+scene_id+"' ";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> list = query.list();
		list.add("-1");
		return list;
	}

	public List<String> findDev_idByScene_idList(List<String> scene_idList)
	{
		String sql = "select dev_id from gm_device where scene_id in ('-1',";
		if(scene_idList != null){
			for (String scene_id : scene_idList)
			{
				sql += "'"+scene_id+"',";
			}
			sql = sql.substring(0,sql.length()-1);
		}
		sql += ")";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.list();
	}

	public List<String > findGRPSBysceneId(String scene_id_selectAll) {
		String sql="SELECT dev_id from gm_device where gm_device.dev_btype=0 ";		
	    if(scene_id_selectAll!=null || !"".equals(scene_id_selectAll)){
	    	sql+=" and gm_device.scene_id in ('"+scene_id_selectAll+"')";
	    }
		SQLQuery query=this.getSession().createSQLQuery(sql);
		return query.list();
	}	
	
}
