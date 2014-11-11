package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_UserInfoDao;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
@Service
public class Op_UserInfoService extends BaseService<Op_UserInfo, String> {
	@Autowired Op_UserInfoDao op_UserInfoDao;
	
	@Override
	protected IBaseDao<Op_UserInfo, String> getEntityDao() {
		return op_UserInfoDao;
	}
	public boolean isExist(String user_loginName){
		Search search = new Search();
		Filter filter = Filter.equal("user_loginName",user_loginName);
		search.addFilter(filter);
		List<Op_Scene> list = this.search(search);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
}
