package org.unism.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_DevCtrlStsDao;
import org.unism.op.domain.Op_DevCtrlSts;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Op_DevCtrlStsService extends BaseService<Op_DevCtrlSts, String> {

	@Autowired Op_DevCtrlStsDao op_DevCtrlStsDao;
	
	@Override
	protected IBaseDao<Op_DevCtrlSts, String> getEntityDao() {
		return op_DevCtrlStsDao;
	}

}
