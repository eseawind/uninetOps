package org.unism.gm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.dao.Gm_ChannelDao;
import org.unism.gm.dao.Gm_DevChannelDao;
import org.unism.gm.dao.Gm_DevFaultDao;
import org.unism.gm.dao.Gm_LatestdataDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevFault;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.op.dao.Op_DevCtrlStsDao;
import org.unism.op.domain.Op_DevCtrlSts;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.util.Chtype_for_charts;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
@Service
public class Gm_ChannelService extends BaseService<Gm_Channel, String> {
	@Autowired Gm_ChannelDao gm_ChannelDao;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_LatestdataDao gm_LatestdataDao;
	@Autowired Gm_DevChannelDao gm_DevChannelDao;
	@Autowired Gm_DevFaultDao gm_DevFaultDao;
	@Autowired Op_DevCtrlStsDao op_DevCtrlStsDao;
	@Autowired Gm_LatestdataService gm_LatestdataService;
	
	@Override
	protected IBaseDao<Gm_Channel, String> getEntityDao() {
		// TODO Auto-generated method stub
		return gm_ChannelDao;
	}
	
	/**
	 * 据场景ID 查找在用通道
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_Channel> findByScene_id(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_id.scene_id", scene_id),Filter.equal("ch_state", 1));
		search.addFilter(filter);
		return this.search(search);
	}

	public List<String> findGm_ChannelByScene_id(String scene_id)
	{
		return gm_ChannelDao.findGm_ChannelByScene_id(scene_id);
	}
	
	public String json_findById(String ch_id){
		Search search = new Search();
		Filter filter = Filter.equal("ch_id",ch_id);
		search.addFilter(filter);
		List<Gm_Channel> list= this.search(search);
		String name = "[";
		for(Gm_Channel gm_Channel:list){
			name+="{id:'"+gm_Channel.getCh_no()+"-"+gm_Channel.getCh_name()+"'},";
		}
		if(name.length()>1){
			name = name.substring(0,name.length()-1);							
		}
		name+="]";
		return name;
	}
	/**
	 * 据场景ID 查找对外提供服务的在用通道
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_Channel> findByScene_idAndCh_offerSer(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_id.scene_id", scene_id),Filter.equal("ch_state", 1),Filter.equal("ch_offerSer", 1));
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 据场景ID 查找对外提供服务的和不对外提供服务的在用通道
	 * @return
	 * @author weixiaohua
	 */
	public List<Gm_Channel> findByScene_idAndCh_offerSerIsOrNot(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_id.scene_id", scene_id),Filter.equal("ch_state", 1));
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 根据场景ID 查询通道信息 以通道编号排序
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_Channel> findBySceneID(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("scene_id.scene_id", scene_id));
		search.addFilter(filter);
		search.addSortAsc("ch_no");
		return this.search(search);		
	}
	
	/**
	 * 指定通道ID 开始时间 结束时间 查询平均值，null=0
	 * @param ch_id
	 * @param beginTime
	 * @param endTime
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findHisAvgByCh_idAndBeginTimeAndEndTime(String ch_id, String beginTime, String endTime) throws Exception{
		return this.gm_ChannelDao.findHisAvgByCh_idAndBeginTimeAndEndTime(ch_id, beginTime, endTime);
	}
	
	/**
	 * 曲线分析 指定场景id 查询对外提供服务的在用通道
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String json_findByScene_idAndCh_offerSer(String scene_id){
		return this.gm_ChannelDao.json_findByScene_idAndCh_offerSer(scene_id);	
	}
	
	/**
	 * 运维 曲线分析 指定场景id 查询在用通道
	 * @param scene_id
	 * @return
	 */
	
	public String json_findByScene_idAndCh_offerSer_run(String scene_id){
		return this.gm_ChannelDao.json_findByScene_idAndCh_offerSer_run(scene_id);
	}
	
	/**
	 * 曲线分析 指定场景id集合 查询对外提供服务的在用通道 并按通道应用类型分类
	 * @return json 
	 * [
	 * 		{
	 * 			chtype_id:'',
	 * 			chtype_no:'',
	 * 			chtype_name:'',
	 * 			channels:
	 * 					[
	 * 						{
	 * 							ch_id:'',
	 * 							ch_no:'',
	 * 							ch_name:''
	 * 						},
	 * 						...
	 * 					]
	 * 		},
	 * 		...
	 * ]
	 * @author Wang_Yuliang
	 */
	public String json_findAllByScene_idAndCh_offerSer(List<String> scene_id_list){
		return this.gm_ChannelDao.json_findAllByScene_idAndCh_offerSer(scene_id_list);
	}
	
	/**
	 * 曲线分析 指定场景id集合 查询对外提供服务的在用通道 并按通道应用类型分类
	 * @return json 
	 * [
	 * 		{
	 * 			chtype_id:'',
	 * 			chtype_no:'',
	 * 			chtype_name:'',
	 * 			channels:
	 * 					[
	 * 						{
	 * 							ch_id:'',
	 * 							ch_no:'',
	 * 							ch_name:''
	 * 						},
	 * 						...
	 * 					]
	 * 		},
	 * 		...
	 * ]
	 * @author Wang_Yuliang
	 */
	public String json_findAllByScene_idAndCh_offerSer_run(List<String> scene_id_list){
		return this.gm_ChannelDao.json_findAllByScene_idAndCh_offerSer_run(scene_id_list);
	}
	
	/**
	 * 通过用户ID 查找采集设备ID集合
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findCh_idByUser_id(String user_id){
		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);		
		List<String> list = new ArrayList();
		//空集合会出错吗？
		list.add("-1");
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("scene_id.scene_id", scene_id_list));
		search.addFilter(filter);
		List<Gm_Channel> gm_channel_list = this.search(search);
		for(Gm_Channel gm_channel : gm_channel_list){
			list.add(gm_channel.getCh_id());
		}
		return list;	
	}

	/**
	 * 指定场景ID 通道应用类型ID 查询在用并对外提供服务的通道集合
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_Channel> findByScene_idAndChtype_id(String scene_id, String chtype_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("ch_offerSer", 1),Filter.equal("scene_id.scene_id", scene_id),Filter.equal("chtype_id.chtype_id", chtype_id));
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 指定场景ID 通道应用类型编号 起止时间 查询平均值
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findAvgByGm_Channel_listAndChtype_noAndCheckTime(List<Gm_Channel> gm_Channel_list, String chtype_no, String beginTime, String endTime){
		return this.gm_ChannelDao.findAvgByGm_Channel_listAndChtype_noAndCheckTime(gm_Channel_list,chtype_no,beginTime,endTime);
	}

	/**
	 * 指定场景ID 通道应用类型编号 起止时间 查询最小值
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findMinByGm_Channel_listAndChtype_noAndCheckTime(List<Gm_Channel> gm_Channel_list, String chtype_no,String beginTime, String endTime) {
		return this.gm_ChannelDao.findMinByGm_Channel_listAndChtype_noAndCheckTime(gm_Channel_list,chtype_no,beginTime,endTime);
	}
	
	/**
	 * 指定一个通道ID的数据，将其按照通道应用类型编号分类，分成若干组，并封装到Chtype_for_charts对象中
	 * @author Wang_Yuliang
	 */
	public List<Chtype_for_charts> findCh_id_arr_listGroupByChtype_no(String[] ch_id_arr){
		return this.gm_ChannelDao.findCh_id_arr_listGroupByChtype_no(ch_id_arr);		
	}
	
	/**
	 * 指定采集设备ID 查询在用通道
	 * @param dev_collectId
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_Channel> findByDev_collectId(String dev_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("dev_collectId.dev_id", dev_id));
		search.addFilter(filter);
		return this.search(search);
	}

	

	public List<Gm_Channel> findBySceneIDandChtype(String scene_id,
			List<String> chtype_idList)
	{
		return this.gm_ChannelDao.findBySceneIDandChtype(scene_id, chtype_idList);
	}

	public List<Gm_Channel> findBySceneIDandChtype_id(String scene_id,
			String chtype_id)
	{
		return this.gm_ChannelDao.findBySceneIDandChtype_id(scene_id,chtype_id);
	}

	/**
	 * 验证通道编号是否重复
	 * @return json
	 * 			boolean
	 * @author Wang_Yuliang
	 */
	public String isCh_noExist(String ch_no){
		String result = "0";
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("ch_no", ch_no));
		search.addFilter(filter);
		List<Gm_Channel> gm_Channel_list = this.search(search);
		if(gm_Channel_list.size()>0){
			result = "1";
		}
		return result;
	}
		
	/**
	 * 保存
	 * @param gm_Channel
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void save_wangyuliang(Gm_Channel gm_Channel) throws Exception{
		this.save(gm_Channel);
		//System.out.println(gm_Channel.getCh_id());
		Gm_Latestdata gm_Latestdata = new Gm_Latestdata();
		gm_Latestdata.setCh_id(gm_Channel);
		this.gm_LatestdataDao.save(gm_Latestdata);
	}

	/**
	 * 删除
	 * @param gm_Channel
	 * @throws Exception
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void delete_wangyuliang(Gm_Channel gm_Channel) throws Exception{
		List<Gm_DevChannel> gm_DevChannelList = this.gm_DevChannelDao.findAllEq("ch_id.ch_id", gm_Channel.getCh_id());
		List<Gm_DevFault> gm_DevFaultList = this.gm_DevFaultDao.findAllEq("ch_id", gm_Channel.getCh_id());
		List<Op_DevCtrlSts> op_devCtrlStsList = this.op_DevCtrlStsDao.findAllEq("ch_id.ch_id", gm_Channel.getCh_id());
		for(Gm_DevChannel gm_DevChannel : gm_DevChannelList){
			this.gm_DevChannelDao.delete(gm_DevChannel);
		}
		for(Gm_DevFault gm_DevFault : gm_DevFaultList){
			this.gm_DevFaultDao.delete(gm_DevFault);
		}	
		for(Op_DevCtrlSts op_DevCtrlSts : op_devCtrlStsList){
			this.op_DevCtrlStsDao.delete(op_DevCtrlSts);
		}	
		Gm_Latestdata gm_Latestdata = this.gm_LatestdataService.findByCh_id(gm_Channel.getCh_id());				
		this.gm_LatestdataDao.delete(gm_Latestdata);
		gm_Channel.setCh_state(0);
		this.update(gm_Channel);
	}

	/**
	 * 通过通道编号查询通道
	 * @param chNo
	 * @return
	 */
	public Gm_Channel findByChNo(String[] chNo) {
		Search search = new Search();
		search.addFilterIn("ch_no", chNo);
		List<Gm_Channel> list = this.gm_ChannelDao.search(search);
		for (Gm_Channel gmChannel : list) {
			return gmChannel;
		}
		return null;
	}

	public List<Gm_Channel> findByIds(String[] channelId) {
		Search search = new Search();
		search.addFilterIn("ch_id", channelId);
		return gm_ChannelDao.search(search);
	}

	/**
	 * 曲线分析 按设备查询通道
	 * @param dev_id_arr
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_showChannelByDevice_run(String[] dev_id_arr){
		return this.gm_ChannelDao.json_showChannelByDevice_run(dev_id_arr);
	}

	public Gm_Channel findByDevCollectId(String devId) {
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("ch_offerSer", 1),Filter.equal("dev_collectId.dev_id", devId));
		search.addFilter(filter);
		List<Gm_Channel> channels = gm_ChannelDao.search(search);
		for (Gm_Channel gmChannel : channels) {
			return gmChannel;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Gm_Channel> findChannelsByScenes(List<String> scene_id_arr) {
		Search search = new Search();
		search.addFilterIn("scene_id.scene_id", scene_id_arr);
		search.addFilterIn("ch_state", 1);
		search.addSortAsc("chtype_id.chtype_id");
		return gm_ChannelDao.search(search);
	}

	public List<Gm_Channel> findByIdArr(String[] chIdArr) {
		Search search = new Search();
		search.addFilterIn("ch_id", chIdArr);
		return gm_ChannelDao.search(search);
	}
	/**
	 * 加载状态通道编号
	 * @return
	 * @auther Wang_Yuliang
	 */
	public String json_jiazaizhuangtaitongdaobianhao(String dev_id, String zhuangtaitongdaobiaohao){
		return this.gm_ChannelDao.json_jiazaizhuangtaitongdaobianhao(dev_id,zhuangtaitongdaobiaohao);
	}

	@SuppressWarnings("unchecked")
	public Gm_Channel findChannelByChNo(String chNo) {
		Search search = new Search();
		search.addFilterEqual("ch_no", chNo);
		List<Gm_Channel> channels = gm_ChannelDao.search(search);
		for (Gm_Channel gm_Channel : channels) {
			return gm_Channel;
		}
		return null;
	}

	public List<Gm_Channel> findBySceneIds(String[] sceneIdArr) {
		Search search = new Search();
		Filter filter = Filter.and(Filter.in("scene_id.scene_id", sceneIdArr),Filter.equal("ch_state", 1),Filter.equal("ch_offerSer", 1));
		search.addFilter(filter);
		return gm_ChannelDao.search(search);
	}
	
	public List<Gm_Channel> findAllByAddrLikeBegin(String addr){
		return gm_ChannelDao.findAllByAddrLikeBegin(addr);
	}
	
	/**
	 * 验证场景下是否存在通道
	 * @return boolean
	 * @author liucl
	 */
	public boolean isScene_idExist(String scene_id){
		Search search = new Search();
		Filter filter = Filter.equal("scene_id.scene_id", scene_id);
		search.addFilter(filter);
		List<Gm_Channel> gm_Channel_list = this.search(search);
		if(gm_Channel_list.size()>0){
			return true;
		}
		return false;
	}
}
