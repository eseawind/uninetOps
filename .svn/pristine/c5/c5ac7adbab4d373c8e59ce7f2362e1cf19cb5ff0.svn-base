package org.unism.gm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevCtrlOperate;
import org.unism.gm.domain.Gm_DevCtrlOperateHistory;
import org.unism.gm.service.Gm_DevCtrlOperateHistoryService;
import org.unism.gm.service.Gm_DevCtrlOperateService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.gson.Gson;

public class Gm_DevCtrlOperateAction {

	/**
	 * 设备操作
	 * @return
	 * @author Wang_Yuliang
	 */
	public String operate(){
		HttpSession session = Struts2Utils.getSession();
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Gm_DevCtrlOperate gm_DevCtrlOperate = this.gm_DevCtrlOperateService.findByDect_id(dect_id);
			if(gm_DevCtrlOperate!=null){
				Integer deco_state = gm_DevCtrlOperate.getDeco_state();
				if(deco_state!=null && deco_state == 0){
					String deco_userId = session.getAttribute("userid")+"";
					String deco_userName = session.getAttribute("username")+"";
					String deco_userIp = request.getRemoteAddr();
					Date deco_time = new Date();
					gm_DevCtrlOperate.setDeco_userId(deco_userId);
					gm_DevCtrlOperate.setDeco_userName(deco_userName);
					gm_DevCtrlOperate.setDeco_type(deco_type);
					gm_DevCtrlOperate.setDeco_time(deco_time);
					gm_DevCtrlOperate.setDeco_state(1);
					gm_DevCtrlOperate.setDeco_sort(3);
					gm_DevCtrlOperate.setDeco_result(0);
					gm_DevCtrlOperate.setDeco_userIp(deco_userIp);
					gm_DevCtrlOperate.setDeco_userType(0);
					this.gm_DevCtrlOperateService.update(gm_DevCtrlOperate);
					out.print("1");
//					out.print("'请求已发送，请等待...！'");
				}else{
					out.print("0");
//					out.print("'当前设备已被其他用户控制，请稍后再试！'");
				}
			}else{
				out.print("2");
//				out.print("'没有找到设备'");
			}			
		}catch(Exception ex){
			ex.printStackTrace();	
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("'未知错误'");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;
	}

	/**
	 * 指定控制设备ID 返回控制设备操作信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_findByDect_id(){	
		HttpSession session = Struts2Utils.getSession();
		HttpServletRequest request = Struts2Utils.getRequest();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Gm_DevCtrlOperate gm_DevCtrlOperate = this.gm_DevCtrlOperateService.findByDect_id(dect_id);
			if(gm_DevCtrlOperate!=null){
				Integer deco_type = gm_DevCtrlOperate.getDeco_type();
				Integer deco_state = gm_DevCtrlOperate.getDeco_state();
				Integer deco_result = gm_DevCtrlOperate.getDeco_result();
				Integer deco_errorCode = gm_DevCtrlOperate.getDeco_errorCode();
				String json = "{";
				json += "deco_type:'"+deco_type+"',";
				json += "deco_state:'"+deco_state+"',";
				json += "deco_result:'"+deco_result+"',";
				json += "deco_errorCode:'"+deco_errorCode+"'";
				json += "}";
				out.print(json);
			}else{
				out.print("null");
			}			
		}catch(Exception ex){
			ex.printStackTrace();	
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("null");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;		
	}
	
	public String operateMsg(){
		try
		{
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Gm_DevCtrl gm_DevCtrl = this.gm_DevCtrlService.findById(dect_id);
			Gm_DevCtrlOperate gm_DevCtrlOperate = this.gm_DevCtrlOperateService.findByDect_id(dect_id);
			List<Map<String, String>> jsonList = new ArrayList<Map<String,String>>();
			Map<String, String> map = new HashMap<String, String>();
			map.put("dectNo", gm_DevCtrl.getDect_no());
			map.put("decoType", gm_DevCtrlOperate.getDeco_type() == null ? "" : gm_DevCtrlOperate.getDeco_type().toString());
			map.put("decoState", gm_DevCtrlOperate.getDeco_state() == null ? "" : gm_DevCtrlOperate.getDeco_state().toString());
			map.put("decoResult", gm_DevCtrlOperate.getDeco_result()==null ? "":gm_DevCtrlOperate.getDeco_result().toString());
			map.put("decoErrorCode", gm_DevCtrlOperate.getDeco_errorCode()==null ? "":gm_DevCtrlOperate.getDeco_errorCode().toString());
			jsonList.add(map);
			Gson gson = new Gson();
			String json = gson.toJson(jsonList);
			out.print(json);
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return null;
	}
	
//分割线-------------------------------------------------------------------------------------------------
	@Autowired Gm_DevCtrlOperateService gm_DevCtrlOperateService;
	@Autowired Gm_DevCtrlService gm_DevCtrlService;
	@Autowired Gm_DevCtrlOperateHistoryService gm_DevCtrlOperateHistoryService;
	
	private String dect_id; //设备操作/查询控制设备操作信息
	private Integer deco_type;
	public String getDect_id() {
		return dect_id;
	}
	public void setDect_id(String dect_id) {
		this.dect_id = dect_id;
	}
	public Integer getDeco_type() {
		return deco_type;
	}
	public void setDeco_type(Integer deco_type) {
		this.deco_type = deco_type;
	}
	
}
