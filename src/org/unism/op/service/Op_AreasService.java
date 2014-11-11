package org.unism.op.service;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_AreasDao;
import org.unism.op.domain.Op_Areas;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Op_AreasService extends BaseService<Op_Areas, String> {
	@Autowired Op_AreasDao op_AreasDao;

	@Override
	protected IBaseDao<Op_Areas, String> getEntityDao() {
		// TODO Auto-generated method stub
		return op_AreasDao;
	}
	
	/**
	 * 查找省级区划 
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findSheng(){
		return this.op_AreasDao.findSheng();
	}
	
	/**
	 * 查询市级区划 据省级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findShiBySheng(String area_id){		
		return this.op_AreasDao.findShiBySheng(area_id);
	}
	
	/**
	 * 查询县级级区划 据省级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findXianBySheng(String area_id){		
		return this.op_AreasDao.findXianBySheng(area_id);
	}
	
	/**
	 * 查询县级区划 据市级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findXianByShi(String area_id){		
		return this.op_AreasDao.findXianByShi(area_id);
	}
}
