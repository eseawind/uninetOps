package org.unism.phone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.phone.dao.TblSendSMSDao;
import org.unism.phone.domain.TblSendSMS;
import org.unism.pro.domain.Pro_Fisheries;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class TblSendSMSService extends BaseService<TblSendSMS, Integer> {

	@Autowired TblSendSMSDao tblSendSMSDao;
		
	@Override
	protected IBaseDao<TblSendSMS, Integer> getEntityDao() {
		return tblSendSMSDao;
	}

}
