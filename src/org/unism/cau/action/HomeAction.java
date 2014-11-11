package org.unism.cau.action;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;

import org.unism.cau.ExcelToMySql;
import org.unism.cau.JcxxImpCommBS;
import org.wangzz.core.web.struts2.Struts2Utils;

public class HomeAction {

	/**
	 * 基本信息导入
	 * @return
	 */
	public String input(){
		HttpServletRequest request = Struts2Utils.getRequest();
		ExcelToMySql cc = new ExcelToMySql();
		FileInputStream stream;
		try {
			stream = new FileInputStream(homeExcel);
			cc.dobase(stream);
			request.setAttribute("result", "success");
			request.setAttribute("desc", JcxxImpCommBS.impMsg);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "operationResult";
	}
	
	/**
	 * 到基础信息导入
	 * @return
	 */
	public String toInput(){
		return "home";
	}
	
	private File homeExcel;

	public File getHomeExcel() {
		return homeExcel;
	}

	public void setHomeExcel(File homeExcel) {
		this.homeExcel = homeExcel;
	}
	
}
