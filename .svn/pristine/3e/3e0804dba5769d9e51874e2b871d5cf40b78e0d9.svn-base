package org.unism.gm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DevNetService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.web.action.LoginAction;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.gson.Gson;


public class Gm_DevChannelAction {
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * 查询所有
	 * @return
	 */
	public String findAll(){
		List<Gm_DevChannel> list=gm_DevChannelService.findAll();
		if(list!=null&&list.size()>0){			
			Struts2Utils.getRequest().setAttribute("list", list);
		}
		return "list";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String save(){
		if(post==0){			
			Struts2Utils.getRequest().setAttribute("channel",this.gm_ChannelService.findAll());
			Struts2Utils.getRequest().setAttribute("device",this.gm_DeviceService.findAll());
			return "add";
		}else if(post==1){
			this.gm_DevChannelService.save(this.gm_DevChannel);
			Struts2Utils.getRequest().setAttribute("result", "success");
			return "operationResult";
		}
		return "operationResult";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String update() {
		if(this.gm_DevChannel!=null){					
			this.gm_DevChannelService.update(this.gm_DevChannel);			
			Struts2Utils.getRequest().setAttribute("result", "success");
			return "operationResult";
		}
		return "operationResult";
	}
	
	/**
	 * 根据ID查询
	 * @return
	 */
	public String findBydchid(){		
		Search search = new Search();
		Filter filter = Filter.equal("dch_id",this.gm_DevChannel.getDch_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("channel",this.gm_ChannelService.findAll());
		Struts2Utils.getRequest().setAttribute("device",this.gm_DeviceService.findAll());
		Struts2Utils.getRequest().setAttribute("list",this.gm_DevChannelService.search(search));
		return "edit";
	}
	/**
	 * 根据ID删除
	 * @return
	 */
	public String delete() {
		gm_DevChannelService.deleteById(this.gm_DevChannel.getDch_id());
		Struts2Utils.getRequest().setAttribute("result", "success");
		return "operationResult";
	}
	
	/**
	 * 分页查询
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toManage(){		
		return "manage";
	}
	
	/**
	 * 指定M2M网络节点 查询所属所有子节点的通道，并注明是否被配置到设备上报通道配置表中
	 * （
	 * 		这里就不再验证用户所关联的场景了，即系统认为，如果当前用户拥有选中的GPRS设备所属的场景的权限，就可已修改它的通道配置
	 * 	不再关心当前用户对子设备所属的场景是否具有权限。
	 * ）
	 * @return json
	 * 			[
	 * 				{
	 * 					ch_id:'?',
	 * 					ch_no:'?',
	 * 					ch_name:'?',
	 * 					ch_chlNo:?,
	 * 					dev_collectId:
	 * 									{
	 * 										dev_id:'?',
	 * 										dev_no:'?',
	 * 										dev_name:'?',
	 * 										net_addr:'?'
	 * 									}
	 * 					devChannel:
	 * 								{
	 * 									dch_order:?,
	 * 									ch_procesStyle:?,
	 * 									ch_MemoryPeriod:?
	 * 								} 					
	 * 				}
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String findAllChannelByM2M_And_AlertIsMapping(){	
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Gm_Channel> gm_Channel_list = new ArrayList<Gm_Channel>();	
			//验证 M2M节点是否在用户关联的场景中
			Gm_DevNet M2M = this.gm_DevNetService.findById(this.net_id);
			boolean isMapping = this.op_UserInfo_SceneService.IsMappingByUser_idAndScene_id(this.user_id, M2M.getDev_id().getScene_id().getScene_id());
			if(isMapping){			
//				if(this.net_id != null){
//					List<String> net_id_arr = new ArrayList<String>();
//					net_id_arr.add(this.net_id);
//					net_id_arr = this.gm_DevNetService.findDeviceTreeByNet_id(net_id_arr);
//					if(net_id_arr.size()>0){									
//						for(String net_id : net_id_arr){
//							Gm_DevNet gm_DevNet = this.gm_DevNetService.findById(net_id);
//							if(gm_DevNet != null){
//								if(gm_DevNet.getDev_id()!=null){
//									gm_Channel_list.addAll(this.gm_ChannelService.findByDev_collectId(gm_DevNet.getDev_id().getDev_id()));
//								}
//							}
//						}					
//					}
//				}
//不能在循环里写查询
				if(M2M != null){
//					List<Gm_DevNet> gm_DevNet_arr = new ArrayList<Gm_DevNet>();
//					gm_DevNet_arr.add(M2M);
//					Search search_gm_DevNet_child_list = new Search();
//					search_gm_DevNet_child_list.addFilterEqual("net_state", 1);
//					search_gm_DevNet_child_list.addSortAsc("net_addr");
//					List<Gm_DevNet> gm_DevNet_child_list = gm_DevNetService.search(search_gm_DevNet_child_list);
//					gm_DevNet_arr = this.gm_DevNetService.findDeviceTreeByDevNet(gm_DevNet_arr, gm_DevNet_child_list);
//					if(gm_DevNet_arr.size()>0){		
//						List<String> dev_id_arr = new ArrayList<String>(); 
//						for(Gm_DevNet gm_DevNet : gm_DevNet_arr){
//							//Gm_DevNet gm_DevNet = this.gm_DevNetService.findById(net_id);
//							if(gm_DevNet != null){
//								if(gm_DevNet.getDev_id()!=null && gm_DevNet.getDev_id().getDev_id()!=null){
//									dev_id_arr.add(gm_DevNet.getDev_id().getDev_id());
//								}
//							}
//						}
//						gm_Channel_list = this.gm_ChannelService.search(new Search().addFilterAnd(Filter.equal("ch_state", 1),Filter.in("dev_collectId.dev_id", dev_id_arr)));						
//					}
					//2012-07-25weixiaohua 修改
					gm_Channel_list=this.gm_ChannelService.findAllByAddrLikeBegin(addr);
				}
			}	
			String json = "[";
			if(gm_Channel_list.size()>0){
				for(Gm_Channel gm_Channel : gm_Channel_list){
					json += "{";
					json += "ch_id:'"+gm_Channel.getCh_id()+"',";
					json += "ch_no:'"+gm_Channel.getCh_no()+"',";
					json += "ch_name:'"+gm_Channel.getCh_name()+"',";
					json += "ch_chlNo:"+gm_Channel.getCh_chlNo()+",";
					json += "dev_collectId:";
					Gm_Device gm_Device = gm_Channel.getDev_collectId();
					if(gm_Device!=null){
						json += "{dev_id:'"+gm_Device.getDev_id()+"',dev_no:'"+gm_Device.getDev_no()+"',dev_name:'"+gm_Device.getDev_name()+"',net_addr:";
						json += "'"+gm_Channel.getDev_collectId().getDev_no()+"'";
//						Gm_DevNet gm_DevNet = this.gm_DevNetService.findByDev_id(gm_Device.getDev_id());
//						if(gm_DevNet!=null){
//							json += "'"+gm_DevNet.getNet_addr()+"'";
//						}else{
//							json += "null";
//						}
						json += "},";
					}else{
						json += "null,";
					}
					json += "devChannel:";
					Gm_DevChannel gm_DevChannel = this.gm_DevChannelService.findByCh_id(gm_Channel.getCh_id());
					if(gm_DevChannel!=null){
						json += "{dch_order:"+gm_DevChannel.getDch_order()+",ch_procesStyle:"+gm_DevChannel.getCh_procesStyle()+",ch_MemoryPeriod:"+gm_DevChannel.getCh_memoryPeriod()+"}";
					}else{
						json += "null";
					}
					json += "},";
				}
				json = json.substring(0,(json.length()-1));
			}
			//System.out.println(json + "]");
			out.print(json+"]");
		}catch(Exception ex){ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception exx){exx.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 配置
	 * @return
	 * @author Wang_Yuliang
	 * 2011-07-20 14:27 UP Wang_Yuliang
	 */	
	public String manage(){
//不能在循环里写查询  >>>>>>>>>>>>>>>>>		
		HttpSession session = Struts2Utils.getSession();
		logger.info("用户"+session.getAttribute("userid")+" 设备上报通道配置管理 》 管理 提交");
		try {			
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String json = this.gm_DevChannelService.manage(this.net_id,ch_id_arr,uc_ch_id_arr,dev_id_arr,dch_order_arr,ch_procesStyle_arr,ch_memoryPeriod_arr);
			out.print(json);				
		}catch(Exception ex){ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:'操作失败!未知错误'}");
			}catch(Exception exx){exx.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 根据设备id查询设备通道上报配置表，查询出所有的通道
	 * @return
	 */
	public String findChannel(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Gm_DevChannel> gmDevChannels = gm_DevChannelService.findDevChannelByDevId(devId);
			List<Map<String, String>> jsonList = new ArrayList<Map<String,String>>();
			for (Gm_DevChannel gmDevChannel : gmDevChannels) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("chId", gmDevChannel.getCh_id().getCh_id());
				map.put("chNo", gmDevChannel.getCh_id().getCh_no());
				jsonList.add(map);
			}
			String json = new Gson().toJson(jsonList);
			out.print(json);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Gm_DevChannelService gm_DevChannelService;
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Gm_DevNetService gm_DevNetService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	
	private Gm_DevChannel gm_DevChannel =new Gm_DevChannel();
	private int post;
	private String net_id; //设备上报通道配置管理-管理 在执行添加关联（manage()函数）中也用到了 M2M节点的ID
	private String user_id; //设备上报通道配置管理-管理 session中的user.user_id
	private String ch_id_arr = ""; //设备上报通道配置管理-管理  选中的通道ID
	private String uc_ch_id_arr; //设备上报通道配置管理-管理 未选中的通道ID
	private String dev_id_arr = ""; 
	private String dch_order_arr = "";
	private String ch_procesStyle_arr = "";
	private String ch_memoryPeriod_arr = "";
	private String devId;
	private String addr;
	
	
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNet_id() {
		return net_id;
	}

	public void setNet_id(String net_id) {
		this.net_id = net_id;
	}

	public Gm_DevChannel getGm_DevChannel() {
		return gm_DevChannel;
	}
	public void setGm_DevChannel(Gm_DevChannel gm_DevChannel) {
		this.gm_DevChannel = gm_DevChannel;
	}
	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}

	public String getCh_id_arr() {
		return ch_id_arr;
	}

	public void setCh_id_arr(String ch_id_arr) {
		this.ch_id_arr = ch_id_arr;
	}

	public String getDev_id_arr() {
		return dev_id_arr;
	}

	public void setDev_id_arr(String dev_id_arr) {
		this.dev_id_arr = dev_id_arr;
	}

	public String getDch_order_arr() {
		return dch_order_arr;
	}

	public void setDch_order_arr(String dch_order_arr) {
		this.dch_order_arr = dch_order_arr;
	}

	public String getCh_procesStyle_arr() {
		return ch_procesStyle_arr;
	}

	public void setCh_procesStyle_arr(String ch_procesStyle_arr) {
		this.ch_procesStyle_arr = ch_procesStyle_arr;
	}

	public String getCh_memoryPeriod_arr() {
		return ch_memoryPeriod_arr;
	}

	public void setCh_memoryPeriod_arr(String ch_memoryPeriod_arr) {
		this.ch_memoryPeriod_arr = ch_memoryPeriod_arr;
	}

	public String getUc_ch_id_arr() {
		return uc_ch_id_arr;
	}

	public void setUc_ch_id_arr(String uc_ch_id_arr) {
		this.uc_ch_id_arr = uc_ch_id_arr;
	}
}
