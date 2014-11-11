package org.unism.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.eventfilesystem.POIFSReader;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.poifs.filesystem.POIFSWriterEvent;
import org.apache.poi.poifs.filesystem.POIFSWriterListener;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.pro.service.Pro_TidesService;
import org.unism.web.service.BasicExcelService;
import org.unism.web.service.ProjectExcelService;
import org.wangzz.core.service.ServiceException;
import org.wangzz.core.web.struts2.Struts2Utils;


public class ImportAction {
	
	private File upload;
	private String message;
	private String uploadContentType; // 文件的内容类型
    private String uploadFileName; // 上传文件名
    private String scene_id_arr; //场景id -1,id1,id2,...idx

	public static final String[] allowedExcelTypes = { "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","application/octet-stream" };
	
	private static final String[] extensionTypes = {"et","xls","xlsx"};
	private Logger logger = Logger.getLogger(ImportAction.class);
	private static String keyword = "";
	
	@Autowired BasicExcelService basicExcelService;
	@Autowired ProjectExcelService projectExcelService;
	@Autowired Pro_TidesService proTidesService;	
	
	public String importBasicExcel(){
		if(upload == null) {
			message = "文件不存在!";
			return "importError";
		}
		String keywordXML = getKeyWord();
		if(keywordXML == null || "".equals(keywordXML)){
			message = "版本配置文件丢失！";
			return "importError";
		}
		try {
			POIFSReader r = new POIFSReader();
			r.registerListener(new MyPOIFSReaderListener(),
					"\005SummaryInformation");
			r.read(new FileInputStream(upload));
		} catch (Exception e) {logger.error("异常:",e);}	
		
		if(ArrayUtils.contains(allowedExcelTypes, uploadContentType)){
			if(!keywordXML.equals(keyword)) {
				message = "模板版本过老，请下载最新版本模板!";
				return "importError";
			}else{
				message = basicExcelService.parseExcel(upload, uploadContentType);			
				return "success";
			}			
		} else {
			if ("application/octet-stream".equals(uploadContentType)) {
				message = "文件在打开状态,请将文件关闭后上传";
			} else {
				message = "错误的文件格式";
			}
		}
		return "importError";
	}
	
	public String importProjectExcel() throws Exception{	
		if(upload == null) {
			message = "文件不存在!";
			return "importError";
		}
		String keywordXML = getKeyWord();
		if(keywordXML == null || "".equals(keywordXML)){
			message = "版本配置文件丢失！";
			return "importError";
		}
		try {
			POIFSReader r = new POIFSReader();
			r.registerListener(new MyPOIFSReaderListener(),
					"\005SummaryInformation");
			r.read(new FileInputStream(upload));
		} catch (Exception e) {logger.error("异常:",e);}			
		if(ArrayUtils.contains(allowedExcelTypes, uploadContentType)){
			try {
				String ext = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
				if(ArrayUtils.contains(extensionTypes, ext)){
					if(!keywordXML.equals(keyword)) {
						message = "模板版本过老，请下载最新版本模板!";
						return "importError";
					}else{
						message = projectExcelService.parseExcel(upload, uploadContentType);
					}
				}else{
					message = "错误的文件格式";
					return "importError";
				}
			} catch (ServiceException e) {
				message = e.getMessage();
				logger.error("异常:",e);
				return "importError";
			}
			return "success";
		} else {
			message = "错误的文件格式";
		}
		return "importError";
	}
	
	/**
	 * 导入潮汐数据
	 * @return 
	 * weixh
	 */
	public String importTidesExcel(){
		if(upload == null) {
			message = "文件不存在!";
			return "importError";
		}
		String keywordXML = getKeyWord();
		if(keywordXML == null || "".equals(keywordXML)){
			message = "版本配置文件丢失！";
			return "importError";
		}
		try {			
			POIFSReader r = new POIFSReader();
			r.registerListener(new MyPOIFSReaderListener(),
					"\005SummaryInformation");
			r.read(new FileInputStream(upload));
		} catch (Exception e) {logger.error("异常:",e);}	
		
		if(ArrayUtils.contains(allowedExcelTypes, uploadContentType)){
			try {
				if(!keywordXML.equals(keyword)) {
					message = "模板版本过老，请下载最新版本模板!";
					return "importError";
				}else{
					message = proTidesService.parseExcel(upload, uploadContentType);
				}
			} catch (ServiceException e) {
				message = e.getMessage();
				logger.error(e.getMessage());
				return "import";
			}
			return "success";
		} else {
			if ("application/octet-stream".equals(uploadContentType)) {
				message = "文件在打开状态,请将文件关闭后上传";
			} else {
				message = "错误的文件格式";
			}
		}
		return "importError";
	}
	
	/**
	 * 项目导出
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuProjectExcel(){
		HttpServletResponse response = Struts2Utils.getResponse();
		OutputStream outputStream = null;
		response.setHeader("Content-disposition", "attachment; filename=fine.xls");// 设定输出文件头
		response.setContentType("application/vnd.ms-excel");// 定义输出类型	
		try {
			outputStream = response.getOutputStream();
			HSSFWorkbook wb = new HSSFWorkbook();
			wb.createInformationProperties();
			SummaryInformation si = wb.getSummaryInformation();
			String keywordXML = getKeyWord();
			si.setKeywords(keywordXML);
			wb = projectExcelService.daochuProjectExcel(wb, scene_id_arr);
			wb.write(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e){
			
			logger.error("异常:",e);
		}
	}
	
	/*
	 * 读取excelconfig的标记配置
	 */
	public String getKeyWord(){
		try {
			String url = ImportAction.class.getClassLoader().getResource("").getPath();
			url = url.substring(1)+"excelconfig.xml";
			File xmlFile = new File(url);
			SAXReader reader = new SAXReader();
	        Document xmlDoc = reader.read(xmlFile);
	        Element root = xmlDoc.getRootElement();
	        for (Iterator i = root.elementIterator("excelConfig"); i.hasNext();) {
	        	Element ele = (Element) i.next();
//	        	System.out.println(">>>>>>>>>>>>>>>"+ele.elementText("Keywords"));
	        	return (ele.elementText("Keywords"));
	        }
		} catch (Exception e) {
			
			logger.error("异常:",e);
		}
		return null;
	}
	
	/*
	 * 
	 */
	static class MyPOIFSReaderListener implements POIFSReaderListener {
		public void processPOIFSReaderEvent(POIFSReaderEvent event) {
			SummaryInformation si = null;
			try {
				si = (SummaryInformation) PropertySetFactory.create(event
						.getStream());
			} catch (Exception ex) {
				throw new RuntimeException("属性集流\"" + event.getPath()
						+ event.getName() + "\": " + ex);
			}
//			System.out.println("标记: \"" + si.getKeywords() + "\"");
			keyword = si.getKeywords();
		}
	}	
	
	
//分割线-----------------------------------------------------------------------------------------------------------------------------
		
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getScene_id_arr() {
		return scene_id_arr;
	}

	public void setScene_id_arr(String scene_id_arr) {
		this.scene_id_arr = scene_id_arr;
	}

	
	
}
