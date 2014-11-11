package org.unism.op.action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.ftp.FtpClient;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_led;
import org.unism.op.domain.Op_led_scene;
import org.unism.op.service.Op_ledService;
import org.unism.util.ImageUrl;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

public class Op_ledAction extends ActionSupport{

	/**
	 * 分页
	 * @return
	 * @author Wang_Yuliang
	 */
	public String page(){
		Search search = new Search();
		if(this.hid_condition!=null && !this.hid_condition.equals("")){
			search.addFilterILike(hid_condition, hid_value);
		}else{}
		search.addSortAsc("led_no");
		this.page = this.op_ledService.search(page, search);		
		return "page";
	}
	
	/**
	 * 验证LED信息是否已存在
	 * @return
	 * @author Wang_Yuliang
	 */
	public String exist(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Op_led> list = this.op_ledService.search(new Search().addFilterEqual("led_no", this.op_led.getLed_no()));
			if(list!=null && list.size()>0)
				out.print("1");
			else
				out.print("0");
		}catch(Exception ex){
			ex.printStackTrace();	
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("2");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;
	}
	
	/**
	 * 验证LED信息是否已存在 更新时用
	 * @return
	 * @author Wang_Yuliang
	 */
	public String exist_edit(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Op_led> list = this.op_ledService.search(new Search().addFilterAnd(Filter.notEqual("led_id", this.op_led.getLed_id()), Filter.equal("led_no", this.op_led.getLed_no())));
			if(list!=null && list.size()>0)
				out.print("1");
			else
				out.print("0");
		}catch(Exception ex){
			ex.printStackTrace();	
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("2");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;
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
	 * 添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save(){		
		try{
			this.op_ledService.save(op_led);
			addActionMessage("操作成功!");
        }catch (Exception e)  {
            e.printStackTrace();
            addActionMessage("操作失败!未知错误");
        }	
		return "operationResult";
	}
	
    /**
     * 到编辑
     * @return
     * @author Wang_Yuliang
     */
    public String toEdit(){
    	this.op_led = this.op_ledService.findById(this.op_led.getLed_id());
    	return "edit";
    }
    
    /**
     * 编辑
     * @return
     * @author Wang_Yuliang
     */
    public String edit(){
		try {
			this.op_ledService.update(op_led);
			addActionMessage("操作成功!");
        }catch (Exception e)  {
        	addActionMessage("操作失败!未知错误");
            e.printStackTrace();
        }
        return "operationResult";
    }
//分隔线-----------------------------------------------------------------------------------------------------------------	
	@Autowired Op_ledService op_ledService;
	
	private Op_led op_led = new Op_led();
	private Page<Op_led> page = new Page<Op_led>();
    private String hid_condition;
    private String hid_value;

	public Op_led getOp_led() {
		return op_led;
	}

	public void setOp_led(Op_led op_led) {
		this.op_led = op_led;
	}

	public Page<Op_led> getPage() {
		return page;
	}

	public void setPage(Page<Op_led> page) {
		this.page = page;
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
}
