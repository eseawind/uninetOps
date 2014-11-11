package org.unism.pro.service;


import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.dao.Gm_ChannelDao;
import org.unism.gm.dao.Gm_LatestdataDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.op.dao.Op_SceneDao;
import org.unism.op.domain.Op_Scene;
import org.unism.pro.dao.Pro_FisheriesDao;
import org.unism.pro.dao.Pro_forecastDao;
import org.unism.pro.domain.Pro_forecast;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;
import org.wangzz.core.web.struts2.Struts2Utils;



@Service
public class ForecastTask  extends BaseService<Pro_forecast, String> {
	
	@Autowired Pro_forecastDao pro_forecastDao;
	@Autowired Gm_LatestdataDao gm_LatestdataDao;
	@Autowired Op_SceneDao op_SceneDao; 
	public static int M=300;	//记录条数，训练集的大小
	public static int N=9;	//从数据库中读取的字段个数
	public static double Data[][]=new double[M][N];	//从数据库中读取的原始数据，并归一化
	public static double Max[]=new double[N];	//各个字段的最大值，归一化和反归一化时要用到
	public static double Min[]=new double[N];	//各个字段的最小值，归一化和反归一化时要用到
	public static double Train[][]=new double[M][N];	//得到归一化之后的数据
	
	public static double result;	//预测10分钟之后的数据
	
	public void execute() {
		DateTime nowDateTime = new DateTime();
		String times=nowDateTime.toString("yyyy-MM-dd HH:mm:ss");
		//getData(times);//取离当前时间时间最近的500条数据放到数组中
		getDataBySceneId(times);
		
	}

	@Override
	protected IBaseDao<Pro_forecast, String> getEntityDao() {
		return pro_forecastDao;
	}
	@Transactional
	public void getDataBySceneId(String endTime){//气象数据
	  try{
		Integer scene_gtype=1;
		String chtype1="DO10-O";//溶解氧的通道类型
		String chtype2="DO10-T";//水温的通道类型
		String chtype3="RAIN-R";//雨量的通道类型
		String chtype4="1200-S";//风速的通道类型
		String chtype5="1200-D";//风向的通道类型
		String chtype6="CMP6-P";//太阳辐射的通道类型
		String chtype7="KQSD1201-T";//空气温度的通道类型
		String chtype8="KQSD1201-H";//空气湿度的通道类型
		String chtype9="PA-P";//大气压力的通道类型
		String beginTime="";
		String ch_idStr="";
		int i=0;
		List<Object[]> historyData_List=null;
		
		String scene="402883e530b6bf830130b6c040fb008e";
	    ch_idStr="'f8435157310a1a8501310d5c7da20021'";//获得雨量的通道ID，组成字符串
	    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
	    for(Object[] objs1 : historyData_List) {
	    	if(objs1[1]!=null){
	    		Double dob=(Double)objs1[1];
	    		Data[i][0]=dob;
				i++;
	    	}
	    }
	    ch_idStr="'f8435157310a1a8501310d5c7dc10024'";//获得风速的通道ID，组成字符串
	    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
	    i=0;
	    for(Object[] objs2 : historyData_List) {
	    	if(objs2[1]!=null){
	    		Double dob=(Double)objs2[1];
	    		Data[i][1]=dob;
				i++;
	    	}
	    }
	    ch_idStr="'f8435157310a1a8501310d5c7dd10027'";//获得风向的通道ID，组成字符串
	    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
	    i=0;
	    for(Object[] objs3 : historyData_List) {
	    	if(objs3[1]!=null){
	    		Double dob=(Double)objs3[1];
	    		Data[i][2]=dob;
				i++;
	    	}
	    }
	    ch_idStr="'f8435157310a1a8501310d5c7de1002a'";//获得太阳辐射的通道ID，组成字符串
	    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
	    i=0;
	    for(Object[] objs4 : historyData_List) {
	    	if(objs4[1]!=null){
	    		Double dob=(Double)objs4[1];
	    		Data[i][3]=dob;
				i++;
	    	}
	    }
	    ch_idStr="'f8435157310a1a8501310d5c7df0002d'";//获得空气温度的通道ID，组成字符串
	    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
	    i=0;
	    for(Object[] objs5 : historyData_List) {
	    	if(objs5[1]!=null){
	    		Double dob=(Double)objs5[1];
	    		Data[i][4]=dob;
				i++;
	    	}
	    }
	    ch_idStr="'f8435157310a1a8501310d5c7e0f0030'";//获得空气温度的通道ID，组成字符串
	    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
	    i=0;
	    for(Object[] objs6 : historyData_List) {
	    	if(objs6[1]!=null){
	    		Double dob=(Double)objs6[1];
	    		Data[i][5]=dob;
				i++;
	    	}
	    }
	    ch_idStr="'f8435157310a1a8501310d5c7e1f0033'";//获得大气压力的通道ID，组成字符串
	    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
	    i=0;
	    for(Object[] objs7 : historyData_List) {
	    	if(objs7[1]!=null){
	    		Double dob=(Double)objs7[1];
	    		Data[i][6]=dob;
				i++;
	    	}
	    }
		String scene_id="402883e530b6bf830130b6c040fb008c";//史荣余
		//根据场景ID和溶解氧的应用类型查询出符合条件的通道ID
		List<Object[] > ch_id_list_DOO = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene_id,chtype1);
		//根据场景ID和水温的应用类型查询出符合条件的通道ID
		List<Object[] > ch_id_list_DOT = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene_id,chtype2);
		
		
		String ch_idStr1="";//获得一个场景下所有的溶解氧的通道ID，组成字符串
		String ch_idStr2="";//获得一个场景下所有的水温的通道ID，组成字符串
		for(Object[] ch_id1 : ch_id_list_DOO) {
			ch_idStr1+="'"+ch_id1[0]+"',";
		}
		for(Object[] ch_id2 : ch_id_list_DOT) {
			ch_idStr2+="'"+ch_id2[0]+"',";
		}
		if(ch_idStr1.length()>0){
			ch_idStr1 = ch_idStr1.substring(0,ch_idStr1.length()-1);
		}
		if(ch_idStr2.length()>0){
			ch_idStr2 = ch_idStr2.substring(0,ch_idStr2.length()-1);
		}
		if(ch_id_list_DOT.size()>1){//算出一个场景下水温的平均值
			historyData_List=pro_forecastDao.findAvgDataBy_Ch_idAnd_Time(ch_idStr2,beginTime,endTime,M);
		}else{//算出一个场景下水温的实际值
			historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr2,beginTime,endTime,M);
		}
		i=0;
	    for(Object[] obj1 : historyData_List) {
	    	if(obj1[1]!=null){
	    		Double dob=(Double)obj1[1];
	    		Data[i][7]=dob;
				i++;
	    	}
	    }
		if(ch_id_list_DOO.size()>1){//算出一个场景下溶解氧的平均值
			historyData_List=pro_forecastDao.findAvgDataBy_Ch_idAnd_Time(ch_idStr1,beginTime,endTime,M);
		}else{//算出一个场景下溶解氧的实际值
			historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr1,beginTime,endTime,M);
		}
		i=0;
	    for(Object[] obj2 : historyData_List) {
	    	if(obj2[1]!=null){
	    		Double dob=(Double)obj2[1];
	    		Data[i][8]=dob;
				i++;
	    	}
	    }
	    reflect();//归一化处理
		setPredict();//计算预测值
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//根据场景Id获得一个对象
		Op_Scene op_Scene =op_SceneDao.findById(scene_id);
		//根据场景ID获得此场景下最新数据表中溶解氧的平均值
		List<String[]> latestData_List=gm_LatestdataDao.findAvgDateBy_Scene_Id(scene_id,chtype1);
		Double latestData=0.0;
		Date hida_record_time=new Date();
		for(Object[] obj3 : latestData_List) {
	    	if(obj3[0]!=null){
	    		latestData=(Double)obj3[0];
	    		//hida_record_time = sdf.parse(String.valueOf(obj3[1]));
	    	}
	    }
		DateTime nowDateTime = new DateTime();		
		DateTime yestDayTime = nowDateTime.plusHours(1);
		String time2=yestDayTime.toString("yyyy-MM-dd HH:mm:ss");//当前时间加上一个小时
		time2=time2.substring(0, 13)+":00:00";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
		Date yestTime = format.parse(time2);  
		
		Pro_forecast pro_forecast=new Pro_forecast();				
		pro_forecast.setScene(op_Scene);
		pro_forecast.setHida_restoreValue(latestData);//修复之后的值				
		pro_forecast.setHida_forecastValue(result);//预测之后的值
		pro_forecast.setHida_record_time(hida_record_time);//采集时间
		pro_forecast.setForecast_time(yestTime);
		pro_forecast.setFc_forecastType(1);//预测是一个小时的
		pro_forecast.setFc_modelType(0);//模型分类 0：神经网络
		pro_forecastDao.save(pro_forecast);
		} catch (Exception e) {			
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("null");
			}catch(Exception exx){exx.printStackTrace();}
		}
	}
	
	
	@Transactional
	public void getData(String endTime){//气象数据
		try {
			Integer scene_gtype=1;
			String chtype1="DO10-O";//溶解氧的通道类型
			String chtype2="DO10-T";//水温的通道类型
			String chtype3="RAIN-R";//雨量的通道类型
			String chtype4="1200-S";//风速的通道类型
			String chtype5="1200-D";//风向的通道类型
			String chtype6="CMP6-P";//太阳辐射的通道类型
			String chtype7="KQSD1201-T";//空气温度的通道类型
			String chtype8="KQSD1201-H";//空气湿度的通道类型
			String chtype9="PA-P";//大气压力的通道类型
//			DateTime nowDateTime = new DateTime();
//			String endTime=nowDateTime.toString("yyyy-MM-dd HH:mm:ss");//结束时间			
//			DateTime yestDayTime = nowDateTime.plusDays(-1);
//			String beginTime=yestDayTime.toString("yyyy-MM-dd HH:mm:ss");//开始时间
			String beginTime="";
			String ch_idStr="";
			int i=0;
			List<Object[]> historyData_List=null;
			
			String scene="402883e530b6bf830130b6c040fb008e";
		    ch_idStr="'f8435157310a1a8501310d5c7da20021'";//获得雨量的通道ID，组成字符串
		    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
		    for(Object[] objs1 : historyData_List) {
		    	if(objs1[1]!=null){
		    		Double dob=(Double)objs1[1];
		    		Data[i][0]=dob;
					i++;
		    	}
		    }
		    ch_idStr="'f8435157310a1a8501310d5c7dc10024'";//获得风速的通道ID，组成字符串
		    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
		    i=0;
		    for(Object[] objs2 : historyData_List) {
		    	if(objs2[1]!=null){
		    		Double dob=(Double)objs2[1];
		    		Data[i][1]=dob;
					i++;
		    	}
		    }
		    ch_idStr="'f8435157310a1a8501310d5c7dd10027'";//获得风向的通道ID，组成字符串
		    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
		    i=0;
		    for(Object[] objs3 : historyData_List) {
		    	if(objs3[1]!=null){
		    		Double dob=(Double)objs3[1];
		    		Data[i][2]=dob;
					i++;
		    	}
		    }
		    ch_idStr="'f8435157310a1a8501310d5c7de1002a'";//获得太阳辐射的通道ID，组成字符串
		    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
		    i=0;
		    for(Object[] objs4 : historyData_List) {
		    	if(objs4[1]!=null){
		    		Double dob=(Double)objs4[1];
		    		Data[i][3]=dob;
					i++;
		    	}
		    }
		    ch_idStr="'f8435157310a1a8501310d5c7df0002d'";//获得空气温度的通道ID，组成字符串
		    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
		    i=0;
		    for(Object[] objs5 : historyData_List) {
		    	if(objs5[1]!=null){
		    		Double dob=(Double)objs5[1];
		    		Data[i][4]=dob;
					i++;
		    	}
		    }
		    ch_idStr="'f8435157310a1a8501310d5c7e0f0030'";//获得空气温度的通道ID，组成字符串
		    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
		    i=0;
		    for(Object[] objs6 : historyData_List) {
		    	if(objs6[1]!=null){
		    		Double dob=(Double)objs6[1];
		    		Data[i][5]=dob;
					i++;
		    	}
		    }
		    ch_idStr="'f8435157310a1a8501310d5c7e1f0033'";//获得大气压力的通道ID，组成字符串
		    historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr,beginTime,endTime,M);
		    i=0;
		    for(Object[] objs7 : historyData_List) {
		    	if(objs7[1]!=null){
		    		Double dob=(Double)objs7[1];
		    		Data[i][6]=dob;
					i++;
		    	}
		    }
		    
						
//			//根据场景ID和降雨量的应用类型查询出符合条件的通道ID
//			List<String[] > ch_id_list_RAIN = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene,chtype3);
//			//根据场景ID和风速的应用类型查询出符合条件的通道ID
//			List<String[] > ch_id_list_1200_S = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene,chtype4);
//			//根据场景ID和风向的应用类型查询出符合条件的通道ID
//			List<String[] > ch_id_list_1200_D = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene,chtype5);
//			//根据场景ID和太阳辐射的应用类型查询出符合条件的通道ID
//			List<String[] > ch_id_list_CMP6_P= pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene,chtype6);
//			//根据场景ID和空气温度的应用类型查询出符合条件的通道ID
//			List<String[] > ch_id_list_KQSD1201_T = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene,chtype7);
//			//根据场景ID和空气湿度的应用类型查询出符合条件的通道ID
//			List<String[] > ch_id_list_KQSD1201_H = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene,chtype8);
//			//根据场景ID和大气压力的应用类型查询出符合条件的通道ID
//			List<String[] > ch_id_list_PA_P = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene,chtype9);
			
			
			List<Object[] > scene_id_list = pro_forecastDao.findScene_idBy_Scene_gtype(scene_gtype);//根据场景类型细类查询出符合条件的场景ID
//			for(int co=0;co<scene_id_list.size();co++){
//			}
			for(Object[] objs8 : scene_id_list) {
				String scene_id=String.valueOf(objs8[0]);
				//根据场景ID和溶解氧的应用类型查询出符合条件的通道ID
				List<Object[] > ch_id_list_DOO = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene_id,chtype1);
				//根据场景ID和水温的应用类型查询出符合条件的通道ID
				List<Object[] > ch_id_list_DOT = pro_forecastDao.findCh_idBy_Scene_idAnd_Chtype_id(scene_id,chtype2);
				
				
				String ch_idStr1="";//获得一个场景下所有的溶解氧的通道ID，组成字符串
				String ch_idStr2="";//获得一个场景下所有的水温的通道ID，组成字符串
				for(Object[] ch_id1 : ch_id_list_DOO) {
					ch_idStr1+="'"+ch_id1[0]+"',";
				}
				for(Object[] ch_id2 : ch_id_list_DOT) {
					ch_idStr2+="'"+ch_id2[0]+"',";
				}
				if(ch_idStr1.length()>0){
					ch_idStr1 = ch_idStr1.substring(0,ch_idStr1.length()-1);
				}
				if(ch_idStr2.length()>0){
					ch_idStr2 = ch_idStr2.substring(0,ch_idStr2.length()-1);
				}
				if(ch_id_list_DOT.size()>1){//算出一个场景下水温的平均值
					historyData_List=pro_forecastDao.findAvgDataBy_Ch_idAnd_Time(ch_idStr2,beginTime,endTime,M);
				}else{//算出一个场景下水温的实际值
					historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr2,beginTime,endTime,M);
				}
				i=0;
			    for(Object[] obj1 : historyData_List) {
			    	if(obj1[1]!=null){
			    		Double dob=(Double)obj1[1];
			    		Data[i][7]=dob;
						i++;
			    	}
			    }
				if(ch_id_list_DOO.size()>1){//算出一个场景下溶解氧的平均值
					historyData_List=pro_forecastDao.findAvgDataBy_Ch_idAnd_Time(ch_idStr1,beginTime,endTime,M);
				}else{//算出一个场景下溶解氧的实际值
					historyData_List=pro_forecastDao.findDataBy_Ch_idAnd_Time(ch_idStr1,beginTime,endTime,M);
				}
				i=0;
			    for(Object[] obj2 : historyData_List) {
			    	if(obj2[1]!=null){
			    		Double dob=(Double)obj2[1];
			    		Data[i][8]=dob;
						i++;
			    	}
			    }
			    reflect();//归一化处理
				setPredict();//计算预测值
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//根据通道Id获得一个对象
//				Gm_Channel channel=gm_ChannelDao.findById(ch_id);
				Op_Scene op_Scene =op_SceneDao.findById(scene_id);
				//根据场景ID获得此场景下最新数据表中溶解氧的平均值
				List<String[]> latestData_List=gm_LatestdataDao.findAvgDateBy_Scene_Id(scene_id,chtype1);
				Double latestData=0.0;
				Date hida_record_time=new Date();
				for(Object[] obj3 : latestData_List) {
			    	if(obj3[1]!=null){
			    		latestData=(Double)obj3[0];
			    		hida_record_time = sdf.parse(String.valueOf(obj3[1]));
			    	}
			    }
				DateTime nowDateTime = new DateTime();		
				DateTime yestDayTime = nowDateTime.plusHours(1);
				String time2=yestDayTime.toString("yyyy-MM-dd HH:mm:ss");//当前时间加上一个小时
				time2=time2.substring(0, 13)+":00:00";
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				Date yestTime = format.parse(time2);  
				
				Pro_forecast pro_forecast=new Pro_forecast();				
				pro_forecast.setScene(op_Scene);
				pro_forecast.setHida_restoreValue(latestData);//修复之后的值				
				pro_forecast.setHida_forecastValue(result);//预测之后的值
				pro_forecast.setHida_record_time(hida_record_time);//采集时间
				pro_forecast.setForecast_time(yestTime);
				pro_forecast.setFc_forecastType(1);//预测是一个小时的
				pro_forecast.setFc_modelType(0);//模型分类 0：神经网络
				pro_forecastDao.save(pro_forecast);
				
				//以下是到最新数据表中取得溶解氧、水温、气象站的最新数据
//				List<Object[]> latestData_List1()
			}//for结束符
		} catch (Exception e) {			
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("null");
			}catch(Exception exx){exx.printStackTrace();}
		}
    }
	
	public static void reflect()//归一化处理
	{
		//寻找每个字段的最大值最小值
		for(int i=0;i<N;i++)
		{
			Max[i]=-32767;
			Min[i]=32767;
			for(int j=0;j<M;j++)
			{
				if(Data[j][i]>Max[i])
				{
					Max[i]=Data[j][i];
				}
				if(Data[j][i]<Min[i])
				{
					Min[i]=Data[j][i];
				}
			}
		}
		//归一化处理
		for(int i=0;i<M;i++)
		{
			for(int j=0;j<N;j++)
			{
				Data[i][j]=(Data[i][j]-Min[j])/(Max[j]-Min[j]);
			}
		}
	}
	
	public static void setPredict()//计算预测值
	{
		Bp p=new Bp();
		p.init();//初始化权值和阈值
		for(int i=0;i<M;i++)//学习144组数据
		{
			p.train(Data[i][2],Data[i][3],Data[i][4],Data[i][5],Data[i][6],Data[i][8]);
			//训练参数：风力2、太阳辐射3、空气温度4、空气湿度5、大气压力6
			//期望输出：第8列溶解氧
		}
		result=p.run(Data[M-1][2], Data[M-1][3], Data[M-1][4], Data[M-1][5], Data[M-1][6]);
		//测试的这个记录也是经过训练的，而且是最后一个。i<M嘛~
		result=result*(Max[8]-Min[8])+Min[8];
	}
}
