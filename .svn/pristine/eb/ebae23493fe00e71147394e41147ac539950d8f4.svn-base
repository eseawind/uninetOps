package org.unism.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_SupplierDao;
import org.unism.op.domain.Op_Supplier;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;
@Service
public class Op_SupplierService extends BaseService<Op_Supplier,String> {
	@Autowired Op_SupplierDao op_SupplierDao;
	
	@Override
	protected IBaseDao<Op_Supplier,String> getEntityDao() {
		// TODO Auto-generated method stub
		return op_SupplierDao;
	}

}
