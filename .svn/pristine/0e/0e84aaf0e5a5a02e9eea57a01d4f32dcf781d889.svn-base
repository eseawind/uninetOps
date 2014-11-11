package org.unism.cau.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.unism.cau.ExcelToMySql;
import org.unism.cau.JcxxImpCommBS;
import org.wangzz.core.web.struts2.Struts2Utils;

public class ProjectAction {

	/**
	 * 项目信息导入
	 * @return
	 */
	public String input(){
		HttpServletRequest request = Struts2Utils.getRequest();
		ExcelToMySql cc = new ExcelToMySql();
		FileInputStream stream;
		try {
			stream = new FileInputStream(projectExcel);
			cc.doproject(stream);
			request.setAttribute("result", "success");
			request.setAttribute("desc", JcxxImpCommBS.impMsg);	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return "operationResult";
	}
	
	/**
	 * 到项目信息导入
	 * @return
	 */
	public String toInput(){
		return "project";
	}
	
	private File projectExcel;

	public File getProjectExcel() {
		return projectExcel;
	}

	public void setProjectExcel(File projectExcel) {
		this.projectExcel = projectExcel;
	}
	
}
