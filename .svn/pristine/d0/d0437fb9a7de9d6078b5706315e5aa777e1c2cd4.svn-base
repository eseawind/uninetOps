package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_AlarmargumentDao;
import org.unism.op.domain.Op_Alarmargument;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Op_AlarmargumentService extends BaseService<Op_Alarmargument, String> {

	@Autowired Op_AlarmargumentDao alarmargumentDao;
	
	@Override
	protected IBaseDao<Op_Alarmargument, String> getEntityDao() {
		return alarmargumentDao;
	}

	@SuppressWarnings("unchecked")
	public List<Op_Alarmargument> findBySceneId(String scene_id) {
		Search search = new Search();
		search.addFilterEqual("scene.scene_id", scene_id);
		return alarmargumentDao.search(search);
	}

	public void deleteBySceneId(String sceneId) {
		alarmargumentDao.deleteBySceneId(sceneId);
	}

}
