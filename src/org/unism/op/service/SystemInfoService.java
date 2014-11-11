package org.unism.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.SystemInfoDao;
import org.unism.op.domain.SystemInfo;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class SystemInfoService extends BaseService<SystemInfo, String> {

	@Autowired SystemInfoDao systemInfoDao;
	
	@Override
	protected IBaseDao<SystemInfo, String> getEntityDao() {
		return systemInfoDao;
	}

}
