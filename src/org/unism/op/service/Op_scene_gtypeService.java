package org.unism.op.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_scene_gtypeDao;
import org.unism.op.domain.Op_scene_gtype;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Op_scene_gtypeService extends BaseService<Op_scene_gtype, String> {

	@Autowired Op_scene_gtypeDao dao;
	
	@Override
	protected IBaseDao<Op_scene_gtype, String> getEntityDao() {
		return dao;
	}

}
