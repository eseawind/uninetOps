package org.unism.gm.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.action.form.DevFaultSearchForm;
import org.unism.gm.action.form.DevFaultTypeForm;
import org.unism.gm.action.form.ReportDataForm;
import org.unism.gm.domain.Gm_DevFault;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DevFaultService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.gm.service.Gm_HistorydataService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.util.Def_symptom;
import org.unism.util.StaticDataManage;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Search;
import org.wangzz.core.utils.DateUtil;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class Gm_DevFaultAction extends ActionSupport {
	
	private static final long serialVersionUID = 7192763111964460377L;

	/**
	 * 查询所有的故障信息
	 * @return 
	 */
	public String findAllDevFault(){
		Search search = new Search();
//		if(StringUtils.hasText(queryName)) {
//			if("def_grade".equals(queryName) || "def_type".equals(queryName)) {
//				search.addFilterEqual(queryName, Integer.parseInt(queryValue));
//			}
//			if("def_state".equals(queryName)) {
//				if(queryValue.equals("1")){
//					search.addFilterNotNull("def_dealTime");
//				} else if(queryValue.equals("0")) {
//					search.addFilterNull("def_dealTime");
//				}
//			}
//			if("dev_no".equals(queryName)) {
//				search.addFilterLike("dev_id.dev_no", queryValue);
//			}
//		}
		
		if (StringUtils.hasText(devFaultSearchForm.getDev_no())) {
			search.addFilterLike("dev_id.dev_no", devFaultSearchForm.getDev_no());
		}
		if (StringUtils.hasText(devFaultSearchForm.getDef_grade())) {
			search.addFilterEqual("def_grade", Integer.parseInt(devFaultSearchForm.getDef_grade()));
		}
		if (StringUtils.hasText(devFaultSearchForm.getDef_object())) {
			search.addFilterEqual("def_object", Integer.parseInt(devFaultSearchForm.getDef_object()));
		}
		if (StringUtils.hasText(devFaultSearchForm.getDef_type())) {
			search.addFilterEqual("def_type", Integer.parseInt(devFaultSearchForm.getDef_type()));
		}
		if (StringUtils.hasText(devFaultSearchForm.getDef_symptom())) {
			search.addFilterEqual("def_symptom", Integer.parseInt(devFaultSearchForm.getDef_symptom()));
		}
		if (StringUtils.hasText(devFaultSearchForm.getDef_state())) {
			if(devFaultSearchForm.getDef_state().equals("0")){
				search.addFilterNull("def_dealTime");
			}else if(devFaultSearchForm.getDef_state().equals("1")){
				search.addFilterNotNull("def_dealTime");
			}
		}
		
		if(StringUtils.hasText(beginTime)) {
			search.addFilterGreaterOrEqual("def_occurTime", DateUtil.getDate(beginTime, "yyyy-MM-dd hh:mm:ss"));
		}
		if(StringUtils.hasText(endTime)) {
			search.addFilterLessOrEqual("def_occurTime", DateUtil.getDate(endTime, "yyyy-MM-dd hh:mm:ss"));
		}
		search.addSortDesc("def_occurTime");
		this.page = this.gm_DevFaultService.search(page, search);
		return "gm_devFault";
	}
	
	/**
	 * 根据id查询出来所要编辑的故障信息
	 * @return
	 */
	public String editGm_DevFault(){
		Gm_DevFault gm_DevFault = this.gm_DevFaultService.findById(def_id);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = sdf.format(date);
		Struts2Utils.getRequest().setAttribute("dateTime", dateTime);
		Struts2Utils.getRequest().setAttribute("gm_DevFault", gm_DevFault);
		Struts2Utils.getRequest().setAttribute("queryName", queryName);
		Struts2Utils.getRequest().setAttribute("queryValue", queryValue);
		return "edit";
	}
	
	public String updateGm_DevFault(){
		try
		{
			gm_DevFault.setDef_grade(Integer.parseInt(def_grade));
			gm_DevFault.setDef_type(Integer.parseInt(def_type));
			
			this.gm_DevFaultService.update(gm_DevFault);
			//addActionMessage("操作成功……");
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
			out.print("<script type='text/javascript' charset='utf-8'>alert('操作成功……')</script>");
			out.print("<script>window.location.href='Gm_DevFault_findAllDevFault.action?page.pageSize=10'</script>");
			out.flush();
			out.close();
		} catch (Exception e)
		{
			//addActionMessage("操作失败……");
			try {
				PrintWriter out = Struts2Utils.getResponse().getWriter();
				out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
				out.print("<script type='text/javascript' charset='utf-8'>alert('操作失败……')</script>");
				out.print("<script>window.location.href='Gm_DevFault_findAllDevFault.action?page.pageSize=10'</script>");
				out.flush();
				out.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return "success";
	}
	
	/**
	 * 故障信息分类
	 * @return 
	 * weixiaohua
	 */
	public String findAllDevFaultType(){
		return "gm_devFaultType";
	}
	/**
	 * 查询所有的故障信息,进行分类
	 * @return 
	 * weixiaohua
	 */
	public String findAllDevFaultTypeList(){
		Map<String, DevFaultTypeForm> map = new LinkedHashMap<String, DevFaultTypeForm>();
		String userId=Struts2Utils.getSession().getAttribute("userid").toString();
		List<String> scene_id_arr = new ArrayList<String>();
		if(scene_id!=null){
			scene_id_arr.add(this.scene_id);				
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(userId);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
		}
		String scene_id_selectAll="";
		for(String sceid : scene_id_arr) {
			scene_id_selectAll=scene_id_selectAll+sceid+"','";
		}
		if(scene_id_selectAll.length()>0){
			scene_id_selectAll=scene_id_selectAll.substring(0, scene_id_selectAll.length()-3);
		}
		
		List<Object[] > devFaultTypeList=this.gm_DevFaultService.findDevFaultTypeListByUserIdAndTime(userId,beginTime,endTime,scene_id_selectAll);
		for(Object[] str : devFaultTypeList) {
			String sceneId=str[0].toString();
			String scene_name=str[1].toString();
			String net_addr=str[2].toString();
			Integer type=Integer.parseInt(str[3].toString());
			Integer count=Integer.parseInt(str[4].toString());
			DevFaultTypeForm devFaultTypeForm=map.get(sceneId);
			if(devFaultTypeForm==null || "".equals(devFaultTypeForm)){//如果map中没有此场景
				devFaultTypeForm =new DevFaultTypeForm();
				devFaultTypeForm.setScene_name(scene_name);
				devFaultTypeForm.setNet_addr(net_addr);
				if(type==0){
					devFaultTypeForm.setPlatformCount(count);
				}
				if(type==1){
					devFaultTypeForm.setGPRSCount(count);
				}
				if(type==2){
					devFaultTypeForm.setWsnCount(count);				
				}
				if(type==3){
					devFaultTypeForm.setSensorCount(count);
				}
				if(type==4){
					devFaultTypeForm.setDevctrlCount(count);
				}
				map.put(sceneId, devFaultTypeForm);
			}else{
				if(type==0){
					devFaultTypeForm.setPlatformCount(count);
				}
				if(type==1){
					devFaultTypeForm.setGPRSCount(count);
				}
				if(type==2){
					devFaultTypeForm.setWsnCount(count);				
				}
				if(type==3){
					devFaultTypeForm.setSensorCount(count);
				}
				if(type==4){
					devFaultTypeForm.setDevctrlCount(count);
				}
				map.put(sceneId, devFaultTypeForm);
			}
		}//for结束符
		Struts2Utils.getRequest().setAttribute("map", map);
		return "gm_devFaultType";
	}
	
	/**
	 * 上报数据统计
	 * @return 
	 * weixiaohua
	 */
	public String reportDataStatic(){
		return "reportDataStatic";
	}
	/**
	 * 根据GPRS,进行上报数据统计
	 * @return 
	 * weixiaohua
	 */
	public String reportDataStaticList(){
		Map<String, ReportDataForm> map = new LinkedHashMap<String, ReportDataForm>();
		String userId=Struts2Utils.getSession().getAttribute("userid").toString();
		List<String> scene_id_arr = new ArrayList<String>();
		if(scene_id!=null){
			scene_id_arr.add(this.scene_id);				
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(userId);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
		}
		String scene_id_selectAll="";
		for(String sceid : scene_id_arr) {
			scene_id_selectAll=scene_id_selectAll+sceid+"','";
		}
		if(scene_id_selectAll.length()>0){
			scene_id_selectAll=scene_id_selectAll.substring(0, scene_id_selectAll.length()-3);
		}
		List<String > GPRSList=this.gm_DeviceService.findGRPSBysceneId(scene_id_selectAll);//根据场景Id得到GPRS设备
		String dev_id_selectAll="";
		for(String str : GPRSList) {
			dev_id_selectAll=dev_id_selectAll+str+"','";
		}
		if(dev_id_selectAll.length()>0){
			dev_id_selectAll=dev_id_selectAll.substring(0, dev_id_selectAll.length()-3);
		}
		Integer reportCount=0;
		List<Object[] > ch_idList=this.gm_DevChannelService.findCh_IdByDev_Id(dev_id_selectAll);
		for(Object[] str : ch_idList) {
			String scene_name=str[0].toString();//场景名称
			String dev_id=str[1].toString();//设备Id
			String dev_addr=str[2].toString();//设备地址
			String ch_id=str[3].toString();//通道Id
			//Integer reportCount=Integer.parseInt(str[2].toString());//上报个数
			ReportDataForm reportDataForm=map.get(dev_addr);
			if(reportDataForm==null || "".equals(reportDataForm)){//如果map中没有此设备
				reportDataForm =new ReportDataForm();
				reportDataForm.setScene_name(scene_name);
				reportDataForm.setDev_addr(dev_addr);
				reportCount = gm_HistorydataService.findCountByCh_IdAndTime(beginTime,endTime,ch_id).intValue();	
//				for(String si : list) {
//					reportCount=Integer.parseInt(si.toString());//上报个数
//				}
				reportDataForm.setReportCount(reportCount);
				map.put(dev_addr, reportDataForm);
			}
		}
		Struts2Utils.getRequest().setAttribute("map", map);
		return "reportDataStatic";
	}
	
//	
//	/**
//	 * 导出故障信息到Excel
//	 * @return
//	 * @author weixiaohua
//	 */
//	public HSSFWorkbook daochuProjectExcel(HSSFWorkbook wb){
//		String result = "操作失败!未知错误";
//		wb = new HSSFWorkbook();//实例化工作类
//		HSSFSheet sheet = wb.createSheet("故障诊断报表"); //创建一张表
//	    sheet.setDefaultColumnWidth((short)20);//默认的列的宽度	    
//	    HSSFCellStyle cs_head = wb.createCellStyle();
//	    cs_head.setWrapText(true);     
//	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        HSSFRow row = sheet.createRow((short) 0);
//        HSSFCell cell;
//        cell = row.createCell((short)0);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("场景名称"));
//        cell = row.createCell((short)1);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("设备地址"));
//        cell = row.createCell((short)2);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("平台故障"));
//        cell = row.createCell((short)3);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("网关故障"));
//        cell = row.createCell((short)4);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("小无线故障"));
//        cell = row.createCell((short)5);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("传感器故障"));
//        cell = row.createCell((short)6);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("控制设备故障"));
//        cell = row.createCell((short)7);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("合计"));
//        
//        Map<String, DevFaultTypeForm> map = new LinkedHashMap<String, DevFaultTypeForm>();
//		String userId=Struts2Utils.getSession().getAttribute("userid").toString();
//		List<String> scene_id_arr = new ArrayList<String>();
//		if(scene_id!=null){
//			scene_id_arr.add(this.scene_id);				
//			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(userId);
//			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
//		}
//		String scene_id_selectAll="";
//		for(String sceid : scene_id_arr) {
//			scene_id_selectAll=scene_id_selectAll+sceid+"','";
//		}
//		if(scene_id_selectAll.length()>0){
//			scene_id_selectAll=scene_id_selectAll.substring(0, scene_id_selectAll.length()-3);
//		}
//		
//		List<Object[] > devFaultTypeList=this.gm_DevFaultService.findDevFaultTypeListByUserIdAndTime(userId,beginTime,endTime,scene_id_selectAll);
//		for(Object[] str : devFaultTypeList) {
//			String sceneId=str[0].toString();
//			String scene_name=str[1].toString();
//			String net_addr=str[2].toString();
//			Integer type=Integer.parseInt(str[3].toString());
//			Integer count=Integer.parseInt(str[4].toString());
//			DevFaultTypeForm devFaultTypeForm=map.get(sceneId);
//			if(devFaultTypeForm==null || "".equals(devFaultTypeForm)){//如果map中没有此场景
//				devFaultTypeForm =new DevFaultTypeForm();
//				devFaultTypeForm.setScene_name(scene_name);
//				devFaultTypeForm.setNet_addr(net_addr);
//				if(type==0){
//					devFaultTypeForm.setPlatformCount(count);
//				}
//				if(type==1){
//					devFaultTypeForm.setGPRSCount(count);
//				}
//				if(type==2){
//					devFaultTypeForm.setWsnCount(count);				
//				}
//				if(type==3){
//					devFaultTypeForm.setSensorCount(count);
//				}
//				if(type==4){
//					devFaultTypeForm.setDevctrlCount(count);
//				}
//				map.put(sceneId, devFaultTypeForm);
//			}else{
//				if(type==0){
//					devFaultTypeForm.setPlatformCount(count);
//				}
//				if(type==1){
//					devFaultTypeForm.setGPRSCount(count);
//				}
//				if(type==2){
//					devFaultTypeForm.setWsnCount(count);				
//				}
//				if(type==3){
//					devFaultTypeForm.setSensorCount(count);
//				}
//				if(type==4){
//					devFaultTypeForm.setDevctrlCount(count);
//				}
//				map.put(sceneId, devFaultTypeForm);
//			}
//		}//for结束符
//        
//	    HSSFCellStyle cs_data = wb.createCellStyle();
//	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//        List<Op_Scene> op_Scene_list = this.sceneDao.findAll();
//        int i = 1;
//        Map op_Scene_map = new HashMap();        	
//        for(Op_Scene op_Scene : op_Scene_list){
//        	op_Scene_map.put(op_Scene.getScene_id(), op_Scene);
//        }
//        
//        for(Op_Scene op_Scene : op_Scene_list){
//            row = sheet.createRow((short) i);i++;
//            cell = row.createCell((short)0);
//            cell.setCellStyle(cs_data);            
//            cell.setCellValue(op_Scene.getScene_no()!=null?op_Scene.getScene_no():"");
//            cell = row.createCell((short)1);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_name()!=null?op_Scene.getScene_name():"");
//            cell = row.createCell((short)2);
//            cell.setCellStyle(cs_data);
//            Op_Scene op_Scene_p = op_Scene.getScene_pid()!=null?(Op_Scene)op_Scene_map.get(op_Scene.getScene_pid()):null;
//            cell.setCellValue((op_Scene_p!=null&&op_Scene_p.getScene_no()!=null)?op_Scene_p.getScene_no():"");
//            cell = row.createCell((short)3);
//            cell.setCellStyle(cs_data);
//            String scene_type_name = Scene_type.findNameById(op_Scene.getScene_type());
//            cell.setCellValue(scene_type_name!=null?scene_type_name:"");
//            cell = row.createCell((short)4);
//            cell.setCellStyle(cs_data);
//            String scene_gtype_name = Scene_gtype.findNameById(op_Scene.getScene_gtype());
//            cell.setCellValue(scene_gtype_name!=null?scene_gtype_name:"");
//            cell = row.createCell((short)5);
//            cell.setCellStyle(cs_data);
//            String scene_ctype_name = Scene_ctype.findNameById(op_Scene.getScene_ctype());
//            cell.setCellValue(scene_ctype_name!=null?scene_ctype_name:"");
//            cell = row.createCell((short)6);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_rank()!=null?op_Scene.getScene_rank()+"":"");
//            cell = row.createCell((short)7);
//            cell.setCellStyle(cs_data);           
//            cell.setCellValue((op_Scene.getArea_id()!=null&&op_Scene.getArea_id().getArea_id()!=null)?op_Scene.getArea_id().getArea_id():"");
//            cell = row.createCell((short)8);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_creater()!=null?op_Scene.getScene_creater():"");
//            cell = row.createCell((short)9);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_desc()!=null?op_Scene.getScene_desc():"");
//            cell = row.createCell((short)10);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_keyWord()!=null?op_Scene.getScene_keyWord():"");
//            cell = row.createCell((short)11);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_addr()!=null?op_Scene.getScene_addr():"");
//        }
//        return wb;
//	}
//	
	
	/**
	 * 查询故障症状信息
	 * @return json
	 * 	        	[
	 * 					{
	 * 						id:'xx',
	 * 						name:'xx'
	 *					}
	 *				] 
	 * @author Wang_Yuliang
	 */
	public String json_getDef_symptomList(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Def_symptom> list = StaticDataManage.getDef_symptomList(this.def_object_int, this.def_type_int);
			String json = "[";
			for(Def_symptom def_symptom : list){
				json += "{";
				json += "id:'"+def_symptom.getId()+"',";
				json += "name:'"+def_symptom.getName()+"'";
				json += "},";
			}
			if(json.length()>1)
				json = json.substring(0,(json.length()-1));
			out.print(json+"]");
		} catch (IOException e) {
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ee){ee.printStackTrace();}
		}
		return null;
	}
	//-------------------------------------------------------------------------------------------

	@Autowired
	Gm_DevFaultService gm_DevFaultService;
	@Autowired
	Gm_DeviceService gm_DeviceService;
	@Autowired Gm_DevChannelService gm_DevChannelService;
	@Autowired Gm_HistorydataService gm_HistorydataService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Op_SceneService op_SceneService;
	
	private Page<Gm_DevFault> page = new Page<Gm_DevFault>();
	
	private Gm_DevFault gm_DevFault = new Gm_DevFault();
	
	private DevFaultSearchForm devFaultSearchForm = new DevFaultSearchForm();

	private String queryName;
	
	private String queryValue;
	
	private String def_id;
	
	private String beginTime;
	
	private String endTime;
	
	private String def_type;
	
	private String def_grade;
	
	private String scene_id;
	
	private Integer def_object_int; //查询故障症状信息
	private Integer def_type_int; //查询故障症状信息

	public Integer getDef_object_int() {
		return def_object_int;
	}

	public void setDef_object_int(Integer def_object_int) {
		this.def_object_int = def_object_int;
	}

	public Integer getDef_type_int() {
		return def_type_int;
	}

	public void setDef_type_int(Integer def_type_int) {
		this.def_type_int = def_type_int;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getDef_type()
	{
		return def_type;
	}


	public void setDef_type(String def_type)
	{
		this.def_type = def_type;
	}


	public String getDef_grade()
	{
		return def_grade;
	}


	public void setDef_grade(String def_grade)
	{
		this.def_grade = def_grade;
	}


	public String getDef_id()
	{
		return def_id;
	}


	public void setDef_id(String def_id)
	{
		this.def_id = def_id;
	}


	public String getQueryName()
	{
		return queryName;
	}

	public void setQueryName(String queryName)
	{
		this.queryName = queryName;
	}

	public String getQueryValue()
	{
		return queryValue;
	}

	public void setQueryValue(String queryValue)
	{
		this.queryValue = queryValue;
	}

	public Page<Gm_DevFault> getPage()
	{
		return page;
	}

	public void setPage(Page<Gm_DevFault> page)
	{
		this.page = page;
	}

	public Gm_DevFault getGm_DevFault()
	{
		return gm_DevFault;
	}

	public void setGm_DevFault(Gm_DevFault gm_DevFault)
	{
		this.gm_DevFault = gm_DevFault;
	}


	public String getBeginTime()
	{
		return beginTime;
	}


	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}


	public String getEndTime()
	{
		return endTime;
	}


	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}
	
	public DevFaultSearchForm getDevFaultSearchForm() {
		return devFaultSearchForm;
	}

	public void setDevFaultSearchForm(DevFaultSearchForm devFaultSearchForm) {
		this.devFaultSearchForm = devFaultSearchForm;
	}

}



