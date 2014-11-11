package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_PlantformUserDao;
import org.unism.op.domain.Op_PlantformUser;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
@Service
public class Op_PlantformUserService extends
		BaseService<Op_PlantformUser, String> {
	@Autowired Op_PlantformUserDao op_PlantformUserDao;
	@Override
	protected IBaseDao<Op_PlantformUser, String> getEntityDao() {
		return op_PlantformUserDao;
	}
	
	/**
	 * 据场景ID 查找平台用户
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Op_PlantformUser> findByScene_id(String scene_id){
		Search search = new Search();
		Filter filter = Filter.equal("scene_id.scene_id", scene_id);
		search.addFilter(filter);
		return this.search(search);
	}
}
