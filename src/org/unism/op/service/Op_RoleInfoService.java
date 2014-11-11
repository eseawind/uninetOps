package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_RoleInfoDao;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_RoleRegith;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;
@Service
public class Op_RoleInfoService extends BaseService<Op_RoleInfo, String> {
	@Autowired Op_RoleInfoDao op_RoleInfoDao;
	@Autowired Op_RoleRegithService op_RoleRegithService;
	@Override
	protected IBaseDao<Op_RoleInfo, String> getEntityDao() {
		return op_RoleInfoDao;
	}
		
}
