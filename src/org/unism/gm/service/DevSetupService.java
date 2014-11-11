package org.unism.gm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.unism.gm.dao.Gm_LatestdataDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_Devsts;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.unism.op.service.Op_ChannelTypeService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.util.DevSetup;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Search;

@Service
@SuppressWarnings("unchecked")
public class DevSetupService {
	
	@Autowired Gm_DeviceService deviceService;
	@Autowired Gm_DevNetService devNetService;
	@Autowired Gm_ChannelService channelService;
	@Autowired Gm_DevChannelService devChannelService;
	@Autowired Gm_DevCtrlService devCtrlService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Gm_DevstsService gm_DevstsService;
	@Autowired Gm_DevChannelService gm_DevChannelService;
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	@Autowired Gm_LatestdataDao gm_LatestdataDao;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	
	/**
	 * 根据设备编号,场景名称查询GPRS设备
	 * @param page 分页类
	 * @param code 设备编号
	 * @param sceneName 场景名称
	 * @param sceneIds 
	 * @return 设备分页类
	 */
	public Page<Gm_Device> findGprsDevByCodeScene(Page<Gm_Device> page, String code, String sceneName, Op_UserInfo user, List<String> sceneIds) {
		Search se = new Search();
		se.addFilterEqual("user_id", user);
		if(!sceneIds.isEmpty()){
			se.addFilterIn("scene_id.scene_id", sceneIds);
		}
		List<Op_Scene> scenes = new ArrayList<Op_Scene>();
		Op_Scene op_Scene = new Op_Scene();
		op_Scene.setScene_id("-1");
		scenes.add(op_Scene);
		List<Op_UserInfo_Scene> list = op_UserInfo_SceneService.search(se);
		for (Op_UserInfo_Scene op_UserInfo_Scene : list) {
			scenes.add(op_UserInfo_Scene.getScene_id());
		}
		Search search = new Search();
		if(StringUtils.hasText(code)) search.addFilterLike("dev_no", code);
		if(StringUtils.hasText(sceneName)) search.addFilterLike("scene_id.scene_name", sceneName);
		search.addFilterEqual("dev_btype", 0);
		search.addFilterIn("scene_id", scenes);
		return deviceService.search(page, search);
	}

	/**
	 * 根据设备id查询设备信息
	 * @param devId 设备id
	 * @return 设备信息
	 */
	public Gm_Device findDeviceById(String devId) {
		return deviceService.findById(devId);
	}
	
	/**
	 * 根据设备id查询网络信息
	 * @param devId 设备id
	 * @return 网络信息
	 */
	public Gm_DevNet findDevNetByDevId(String devId) {
		Search search = new Search();
		search.addFilterEqual("dev_id.dev_id", devId);
		search.addFilterEqual("net_type", 0);
		List list=devNetService.search(search);
		if(list.size()>0){
			return (Gm_DevNet)devNetService.search(search).get(0);
		}else{
			return null;
		}
	}

	/**
	 * 查询此设备下的上报通道配置
	 * @param devId 设备id
	 * @return 上报通道配置
	 */
	public List<Gm_DevChannel> findOrderDevChlByDevId(String devId) {
		Search search = new Search();
		search.addFilterEqual("dev_id.dev_id", devId);
		search.addSortAsc("dch_order");
		return devChannelService.search(search);
	}
	

	/**
	 * 根据设备id组查询这组设备下的所有控制设备
	 * @param devIdList 设备id组
	 * @return 控制设备
	 */
	public List<Gm_DevCtrl> findDevCtrlByDevIds(List<String> devIdList) {
		Search search = new Search();
		search.addFilterIn("dev_id.dev_id", devIdList);
		return devCtrlService.search(search);
	}
	
	
	/**
	 * 获得子节点的id,拼成json
	 * @param devNet 父节点
	 * @param buffer 字符串
	 * @param devIdList 保存此父节点下的所有子节点
	 * @return 递归使用
	 */
	public StringBuffer getChildJson(Gm_DevNet devNet, StringBuffer buffer, List<String> devIdList) {
		String pid = (devNet.getNet_pid() == null) ? "0" : devNet.getNet_pid();
		String nodeName = devNet.getNet_addr() + "(场景:" + devNet.getDev_id().getScene_id().getScene_name() + ",设备编号:" + devNet.getDev_id().getDev_no() + ")";
		buffer.append("{id:\""+devNet.getNet_id()+"\", pId:\""+pid+"\", name:\""+nodeName+"\"},");
		devIdList.add(devNet.getDev_id().getDev_id());
		List<Gm_DevNet> netChildList = devNetService.findAllEq("net_pid", devNet.getNet_id());
		for(Gm_DevNet net : netChildList) {
			getChildJson(net, buffer, devIdList);
		}
		return buffer;
	}

	/**
	 * 添加
	 * @param gm_Device
	 * @param gm_DevNet
	 * @param devSetup_list
	 * @param _scene_id
	 * @param _ch_no
	 * @param _ch_name
	 * @param _dev_collectId
	 * @param _ch_chlNo
	 * @param _chtype_id
	 * @param _ch_offerSer
	 * @param _order
	 * @param _procesStyle
	 * @param _memoryPeriod
	 * @throws Exception
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void save(Gm_Device gm_Device, Gm_DevNet gm_DevNet, Map<String,DevSetup> devSetup_map, String[] _scene_id, String[] _ch_no, String[] _ch_name, String[] _dev_collectId, String[] _ch_chlNo, String[] _chtype_id, String[] _ch_offerSer, String[] _order, String[] _procesStyle,String[] _memoryPeriod, Map<String, Op_Scene> op_Scene_map, Map<String, Op_ChannelType> op_ChannelType_map) throws Exception{
//		//save
//		gm_Device.setCust_saleId(null);
//		gm_Device.setCust_serviceId(null);
//		gm_DeviceService.save(gm_Device);
//		gm_DevNet.setDev_id(gm_Device);
//		gm_DevNet.setNet_no(gm_Device.getDev_no());		
//		//save
//		devNetService.save(gm_DevNet);
//		//save
//		Gm_Devsts gm_Devsts = this.gm_DevstsService.findByDev_addr(gm_DevNet.getNet_addr());
//		if(gm_Devsts==null)gm_Devsts = new Gm_Devsts();
//		gm_Devsts.setDev_id(gm_DevNet.getDev_id());
//		gm_Devsts.setDev_addr(gm_DevNet.getNet_addr());
//		gm_Devsts.setDest_regSts(0);
//		//saveOrUpdate
//		this.gm_DevstsService.saveOrUpdate(gm_Devsts);		
//		for(DevSetup devSetup : devSetup_map.values()){
//			Gm_Device gm_Device_wsn = new Gm_Device();
//			Gm_DevNet gm_DevNet_wsn = new Gm_DevNet();
//			gm_Device_wsn.setCust_saleId(null);
//			gm_Device_wsn.setCust_serviceId(null);			
//			gm_Device_wsn.setDev_btype(1);		
//			//gm_Device_wsn.setDev_id(devSetup.getDev_id());
//			gm_Device_wsn.setDev_model(devSetup.getDev_model());
//			gm_Device_wsn.setDev_name(devSetup.getDev_name());
//			gm_Device_wsn.setDev_no(devSetup.getDev_no());
//			gm_Device_wsn.setDev_powerType(devSetup.getDev_powerType());
//			gm_Device_wsn.setDev_serial(devSetup.getDev_serial());
//			gm_Device_wsn.setDev_state(1);
//			Op_Scene op_Scene_wsn = op_SceneService.findById(devSetup.getTianjiawangluojiedian_scene_id());
//			gm_Device_wsn.setScene_id(op_Scene_wsn);			
//			//save
//			gm_DeviceService.save(gm_Device_wsn);
//			devSetup.setDev_id(gm_Device_wsn.getDev_id());
//			
//			gm_DevNet_wsn.setDev_id(gm_Device_wsn);
//			gm_DevNet_wsn.setNet_addr(devSetup.getNet_addr());
//			gm_DevNet_wsn.setNet_depth(devSetup.getNet_depth());
//			//gm_DevNet_wsn.setNet_id(devSetup.getId());
//			gm_DevNet_wsn.setNet_linkSts(Integer.parseInt(devSetup.getNet_linkSts()));
//			gm_DevNet_wsn.setNet_no(devSetup.getDev_no());
//			if(devSetup.getPid().equals("root"))	{			
//				gm_DevNet_wsn.setNet_pid(gm_DevNet.getNet_id());
//			}else{	
//				gm_DevNet_wsn.setNet_pid(devSetup_map.get(devSetup.getPid()).getId());
//			}
//			gm_DevNet_wsn.setNet_role(devSetup.getNet_role());
//			gm_DevNet_wsn.setNet_state(1);
//			gm_DevNet_wsn.setNet_type(0);
//			//save
//			devNetService.save(gm_DevNet_wsn);
//			devSetup.setId(gm_DevNet_wsn.getNet_id());			
//		}
//		if(_ch_no!=null){
//			for(int i=0;i<_ch_no.length;i++){
//				Gm_Channel gm_Channel = new Gm_Channel();
//				Gm_DevChannel gm_DevChannel = new Gm_DevChannel();
//				String scene_id = _scene_id[i];
//				String ch_no = _ch_no[i];
//				String ch_name = _ch_name[i];
//				String dev_collectId = _dev_collectId[i];
//				String ch_chlNo = _ch_chlNo[i];
//				String chtype_id = _chtype_id[i];
//				String ch_offerSer = _ch_offerSer[i];
//				String order = _order[i];
//				String procesStyle = _procesStyle[i];
//				String memoryPeriod = _memoryPeriod[i];
//				gm_Channel.setCh_chlNo(Integer.parseInt(ch_chlNo));
//				gm_Channel.setCh_corrFormula("");
//				gm_Channel.setCh_depth("");
//				gm_Channel.setCh_memoryPeriod(Integer.parseInt(memoryPeriod));
//				gm_Channel.setCh_name(ch_name);
//				gm_Channel.setCh_no(ch_no);
//				gm_Channel.setCh_offerSer(Integer.parseInt(ch_offerSer));
//				gm_Channel.setCh_procesStyle(Integer.parseInt(procesStyle));
//				gm_Channel.setCh_seat_no("");
//				gm_Channel.setCh_state(1);
//				gm_Channel.setChtype_id(op_ChannelTypeService.findById(chtype_id));				
//				if(dev_collectId.equals("root")){
//					gm_Channel.setDev_collectId(gm_Device);
//				}else{
//					gm_Channel.setDev_collectId(this.gm_DeviceService.findById(devSetup_map.get(dev_collectId).getDev_id()));
//				}
//				gm_Channel.setDev_sensorId(null);
//				gm_Channel.setScene_id(op_SceneService.findById(scene_id));				
//				//save
//				channelService.save(gm_Channel);
//				gm_DevChannel.setCh_id(gm_Channel);
//				gm_DevChannel.setCh_memoryPeriod(Integer.parseInt(memoryPeriod));
//				gm_DevChannel.setCh_procesStyle(Integer.parseInt(procesStyle));
//				gm_DevChannel.setDch_order(Integer.parseInt(order));
//				gm_DevChannel.setDev_addr(gm_DevNet.getNet_addr());
//				gm_DevChannel.setDev_id(gm_Device);
//				//save
//				gm_DevChannelService.save(gm_DevChannel);
//			}
//		}
//不能在循环里写查询		
		//save
		gm_Device.setCust_saleId(null);
		gm_Device.setCust_serviceId(null);
		gm_DeviceService.save(gm_Device);
		gm_DevNet.setDev_id(gm_Device);
		gm_DevNet.setNet_no(gm_Device.getDev_no());		
		//save
		devNetService.save(gm_DevNet);
		//save
		Gm_Devsts gm_Devsts = this.gm_DevstsService.findByDev_addr(gm_DevNet.getNet_addr());
		if(gm_Devsts==null)gm_Devsts = new Gm_Devsts();
		gm_Devsts.setDev_id(gm_DevNet.getDev_id());
		gm_Devsts.setDev_addr(gm_DevNet.getNet_addr());
		gm_Devsts.setDest_regSts(0);
		gm_Devsts.setDest_linkSts(gm_DevNet.getNet_linkSts());
		//saveOrUpdate
		this.gm_DevstsService.saveOrUpdate(gm_Devsts);		
		for(DevSetup devSetup : devSetup_map.values()){
			Gm_Device gm_Device_wsn = new Gm_Device();
			Gm_DevNet gm_DevNet_wsn = new Gm_DevNet();
			gm_Device_wsn.setCust_saleId(null);
			gm_Device_wsn.setCust_serviceId(null);			
			gm_Device_wsn.setDev_btype(1);		
			//gm_Device_wsn.setDev_id(devSetup.getDev_id());
			gm_Device_wsn.setDev_model(devSetup.getDev_model());
			gm_Device_wsn.setDev_name(devSetup.getDev_name());
			gm_Device_wsn.setDev_no(devSetup.getDev_no());
			gm_Device_wsn.setDev_powerType(devSetup.getDev_powerType());
			gm_Device_wsn.setDev_serial(devSetup.getDev_serial());
			gm_Device_wsn.setDev_state(1);
			Op_Scene op_Scene_wsn = op_Scene_map.get(devSetup.getTianjiawangluojiedian_scene_id());
			gm_Device_wsn.setScene_id(op_Scene_wsn);			
			//save
			gm_DeviceService.save(gm_Device_wsn);
			devSetup.setGm_Device(gm_Device_wsn);
			
			gm_DevNet_wsn.setDev_id(gm_Device_wsn);
			gm_DevNet_wsn.setNet_addr(devSetup.getNet_addr());
			gm_DevNet_wsn.setNet_depth(devSetup.getNet_depth());
			//gm_DevNet_wsn.setNet_id(devSetup.getId());
			gm_DevNet_wsn.setNet_linkSts(Integer.parseInt(devSetup.getNet_linkSts()));
			gm_DevNet_wsn.setNet_no(devSetup.getDev_no());
			if(devSetup.getPid().equals("root"))	{			
				gm_DevNet_wsn.setNet_pid(gm_DevNet.getNet_id());
			}else{	
				gm_DevNet_wsn.setNet_pid(devSetup_map.get(devSetup.getPid()).getId());
			}
			gm_DevNet_wsn.setNet_role(devSetup.getNet_role());
			gm_DevNet_wsn.setNet_state(1);
			gm_DevNet_wsn.setNet_type(1);
			//save
			devNetService.save(gm_DevNet_wsn);
			devSetup.setId(gm_DevNet_wsn.getNet_id());			
		}
		if(_ch_no!=null){
			for(int i=0;i<_ch_no.length;i++){
				Gm_Channel gm_Channel = new Gm_Channel();
				Gm_DevChannel gm_DevChannel = new Gm_DevChannel();
				String scene_id = _scene_id[i];
				String ch_no = _ch_no[i];
				String ch_name = _ch_name[i];
				String dev_collectId = _dev_collectId[i];
				String ch_chlNo = _ch_chlNo[i];
				String chtype_id = _chtype_id[i];
				String ch_offerSer = _ch_offerSer[i];
				String order = _order[i];
				String procesStyle = _procesStyle[i];
				String memoryPeriod = _memoryPeriod[i];
				gm_Channel.setCh_chlNo(Integer.parseInt(ch_chlNo));
				gm_Channel.setCh_corrFormula("");
				gm_Channel.setCh_depth("");
				gm_Channel.setCh_memoryPeriod(Integer.parseInt(memoryPeriod));
				gm_Channel.setCh_name(ch_name);
				gm_Channel.setCh_no(ch_no);
				gm_Channel.setCh_offerSer(Integer.parseInt(ch_offerSer));
				gm_Channel.setCh_procesStyle(Integer.parseInt(procesStyle));
				gm_Channel.setCh_seat_no("");
				gm_Channel.setCh_state(1);
				
				Op_ChannelType gm_ChannelType = op_ChannelType_map.get(chtype_id);
				gm_Channel.setChtype_id(gm_ChannelType);	
				
				gm_Channel.setCh_dectDig(gm_ChannelType.getCh_dectDig());
				gm_Channel.setCh_unit(gm_ChannelType.getCh_unit());
				gm_Channel.setCh_max(gm_ChannelType.getCh_max());
				gm_Channel.setCh_min(gm_ChannelType.getCh_min());
				gm_Channel.setCh_crateMax(gm_ChannelType.getCh_crateMax());
				gm_Channel.setCh_corrCyc(gm_ChannelType.getCh_corrCyc());
				gm_Channel.setCh_corrFormula(gm_ChannelType.getCh_corrFormula());
				gm_Channel.setCh_corrUnit(gm_ChannelType.getCh_corrUnit());
				
				if(dev_collectId.equals("root")){
					gm_Channel.setDev_collectId(gm_Device);
				}else{
					gm_Channel.setDev_collectId(devSetup_map.get(dev_collectId).getGm_Device());
				}
				gm_Channel.setDev_sensorId(null);
				gm_Channel.setScene_id(op_Scene_map.get(scene_id));			
				//save
				channelService.save(gm_Channel);
				Gm_Latestdata gm_Latestdata = new Gm_Latestdata();
				gm_Latestdata.setCh_id(gm_Channel);
				this.gm_LatestdataDao.save(gm_Latestdata);
				gm_DevChannel.setCh_id(gm_Channel);
				gm_DevChannel.setCh_memoryPeriod(Integer.parseInt(memoryPeriod));
				gm_DevChannel.setCh_procesStyle(Integer.parseInt(procesStyle));
				gm_DevChannel.setDch_order(Integer.parseInt(order));
				gm_DevChannel.setDev_addr(gm_DevNet.getNet_addr());
				gm_DevChannel.setDev_id(gm_Device);
				//save
				gm_DevChannelService.save(gm_DevChannel);
			}
		}	
	}
	
}
