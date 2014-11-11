package org.unism.pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.pro.dao.Pro_mapDao;
import org.unism.pro.domain.Pro_map;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Pro_mapService extends BaseService<Pro_map, String>
{
	@Autowired Pro_mapDao pro_mapDao;

	@Override
	protected IBaseDao<Pro_map, String> getEntityDao()
	{
		// TODO Auto-generated method stub
		return pro_mapDao;
	}

}
