package org.unism.op.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_scene_gtype;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Op_scene_gtypeDao extends HibernateBaseDao<Op_scene_gtype, String> {

	public Map<String, Integer> getAll() {
		List<Op_scene_gtype> list = this.findAll();
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (Op_scene_gtype op_scene_gtype : list) {
			map.put(op_scene_gtype.getName(), op_scene_gtype.getGtype());
		}
		return map;
	}
}
