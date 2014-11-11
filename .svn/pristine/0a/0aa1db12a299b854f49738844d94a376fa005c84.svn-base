package org.unism.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_ledDao;
import org.unism.op.domain.Op_led;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Op_ledService extends BaseService<Op_led, String>{
	@Autowired Op_ledDao op_ledDao;

	@Override
	protected IBaseDao<Op_led, String> getEntityDao()
	{
		// TODO Auto-generated method stub
		return op_ledDao;
	}
}
