package org.unism.pro.service;



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.unism.gm.dao.Gm_ChannelDao;
import org.unism.gm.dao.Gm_DevstsDao;
import org.unism.gm.dao.Gm_HistorydataDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_Devsts;
import org.unism.gm.domain.Gm_Historydata;


import org.unism.op.dao.Op_SceneDao;
import org.unism.op.dao.Op_SceneFaultStateDao;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_SceneFaultState;
import org.unism.util.Dest_workSts;
import org.unism.util.StaticDataManage;
import org.wangzz.core.orm.IBaseDao;

import org.wangzz.core.service.BaseService;




@Service
public class FaultTask extends BaseService<Op_SceneFaultState, String>{
	
	@Autowired private Op_SceneDao op_SceneDao;
	@Autowired private Gm_DevstsDao gm_DevstsDao;
	@Autowired private Gm_HistorydataDao gm_HistorydataDao;
	@Autowired private Op_SceneFaultStateDao op_SceneFaultStateDao;
	@Autowired private Gm_ChannelDao gm_ChannelDao;
	private String DO="DO10-O";//溶解氧
	private String DT="DO10-T";//水温
	private String YD="YD-1";//盐度
	private String PH="WaterPH";//PH
	private double ch_max=0.0d;
	private double ch_min=0.0d;
	@Override
	protected IBaseDao<Op_SceneFaultState, String> getEntityDao() {
		return op_SceneFaultStateDao;
	}
	public void execute() throws IOException, ParseException {
		try{
			DateTime nowDateTime = new DateTime();		
			DateTime yestDayTime = nowDateTime.plusDays(-1);//当前日期减去一天
			String time=yestDayTime.toString("yyyy-MM-dd");
			String beginTime=time+" 00:00:00";
			String endTime=time+" 23:59:59";
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
			long limitTime=2*60*60*1000;		
			Date nowDate=new Date();
			Long nowTime=nowDate.getTime();//当前时间
			List<Op_Scene> sceneList=op_SceneDao.findAll();//查询所有的场景
			for(Op_Scene scene:sceneList){
				String scene_id=scene.getScene_id();
				List<Gm_Channel> channelList=gm_ChannelDao.findByScene_id(scene_id);
				if(!channelList.isEmpty()){
					List<Gm_Channel> ch_idList=new ArrayList<Gm_Channel>();
					Set<String> devAddrSet=new HashSet<String>();
					for(Gm_Channel channel:channelList){
						String chtype_no=channel.getChtype_id().getChtype_no();//通道应用类型编号
						String ch_no=channel.getCh_no();//通道编号
						if(ch_no!=null) {
							String[] devAddr=ch_no.split("-");	
							devAddrSet.add(devAddr[0]);
						}
						//溶解氧、水温、盐度、PH
						if(chtype_no.equals(DO)||chtype_no.equals(DT)||chtype_no.equals(YD)||chtype_no.equals(PH)){
							ch_idList.add(channel);
						}
					}//channelList结束符
					//循环存放的设备地址
					String sfs_devState="";//设备状态
					String sfs_inf="";//详细信息
					for(String devAddr:devAddrSet){
						Gm_Devsts devsts=gm_DevstsDao.findByDevAddr(devAddr);
						if(devsts!=null){
							Integer dest_linkSts=devsts.getDest_linkSts();//连接方式 1: 长连接；2: 短连接；3：无连接
							Integer dest_workSts=devsts.getDest_workSts();//运行状态 0.离线；1.在线；2.网关小无线模块故障；3.小无线能量故障；4.小无线通讯故障；5.传感器故障；6.传感器超量程；7.传感器超变化率
							Date dest_lastCommTime=devsts.getDest_lastCommTime();
							if(dest_linkSts!=null && dest_workSts!=null){
								switch (dest_linkSts) {
								case 1:
									if(dest_workSts==0){
										sfs_devState+="设备"+devAddr+":离线<br>";
										sfs_inf+="设备"+devAddr+",最后通信时间："+dest_lastCommTime+"<br>";
									}else {
										sfs_devState+="设备"+devAddr+":在线<br>";
									}
									break;
								case 2:										
									if(dest_lastCommTime!=null){
										Long lastTime=devsts.getDest_lastCommTime().getTime();//最近通信时间
										if(nowTime-lastTime>limitTime){
											sfs_devState+="设备"+devAddr+":离线"+"<br>";
											sfs_inf+="设备"+devAddr+",最后通信时间："+dest_lastCommTime+"<br>";
										}else {
											sfs_devState+="设备"+devAddr+":在线<br>";
										}
									}
									break;
								}
								
							}
						}
					}//for结束符			
					  
					String chValUp="";
					String chValDown="";				
					//循环需要验证数据的通道
					for(Gm_Channel channel:ch_idList){
						String chtype_no=channel.getChtype_id().getChtype_no();//通道应用类型编号
						String ch_corrUnit=channel.getCh_corrUnit();//校正后单位
						if(chtype_no.equals(DO)){
							ch_min=0.0d;
							ch_max=20.0d;
						}
						if(chtype_no.equals(DT)){
							ch_min=0.0d;
							ch_max=40.0d;
						}
						if(chtype_no.equals(YD)){
							ch_min=0.0d;
							ch_max=35.0d;
						}
						if(chtype_no.equals(PH)){
							ch_min=0.0d;
							ch_max=14.0d;
						}
						List<Object[]> historyDataList=gm_HistorydataDao.findTimeAndDataByChidAndTime(channel.getCh_id(), format.parse(beginTime), format.parse(endTime));
						int sumup=0;//比实际值高的统计个数
						int sumdown=0;//比实际值低的统计个数
						for(Object[] historyData:historyDataList){
							if(historyData[1]!=null&&historyData[0]!=null){
								double hidaValue=Double.valueOf(historyData[1].toString());
								if(hidaValue<ch_min){
									sumdown++;
									if(sumdown<=5){
										chValDown+=channel.getCh_no()+" "+channel.getCh_name()+","+hidaValue+ch_corrUnit+", "+historyData[0]+"<br>";
									}else{
										chValDown=channel.getCh_no()+" "+channel.getCh_name()+",数据持续偏低"+"<br>";
									}
								}
								if(hidaValue>ch_max){
									sumup++;
									if(sumup<=5){
										chValUp+=channel.getCh_no()+" "+channel.getCh_name()+","+hidaValue+ch_corrUnit+", "+historyData[0]+"<br>";
									}else{
										chValUp=channel.getCh_no()+" "+channel.getCh_name()+",数据持续偏高"+"<br>";
									}
								}
							}
						}//for结束符
					}//for结束符
					sfs_inf+=chValDown+chValUp;	
					if(!sfs_inf.equals("")){
						Op_SceneFaultState op_SceneFaultState=new Op_SceneFaultState();
						op_SceneFaultState.setSfs_date(yestDayTime.toDate());
						op_SceneFaultState.setSfs_devState(sfs_devState);
						op_SceneFaultState.setSfs_inf(sfs_inf);
						op_SceneFaultState.setSfs_sceneId(scene_id);
						op_SceneFaultState.setSfs_sceneName(scene.getScene_name());
						op_SceneFaultStateDao.save(op_SceneFaultState);
					}
					
				}//if结束符
			}//sceneList结束符		
		}catch (Exception e) {
			e.getMessage();
		}
	}
	public void execute2() throws IOException, ParseException {
		System.out.println("故障症状捕获程序开始运行。。。。。");
		/**
		 * 查询所有的场景
		 */
		List<Op_Scene>scenes=op_SceneDao.findAll();
		long limit=2*60*60*1000;
		List<Map<String,List<Op_SceneFaultState>>> fault_detected=new ArrayList<Map<String,List<Op_SceneFaultState>>>();

		
		String DO_type_no="DO10-O";//溶氧通道的类型编号
		String PH_type_no="PH-Z";  //PH通道的类型编号
		String WT_type_no="WaterTemp";//水温通道的类型编号
		
		long now=System.currentTimeMillis();
		Date twentyFourHoursAgo=new Date(now-24l*60l*60l*1000l);
		Date justNow=new Date(now);
		java.sql.Date when=new java.sql.Date(now);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/**
		 * 循环场景，根据场景Id查询出所有的通道。
		 */
		for(Op_Scene singleScene:scenes){
			String scene_id=singleScene.getScene_id();
			String faultInfo="";
			Map<String,List<Op_SceneFaultState>> scene_fault=new HashMap<String,List<Op_SceneFaultState>>();
			List<Op_SceneFaultState> device_fault=new ArrayList<Op_SceneFaultState>();
			/**
			 * 对每个场景而言，下属的设备的OpSceneFaultState对象共用属性为scene_id、scene_name,和date
			 * 每次循环，都在这里将这些公共属性初始化，然后在循环需要判断的通道的时候，
			 * 设置sfs_inf、dev_state这些私有属性
			 */
			Op_SceneFaultState sceneFault=new Op_SceneFaultState();
			sceneFault.setSfs_sceneId(scene_id);
			sceneFault.setSfs_sceneName(singleScene.getScene_name());
			sceneFault.setSfs_date(when);
			
			Map<String, String> gprs_state=new HashMap<String, String>();
//返回什么实体类，就应该在对应实体类的导中写方法，因为这是要进行表的映射的-----Hibernate
			List<Gm_Channel> allChannel=gm_ChannelDao.findByScene_id(scene_id);
			List<Gm_Channel> needToCheck=new ArrayList<Gm_Channel>();
			/**
			 * 循环通道，截取通道编号放到set中，同时判断此通道是不是需要判断超限，
			 * 如果需要判断超限，将此通道放到一个List中。
			 */
			Set<String> GprsSet=new HashSet<String>();
			
			for(Gm_Channel channel:allChannel){
				String channelTypeNo=channel.getChtype_id().getChtype_no();
				String channel_no=channel.getCh_no();

				if(channel_no!=null) {
					String[] scene_net_no=channel_no.split("-");	
					GprsSet.add(scene_net_no[0]);
				}

				if(channelTypeNo.equals(DO_type_no) | channelTypeNo.equals(PH_type_no) | channelTypeNo.equals(WT_type_no)){
					needToCheck.add(channel);
				}
			}
			/**
			 *然后循环set,根据set中保存的设备地址，到智能设备状态表中查询出此设备，
			 * 设备状态的判断规则，如果连接方式为长连接，则直接获取设备运行状况，如果连接方式为短连接，
			 * 则根据最新通信时间与当前时间对比，如果大于两小时，则认为离线否则认为在线。
			 */
			for(String gprsAddr:GprsSet){
				System.out.println(gprsAddr);
				String dev_sts="";			
				//9?
				Gm_Devsts devsts=gm_DevstsDao.findByDevAddr(gprsAddr);
				int linkType=0;
				if(devsts!=null)linkType=devsts.getDest_linkSts();
					if(linkType==1){
					int state=devsts.getDest_workSts();
					switch (state) {
					case 0:
						dev_sts="长连接,离线";
						break;
					case 1:
						dev_sts="长连接,在线";
						break;
					case 2:
						dev_sts="网关小无线模块故障";
						break;
					case 3:
						dev_sts="小无线能量故障";
						break;
					case 4:
						dev_sts="小无线通讯故障";
						break;
					case 5:
						dev_sts="传感器故障";
						break;
					case 6:
						dev_sts="传感器超量程";
						break;

					case 7:
						dev_sts="传感器超变化率";
						break;
					}
					
				}
				else if(linkType==2){

					Long lastCom=devsts.getDest_lastCommTime().getTime();
					if(now-lastCom>limit) dev_sts="短连接,离线";
					else dev_sts="短连接，在线";
					
				}
				/**
				 * 将设备状态拼成字符串
				 */
				dev_sts=gprsAddr+":"+dev_sts;
				gprs_state.put(gprsAddr, dev_sts);
			}
			/**
			 * 循环List，根据每个通道Id取前一天的历史数据。
			 * 对op_SceneFaultState的devState、Sfs_inf；两个私有属性设置
			 */
			for(Gm_Channel child_channel:needToCheck){

				List<Object[]> dataInOneDay=gm_HistorydataDao.findTimeAndDataByChidAndTime(child_channel.getCh_id(),twentyFourHoursAgo, justNow);

				String channelNo=child_channel.getCh_no();
				if(channelNo!=null)sceneFault.setSfs_devState(gprs_state.get(channelNo.split("-")[0]));
				/**
				 * 类型比较
				 */
				if(child_channel.getChtype_id().getChtype_no().equals(DO_type_no)){
					int counter=0;
					double counter2=0;
					String valueFaultStartTime="";
					String valueFaultEndTime="";
					/**
					 * 循环查询出的历史数据，对每条数据进行分析，判断是否超限：
					 * 如果此通道的当天数据大于5条数据超限了，则认为此通道持续超限，
					 * 如果小于等于5条一一列出就可以了，
					 */
	
					for(Object[] data:dataInOneDay){
						
						double hidaValue=Double.valueOf(data[1].toString());
	
						if(hidaValue<0.0d || hidaValue>20.0d){
							counter++;
							counter2=hidaValue;
							if(counter==0 || valueFaultStartTime.equals(""))valueFaultStartTime=data[0].toString();
						}
						else if(counter>=5){
							counter=0;
							valueFaultEndTime=data[0].toString();
							/**
							 * 将通道的故障信息拼成字符串
							 */
							faultInfo=channelNo+" 溶解氧采集值，由"+valueFaultStartTime+"至"+valueFaultEndTime+"持续超限";
							valueFaultStartTime="";
							valueFaultEndTime="";
							sceneFault.setSfs_inf(faultInfo);
						}
						else if(hidaValue>0.0d & hidaValue<20.0d){
							counter=0;
							valueFaultStartTime="";
							/**
							 * 将通道的故障信息拼成字符串
							 */
							faultInfo=channelNo+"溶解氧采集值:"+counter2+"mg/L,"+data[0];
							sceneFault.setSfs_inf(faultInfo);

						}
					}
				}
				/**
				 * 类型比较
				 */
				else if(child_channel.getChtype_id().getChtype_no().equals(PH_type_no)){
					int counter=0;
					double counter2=0;
					String valueFaultStartTime="";
					String valueFaultEndTime="";
					/**
					 * 循环查询出的历史数据，对每条数据进行分析，判断是否超限：
					 * 如果此通道的当天数据大于5条数据超限了，则认为此通道持续超限，
					 * 如果小于等于5条一一列出就可以了，
					 */

					for(Object[] data:dataInOneDay){
						
						double hidaValue=Double.valueOf(data[1].toString());
	
						if(hidaValue<5.5d || hidaValue>7.5d){
							counter++;
							counter2=hidaValue;
							if(counter==0 || valueFaultStartTime.equals(""))valueFaultStartTime=data[0].toString();
						}
						else if(counter>=5){
							counter=0;
							valueFaultEndTime=data[0].toString();
							/**
							 * 将通道的故障信息拼成字符串
							 */
							faultInfo=channelNo+"PH采集值，由"+valueFaultStartTime+"至"+valueFaultEndTime+"持续超限";
							valueFaultStartTime="";
							valueFaultEndTime="";
							sceneFault.setSfs_inf(faultInfo);
						}
						else if(hidaValue>5.5d & hidaValue<7.5d){
							counter=0;
							valueFaultStartTime="";
							/**
							 * 将通道的故障信息拼成字符串
							 */
							faultInfo=channelNo+"PH采集值:"+counter2+","+data[0];
							sceneFault.setSfs_inf(faultInfo);
						}
						
					}
				}
				else if(child_channel.getChtype_id().getChtype_no().equals(WT_type_no)){
					int counter=0;
					double counter2=0;
					String valueFaultStartTime="";
					String valueFaultEndTime="";
					/**
					 * 循环查询出的历史数据，对每条数据进行分析，判断是否超限：
					 * 如果此通道的当天数据大于5条数据超限了，则认为此通道持续超限，
					 * 如果小于等于5条一一列出就可以了，
					 */
					for(Object[] data:dataInOneDay){
						
						double hidaValue=Double.valueOf(data[1].toString());
	
						if(hidaValue<-1.0d || hidaValue>20d){
							counter++;
							counter2=hidaValue;
							if(counter==0 || valueFaultStartTime.equals(""))valueFaultStartTime=data[0].toString();
						}
						else if(counter>=5){
							counter=0;
							valueFaultEndTime=data[0].toString();
							/**
							 * 将通道的故障信息拼成字符串
							 */
							faultInfo=channelNo+"水温采集值，由"+valueFaultStartTime+"至"+valueFaultEndTime+"持续超限";
							valueFaultStartTime="";
							valueFaultEndTime="";
							sceneFault.setSfs_inf(faultInfo);
						}
						else if(hidaValue>-1.0d & hidaValue<20d){
							counter=0;
							valueFaultStartTime="";
							/**
							 * 将通道的故障信息拼成字符串
							 */
							faultInfo=channelNo+"水温采集值:"+counter2+"℃,"+data[0];
							sceneFault.setSfs_inf(faultInfo);
						}
						
					}
				}
				op_SceneFaultStateDao.save(sceneFault);
				device_fault.add(sceneFault);
			}
			scene_fault.put(singleScene.getScene_name(), device_fault);
			
			if(device_fault.size()>0)  fault_detected.add(scene_fault);
		}
		System.out.println("捕获到的异常数量:"+fault_detected.size());
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		String newFilePathString="D:/Auto_Generate_Fault_msg/"+dateFormat.format(new Date())+".txt";
		genericNewFile=new GenericNewFile(newFilePathString,true);
		
		for(Map<String, List<Op_SceneFaultState>> sceneFault:fault_detected){
				String sceneName="";
				System.out.println("-----------------------------------------------------------------------------------");
				genericNewFile.write("----------------------------------------------------------------------------------");
				for(String str:sceneFault.keySet()){
					sceneName=str;
					break;
				}
				genericNewFile.write(sceneName);
				for(List<Op_SceneFaultState> list:sceneFault.values()){
					for(Op_SceneFaultState singleFaultState:list){
						genericNewFile.write(singleFaultState.getSfs_devState());
						genericNewFile.write(format.format(singleFaultState.getSfs_date()));
						genericNewFile.write(singleFaultState.getSfs_inf());
					}
				}
		}
	}

	private GenericNewFile genericNewFile;
	/**
	 *	用于输出新的故障捕获文本到 D:/Auto_Generate_Fault_msg 路径下
	 * 	@author 杨昊
	 *
	 */
	public class GenericNewFile{
		/**
		 * 路径
		 */
		private String filePath;
		/**
		 * 是否可追加
		 */
		private boolean appendable;
		private FileWriter writer;
		public GenericNewFile(String path,boolean appendable) throws IOException{
			this.appendable=appendable;
			this.filePath=path;
			
		}
		public void write(String arg) throws IOException{
			writer=new FileWriter(filePath,appendable);
			PrintWriter printer=new PrintWriter(writer);
			printer.println(arg);
			printer.close();
		}
		
	}


}
