package org.unism.op.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_ChtypeOperate;
import org.unism.op.domain.Op_Scene;
import org.unism.util.DecimalUtils;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Op_ChtypeOperateDao extends HibernateBaseDao<Op_ChtypeOperate, String> {
	
	@Autowired Op_ChannelTypeDao op_ChannelTypeDao;
	@Autowired Op_SceneDao op_SceneDao;
	
	/**
	 * 数据汇总
	 * @return json
	 * 			[	
	 * 				{
	 * 					scene_gtype:'',
	 * 					head:
	 * 						[
	 * 							'xx','xxx','xxxx'
	 * 						],
	 * 					scenes:
	 * 						[
	 * 							null, 						
	 * 							{
	 * 								scene_name:'',
	 * 								time:'yyyy-MM-dd HH:mm:ss',
	 * 								data:
	 * 									[
	 * 										'21','23','','23'
	 * 									]
	 * 							},
	 * 							...
	 * 						]
	 * 				},
	 *				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String data_collect(List<String> scene_id_arr){
		try{
			//--时间检查
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currDate = new Date();
			String beginTime = df.format(currDate.getTime()-1000l*60l*60l*24l);
			String endTime = df.format(currDate.getTime()+1000l*60l*60l*1l);
			//--
			String scene_id_arr_str = "";
			for(String scene_id : scene_id_arr){
				scene_id_arr_str += "'"+scene_id+"',";
			}
			String sql_gtype = "select s.scene_gtype from op_scene as s where s.scene_id in('-1',";
			sql_gtype += scene_id_arr_str;
			sql_gtype = sql_gtype.substring(0,(sql_gtype.length()-1));
			sql_gtype += ") ";
			sql_gtype += "group by s.scene_gtype ";
			List<Integer> scene_gtype_list = new ArrayList<Integer>();
			SQLQuery query = getSession().createSQLQuery(sql_gtype);
			scene_gtype_list = query.list();
			String json = "[";
			if(scene_gtype_list.size()>0){				
				for(Integer scene_gtype : scene_gtype_list){
					//System.out.println("scene_gtype:"+scene_gtype);					
					String sql_head = "select cho.chtype_id,cho.ch_seat_no,cho.ch_depth,cho.cho_datetype from op_chtypeoperate as cho ";
					sql_head += "where cho.scene_gtype="+scene_gtype+" ";
					sql_head += "order by cho.cho_sequence asc";
					query = getSession().createSQLQuery(sql_head);
					List<Object []> op_ChtypeOperate_ob_list_head = query.list();
					if(op_ChtypeOperate_ob_list_head.size()>0){//没数据是0行嘛？一会试试
						json += "{scene_gtype:'"+scene_gtype+"',";						
						json += "head:['场景名称','采集时间',";
						for(Object[] row : op_ChtypeOperate_ob_list_head){
							Op_ChannelType op_ChannelType = null;
							if(row[0]!=null){
								//System.out.println(row[0]+"");
								op_ChannelType = this.op_ChannelTypeDao.findById(String.valueOf(row[0]));
							}
							json += "'";
							if(row[1]!=null && !(row[1]+"").equals("")){
								json += ""+row[1]+"_";
							}
							if(row[2]!=null && !(row[2]+"").equals("")){
								json += ""+row[2]+"_";
							}
							if(op_ChannelType!=null){								
								json += ""+op_ChannelType.getChtype_displayName()+"_";
							}
							if(row[3]!=null && !(row[3]+"").equals("")){
								if((row[3]+"").equals("avg")){
									json += "平均值_";
								}else if((row[3]+"").equals("max")){
									json += "最大值_";
								}else if((row[3]+"").equals("min")){
									json += "最小值_";
								}
							}
							json = json.substring(0,(json.length()-1));
							json += "',";
						}
						json = json.substring(0,(json.length()-1));
						json += "],";
						json += "scenes:[null,";
						String sql_scenes = "select s.scene_id from op_scene as s ";
						sql_scenes += "where s.scene_gtype="+scene_gtype+" ";
						sql_scenes += "and s.scene_id in('-1',";
						sql_scenes += scene_id_arr_str;
						sql_scenes = sql_scenes.substring(0,(sql_scenes.length()-1));
						sql_scenes += ")";
						query = getSession().createSQLQuery(sql_scenes);
						List<String> scene_id_list_bygtype = query.list();
						if(scene_id_list_bygtype.size()>0){
							for(String scene_id : scene_id_list_bygtype){
								if(scene_id!=null){									
									Op_Scene op_Scene = this.op_SceneDao.findById(scene_id);
									if(op_Scene!=null){
										json += "{";
										json += "scene_name:'"+op_Scene.getScene_name()+"',";
										String time = "";
										String sql_time = "select l.hida_record_time from gm_latestdata as l ";
										sql_time += "where l.ch_id in(select c.ch_id from gm_channel as c where c.ch_state=1 and c.scene_id='"+scene_id+"') ";
										sql_time += "order by l.hida_record_time desc ";
										sql_time += "limit 0,1";
										query = getSession().createSQLQuery(sql_time);
										List<String> time_list = query.list();
										if(time_list.size()>0){
											if(time_list.get(0)!=null){
												time = df.format(time_list.get(0));
											}
										}
										json += "time:'"+time+"',";
										json += "data:[";
										for(Object[] row : op_ChtypeOperate_ob_list_head){
											if(row[1]!=null && !(row[1]+"").equals("")){
												//json += ""+row[1]+"_";
											}
											if(row[2]!=null && !(row[2]+"").equals("")){
												//json += "'"+row[2]+"_";
											}

											json += "'";
											String value = "";
											
											String sql_value = "select ";
											if(row[3]!=null && !(row[3]+"").equals("")){
												if((row[3]+"").equals("avg")){
													sql_value += "avg(l.hida_corrValue) ";
												}else if((row[3]+"").equals("max")){
													sql_value += "max(l.hida_corrValue) ";
												}else if((row[3]+"").equals("min")){
													sql_value += "min(l.hida_corrValue) ";
												}else{
													sql_value += "l.hida_corrValue ";
												}
											}else{
												sql_value += "l.hida_corrValue ";
											}
											sql_value += "from gm_latestdata as l ";
											sql_value += "where l.ch_id in(select c.ch_id from gm_channel as c where c.ch_state=1 and c.scene_id='"+scene_id+"' ";
											if(row[0]!=null){
												sql_value += "and c.chtype_id='"+row[0]+"' ";
											}else{
												sql_value += "and c.chtype_id is null' ";
											}
											if(row[1]!=null && !(row[1]+"").equals("")){
												sql_value += "and c.ch_seat_no='"+row[1]+"' ";
											}
											if(row[2]!=null && !(row[2]+"").equals("")){
												sql_value += "and c.ch_depth='"+row[2]+"' ";
											}
											sql_value += ") ";
											if(row[3]!=null && !(row[3]+"").equals("")){
												if((row[3]+"").equals("avg")){
													sql_value += "and (l.hida_record_time>='"+beginTime+"' and l.hida_record_time<='"+endTime+"')";
												}else if((row[3]+"").equals("max")){
													//sql_value += "max(l.hida_corrValue) ";
												}else if((row[3]+"").equals("min")){
													//sql_value += "min(l.hida_corrValue) ";
												}else{
													sql_value += "order by l.hida_record_time desc limit 0,1 ";
												}
											}else{
												sql_value += "order by l.hida_record_time desc limit 0,1 ";
											}
											query = getSession().createSQLQuery(sql_value);
											List<String> value_list = query.list();
											if(value_list.size()>0){
												if(value_list.get(0)!=null){
													json += String.valueOf(value_list.get(0));
												}
											}
											json += "',";												
										}
										json = json.substring(0,(json.length()-1));
										json += "]";
										json += "},";
									}
								}
							}
						}
						json = json.substring(0,(json.length()-1));
						json += "]";
						json += "},";
					}	
				}
			}
			if(json.length()>1){
				json = json.substring(0,(json.length()-1));
			}
			json += "]";
			//System.out.println("数据汇总："+json);
			return json;
		}catch(Exception ex){ex.printStackTrace();return "[]";}		
	}
	
	/**
	 * 数据汇总 0708
	 * @return json
	 * 			[	
	 * 				{
	 * 					scene_gtype:'',
	 * 					head:
	 * 						[
	 * 							'xx','xxx','xxxx'
	 * 						],
	 * 					scenes:
	 * 						[
	 * 							null, 						
	 * 							{
	 * 								scene_name:'',
	 * 								time:'yyyy-MM-dd HH:mm:ss',
	 * 								data:
	 * 									[
	 * 										'21','23','','23'
	 * 									]
	 * 							},
	 * 							...
	 * 						]
	 * 				},
	 *				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String data_collect_0708(List<String> scene_id_arr){
		try{
			//--时间检查
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date currDate = new Date();
			String beginTime = df.format(currDate.getTime()-1000l*60l*60l*24l);
			String endTime = df.format(currDate.getTime()+1000l*60l*60l*1l);
			//--
			String scene_id_arr_str = "";
			for(String scene_id : scene_id_arr){
				scene_id_arr_str += "'"+scene_id+"',";
			}
			String sql_gtype = "select s.scene_gtype from op_scene as s where s.scene_id in('-1',";
			sql_gtype += scene_id_arr_str;
			sql_gtype = sql_gtype.substring(0,(sql_gtype.length()-1));
			sql_gtype += ") ";
			sql_gtype += "group by s.scene_gtype ";
			List<Integer> scene_gtype_list = new ArrayList<Integer>();
			SQLQuery query = getSession().createSQLQuery(sql_gtype);
			scene_gtype_list = query.list();
			String json = "[";
			if(scene_gtype_list.size()>0){				
				for(Integer scene_gtype : scene_gtype_list){
					//System.out.println("scene_gtype:"+scene_gtype);					
					String sql_head = "select cho.chtype_id,cho.ch_seat_no,cho.ch_depth,cho.cho_datetype,cho.cho_rowname from op_chtypeoperate as cho ";
					sql_head += "where cho.scene_gtype="+scene_gtype+" ";
					sql_head += "order by cho.cho_sequence asc";
					query = getSession().createSQLQuery(sql_head);
					List<Object []> op_ChtypeOperate_ob_list_head = query.list();
					if(op_ChtypeOperate_ob_list_head.size()>0){//没数据是0行嘛？一会试试
						json += "{scene_gtype:'"+scene_gtype+"',";						
						json += "head:['场景名称','采集时间',";
						for(Object[] row : op_ChtypeOperate_ob_list_head){
							/**
							Op_ChannelType op_ChannelType = null;							
							if(row[0]!=null){
								System.out.println(row[0]+"");
								op_ChannelType = this.op_ChannelTypeDao.findById(String.valueOf(row[0]));
							}
							*/
							json += "'";
							/**
							if(row[1]!=null && !(row[1]+"").equals("")){
								json += ""+row[1]+"_";
							}
							if(row[2]!=null && !(row[2]+"").equals("")){
								json += ""+row[2]+"_";
							}
							if(op_ChannelType!=null){								
								json += ""+op_ChannelType.getChtype_displayName()+"_";
							}
							if(row[3]!=null && !(row[3]+"").equals("")){
								if((row[3]+"").equals("avg")){
									json += "平均值_";
								}else if((row[3]+"").equals("max")){
									json += "最大值_";
								}else if((row[3]+"").equals("min")){
									json += "最小值_";
								}
							}
							json = json.substring(0,(json.length()-1));
							*/
							String cho_rowname = "???";
							if(row[4]!=null){
								//System.out.println(row[4]+"");
								cho_rowname = row[4]+"";
							}
							json += cho_rowname;
							json += "',";
						}
						json = json.substring(0,(json.length()-1));
						json += "],";
						json += "scenes:[null,";
						String sql_scenes = "select s.scene_id from op_scene as s ";
						sql_scenes += "where s.scene_gtype="+scene_gtype+" ";
						sql_scenes += "and s.scene_id in('-1',";
						sql_scenes += scene_id_arr_str;
						sql_scenes = sql_scenes.substring(0,(sql_scenes.length()-1));
						sql_scenes += ")";
						query = getSession().createSQLQuery(sql_scenes);
						List<String> scene_id_list_bygtype = query.list();
						if(scene_id_list_bygtype.size()>0){
							for(String scene_id : scene_id_list_bygtype){
								if(scene_id!=null){									
									Op_Scene op_Scene = this.op_SceneDao.findById(scene_id);
									if(op_Scene!=null){
										json += "{";
										json += "scene_name:'"+op_Scene.getScene_name()+"',";
										String time = "";
										String sql_time = "select l.hida_record_time from gm_latestdata as l ";
										sql_time += "where l.ch_id in(select c.ch_id from gm_channel as c where c.ch_state=1 and c.scene_id='"+scene_id+"') ";
										sql_time += "order by l.hida_record_time desc ";
										sql_time += "limit 0,1";
										query = getSession().createSQLQuery(sql_time);
										List<String> time_list = query.list();
										if(time_list.size()>0){
											if(time_list.get(0)!=null){
												time = df.format(time_list.get(0));
											}
										}
										json += "time:'"+time+"',";
										json += "data:[";
										for(Object[] row : op_ChtypeOperate_ob_list_head){
											if(row[1]!=null && !(row[1]+"").equals("")){
												//json += ""+row[1]+"_";
											}
											if(row[2]!=null && !(row[2]+"").equals("")){
												//json += "'"+row[2]+"_";
											}

											json += "'";
											String value = "";
											
											String sql_value = "select ";
											/**
											if(row[3]!=null && !(row[3]+"").equals("")){
												if((row[3]+"").equals("avg")){
													sql_value += "avg(l.hida_corrValue) ";
												}else if((row[3]+"").equals("max")){
													sql_value += "max(l.hida_corrValue) ";
												}else if((row[3]+"").equals("min")){
													sql_value += "min(l.hida_corrValue) ";
												}else{
													sql_value += "l.hida_corrValue ";
												}
											}else{
											*/
											//	sql_value += "l.hida_corrValue ";
											//}
											sql_value += "l.hida_corrValue,";
											sql_value += "(select cc.ch_dectDig from gm_channel as cc where cc.ch_id=l.ch_id) ";
											sql_value += "from gm_latestdata as l ";
											sql_value += "where l.ch_id in(select c.ch_id from gm_channel as c where c.ch_state=1 and c.scene_id='"+scene_id+"' ";
											if(row[0]!=null){
												sql_value += "and c.chtype_id='"+row[0]+"' ";												
											}else{
												sql_value += "and c.chtype_id is null' ";
											}
											if(row[1]!=null){
												sql_value += "and c.ch_seat_no='"+row[1]+"' ";
											}else{
												sql_value += "and c.ch_seat_no is null ";
											}
											if(row[2]!=null){
												sql_value += "and c.ch_depth='"+row[2]+"' ";
											}else{
												sql_value += "and c.ch_depth is null ";
											}
											if(row[3]!=null){
												Integer row3 = Integer.parseInt(String.valueOf(row[3]));
												//if(row3.equals(1) || row3.equals(2) || row3.equals(3)){
												sql_value += "and c.ch_datetype="+row[3]+" ";
												//}else{}
											}else{
												sql_value += "and c.ch_datetype is null ";
											}											
											/**
											if(row[1]!=null && !(row[1]+"").equals("")){
												sql_value += "and c.ch_seat_no='"+row[1]+"' ";
											}
											if(row[2]!=null && !(row[2]+"").equals("")){
												sql_value += "and c.ch_depth='"+row[2]+"' ";
											}
											*/
											sql_value += ") ";											
											sql_value += "order by l.hida_record_time desc limit 0,1 ";
											
											query = getSession().createSQLQuery(sql_value);
											List<Object[]> value_list = query.list();
											if(value_list.size()>0){
												if(value_list.get(0)!=null){
													Object value_o = value_list.get(0)[0];
													Double value_d = null;
													if(value_o!=null){
														value_d = Double.parseDouble(String.valueOf(value_o));
													}
													Object ff_o = value_list.get(0)[1];
													Integer ff_i = null;
													if(ff_o!=null){
														ff_i = Integer.parseInt(String.valueOf(ff_o));
													}
													String result = DecimalUtils.subDecimal(value_d, ff_i);
													if(result!=null){
														json += result;
													}else{}	
												}
											}
											json += "',";												
										}
										json = json.substring(0,(json.length()-1));
										json += "]";
										json += "},";
									}
								}
							}
						}
						json = json.substring(0,(json.length()-1));
						json += "]";
						json += "},";
					}	
				}
			}
			if(json.length()>1){
				json = json.substring(0,(json.length()-1));
			}
			json += "]";
			//System.out.println("数据汇总："+json);
			return json;
		}catch(Exception ex){ex.printStackTrace();return "[]";}		
	}
}
