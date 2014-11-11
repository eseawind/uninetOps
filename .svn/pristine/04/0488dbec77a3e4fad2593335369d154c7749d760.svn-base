package org.unism.gm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.dao.Gm_DevCtrlOperateDao;
import org.unism.gm.domain.Gm_DevCtrlOperate;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_DevCtrlOperateService extends BaseService<Gm_DevCtrlOperate, String> {
	@Autowired Gm_DevCtrlOperateDao gm_DevCtrlOperateDao;
	@Override
	protected IBaseDao<Gm_DevCtrlOperate, String> getEntityDao() {
		// TODO Auto-generated method stub
		return gm_DevCtrlOperateDao;
	}
	
	/**
	 * 不用了 指定控制设备ID 查询设备控制信息
	 * @param dect_id
	 * @return
	 * @author Wang_Yuliang
	 * Wang_Yuliang 0603 UP
	 */
//	public Gm_DevCtrlOperate findByDect_id(String dect_id){
//		Search search = new Search();
//		Filter filter = Filter.equal("dect_id.dect_id", dect_id);
//		search.addFilter(filter);
//		List<Gm_DevCtrlOperate> list = this.search(search);
//		if(list.size()>0){
//			return list.get(0);
//		}
//		return null;
//	}
	
	/**
	 * 指定控制设备ID 查询控制设备操作信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public Gm_DevCtrlOperate findByDect_id(String dect_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dect_id.dect_id", dect_id));
		search.addFilter(filter);
		List<Gm_DevCtrlOperate> list = this.search(search);
		if(list.size()>0){
			for(Gm_DevCtrlOperate gm_DevCtrlOperate : list){
				if(gm_DevCtrlOperate.getDect_id().getDect_state() == 1){
					return gm_DevCtrlOperate;
				}
			}
		}
		return null;
	}

	
}
