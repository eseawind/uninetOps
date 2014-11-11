package org.unism.pro.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_Channel;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.Op_SceneService;
import org.unism.pro.domain.Pro_forecast;
import org.unism.pro.service.ForecastService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

public class ForecastAction extends ActionSupport {

	public String json_findForecastDataByScene_IdAnd60min(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			//System.out.println(op_Scene.getScene_id());
			String scene_id=op_Scene.getScene_id();
			
			int count=12;
			Page<Pro_forecast> page = new Page<Pro_forecast>(count);
			page = forecastService.findPageByPropertyExact(page, "scene.scene_id", scene_id);
			List<Pro_forecast> list = page.getResult();//.findAllEq("scene.scene_id", scene_id);
			String json = "[";
			if(list.size()>0){
				json += "{name:'预测值',visible:true,marker: {symbol: 'square'},data:[";
				for(Pro_forecast foc : list){
					json += "["+foc.getForecast_time()+","+foc.getHida_forecastValue()+"],";
				}
				json = json.substring(0,json.length()-1);
				json = json+"]},";
			}
			DateTime nowDateTime = new DateTime();
			String time=nowDateTime.toString("yyyy-MM-dd HH:mm:ss");
			List<Object[]> hourDataList=forecastService.findHourDataByHistoryData(time,count);
			if(hourDataList.size()>0){
				json += "{name:'实际值',visible:true,marker: {symbol: 'square'},data:[";
				for(Object[] objs : hourDataList){
					json += "["+String.valueOf(objs[1])+","+String.valueOf(objs[0])+"],";
				}
				json = json.substring(0,json.length()-1);
				json = json+"]}";
			}
			json = json+"]";
			//System.out.println("json:"+json);
			out.print(json);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			try {
//				HttpServletResponse response = Struts2Utils.getResponse();
//				response.setContentType("text/html; charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.print("[]");
//			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	@Autowired ForecastService forecastService;
	private Op_Scene op_Scene = new Op_Scene();

	public Op_Scene getOp_Scene() {
		return op_Scene;
	}

	public void setOp_Scene(Op_Scene op_Scene) {
		this.op_Scene = op_Scene;
	}
}
