package org.unism.gm.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.action.form.DevFaultTypeForm;
import org.unism.gm.action.form.HisFindForm;
import org.unism.gm.action.form.ReportDataForm;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_Historydata;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.gm.service.Gm_HistorydataService;
import org.unism.gm.service.IExcelService;
import org.unism.gm.util.DateTool;
import org.unism.gm.util.ExcelServiceImpl;
import org.unism.gm.util.NumUtils;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.util.Chtype_for_charts;
import org.unism.util.DateTools;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.utils.DateUtil;
import org.wangzz.core.web.struts2.Struts2Utils;

import EDU.oswego.cs.dl.util.concurrent.Channel;

import com.google.gson.Gson;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.exception.ExpressionRuntimeException;


public class Gm_HistorydataAction {

	/**
	 * 曲线分析 指定采集通道id集合 起止时间 这里处理的比较简单了，但可以满足页面的需求，即起止时间差>1000*60*60*24*7 显示日均线，否则显示日均线
	 * @return json for Highcharts series
	 * 					[
	 * 						{name: 'aa',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},			
	 * 						{name: 'bb',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},
	 * 						...
	 * 					] 
	 * @author Wang_Yuliang
	 * 2011-05-19 11:15 弃用
	 */
	public String json_findHistoryByChannelsAndTime(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			beginTime += " 00:00:00";
			endTime += " 23:59:59";
			//0609需求加的 dateView 要改为24小时
			if(charts_type.equals("date")){
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*1l));
				endTime = df.format(new Date());
			}
			String[] ch_id_arr = ch_id_list.split(",");
			String json = "[";
			for(String ch_id : ch_id_arr){
				Gm_Channel gm_Channel = this.gm_ChannelService.findById(ch_id);
				if(gm_Channel!=null){
					String data = "[]";
					try{
						data = this.gm_HistorydataService.json_findHistoryByCh_idAndTime(gm_Channel.getCh_id(), beginTime, endTime);
					}catch(Exception exxx){exxx.printStackTrace();}
					json += "{name:'"+gm_Channel.getCh_no()+"-"+gm_Channel.getCh_name()+"',marker: {symbol: 'square'},data:"+data+"},";
				}
			}
			if(json.length()>1){
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 曲线分析0609 指定采集通道id集合 起止时间 这里处理的比较简单了，但可以满足页面的需求，即起止时间差>1000*60*60*24*7 显示日均线，否则显示日均线
	 * @return json for Highcharts series
	 * 				[
	 * 					chtype_id:'aa',
	 * 					chtype_no:'aa',	
	 * 					chtype_displayName:'aa',
	 * 						line:[
	 * 							{name: 'aa',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},			
	 * 							{name: 'bb',marker: {symbol: 'square'},data: [23, 19, 23,21,24, 24,23,26,22, 23, 24, 23]},
	 * 							...
	 * 						]
	 * 				] 
	 * @author Wang_Yuliang
	 * 
	 */
	public String json_findHistoryByChannelsAndTime_0609(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			beginTime += " 00:00:00";
			endTime += " 23:59:59";			
			String[] ch_id_arr = ch_id_list.split(",");
			List<Chtype_for_charts> chtype_for_charts_list = this.gm_ChannelService.findCh_id_arr_listGroupByChtype_no(ch_id_arr);
			String json = "[";
			Integer c1 = 0;
			for(Chtype_for_charts chtype_for_charts : chtype_for_charts_list){				
				json += "{chtype_id:'"+chtype_for_charts.chtype_id+"',chtype_no:'"+chtype_for_charts.chtype_no+"',chtype_displayName:'"+chtype_for_charts.chtype_displayName+"',lines:[";
				Integer c2 = 0;
				for(Gm_Channel gm_Channel : chtype_for_charts.channel_list){
					if(gm_Channel!=null){
						String data = "[]";
						try{
							data = this.gm_HistorydataService.json_findHistoryByCh_idAndTime(gm_Channel.getCh_id(), beginTime, endTime);
						}catch(Exception exxx){exxx.printStackTrace();}
						json += "{name:'"+gm_Channel.getCh_no()+"-"+gm_Channel.getCh_name()+"',marker: {symbol: 'square'},data:"+data+"},";
						c2 += 1;
					}
				}
				if(c2>0){
					json = json.substring(0,json.length()-1);
				}
				json += "]},";	
				c1 += 1;
			}
			if(c1>0){
				json = json.substring(0,json.length()-1);
			}
			out.print(json+"]");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 数据查询
	 * @return
	 * @author Liu_ChangLong
	 */
	public String findHistoryByChannelsAndTime(){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			beginTime += " 00:00:00";
			endTime += " 23:59:59";
			Long hoursToMS = Long.parseLong(hours)*60*60*1000;
			Long minutesToMS = Long.parseLong(minutes)* 60 * 1000;
			Long beginMS=sdf.parse(beginTime).getTime();
			Long endMS=sdf.parse(endTime).getTime();
			Long now = new Date().getTime();
			if(endMS > now){
				endMS = now;
			}
			Long MS= endMS - beginMS;
			Long toMs = hoursToMS + minutesToMS;
			Long size = (MS+1000)/toMs;
			
			int pageNo = Integer.parseInt(pageIndex); 
			
			Long startTime = beginMS+(pageNo-1)*500*toMs;
			Long overTime = startTime + 500*toMs;
			if(overTime > endMS){
				overTime = endMS;
			}
			beginTime = sdf.format(startTime);
			endTime = sdf.format(overTime);
			Long realSize = (overTime-startTime+1000)/toMs;
			
			
			StringBuilder jsonBuilder = new StringBuilder();
			jsonBuilder.append("[{time:'时间＼数据通道',data:[");
			String[] chIdArr = ch_id_list.split(",");
			List<String> chIdList = new ArrayList<String>();
			String[] menu = new String[chIdArr.length];
			for (String string : chIdArr) {
				chIdList.add(string);
			}
			List<Gm_Channel> channels = gm_ChannelService.findByIdArr(chIdArr);
			for (Gm_Channel gm_Channel : channels) {
				int index = chIdList.indexOf(gm_Channel.getCh_id());
				menu[index] = gm_Channel.getCh_no()+"-"+gm_Channel.getCh_name();
			}
			for (int i = 0; i < menu.length; i++) {
				jsonBuilder.append("['").append(menu[i]).append("'],");
			}
			if(jsonBuilder.length() > 0){
				jsonBuilder.deleteCharAt(jsonBuilder.length()-1);
			}
			jsonBuilder.append("]},");
			List<Gm_Historydata> gm_Historydatas = gm_HistorydataService.findHistoryByChIdArrAndTime(chIdArr,DateUtil.getDate(beginTime, "yyyy-MM-dd hh:mm:ss"),DateUtil.getDate(endTime, "yyyy-MM-dd hh:mm:ss"));
			Map<String, List<Double>> map = new HashMap<String, List<Double>>();
			Map<String, List<String>> corrValueMap = new LinkedHashMap<String, List<String>>();
			for (int i = 0; i < chIdArr.length; i++) {
				List<String> valueList = new ArrayList<String>();
				Long beginTimeMs = startTime;
				int num = 0;
				for (Gm_Historydata gm_Historydata : gm_Historydatas) {
					num++;
					if (gm_Historydata.getCh_id().getCh_id().equals(chIdArr[i].toString())) {
						while (gm_Historydata.getHida_record_time().getTime() >= (beginTimeMs + toMs)) {
							List<Double> corrValues = map.get(chIdArr[i].toString());
							double sum = 0;
							if(corrValues!=null){
								for (Double corrValue : corrValues) {
									sum += corrValue;
								}
							}
							double avgValue = 0;
							int digits = 2;//channel.getCh_dectDig();
							if(corrValues!=null){
								double avg = sum/corrValues.size();
								avgValue = NumUtils.format(avg, digits);
							}
							valueList.add(avgValue + "");
							beginTimeMs += toMs;
							map.clear();
						}
					}
					
					if(chIdArr[i].toString().equals(gm_Historydata.getCh_id().getCh_id())){
						if (map.get(chIdArr[i].toString()) == null) {
							List<Double> list = new ArrayList<Double>();
							Double valueD = null;
							if("origValue".equals(this.dataType)){
								valueD = gm_Historydata.getHida_origValue();
							}else{
								valueD = gm_Historydata.getHida_corrValue();
							}
							list.add(valueD==null?0:valueD);
							map.put(gm_Historydata.getCh_id().getCh_id(), list);
						} else {
							Double valueD = null;
							if("origValue".equals(this.dataType)){
								valueD = gm_Historydata.getHida_origValue();
							}else{
								valueD = gm_Historydata.getHida_corrValue();
							}
							map.get(gm_Historydata.getCh_id().getCh_id()).add(valueD==null?0:valueD);
						}
					}
					
					if(num == gm_Historydatas.size()){
						List<Double> corrValues = map.get(chIdArr[i].toString());
						double sum = 0;
						if(corrValues!=null){
							for (Double corrValue : corrValues) {
								sum += corrValue;
							}
						}
						double avgValue = 0;
						int digits = 2;//channel.getCh_dectDig();
						if(corrValues != null){
							double avg = sum/corrValues.size();
							avgValue = NumUtils.format(avg, digits);
						}
						valueList.add(avgValue+"");
						beginTimeMs += toMs;
						map.clear();
					}
				}
				corrValueMap.put(chIdArr[i].toString(), valueList);
			}
			Long begin = sdf.parse(beginTime).getTime();
			for (int i = 0; i < realSize; i++) {
				StringBuilder builder = new StringBuilder("[");
				for(Map.Entry<String, List<String>> entry : corrValueMap.entrySet()) {   
					List<String> valueList = entry.getValue();
					if(null != valueList && valueList.size() > 0) {
						if(i < valueList.size()){
							builder.append("['").append(valueList.get(i)).append("'],");
						}else {
							builder.append("['").append(0.00).append("'],");
						}
					}
				}
				if(builder.length() > 2){
					builder.deleteCharAt(builder.length()-1);
				}
				builder.append("]");
				jsonBuilder.append("{time:'").append(sdf.format(begin)).append("',data:").append(builder.toString()).append("},");
				begin += toMs;
			}
			if(jsonBuilder.length() > 1){
				jsonBuilder.deleteCharAt(jsonBuilder.length()-1);
			}
			jsonBuilder.append("]");
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			out.print(jsonBuilder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 到曲线分析
	 * @return
	 * @author Wang_Yuliang
	 * 不在使用 王雨良 0601 UP
	 */
	public String toCharts(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateView_beginTime = df.format(new Date());
		String dateView_endTime = df.format(new Date());		
		String weekView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*6l));
		String weekView_endTime = df.format(new Date());
		String monthView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*29l));
		String monthView_endTime = df.format(new Date());
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
		String yearView_beginTime = df2.format(new Date())+"-01-01";
		String yearView_endTime = df2.format(new Date())+"-12-31";
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("dateView_beginTime", dateView_beginTime);
		request.setAttribute("dateView_endTime", dateView_endTime);
		request.setAttribute("weekView_beginTime", weekView_beginTime);
		request.setAttribute("weekView_endTime", weekView_endTime);
		request.setAttribute("monthView_beginTime", monthView_beginTime);
		request.setAttribute("monthView_endTime", monthView_endTime);
		request.setAttribute("yearView_beginTime", yearView_beginTime);
		request.setAttribute("yearView_endTime", yearView_endTime);
		return "charts";
	}
	
	/**
	 * 到曲线分析0601
	 * @return
	 * @author Wang_Yuliang
	 * 不在使用 王雨良 0601 UP
	 */
	public String toCharts_0601(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateView_beginTime = df.format(new Date());
		String dateView_endTime = df.format(new Date());		
		String weekView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*6l));
		String weekView_endTime = df.format(new Date());
		String monthView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*29l));
		String monthView_endTime = df.format(new Date());
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
		String yearView_beginTime = df2.format(new Date())+"-01-01";
		String yearView_endTime = df2.format(new Date())+"-12-31";
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("dateView_beginTime", dateView_beginTime);
		request.setAttribute("dateView_endTime", dateView_endTime);
		request.setAttribute("weekView_beginTime", weekView_beginTime);
		request.setAttribute("weekView_endTime", weekView_endTime);
		request.setAttribute("monthView_beginTime", monthView_beginTime);
		request.setAttribute("monthView_endTime", monthView_endTime);
		request.setAttribute("yearView_beginTime", yearView_beginTime);
		request.setAttribute("yearView_endTime", yearView_endTime);
		return "charts_0601";
	}
	
	
	public String toCharts_0601_run(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateView_beginTime = df.format(new Date());
		String dateView_endTime = df.format(new Date());		
		String weekView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*6l));
		String weekView_endTime = df.format(new Date());
		String monthView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*29l));
		String monthView_endTime = df.format(new Date());
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
		String yearView_beginTime = df2.format(new Date())+"-01-01";
		String yearView_endTime = df2.format(new Date())+"-12-31";
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("dateView_beginTime", dateView_beginTime);
		request.setAttribute("dateView_endTime", dateView_endTime);
		request.setAttribute("weekView_beginTime", weekView_beginTime);
		request.setAttribute("weekView_endTime", weekView_endTime);
		request.setAttribute("monthView_beginTime", monthView_beginTime);
		request.setAttribute("monthView_endTime", monthView_endTime);
		request.setAttribute("yearView_beginTime", yearView_beginTime);
		request.setAttribute("yearView_endTime", yearView_endTime);
		return "toCharts_0601_run";
		
	}
	
	
	/**
	 * 到曲线分析0609
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toCharts_0609(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateView_beginTime = df.format(new Date());
		String dateView_endTime = df.format(new Date());		
		String weekView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*6l));
		String weekView_endTime = df.format(new Date());
		String monthView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*29l));
		String monthView_endTime = df.format(new Date());
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
		String yearView_beginTime = df2.format(new Date())+"-01-01";
		String yearView_endTime = df2.format(new Date())+"-12-31";
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("dateView_beginTime", dateView_beginTime);
		request.setAttribute("dateView_endTime", dateView_endTime);
		request.setAttribute("weekView_beginTime", weekView_beginTime);
		request.setAttribute("weekView_endTime", weekView_endTime);
		request.setAttribute("monthView_beginTime", monthView_beginTime);
		request.setAttribute("monthView_endTime", monthView_endTime);
		request.setAttribute("yearView_beginTime", yearView_beginTime);
		request.setAttribute("yearView_endTime", yearView_endTime);
		return "charts_0609";
	}
	
	
	public String toCharts_0609_run(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateView_beginTime = df.format(new Date());
		String dateView_endTime = df.format(new Date());		
		String weekView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*6l));
		String weekView_endTime = df.format(new Date());
		String monthView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*29l));
		String monthView_endTime = df.format(new Date());
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
		String yearView_beginTime = df2.format(new Date())+"-01-01";
		String yearView_endTime = df2.format(new Date())+"-12-31";
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("dateView_beginTime", dateView_beginTime);
		request.setAttribute("dateView_endTime", dateView_endTime);
		request.setAttribute("weekView_beginTime", weekView_beginTime);
		request.setAttribute("weekView_endTime", weekView_endTime);
		request.setAttribute("monthView_beginTime", monthView_beginTime);
		request.setAttribute("monthView_endTime", monthView_endTime);
		request.setAttribute("yearView_beginTime", yearView_beginTime);
		request.setAttribute("yearView_endTime", yearView_endTime);
		return "toCharts_run";
	}
	
	
	//未使用 王雨良 0601 UP
	public String toDataquery(){
		HttpSession session = Struts2Utils.getSession();
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate=format.format(date);
		session.setAttribute("nowDate", nowDate);
		return "dataquery";
	}
	
	/**
	 * 到数据查询0601
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toDataquery_0601(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateView_beginTime = df.format(new Date());
		String dateView_endTime = df.format(new Date());		
		String weekView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*6l));
		String weekView_endTime = df.format(new Date());
		String monthView_beginTime = df.format(new Date().getTime()-(1000l*60l*60l*24l*29l));
		String monthView_endTime = df.format(new Date());
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy");
		String yearView_beginTime = df2.format(new Date())+"-01-01";
		String yearView_endTime = df2.format(new Date())+"-12-31";
		HttpServletRequest request = Struts2Utils.getRequest();
		request.setAttribute("dateView_beginTime", dateView_beginTime);
		request.setAttribute("dateView_endTime", dateView_endTime);
		request.setAttribute("weekView_beginTime", weekView_beginTime);
		request.setAttribute("weekView_endTime", weekView_endTime);
		request.setAttribute("monthView_beginTime", monthView_beginTime);
		request.setAttribute("monthView_endTime", monthView_endTime);
		request.setAttribute("yearView_beginTime", yearView_beginTime);
		request.setAttribute("yearView_endTime", yearView_endTime);
		return "dataquery_0601";
	}
	
	/**
	 * 场景监控曲线图 测试
	 * @return
	 */
	public String tt(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(this.gm_HistorydataService.tt());			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[null,null]");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;		
	}
	
	
	public String dataExport(){
//		List<Object[]> objList = new ArrayList<Object[]>();
//		int day = 0;
//		Op_Scene op_Scene = op_SceneService.findById(scene_id);
//		try
//		{
//			HttpServletResponse response = Struts2Utils.getResponse();
//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			beginTime += " 00:00:00";
//			endTime += " 23:59:59";
//			Long beginMS=df.parse(beginTime).getTime();//开始毫秒数
//			Long endMS=df.parse(endTime).getTime();//结束毫秒数
//			Long MS= endMS - beginMS;//开始时间和结束时间之间的时间差
//			day = (int) ((MS/(3600000*24))+1);
//			Long hoursToMS = Long.parseLong(hours)*60*60*1000;
//			Long minutesToMS = Long.parseLong(minutes)* 60 * 1000;
//			Long secondsToMS = Long.parseLong(seconds)* 1000;			
//			Long toMs = hoursToMS + minutesToMS + secondsToMS;//时间间隔(毫秒)
//			Long size = (MS+1000)/toMs;//要显示的数据行数
//			String[] ch_id_arr = ch_id_list.split(",");
//			List<String> lableList = new ArrayList<String>();
//			lableList.add("时间＼数据通道");
//			List<String> dataList = new ArrayList<String>();
//			dataList.add("时间＼数据通道");
//			Long timeadd = beginMS;
//			Double avg = 0.0;
//			NumberFormat   nbf=NumberFormat.getInstance();//新建一个格式化器对象
//			String avgString="";
//			
//			for(String ch_id : ch_id_arr){
//				Gm_Channel gm_Channel = this.gm_ChannelService.findById(ch_id);
//				dataList.add(gm_Channel.getCh_no()+"-"+gm_Channel.getCh_name());
//			}
//			
//			for(int i = 0;i<size;i++){			
//				if(i==0){
//					dataList.add(beginTime);
//					for(String ch_id : ch_id_arr){
//						avg=this.gm_HistorydataService.findHistoryByCh_idAndTime(ch_id, beginTime, df.format(beginMS+toMs));
//						if(avg!=null){
//							nbf.setMaximumFractionDigits(2);//设置最大保留两位小数
//							avgString=nbf.format(avg);//将a/b格式化后的值赋给c,注意是String型
//							dataList.add(avgString);
//						}else{
//							dataList.add("");
//						}						
//					}	
//				}else{
//				    timeadd = timeadd + toMs;
//					String time = df.format(timeadd);
//					dataList.add(time);
//					for(String ch_id : ch_id_arr){								
//						avg=this.gm_HistorydataService.findHistoryByCh_idAndTime(ch_id, time, df.format(timeadd+toMs));
//						if(avg!=null){
//							nbf.setMaximumFractionDigits(2);//设置最大保留两位小数
//							avgString=nbf.format(avg);//将a/b格式化后的值赋给c,注意是String型
//							dataList.add(avgString);
//						}else{
//							dataList.add("");
//						}																	
//					}					
//				}				    
//			}
//			int IntSize = Integer.parseInt(size.toString());
//			for (String string : dataList)
//			{
//			}
//			int c=0;
//			for (int i = 0; i < IntSize; i++)
//			{
//				
//				Object[] object = new Object[ch_id_arr.length+1];
//				for (int j = 0; j < ch_id_arr.length+1; j++)
//				{
//					
//						object[j] = dataList.get(c);
//						c++;
//					
//				}
//				objList.add(object);
//			}
//		} catch (Exception e)
//		{
//			// TODO: handle exception
//		}
//		for (Object[] objects : objList)
//		{
//			for (Object object : objects)
//			{
//			}
//		}
//		String name = op_Scene.getScene_name()+"_" + hours +"小时"+ minutes +"分"+ seconds +"秒" + "_" + day+"天";
//		try {
//			Struts2Utils.getResponse().setHeader("Content-Disposition",  "attachment;filename=" + new String(name.getBytes("GBK"),"ISO-8859-1")+".xls");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		excelStream = excelService.getExcelInputStream(objList);
//		return "excel";
		
		List<Object[]> objList = new ArrayList<Object[]>();
		int day = 0;
		Op_Scene op_Scene = op_SceneService.findById(scene_id);
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			beginTime += " 00:00:00";
			endTime += " 23:59:59";
			Long beginMS=sdf.parse(beginTime).getTime();//开始毫秒数
			Long endMS=sdf.parse(endTime).getTime();//结束毫秒数
			Long MS= endMS - beginMS;//开始时间和结束时间之间的时间差
			day = (int) ((MS/(3600000*24))+1);
			Long hoursToMS = Long.parseLong(hours)*60*60*1000;
			Long minutesToMS = Long.parseLong(minutes)* 60 * 1000;
			Long toMs = hoursToMS + minutesToMS;
			Long size = (MS+1000)/toMs;//要显示的数据行数
			String[] chIdArr = ch_id_list.split(",");
			List<String> chIdList = new ArrayList<String>();
			String[] menu = new String[chIdArr.length];
			for (String string : chIdArr) {
				chIdList.add(string);
			}
			List<Gm_Channel> channels = gm_ChannelService.findByIdArr(chIdArr);
			for (Gm_Channel gm_Channel : channels) {
				int index = chIdList.indexOf(gm_Channel.getCh_id());
				menu[index] = gm_Channel.getCh_no()+"-"+gm_Channel.getCh_name();
			}
			List<String> dataList = new ArrayList<String>();
			dataList.add("时间＼数据通道");
			for (String title : menu) {
				dataList.add(title);
			}
//			for(String ch_id : chIdArr){
//				Gm_Channel gm_Channel = this.gm_ChannelService.findById(ch_id);
//				dataList.add(gm_Channel.getCh_no()+"-"+gm_Channel.getCh_name());
//			}
			List<Gm_Historydata> gm_Historydatas = gm_HistorydataService.findHistoryByChIdArrAndTime(chIdArr,DateUtil.getDate(beginTime, "yyyy-MM-dd hh:mm:ss"),DateUtil.getDate(endTime, "yyyy-MM-dd hh:mm:ss"));
			Map<String, List<Double>> map = new HashMap<String, List<Double>>();
			Map<String, List<String>> corrValueMap = new LinkedHashMap<String, List<String>>();
			for (int i = 0; i < chIdArr.length; i++) {
				List<String> valueList = new ArrayList<String>();
				Long beginTimeMs = beginMS;
				int num = 0;
				for (Gm_Historydata gm_Historydata : gm_Historydatas) {
					num++;
					if (gm_Historydata.getCh_id().getCh_id().equals(chIdArr[i].toString())) {
						while (gm_Historydata.getHida_record_time().getTime() >= (beginTimeMs + toMs)) {
							List<Double> corrValues = map.get(chIdArr[i].toString());
							double sum = 0;
							if(corrValues!=null){
								for (Double corrValue : corrValues) {
									sum += corrValue;
								}
							}
							double avgValue = 0;
							if(corrValues!=null){
								avgValue = sum / corrValues.size();
							}
							valueList.add(avgValue + "");
							beginTimeMs += toMs;
							map.clear();
						}
					}
					
					if(chIdArr[i].toString().equals(gm_Historydata.getCh_id().getCh_id())){
						if (map.get(chIdArr[i].toString()) == null) {
							List<Double> list = new ArrayList<Double>();
							list.add(gm_Historydata.getHida_corrValue()==null?0:gm_Historydata.getHida_corrValue());
							map.put(gm_Historydata.getCh_id().getCh_id(), list);
						} else {
							map.get(gm_Historydata.getCh_id().getCh_id()).add(gm_Historydata.getHida_corrValue()==null?0:gm_Historydata.getHida_corrValue());
						}
					}
					
					if(num == gm_Historydatas.size()){
						List<Double> corrValues = map.get(chIdArr[i].toString());
						double sum = 0;
						if(corrValues!=null){
							for (Double corrValue : corrValues) {
								sum += corrValue;
							}
						}
						double avgValue = 0;
						if(corrValues != null){
							avgValue = sum/corrValues.size();
						}
						valueList.add(avgValue+"");
						beginTimeMs += toMs;
						map.clear();
					}
				}
				corrValueMap.put(chIdArr[i].toString(), valueList);
			}
			Long begin = beginMS;
			DecimalFormat df = new DecimalFormat("0.00");
			for (int i = 0; i < size; i++) {
				dataList.add(sdf.format(begin));
				for(String key : corrValueMap.keySet()){
					if(corrValueMap.get(key)!=null&&corrValueMap.get(key).size()>0){
						if(i<corrValueMap.get(key).size()){
							String num = df.format(Double.parseDouble(corrValueMap.get(key).get(i)));
							dataList.add(num);
						}else {
							dataList.add("0");
						}
					}else{
						dataList.add("");
					}
				}
				begin += toMs;
			}
			
			int IntSize = Integer.parseInt(size.toString());
			int c=0;
			for (int i = 0; i < IntSize+1; i++)
			{
				Object[] object = new Object[chIdArr.length+1];
				for (int j = 0; j < chIdArr.length+1; j++)
				{
						object[j] = dataList.get(c);
						c++;
				}
				objList.add(object);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		String name = op_Scene.getScene_name()+"_" + hours +"小时"+ minutes +"分" + "_" + day+"天";
		try {
			Struts2Utils.getResponse().setHeader("Content-Disposition",  "attachment;filename=" + new String(name.getBytes("GBK"),"ISO-8859-1")+".xls");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		excelStream = excelService.getExcelInputStream(objList);
		return "excel";
		
	}
	
	/**
	 * 跳转到历史数据查询
	 * @return
	 */
	public String tofindHistorydata(){
		return "historydata";
	}
	
	/**
	 * 查询历史数据通过通道id和开始结束时间
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findHistorydataDevByCh_id(){
		String[] ch_id = channels.split(",");
		List<String> chIdList = new ArrayList<String>();
		for (int i = 0; i < ch_id.length; i++) {
			chIdList.add(ch_id[i]);
		}
		String[] menus = menu.split(",");
		List<String> menu = new ArrayList<String>();
		for (int i = 0; i < menus.length; i++){
			menu.add(menus[i]);
		}
		Struts2Utils.getRequest().setAttribute("netAddr", netAddr);
		Search search = new Search();
		Filter filter = new Filter();
		if(endTime != null && beginTime != null && !beginTime.equals("") && !endTime.equals("")){
			filter = Filter.and(Filter.in("ch_id.ch_id", ch_id),Filter.greaterOrEqual("hida_record_time", DateUtil.getDate(beginTime, "yyyy-MM-dd hh:mm:ss")),Filter.lessOrEqual("hida_record_time", DateUtil.getDate(endTime, "yyyy-MM-dd hh:mm:ss")));
		}else {
			filter = Filter.and(Filter.in("ch_id.ch_id", ch_id));
		}
		search.addFilter(filter).addSortDesc("hida_record_time");
		Page<Gm_Historydata> hisPage = new Page<Gm_Historydata>(ch_id.length*50);
		try {
			if(StringUtils.hasText(hisPageNo)){
				hisPage.setPageNo(Integer.parseInt(hisPageNo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		hisPage = this.gm_HistorydataService.search(hisPage,search);
		List<Gm_Historydata> gm_HistorydataList = hisPage.getResult();
		Map<String, HisFindForm> map = new LinkedHashMap<String, HisFindForm>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Gm_Historydata gm_Historydata : gm_HistorydataList) { 
			int order = chIdList.indexOf(gm_Historydata.getCh_id().getCh_id());
			String key = sdf.format(gm_Historydata.getHida_record_time())+","+sdf.format(gm_Historydata.getHida_reportTime());
			HisFindForm hisFindForm = map.get(key);
			if(hisFindForm == null) {
				hisFindForm = new HisFindForm(ch_id.length);
				hisFindForm.getOriValue()[order] = gm_Historydata.getHida_origValue();
				hisFindForm.getCorrValue()[order]= gm_Historydata.getHida_corrValue();
				map.put(key, hisFindForm);
			} else {
				hisFindForm.getOriValue()[order] = gm_Historydata.getHida_origValue();
				hisFindForm.getCorrValue()[order]= gm_Historydata.getHida_corrValue();
			}
		}
		
		Struts2Utils.getRequest().setAttribute("map", map);
		Struts2Utils.getRequest().setAttribute("menu", menu);
		Struts2Utils.getRequest().setAttribute("hisPageCount", hisPage.getTotalCount()/ch_id.length);
		Struts2Utils.getRequest().setAttribute("hisPageSize", hisPage.getPageSize()/ch_id.length);
		Struts2Utils.getRequest().setAttribute("hisPage", hisPage);
		return "show";
	}
	
	
	public String toDataAnalysis(){
		return "toDataAnalysis";
	}
	
	
	//{name: 'aa',marker: {symbol: 'square'},data: [[23, 19], [23,21],[24, 24],[23,26],[22, 23], [24, 23]]}";
	public String dataAnalysis(){
		try {
			String json = "[";
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String[] devIds = devId.split(",");
			//List<Gm_Channel> gmChannels = gm_ChannelService.findByIds(channelId);
			List<Gm_Device> gmDevices = gm_DeviceService.findbyIds(devIds);
			for (Gm_Device gmDevice : gmDevices) {
				String data = "[]";
				Gm_Channel gmChannel = gm_ChannelService.findByDevCollectId(gmDevice.getDev_id());
				if(gmChannel!=null){
					if(chartsType.equals("dType")){
						data = gm_HistorydataService.findByCh_idAndTime(gmChannel.getCh_id(),beginTime,endTime);
					}else if(chartsType.equals("nType")){
						data = gm_HistorydataService.findByChIdAndTime(gmChannel.getCh_id(),beginTime,endTime);
					}
				}
				json+="{name:'"+gmDevice.getDev_no()+"("+gmDevice.getDev_name() + ")',marker: {symbol: 'square'},data:" + data + "},";
			}
//			for (Gm_Channel gmChannel : gmChannels) {
//				String data = "[]";
//				if(chartsType.equals("dType")){
//					data = gm_HistorydataService.findByCh_idAndTime(gmChannel.getCh_id(),beginTime,endTime);
//				}else if(chartsType.equals("nType")){
//					data = gm_HistorydataService.findByChIdAndTime(gmChannel.getCh_id(),beginTime,endTime);
//				}
//				json+="{name:'"+gmChannel.getCh_no()+"-"+gmChannel.getCh_name() + "',marker: {symbol: 'square'},data:" + data + "},";
//			}
			if(json.length() > 0){
				json = json.substring(0, json.length()-1);
			}
			out.print(json+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toHistorydataRevise(){
		Date date = new Date();
		Struts2Utils.getRequest().setAttribute("beginTime", DateTool.getDate(date)+" 00:00:00");
		Struts2Utils.getRequest().setAttribute("endTime", DateTool.getDateTime(date));
		return "historydataRevise";
	}
	
	
	public String findHistorydataByChIdAndTime() throws IOException{
		String json = "";
		int count=0;
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		try {
			String[] chIdArr=chIds.split(",");
			String chId="";
			Gm_Channel gm_Channel=null;
			List<Gm_Historydata> list=null;
			for(int i=0;i<chIdArr.length;i++){
				chId=chIdArr[i];
				gm_Channel=gm_ChannelService.findById(chId);
				Integer ch_dectDig=gm_Channel.getCh_dectDig();//小数位数
				Float ch_max=gm_Channel.getCh_max();//量程上限
				Float ch_min=gm_Channel.getCh_min();//量程下限
				Double ch_crateMax=gm_Channel.getCh_crateMax();//变化率上限
				String ch_corrFormula=gm_Channel.getCh_corrFormula();//校正公式
				list=gm_HistorydataService.findHistoryByChIdAndTime(chId, beginTime, endTime);
				int m=1;
				double lastOrigValus=0.0;//上一次处理之前的值
				double lastCorrValus=0.0;//上一次处理之后的值
				for (Gm_Historydata historydata : list) {					
					Double origValue=historydata.getHida_origValue();//处理之前的值
					double corrValue = origValue;//原数据值赋值给处理后数据值
					if(m==1){
						//校正公式
						if (StringUtils.hasText(ch_corrFormula)) {
							try {
								corrValue = getCorrValueByFormula(origValue, ch_corrFormula).floatValue();// 通过公式校正
							} catch (ExpressionRuntimeException e) {
							}
						}
						//小数位数
						double newCorrValue = corrValue;// 校正后数据
						if (null != ch_dectDig) {
							BigDecimal bd = new BigDecimal(corrValue).setScale(ch_dectDig , BigDecimal.ROUND_HALF_UP);
							newCorrValue = bd.doubleValue();// 保留N位小数
						}
						//量程上限
						if(null != ch_max){
							if (newCorrValue > ch_max) {
								newCorrValue = ch_max.doubleValue();
							}
						}
						//量程下限
						if(null != ch_min){
							if (newCorrValue < ch_min) {
								newCorrValue = ch_min.doubleValue();
							}
						}
						
						historydata.setHida_corrValue(newCorrValue);
						gm_HistorydataService.update(historydata);
						lastOrigValus=origValue;
						lastCorrValus=newCorrValue;//比对值,上一次处理之后的值
					}else{						
						//校正公式
						if (StringUtils.hasText(ch_corrFormula)) {
							try {
								corrValue = getCorrValueByFormula(origValue, ch_corrFormula).floatValue();// 通过公式校正
							} catch (ExpressionRuntimeException e) {
							}
						}
						//小数位数
						if (null != ch_dectDig) {
							BigDecimal bd = new BigDecimal(corrValue).setScale(ch_dectDig , BigDecimal.ROUND_HALF_UP);
							corrValue = bd.doubleValue();// 保留N位小数
						}
						double upabsVlues=corrValue-lastCorrValus;//本次处理之前的后-上一次处理之后的值
						double newCorrValue = corrValue;// 校正后数据
						int n=1;
						if(upabsVlues>0){
							n=1;
						}
						if(upabsVlues<0){
							n=-1;
						}
						double absVlues=Math.abs(corrValue-lastCorrValus);//本次处理之后的值-上一次处理之后的值，然后取绝对值
						if(ch_crateMax!=null && absVlues>ch_crateMax){
								if((null != ch_max&&corrValue>ch_max)||(absVlues>ch_crateMax&&corrValue<ch_min)){
									corrValue=lastCorrValus;
								}else{
									corrValue=lastCorrValus+n*ch_crateMax;
								}
						}else{
								//量程上限
								if(null != ch_max){
									if (corrValue > ch_max) {
										corrValue = ch_max.doubleValue();
									}
								}
								//量程下限
								if(null != ch_min){
									if (corrValue < ch_min) {
										corrValue = ch_min.doubleValue();
									}
								}
						}
						
						newCorrValue=corrValue;
						lastOrigValus=origValue;
						historydata.setHida_corrValue(newCorrValue);
						gm_HistorydataService.update(historydata);
						lastCorrValus=newCorrValue;//比对值
					}//else结束符
					m++;
					count++;
				}//for结束符
				
			}
			json = "[{message:\"操作成功\"}]";
		}catch (Exception e) {
			e.printStackTrace();
			json = "[{message:\"操作失败\"}]";
		}
		out.print(json);
		return null;
	}
	
//	public String findHistorydataByChIdAndTime() throws IOException{
//		String json = "";
//		int count=0;
//		PrintWriter out = Struts2Utils.getResponse().getWriter();
//		try {
//			String[] chIdArr=chIds.split(",");
//			String chId="";
//			Gm_Channel gm_Channel=null;
//			List<Gm_Historydata> list=null;
//			for(int i=0;i<chIdArr.length;i++){
//				chId=chIdArr[i];
//				gm_Channel=gm_ChannelService.findById(chId);
//				Integer ch_dectDig=gm_Channel.getCh_dectDig();//小数位数
//				Float ch_max=gm_Channel.getCh_max();//量程上限
//				Float ch_min=gm_Channel.getCh_min();//量程下限
//				Double ch_crateMax=gm_Channel.getCh_crateMax();//变化率上限
//				String ch_corrFormula=gm_Channel.getCh_corrFormula();//校正公式
//				list=gm_HistorydataService.findHistoryByChIdAndTime(chId, beginTime, endTime);
//				int m=1;
//				double lastOrigValus=0.0;//上一次处理之前的值
//				double lastCorrValus=0.0;//上一次处理之后的值
//				for (Gm_Historydata historydata : list) {					
//					Double origValue=historydata.getHida_origValue();//处理之前的值
//					double corrValue = origValue;//原数据值赋值给处理后数据值
//					if(m==1){
//						//量程上限
//						if(null != ch_max){
//							if (origValue > ch_max) {
//								corrValue = ch_max.doubleValue();
//							}
//						}
//						//量程下限
//						if(null != ch_min){
//							if (origValue < ch_min) {
//								corrValue = ch_min.doubleValue();
//							}
//						}
//						//校正公式
//						if (StringUtils.hasText(ch_corrFormula)) {
//							try {
//								corrValue = getCorrValueByFormula(origValue, ch_corrFormula).floatValue();// 通过公式校正
//							} catch (ExpressionRuntimeException e) {
//							}
//						}
//						//小数位数
//						double newCorrValue = corrValue;// 校正后数据
//						if (null != ch_dectDig) {
//							BigDecimal bd = new BigDecimal(corrValue).setScale(ch_dectDig , BigDecimal.ROUND_HALF_UP);
//							newCorrValue = bd.doubleValue();// 保留N位小数
//						}
//						historydata.setHida_corrValue(newCorrValue);
//						gm_HistorydataService.update(historydata);
//						lastOrigValus=origValue;
//						lastCorrValus=newCorrValue;//比对值
//					}else{
//						double upabsVlues=origValue-lastOrigValus;//本次处理之前的值-上一次处理之前的值
//						double newCorrValue = origValue;// 校正后数据
//						int n=1;
//						if(upabsVlues>0){
//							n=1;
//						}
//						if(upabsVlues<0){
//							n=-1;
//						}
//						double absVlues=Math.abs(origValue-lastOrigValus);//本次处理之前的值-上一次处理之前的值，然后取绝对值
//						if(ch_crateMax!=null && absVlues>ch_crateMax){
//								if((null != ch_max&&origValue>ch_max)||(absVlues>ch_crateMax&&origValue<ch_min)){
//									corrValue=lastCorrValus;
//								}else{
//									corrValue=lastCorrValus+n*ch_crateMax;
//								}
//						}else{
//								//量程上限
//								if(null != ch_max){
//									if (origValue > ch_max) {
//										corrValue = ch_max.doubleValue();
//									}
//								}
//								//量程下限
//								if(null != ch_min){
//									if (origValue < ch_min) {
//										corrValue = ch_min.doubleValue();
//									}
//								}
//						}
//						//校正公式
//						if (StringUtils.hasText(ch_corrFormula)) {
//							try {
//								corrValue = getCorrValueByFormula(corrValue, ch_corrFormula).floatValue();// 通过公式校正
//							} catch (ExpressionRuntimeException e) {
//							}
//						}
//						//小数位数
//						if (null != ch_dectDig) {
//							BigDecimal bd = new BigDecimal(corrValue).setScale(ch_dectDig , BigDecimal.ROUND_HALF_UP);
//							corrValue = bd.doubleValue();// 保留N位小数
//						}
//						newCorrValue=corrValue;
//						lastOrigValus=origValue;
//						historydata.setHida_corrValue(newCorrValue);
//						gm_HistorydataService.update(historydata);
//						lastCorrValus=newCorrValue;//比对值
//					}//else结束符
//					m++;
//					count++;
//				}//for结束符
//				
//			}
//			json = "[{message:\"操作成功\"}]";
//		}catch (Exception e) {
//			e.printStackTrace();
//			json = "[{message:\"操作失败\"}]";
//		}
//		out.print(json);
//		return null;
//	}
	
	/**
	 * 根据校正公式校正值
	 * @param origValue 源数据值
	 * @param formula 校正公式
	 * @return 校正后的值
	 * @throws ExpressionRuntimeException 公式不合法
	 */
	public Double getCorrValueByFormula(Double origValue, String formula) throws ExpressionRuntimeException {
		Expression compiledExp = AviatorEvaluator.compile(formula, true);
		Map<String, Object> env = new HashMap<String, Object>();
		env.put("x", origValue);
		return (Double) compiledExp.execute(env);
	}
	
	public String toTestCharts(){
		Date date = new Date();
		Struts2Utils.getRequest().setAttribute("beginTime", DateTool.getDate(date));
		Struts2Utils.getRequest().setAttribute("endTime", DateTool.getDate(date));
		return "toCharts";
	}
	
	public String getDataSum(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String[] chIds = ch_id_list.split(",");
			//Map<String, Integer> map = new HashMap<String, Integer>();
			int dataSum = gm_HistorydataService.findCountByCh_IdAndTime(beginTime, endTime, chIds).intValue();
			//map.put(chId, data);
			//map.put("sum", dataSum);
			//String json = new Gson().toJson(map);
			out.print("{dataSum:'"+dataSum+"'}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public String curveAnalysis(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String[] chIds = ch_id_list.split(",");
			StringBuffer jsonBuffer = new StringBuffer("[");
			beginTime += " 00:00:00";
			endTime += " 23:59:59";
			int dataSum = gm_HistorydataService.findCountByCh_IdAndTime(beginTime, endTime, chIds).intValue();
			List<String> glList = new ArrayList<String>();
			if(StringUtils.hasText(glValue)){
				String[] glArr = glValue.split(",");
				glList = Arrays.asList(glArr);
			}
			if(dataSum < 200000){
				List<Gm_Channel> gm_Channels = gm_ChannelService.findByIds(chIds);
				for (Gm_Channel gm_Channel : gm_Channels) {
					StringBuffer data = new StringBuffer("[");
					List<Gm_Historydata> gm_HistorydatasList = gm_HistorydataService.findHistoryByChIdAndTimeStartingPosition(gm_Channel.getCh_id(), beginTime, endTime,0,glList);
					for (Gm_Historydata gm_Historydata : gm_HistorydatasList) {						
							long time = gm_Historydata.getHida_record_time().getTime()+8*60*60*1000L;
							Double value = null;
							if("origValue".equals(dataType)){
								value = gm_Historydata.getHida_origValue();
							}else{
								value = gm_Historydata.getHida_corrValue();
							}
							data.append("[").append(time).append(",").append(value).append("],");
					}
					if(data.length() > 1){
						String dataStr = data.toString();
						dataStr = dataStr.substring(0,dataStr.length()-1);
						jsonBuffer.append("{\"name\":\"").append(gm_Channel.getCh_name()).append("(").append(gm_Channel.getCh_no()).append(")\",\"data\":").append(dataStr).append("]},");
					}
				}
				if(jsonBuffer.length()>1)
					jsonBuffer.deleteCharAt(jsonBuffer.length()-1);
				jsonBuffer.append("]");
				out.print(jsonBuffer.toString());
			}else{
				out.print("{message:'数据量过大……'}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String curveAnalysisAdd(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			StringBuffer buffer = new StringBuffer("[");
			int num = 0;
			beginTime += " 00:00:00";
			endTime += " 23:59:59";
			if(dataBegin!=null && !"".equals(dataBegin))
				num = Integer.parseInt(dataBegin);
			Gm_Channel channel = gm_ChannelService.findChannelByChNo(chNo);
			List<String> glList = new ArrayList<String>();
			if(StringUtils.hasText(glValue)){
				String[] glArr = glValue.split(",");
				glList = Arrays.asList(glArr);
			}
			if(channel != null){
				try {
					List<Gm_Historydata> gm_Historydatas = gm_HistorydataService.findHistoryByChIdAndTimeStartingPosition(channel.getCh_id(),beginTime,endTime,num,glList);
					for (Gm_Historydata gm_Historydata : gm_Historydatas) {
						long time = gm_Historydata.getHida_record_time().getTime()+8*60*60*1000L;
						Double value = null;
						if("origValue".equals(dataType)){
							value = gm_Historydata.getHida_origValue();
						}else{
							value = gm_Historydata.getHida_corrValue();
						}
						buffer.append("[").append(time).append(",").append(value).append("],");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			String json = buffer.toString();
			if(json.length()>1)
				json = json.substring(0,json.length()-1);
			out.print(json+"]");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	/**
	 * 曲线测试方法，可删除
	 * @return
	 */
	public String findHistorydataByChId(){
		try {
//			long startTest=System.currentTimeMillis();
//			PrintWriter out = Struts2Utils.getResponse().getWriter();
//			String[] chIdArr = chIds.split(",");
//			String json = "[";
//			long beginTest=System.currentTimeMillis();
//			List<Gm_Historydata> gm_Historydatas = gm_HistorydataService.findHistorydataByChId(chIdArr,begin);
//			long overTest=System.currentTimeMillis();
//			List<Gm_Channel> channels = gm_ChannelService.findByIds(chIdArr);
//			for (Gm_Channel gm_Channel : channels) {
//				StringBuffer data = new StringBuffer("[");
//				for (Gm_Historydata gm_Historydata : gm_Historydatas) {
//					if(gm_Channel.getCh_id().equals(gm_Historydata.getCh_id().getCh_id())){
//						long time = gm_Historydata.getHida_record_time().getTime()+8*60*60*1000L;
//						data.append("[").append(time).append(",").append(gm_Historydata.getHida_corrValue()).append("],");
//					}
//				}
//				if(data.length() > 1){
//					String dataStr = data.toString();
//					dataStr = dataStr.substring(0,dataStr.length()-1);
//					json += "{name:'"+gm_Channel.getCh_name()+"("+gm_Channel.getCh_no()+")',data:"+dataStr+"]},";
//				}
//			}
//			if(json.length()>1)
//				json = json.substring(0,json.length()-1);
//			long endTest=System.currentTimeMillis();
//			long beginjson=System.currentTimeMillis();
//			out.print(json+"]");
//			long endjson=System.currentTimeMillis();
			
			
			long startTest=System.currentTimeMillis();
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String json = "[";
			Date date = new Date();
			for (int i =0 ;i < 2; i++) {
				StringBuffer data = new StringBuffer("[");
				long time = date.getTime()+8*60*60*1000L;
				for (int j = 0; j < 2000;j++) {
						data.append("[").append(time).append(",").append(new Random().nextDouble()*20).append("],");
						time += 5000;
				}
				if(data.length() > 1){
					String dataStr = data.toString();
					dataStr = dataStr.substring(0,dataStr.length()-1);
					json += "{name:'"+"x"+"("+"007-"+i+")',data:"+dataStr+"]},";
				}
			}
			if(json.length()>1)
				json = json.substring(0,json.length()-1);
			long endTest=System.currentTimeMillis();
			long beginjson=System.currentTimeMillis();
			out.print(json+"]");
			long endjson=System.currentTimeMillis();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 曲线测试方法，可删除
	 * @return
	 */
	public String findHistorydataByChId2(){
		try {
			long startTest=System.currentTimeMillis();
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String json = "[";
			for (int i =0 ;i < 1; i++) {
				StringBuffer data = new StringBuffer();
				long time = Long.parseLong(beginTime)+5000L;
				for (int j = 0; j < 10000;j++) {
					data.append("[").append(time).append(",").append(new Random().nextDouble()*20).append("],");
					time += 5000L;
				}
				if(data.length() > 1){
					String dataStr = data.toString();
					dataStr = dataStr.substring(0,dataStr.length()-1);
					json += dataStr+",";
				}
			}
			if(json.length()>1)
				json = json.substring(0,json.length()-1);
			long endTest=System.currentTimeMillis();
			long beginjson=System.currentTimeMillis();
			out.print(json+"]");
			long endjson=System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	//分割线--------------------------------------------------------------------------------------------------
	@Autowired Gm_HistorydataService gm_HistorydataService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_DeviceService gm_DeviceService;
	private String fileName;
	private IExcelService excelService = new ExcelServiceImpl();
	private InputStream excelStream;
	private Gm_Historydata gm_Historydata = new Gm_Historydata();
	private Page<Gm_Historydata> page;
	private String ch_id_list; //曲线分析 指定通道id集合,指定起止时间 return json for Highcharts series
	private String charts_type; //曲线分析 指定通道id集合,指定起止时间 return json for Highcharts series
	private String beginTime; //曲线分析 指定通道id集合,指定起止时间 return json for Highcharts series
	private String endTime; //曲线分析 指定通道id集合,指定起止时间 return json for Highcharts series 
	private String hours;
	private String minutes;
	private String seconds;
	private String scene_id;
	private String channels;
	private String netAddr;
	private String select;
	private String dateHTML;
	private String menu;
	private String hisPageNo;
	private String chartsType;
	private String devId;
	private String chIds;
	private String type;
	private String dataBegin;
	private String chNo;
	private String pageIndex;
	private String dataType;
	private String glValue;


	public String getGlValue() {
		return glValue;
	}

	public void setGlValue(String glValue) {
		this.glValue = glValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getChNo() {
		return chNo;
	}

	public void setChNo(String chNo) {
		this.chNo = chNo;
	}

	public String getDataBegin() {
		return dataBegin;
	}

	public void setDataBegin(String dataBegin) {
		this.dataBegin = dataBegin;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChIds() {
		return chIds;
	}

	public void setChIds(String chIds) {
		this.chIds = chIds;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getChartsType() {
		return chartsType;
	}

	public void setChartsType(String chartsType) {
		this.chartsType = chartsType;
	}

	public String getHisPageNo()
	{
		return hisPageNo;
	}

	public void setHisPageNo(String hisPageNo)
	{
		this.hisPageNo = hisPageNo;
	}

	public String getMenu()
	{
		return menu;
	}

	public void setMenu(String menu)
	{
		this.menu = menu;
	}

	public String getDateHTML()
	{
		return dateHTML;
	}

	public void setDateHTML(String dateHTML)
	{
		this.dateHTML = dateHTML;
	}

	public String getChannels()
	{
		return channels;
	}

	public void setChannels(String channels)
	{
		this.channels = channels;
	}

	public String getNetAddr()
	{
		return netAddr;
	}

	public void setNetAddr(String netAddr)
	{
		this.netAddr = netAddr;
	}

	public String getScene_id()
	{
		return scene_id;
	}

	public void setScene_id(String scene_id)
	{
		this.scene_id = scene_id;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public Gm_Historydata getGm_Historydata() {
		return gm_Historydata;
	}
	public void setGm_Historydata(Gm_Historydata gm_Historydata) {
		this.gm_Historydata = gm_Historydata;
	}
	public Page<Gm_Historydata> getPage() {
		return page;
	}
	public void setPage(Page<Gm_Historydata> page) {
		this.page = page;
	}
	public String getCharts_type() {
		return charts_type;
	}

	public void setCharts_type(String charts_type) {
		this.charts_type = charts_type;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCh_id_list() {
		return ch_id_list;
	}
	public void setCh_id_list(String ch_id_list) {
		this.ch_id_list = ch_id_list;
	}

	public IExcelService getExcelService()
	{
		return excelService;
	}

	public void setExcelService(IExcelService excelService)
	{
		this.excelService = excelService;
	}

	public InputStream getExcelStream()
	{
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream)
	{
		this.excelStream = excelStream;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public String getSelect()
	{
		return select;
	}

	public void setSelect(String select)
	{
		this.select = select;
	}
	
	
	
}
