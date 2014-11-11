package org.unism.gm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_Devsts;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DevNetService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.gm.service.Gm_DevstsService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.util.Net_role;
import org.unism.util.Net_type;
import org.unism.web.action.LoginAction;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

public class Gm_DevNetAction extends ActionSupport {
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * 分页查询
	 * @return
	 * @author Wang_Yuliang
	 * 0704 UP Wang_Yuliang
	 */
	public String page(){
		//System.out.println("ccccccccccccc");
		HttpSession session = Struts2Utils.getSession();
		String user_id = (String)session.getAttribute("userid");	
		//Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		//List<String> list = this.gm_DevNetService.findNet_idByUser_id(user.getUser_id());
		List<String> list = this.gm_DevNetService.findNet_idByUser_id(user_id);
	
		List<String> scene_id_arr = new ArrayList<String>();
		scene_id_arr.add(this.scene_id);				
		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
		scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
		Search search_dev = new Search();
		Filter filter_dev = Filter.and(Filter.equal("dev_state", 1),Filter.in("scene_id.scene_id", scene_id_arr));
		List<Gm_Device> device_arr = new ArrayList<Gm_Device>();	
		search_dev.addFilter(filter_dev);
		device_arr = this.gm_DeviceService.search(search_dev);
		Gm_Device gm_Device_def = new Gm_Device();
		gm_Device_def.setDev_id("-1");	
		device_arr.add(gm_Device_def);

		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("net_state", 1),Filter.in("net_id", list));
		if(this.hid_condition != null && !this.hid_condition.equals("")){
			if(this.scene_id!=null && !this.scene_id.equals("")){
				filter = Filter.and(Filter.equal("net_state", 1),Filter.like(this.hid_condition, this.hid_value),Filter.in("dev_id", device_arr));
			}else{
				filter = Filter.and(Filter.equal("net_state", 1),Filter.like(this.hid_condition, this.hid_value),Filter.in("net_id", list));
			}
		}else{
			if(this.scene_id!=null && !this.scene_id.equals("")){
				filter = Filter.and(Filter.equal("net_state", 1),Filter.in("dev_id", device_arr));
			}else{}
		}
		
		search.addFilter(filter);
		search.addSortAsc("net_no");
		this.page = this.gm_DevNetService.search(page, search);
		return "page";
	}
	
	/**
	 * 到添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave(){

		return "save";
	}
	
	/**
	 * 到添加 0716
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave_0716(){

		return "save_0716";
	}
		
	/**
	 * 添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save(){
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("list", "gm_devnet_page.action?page.pageSize=12");
		if(this.gm_DevNetService.isExist(this.gm_DevNet)){
			request.setAttribute("sms", "网络节点已存在");
			return "operationResult";
		}
		if(this.gm_DevNet.getNet_type() == 0){
			//远程网 M2M 设备地址唯一
			if(this.gm_DevNetService.addIsUq(this.gm_DevNet.getNet_addr())){
				request.setAttribute("sms", "网络节点已存在");
				return "operationResult";
			}
		}
		try{
			this.gm_DevNetService.save(this.gm_DevNet);
			Gm_Devsts gm_Devsts = new Gm_Devsts();
			gm_Devsts.setDev_id(this.gm_DevNet.getDev_id());
			gm_Devsts.setDev_addr(this.gm_DevNet.getNet_addr());
			this.gm_DevstsService.save(gm_Devsts);
			request.setAttribute("result", "success");
		}catch(Exception ex){ex.printStackTrace();}
		return "operationResult";
	}
	
	/**
	 * 添加 0716
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save_0716(){
		HttpSession session = Struts2Utils.getSession();
		//request.setAttribute("list", "gm_devnet_page.action?page.pageSize=12");
		//if(this.gm_DevNetService.isExist(this.gm_DevNet)){
		//	addActionMessage("操作失败!网络节点已存在");
		//	return "operationResult";
		//}
		if(this.gm_DevNet.getNet_type() == 0){
			//远程网 M2M 设备地址唯一
			if(this.gm_DevNetService.addIsUq(this.gm_DevNet.getNet_addr())){
				addActionMessage("操作失败!网络节点已存在");
				return "operationResult";
			}
			try{
				this.gm_DevNetService.saveM2M(this.gm_DevNet);
				addActionMessage("操作成功");
				logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 添加 提交 操作成功");
			}catch(Exception ex){ex.printStackTrace();}
			return "operationResult";
		}
		try{
			this.gm_DevNetService.saveWSN(this.gm_DevNet);
			addActionMessage("操作成功");
			logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 添加 提交 操作成功");
		}catch(Exception ex){ex.printStackTrace();}
		return "operationResult";
	}
	
	/**
	 * 查找空闲M2M设备
	 * @return
	 * @author Wang_Yuliang
	 * 0608UP Wang_Yuliang 增加,Filter.in("scene_id.scene_id", this.op_UserInfo_SceneService.findScene_idByUser_id(userid))
	 */
	public String findFreeM2M(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Search search = new Search();
			String userid = (String)session.getAttribute("userid");
			Filter filter = Filter.and(Filter.notIn("dev_id", this.gm_DeviceService.findDev_idFromDevNet()),Filter.equal("dev_state", 1),Filter.equal("dev_btype", 0),Filter.in("scene_id.scene_id", this.op_UserInfo_SceneService.findScene_idByUser_id(userid)));
			search.addFilter(filter);
			this.gm_Device_list = this.gm_DeviceService.search(search);
			String json = "[";
			if(this.gm_Device_list.size()>0){
				for(Gm_Device gm_Device : gm_Device_list){
					json += "{dev_id:\""+gm_Device.getDev_id()+"\",dev_no:\""+gm_Device.getDev_no()+"\",dev_name:\""+gm_Device.getDev_name()+"\"},";
				}
				json = json.substring(0,json.length()-1);
			}			
			//System.out.println("json:"+json);
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * 查找空闲WSN设备
	 * @return
	 * @author Wang_Yuliang
	 * 0608UP Wang_Yuliang 增加,Filter.in("scene_id.scene_id", this.op_UserInfo_SceneService.findScene_idByUser_id(userid))
	 */
	public String findFreeWSN(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Search search = new Search();
			String userid = (String)session.getAttribute("userid");
			Filter filter = Filter.and(Filter.notIn("dev_id", this.gm_DeviceService.findDev_idFromDevNet()),Filter.equal("dev_state", 1),Filter.equal("dev_btype", 1),Filter.in("scene_id.scene_id", this.op_UserInfo_SceneService.findScene_idByUser_id(userid)));
			search.addFilter(filter);
			this.gm_Device_list = this.gm_DeviceService.search(search);
			String json = "[";
			if(this.gm_Device_list.size()>0){
				for(Gm_Device gm_Device : gm_Device_list){
					json += "{dev_id:\""+gm_Device.getDev_id()+"\",dev_no:\""+gm_Device.getDev_no()+"\",dev_name:\""+gm_Device.getDev_name()+"\"},";
				}
				json = json.substring(0,json.length()-1);
			}			
			//System.out.println("json:"+json);
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * 查找空闲智能单元
	 * @return
	 * @author Wang_Yuliang
	 * 0608UP Wang_Yuliang 增加,Filter.in("scene_id.scene_id", this.op_UserInfo_SceneService.findScene_idByUser_id(userid))
	 */
	public String findFreeISENSOR(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Search search = new Search();
			String userid = (String)session.getAttribute("userid");
			Filter filter = Filter.and(Filter.notIn("dev_id", this.gm_DeviceService.findDev_idFromDevNet()),Filter.equal("dev_state", 1),Filter.equal("dev_btype", 2),Filter.in("scene_id.scene_id", this.op_UserInfo_SceneService.findScene_idByUser_id(userid)));
			search.addFilter(filter);
			this.gm_Device_list = this.gm_DeviceService.search(search);
			String json = "[";
			if(this.gm_Device_list.size()>0){
				for(Gm_Device gm_Device : gm_Device_list){
					json += "{dev_id:\""+gm_Device.getDev_id()+"\",dev_no:\""+gm_Device.getDev_no()+"\",dev_name:\""+gm_Device.getDev_name()+"\"},";
				}
				json = json.substring(0,json.length()-1);
			}			
			//System.out.println("json:"+json+"]");
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;		
	}
	
	/**
	 * 到编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toEdit(){
		HttpServletRequest request = Struts2Utils.getRequest();
		this.gm_DevNet = this.gm_DevNetService.findById(this.gm_DevNet.getNet_id());
		if(this.gm_DevNet == null){
			request.setAttribute("sms", "网络节点不存在");
			return "operationResult";
		}
		if(this.gm_DevNet.getDev_id() == null){
			request.setAttribute("sms", "未知错误");
			return "operationResult";
		}
		Gm_Devsts gm_Devsts = this.gm_DevstsService.findByDev_id(this.gm_DevNet.getDev_id().getDev_id());
		if(gm_Devsts == null){
			request.setAttribute("sms", "未知错误");
			return "operationResult";
		}
		this.dest_id = gm_Devsts.getDest_id();
		return "edit";
	}
	
	/**
	 * 到编辑 0716
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toEdit_0716(){
		HttpServletRequest request = Struts2Utils.getRequest();
		this.gm_DevNet = this.gm_DevNetService.findById(this.gm_DevNet.getNet_id());
		if(this.gm_DevNet == null){
			request.setAttribute("sms", "网络节点不存在");
			return "operationResult";
		}
		if(this.gm_DevNet.getDev_id() == null){
			request.setAttribute("sms", "未知错误");
			return "operationResult";
		}
		Gm_Devsts gm_Devsts = this.gm_DevstsService.findByDev_id(this.gm_DevNet.getDev_id().getDev_id());
		//if(gm_Devsts == null){
		//	request.setAttribute("sms", "未知错误");
		//	return "operationResult";
		//}
		if(gm_Devsts!=null){
			this.dest_id = gm_Devsts.getDest_id();
		}
		return "edit_0716";
	}
	
	/**
	 * 编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String edit(){
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("list", "gm_devnet_page.action?page.pageSize=12");		
		//验证网络节点是否已存在
		List<Gm_DevNet> list = new ArrayList<Gm_DevNet>();
		Search s1 = new Search();
		Filter f1 = Filter.and(Filter.equal("net_state", 1),Filter.equal("net_no", this.gm_DevNet.getNet_no()),Filter.notEqual("net_id", this.gm_DevNet.getNet_id()));
		s1.addFilter(f1);
		list = this.gm_DevNetService.search(s1);
		if(list.size()>0){
			request.setAttribute("sms", "网络节点已存在");
			return "operationResult";
		}
		
		if(this.gm_DevNet.getNet_pid() == null){
			Search s2 = new Search();
			Filter f2= Filter.and(Filter.equal("net_state", 1),Filter.equal("net_addr", this.gm_DevNet.getNet_addr()),Filter.notEqual("net_id", this.gm_DevNet.getNet_id()));
			s2.addFilter(f2);
			list = this.gm_DevNetService.search(s2);
			if(list.size()>0){
				request.setAttribute("sms", "网络节点已存在");
				return "operationResult";
			}
		}else if(this.gm_DevNet.getNet_pid().equals("FF")){
			//FF的只要编号不重复就可以了
		}else{
			Search s3 = new Search();
			Filter f3= Filter.and(Filter.equal("net_state", 1),Filter.equal("net_addr", this.gm_DevNet.getNet_addr()),Filter.notEqual("net_id", this.gm_DevNet.getNet_id()));
			s3.addFilter(f3);
			list = this.gm_DevNetService.search(s3);
			if(list.size()>0){
				for(Gm_DevNet gm_DevNet : list){
					String root_id = this.gm_DevNetService.findRootByNet_id(this.gm_DevNet.getNet_id());
					String root_id_temp = this.gm_DevNetService.findRootByNet_id(gm_DevNet.getNet_id());
					if(root_id != null && root_id_temp!=null){
						if(root_id_temp.equals(root_id)){
							request.setAttribute("sms", "网络节点已存在");
							return "operationResult";
						}
					}
				}
			}
		}
		//应该就这些了吧？先这样吧 0608
		try{
			this.gm_DevNetService.update(this.gm_DevNet);
			Gm_Devsts gm_Devsts = this.gm_DevstsService.findById(this.dest_id);
			gm_Devsts.setDev_id(this.gm_DevNet.getDev_id());
			gm_Devsts.setDev_addr(this.gm_DevNet.getNet_addr());
			this.gm_DevstsService.update(gm_Devsts);
			request.setAttribute("result", "success");	
			request.setAttribute("sms", "如果您修改了网络地址，或关联设备信息，请到设备上报通道配置管理中更新配置");
		}catch(Exception ex){ex.printStackTrace();}
		return "operationResult";
	}
	
	/**
	 * 编辑 0716
	 * @return
	 * @author Wang_Yuliang
	 */
	public String edit_0716(){
		HttpSession session = Struts2Utils.getSession();
		//验证网络编号是否已存在
		//  如果是 提示操作失败 返回
		List<Gm_DevNet> list = new ArrayList<Gm_DevNet>();
		Search s1 = new Search();
		Filter f1 = Filter.and(Filter.equal("net_state", 1),Filter.equal("net_no", this.gm_DevNet.getNet_no()),Filter.notEqual("net_id", this.gm_DevNet.getNet_id()));
		s1.addFilter(f1);
		list = this.gm_DevNetService.search(s1);
		if(list.size()>0){
			addActionMessage("操作失败!网络节点已存在");
			return "operationResult";
		}
		
		//如果【智能设备状态信息】的ID不为null或””（改前是M2M）
		if(this.dest_id!=null && !this.dest_id.equals("")){
			//判断父节点ID
			//如果是null
			if(this.gm_DevNet.getNet_pid() == null){
				//验证地址是否重复
				Search s2 = new Search();
				Filter f2= Filter.and(Filter.equal("net_state", 1),Filter.equal("net_addr", this.gm_DevNet.getNet_addr()),Filter.notEqual("net_id", this.gm_DevNet.getNet_id()));
				s2.addFilter(f2);
				list = this.gm_DevNetService.search(s2);
				if(list.size()>0){//如果是 提示操作失败 返回
					addActionMessage("操作失败!网络节点已存在");
					return "operationResult";
				}
				//如果不是 
				try{
					//更新网络信息
//					获得原来的智能设备状态信息的ID  
//					获得原来的智能设备状态信息					
					//如果原智能设备状态信息中的设备地址==新网络信息中的网络地址
						//更新智能设备状态信息(注册身份不变)  返回
//					否则
//						删除原智能设备状态信息
//						删除与新网络信息地址等值的智能设备状态信息
//						添加智能设备状态信息（含关联设备，注册身份0，设备地址）
					this.gm_DevNetService.edit_M2M_M2M(this.gm_DevNet, this.dest_id);
					addActionMessage("操作成功!如果您修改了网络地址，或关联设备信息，请到设备上报通道配置管理中更新配置");
					logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 编辑 提交 操作成功");
					return "operationResult";
				}catch(Exception ex){ex.printStackTrace();addActionMessage("操作失败!未知错误");return "operationResult";}				
			}else{//如果不是null
				//Pid设置为FF 	
				//删除原来的智能设备状态信息
				//		 	 更新网络信息 返回
				try{
					this.gm_DevNetService.edit_M2M_WSN(this.gm_DevNet, this.dest_id);
					addActionMessage("操作成功!如果您修改了网络地址，或关联设备信息，请到设备上报通道配置管理中更新配置");
					logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 编辑 提交 操作成功");
					return "operationResult";
				}catch(Exception ex){ex.printStackTrace();addActionMessage("操作失败!未知错误");return "operationResult";}		
			}
		}
		else{//否则 说明变之前不是M2M节点 (改前不是M2M)
			//判断父节点ID
			if(this.gm_DevNet.getNet_pid() == null){
				//如果是null
//				验证地址是否重复
//				如果是 提示操作失败 返回
				Search s2 = new Search();
				Filter f2= Filter.and(Filter.equal("net_state", 1),Filter.equal("net_addr", this.gm_DevNet.getNet_addr()),Filter.notEqual("net_id", this.gm_DevNet.getNet_id()));
				s2.addFilter(f2);
				list = this.gm_DevNetService.search(s2);
				if(list.size()>0){
					addActionMessage("操作失败!网络节点已存在");
					return "operationResult";
				}
				//如果不是 
				try{					
					this.gm_DevNetService.edit_WSN_M2M(this.gm_DevNet);
					addActionMessage("操作成功!如果您修改了网络地址，或关联设备信息，请到设备上报通道配置管理中更新配置");
					logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 编辑 提交 操作成功");
					return "operationResult";
				}catch(Exception ex){ex.printStackTrace();addActionMessage("操作失败!未知错误");return "operationResult";}							
			}else if(this.gm_DevNet.getNet_pid().equals("FF")){
				//如果是FF
				// 无需验证
			}else{
//				验证其自身地址和所属GPRS设备的地址的组合 是否在网络表中重复
//	         	如果是 提示操作失败 返回
				Search s3 = new Search();
				Filter f3= Filter.and(Filter.equal("net_state", 1),Filter.equal("net_addr", this.gm_DevNet.getNet_addr()),Filter.notEqual("net_id", this.gm_DevNet.getNet_id()));
				s3.addFilter(f3);
				list = this.gm_DevNetService.search(s3);
				if(list.size()>0){
					String root_id = this.gm_DevNetService.findRootByNet_id(this.gm_DevNet.getNet_id());
					for(Gm_DevNet gm_DevNet : list){					
						String root_id_temp = this.gm_DevNetService.findRootByNet_id(gm_DevNet.getNet_id());
						if(root_id != null && root_id_temp!=null){
							if(root_id_temp.equals(root_id)){
								addActionMessage("操作失败!网络节点已存在");
								return "operationResult";
							}
						}
					}
				}
			}
			//执行更新网络信息 返回
			try{
				this.gm_DevNetService.update(this.gm_DevNet);
				addActionMessage("操作成功!如果您修改了网络地址，或关联设备信息，请到设备上报通道配置管理中更新配置");
				logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 编辑 提交 操作成功");
				return "operationResult";
			}catch(Exception ex){ex.printStackTrace();addActionMessage("操作失败!未知错误");return "operationResult";}
		}
	}
	
	/**
	 * 到添加场景关联
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toAddChild(){
		return "addChild";
	}	
	
	/**
	 * 到删除场景关联
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toDeleteChild(){
		return "deleteChild";
	}
	
	/**
	 * 指定网络信息，场景ID集合 查询与用户相关的场景中的设备关联的 在用的 下一级网络信息
	 * @return json
	 * 			[
	 * 				{net_id:'?',net_no:'?',net_addr:'?'},
	 * 				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String findChildList(){		
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Gm_DevNet gm_DevNet = this.gm_DevNetService.findById(this.gm_DevNet.getNet_id());
			List<String> scene_id_list = op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			String json = this.gm_DevNetService.findChildList(gm_DevNet, scene_id_list);
			//System.out.println("net ChildList:"+json);
			out.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 指定网络信息，场景ID集合 查询与用户相关的场景中的设备关联的 在用的 子网络信息
	 * @return json
	 * 			[
	 * 				{net_id:'?',net_no:'?',net_addr:'?'},
	 * 				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String findChildedList(){		
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Gm_DevNet gm_DevNet = this.gm_DevNetService.findById(this.gm_DevNet.getNet_id());
			List<String> scene_id_list = op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			String json = this.gm_DevNetService.findChildedList(gm_DevNet, scene_id_list);
			//System.out.println("net ChildList:"+json);
			out.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 添加网络关联
	 * @return
	 * @author Wang_Yuliang
	 */
	public String addChild(){
		HttpSession session = Struts2Utils.getSession();
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html; charset=utf-8");		
		PrintWriter out = null; 
		try {
			//List<String> scene_id_list = op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			out = response.getWriter();
			String net_id_list[] = new String[0];
			if(this.net_id_list.length()>0)net_id_list = this.net_id_list.split(",");
			for(int i=0;i<net_id_list.length;i++){
				Gm_DevNet child = this.gm_DevNetService.findById(net_id_list[i]);
				//if()这个就不再再次做验证了，场景那边也是，只要不是【同一个用户在多个客户端（浏览器登录），a浏览器先到添加关联 查出待选子场景，b浏览器改信息，a再提交】就行
				//这个要验证的话。。场景那边也是，可麻烦了
				child.setNet_pid(this.gm_DevNet.getNet_id());
				this.gm_DevNetService.update(child);
			}			
			out.print("1");
			logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 添加网络关联 提交 操作成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("0");
		}
		return null;	
	}
	
	/**
	 * 删除网络关联
	 * @return
	 * @author Wang_Yuliang
	 */
	public String deleteChild(){
		HttpSession session = Struts2Utils.getSession();
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html; charset=utf-8");		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			String net_id_list[] = new String[0];
			if(this.net_id_list.length()>0)net_id_list = this.net_id_list.split(",");
			for(int i=0;i<net_id_list.length;i++){
				Gm_DevNet child = this.gm_DevNetService.findById(net_id_list[i]);				
				child.setNet_pid("FF");
				this.gm_DevNetService.update(child);
			}			
			out.print("1");
			logger.info("用户"+session.getAttribute("userid")+" 网络信息管理 》 删除网络关联 提交 操作成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("0");
		return null;		
	}
	
    /**
     * 删除
     * @return
     * @author Wang_Yuliang
     */
    public String delete(){
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("list", "op_scene_page.action?page.pageSize=10");
		
		List<String> scene_id_list = op_UserInfo_SceneService.findScene_idByUser_id(user_id);
		this.gm_DevNet = this.gm_DevNetService.findById(this.gm_DevNet.getNet_id());
		if(this.gm_DevNet == null){
			request.setAttribute("sms", "无法找到指定的网络信息");
			return "operationResult";
		}
		String gm_DevNet_list = this.gm_DevNetService.findChildedList(gm_DevNet, scene_id_list);
		if(!gm_DevNet_list.equals("[]")){
			request.setAttribute("sms", "请先删除该网路的子网络");
			return "operationResult";
		}
    	try{
    		this.gm_DevNet.setNet_state(0);
    		this.gm_DevNetService.update(this.gm_DevNet);
    		Gm_Devsts gm_Devsts = this.gm_DevstsService.findByDev_id(this.gm_DevNet.getDev_id().getDev_id());
    		if(gm_Devsts != null){
    			this.gm_DevstsService.delete(gm_Devsts);
    		}
			List<Gm_DevChannel> gm_DevChannel_list = this.gm_DevChannelService.findByDev_id(this.gm_DevNet.getDev_id().getDev_id());
			for(Gm_DevChannel gm_DevChannel : gm_DevChannel_list){
				this.gm_DevChannelService.delete(gm_DevChannel);
			}
    		request.setAttribute("result", "success");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return "operationResult";
    } 
    
    /**
     * 删除 0716
     * @return
     * @author Wang_Yuliang
     */
    public String delete_0716(){
		//HttpServletRequest request = Struts2Utils.getRequest();
		//request.setAttribute("list", "op_scene_page.action?page.pageSize=10");
		
		List<String> scene_id_list = op_UserInfo_SceneService.findScene_idByUser_id(user_id);
		this.gm_DevNet = this.gm_DevNetService.findById(this.gm_DevNet.getNet_id());
		if(this.gm_DevNet == null){
			addActionMessage("操作失败!无法找到指定的网络信息");
			return "operationResult";
		}
		String gm_DevNet_list = this.gm_DevNetService.findChildedList(gm_DevNet, scene_id_list);
		if(!gm_DevNet_list.equals("[]")){
			addActionMessage("操作失败!请先删除该网路的子网络");
			return "operationResult";
		}
    	try{
    		this.gm_DevNetService.delete_wangyuliang(this.gm_DevNet);
    		addActionMessage("操作成功!");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return "operationResult";
    } 
    
    /**
     * 指定网络类型性，返回网络角色
     * @return
     * @author Wang_Yuliang
     */
    public String findRolesByType(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Net_role> net_roles = new Net_type(String.valueOf(this.gm_DevNet.getNet_type()),"XX").getNet_roles();
			String json = "[";
			for(Net_role net_role : net_roles){
				json += "{";
				json += "id:'"+net_role.getId()+"',";
				json += "name:'"+net_role.getName()+"'";
				json += "},";
			}
			if(json.length()>1){
				json = json.substring(0,(json.length()-1));
			}
			json += "]";
			//System.out.println("net ChildList:"+json);
			out.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
    }    
   
//分割线------------------------------------------------------------------------------------------------------------
	@Autowired Gm_DevNetService gm_DevNetService;
	@Autowired Gm_DeviceService gm_DeviceService;	
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_DevstsService gm_DevstsService;
	@Autowired Gm_DevChannelService gm_DevChannelService;
	@Autowired Op_SceneService op_SceneService;
	
	
	private Gm_DevNet gm_DevNet = new Gm_DevNet();
	private Gm_Device gm_Device=new Gm_Device();
	private Page<Gm_DevNet> page;
	private List<Gm_Device> gm_Device_list = Lists.newArrayList();
	private String user_id;
	private String net_id_list; //添加场景关联
	private String dest_id; //到编辑/编辑网络信息 
	private String scene_id; //page
	private String hid_condition;
	private String hid_value;
	
	public String getHid_condition() {
		return hid_condition;
	}

	public void setHid_condition(String hid_condition) {
		this.hid_condition = hid_condition;
	}

	public String getHid_value() {
		return hid_value;
	}

	public void setHid_value(String hid_value) {
		this.hid_value = hid_value;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getDest_id() {
		return dest_id;
	}

	public void setDest_id(String dest_id) {
		this.dest_id = dest_id;
	}

	public String getNet_id_list() {
		return net_id_list;
	}

	public void setNet_id_list(String net_id_list) {
		this.net_id_list = net_id_list;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Gm_DevNet getGm_DevNet() {
		return gm_DevNet;
	}
	public void setGm_DevNet(Gm_DevNet gm_DevNet) {
		this.gm_DevNet = gm_DevNet;
	}
	public Page<Gm_DevNet> getPage() {
		return page;
	}
	public void setPage(Page<Gm_DevNet> page) {
		this.page = page;
	}
	public List<Gm_Device> getGm_Device_list() {
		return gm_Device_list;
	}
	public void setGm_Device_list(List<Gm_Device> gm_Device_list) {
		this.gm_Device_list = gm_Device_list;
	}

	public Gm_Device getGm_Device() {
		return gm_Device;
	}

	public void setGm_Device(Gm_Device gm_Device) {
		this.gm_Device = gm_Device;
	}
	
	
	
}
