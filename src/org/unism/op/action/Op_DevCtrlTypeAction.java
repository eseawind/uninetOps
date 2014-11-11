package org.unism.op.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.ftp.FtpClient;
import org.unism.op.domain.Op_DevCtrlType;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_DevCtrlTypeService;
import org.unism.util.GetUUID;
import org.unism.util.ImageUrl;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;



@SuppressWarnings("serial")
public class Op_DevCtrlTypeAction extends ActionSupport{

	
	/**
	 * 查询所有
	 * @return
	 */
//	public String findAll(){
//		List<Op_DevCtrlType> list=op_DevCtrlTypeService.findAll();
//		if(list!=null&&list.size()>0){			
//			Struts2Utils.getRequest().setAttribute("list", list);
//		}
//		return "list";
//	}
	
	
	@SuppressWarnings("unchecked")
	public String findAll(){
		Search search = new Search();
		search.addFilter(Filter.like("decttype_displayName", queryValue));
		this.page = this.op_DevCtrlTypeService.search(page, search);
		return "list";
		
	}
	
	
	/**
	 * 添加
	 * @return
	 */
	public String save(){
		try {
			// 第二次点添加提交值，并执行添加方法
			if (post == 0) {
				return "add";
			} else if (post == 1) {// 第二次点添加提交值，并执行添加方法
				Op_DevCtrlType devCtrlType = this.op_DevCtrlTypeService.findBydecttype_no(this.op_DevCtrlType.getDecttype_no());
				if (devCtrlType != null) {
					addActionMessage("编号已存在");
					return "ok";
				} else {
					if (imagefile != null && imagefile.length > 0) {
						try {
							String uuid = GetUUID.getUUID();
							for (int i = 0; i < imagefile.length; i++) {
								String fileName = uuid+"_"+i+".gif";
								String url = ImageUrl.getInstance().getProperty("scene_img.url");
						        String port = ImageUrl.getInstance().getProperty("scene_img.port");
						        String username = ImageUrl.getInstance().getProperty("scene_img.username");
						        String password = ImageUrl.getInstance().getProperty("scene_img.password");
						        String path = ImageUrl.getInstance().getProperty("scene_img.path");
						        BufferedInputStream in = new BufferedInputStream(new FileInputStream(imagefile[i]),16*1024);
						        if(FtpClient.uploadFile(url, Integer.parseInt(port), username, password, path, fileName, in)){
					        	}else{
					        		addActionMessage("操作失败!添加图片时发生错误");
					        	}
							}
							String picName = uuid+".gif";
							this.op_DevCtrlType.setDecttype_img(picName);
				        	this.op_DevCtrlTypeService.save(this.op_DevCtrlType);
				        	addActionMessage("操作成功!");
						} catch (Exception e) {
							e.printStackTrace();
							addActionMessage("操作失败!");
						}
					}else {
						this.op_DevCtrlTypeService.save(this.op_DevCtrlType);
						addActionMessage("操作成功!");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("操作失败!");
		}
		return "ok";
	}

	private static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
	


	/**
	 * 更新
	 * @return
	 */
	public String update() {
		try
		{
			if(this.op_DevCtrlType!=null){					
				if (imagefile != null && imagefile.length > 0) {
					try {
						String uuid = GetUUID.getUUID();
						for (int i = 0; i < imagefile.length; i++) {
							String fileName = uuid+"_"+i+".gif";
							String url = ImageUrl.getInstance().getProperty("scene_img.url");
					        String port = ImageUrl.getInstance().getProperty("scene_img.port");
					        String username = ImageUrl.getInstance().getProperty("scene_img.username");
					        String password = ImageUrl.getInstance().getProperty("scene_img.password");
					        String path = ImageUrl.getInstance().getProperty("scene_img.path");
					        BufferedInputStream in = new BufferedInputStream(new FileInputStream(imagefile[i]),16*1024);
					        if(FtpClient.uploadFile(url, Integer.parseInt(port), username, password, path, fileName, in)){
				        	}else{
				        		addActionMessage("操作失败!添加图片时发生错误");
				        	}
						}
						String picName = uuid+".gif";
						this.op_DevCtrlType.setDecttype_img(picName);
			        	this.op_DevCtrlTypeService.update(this.op_DevCtrlType);
			        	addActionMessage("操作成功!");
					} catch (Exception e) {
						e.printStackTrace();
						addActionMessage("操作失败!");
					}
				}else {
					this.op_DevCtrlTypeService.update(this.op_DevCtrlType);
					addActionMessage("操作成功!");
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			addActionMessage("操作失败!");
		}
		return "ok";
	}
	
	/**
	 * 根据ID查询
	 * @return
	 */
	public String findBydecttypeid(){		
		Search search = new Search();
		Filter filter = Filter.equal("decttype_id",this.op_DevCtrlType.getDecttype_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("list",this.op_DevCtrlTypeService.search(search));
		return "edit";
	}
	/**
	 * 根据ID删除
	 * @return
	 */
	public String delete() {
		try {
			op_DevCtrlTypeService.deleteById(this.op_DevCtrlType.getDecttype_id());
			Struts2Utils.getRequest().setAttribute("result", "success");
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
			out.print("<script type='text/javascript' charset='utf-8'>alert('删除成功')</script>");
			out.print("<script>window.location.href='Op_DevCtrlType_findAll.action?page.pageSize=10'</script>");
			out.flush();
			out.close();
		} catch (Exception e) {
			try {
				PrintWriter out = Struts2Utils.getResponse().getWriter();
				out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
				out.print("<script type='text/javascript' charset='utf-8'>alert('删除失败')</script>");
				out.print("<script>window.location.href='Op_DevCtrlType_findAll.action?page.pageSize=10'</script>");
				out.flush();
				out.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return "operationResult";
	}
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_DevCtrlTypeService op_DevCtrlTypeService;
	
	private File[] imagefile;
	private String[] imageContentType;
	private String[] imagefileFileName;
	private Page<Op_DevCtrlType> page = new Page<Op_DevCtrlType>();
	private String queryValue;
	private Op_DevCtrlType op_DevCtrlType = new Op_DevCtrlType();
	private int post;
	public Op_DevCtrlType getOp_DevCtrlType() {
		return op_DevCtrlType;
	}

	public void setOp_DevCtrlType(Op_DevCtrlType op_DevCtrlType) {
		this.op_DevCtrlType = op_DevCtrlType;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}


	public Page<Op_DevCtrlType> getPage()
	{
		return page;
	}


	public void setPage(Page<Op_DevCtrlType> page)
	{
		this.page = page;
	}


	public String getQueryValue()
	{
		return queryValue;
	}

	public File[] getImagefile() {
		return imagefile;
	}


	public void setImagefile(File[] imagefile) {
		this.imagefile = imagefile;
	}


	public String[] getImageContentType() {
		return imageContentType;
	}


	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String[] getImagefileFileName() {
		return imagefileFileName;
	}


	public void setImagefileFileName(String[] imagefileFileName) {
		this.imagefileFileName = imagefileFileName;
	}


	public void setQueryValue(String queryValue)
	{
		this.queryValue = queryValue;
	}
	
	
}
