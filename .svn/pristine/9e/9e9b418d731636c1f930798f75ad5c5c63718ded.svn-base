package org.unism.gm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.dao.Gm_DevstsDao;
import org.unism.gm.domain.Gm_Devsts;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_DevstsService extends BaseService<Gm_Devsts, String>{
	@Autowired Gm_DevstsDao gm_DevstsDao;
	
	@Override
	protected IBaseDao<Gm_Devsts, String> getEntityDao() {
		return gm_DevstsDao;
	}
	
	@Transactional(readOnly = true)
	public boolean isExist(String propertyName, Object newValue, Object oldValue) {
		if (newValue == null || "".equals(newValue) || newValue.equals(oldValue))
			return true;
		Gm_Devsts object = this.findUniqueByProperty(propertyName, newValue);
		if(object == null){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 指定设备ID 查找智能设备状态信息
	 * @param dev_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public Gm_Devsts findByDev_id(String dev_id){
		Search search = new Search();
		Filter filter = Filter.equal("dev_id.dev_id", dev_id);
		search.addFilter(filter);
		List<Gm_Devsts> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 分页查询
	 * @return json
	 * 		{
	 * 			count:'?',
	 * 			no:'?',
	 * 			size:'12',
	 * 			result:
	 * 				[
	 * 					{
	 * 						dev_name:'?',
	 * 						net_addr:'?',
	 * 						dest_regSts:'?',
	 * 						dest_lastCommTime:'?',
	 * 						dest_curTime:'?',
	 * 						dest_timeSts:'?',
	 * 						dest_workSts:'?',
	 * 						dest_sigStg:'?',
	 * 						dest_commQuaily:'?',
	 * 						dest_linkSts:'?',
	 * 						st_commCyc:'?',
	 * 						dest_repCyc:'?',
	 * 						dest_vol:'?',
	 * 						dest_resetNum:'?',
	 * 						dest_recData:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 * 0628 弃用 Wang_Yuliang
	 */
	public String page(List<String> scene_id_arr, Integer no, Integer size){
		return this.gm_DevstsDao.page(scene_id_arr,no,size);
	}
	
	/**
	 * 分页查询 (由王振智提供的sql脚本)
	 * @return json
	 * 		{
	 * 			count:'?',
	 * 			no:'?',
	 * 			size:'12',
	 * 			result:
	 * 				[
	 * 					{
	 * 						dev_name:'?',
	 * 						net_addr:'?',
	 * 						dest_regSts:'?',
	 * 						dest_lastCommTime:'?',
	 * 						dest_curTime:'?',
	 * 						dest_timeSts:'?',
	 * 						dest_workSts:'?',
	 * 						dest_sigStg:'?',
	 * 						dest_commQuaily:'?',
	 * 						dest_linkSts:'?',
	 * 						st_commCyc:'?',
	 * 						dest_repCyc:'?',
	 * 						dest_vol:'?',
	 * 						dest_resetNum:'?',
	 * 						dest_recData:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 */
	public String page_wangzz(List<String> scene_id_arr, Integer no, Integer size){
		return this.gm_DevstsDao.page_wangzz(scene_id_arr,no,size);
	}
	
	/**
	 * 分页查询 (由王振智提供的sql脚本)
	 * @return json
	 * 		{
	 * 			count:'?',
	 * 			no:'?',
	 * 			size:'12',
	 * 			result:
	 * 				[
	 * 					{
	 * 						dev_name:'?',
	 * 						net_addr:'?',
	 * 						dest_regSts:'?',
	 * 						dest_lastCommTime:'?',
	 * 						dest_curTime:'?',
	 * 						dest_timeSts:'?',
	 * 						dest_workSts:'?',
	 * 						dest_sigStg:'?',
	 * 						dest_commQuaily:'?',
	 * 						dest_linkSts:'?',
	 * 						st_commCyc:'?',
	 * 						dest_repCyc:'?',
	 * 						dest_vol:'?',
	 * 						dest_resetNum:'?',
	 * 						dest_recData:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 * 这个是根据 查询按钮触发的查询 更改了查询条件 Wang_Yuliang 2011-07-20 10:28
	 */
	public String page_search(List<String> scene_id_list, String hid_condition, String hid_value, Integer no, Integer size){
		return this.gm_DevstsDao.page_search(scene_id_list,hid_condition,hid_value,no,size);
	}
	
	/**
	 * 指定设备地址 查询设备状态
	 * @param dev_addr
	 * @return
	 * @author Wang_Yuliang
	 */
	public Gm_Devsts findByDev_addr(String dev_addr){
		Gm_Devsts gm_Devsts = null;
		Search search = new Search();
		Filter filter = Filter.equal("dev_addr", dev_addr);
		search.addFilter(filter);
		List<Gm_Devsts> gm_Devsts_list = this.search(search);
		if(gm_Devsts_list.size()>0){
			gm_Devsts = gm_Devsts_list.get(0);
		}
		return gm_Devsts;
	}

	public List<Object[]> findDest_WorkStsListByUserIdAndDevaddr(String userId,
			String hid_condition, String hid_value, String scene_id_selectAll) {
		// TODO Auto-generated method stub
		return this.gm_DevstsDao.findDest_WorkStsListByUserIdAndDevaddr(userId,hid_condition,hid_value,scene_id_selectAll);
	}
	
}
