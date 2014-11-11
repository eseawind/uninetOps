package org.unism.op.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_led_sceneDao;
import org.unism.op.domain.Op_led;
import org.unism.op.domain.Op_led_scene;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class Op_led_sceneService extends BaseService<Op_led_scene, String> {
	@Autowired Op_led_sceneDao op_led_sceneDao;

	@Override
	protected IBaseDao<Op_led_scene, String> getEntityDao()
	{
		// TODO Auto-generated method stub
		return op_led_sceneDao;
	}
}
