package org.unism.op.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.ftp.FtpClient;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevCtrlSts;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevCtrlOperateService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.gm.service.Gm_DevCtrlStsService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.gm.service.Gm_HistorydataService;
import org.unism.gm.service.Gm_LatestdataService;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.unism.op.domain.Op_scene_gtype;
import org.unism.op.service.Op_AreasService;
import org.unism.op.service.Op_ChannelTypeService;
import org.unism.op.service.Op_DevCtrlBtnService;
import org.unism.op.service.Op_PlantformUserService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.op.service.Op_scene_gtypeService;
import org.unism.pro.service.Pro_FisheriesService;
import org.unism.util.DateTools;
import org.unism.util.GetUUID;
import org.unism.util.ImageUrl;
import org.unism.web.action.LoginAction;
import org.unism.web.service.CacheService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class Op_SceneAction extends ActionSupport{	
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * 分页查询
	 * @return
	 * @author Wang_Yuliang
	 */
	public String page(){
		HttpSession session = Struts2Utils.getSession();
		//Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");//从session里获取用户实体
		//List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user.getUser_id());//获取场景ID集合
		
		//上面的代码不要了 0704 wang _yuliang 现在要查询所有子场景，包含输入场景
		List<String> scene_id_arr = new ArrayList<String>();
		scene_id_arr.add(this.scene_pid);
		String user_id = (String)session.getAttribute("userid");			
		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
		scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
		//--end
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_state", 1),Filter.in("scene_id", scene_id_list));//根据场景使用状态和是否在场景内查询场景
		if(this.scene_pid != null && !this.scene_pid.equals("")){//页面提交的场景父Id不为null
			//filter = Filter.and(Filter.equal("scene_state", 1),Filter.equal("scene_pid", this.scene_pid),Filter.in("scene_id", list));
			//上面的代码不要了 0704 wang _yuliang 现在要查询所有子场景，包含输入场景			
			//--end
			if(this.hid_condition!=null && !this.hid_condition.equals("")){
				if(this.hid_condition.equals("scene_pname")){
					List<String> scene_id_arr_bypname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
					filter = Filter.and(Filter.in("scene_id", scene_id_arr),Filter.in("scene_pid", scene_id_arr_bypname));
				}else{
					filter = Filter.and(Filter.in("scene_id", scene_id_arr),Filter.like(this.hid_condition, this.hid_value));
				}		
			}else{
				filter = Filter.in("scene_id", scene_id_arr);
			}
		}else{
			if(this.hid_condition!=null && !this.hid_condition.equals("")){
				if(this.hid_condition.equals("scene_pname")){
					List<String> scene_id_arr_bypname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
					filter = Filter.and(Filter.equal("scene_state", 1),Filter.in("scene_id", scene_id_list),Filter.in("scene_pid", scene_id_arr_bypname));
				}else{
					filter = Filter.and(Filter.equal("scene_state", 1),Filter.in("scene_id", scene_id_list),Filter.like(this.hid_condition, this.hid_value));
				}	
			}else{}
		}
		search.addFilter(filter);
		//0714 UP Wang_Yuliang 据小华姐《每日安排.docx》
		search.addSortAsc("scene_no");
		this.page = this.op_SceneService.search(page, search);
		List<Op_scene_gtype> list = scene_gtypeService.findAll();
    	Struts2Utils.getRequest().setAttribute("sceneGtype", list);
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
	 * 到添加 0714
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave_0714(){
		List<Op_scene_gtype> list = scene_gtypeService.findAll();
    	Struts2Utils.getRequest().setAttribute("sceneGtype", list);
		return "save_0714";
	}
	
	/**
	 * 验证场景编号是否唯一
	 * @return 
	 * @author Wang_Yuliang
	 */
	public String isExist(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(this.op_SceneService.isExist(this.op_Scene.getScene_no())){
				out.print("{value:true,msg:'操作失败!场景编号已存在'}");
			}else{
				out.print("{value:false,msg:''}");
			}	
		} catch (IOException e) {
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:true,msg:'操作失败!无法验证场景是否已存在'}");
			}catch(Exception ee){ee.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save(){		
		HttpSession session = Struts2Utils.getSession();
		//request.setAttribute("list", "op_scene_page.action?page.pageSize=12");
		
		this.op_Scene.setScene_creater(((Op_UserInfo)Struts2Utils.getSession().getAttribute("user")).getUser_name());
		this.op_Scene.setScene_createDate(new Date());		
		//Wang_Yuliang 2011-07-28 UP  改为客户端 异步验证 --begin if(this.op_SceneService.isExist(this.op_Scene.getScene_no())){
		//	addActionMessage("操作失败!场景已存在");
		//	return "operationResult";
		//} 
		//--end
//      14日拿到的需求中，等级在页面中让用户选，这个不需要了
//		if(this.op_Scene.getScene_pid().equals("-1")){
//			if(this.op_Scene.getScene_type() == 1){
//				this.op_Scene.setScene_rank(3);				
//			}else if(this.op_Scene.getScene_type() == 2){
//				this.op_Scene.setScene_type(3);
//			}else if(this.op_Scene.getScene_type() == 3){
//				this.op_Scene.setScene_type(3);
//			}else if(this.op_Scene.getScene_type() == 4){
//				this.op_Scene.setScene_type(3);
//			}
//		}else{
//			Op_Scene scene_pid = this.op_SceneService.findById(this.op_Scene.getScene_pid());
//			this.op_Scene.setScene_rank(scene_pid.getScene_rank()-1);			
//		}
		if(this.op_Scene.getArea_id().getArea_id().equals("-1")){
			this.op_Scene.setArea_id(null);
		}
		try {
			if(this.sceneImage != null){
				if(sceneImageFileName!=null && !sceneImageFileName.equals("")){
					//String uu = GetUUID.getUUID();
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			        String imageFileName = this.op_Scene.getScene_no() + "_" + df.format(new Date()) + getExtention(sceneImageFileName);
			        String url = ImageUrl.getInstance().getProperty("scene_img.url");
			        String port = ImageUrl.getInstance().getProperty("scene_img.port");
			        String username = ImageUrl.getInstance().getProperty("scene_img.username");
			        String password = ImageUrl.getInstance().getProperty("scene_img.password");
			        String path = ImageUrl.getInstance().getProperty("scene_img.path");
			        BufferedInputStream in = new BufferedInputStream(new FileInputStream(sceneImage),16*1024);
			        try{    			        	
			        	if(FtpClient.uploadFile(url, Integer.parseInt(port), username, password, path, imageFileName, in)){
					        this.op_Scene.setScene_image(imageFileName);
					        this.op_SceneService.save2(this.op_Scene);
					        this.op_UserInfo_Scene.setScene_id(this.op_Scene);
							this.op_UserInfo_Scene.setUser_id((Op_UserInfo)Struts2Utils.getSession().getAttribute("user"));
							this.op_UserInfo_SceneService.save(this.op_UserInfo_Scene);	
							addActionMessage("操作成功!");
							logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 添加 提交 操作成功");
			        	}else{
			        		addActionMessage("操作失败!添加场景图片时发生错误");
			        	}
			        }catch(Exception ex){ex.printStackTrace();addActionMessage("操作失败!");return "operationResult";}
				}else{
					this.op_SceneService.save2(this.op_Scene);
					this.op_UserInfo_Scene.setScene_id(this.op_Scene);
					this.op_UserInfo_Scene.setUser_id((Op_UserInfo)Struts2Utils.getSession().getAttribute("user"));
					this.op_UserInfo_SceneService.save(this.op_UserInfo_Scene);	
					addActionMessage("操作成功!");
					logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 添加 提交 操作成功");
				}
		 	}else{		 		
				this.op_SceneService.save2(this.op_Scene);				
				this.op_UserInfo_Scene.setScene_id(this.op_Scene);
				this.op_UserInfo_Scene.setUser_id((Op_UserInfo)Struts2Utils.getSession().getAttribute("user"));
				this.op_UserInfo_SceneService.save(this.op_UserInfo_Scene);				
				addActionMessage("操作成功!");	
				logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 添加 提交 操作成功");
		 	}    
        }catch (Exception e)  {
            e.printStackTrace();
            addActionMessage("操作失败!未知错误");return "operationResult";
        }	
		return "operationResult";
	}
    private static String getExtention(String fileName)  {
        int pos = fileName.lastIndexOf(".");
        return fileName.substring(pos);
    } 
    
    /**
     * 删除
     * @return
     * @author Wang_Yuliang
     * 0713 UP Wang_Yuliang 用户场景关联不做验证，改为级联删除
     */
    public String delete(){
		HttpServletRequest request = Struts2Utils.getRequest();
		//request.setAttribute("list", "op_scene_page.action?page.pageSize=10");
		
		if(this.gm_DeviceService.findByScene_id(this.op_Scene.getScene_id()).size()>0){
			addActionMessage("操作失败!请先删除该场景下的设备");
			return "operationResult";
		}
		if(this.gm_ChannelService.findByScene_id(this.op_Scene.getScene_id()).size()>0){
			addActionMessage("操作失败!请先删除该场景下的数据通道");
			return "operationResult";
		}
		if(this.gm_DevCtrlService.findByScene_id(this.op_Scene.getScene_id()).size()>0){
			addActionMessage("操作失败!请先删除该场景下的控制设备");
			return "operationResult";
		}
		if(this.op_PlantformUserService.findByScene_id(this.op_Scene.getScene_id()).size()>0){
			addActionMessage("操作失败!请先删除关联该场景的平台用户信息");
			return "operationResult";
		}
		//if(this.op_UserInfo_SceneService.findByScene_id(this.op_Scene.getScene_id()).size()>0){
		//	request.setAttribute("sms", "请先删除该场景与用户的关联信息");
		//	return "operationResult";
		//}
		if(this.op_SceneService.findByScene_pid(this.op_Scene.getScene_id()).size()>0){
			addActionMessage("操作失败!请先删除该场景的子场景");
			return "operationResult";
		}
    	try{
    		this.op_SceneService.deleteById_this(this.op_Scene.getScene_id());
    		addActionMessage("操作成功!");
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	return "operationResult";
    }    
    
    /**
     * 到编辑
     * @return
     * @author Wang_Yuliang
     */
    public String toEdit(){
    	this.op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
    	if(this.op_Scene.getScene_pid()!=null && !this.op_Scene.getScene_pid().equals("FF")){
        	Struts2Utils.getRequest().setAttribute("dis_scene_pid", this.op_SceneService.findById(this.op_Scene.getScene_pid()));
    	}
    	return "edit";
    }
	
    /**
     * 到编辑 0714
     * @return
     * @author Wang_Yuliang
     */
    public String toEdit_0714(){
    	this.op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
    	if(this.op_Scene.getScene_pid()!=null && !this.op_Scene.getScene_pid().equals("FF")){
        	Struts2Utils.getRequest().setAttribute("dis_scene_pid", this.op_SceneService.findById(this.op_Scene.getScene_pid()));
    	}
        String url = ImageUrl.getInstance().getProperty("scene_img.url");
        String port = ImageUrl.getInstance().getProperty("scene_img.port");
        String path = ImageUrl.getInstance().getProperty("scene_img.path");
        String scene_image_url = "ftp://" + url + ":" + port + "/" + path + "/groupImg.jpg";
    	if(this.op_Scene.getScene_image() != null && !this.op_Scene.getScene_image().equals("")){
    		scene_image_url = "ftp://" + url + ":" + port + "/" + path + "/" + this.op_Scene.getScene_image();
    	}
    	Struts2Utils.getRequest().setAttribute("scene_image_url", scene_image_url);
    	List<Op_scene_gtype> list = scene_gtypeService.findAll();
    	Struts2Utils.getRequest().setAttribute("sceneGtype", list);
    	return "edit_0714";
    }
    
    /**
     * 编辑
     * @return
     * @author Wang_Yuliang
     */

    public String edit(){//还有错
		HttpSession session = Struts2Utils.getSession();
		//request.setAttribute("list", "op_scene_page.action?page.pageSize=10");
		
		//验证场景是否已存在
		//if(!this.no.equals(this.op_Scene.getScene_no())){
		//	if(this.op_SceneService.isExist(this.op_Scene.getScene_no())){
		//		addActionMessage("操作失败!场景已存在");
		//		return "operationResult";
		//	}
		//}	
		//0714 UP Wang_Yuliang 如果场景编号 未改动 则可直接更新，如果修改了编号 则验证是否存在 不存在才能更新		
		//else{
		if(this.op_Scene.getArea_id().getArea_id().equals("-1")){
			this.op_Scene.setArea_id(null);
		}
		try {
			if(this.sceneImage != null){
				if(sceneImageFileName!=null && !sceneImageFileName.equals("")){
					//String uu = GetUUID.getUUID();
			        //String imageFileName = uu + getExtention(sceneImageFileName);
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String imageFileName = this.op_Scene.getScene_no() + "_" + df.format(new Date()) + getExtention(sceneImageFileName);
			        String url = ImageUrl.getInstance().getProperty("scene_img.url");
			        String port = ImageUrl.getInstance().getProperty("scene_img.port");
			        String username = ImageUrl.getInstance().getProperty("scene_img.username");
			        String password = ImageUrl.getInstance().getProperty("scene_img.password");
			        String path = ImageUrl.getInstance().getProperty("scene_img.path");
			        BufferedInputStream in = new BufferedInputStream(new FileInputStream(sceneImage),16*1024);
			        OutputStream out = null ;
			        try{                
			        	if(FtpClient.uploadFile(url, Integer.parseInt(port), username, password, path, imageFileName, in)){
					        this.op_Scene.setScene_image(imageFileName);
					        this.op_SceneService.update(this.op_Scene);
							addActionMessage("操作成功!");
							logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 编辑 提交 操作成功");
			        	}else{
			        		addActionMessage("操作失败!更新场景图片时发生错误");
			        	}
			        }catch(Exception ex){ ex.printStackTrace();addActionMessage("操作失败!");return "operationResult";}
			        finally{
			        	if(null != in){
			        		in.close();
			            }
			        	if(null != out){
			        		out.close();
			            } 
			        }
				}else{
					this.op_SceneService.update(this.op_Scene);
					addActionMessage("操作成功!");
					logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 编辑 提交 操作成功");
				}
		 	}else{
				this.op_SceneService.update(this.op_Scene);
				addActionMessage("操作成功!");
				logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 编辑 提交 操作成功");
		 	}    
        }catch (Exception e)  {
        	addActionMessage("操作失败!未知错误");
            e.printStackTrace();return "operationResult";
        }
        return "operationResult";
    }
    
    /**
     * 场景树
     * @return
     * @author Wang_Yuliang
     */
	public String sceneTree(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String scene_tree = (String)session.getAttribute("scene_tree");
			out.print(scene_tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
	 * 添加场景关联
	 * @return
	 * @author Wang_Yuliang
	 */
	public String addChild(){
		HttpSession session = Struts2Utils.getSession();
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html; charset=utf-8");		
		PrintWriter out = null; 
		try {
			out = response.getWriter();
			String scene_id_list[] = new String[0];
			if(this.scene_id_list.length()>0)scene_id_list = this.scene_id_list.split(",");
			for(int i=0;i<scene_id_list.length;i++){
				Op_Scene child = this.op_SceneService.findById(scene_id_list[i]);
				child.setScene_pid(this.op_Scene.getScene_id());
				this.op_SceneService.update(child);
			}			
			out.print("1");
			logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 添加场景关联 提交 操作成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("0");
		return null;		
	}
	
	/**
	 * 删除场景关联
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
			String scene_id_list[] = new String[0];
			if(this.scene_id_list.length()>0)scene_id_list = this.scene_id_list.split(",");
			for(int i=0;i<scene_id_list.length;i++){
				Op_Scene child = this.op_SceneService.findById(scene_id_list[i]);				
				child.setScene_pid("FF");
				this.op_SceneService.update(child);
			}			
			out.print("1");
			logger.info("用户"+session.getAttribute("userid")+" 场景信息管理 》 删除场景关联 提交 操作成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("0");
		return null;		
	}
	
	/**
	 * 指定用户，场景 查询同子类在用下一级场景
	 * @param op_Scene
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 */	
	public String findChildList(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			List<Op_Scene> childList = this.op_SceneService.findChildList(op_Scene, this.user_id);
			String json = "[";
			if(childList.size() > 0){
				for(Op_Scene child : childList){
					json += "{scene_id:\""+child.getScene_id()+"\",scene_no:\""+child.getScene_no()+"\",scene_name:\""+child.getScene_name()+"\"},";
				}
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 指定用户，场景 查询同子类在用子场景
	 * @param op_scene
	 * @param user_id
	 * @return
	 */
	public String findChildedList(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			List<Op_Scene> childList = this.op_SceneService.findChildedList(op_Scene, this.user_id);
			String json = "[";
			if(childList.size() > 0){
				for(Op_Scene child : childList){
					json += "{scene_id:\""+child.getScene_id()+"\",scene_no:\""+child.getScene_no()+"\",scene_name:\""+child.getScene_name()+"\"},";
				}
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 到场景监控 0518
	 * @return
	 * @author Wang_Yuliang、
	 * 据魏小华 0615需求 此函数弃用
	 */
	public String toSceneSO_0518(){	
		//Pro_Fisheries fisheries = pro_FisheriesService.findUniqueByProperty("scene.id", sceneId); 
		return "scene_so_0518";
	}
	
	/**
	 * 到场景监控 0531
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSceneSO_0531(){	
		return "scene_so_0531";
	}
	
	/**
	 * 到场景监控 0616
	 * @return
	 */
	public String toSceneSO_0616(){
		return "scene_so_0616";		
	}
	
	/**
	 * 到场景监控 0616
	 * @return
	 */
	public String toSceneSO_0715(){
		return "scene_so_0715";		
	}
	
	/**
	 * 到数据汇总 0523
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toDataCollect_0523(){
		return "data_collect_0523";
	}
	
	/**
	 * 到数据汇总 0613
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toDataCollect_0613(){
		return "data_collect_0613";
	}
	
	/**
	 * 到数据汇总
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toDataCollect(){
		return "data_collect";
	}
	
	/**
	 * 场景监控 指定场景ID 通道类型ID 查询平均值(不含子场景的通道) 时间检查-24，+1之间
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_findAvgByScene_idAndChtype_id(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String json = "{value:\"???\"}";
			//查询
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			if(op_Scene != null){
				List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findBySceneID(this.op_Scene.getScene_id());


				for (Gm_Channel gm_Channel : gm_Channel_list) {
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String beginTime = df.format(new Date().getTime()-24l*60l*60l*1000l);
				String endTime = df.format(new Date().getTime()+1l*60l*60l*1000l);
				json = "{value:\""+this.gm_ChannelService.findAvgByGm_Channel_listAndChtype_noAndCheckTime(gm_Channel_list,this.chtype_no,beginTime,endTime)+"\"}";
			}
			out.print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:\"???\"}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	
	public String test(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String json = "{value:\"???\"}";
			//查询
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			if(op_Scene != null){
				List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findBySceneID(this.op_Scene.getScene_id());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String beginTime = df.format(new Date().getTime()-24l*60l*60l*1000l);
				String endTime = df.format(new Date().getTime()+1l*60l*60l*1000l);
				
				List<Gm_Latestdata> gm_LatestdataList = gm_LatestdataService.findAllByChtypeandScene(this.op_Scene.getScene_id());
				
				json = "{value:\""+this.gm_ChannelService.findAvgByGm_Channel_listAndChtype_noAndCheckTime(gm_Channel_list,this.chtype_no,beginTime,endTime)+"\"}";
			}
			out.print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:\"???\"}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 场景监控 指定场景ID 通道类型ID 查询最小值(不含子场景的通道) 时间检查-24，+1之间
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_findMinByScene_idAndChtype_id(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String json = "{value:\"???\"}";
			//查询
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			if(op_Scene != null){
				List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findBySceneID(this.op_Scene.getScene_id());
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String beginTime = df.format(new Date().getTime()-24l*60l*60l*1000l);
				String endTime = df.format(new Date().getTime()+1l*60l*60l*1000l);
				json = "{value:\""+this.gm_ChannelService.findMinByGm_Channel_listAndChtype_noAndCheckTime(gm_Channel_list,this.chtype_no,beginTime,endTime)+"\"}";
			}
			out.print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:\"???\"}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 场景监控 指定场景id 查询场景中的采集量
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String json_findChannelBySceneID(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndCh_offerSer(op_Scene.getScene_id());
			String json = "[";
			if(gm_Channel_list.size()>0){
				for(Gm_Channel gm_Channel : gm_Channel_list)
				{
					Gm_Latestdata gm_Latestdata = this.gm_LatestdataService.findByCh_id(gm_Channel.getCh_id());
					String hida_corrValue = "???";
					String hida_record_time = "???";
					if(gm_Latestdata != null){						
						if(gm_Latestdata.getHida_corrValue() != null){
							Double hida_corrValue_temp = gm_Latestdata.getHida_corrValue();
							String formatString = "0.0";
							if(gm_Channel.getCh_dectDig() != null){
								if(gm_Channel.getCh_dectDig()>1){
									for(int i=1;i<gm_Channel.getCh_dectDig();i++){
										formatString += "0";
									}
								}
							}
							DecimalFormat f = new DecimalFormat(formatString);							
							hida_corrValue = f.format(hida_corrValue_temp);
							if(gm_Latestdata.getHida_record_time()!=null){
								hida_record_time = df.format(gm_Latestdata.getHida_record_time());
							}
						}						
					}	
					json += "{ch_no:\"" + gm_Channel.getCh_no() + "\",ch_name:\"" + gm_Channel.getCh_name() + "\",chtype_no:\""+gm_Channel.getChtype_id().getChtype_no()+"\",ch_corrUnit:\"" + gm_Channel.getCh_corrUnit() + "\",hida_corrValue:\"" + hida_corrValue + "\",hida_record_time:\""+hida_record_time+"\"},";
				}
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (IOException e) {
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
	 * 场景监控 指定场景id 查询场景中的所有的对外提供服务和不对外提供服务的采集量
	 * @return json
	 * @author weixiaohua
	 */
	public String json_findChannelBySceneIDS(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndCh_offerSerIsOrNot(op_Scene.getScene_id());
			String json = "[";
			if(gm_Channel_list.size()>0){
				for(Gm_Channel gm_Channel : gm_Channel_list)
				{
					Gm_Latestdata gm_Latestdata = this.gm_LatestdataService.findByCh_id(gm_Channel.getCh_id());
					String hida_corrValue = "???";
					String hida_record_time = "???";
					if(gm_Latestdata != null){						
						if(gm_Latestdata.getHida_corrValue() != null){
							Double hida_corrValue_temp = gm_Latestdata.getHida_corrValue();
							String formatString = "0.0";
							if(gm_Channel.getCh_dectDig() != null){
								if(gm_Channel.getCh_dectDig()>1){
									for(int i=1;i<gm_Channel.getCh_dectDig();i++){
										formatString += "0";
									}
								}
							}
							DecimalFormat f = new DecimalFormat(formatString);							
							hida_corrValue = f.format(hida_corrValue_temp);
							if(gm_Latestdata.getHida_record_time()!=null){
								hida_record_time = df.format(gm_Latestdata.getHida_record_time());
							}
						}						
					}	
					json += "{ch_no:\"" + gm_Channel.getCh_no() + "\",ch_name:\"" + gm_Channel.getCh_name() + "\",chtype_no:\""+gm_Channel.getChtype_id().getChtype_no()+"\",ch_corrUnit:\"" + gm_Channel.getCh_corrUnit() + "\",hida_corrValue:\"" + hida_corrValue + "\",hida_record_time:\""+hida_record_time+"\"},";
				}
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	 * 场景监控 指定场景id 查询场景中的采集量
	 * @return json
	 * @author Wang_Yuliang
	 */
//	public String json_findChannelBySceneID_0524_UP(){
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		HttpSession session = Struts2Utils.getSession();
//		try {
//			HttpServletResponse response = Struts2Utils.getResponse();
//			response.setContentType("text/html; charset=utf-8");
//			PrintWriter out = response.getWriter();
//			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
//			List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndCh_offerSer(op_Scene.getScene_id());
//			String json = "[";
//			if(gm_Channel_list.size()>0){
//				for(Gm_Channel gm_Channel : gm_Channel_list)
//				{
//					Gm_Latestdata gm_Latestdata = this.gm_LatestdataService.findByCh_id(gm_Channel.getCh_id());
//					String hida_corrValue = "未知";
//					String hida_record_time = "未知";
//					if(gm_Latestdata != null){						
//						if(gm_Latestdata.getHida_corrValue() != null){
//							String hida_corrValue_temp = gm_Latestdata.getHida_corrValue()+"";
//							if(gm_Channel.getCh_dectDig() != null){
//								hida_corrValue_temp = hida_corrValue_temp.substring(0,hida_corrValue_temp.indexOf(".")) + hida_corrValue_temp.substring(hida_corrValue_temp.indexOf("."),(hida_corrValue_temp.indexOf(".")+1+gm_Channel.getCh_dectDig()));
//							}else{							
//								hida_corrValue_temp = hida_corrValue_temp.substring(0,hida_corrValue_temp.indexOf(".")) + hida_corrValue_temp.substring(hida_corrValue_temp.indexOf("."),(hida_corrValue_temp.indexOf(".")+2));
//							}
//							hida_corrValue = hida_corrValue_temp;
//							if(gm_Latestdata.getHida_record_time()!=null){
//								hida_record_time = df.format(gm_Latestdata.getHida_record_time());
//							}
//						}						
//					}	
//					json += "{ch_no:\"" + gm_Channel.getCh_no() + "\",ch_name:\"" + gm_Channel.getCh_name() + "\",chtype_no:\""+gm_Channel.getChtype_id().getChtype_no()+"\",ch_corrUnit:\"" + gm_Channel.getCh_corrUnit() + "\",hida_corrValue:\"" + hida_corrValue + "\",hida_record_time:\""+hida_record_time+"\"},";
//				}
//				json = json.substring(0,json.length()-1);
//			}
//			out.print(json+"]");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			try {
//				HttpServletResponse response = Struts2Utils.getResponse();
//				response.setContentType("text/html; charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.print("[]");
//			}catch(Exception ex){ex.printStackTrace();}	
//		}
//		return null;
//	}
	
	/**
	 * 未使用 指定场景ID 查询场景及其子场景中通道的历史数据，
	 * @return json for Highcharts series
	 * 			series: [
	 * 						{name: 'aa',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},			
	 * 						{name: 'bb',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},
	 * 					] 
	 * @author Wang_Yuliang
	 * 2011-05-19 11:15 弃用
	 */
	public String json_findHistoryBySceneID(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			//获得 x坐标		
			List<String> _24hours = DateTools.find_24hours();
			String json = "{x:[";
			for(String h : _24hours){
				json += "'"+h.substring(0,16)+"',";
			}
			json = json.substring(0,json.length()-1);
			json += "],";
			//获得采集通道ID
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());			
			json += "lines:[";
			if(op_Scene != null){
				List<String> scene_id_arr = new ArrayList<String>();
				scene_id_arr.add(op_Scene.getScene_id());
				scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr);
				List<Gm_Channel> gm_Channel_list = new ArrayList<Gm_Channel>();	
				for(String scene_id : scene_id_arr){
					List<Gm_Channel> gm_Channel_list_tmp = this.gm_ChannelService.findBySceneID(scene_id);
					gm_Channel_list.addAll(gm_Channel_list_tmp);
				}
				if(gm_Channel_list.size() > 0){
					for(Gm_Channel gm_Channel : gm_Channel_list){
						json += "{name:'" + gm_Channel.getCh_name()+"-"+gm_Channel.getCh_no() + "',marker: {symbol: 'square'},data: [";						
						for(String h : _24hours){
							String value = "0";
							value = this.gm_ChannelService.findHisAvgByCh_idAndBeginTimeAndEndTime(gm_Channel.getCh_id(),h,h.substring(0,14)+"59:59");
							json += value + ",";
						}
						json = json.substring(0,json.length()-1);
						json += "]},";
					}
					json = json.substring(0,json.length()-1);
				}
			}
			out.print(json+"]}");			
		} catch (Exception e) {
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
	 * 未使用 场景监控 指定场景ID 查询场景中通道的历史数据
	 * @return json 
	 * 		{
	 * 			x:['',...]
	 * 			lines: [
	 * 						{name: 'aa',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},			
	 * 						{name: 'bb',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},
	 * 					] 
	 * @author Wang_Yuliang
	 */
	public String json_findHistoryBySceneID_0(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			//获得 x坐标		
			List<String> _24hours = DateTools.find_24hours();
			String json = "{x:[";
			for(String h : _24hours){
				json += "'"+h.substring(0,16)+"',";
			}
			json = json.substring(0,json.length()-1);
			json += "],";
			//获得采集通道ID
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());			
			json += "lines:[";
			if(op_Scene != null){
				List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findBySceneID(op_Scene.getScene_id());
				if(gm_Channel_list.size() > 0){
					for(Gm_Channel gm_Channel : gm_Channel_list){
						json += "{name:'" + gm_Channel.getCh_name()+"-"+gm_Channel.getCh_no() + "',marker: {symbol: 'square'},data: [";						
						for(String h : _24hours){
							String value = "0";
							value = this.gm_ChannelService.findHisAvgByCh_idAndBeginTimeAndEndTime(gm_Channel.getCh_id(),h,h.substring(0,14)+"59:59");
							json += value + ",";
						}
						json = json.substring(0,json.length()-1);
						json += "]},";
					}
					json = json.substring(0,json.length()-1);
				}
			}
			out.print(json+"]}");			
		} catch (Exception e) {
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
	 * 未使用 场景监控_0518 指定场景ID 查询场景中水温和溶解氧的最近144个值
	 * @return json 
	 * 		{
	 * 			x:['',...]
	 * 			lines: [
	 * 						{name: 'aa',visible:boolean,marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23],id:''},			
	 * 						{name: 'bb',visible:boolean,marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},
	 * 					] 
	 * @author Wang_Yuliang
	 * 据魏小华 0615需求 此方法弃用 Wang_Yuliang 0616UP
	 */
	public String json_findHistoryBySceneID_0518(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			//获得采集通道ID
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());			
			String json = "[";
			if(op_Scene != null){
				List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findBySceneID(op_Scene.getScene_id());
				if(gm_Channel_list.size() > 0){
					for(Gm_Channel gm_Channel : gm_Channel_list){
						if(gm_Channel.getChtype_id()!=null){
							if(gm_Channel.getChtype_id().getChtype_no().equals("ST10-11")){
								json += "{name:'"+gm_Channel.getCh_no()+"("+gm_Channel.getCh_name()+")"+"',visible:false,marker: {symbol: 'square'},data:"+this.gm_HistorydataService.findLimit144ByCh_id(gm_Channel.getCh_id())+"},";							
							}
							if(gm_Channel.getChtype_id().getChtype_no().equals("DO10-O")){
								json += "{name:'"+gm_Channel.getCh_no()+"("+gm_Channel.getCh_name()+")"+"',visible:true,marker: {symbol: 'square'},data:"+this.gm_HistorydataService.findLimit144ByCh_id(gm_Channel.getCh_id())+"},";	
							}
						}
					}
					json = json.substring(0,json.length()-1);
				}
			}
			out.print(json+"]");			
		} catch (Exception e) {
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
	 * 场景监控 指定场景ID 通道应用类型编号 查询场景中指定应用类型的最近144个值
	 * @return json 
	 * 		{
	 * 			x:['',...]
	 * 			lines: [
	 * 						{name: 'aa',visible:boolean,marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23],id:''},			
	 * 						{name: 'bb',visible:boolean,marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},
	 * 					] 
	 * @author Wang_Yuliang
	 */
	public String json_findHistoryBySceneIDAndChtype_no(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			//获得采集通道ID
			String json = CacheService.getJsonBySceneIdChType(op_Scene.getScene_id(), chtype_no);
			out.print(json);			
		} catch (Exception e) {
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
	 * 场景监控_0531 指定场景ID 查询场景中空气温度、空气湿度、太阳辐射、大气压强的最近144个值
	 * @return
	 */
	public String json_findHistoryBySceneID_0531(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());			
			String json = "[";
			if(op_Scene != null){
				List<Gm_Channel> gm_ChannelList = this.gm_ChannelService.findByScene_id(op_Scene.getScene_id());
				if(gm_ChannelList.size() > 0){
					for (Gm_Channel gm_Channel : gm_ChannelList) {
						if(gm_Channel.getChtype_id() != null){
							//空气温度
							if(gm_Channel.getChtype_id().getChtype_no().equals("KQSD1201-T")){
								json += "{name:'"+gm_Channel.getCh_no()+"("+gm_Channel.getCh_name()+")"+"',marker: {symbol: 'square'},data:"+this.gm_HistorydataService.findLimit144ByCh_id(gm_Channel.getCh_id())+"},";
							}
							//空气湿度
							if(gm_Channel.getChtype_id().getChtype_no().equals("KQSD1201-H")) {
								json += "{name:'"+gm_Channel.getCh_no()+"("+gm_Channel.getCh_name()+")"+"',marker: {symbol: 'square'},data:"+this.gm_HistorydataService.findLimit144ByCh_id(gm_Channel.getCh_id())+"},";
							}
							//太阳辐射
							if(gm_Channel.getChtype_id().getChtype_no().equals("RAD-10")) {
								json += "{name:'"+gm_Channel.getCh_no()+"("+gm_Channel.getCh_name()+")"+"',marker: {symbol: 'square'},data:"+this.gm_HistorydataService.findLimit144ByCh_id(gm_Channel.getCh_id())+"},";
							}
							//大气压强
							if(gm_Channel.getChtype_id().getChtype_no().equals("PA-P")) {
								json += "{name:'"+gm_Channel.getCh_no()+"("+gm_Channel.getCh_name()+")"+"',marker: {symbol: 'square'},data:"+this.gm_HistorydataService.findLimit144ByCh_id(gm_Channel.getCh_id())+"},";
							}
						}
					}
				}
			}
			if(json.length() > 0){
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (IOException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 场景监控 指定场景id 查询场景中的控制量
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String json_findDevCtrlBySceneID(){
		/** 0617UP 据魏小华需求 0615 第6款 UP  --begin
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			List<Gm_DevCtrl> gm_DevCtrl_list = this.gm_DevCtrlService.findByScene_idAndCh_offerSer(op_Scene.getScene_id());
			String json = "[";
			if(gm_DevCtrl_list.size()>0){
				for(Gm_DevCtrl gm_DevCtrl : gm_DevCtrl_list)
				{
					Gm_DevCtrlSts gm_DevCtrlSts = this.gm_DevCtrlStsService.findByDect_id(gm_DevCtrl.getDect_id());
					String dect_state = "null";
					String decst_valid = "null";
					if(gm_DevCtrlSts != null){
						dect_state = gm_DevCtrlSts.getDect_state() + "";
						decst_valid = gm_DevCtrlSts.getDecst_valid() + "";
					}
					String json_btns = this.op_DevCtrlBtnService.json_findByDect_id(gm_DevCtrl.getDect_id());
					String decttype_id = "null";
					if(gm_DevCtrl.getDecttype_id() != null){
						decttype_id = "{decttype_No:'"+gm_DevCtrl.getDecttype_id().getDecttype_no()+"',decttype_Img:'"+gm_DevCtrl.getDecttype_id().getDecttype_img()+"'}";
					}
					String dev_id = "null";
					if(gm_DevCtrl.getDev_id()!=null){
						dev_id = "'"+gm_DevCtrl.getDev_id().getDev_id()+"'";
					}
					String deco_sort = "";
					Gm_DevCtrlOperate gm_DevCtrlOperate = this.gm_DevCtrlOperateService.findByDect_id(gm_DevCtrl.getDect_id());
					if(gm_DevCtrlOperate!=null){
						if(gm_DevCtrlOperate.getDeco_sort()!=null){
							if(gm_DevCtrlOperate.getDeco_sort()==0){
								deco_sort = "未知";
							}else if(gm_DevCtrlOperate.getDeco_sort()==1){
								deco_sort = "定时";
							}else if(gm_DevCtrlOperate.getDeco_sort()==2){
								deco_sort = "自动";
							}else if(gm_DevCtrlOperate.getDeco_sort()==3){
								deco_sort = "遥控";
							}else if(gm_DevCtrlOperate.getDeco_sort()==4){
								deco_sort = "手动";
							}else if(gm_DevCtrlOperate.getDeco_sort()==5){
								deco_sort = "异常保护";
							}else if(gm_DevCtrlOperate.getDeco_sort()==6){
								deco_sort = "非法控制";
							}else if(gm_DevCtrlOperate.getDeco_sort()==7){
								deco_sort = "手机";
							}
						}
					}
					json += "{dect_id:'"+gm_DevCtrl.getDect_id()+"',desc_no:'"+gm_DevCtrl.getDect_no()+"',dect_name:'"+gm_DevCtrl.getDect_name()+"',decttype_id:"+decttype_id+",dev_id:"+dev_id+",dect_state:"+dect_state+",decst_valid:"+decst_valid+",btns:"+json_btns+",deco_sort:'"+deco_sort+"'},";
				}
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
		*/
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Op_Scene op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			List<Gm_DevCtrl> gm_DevCtrl_list = this.gm_DevCtrlService.findByScene_idAndCh_offerSer(op_Scene.getScene_id());
			String json = "[";
			if(gm_DevCtrl_list.size()>0){
				for(Gm_DevCtrl gm_DevCtrl : gm_DevCtrl_list)
				{
					Gm_DevCtrlSts gm_DevCtrlSts = this.gm_DevCtrlStsService.findByDect_id(gm_DevCtrl.getDect_id());
					String dect_state = "null";
					String decst_valid = "null";
					String deco_sort = "未知";
					if(gm_DevCtrlSts != null){
						dect_state = gm_DevCtrlSts.getDect_state() + "";
						decst_valid = gm_DevCtrlSts.getDecst_valid() + "";
						if(gm_DevCtrlSts.getDeco_sort()!=null){
							if(gm_DevCtrlSts.getDeco_sort()==0){
								deco_sort = "未知";
							}else if(gm_DevCtrlSts.getDeco_sort()==1){
								deco_sort = "定时";
							}else if(gm_DevCtrlSts.getDeco_sort()==2){
								deco_sort = "自动";
							}else if(gm_DevCtrlSts.getDeco_sort()==3){
								deco_sort = "遥控";
							}else if(gm_DevCtrlSts.getDeco_sort()==4){
								deco_sort = "手动";
							}else if(gm_DevCtrlSts.getDeco_sort()==5){
								deco_sort = "异常保护";
							}else if(gm_DevCtrlSts.getDeco_sort()==6){
								deco_sort = "强制控制";
							}else if(gm_DevCtrlSts.getDeco_sort()==7){
								deco_sort = "手机控制";
							}else if(gm_DevCtrlSts.getDeco_sort()==8){
								deco_sort = "未操作";
							}else if(gm_DevCtrlSts.getDeco_sort()==9){
								deco_sort = "短信控制";
							}
						}
					}
					String json_btns = this.op_DevCtrlBtnService.json_findByDect_id(gm_DevCtrl.getDect_id());
					String decttype_id = "null";
					if(gm_DevCtrl.getDecttype_id() != null){
						decttype_id = "{decttype_No:'"+gm_DevCtrl.getDecttype_id().getDecttype_no()+"',decttype_Img:'"+gm_DevCtrl.getDecttype_id().getDecttype_img()+"'}";
					}
					String dev_id = "null";
					if(gm_DevCtrl.getDev_id()!=null){
						dev_id = "'"+gm_DevCtrl.getDev_id().getDev_id()+"'";
					}
					json += "{dect_id:'"+gm_DevCtrl.getDect_id()+"',desc_no:'"+gm_DevCtrl.getDect_no()+"',dect_name:'"+gm_DevCtrl.getDect_name()+"',decttype_id:"+decttype_id+",dev_id:"+dev_id+",dect_state:"+dect_state+",decst_valid:"+decst_valid+",btns:"+json_btns+",deco_sort:'"+deco_sort+"'},";
				}
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
		//0617UP 据魏小华需求 0615 第6款 UP --end
	}
	
	/**
	 * 数据汇总 0523
	 * @return json scene array
	 * 	[
	 * 		{
	 * 			scene_id:'',
	 * 			scene_no:'',
	 * 			scene_name:'',
	 * 			time:'',
	 * 			DO10_O:'',
	 * 			ST10_11:'',
	 * 			SS_1:'',
	 * 			PH_1:'',
	 * 			DDL_1:'',
	 * 			ZD_1:'',
	 * 			YLS_1:''
	 * 		} 	
	 * 		...
	 * 	]
	 * @author Wang_Yuliang
	 */
	public String json_data_collect_0523(){
		HttpSession session = Struts2Utils.getSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = Struts2Utils.getSession().getAttribute("userid")+"";
			//与用户相关的场景id
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			String json = "";
			//获得采集通道ID
			Op_Scene root_scene = this.op_SceneService.findById(this.op_Scene.getScene_id());			
			json += "[";
			if(root_scene != null){
				List<String> scene_id_arr = new ArrayList<String>();
				scene_id_arr.add(root_scene.getScene_id());
				scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr,scene_id_list);
				for(String scene_id : scene_id_arr){
					Op_Scene op_Scene = this.op_SceneService.findById(scene_id);
					if(op_Scene != null){
						//Wang_Yuliang 0613UP 据《宜兴水产项目需求确认--魏晓华-20110612.docx》 第11款  --begin
						List<Gm_Channel> gm_Channel_list_all = this.gm_ChannelService.findByScene_id(op_Scene.getScene_id());
						if(gm_Channel_list_all.size()==0){
							continue;
						}
						// --end
						json += "{";
						json += "scene_id:'"+op_Scene.getScene_id()+"',";
						json += "scene_no:'"+op_Scene.getScene_no()+"',";
						json += "scene_name:'"+op_Scene.getScene_name()+"',";
						String time = "";
						String DO10_O = "";
						String D010_0_MAX = "";
						String D010_0_MIN = "";
						String ST10_11 = "";
						String DO10_T = "";
						String SS_1 = "";
						String PH_1 = "";
						String DDL_1 = "";
						String ZD_1 = "";
						String YLS_1 = "";
						Op_ChannelType op_ChannelType_DO10_O = this.op_ChannelTypeService.findByChtype_no("DO10-O");
						if(op_ChannelType_DO10_O!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_DO10_O.getChtype_id());
							DO10_O = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
							D010_0_MAX = this.gm_LatestdataService.findMaxByGm_Channel_list(gm_Channel_list);
							D010_0_MIN = this.gm_LatestdataService.findMinByGm_Channel_list(gm_Channel_list);
							for(Gm_Channel gm_Channel_fortime : gm_Channel_list){
								if(gm_Channel_fortime!=null){
									Gm_Latestdata gm__Latestdata_fortime = this.gm_LatestdataService.findByCh_id(gm_Channel_fortime.getCh_id());
									if(gm__Latestdata_fortime!=null && gm__Latestdata_fortime.getHida_record_time()!=null){
										time = df.format(gm__Latestdata_fortime.getHida_record_time());
										break;
									}
								}
							}
						}
						Op_ChannelType op_ChannelType_ST10_11 = this.op_ChannelTypeService.findByChtype_no("ST10-11");
						if(op_ChannelType_ST10_11!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_ST10_11.getChtype_id());
							ST10_11 = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
						}
						Op_ChannelType op_ChannelType_DO10_T = this.op_ChannelTypeService.findByChtype_no("DO10-T");
						if(op_ChannelType_DO10_T!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_DO10_T.getChtype_id());
							DO10_T = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
						}
						Op_ChannelType op_ChannelType_SS_1 = this.op_ChannelTypeService.findByChtype_no("SS-1");
						if(op_ChannelType_ST10_11!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_SS_1.getChtype_id());
							SS_1 = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
						}
						Op_ChannelType op_ChannelType_PH_1 = this.op_ChannelTypeService.findByChtype_no("PH-1");
						if(op_ChannelType_PH_1!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_PH_1.getChtype_id());
							PH_1 = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
						}
						Op_ChannelType op_ChannelType_DDL_1 = this.op_ChannelTypeService.findByChtype_no("DDL-1");
						if(op_ChannelType_DDL_1!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_DDL_1.getChtype_id());
							DDL_1 = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
						}
						Op_ChannelType op_ChannelType_ZD_1 = this.op_ChannelTypeService.findByChtype_no("ZD-1");
						if(op_ChannelType_ZD_1!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_ZD_1.getChtype_id());
							ZD_1 = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
						}
						Op_ChannelType op_ChannelType_YLS_1 = this.op_ChannelTypeService.findByChtype_no("YLS-1");
						if(op_ChannelType_YLS_1!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_YLS_1.getChtype_id());
							YLS_1 = this.gm_LatestdataService.findAvgByGm_Channel_list(gm_Channel_list);
						}
						json += "time:'"+time+"',";
						json += "DO10_O:'"+DO10_O+"',";
						json += "DO10_O_MAX:'"+D010_0_MAX+"',";
						json += "DO10_O_MIN:'"+D010_0_MIN+"',";
						json += "ST10_11:'"+ST10_11+"',";
						json += "DO10_T:'"+DO10_T+"',";
						json += "SS_1:'"+SS_1+"',";
						json += "PH_1:'"+PH_1+"',";
						json += "DDL_1:'"+DDL_1+"',";
						json += "ZD_1:'"+ZD_1+"',";
						json += "YLS_1:'"+YLS_1+"'";
						json += "},";
					}
				}				
			}
			if(json.length()>1){
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");			
		} catch (Exception e) {
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
	 * 数据汇总 0613
	 * @return json scene array
	 * 	[
	 * 		{
	 * 			scene_id:'',
	 * 			scene_no:'',
	 * 			scene_name:'',
	 * 			time:'',
	 * 			1200_S:'',
	 * 			1200_D:'',
	 * 			KQSD1201_T:'',
	 * 			KQSD1201_H:'',
	 * 			RAD_10:'',
	 * 			PA_P:'',
	 * 		} 	
	 * 		...
	 * 	]
	 * @author Wang_Yuliang
	 */
	public String json_data_collect_0613(){
		HttpSession session = Struts2Utils.getSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = Struts2Utils.getSession().getAttribute("userid")+"";
			//与用户相关的场景id
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			String json = "";
			//获得采集通道ID
			Op_Scene root_scene = this.op_SceneService.findById(this.op_Scene.getScene_id());			
			json += "[";
			if(root_scene != null){
				List<String> scene_id_arr = new ArrayList<String>();
				scene_id_arr.add(root_scene.getScene_id());
				scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr,scene_id_list);
				for(String scene_id : scene_id_arr){
					Op_Scene op_Scene = this.op_SceneService.findById(scene_id);
					if(op_Scene != null){
						//Wang_Yuliang 0613UP 据《宜兴水产项目需求确认--魏晓华-20110612.docx》 第11款  --begin
						List<Gm_Channel> gm_Channel_list_all = this.gm_ChannelService.findByScene_id(op_Scene.getScene_id());
						if(gm_Channel_list_all.size()==0){
							continue;
						}
						// --end
						json += "{";
						json += "scene_id:'"+op_Scene.getScene_id()+"',";
						json += "scene_no:'"+op_Scene.getScene_no()+"',";
						json += "scene_name:'"+op_Scene.getScene_name()+"',";
						String time = "";
						String _1200_S = "";
						String _1200_D = "";
						String KQSD1201_T = "";
						String KQSD1201_H = "";
						String RAD_10 = "";
						String PA_P = "";
						Op_ChannelType op_ChannelType_1200_S = this.op_ChannelTypeService.findByChtype_no("1200-S");
						if(op_ChannelType_1200_S!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_1200_S.getChtype_id());
							if(gm_Channel_list != null && gm_Channel_list.size()>0){
								Gm_Channel gm_Channel_1200_S = gm_Channel_list.get(0);
								if(gm_Channel_1200_S != null && gm_Channel_1200_S.getCh_id() != null){
									Gm_Latestdata gm_Latestdata_1200_S = this.gm_LatestdataService.findByCh_id(gm_Channel_1200_S.getCh_id());
									if(gm_Latestdata_1200_S.getHida_corrValue()!=null){
										_1200_S = gm_Latestdata_1200_S.getHida_corrValue()+"";
									}
									if(gm_Latestdata_1200_S.getHida_record_time()!=null){
										time = df.format(gm_Latestdata_1200_S.getHida_record_time());
									}
								}
							}
						}
						Op_ChannelType op_ChannelType_1200_D = this.op_ChannelTypeService.findByChtype_no("1200-D");
						if(op_ChannelType_1200_D!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_1200_D.getChtype_id());
							if(gm_Channel_list != null && gm_Channel_list.size()>0){
								Gm_Channel gm_Channel_1200_D = gm_Channel_list.get(0);
								if(gm_Channel_1200_D != null && gm_Channel_1200_D.getCh_id() != null){
									Gm_Latestdata gm_Latestdata_1200_D = this.gm_LatestdataService.findByCh_id(gm_Channel_1200_D.getCh_id());
									if(gm_Latestdata_1200_D.getHida_corrValue()!=null){
										_1200_D = gm_Latestdata_1200_D.getHida_corrValue()+"";
									}
								}
							}
						}
						Op_ChannelType op_ChannelType_KQSD1201_T = this.op_ChannelTypeService.findByChtype_no("KQSD1201-T");
						if(op_ChannelType_KQSD1201_T!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_KQSD1201_T.getChtype_id());
							if(gm_Channel_list != null && gm_Channel_list.size()>0){
								Gm_Channel gm_Channel_KQSD1201_T = gm_Channel_list.get(0);
								if(gm_Channel_KQSD1201_T != null && gm_Channel_KQSD1201_T.getCh_id() != null){
									Gm_Latestdata gm_Latestdata_KQSD1201_T = this.gm_LatestdataService.findByCh_id(gm_Channel_KQSD1201_T.getCh_id());
									if(gm_Latestdata_KQSD1201_T.getHida_corrValue()!=null){
										KQSD1201_T = gm_Latestdata_KQSD1201_T.getHida_corrValue()+"";
									}
								}
							}
						}
						Op_ChannelType op_ChannelType_KQSD1201_H = this.op_ChannelTypeService.findByChtype_no("KQSD1201-H");
						if(op_ChannelType_KQSD1201_H!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_KQSD1201_H.getChtype_id());
							if(gm_Channel_list != null && gm_Channel_list.size()>0){
								Gm_Channel gm_Channel_KQSD1201_H = gm_Channel_list.get(0);
								if(gm_Channel_KQSD1201_H != null && gm_Channel_KQSD1201_H.getCh_id() != null){
									Gm_Latestdata gm_Latestdata_KQSD1201_H = this.gm_LatestdataService.findByCh_id(gm_Channel_KQSD1201_H.getCh_id());
									if(gm_Latestdata_KQSD1201_H.getHida_corrValue()!=null){
										KQSD1201_H = gm_Latestdata_KQSD1201_H.getHida_corrValue()+"";
									}
								}
							}
						}
						Op_ChannelType op_ChannelType_RAD_10 = this.op_ChannelTypeService.findByChtype_no("RAD-10");
						if(op_ChannelType_RAD_10!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_RAD_10.getChtype_id());
							if(gm_Channel_list != null && gm_Channel_list.size()>0){
								Gm_Channel gm_Channel_RAD_10 = gm_Channel_list.get(0);
								if(gm_Channel_RAD_10 != null && gm_Channel_RAD_10.getCh_id() != null){
									Gm_Latestdata gm_Latestdata_RAD_10 = this.gm_LatestdataService.findByCh_id(gm_Channel_RAD_10.getCh_id());
									if(gm_Latestdata_RAD_10.getHida_corrValue()!=null){
										RAD_10 = gm_Latestdata_RAD_10.getHida_corrValue()+"";
									}
								}
							}
						}
						Op_ChannelType op_ChannelType_PA_P = this.op_ChannelTypeService.findByChtype_no("PA-P");
						if(op_ChannelType_PA_P!=null){
							List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findByScene_idAndChtype_id(op_Scene.getScene_id(), op_ChannelType_PA_P.getChtype_id());
							if(gm_Channel_list != null && gm_Channel_list.size()>0){
								Gm_Channel gm_Channel_PA_P = gm_Channel_list.get(0);
								if(gm_Channel_PA_P != null && gm_Channel_PA_P.getCh_id() != null){
									Gm_Latestdata gm_Latestdata_PA_P = this.gm_LatestdataService.findByCh_id(gm_Channel_PA_P.getCh_id());
									if(gm_Latestdata_PA_P.getHida_corrValue()!=null){
										PA_P = gm_Latestdata_PA_P.getHida_corrValue()+"";
									}
								}
							}
						}
						json += "time:'"+time+"',";
						json += "_1200_S:'"+_1200_S+"',";
						json += "_1200_D:'"+_1200_D+"',";
						json += "KQSD1201_T:'"+KQSD1201_T+"',";
						json += "KQSD1201_H:'"+KQSD1201_H+"',";
						json += "RAD_10:'"+RAD_10+"',";
						json += "PA_P:'"+PA_P+"'";
						json += "},";
					}
				}				
			}
			if(json.length()>1){
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");			
		} catch (Exception e) {
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
	
	public String json_findById(){
		HttpSession session = Struts2Utils.getSession();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			this.op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			if(op_Scene!=null){
				if(op_Scene.getScene_id()!=null){
					String json = "{\"scene_id\":\""+op_Scene.getScene_id()+"\",\"scene_name\":\""+op_Scene.getScene_name()+"\",\"scene_no\":\""+op_Scene.getScene_no()+"\",\"scene_ctype\":\""+op_Scene.getScene_ctype()+"\",\"scene_rank\":\""+op_Scene.getScene_rank()+"\",\"scene_gtype\":\""+op_Scene.getScene_gtype()+"\",\"scene_videoUrl\":\""+op_Scene.getScene_videoUrl()+"\"}";
					out.print(json);
				}else{
					out.print("null");
				}
			}else{
				out.print("null");
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("null");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	
	
	public String findChannelInformation(){
		try
		{
			HttpServletResponse response = Struts2Utils.getResponse();
			
			response.setContentType("text/html; charset=utf-8");
			
			String json = "[";
			
			PrintWriter out = response.getWriter();
			
			this.op_Scene = this.op_SceneService.findById(this.op_Scene.getScene_id());
			
			if(op_Scene!=null){
				
				List<String> ch_idList = this.gm_ChannelService.findGm_ChannelByScene_id(op_Scene.getScene_id());
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String beginTime = df.format(new Date().getTime()-24l*60l*60l*1000l);
				String endTime = df.format(new Date().getTime()+1l*60l*60l*1000l);
				
				List<Gm_Latestdata> gm_LatestdataList = this.gm_LatestdataService.findGm_LatestdataByCh_id(ch_idList,beginTime,endTime);
				
				for (Gm_Latestdata gm_Latestdata : gm_LatestdataList)
				{
					if(gm_Latestdata != null){
						json += "{\"name\":\""+gm_Latestdata.getCh_id().getCh_name()+"\",\"value\":\""+gm_Latestdata.getHida_corrValue() + gm_Latestdata.getCh_id().getCh_corrUnit() +"\",\"time\":\""+gm_Latestdata.getHida_record_time().toLocaleString()+ "\"},";
					}else {

					}
				}
			}else
			{
				out.print("null");
			}
			
			if(json.length() > 1)
			{
				json = json.substring(0,json.length()-1);
			}
			
			
			out.print(json+"]");
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String findDevByScene(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			List<Gm_Device> gmDevices = gm_DeviceService.findByScene_id(sceneId);
			List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
			for (Gm_Device gmDevice : gmDevices) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("devId", gmDevice.getDev_id());
				map.put("devName", gmDevice.getDev_no()+"-"+gmDevice.getDev_name());
				jsons.add(map);
			}
			String json = new Gson().toJson(jsons);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
     * 刷新场景树
     */
	public String refreshSceneTree(){
		HttpSession session = Struts2Utils.getSession();
		try {
			op_UserInfo_SceneService.refresh();
			String userId = (String)session.getAttribute("userid");
			List<Op_Scene> sceneList = this.op_UserInfo_SceneService.findSceneListByUserId(userId);
			session.setAttribute("sceneList", sceneList);
			String scene_tree = this.op_SceneService.generateSceneTree(sceneList);
			session.setAttribute("scene_tree", scene_tree);
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(scene_tree);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除场景关联及场景信息
	 */
	public String op_Scene_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(this.scene_id_list==null||"".equals(this.scene_id_list)){out.print("请勾选需要删除的场景！");return null;}
			String[] scene_id_arr = new String[0];
			if(scene_id_list.length()>0)scene_id_arr = scene_id_list.split(",");
			String errorStr = "";
			for(String scene_id : scene_id_arr){
				Op_Scene s = this.op_SceneService.findById(scene_id);
				if(this.gm_DeviceService.isScene_idExist(scene_id)){
					errorStr += "操作失败!"+s.getScene_name()+"下存在设备！\n";
				}
				if(this.gm_ChannelService.isScene_idExist(scene_id)){
					errorStr += "操作失败!"+s.getScene_name()+"下存在通道！\n";
				}				
			}
			if(!"".equals(errorStr)){
				out.print(errorStr);
				return null;
			}
			this.op_UserInfo_SceneService.op_UserInfo_Scene_del(scene_id_arr);
			try {
				this.op_SceneService.deleteById(scene_id_arr);
			} catch (Exception e) {
				e.printStackTrace();
				out.print("场景可能被使用，请确认配置信息是否删除完全！");
				return null;
			}
			out.print("1");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 用户场景关联信息管理 》 删除Op_Scene操作成功");
			return null;					
		} catch (Exception e) {
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("操作失败!未知错误");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_AreasService op_AreasService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_PlantformUserService op_PlantformUserService;
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Gm_DevCtrlService gm_DevCtrlService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_LatestdataService gm_LatestdataService;
	@Autowired Gm_DevCtrlStsService gm_DevCtrlStsService;
	@Autowired Op_DevCtrlBtnService op_DevCtrlBtnService;	
	@Autowired Gm_HistorydataService gm_HistorydataService;
	@Autowired Pro_FisheriesService pro_FisheriesService;
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	@Autowired Gm_DevCtrlOperateService gm_DevCtrlOperateService;
	@Autowired CacheService CacheService;
	@Autowired Op_scene_gtypeService scene_gtypeService;
	
	private Op_Scene op_Scene = new Op_Scene();
	private List<Op_Scene> list = Lists.newArrayList();
	private Page<Op_Scene> page;
    private File sceneImage;
    private String sceneImageContentType;
    private String sceneImageFileName;
    private String user_id;
    private String scene_id_list; //子场景ID
    private Op_UserInfo_Scene op_UserInfo_Scene=new Op_UserInfo_Scene();
    private String no; //场景监控 0518 曲线编号
    private String chtype_no; //场景监控 0518 通道应用类型编号 查询场景中指定类型通道的平均值 (不含子场景，时间校验)
    						  //场景监控 指定场景ID 通道应用类型编号 查询场景中指定应用类型的最近144个值
    private String hid_condition;
    private String hid_value;
    private String scene_pid; //分页查询
    
    private Boolean op; //用于重定向到列表action 的时候 传递操作结果信息
    private String result; //用于重定向到列表action 的时候 传递操作结果信息
    private String sms; //用于重定向到列表action 的时候 传递操作结果信息
    private String sceneId;

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public Boolean getOp() {
		return op;
	}

	public void setOp(Boolean op) {
		this.op = op;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

	public String getScene_pid() {
		return scene_pid;
	}

	public void setScene_pid(String scene_pid) {
		this.scene_pid = scene_pid;
	}

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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Op_UserInfo_Scene getOp_UserInfo_Scene() {
		return op_UserInfo_Scene;
	}

	public void setOp_UserInfo_Scene(Op_UserInfo_Scene op_UserInfo_Scene) {
		this.op_UserInfo_Scene = op_UserInfo_Scene;
	}

	public String getScene_id_list() {
		return scene_id_list;
	}

	public void setScene_id_list(String scene_id_list) {
		this.scene_id_list = scene_id_list;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Op_Scene getOp_Scene() {
		return op_Scene;
	}

	public void setOp_Scene(Op_Scene op_Scene) {
		this.op_Scene = op_Scene;
	}

	public List<Op_Scene> getList() {
		return list;
	}

	public void setList(List<Op_Scene> list) {
		this.list = list;
	}

	public Page<Op_Scene> getPage() {
		return page;
	}

	public void setPage(Page<Op_Scene> page) {
		this.page = page;
	}

	public File getSceneImage() {
		return sceneImage;
	}

	public void setSceneImage(File sceneImage) {
		this.sceneImage = sceneImage;
	}

	public String getSceneImageContentType() {
		return sceneImageContentType;
	}

	public void setSceneImageContentType(String sceneImageContentType) {
		this.sceneImageContentType = sceneImageContentType;
	}

	public String getSceneImageFileName() {
		return sceneImageFileName;
	}

	public void setSceneImageFileName(String sceneImageFileName) {
		this.sceneImageFileName = sceneImageFileName;
	}

	public String getChtype_no() {
		return chtype_no;
	}

	public void setChtype_no(String chtype_no) {
		this.chtype_no = chtype_no;
	}
}
