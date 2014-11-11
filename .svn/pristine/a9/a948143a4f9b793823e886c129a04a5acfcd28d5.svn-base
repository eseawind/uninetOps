package org.unism.op.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.op.domain.Op_Areas;
import org.unism.op.service.Op_AreasService;
import org.wangzz.core.web.struts2.Struts2Utils;

public class Op_AreasAction {
	
	/**
	 * 查找省级区划 
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findSheng(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String list = this.op_AreasService.findSheng();
			out.print(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询市级区划 据省级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findShiBySheng(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String list = this.op_AreasService.findShiBySheng(this.op_Areas.getArea_id());
			out.print(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询县级级区划 据省级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findXianBySheng(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String list = this.op_AreasService.findXianBySheng(this.op_Areas.getArea_id());
			out.print(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询县级区划 据市级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findXianByShi(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String list = this.op_AreasService.findXianByShi(this.op_Areas.getArea_id());
			out.print(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
//分割线--------------------------------------------------------------------------------------------------
	@Autowired Op_AreasService op_AreasService;
	private Op_Areas op_Areas = new Op_Areas();
	
	public Op_AreasService getOp_AreasService() {
		return op_AreasService;
	}
	public void setOp_AreasService(Op_AreasService op_AreasService) {
		this.op_AreasService = op_AreasService;
	}
	public Op_Areas getOp_Areas() {
		return op_Areas;
	}
	public void setOp_Areas(Op_Areas op_Areas) {
		this.op_Areas = op_Areas;
	}
}
