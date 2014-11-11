package org.unism.op.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.op.dao.Op_SceneDao;
import org.unism.op.dao.Op_UserInfoDao;
import org.unism.op.dao.Op_UserInfo_SceneDao;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
@SuppressWarnings("unchecked")
public class Op_UserInfo_SceneService extends BaseService<Op_UserInfo_Scene, String>{

	@Autowired Op_UserInfo_SceneDao op_UserInfo_SceneDao;
	@Autowired Op_UserInfoDao op_UserInfoDao;
	@Autowired Op_SceneDao op_SceneDao;
	
	@Override
	protected IBaseDao<Op_UserInfo_Scene, String> getEntityDao() {
		return op_UserInfo_SceneDao;
	}
	
	/**
	 * 据场景ID 查找场景_用户关联信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Op_UserInfo_Scene> findByScene_id(String scene_id){
		Search search = new Search();
		Filter filter = Filter.equal("scene_id.scene_id", scene_id);
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 通过用户ID 查找场景ID集合
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findScene_idByUser_id(String user_id){
		List<String> list = new ArrayList();
		//空集合会出错吗？
		list.add("-1");
		Search search = new Search();
		Filter filter = Filter.equal("user_id.user_id", user_id);
		search.addFilter(filter);
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.search(search);
		for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
			list.add(op_UserInfo_Scene.getScene_id().getScene_id());
		}
		return list;
	}
	
	/**
	 * 指定用户ID 场景ID 查看用户是否关联了此场景
	 * @param user_id
	 * @param scene_id
	 * @return boolean
	 */
	public boolean IsMappingByUser_idAndScene_id(String user_id, String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("user_id.user_id", user_id),Filter.equal("scene_id.scene_id", scene_id));
		search.addFilter(filter);
		List<Op_UserInfo_Scene> list = this.search(search);
		if(list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 指定用户ID 查询用户关联的第一个养殖池的场景ID
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 * 0702 弃用 王雨良 据王振智需求
	 * 0702 启用 王雨良 据王振智需求
	 */
	public String findByGtype1AndUser_id(String user_id){
		return this.op_UserInfo_SceneDao.findByGtype1AndUser_id(user_id);
	}

	/**
	 * 指定用户ID 查询用户关联的第一个最高等级的场景ID
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 * 0702 弃用 王雨良 据王振智需求
	 */
	public String findByRank0AndUser_id(String user_id){	
		return this.op_UserInfo_SceneDao.findByRank0AndUser_id(user_id);
	}
	
	/**
	 * 加载树
	 * @return json [
	 * 					{ id:1, pid:0, name:"Hunter's Home", url:"#",open:true,checked:true},
	 * 					...	
	 * 				]
	 * @author Wang_Yuliang
	 */
	public String loadTree(String user_id){
		return this.op_UserInfo_SceneDao.loadTree(user_id);
	}
	
	/**
	 * 管理
	 * @param user_id
	 * @param node_id_arr
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void manage(String user_id, String[] node_id_arr) throws Exception{
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.findAllEq("user_id.user_id", user_id);
		for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
			this.op_UserInfo_SceneDao.delete(op_UserInfo_Scene);
		}
		//Integer.parseInt("dad");
		Op_UserInfo op_UserInfo = this.op_UserInfoDao.findById(user_id);
		for(String scene_id : node_id_arr){
			Op_Scene op_Scene = this.op_SceneDao.findById(scene_id);
			Op_UserInfo_Scene op_UserInfo_Scene = new Op_UserInfo_Scene();
			op_UserInfo_Scene.setUser_id(op_UserInfo);
			op_UserInfo_Scene.setScene_id(op_Scene);
			this.op_UserInfo_SceneDao.save(op_UserInfo_Scene);
		}
	}

	/**
	 * 根据userid查询带经纬度的场景id
	 */
	public List<Op_Scene> findMapScenesByUserId(String userId) {
		return this.op_UserInfo_SceneDao.findMapSceneByUserId(userId);
	}
	
	/**
	 * 根据用户id查询此用户所配置的所有场景
	 * @param userId
	 * @return
	 */
	public List<Op_Scene> findSceneListByUserId(String userId) {
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.findAllEq("user_id.user_id", userId);
		List<Op_Scene> op_Scene_list = new ArrayList<Op_Scene>();
		for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
			if(op_UserInfo_Scene.getScene_id().getScene_state() == 1){
				op_Scene_list.add(op_UserInfo_Scene.getScene_id());
			}
		}
		Collections.sort(op_Scene_list, new Comparator<Op_Scene>() {
			//@Override
			public int compare(Op_Scene s1, Op_Scene s2) {
				Integer order1 = s1.getScene_order();
				Integer order2 = s2.getScene_order();
				if(order1 != null) {
					if(order2 != null) {
						if(order1 > order2)
							return 1;
						else return 0;
					}
					else return 0;
				} else if(order2 != null)
					return 1;
				return 1;
			}    
		}); 
		return op_Scene_list;
	}

	public void refresh() {
		this.op_SceneDao.getSessionFactory().getCache().evictEntityRegion(Op_Scene.class);
	}	
	
	/**
	 * 根据场景数组删除用户场景关联表数据
	 * @param scene_id_arr
	 * @return
	 */
	public void op_UserInfo_Scene_del(String[] scene_id_arr) throws Exception{
		for(String scene_id : scene_id_arr){
			List<Op_UserInfo_Scene> op_UserInfo_SceneList = this.op_UserInfo_SceneDao.findAllByScene(scene_id);
			if(op_UserInfo_SceneList!=null){
				for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_SceneList){
					if(op_UserInfo_Scene!=null)
						this.op_UserInfo_SceneDao.delete(op_UserInfo_Scene);
				}
			}			
		}
	}
}
