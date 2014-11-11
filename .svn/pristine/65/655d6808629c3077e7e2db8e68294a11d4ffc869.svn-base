package org.unism.gm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.dao.Gm_DevCtrlStsDao;
import org.unism.gm.domain.Gm_DevCtrlSts;
import org.unism.gm.domain.Gm_Latestdata;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_DevCtrlStsService extends BaseService<Gm_DevCtrlSts, String> {
	@Autowired Gm_DevCtrlStsDao gm_DevCtrlStsDao;

	@Override
	protected IBaseDao<Gm_DevCtrlSts, String> getEntityDao() {
		// TODO Auto-generated method stub
		return gm_DevCtrlStsDao;
	}
	
	/**
	 * 指定控制设备ID 返回控制设备状态信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public Gm_DevCtrlSts findByDect_id(String dect_id){
		Search search = new Search();
		Filter filter = Filter.equal("dect_id.dect_id", dect_id);
		search.addFilter(filter);
		List<Gm_DevCtrlSts> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
