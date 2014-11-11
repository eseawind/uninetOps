package org.unism.op.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.service.Op_ChannelTypeService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;





public class Op_ChannelTypeAction extends ActionSupport{
	
	public String findAll(){
		//System.out.println(queryValue);
		Search search = new Search();
		Filter filter = Filter.like("chtype_displayName", queryValue);
		search.addFilter(filter);
		this.page = this.op_ChannelTypeService.search(page, search);
		return "page";
	}

	/**
	 * 查询所有
	 * @return
	 */
//	public String findAll(){
//		List<Op_ChannelType> list=op_ChannelTypeService.findAll();
//		if(list!=null&&list.size()>0){			
//			Struts2Utils.getRequest().setAttribute("list", list);
//		}
//		return "list";
//	}
	
	/**
	 * 添加
	 * @return
	 */
	public String save(){
		//第二次点添加提交值，并执行添加方法
		if(post==0){			
			return "add";
		}else if(post==1){//第二次点添加提交值，并执行添加方法
			try
			{
				Op_ChannelType op_ChannelType = this.op_ChannelTypeService.findByChTypeNo(this.op_ChannelType.getChtype_no());
				if(op_ChannelType != null){
					PrintWriter out = Struts2Utils.getResponse().getWriter();
					out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
					out.print("<script type='text/javascript' charset='utf-8'>alert('编号已存在！')</script>");
					out.print("<script>window.location.href='Op_ChannelType_findAll.action?page.pageSize=10'</script>");
					out.flush();
					out.close();
					
				}else {
					if (file != null) {
			            // 读取文件内容到InputStream里  
						Date date = new Date();
			            InputStream is = new FileInputStream(getFile());  
			            String filename = getFileFileName();
						filename=filename.substring(filename.lastIndexOf("."));
						String _filename = this.op_ChannelType.getChtype_no()+filename;
						// 创建输出流，生成新文件  
			            OutputStream os = new FileOutputStream(getSavePath() + "\\" + _filename);  
			            // 将InputStream里的byte拷贝到OutputStream  
			            IOUtils.copy(is, os);  
			            os.flush();
			            IOUtils.closeQuietly(is);  
			            IOUtils.closeQuietly(os);
			            this.op_ChannelType.setTypeImg(_filename);
			        } 
					this.op_ChannelTypeService.save(this.op_ChannelType);
					PrintWriter out = Struts2Utils.getResponse().getWriter();
					out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
					out.print("<script type='text/javascript' charset='utf-8'>alert('添加成功')</script>");
					out.print("<script>window.location.href='Op_ChannelType_findAll.action?page.pageSize=10'</script>");
					out.flush();
					out.close();
				}
				
				
			} catch (Exception e)
			{
				try {
					PrintWriter out = Struts2Utils.getResponse().getWriter();
					out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
					out.print("<script type='text/javascript' charset='utf-8'>alert('添加失败')</script>");
					out.print("<script>window.location.href='Op_ChannelType_findAll.action?page.pageSize=10'</script>");
					out.flush();
					out.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			} 
		}
		return "operationResult";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String update() {
		if(this.op_ChannelType!=null){
			try
			{
				if(file != null){
					InputStream is = new FileInputStream(getFile());  
		            String filename = getFileFileName();
					filename=filename.substring(filename.lastIndexOf("."));
					String _filename = this.op_ChannelType.getChtype_no()+filename;
					// 创建输出流，生成新文件  
		            OutputStream os = new FileOutputStream(getSavePath() + "\\" + _filename);  
		            // 将InputStream里的byte拷贝到OutputStream  
		            IOUtils.copy(is, os);  
		            os.flush();
		            IOUtils.closeQuietly(is);  
		            IOUtils.closeQuietly(os);
		            this.op_ChannelType.setTypeImg(_filename);
				}
				this.op_ChannelTypeService.update(this.op_ChannelType);
				PrintWriter out = Struts2Utils.getResponse().getWriter();
				out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
				out.print("<script type='text/javascript' charset='utf-8'>alert('修改成功')</script>");
				out.print("<script>window.location.href='Op_ChannelType_findAll.action?page.pageSize=10'</script>");
				out.flush();
				out.close();
			} catch (Exception e)
			{
				// TODO: handle exception
				return "operationResult";
			}
			
			
			
		}
		return "operationResult";
	}
	
	/**
	 * 根据ID查询
	 * @return
	 */
	public String findByroleid(){		
		Search search = new Search();
		Filter filter = Filter.equal("chtype_id",this.op_ChannelType.getChtype_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("list",this.op_ChannelTypeService.search(search));
		
		return "edit";
	}
	/**
	 * 根据ID删除
	 * @return
	 */
	public String delete() {
		//System.out.println("laile");
		//System.out.println(op_ChannelType.getChtype_id());
		try {
			op_ChannelTypeService.deleteById(this.op_ChannelType.getChtype_id());
			Struts2Utils.getRequest().setAttribute("result", "success");
			Struts2Utils.getRequest().setAttribute("operation", "Op_ChannelType_findAll.action");
			addActionMessage("操作成功……");
		} catch (Exception e) {
			addActionMessage("操作失败……");
		}
		return "ok";
	}
	
	/**
	 * 指定类型ID 查找类型信息 
	 * @return json
	 * 			{
	 * 				chtype_id:'',
	 * 				chtype_no:'',
	 * 				chtype_displayName:'',
	 * 				dev_model:'',
	 * 				ch_dectDig:'',
	 * 				ch_unit:'',
	 * 				ch_max:'',
	 * 				ch_min:'',
	 * 				ch_crateMax:'',
	 * 				ch_corrCyc:'',
	 * 				ch_corrFormula:'',
	 * 				ch_corrUnit:'',
	 * 				ch_ClassName:'',
	 * 				typeImg:''
	 * 			}
	 * @author Wang_Yuliang
	 */
	public String json_findById(){		
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			this.op_ChannelType = this.op_ChannelTypeService.findById(this.op_ChannelType.getChtype_id());
			String json = "{";
			json += "chtype_id:'"+this.op_ChannelType.getChtype_id()+"',";
			json += "chtype_no:'"+this.op_ChannelType.getChtype_no()+"',";
			json += "chtype_displayName:'"+this.op_ChannelType.getChtype_displayName()+"',";
			json += "dev_model:'"+this.op_ChannelType.getDev_model()+"',";
			json += "ch_dectDig:'"+this.op_ChannelType.getCh_dectDig()+"',";
			json += "ch_unit:'"+this.op_ChannelType.getCh_unit()+"',";
			json += "ch_max:'"+this.op_ChannelType.getCh_max()+"',";
			json += "ch_min:'"+this.op_ChannelType.getCh_min()+"',";
			json += "ch_crateMax:'"+this.op_ChannelType.getCh_crateMax()+"',";
			json += "ch_corrCyc:'"+this.op_ChannelType.getCh_corrCyc()+"',";
			json += "ch_corrFormula:'"+this.op_ChannelType.getCh_corrFormula()+"',";
			json += "ch_corrUnit:'"+this.op_ChannelType.getCh_unit()+"',";
			json += "ch_ClassName:'"+this.op_ChannelType.getCh_className()+"',";
			json += "typeImg:'"+this.op_ChannelType.getTypeImg()+"'";
			json += "}";
			//System.out.println(json);
			out.print(json);
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{chtype_id:'',chtype_no:'',chtype_displayName:'',dev_model:'',ch_dectDig:'',ch_unit:'',ch_max:'',ch_min:'',ch_crateMax:'',ch_corrCyc:'',ch_corrFormula:'',ch_corrUnit:'',ch_ClassName:'',typeImg:''}");
			}catch(Exception exx){exx.printStackTrace();}	
		}
		
		return null;
	}
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	
    private File file;
    private String fileContentType;// 封装文件类型  
    private String fileFileName;// 封装文件名  
    private String savePath;// 保存路径  
    private String title;// 文件标题
	private String queryValue;
	
	private Page<Op_ChannelType> page = new Page<Op_ChannelType>();
	
	private Op_ChannelType op_ChannelType=new Op_ChannelType();
	
	private int post;
	
	public Op_ChannelType getOp_ChannelType() {
		return op_ChannelType;
	}
	public void setOp_ChannelType(Op_ChannelType op_ChannelType) {
		this.op_ChannelType = op_ChannelType;
	}

	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public Page<Op_ChannelType> getPage() {
		return page;
	}

	public void setPage(Page<Op_ChannelType> page) {
		this.page = page;
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
	}
    
    public String getFileContentType()
	{
		return fileContentType;
	}

	public void setFileContentType(String fileContentType)
	{
		this.fileContentType = fileContentType;
	}

	public String getFileFileName()
	{
		return fileFileName;
	}

	public void setFileFileName(String fileFileName)
	{
		this.fileFileName = fileFileName;
	}

	public String getSavePath()
	{
		// 将相对路径转换成绝对路径
		//System.out.println(Struts2Utils.getRequest().getRealPath(savePath));
		return Struts2Utils.getRequest().getRealPath(savePath);
	}

	public void setSavePath(String savePath)
	{
		this.savePath = savePath;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}  
    
    
    
	
}
