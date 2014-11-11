package org.unism.gm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.dao.Gm_DevSetupTemplateDao;
import org.unism.gm.domain.Gm_DevSetupTemplate;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_DevSetupTemplateService extends BaseService<Gm_DevSetupTemplate, String>{

	@Autowired Gm_DevSetupTemplateDao gm_DevSetupTemplateDao;
	
	@Override
	protected IBaseDao<Gm_DevSetupTemplate, String> getEntityDao() {
		return gm_DevSetupTemplateDao;
	}

	/**
	 * 判断设备是否已经存在
	 * @param gm_Device
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isExist(Gm_DevSetupTemplate gm_DevSetupTemplate){
		List<Gm_DevSetupTemplate> gm_DevSetupTemplate_list = new ArrayList<Gm_DevSetupTemplate>();
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("devst_name", gm_DevSetupTemplate.getDevst_name()),Filter.equal("devst_author", gm_DevSetupTemplate.getDevst_author()));
		search.addFilter(filter);
		gm_DevSetupTemplate_list = this.gm_DevSetupTemplateDao.search(search);
		if(gm_DevSetupTemplate_list.size()>0){
			return true;
		}
		return false;
	}
}
