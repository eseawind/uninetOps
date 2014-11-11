package org.unism.gm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.dao.Gm_DevstsHisDao;
import org.unism.gm.domain.Gm_DevstsHis;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_DevstsHisService extends BaseService<Gm_DevstsHis, String>{
	@Autowired Gm_DevstsHisDao gm_DevstsHisDao;
	
	@Override
	protected IBaseDao<Gm_DevstsHis, String> getEntityDao() {
		return gm_DevstsHisDao;
	}
	
}
