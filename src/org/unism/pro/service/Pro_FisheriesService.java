package org.unism.pro.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevCtrlOperate;
import org.unism.gm.domain.Gm_DevCtrlOperateHistory;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.gm.service.Gm_DevCtrlOperateHistoryService;
import org.unism.gm.service.Gm_DevCtrlOperateService;
import org.unism.gm.service.Gm_LatestdataService;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.Op_SceneService;
import org.unism.phone.domain.TblReceive;
import org.unism.phone.domain.TblSendSMS;
import org.unism.phone.service.TblReceiveService;
import org.unism.phone.service.TblSendSMSService;
import org.unism.pro.dao.Pro_FisheriesDao;
import org.unism.pro.domain.Pro_Fisheries;
import org.unism.util.Fisheries_Message_Map;
import org.unism.util.Fisheries_Message_Map2;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Pro_FisheriesService extends BaseService<Pro_Fisheries, String> {
	
	@Autowired Pro_FisheriesDao pro_FisheriesDao;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Gm_LatestdataService gm_LatestdataService;
	@Autowired TblSendSMSService tblSendSMSService;
	@Autowired TblReceiveService tblReceiveService;
	@Autowired Gm_DevCtrlOperateService gm_DevCtrlOperateService;
	@Autowired Gm_DevCtrlOperateHistoryService gm_DevCtrlOperateHistoryService;
	@Autowired Fisheries_Message_Map fisheries_Message_Map;
	@Autowired Fisheries_Message_Map2 fisheries_Message_Map2;
	
	@Override
	protected IBaseDao<Pro_Fisheries, String> getEntityDao() {
		return pro_FisheriesDao;
	}
	
	public Pro_Fisheries findByScene_id(String sceneId){
		Search search = new Search();
		Filter filter = Filter.equal("scene.scene_id",sceneId);
		search.addFilter(filter);
		List<Pro_Fisheries> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}		
		return null;
	}
	

	
	public Pro_Fisheries findByfi_pondNoAndfi_phone(String phone,String pondNo){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("fi_pondNo", pondNo),Filter.equal("fi_phone", phone));
		search.addFilter(filter);
		List<Pro_Fisheries> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}		
		return null;
	}
	
	/**
	 * 养殖池 短信预警
	 * @author Wang_Yuliang
	 */
	public void 养殖池_短信预警(){
		List<Pro_Fisheries> pro_Fisheries_list = this.pro_FisheriesDao.findAll();
		for(Pro_Fisheries pro_Fisheries : pro_Fisheries_list){
			if(pro_Fisheries!=null){
				Op_Scene op_Scene = pro_Fisheries.getScene();
				if(op_Scene != null){
					if(op_Scene.getScene_state() == 1){
						List<String> scene_id_list = new ArrayList<String>();
						scene_id_list.add(op_Scene.getScene_id());
						scene_id_list = this.op_SceneService.findSceneTree_wangyuliang(scene_id_list);
						
						Double min = this.gm_LatestdataService.findMinByScene_id_listAndChtype_noAndCh_offerSer(scene_id_list,"DO10-O");
						if(min!=null){
							try{
								Double fi_doyj = Double.parseDouble(pro_Fisheries.getFi_doyj());
								Double fi_do = Double.parseDouble(pro_Fisheries.getFi_do());
								Integer fi_state = pro_Fisheries.getFi_state();
								if(fi_doyj>fi_do){
									if(min>fi_do && min<=fi_doyj){
										if(fi_state==0){
											try{
												String MSM = null;
												Gm_DevCtrl gm_DevCtrl = pro_Fisheries.getDect_id();
												if(gm_DevCtrl!=null){
													if(gm_DevCtrl.getDect_id()!=null){
														MSM = pro_Fisheries.getFi_pondNo()+"养殖池溶氧偏低，建议开启增氧机，如果短信开启增氧机回复："+pro_Fisheries.getFi_pondNo()+"+1。";
													}else{
														MSM = pro_Fisheries.getFi_pondNo()+"养殖池溶氧偏低，建议手动开启增氧机。";
													}													
												}else{
													MSM = pro_Fisheries.getFi_pondNo()+"养殖池溶氧偏低，建议手动开启增氧机。";
												}												
												//发短信
												TblSendSMS tblSendSMS = new TblSendSMS();
												tblSendSMS.setBhz(0);
												tblSendSMS.setDd(new Date());
												tblSendSMS.setFlag("0");
												tblSendSMS.setMobileNumber(pro_Fisheries.getFi_phone());
												tblSendSMS.setSendmsg(MSM);
												this.tblSendSMSService.save(tblSendSMS);
												//状态改为1
												pro_Fisheries.setFi_state(1);
												this.pro_FisheriesDao.update(pro_Fisheries);
											}catch(Exception ex){ex.printStackTrace();
											}	
										}else if(fi_state==1){
											try{

											}catch(Exception ex){ex.printStackTrace();
											}	
										}else{
											pro_Fisheries.setFi_state(0);
											this.pro_FisheriesDao.update(pro_Fisheries);
										}
									}else if(min>fi_doyj){
										if(fi_state==0){
											try{


											}catch(Exception ex){ex.printStackTrace();
											}
										}else if(fi_state==1){
											try{
												pro_Fisheries.setFi_state(0);
												this.pro_FisheriesDao.update(pro_Fisheries);
											}catch(Exception ex){ex.printStackTrace();
											}
										}else{
											pro_Fisheries.setFi_state(0);
											this.pro_FisheriesDao.update(pro_Fisheries);
										}
									}else if(min<=fi_do){
										if(fi_state==0){
											try{
												String MSM = null;
												Gm_DevCtrl gm_DevCtrl = pro_Fisheries.getDect_id();
												if(gm_DevCtrl!=null){
													if(gm_DevCtrl.getDect_id()!=null){
														MSM = pro_Fisheries.getFi_pondNo()+"养殖池严重缺氧，建议开启增氧机，如果短信开启增氧机回复："+pro_Fisheries.getFi_pondNo()+"+1。";
													}else{
														MSM = pro_Fisheries.getFi_pondNo()+"养殖池严重缺氧，建议手动开启增氧机。";
													}													
												}else{
													MSM = pro_Fisheries.getFi_pondNo()+"养殖池严重缺氧，建议手动开启增氧机。";
												}
												//发短信
												TblSendSMS tblSendSMS = new TblSendSMS();
												tblSendSMS.setBhz(0);
												tblSendSMS.setDd(new Date());
												tblSendSMS.setFlag("0");
												tblSendSMS.setMobileNumber(pro_Fisheries.getFi_phone());
												tblSendSMS.setSendmsg(MSM);
												this.tblSendSMSService.save(tblSendSMS);												
												//放入重发队列
												fisheries_Message_Map.putMessage(pro_Fisheries.getFi_id(),tblSendSMS);			
												//状态改为1
												pro_Fisheries.setFi_state(1);
												this.pro_FisheriesDao.update(pro_Fisheries);	
											}catch(Exception ex){ex.printStackTrace();
											}
										}else if(fi_state==1){
											try{
									
											}catch(Exception ex){ex.printStackTrace();
											}
										}else{
											pro_Fisheries.setFi_state(0);
											this.pro_FisheriesDao.update(pro_Fisheries);
										}
									}else{
									}
								}	
							}catch(Exception ex){ex.printStackTrace();}
						}
					}
				}
			}			
		}
	}

	/**
	 * 养殖池 短信重发
	 * @author Wang_Yuliang
	 */
	public void 养殖池_短信重发(){
		Map<String, TblSendSMS> map = fisheries_Message_Map.getMessageMap();
		if(map!=null){
			Iterator it = map.entrySet().iterator();
			if(it!=null){
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					if(entry!=null){
						Object key = entry.getKey();
						Object value = entry.getValue();
						try{
							if(value!=null){
								TblSendSMS tblSendSMS = (TblSendSMS)value;
								this.tblSendSMSService.save(tblSendSMS);
							}
						}catch(Exception ex){ex.printStackTrace();
						}
					}
				}
			}
		}	
	}
	
	/**
	 * 养殖池 短信回复
	 * @author Wang_Yuliang
	 */
	public void 养殖池_短信回复(){
		List<TblReceive> tblReceive_list = this.tblReceiveService.findByFlag();
		for(TblReceive tblReceive : tblReceive_list){
			if(tblReceive!=null && tblReceive.getId()!=null){
				String receivemsg = tblReceive.getReceivemsg();
				String mobileNumber = tblReceive.getMobileNumber();
				if(receivemsg!=null && !receivemsg.equals("")){
					if(receivemsg.indexOf("+")>0){
						String fi_pondNo = receivemsg.substring(0,receivemsg.indexOf("+"));
						if(fi_pondNo != null && !fi_pondNo.equals("")){
							if(mobileNumber != null && !mobileNumber.equals("")){
								Pro_Fisheries pro_Fisheries = this.findByFi_pondNoAndFi_phone_wangyuliang(fi_pondNo, mobileNumber);
								if(pro_Fisheries != null && pro_Fisheries.getFi_id() != null){
									TblSendSMS tblSendSMS = fisheries_Message_Map.getSendMessage(pro_Fisheries.getFi_id());
									if(tblSendSMS == null){
										//一种情况为：溶氧偏低
										String dect_id = pro_Fisheries.getDect_id().getDect_id();
										if(dect_id != null && !dect_id.equals("")){
											Gm_DevCtrlOperate gm_DevCtrlOperate = this.gm_DevCtrlOperateService.findByDect_id(dect_id);
											if(gm_DevCtrlOperate!=null && gm_DevCtrlOperate.getDeco_id()!=null){
												Integer deco_state = gm_DevCtrlOperate.getDeco_state();
												if(deco_state!=null){
													TblSendSMS rs_tblSendSMS = new TblSendSMS();
													if(deco_state == 0){
														String deco_userId = mobileNumber;
														String deco_userName = mobileNumber;
														Date deco_time = new Date();
														gm_DevCtrlOperate.setDeco_userId(deco_userId);
														gm_DevCtrlOperate.setDeco_userName(deco_userName);
														gm_DevCtrlOperate.setDeco_type(1);
														gm_DevCtrlOperate.setDeco_time(deco_time);
														gm_DevCtrlOperate.setDeco_state(1);
														gm_DevCtrlOperate.setDeco_sort(0);
														gm_DevCtrlOperate.setDeco_userIp(null);
														gm_DevCtrlOperate.setDeco_userType(0);
														this.gm_DevCtrlOperateService.update(gm_DevCtrlOperate);
														Gm_DevCtrlOperateHistory gm_DevCtrlOperateHistory = new Gm_DevCtrlOperateHistory();
														gm_DevCtrlOperateHistory.setDect_id(gm_DevCtrlOperate.getDect_id());
														gm_DevCtrlOperateHistory.setDeco_userId(deco_userId);
														gm_DevCtrlOperateHistory.setDeco_userName(deco_userName);
														gm_DevCtrlOperateHistory.setDeco_userType(1);
														gm_DevCtrlOperateHistory.setDeco_type(1);
														gm_DevCtrlOperateHistory.setDeco_time(deco_time);
														gm_DevCtrlOperateHistory.setDeco_userIp(null);
														this.gm_DevCtrlOperateHistoryService.save(gm_DevCtrlOperateHistory);
														//发短信
														rs_tblSendSMS.setBhz(0);
														rs_tblSendSMS.setDd(new Date());
														rs_tblSendSMS.setFlag("0");
														rs_tblSendSMS.setMobileNumber(mobileNumber);
														rs_tblSendSMS.setSendmsg("请求已发送");
														this.tblSendSMSService.save(rs_tblSendSMS);
													}else{
														//发短信
														rs_tblSendSMS.setBhz(0);
														rs_tblSendSMS.setDd(new Date());
														rs_tblSendSMS.setFlag("0");
														rs_tblSendSMS.setMobileNumber(mobileNumber);
														rs_tblSendSMS.setSendmsg("当前设备已被其他用户控制，请稍后再试！");
														this.tblSendSMSService.save(rs_tblSendSMS);
													}
												}
											}
										}
									}else{
										//一种情况为：严重缺氧
										if(receivemsg.substring(receivemsg.indexOf("+")+1).equals("知道了")){//如果回复内容为《养殖池编号》+知道了，
											//将此养殖池的信息从重发队列1中删除，不在给此养殖池的用户发短信。
											fisheries_Message_Map.removeMessage(pro_Fisheries.getFi_id());
											//然后将此养殖池的信息放入另一个重发队列2中，
											fisheries_Message_Map2.putMessage(pro_Fisheries.getFi_id(),pro_Fisheries);
										}else if(receivemsg.substring(receivemsg.indexOf("+")+1).equals("1")){//如果回复内容为《养殖池编号》+1，
											//根据短信回复的《养殖池编号》和手机号去水产应用配置表中查询出控制设备的ID，
											String dect_id = pro_Fisheries.getDect_id().getDect_id();
											if(dect_id != null && !dect_id.equals("")){
												//去更改控制设备操作表。
												Gm_DevCtrlOperate gm_DevCtrlOperate = this.gm_DevCtrlOperateService.findByDect_id(dect_id);
												if(gm_DevCtrlOperate!=null && gm_DevCtrlOperate.getDeco_id()!=null){
													Integer deco_state = gm_DevCtrlOperate.getDeco_state();
													if(deco_state!=null){
														TblSendSMS rs_tblSendSMS = new TblSendSMS();
														if(deco_state == 0){
															String deco_userId = mobileNumber;
															String deco_userName = mobileNumber;
															Date deco_time = new Date();
															gm_DevCtrlOperate.setDeco_userId(deco_userId);
															gm_DevCtrlOperate.setDeco_userName(deco_userName);
															gm_DevCtrlOperate.setDeco_type(1);
															gm_DevCtrlOperate.setDeco_time(deco_time);
															gm_DevCtrlOperate.setDeco_state(1);
															gm_DevCtrlOperate.setDeco_sort(0);
															gm_DevCtrlOperate.setDeco_userIp(null);
															gm_DevCtrlOperate.setDeco_userType(0);
															this.gm_DevCtrlOperateService.update(gm_DevCtrlOperate);
															Gm_DevCtrlOperateHistory gm_DevCtrlOperateHistory = new Gm_DevCtrlOperateHistory();
															gm_DevCtrlOperateHistory.setDect_id(gm_DevCtrlOperate.getDect_id());
															gm_DevCtrlOperateHistory.setDeco_userId(deco_userId);
															gm_DevCtrlOperateHistory.setDeco_userName(deco_userName);
															gm_DevCtrlOperateHistory.setDeco_userType(1);
															gm_DevCtrlOperateHistory.setDeco_type(1);
															gm_DevCtrlOperateHistory.setDeco_time(deco_time);
															gm_DevCtrlOperateHistory.setDeco_userIp(null);
															this.gm_DevCtrlOperateHistoryService.save(gm_DevCtrlOperateHistory);
															//发短信
															rs_tblSendSMS.setBhz(0);
															rs_tblSendSMS.setDd(new Date());
															rs_tblSendSMS.setFlag("0");
															rs_tblSendSMS.setMobileNumber(mobileNumber);
															rs_tblSendSMS.setSendmsg("请求已发送");
															this.tblSendSMSService.save(rs_tblSendSMS);
															//将此养殖池的信息从重发队列1中删除，不再给此养殖池的用户发短信。
															fisheries_Message_Map.removeMessage(pro_Fisheries.getFi_id());
															//然后将此养殖池的信息放入另一个重发队列2中，
															fisheries_Message_Map2.putMessage(pro_Fisheries.getFi_id(),pro_Fisheries);
														}else{
															//发短信
															rs_tblSendSMS.setBhz(0);
															rs_tblSendSMS.setDd(new Date());
															rs_tblSendSMS.setFlag("0");
															rs_tblSendSMS.setMobileNumber(mobileNumber);
															rs_tblSendSMS.setSendmsg("当前设备已被其他用户控制，请稍后再试！");
															this.tblSendSMSService.save(rs_tblSendSMS);
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				tblReceive.setFlag("1");
				this.tblReceiveService.update(tblReceive);
			}
		}		
	}
	
	/**
	 * 养殖池 短信重发2
	 * @author Wang_Yuliang
	 */
	public void 养殖池_短信重发2(){
		//每隔30分钟根据养殖池的编号查出当前养殖池的溶氧值，发短信给养殖池的用户。短信内容：《养殖池编号》养殖池目前的溶氧值为《溶氧值》。
		Map<String, Pro_Fisheries> map = fisheries_Message_Map2.getMessageMap();
		if(map!=null){
			Iterator it = map.entrySet().iterator();
			if(it!=null){
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					if(entry!=null){
						Object key = entry.getKey();
						Object value = entry.getValue();
						if(value!=null){
							Pro_Fisheries pro_Fisheries = (Pro_Fisheries)value;
							if(pro_Fisheries!=null){
								Op_Scene op_Scene = null;
								if(pro_Fisheries.getScene()!=null){
									String scene_id = pro_Fisheries.getScene().getScene_id();
									if(scene_id!=null){
										op_Scene = this.op_SceneService.findById(scene_id);
									}
								}
								if(op_Scene != null){
									if(op_Scene.getScene_state() == 1){
										List<String> scene_id_list = new ArrayList<String>();
										scene_id_list.add(op_Scene.getScene_id());
										scene_id_list = this.op_SceneService.findSceneTree_wangyuliang(scene_id_list);
										Double min = this.gm_LatestdataService.findMinByScene_id_listAndChtype_noAndCh_offerSer(scene_id_list,"DO10-O");
										if(min!=null){
											//发短信
											TblSendSMS tblSendSMS = new TblSendSMS();
											tblSendSMS.setBhz(0);
											tblSendSMS.setDd(new Date());
											tblSendSMS.setFlag("0");
											tblSendSMS.setMobileNumber(pro_Fisheries.getFi_phone());
											tblSendSMS.setSendmsg(pro_Fisheries.getFi_pondNo()+"养殖池目前的溶氧值为"+min);
											this.tblSendSMSService.save(tblSendSMS);
											//如果查出的溶氧值大于预警值先发短信，然后将此养殖池的信息从重发队列2中删除。
											try{	
												Double fi_doyj = Double.parseDouble(pro_Fisheries.getFi_doyj());	
												if(fi_doyj!=null){
													if(min>fi_doyj){
														fisheries_Message_Map2.removeMessage(pro_Fisheries.getFi_id());	
													}
												}
											}catch(Exception ex){ex.printStackTrace();}	
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 指定养殖池编号，手机号，查询养殖池信息 输入的手机号只要包含字段值即可匹配
	 * @return
	 * @author Wang_Yuliang
	 */
	public Pro_Fisheries findByFi_pondNoAndFi_phone_wangyuliang(String fi_pondNo, String fi_phone){
		return this.pro_FisheriesDao.findByFi_pondNoAndFi_phone(fi_pondNo, fi_phone);
	}
	
	

	public List<Pro_Fisheries> findPro_FisheriesByScene_id(List<String> scene_idList)
	{
		return this.pro_FisheriesDao.findPro_FisheriesByScene_id(scene_idList);
	}
	
	/**
	 * 查询溶解氧
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findLatestDataByUserId(String userId) {
		return pro_FisheriesDao.findSceneFishDataByUserId(userId);
	}
	
	/**
	 * 查询气象站和视频点
	 */
	public List<Object[]> findWeatherVidioSceneByUserId(String userId) {
		return pro_FisheriesDao.findSceneWeatherVidioByUserId(userId);
	}
	
	/**
	 * 查询气象站数据
	 */
	public List<Object[]> findWeatherDataBySceneId(String sceneId) {
		return pro_FisheriesDao.findWeatherDataBySceneId(sceneId);
	}
	
	/**
	 * 
	 * @return
	 */
	
	public List<Object[]> findAllFisheriesMsg(String userId){
		return this.pro_FisheriesDao.findAllFisheriesMsg(userId);
	}
	
}
