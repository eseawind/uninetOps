package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.AutoCtrlFactorDao;
import org.unism.op.domain.AutoCtrlConfig;
import org.unism.op.domain.AutoCtrlFactor;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class AutoCtrlFactorService extends BaseService<AutoCtrlFactor, String> {

	@Autowired AutoCtrlFactorDao autoCtrlFactorDao;
	
	@Override
	protected IBaseDao<AutoCtrlFactor, String> getEntityDao() {
		return autoCtrlFactorDao;
	}

	public List<AutoCtrlFactor> findByAutoCtrlConfig(AutoCtrlConfig autoCtrlConfig) {
		Search search = new Search();
		search.addFilterEqual("autoCtrlConfig", autoCtrlConfig);
		return autoCtrlFactorDao.search(search);
	}

}
