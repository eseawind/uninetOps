package org.unism.gm.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.dao.Gm_ChannelDao;
import org.unism.gm.dao.Gm_DevChannelDao;
import org.unism.gm.dao.Gm_DevCtrlDao;
import org.unism.gm.dao.Gm_DevNetDao;
import org.unism.gm.dao.Gm_DeviceDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.op.dao.Op_UserInfo_SceneDao;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
import org.wangzz.core.web.struts2.Struts2Utils;

@Service
@SuppressWarnings("unchecked")
public class Gm_DeviceService extends BaseService<Gm_Device, String> {
	
	@Autowired Gm_DeviceDao gm_DeviceDao;
	@Autowired Gm_DevNetDao gm_DevNetDao;
	@Autowired Op_UserInfo_SceneDao op_UserInfo_SceneDao;
	@Autowired Gm_ChannelDao gm_ChannelDao;
	@Autowired Gm_DevChannelDao gm_DevChannelDao;
	@Autowired Gm_DevCtrlDao gm_DevCtrlDao;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_DevNetService gm_DevNetService;
//	@Autowired Op_DevCtrlBtnService op_DevCtrlBtnService;
	
	@Override
	protected IBaseDao<Gm_Device, String> getEntityDao() {
		return gm_DeviceDao;
	}
	
	public String findDeviceTreeBySceneList(List<Op_Scene> sceneList) {
		String deviceTree = "";
		StringBuffer buffer = new StringBuffer("[");
		if(sceneList.size() > 0) {
			Search searchNet = new Search();
			searchNet.addFilterIn("dev_id.scene_id", sceneList);
			searchNet.addFilterEqual("net_state", 1);
			List<Gm_DevNet> devNetList = gm_DevNetDao.search(searchNet);
			
			Search searchChl = new Search();
	    	searchChl.addFilterEqual("ch_state", 1);
	    	searchChl.addFilterIn("scene_id", sceneList);
	    	List<Gm_Channel> gm_channel_list = this.gm_ChannelDao.search(searchChl);
	    	
			for(Op_Scene scene : sceneList) {
				String url = "javascript:window.parent.document.getElementById('centerFrame').contentWindow.echoSceneTree('"+scene.getScene_id()+"','"+scene.getScene_name()+"','"+scene.getScene_no()+"','" + scene.getScene_rank()+"','" + scene.getScene_gtype() + "','"+scene.getScene_videoUrl()+"');";
				if(scene.getScene_gtype() == 97){
					url = scene.getScene_videoUrl();
				}
				if(scene.getScene_pid() == null){
					buffer.append("{type:\"scene\",node:\"s_").append(scene.getScene_id()).append("\",pnode:\"0\",text:\"").append(scene.getScene_addr()).append("-").append(scene.getScene_name()).append("\",url:\"").append(url).append("\",scene_rank:\"").append(scene.getScene_rank()).append("\"},");
				} else {
					buffer.append("{type:\"scene\",node:\"s_").append(scene.getScene_id()).append("\",pnode:\"s_").append(scene.getScene_pid()).append("\",text:\"").append(scene.getScene_addr()).append("-").append(scene.getScene_name()).append("\",url:\"").append(url).append("\",scene_rank:\"-1\"},");
				}
				for(Gm_DevNet devNet : devNetList) {
					if(scene.getScene_id().equals(devNet.getDev_id().getScene_id().getScene_id())) {
						if(devNet.getNet_pid() == null){
							buffer.append("{type:\"").append(devNet.getNet_role()).append("\",node:\"e_").append(devNet.getNet_id()).append("\",pnode:\"s_").append(devNet.getDev_id().getScene_id().getScene_id()).append("\",text:\"").append(devNet.getNet_addr()).append("\",url:\"javascript:window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('").append(devNet.getNet_id()).append("','").append(devNet.getNet_addr()).append("','").append(devNet.getNet_no()).append("','").append(devNet.getNet_type()).append("')\",scene_rank:\"-1\"},");	
				    	} else {
				    		buffer.append("{type:\"").append(devNet.getNet_role()).append("\",node:\"e_").append(devNet.getNet_id()).append("\",pnode:\"e_").append(devNet.getNet_pid()).append("\",text:\"").append(devNet.getNet_addr()).append("\",url:\"javascript:window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('").append(devNet.getNet_id()).append("','").append(devNet.getNet_addr()).append("','").append(devNet.getNet_no()).append("','").append(devNet.getNet_type()).append("')\",scene_rank:\"-1\"},");			    
				    	}
						for(Gm_Channel gm_Channel : gm_channel_list){
							if(gm_Channel.getDev_collectId().getDev_id().equals(devNet.getDev_id().getDev_id())) {
								buffer.append("{type:\"channel\",node:\"c_").append(gm_Channel.getCh_id()).append("\",pnode:\"e_").append(devNet.getNet_id()).append("\",text:\"").append(gm_Channel.getCh_name()).append("\",url:\"#\",scene_rank:\"-1\"},");	
							}
				    	}
					}
				}
			}
			deviceTree = buffer.toString();
			deviceTree = deviceTree.substring(0, deviceTree.length() - 1);
		}
		return deviceTree + "]";
	}

	/**
	 * 根据用户信息 查找设备树 json
	 * @param op_UserInfo
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findDeviceTree(Op_UserInfo op_UserInfo){
		String device_tree = "[";
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.op_UserInfo_SceneDao.findAllEq("user_id.user_id", op_UserInfo.getUser_id());
		List<Op_Scene> op_Scene_list = new ArrayList<Op_Scene>();
		for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
			if(op_UserInfo_Scene.getScene_id().getScene_state() == 1){
				op_Scene_list.add(op_UserInfo_Scene.getScene_id());
			}
		}
		if(op_Scene_list.size()>0){
			for(Op_Scene op_Scene : op_Scene_list){
				//....
				String url = "javascript:window.parent.document.getElementById('centerFrame').contentWindow.echoSceneTree('"+op_Scene.getScene_id()+"','"+op_Scene.getScene_name()+"','"+op_Scene.getScene_no()+"','" + op_Scene.getScene_rank()+"','" + op_Scene.getScene_gtype() + "','"+op_Scene.getScene_videoUrl()+"');";
				if(op_Scene.getScene_gtype() == 97){
					url = op_Scene.getScene_videoUrl();
				}
				//....唉 623**需求
				if(op_Scene.getScene_pid() == null){
					device_tree += "{type:\"scene\",node:\"s_" + op_Scene.getScene_id() + "\",pnode:\"0\",text:\"" + op_Scene.getScene_addr() + "-" + op_Scene.getScene_name() + "\",url:\""+url+"\",scene_rank:\"" + op_Scene.getScene_rank() + "\"},";
				}else{
					device_tree += "{type:\"scene\",node:\"s_" + op_Scene.getScene_id() + "\",pnode:\"s_" + op_Scene.getScene_pid() + "\",text:\"" + op_Scene.getScene_addr() + "-" + op_Scene.getScene_name() + "\",url:\""+url+"\",scene_rank:\"-1\"},";
				}
			    List<Gm_DevNet> gm_DevNet_list = this.gm_DevNetDao.findAllEq("dev_id.scene_id.scene_id", op_Scene.getScene_id());
			    for(Gm_DevNet gm_DevNet : gm_DevNet_list){
			    	//if(gm_DevNet.getNet_state()!=null){
				    	if(gm_DevNet.getNet_state()==1){
					    	if(gm_DevNet.getNet_pid() == null){
					    		device_tree += "{type:\"" + gm_DevNet.getNet_role() + "\",node:\"e_" + gm_DevNet.getNet_id() + "\",pnode:\"s_" + gm_DevNet.getDev_id().getScene_id().getScene_id() + "\",text:\"" + gm_DevNet.getNet_addr() + "\",url:\"javascript:window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('"+gm_DevNet.getNet_id()+"','"+gm_DevNet.getNet_addr()+"','"+gm_DevNet.getNet_no()+"','"+gm_DevNet.getNet_type()+"')\",scene_rank:\"-1\"},";	
					    	}else{
					    		device_tree += "{type:\"" + gm_DevNet.getNet_role() + "\",node:\"e_" + gm_DevNet.getNet_id() + "\",pnode:\"e_" + gm_DevNet.getNet_pid() + "\",text:\"" + gm_DevNet.getNet_addr() + "\",url:\"javascript:window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('"+gm_DevNet.getNet_id()+"','"+gm_DevNet.getNet_addr()+"','"+gm_DevNet.getNet_no()+"','"+gm_DevNet.getNet_type()+"')\",scene_rank:\"-1\"},";			    
					    	}
					    	//Wang_Yuliang0615 UP List<Gm_Channel> gm_channel_list = this.gm_ChannelDao.findAllEq("dev_collectId.dev_id", gm_DevNet.getDev_id().getDev_id());
					    	Search search = new Search();
					    	Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("dev_collectId.dev_id", gm_DevNet.getDev_id().getDev_id()));
					    	search.addFilter(filter);
					    	List<Gm_Channel> gm_channel_list = this.gm_ChannelDao.search(search);
					    	//--end
					    	for(Gm_Channel gm_Channel : gm_channel_list){
					    		device_tree += "{type:\"channel\",node:\"c_" + gm_Channel.getCh_id() + "\",pnode:\"e_" + gm_DevNet.getNet_id() + "\",text:\"" + gm_Channel.getCh_name() + "\",url:\"#\",scene_rank:\"-1\"},";	
					    	}
		//			    	List<Gm_DevCtrl> gm_devCtrl_list = this.gm_DevCtrlDao.findAllEq("dev_id.dev_id", gm_DevNet.getDev_id().getDev_id());
		//			    	for(Gm_DevCtrl gm_DevCtrl : gm_devCtrl_list){
		//			    		device_tree += "{type:\"devctrl\",node:\"t_" + gm_DevCtrl.getDect_id() + "\",pnode:\"e_" + gm_DevNet.getNet_id() + "\",text:\"" + gm_DevCtrl.getDect_name() + "\",url:\"#\",scene_rank:\"-1\"},";	
		//			    	}
				    	}
			    	//}	
			    }	
			}
			device_tree = device_tree.substring(0,device_tree.length()-1);
		}
		return device_tree + "]";
	}
	
	/**
	 * 根据用户信息 查找设备树 止于GPRS
	 * @param op_UserInfo
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findDeviceTree_endsWithGPRS(Op_UserInfo op_UserInfo){
		String device_tree = "[";
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.op_UserInfo_SceneDao.findAllEq("user_id.user_id", op_UserInfo.getUser_id());
		List<Op_Scene> op_Scene_list = new ArrayList<Op_Scene>();
		for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
			if(op_UserInfo_Scene.getScene_id().getScene_state() == 1){
				op_Scene_list.add(op_UserInfo_Scene.getScene_id());
			}
		}
		if(op_Scene_list.size()>0){
			for(Op_Scene op_Scene : op_Scene_list){
				//....
				String click = "click:\"window.parent.document.getElementById('centerFrame').contentWindow.echoSceneTree('"+op_Scene.getScene_id()+"','"+op_Scene.getScene_name()+"','"+op_Scene.getScene_no()+"','" + op_Scene.getScene_rank()+"','" + op_Scene.getScene_gtype() + "','"+op_Scene.getScene_videoUrl()+"');\"";
				if(op_Scene.getScene_gtype() == 97){
					click = "url:\""+op_Scene.getScene_videoUrl()+"\"";
				}
				//....唉 623**需求
				if(op_Scene.getScene_pid() == null){
					device_tree += "{type:\"scene\",id:\"s_" + op_Scene.getScene_id() + "\",pid:\"0\",name:\"" + op_Scene.getScene_addr() + "-" + op_Scene.getScene_name() + "\","+click+",scene_rank:\"" + op_Scene.getScene_rank() + "\",isParent:true,target:\"_self\"},";
				}else{
					device_tree += "{type:\"scene\",id:\"s_" + op_Scene.getScene_id() + "\",pid:\"s_" + op_Scene.getScene_pid() + "\",name:\"" + op_Scene.getScene_addr() + "-" + op_Scene.getScene_name() + "\","+click+",scene_rank:\"-1\",isParent:true,target:\"_self\"},";
				}
				Search search_findGPRS = new Search();//有问题
				Filter filter_findGPRS = Filter.and(Filter.equal("net_state", 1),Filter.in("dev_id.dev_id", this.findDev_id_listByScene_id(op_Scene.getScene_id())),Filter.equal("net_type", 0));
				search_findGPRS.addFilter(filter_findGPRS);
				search_findGPRS.addSortAsc("net_addr");
			    List<Gm_DevNet> GPRS_LIST = this.gm_DevNetDao.search(search_findGPRS);
			    for(Gm_DevNet gm_DevNet : GPRS_LIST){
			    	device_tree += "{type:\"" + gm_DevNet.getNet_role() + "\",id:\"e_" + gm_DevNet.getNet_id() + "\",pid:\"s_" + gm_DevNet.getDev_id().getScene_id().getScene_id() + "\",name:\"" + gm_DevNet.getNet_addr() + "\",click:\"window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('"+gm_DevNet.getNet_id()+"','"+gm_DevNet.getNet_addr()+"','"+gm_DevNet.getNet_no()+"','"+gm_DevNet.getNet_type()+"')\",scene_rank:\"-1\",isParent:true, iconOpen:\"images/dtree/device.gif\",iconClose:\"images/dtree/device.gif\",target:\"_self\"},";	
			    }	
			}
			device_tree = device_tree.substring(0,device_tree.length()-1);
		}
		return device_tree + "]";
	}
	
	/**
	 * 根据用户信息 查找设备树 止于最小场景
	 * @param op_UserInfo
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findDeviceTree_endsWithMinScene(Op_UserInfo op_UserInfo){
		String device_tree = "[";
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.op_UserInfo_SceneDao.findAllEq("user_id.user_id", op_UserInfo.getUser_id());
		if(op_UserInfo_Scene_list.size()>0){
			for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
				Op_Scene op_Scene = op_UserInfo_Scene.getScene_id();
				Integer gtype = op_Scene.getScene_gtype();
				if(gtype != null){
					//....
					String click = "click:\"window.parent.document.getElementById('centerFrame').contentWindow.echoSceneTree('"+op_Scene.getScene_id()+"','"+op_Scene.getScene_name()+"','"+op_Scene.getScene_no()+"','" + op_Scene.getScene_rank()+"','" + op_Scene.getScene_gtype() + "','"+op_Scene.getScene_videoUrl()+"');\"";
					if(gtype == 97){
						click = "url:\""+op_Scene.getScene_videoUrl()+"\"";
					}
					//....唉 623**需求
					if(op_Scene.getScene_pid() == null){
						device_tree += "{type:\"scene\",id:\"s_" + op_Scene.getScene_id() + "\",pid:\"0\",name:\"" + op_Scene.getScene_addr() + "-" + op_Scene.getScene_name() + "\","+click+",scene_rank:\"" + op_Scene.getScene_rank() + "\",isParent:true,target:\"_self\"},";
					}//else{
						//device_tree += "{type:\"scene\",id:\"s_" + op_Scene.getScene_id() + "\",pid:\"s_" + op_Scene.getScene_pid() + "\",name:\"" + op_Scene.getScene_addr() + "-" + op_Scene.getScene_name() + "\","+click+",scene_rank:\"-1\",isParent:true,target:\"_self\"},";
					//}
				}
			}
			device_tree = device_tree.substring(0,device_tree.length()-1);
		}
		return device_tree + "]";
	}
	
	/**
	 * 据场景ID 查找在用设备
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_Device> findByScene_id(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_id.scene_id", scene_id),Filter.equal("dev_state", 1));
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 据场景ID 查找在用设备ID
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDev_id_listByScene_id(String scene_id){
		return this.gm_DeviceDao.findDev_id_listByScene_id(scene_id);
	}
	
	/**
	 * 通过用户ID 查找设备ID集合
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDev_idByUser_id(String user_id){
		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);		
		List<String> list = new ArrayList();
		//空集合会出错吗？
		list.add("-1");
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_state", 1),Filter.in("scene_id.scene_id", scene_id_list));
		search.addFilter(filter);
		List<Gm_Device> gm_device_list = this.search(search);
		for(Gm_Device gm_device : gm_device_list){
			list.add(gm_device.getDev_id());
		}
		return list;	
	} 
	
	/**
	 * 通过用户ID 查找设备集合
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_Device> findByUser_id(String user_id){
		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
		//空集合会出错吗？
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_state", 1),Filter.in("scene_id.scene_id", scene_id_list));
		search.addFilter(filter);
		List<Gm_Device> gm_device_list = this.search(search);
		return gm_device_list;	
	}
	
	/**
	 * 判断设备是否已经存在
	 * @param gm_Device
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isExist(Gm_Device gm_Device){
		List<Gm_Device> gm_Device_list = new ArrayList<Gm_Device>();
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_state", 1),Filter.equal("dev_no", gm_Device.getDev_no()));
		search.addFilter(filter);
		gm_Device_list = this.gm_DeviceDao.search(search);
		if(gm_Device_list.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * 判断设备编号是否重复
	 * @param gm_Device
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isExist_dev_no(String dev_no){
		List<Gm_Device> gm_Device_list = new ArrayList<Gm_Device>();
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_state", 1),Filter.equal("dev_no", dev_no));
		search.addFilter(filter);
		gm_Device_list = this.gm_DeviceDao.search(search);
		if(gm_Device_list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断设备序列号是否重复
	 * @param gm_Device
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isExist_dev_serial(String dev_serial){
		List<Gm_Device> gm_Device_list = new ArrayList<Gm_Device>();
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_state", 1),Filter.equal("dev_serial", dev_serial));
		search.addFilter(filter);
		gm_Device_list = this.gm_DeviceDao.search(search);
		if(gm_Device_list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断设备是否在网
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isExistNet(String dev_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_id.dev_id", dev_id),Filter.equal("net_state", 1));
		search.addFilter(filter);
		List list = this.gm_DevNetDao.search(search);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断设备是否已配置于通道中
	 * @param dev_id
	 * @return
	 */
	public boolean isExistChannel(String dev_id){
		Search search = new Search();
		Filter f1 = Filter.and(Filter.equal("dev_collectId.dev_id", dev_id),Filter.equal("ch_state", 1));
		Filter f2 = Filter.and(Filter.equal("dev_sensorId.dev_id", dev_id),Filter.equal("ch_state", 1));
		search.addFilter(f1);		
		List dev_collectId_list = this.gm_ChannelDao.search(search);
		search.removeFilter(f1);
		search.addFilter(f2);
		List dev_sensorId_list = this.gm_ChannelDao.search(search);
		if(dev_collectId_list.size()>0 || dev_sensorId_list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断设备是否已配置于控制设备信息中
	 * @param dev_id
	 * @return
	 */
	public boolean isExistDevCtrl(String dev_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_id.dev_id", dev_id),Filter.equal("dect_state", 1));
		search.addFilter(filter);
		List list = this.gm_DevCtrlDao.search(search);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断设备是否已配置到按钮中
	 * @param dev_id
	 * @return
	 */
	public boolean isExistBtn(String dev_id){
		Search search = new Search();
		Filter filter = Filter.equal("dev_id.dev_id", dev_id);
		search.addFilter(filter);
		List list = this.gm_DevCtrlDao.search(search);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 查找在网设备的ID
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDev_idFromDevNet(){
		return this.gm_DeviceDao.findDev_idFromDevNet();
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
		return this.gm_DeviceDao.page_wangzz(scene_id_arr,no,size,dev_addr);
	}
	
	/**
	 * 判断用户是否有操作指定设备信息的权限
	 * @param user_id
	 * @param dev_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isHasDevice(String user_id, String dev_id){
		return this.gm_DeviceDao.isHasDevice(user_id,dev_id);
	}
	
	/**
	 * 
	 */
	@Transactional
	public String manage_del(String dev_id) throws Exception{
		HttpSession session = Struts2Utils.getSession();
		String sql = "";
		String dev_id_arr_str = this.findAllDev_idByDev_id_wangyuliang(dev_id); 
		if("'-1'".equals(dev_id_arr_str)) {
			dev_id_arr_str = "'-1','"+dev_id+"'";
		}
		return dev_id_arr_str;
//		List<String> ch_id_str = gm_ChannelDao.findCh_idByDev_id_arr(dev_id_arr_str);
//		String ch_id_arr = "'-1',";
//		for(String str : ch_id_str){
//			ch_id_arr += "'" +str+"',";
//		}
//		ch_id_arr = ch_id_arr.substring(0,(ch_id_arr.length()-1));
//		
//		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
//		String dect_id_arr = "'-1',";
//		for(String str : dect_id_str){
//			dect_id_arr += "'" +str+"',";
//		}
//		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		
		//session.setAttribute("dev_id_arr_str", dev_id_arr_str);
		//session.setAttribute("ch_id_arr", ch_id_arr);
		//session.setAttribute("dect_id_arr", dect_id_arr);
	}
	
	
	/**
	 * 最新数据 删除
	 */
	@Transactional
	public void gm_Latestdata_del(String dev_id_arr_str) throws Exception{
		List<String> ch_id_str = gm_ChannelDao.findCh_idByDev_id_arr(dev_id_arr_str);
		String ch_id_arr = "'-1',";
		for(String str : ch_id_str){
			ch_id_arr += "'" +str+"',";
		}
		ch_id_arr = ch_id_arr.substring(0,(ch_id_arr.length()-1));
		
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.gm_Latestdata_del(ch_id_arr);
	}
	
	/**
	 * 历史数据 删除
	 */
	@Transactional
	public void gm_Historydata_del(String dev_id_arr_str) throws Exception{
		List<String> ch_id_str = gm_ChannelDao.findCh_idByDev_id_arr(dev_id_arr_str);
		String ch_id_arr = "'-1',";
		for(String str : ch_id_str){
			ch_id_arr += "'" +str+"',";
		}
		ch_id_arr = ch_id_arr.substring(0,(ch_id_arr.length()-1));
		this.gm_DeviceDao.gm_Historydata_del(ch_id_arr);
	}
	
	/**
	 * 设备上报通道配置 删除
	 */
	@Transactional
	public void gm_DevChannel_del(String dev_id_arr_str) throws Exception{
		List<String> ch_id_str = gm_ChannelDao.findCh_idByDev_id_arr(dev_id_arr_str);
		String ch_id_arr = "'-1',";
		for(String str : ch_id_str){
			ch_id_arr += "'" +str+"',";
		}
		ch_id_arr = ch_id_arr.substring(0,(ch_id_arr.length()-1));
		this.gm_DeviceDao.gm_DevChannel_del(dev_id_arr_str,ch_id_arr);
	}
	
	/**
	 * 故障信息 删除
	 */
	@Transactional
	public void gm_DevFault_del(String dev_id_arr_str) throws Exception{
		List<String> ch_id_str = gm_ChannelDao.findCh_idByDev_id_arr(dev_id_arr_str);
		String ch_id_arr = "'-1',";
		for(String str : ch_id_str){
			ch_id_arr += "'" +str+"',";
		}
		ch_id_arr = ch_id_arr.substring(0,(ch_id_arr.length()-1));
		this.gm_DeviceDao.gm_DevFault_del(dev_id_arr_str,ch_id_arr);
	}
	
	/**
	 * 报警配置参数 删除
	 */
	@Transactional
	public void op_alarmargument_del(String dev_id_arr_str) throws Exception{
		List<String> ch_id_str = gm_ChannelDao.findCh_idByDev_id_arr(dev_id_arr_str);
		String ch_id_arr = "'-1',";
		for(String str : ch_id_str){
			ch_id_arr += "'" +str+"',";
		}
		ch_id_arr = ch_id_arr.substring(0,(ch_id_arr.length()-1));
		this.gm_DeviceDao.op_alarmargument_del(ch_id_arr);
	}
	
	/**
	 * 控制设备状态配置 删除
	 */
	@Transactional
	public void op_DevCtrlSts_del(String dev_id_arr_str) throws Exception{
		List<String> ch_id_str = gm_ChannelDao.findCh_idByDev_id_arr(dev_id_arr_str);
		String ch_id_arr = "'-1',";
		for(String str : ch_id_str){
			ch_id_arr += "'" +str+"',";
		}
		ch_id_arr = ch_id_arr.substring(0,(ch_id_arr.length()-1));
		
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.op_DevCtrlSts_del(ch_id_arr,dect_id_arr);
	}
	
	/**
	 * 控制设备按钮配置 删除
	 */
	@Transactional
	public void op_DevCtrlBtn_del(String dev_id_arr_str) throws Exception{
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.op_DevCtrlBtn_del(dev_id_arr_str,dect_id_arr);
	}
	
	/**
	 * 控制设备操作 删除
	 */
	@Transactional
	public void gm_DevCtrlOperate_del(String dev_id_arr_str) throws Exception{
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.gm_DevCtrlOperate_del(dect_id_arr);
	}	
	
	/**
	 * 控制设备操作记录 删除
	 */
	@Transactional
	public void gm_DevCtrlOperateHistory_del(String dev_id_arr_str) throws Exception{
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.gm_DevCtrlOperateHistory_del(dect_id_arr);
	}
	
	/**
	 * 手机控制设备 删除
	 */
	@Transactional
	public void phone_DevCtrl_del(String dev_id_arr_str) throws Exception{
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.phone_DevCtrl_del(dect_id_arr);
	}
	
	/**
	 * 控制设备状态 删除
	 */
	@Transactional
	public void gm_DevCtrlSts_del(String dev_id_arr_str) throws Exception{
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.gm_DevCtrlSts_del(dect_id_arr);
	}
	
	/**
	 * 控制设备状态历史 删除
	 */
	@Transactional
	public void gm_DevCtrlStsHistory_del(String dev_id_arr_str) throws Exception{
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.gm_DevCtrlStsHistory_del(dect_id_arr);
	}

	/**
	 * 水产应用配置 删除
	 */
	@Transactional
	public void pro_fisheries_del(String dev_id_arr_str) throws Exception{
		List<String> dect_id_str = gm_DevCtrlDao.findDect_idByDev_id_arr(dev_id_arr_str);
		String dect_id_arr = "'-1',";
		for(String str : dect_id_str){
			dect_id_arr += "'" +str+"',";
		}
		dect_id_arr = dect_id_arr.substring(0,(dect_id_arr.length()-1));
		this.gm_DeviceDao.pro_fisheries_del(dect_id_arr);
	}
	
	/**
	 * 采集通道信息 删除
	 */
	@Transactional
	public void gm_Channel_del(String dev_id_arr_str) throws Exception{
		this.gm_DeviceDao.gm_Channel_del(dev_id_arr_str);
	}
	
	/**
	 * 控制设备信息 删除
	 */
	@Transactional
	public void gm_DevCtrl_del(String dev_id_arr_str) throws Exception{
		this.gm_DeviceDao.gm_DevCtrl_del(dev_id_arr_str);
	}
	
	/**
	 * 网络信息 删除
	 */
	@Transactional
	public void gm_Devnet_del(String dev_id_arr_str) throws Exception{
		this.gm_DeviceDao.gm_Devnet_del(dev_id_arr_str);		
	}
	
	/**
	 * 智能设备状态 删除
	 */
	@Transactional
	public void gm_Devsts_del(String dev_id_arr_str) throws Exception{
		this.gm_DeviceDao.gm_Devsts_del(dev_id_arr_str);		
	}
	
	/**
	 * 智能设备状态历史 删除
	 */
	@Transactional
	public void gm_DevstsHis_del(String dev_id_arr_str) throws Exception{
		this.gm_DeviceDao.gm_DevstsHis_del(dev_id_arr_str);	
	}
	
	/**
	 * 设备信息表 删除
	 */
	@Transactional
	public void gm_Device_del(String dev_id_arr_str) throws Exception{
		this.gm_DeviceDao.gm_Device_del(dev_id_arr_str);	
	}	

	/**
	 * 智能设备配置管理 删除
	 * @param dev_id
	 * @author Wang_Yuliang
	 */
//	@Transactional
//	public void manage_del(String dev_id) throws Exception{
//		/**测试是否可以回滚
//		System.out.println("dev_idddddddddddddddddddd:"+dev_id);
//		this.gm_DeviceDao.upDevNameForTest(dev_id, "无线远程控制网关xx");
//		Integer.parseInt("djaojdi");
//		this.gm_DeviceDao.upDevNameForTest(dev_id, "无线远程控制网关yy");
//		*/
		
		/**
		 * 之前的版本
		 
		System.out.println(new Date());
		String dev_id_arr_str = this.findAllDev_idByDev_id_wangyuliang(dev_id);
		//System.out.println("dev_idddddddddddddddddddd:"+dev_id_arr_str);  
		if("'-1'".equals(dev_id_arr_str)) {
			dev_id_arr_str = "'-1','"+dev_id+"'";
		}
		String sql = "";		
		
		//删除Gm_Latestdata 
		sql = "delete from gm_latestdata ";
		sql += "where ch_id in (select c.ch_id from gm_channel as c where c.dev_collectId in ("+dev_id_arr_str+") or c.dev_sensorId in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
				
		//删除Gm_Historydata
		sql = "delete from gm_historydata ";
		sql += "where ch_id in (select c.ch_id from gm_channel as c where c.dev_collectId in ("+dev_id_arr_str+") or c.dev_sensorId in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_DevChannel
		sql = "delete from gm_devchannel ";
		sql += "where dev_addr in(select n.net_addr from gm_devnet as n where n.dev_id in ("+dev_id_arr_str+")) ";
		sql += "or ch_id in (select c.ch_id from gm_channel as c where c.dev_collectId in ("+dev_id_arr_str+") or c.dev_sensorId in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_DevFault
		sql = "delete from gm_devfault ";
		sql += "where ch_id in (select c.ch_id from gm_channel as c where c.dev_collectId in ("+dev_id_arr_str+") or c.dev_sensorId in ("+dev_id_arr_str+")) "; 
		sql += "or dev_id in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除op_alarmargument
		sql = "delete from op_alarmargument ";
		sql += "where ch_id in (select c.ch_id from gm_channel as c where c.dev_collectId in ("+dev_id_arr_str+") or c.dev_sensorId in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Op_DevCtrlSts
		sql = "delete from op_devctrlsts ";
		sql += "where dect_id in(select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+")) ";
		sql += "or ch_id in (select c.ch_id from gm_channel as c where c.dev_collectId in ("+dev_id_arr_str+") or c.dev_sensorId in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Op_DevCtrlBtn
		sql = "delete from op_devctrlbtn ";
		sql += "where dect_id in (select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+")) "; 
		sql += "and dev_id in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_DevCtrlOperate
		sql = "delete from gm_devctrloperate ";
		sql += "where dect_id in (select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_DevCtrlOperateHistory
		sql = "delete from gm_devctrloperatehistory ";
		sql += "where dect_id in (select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Phone_DevCtrl
		sql = "delete from phone_devctrl ";
		sql += "where dect_id in (select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_DevCtrlSts
		sql = "delete from gm_devctrlsts ";
		sql += "where dect_id in (select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+")) ";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_DevCtrlStsHistory
		sql = "delete from gm_devctrlstshistory ";
		sql += "where dect_id in (select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//更新pro_fisheries
		sql = "update pro_fisheries set dect_id=null ";
		sql += "where dect_id in (select t.dect_id from gm_devctrl as t where t.dev_id in ("+dev_id_arr_str+"))";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_Channel
		sql = "delete from gm_channel ";
		sql += "where dev_collectId in ("+dev_id_arr_str+") or dev_sensorId in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_DevCtrl
		sql = "delete from gm_devctrl ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_Devnet
		sql = "delete from gm_devnet ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_Devsts
		sql = "delete from gm_devsts ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);

		//删除Gm_DevstsHis
		sql = "delete from gm_devstshis ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);
		
		//删除Gm_Device
		sql = "delete from gm_device ";
		sql += "where dev_id in ("+dev_id_arr_str+")";
		this.gm_DeviceDao.executeUpdate(sql);	
			
		System.out.println(new Date());
		*/		
//	}
	
	/**
	 * 指定智能设备ID 查询与之相关的所有智能设备的ID 以','分隔 
	 * @param dev_id
	 * @return 'xxx','xx','x'
	 * @author Wang_Yuliang
	 */
	public String findAllDev_idByDev_id_wangyuliang(String dev_id){
		//以下是王雨良写的
//		String dev_id_arr_str = "'-1',";
//		List<Gm_DevNet> gm_DevNet_list = gm_DevNetDao.findAllEq("dev_id.dev_id", dev_id);
//		if(gm_DevNet_list.size()>0){
//			for(Gm_DevNet gm_DevNet : gm_DevNet_list){
//				List<String> net_id_arr = new ArrayList<String>();
//				net_id_arr.add(gm_DevNet.getNet_id()); 
//				net_id_arr = this.gm_DevNetService.findDeviceTreeByNet_id(net_id_arr);//GPRS都能删，别的有没有权限就不管了
//				for(String net_id : net_id_arr){
//					Gm_DevNet gm_DevNet_xx = this.gm_DevNetDao.findById(net_id);
//					if(gm_DevNet_xx!=null){
//						if(gm_DevNet_xx.getDev_id()!=null){
//							dev_id_arr_str += "'"+gm_DevNet_xx.getDev_id().getDev_id()+"',";
//						}
//					}
//				}
//			}
//		}
//		dev_id_arr_str = dev_id_arr_str.substring(0,(dev_id_arr_str.length()-1));
//		return dev_id_arr_str;
		
		//以下是魏晓华修改
		String dev_id_arr_str = "'-1',";		
		List<Gm_DevNet> gm_DevNet_list = gm_DevNetDao.findAllEq("dev_id.dev_id", dev_id);		
		if(gm_DevNet_list.size()>0){
			for(Gm_DevNet gm_DevNet : gm_DevNet_list){
				List<String> net_id_arr = new ArrayList<String>();
				net_id_arr.add(gm_DevNet.getNet_id());
				net_id_arr = this.gm_DevNetService.findDeviceTreeByNet_id(net_id_arr);//GPRS都能删，别的有没有权限就不管了
				for(String net_id : net_id_arr){
					Gm_DevNet gm_DevNet_xx = this.gm_DevNetDao.findById(net_id);
					if(gm_DevNet_xx!=null){
						if(gm_DevNet_xx.getDev_id()!=null){
							dev_id_arr_str += "'"+gm_DevNet_xx.getDev_id().getDev_id()+"',";
						}
					}
				}
			}
		}	
		
		List<Gm_DevChannel> gm_DevChannel_list = gm_DevChannelDao.findAllEq("dev_id.dev_id", dev_id);//根据设备Id到设备上报通道配置表中查询出所有的通道
		if(gm_DevChannel_list.size()>0){
			String ch_id_arr="'-1',";
			for(Gm_DevChannel gm_DevChannel : gm_DevChannel_list){
				if(gm_DevChannel.getCh_id()!=null){
					ch_id_arr+="'"+gm_DevChannel.getCh_id().getCh_id()+"',";
				}		
			}
			ch_id_arr=ch_id_arr.substring(0, ch_id_arr.length()-1);
			List<Gm_Channel>  gm_Channel_list= this.gm_ChannelDao.findDev_idByCh_id(ch_id_arr);
			if(gm_Channel_list.size()>0){
				for(Gm_Channel gm_Channel : gm_Channel_list){
					if(gm_Channel.getDev_collectId()!=null){
						if(gm_Channel.getDev_collectId().getDev_id()!=null&&!"".equals(gm_Channel.getDev_collectId().getDev_id())){
							dev_id_arr_str+="'"+gm_Channel.getDev_collectId().getDev_id()+"',";
						}
					}					
					if(gm_Channel.getDev_sensorId()!=null){
						dev_id_arr_str+="'"+gm_Channel.getDev_sensorId().getDev_id()+"',";
					}
					//dev_id_arr_str+="'"+gm_Channel.getDev_collectId().getDev_id()+"','"+gm_Channel.getDev_sensorId().getDev_id()+"',";
				}
			}
		}
		dev_id_arr_str = dev_id_arr_str.substring(0,(dev_id_arr_str.length()-1));		
		
//		System.out.println("dev_id_arr_str2："+dev_id_arr_str);
		return dev_id_arr_str;
	}
	
	/**
	 * 指定设备编号 模糊查询设备ID数组
	 * @param dev_no
	 * @return
	 */
	public List<String> findDev_id_arrLikeDev_no(String dev_no){
		return this.gm_DeviceDao.findDev_id_arrLikeDev_no(dev_no);
	}

	public List<String> findDev_idByScene_idList(List<String> scene_idList)
	{
		return this.gm_DeviceDao.findDev_idByScene_idList(scene_idList);
	}
	
	/**
	 * 指定场景ID 返回场景及其子场景下的设备
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_findAllByScene_id_min(List<String> scene_id_arr){
		String json = "[";
		List<Gm_Device> gm_Device_list = this.search(new Search().addFilterAnd(Filter.equal("dev_state", 1),Filter.in("scene_id.scene_id", scene_id_arr)));
		for(Gm_Device gm_Device : gm_Device_list){
			json += "{";
			json += "\"dev_id\":\""+gm_Device.getDev_id()+"\",";
			json += "\"dev_name\":\""+gm_Device.getDev_name()+"\",";
			json += "\"dev_no\":\""+gm_Device.getDev_no()+"\"";
			json += "},";
		}
		if(json.length()>1)
			json = json.substring(0,(json.length()-1));
		return json+"]";
	}

	public List<String> findGRPSBysceneId(String scene_id_selectAll) {
		return this.gm_DeviceDao.findGRPSBysceneId(scene_id_selectAll);
	}

	public List<Gm_Device> findByScene(List<String> opScenes) {
		Search search = new Search();
		search.addFilterIn("scene_id.scene_id", opScenes);
		search.addFilterEqual("dev_state", 1);
		return gm_DeviceDao.search(search);
	}

	public List<Gm_Device> findbyIds(String[] devId) {
		Search search = new Search();
		search.addFilterIn("dev_id", devId);
		return gm_DeviceDao.search(search);
	}
	
	/**
	 * 验证场景下是否存在设备
	 * @return boolean
	 * @author liucl
	 */
	public boolean isScene_idExist(String scene_id){
		Search search = new Search();
		Filter filter = Filter.equal("scene_id.scene_id", scene_id);
		search.addFilter(filter);
		List<Gm_Device> gm_Device_list = this.search(search);
		if(gm_Device_list.size()>0){
			return true;
		}
		return false;
	}
}
