package org.unism.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_DevCtrlTypeDao;
import org.unism.op.domain.Op_DevCtrlType;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;
@Service
public class Op_DevCtrlTypeService extends BaseService<Op_DevCtrlType, String> {
	@Autowired Op_DevCtrlTypeDao op_DevCtrlTypeDao;

	@Override
	protected IBaseDao<Op_DevCtrlType, String> getEntityDao() {
		// TODO Auto-generated method stub
		return op_DevCtrlTypeDao;
	}

	public Op_DevCtrlType findBydecttype_no(String decttype_no)
	{
		return this.op_DevCtrlTypeDao.findBydecttype_no(decttype_no);
	}
}
