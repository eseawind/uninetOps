package org.unism.gm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.dao.Gm_DevFaultDao;
import org.unism.gm.domain.Gm_DevFault;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_DevFaultService extends BaseService<Gm_DevFault, String>
{
	@Autowired Gm_DevFaultDao gm_DevFaultDao;

	@Override
	protected IBaseDao<Gm_DevFault, String> getEntityDao()
	{
		// TODO Auto-generated method stub
		return gm_DevFaultDao;
	}

	public Gm_DevFault findBydefId(String def_id)
	{
		return this.gm_DevFaultDao.findBydefId(def_id);
	}

	public List<Object[] > findDevFaultTypeListByUserIdAndTime(
			String userId, String beginTime, String endTime, String scene_id_selectAll) {
		// TODO Auto-generated method stub
		return this.gm_DevFaultDao.findDevFaultTypeListByUserIdAndTime(userId,beginTime,endTime,scene_id_selectAll);
	}

}
