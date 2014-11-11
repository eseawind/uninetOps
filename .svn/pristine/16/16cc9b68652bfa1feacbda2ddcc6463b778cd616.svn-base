package org.unism.op.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.ftp.FtpClient;
import org.unism.op.domain.SystemInfo;
import org.unism.op.service.SystemInfoService;
import org.unism.util.ImageUrl;
import org.wangzz.core.web.struts2.Struts2Utils;

public class SystemInfoAction {
	
	@Autowired SystemInfoService systemInfoService;
	
	private SystemInfo systemInfo;
	
	public String show() {
		List<SystemInfo> list = systemInfoService.findAll();
		if(!list.isEmpty()) {
			systemInfo = list.get(0);
		}
		return "show";
	}	
	
	public String update() {
		if(StringUtils.hasLength(systemInfo.getId())) {
			systemInfoService.update(systemInfo);
		} else {
			systemInfoService.save(systemInfo);
		}
		return "success";
	}
	
	/**
	 * 上传图片
	 * @return
	 * weixiaohua
	 */
	public String uploadImage() {
		HttpSession session = Struts2Utils.getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
        String imageName = "topic_"+new Date().getTime()+Math.random()*10000+".jpg";   
        File dirPath =new File(session.getServletContext().getRealPath("/")+"\\upload\\systemInfoImg\\");   
        //如果文件夹不存在，创建它   
        if(!dirPath.exists()){   
            dirPath.mkdirs();   
        }   
        //文件存放路径   
        String newPath = dirPath+"\\"+imageName; 
        //上传文件   
        try {   
        	//System.out.println(imgFile);
            //upload(imgFile,imageName);  //往FTP上上传文件
            copy(imgFile, newPath);
        } catch (Exception e1) {   
            e1.printStackTrace();   
        }      
           
        String id = "content";      
        String url = "http://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath()    
                +"/upload/systemInfoImg/" + imageName;      
  
        String border = "0";      
        String result =    
            "<script type=\"text/javaScript\">parent.KE.plugin[\"image\"].insert(\""     
                + id      
                + "\",\""     
                + url      
                + "\",\""     
                + imgTitle      
                + "\",\""     
                + imgWidth      
                + "\",\""    
                + imgHeight    
                + "\",\""    
                + border + "\""    
                +");</script>";
        PrintWriter out = null;   
        try {   
            out = encodehead(request, response);   
        } catch (IOException e) {   
            e.printStackTrace();   
        }   
        out.write(result);   
        out.close();    
        return null;
	}
	private void upload(File imgFile,String imageName) {
		try {
			if(this.imgFile != null){
		        String url = ImageUrl.getInstance().getProperty("scene_img.url");
		        String port = ImageUrl.getInstance().getProperty("scene_img.port");
		        String username = ImageUrl.getInstance().getProperty("scene_img.username");
		        String password = ImageUrl.getInstance().getProperty("scene_img.password");
		        String path = ImageUrl.getInstance().getProperty("scene_img.path");
		        BufferedInputStream in = new BufferedInputStream(new FileInputStream(imgFile),16*1024);
		        OutputStream out = null ;
		        try{                
		        	FtpClient.uploadFile(url, Integer.parseInt(port), username, password, path, imageName, in);
		        }catch(Exception ex){ ex.printStackTrace();}
		        finally{
		        	if(null != in){
		        		in.close();
		            }
		        	if(null != out){
		        		out.close();
		            } 
		        }
		 	}else{
		 	}    
	    }catch (Exception e)  {
	        e.printStackTrace();
	    }
	}

//	public String uploadImage() {
//		HttpSession session = Struts2Utils.getSession();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		HttpServletResponse response=ServletActionContext.getResponse();
//        String imageName = "topic_"+new Date().getTime()+Math.random()*10000+".jpg";   
//        File dirPath =new File(session.getServletContext().getRealPath("/")+"\\upload\\systemInfoImg\\");   
//        //如果文件夹不存在，创建它   
//        if(!dirPath.exists()){   
//            dirPath.mkdirs();   
//        }   
//        //文件存放路径   
//        String newPath = dirPath+"\\"+imageName; 
//        //上传文件   
//        try {   
//            copy(imgFile, newPath);   
//        } catch (Exception e1) {   
//            e1.printStackTrace();   
//        }      
//           
//        String id = "content";      
//        String url = "http://" + request.getServerName() + ":"+ request.getServerPort() + request.getContextPath()    
//                +"/upload/systemInfoImg/" + imageName;      
//  
//        String border = "0";      
//        String result =    
//            "<script type=\"text/javaScript\">parent.KE.plugin[\"image\"].insert(\""     
//                + id      
//                + "\",\""     
//                + url      
//                + "\",\""     
//                + imgTitle      
//                + "\",\""     
//                + imgWidth      
//                + "\",\""    
//                + imgHeight    
//                + "\",\""    
//                + border + "\""    
//                +");</script>";
//        PrintWriter out = null;   
//        try {   
//            out = encodehead(request, response);   
//        } catch (IOException e) {   
//            e.printStackTrace();   
//        }   
//        out.write(result);   
//        out.close();    
//        return null;
//	}	
	/**    
     * 拷贝文件    
     *     
     * @param upload文件流    
     * @param newPath新文件路径和名称    
     * @throws Exception    
     */     
    private void copy(File upload, String newPath) throws Exception {      
        FileOutputStream fos = new FileOutputStream(newPath);      
        FileInputStream fis = new FileInputStream(upload);      
        byte[] buffer = new byte[1024];      
        int len = 0;      
        while ((len = fis.read(buffer)) > 0) {      
            fos.write(buffer, 0, len);      
        }      
        fos.close();      
        fis.close();      
    } 
    /**  
     * Ajax辅助方法 获取 PrintWriter  
     * @return  
     * @throws IOException   
     * @throws IOException   
     * request.setCharacterEncoding("utf-8");  
        response.setContentType("text/html; charset=utf-8");  
     */  
    private PrintWriter encodehead(HttpServletRequest request,HttpServletResponse response) throws IOException{   
        request.setCharacterEncoding("utf-8");   
        response.setContentType("text/html; charset=utf-8");   
        return response.getWriter();   
    } 

	/**  
     * 图片对象  
     */  
    private File imgFile;   
       
    /**  
     * 图片宽度  
     */  
    private String imgWidth;   
       
    /**  
     * 图片高度  
     */  
    private String imgHeight;   
       
    /**  
     * 图片对齐方式  
     */  
    private String align;   
       
    /**  
     * 图片标题  
     */  
    private String imgTitle;   
       
    public File getImgFile() {   
        return imgFile;   
    }   
  
    public void setImgFile(File imgFile) {   
        this.imgFile = imgFile;   
    }   
  
    public String getImgWidth() {   
        return imgWidth;   
    }   
  
    public void setImgWidth(String imgWidth) {   
        this.imgWidth = imgWidth;   
    }   
  
    public String getImgHeight() {   
        return imgHeight;   
    }   
  
    public void setImgHeight(String imgHeight) {   
        this.imgHeight = imgHeight;   
    }   
  
    public String getAlign() {   
        return align;   
    }   
  
    public void setAlign(String align) {   
        this.align = align;   
    }   
  
    public String getImgTitle() {   
        return imgTitle;   
    }   
  
    public void setImgTitle(String imgTitle) {   
        this.imgTitle = imgTitle;   
    } 
    
	public SystemInfo getSystemInfo() {
		return systemInfo;
	}

	public void setSystemInfo(SystemInfo systemInfo) {
		this.systemInfo = systemInfo;
	}
	
	
}
