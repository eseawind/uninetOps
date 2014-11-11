package org.unism.op.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_Channel;
import org.unism.op.domain.Op_Scene;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;

@Repository
public class Op_SceneDao extends HibernateBaseDao<Op_Scene, String>{

	
	
	/**
	 * 查找父场景编号
	 * @param scene_pid
	 * @return
	 */
	public String findScene_pno(String scene_pid){
		String sql = "select scene_no from op_scene where scene_id = '" + scene_pid + "' and scene_state = 1";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> lst = query.list();
		StringBuilder builder = new StringBuilder("[");
		if(lst!=null){
			return lst.get(0);
		}
		return null;
	}	
	
	/**
	 * 查找父场景名称
	 * @param scene_pid
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findScene_pname(String scene_pid){
		String sql = "select scene_name from op_scene where scene_id = '" + scene_pid + "' and scene_state = 1";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> lst = query.list();
		if(lst!=null && lst.size()>0){
			return lst.get(0);
		}
		return null;
	}
	
	/**
	 * 根据场景父ID, 查询场景,以场景编号排序
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Op_Scene> findByScene_pid(String scene_pid){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_pid", scene_pid),Filter.equal("scene_state", 1));
		search.addFilter(filter);
		search.addSortAsc("scene_no");
		return this.search(search);
	}
	
	/**
	 * 根据场景父ID, 查询场景,以场景编号排序 (限定scene_id范围，的通道用回与用户相关的场景树向下查询)
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Op_Scene> findByScene_pid(String scene_pid,List<String> scene_id_list){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_pid", scene_pid),Filter.equal("scene_state", 1),Filter.in("scene_id", scene_id_list));
		search.addFilter(filter);
		search.addSortAsc("scene_no");
		return this.search(search);
	}
	
	/**
	 * 根据场景ID集合 返回所有相关场景ID集合 同级以场景编号排序 
	 * @param DEV_ID_ARR
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findSceneTree_wangyuliang(List<String> scene_id_arr){		
		String scene_id = scene_id_arr.get(scene_id_arr.size()-1);
		List<Op_Scene> child_list = this.findByScene_pid(scene_id);
		for(Op_Scene op_Scene : child_list){
			if(child_list != null && child_list.size() > 0){
				scene_id_arr.add(op_Scene.getScene_id());
				scene_id_arr = findSceneTree_wangyuliang(scene_id_arr);
			}
		}
		return scene_id_arr;
	}
	
	/**
	 * 根据场景ID集合 返回所有相关场景ID集合 同级以场景编号排序 (限定scene_id范围，的通道用回与用户相关的场景树向下查询)
	 * @param DEV_ID_ARR
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findSceneTree_wangyuliang(List<String> scene_id_arr, List<String> scene_id_list){		
		String scene_id = scene_id_arr.get(scene_id_arr.size()-1);
		List<Op_Scene> child_list = this.findByScene_pid(scene_id, scene_id_list);
		for(Op_Scene op_Scene : child_list){
			if(child_list != null && child_list.size() > 0){
				scene_id_arr.add(op_Scene.getScene_id());
				scene_id_arr = findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			}
		}
		return scene_id_arr;
	}

	public List<Op_Scene> findOp_Scene()
	{
		String hql = "FROM Op_Scene WHERE scene_longitude is not null and scene_latitude is not null";
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 根据父场景名称查询场景ID集合
	 * @param pname
	 * @author Wang_Yuliang
	 */
	public List<String> findScene_id_arrByPname(String pname){
		String sql = "select scene_id from op_scene where scene_state=1 ";
		sql += "and scene_name like '%"+pname+"%'";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<String> list = query.list();
		list.add("-1");
		return list;
	}

	public List<String> findLikeSceneName(String queryValue)
	{
		String sql = "select scene_id from op_scene where scene_name like '%"+queryValue+"%'";
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		return sqlQuery.list();
	}
}
