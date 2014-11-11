package org.unism.gm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.dao.Gm_ChannelDao;
import org.unism.gm.dao.Gm_LatestdataDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_Latestdata;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_LatestdataService extends BaseService<Gm_Latestdata, String>{
	@Autowired Gm_LatestdataDao gm_LatestdataDao;
	@Autowired Gm_ChannelDao gmChannelDao;
	@Autowired Gm_ChannelService channelService;
	
	@Override
	protected IBaseDao<Gm_Latestdata, String> getEntityDao() {
		return gm_LatestdataDao;
	}
	
	public Gm_Latestdata findByCh_id(String ch_id){
		Search search = new Search();
		Filter filter = Filter.equal("ch_id.ch_id", ch_id);
		search.addFilter(filter);
		List<Gm_Latestdata> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 指定通道集合 查询当前平均值
	 * @param gm_Channel_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findAvgByGm_Channel_list(List<Gm_Channel> gm_Channel_list){
		return this.gm_LatestdataDao.findAvgByGm_Channel_list(gm_Channel_list);
	}
	
	/**
	 * 指定通道集合 查询当前最大值
	 * @param gm_Channel_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findMaxByGm_Channel_list(List<Gm_Channel> gm_Channel_list){
		return this.gm_LatestdataDao.findMaxByGm_Channel_list(gm_Channel_list);
	}
	
	/**
	 * 指定通道集合 查询当前最小值
	 * @param gm_Channel_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findMinByGm_Channel_list(List<Gm_Channel> gm_Channel_list){
		return this.gm_LatestdataDao.findMinByGm_Channel_list(gm_Channel_list);
	}
	
	/**
	 * 指定场景ID集合 通道应用类型 查询符合条件的通道(且对外提供服务的)的平均值 时间检查-24 +1
	 * @param scene_id_list
	 * @param chtppy_id
	 * @return
	 * @author Wang_Yuliang
	 * 0624 弃用 Wang_Yuliang
	 */
	public Double findAvgByScene_id_listAndChtype_noAndCh_offerSer(List<String> scene_id_list, String chtype_no){
		return this.gm_LatestdataDao.findAvgByScene_id_listAndChtype_noAndCh_offerSer(scene_id_list, chtype_no);
	}
	
	/**
	 * 指定场景ID集合 通道应用类型 查询符合条件的通道(且对外提供服务的)的最小值 时间检查-24 +1
	 * @param scene_id_list
	 * @param chtppy_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public Double findMinByScene_id_listAndChtype_noAndCh_offerSer(List<String> scene_id_list, String chtype_no){
		return this.gm_LatestdataDao.findMinByScene_id_listAndChtype_noAndCh_offerSer(scene_id_list, chtype_no);
	}
	
	public List<Gm_Latestdata> findGm_LatestdataByCh_id(List<String> ch_idList,String endTime,String beginTime)
	{
		return this.gm_LatestdataDao.findGm_LatestdataByCh_id(ch_idList,beginTime,endTime);
	}
	
	/**
	 * 通过通道查询最新数据
	 * @param gm_Channel_list
	 * @return
	 */
	public List<Gm_Latestdata> findLatestdataByCh_id(List<Gm_Channel> gm_Channel_list)
	{
		return this.gm_LatestdataDao.findLatestdataByCh_id(gm_Channel_list);
	}

	public List<Gm_Latestdata> findAllByChtypeandScene(String scene_id)
	{
		return this.gm_LatestdataDao.findAllByChtypeandScene(scene_id);
	}
	
	/**
	 * 通过net_id查询所关联的设备所有通道的最新数据
	 * @param net_id
	 * @return
	 */
	public List<Gm_Latestdata> findByNet_id(List<String> net_id)
	{
		return this.gm_LatestdataDao.findByNet_id(net_id);
	}

	@SuppressWarnings("unchecked")
	public List<Gm_Latestdata> findByChannel(List<Gm_Channel> channels) {
		Search search = new Search();
		search.addFilterIn("ch_id", channels);
		return gm_LatestdataDao.search(search);
	}

	@SuppressWarnings("unchecked")
	public List<Gm_Latestdata> findByChIds(List<String> chIds) {
		Search search = new Search();
		search.addFilterIn("ch_id.ch_id", chIds);
		return gm_LatestdataDao.search(search);
	}

	public List<Gm_Latestdata> findBySceneIds(String[] sceneIdArr) {
		List<Gm_Channel> channels = channelService.findBySceneIds(sceneIdArr);
		Search search = new Search();
		search.addFilterIn("ch_id", channels);
//		search.addFilterNotEqual("hida_corrValue", null);
		return gm_LatestdataDao.search(search);
	}

	public List<Object[]> findByScene(String[] sceneIdArr) {
		return gm_LatestdataDao.findByScene(sceneIdArr);
	}

}
