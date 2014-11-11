package org.unism.op.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;

@Repository
public class Op_UserInfo_SceneDao extends HibernateBaseDao<Op_UserInfo_Scene, String>{

	/**
	 * 通过用户ID 查找场景ID
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
	 * 指定用户ID 查询用户关联的第一个养殖池的场景ID
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 * 0702 弃用 王雨良 据王振智需求
	 * 0702 启用 王雨良 据王振智需求
	 */
	public String findByGtype1AndUser_id(String user_id){		
		String scene_id = null;
		try{
			String sql = "select c.scene_id from op_userinfo_scene as c where user_id='"+user_id+"' and (select s.scene_gtype from op_scene as s where s.scene_id=c.scene_id)=1";			
			SQLQuery query = getSession().createSQLQuery(sql);
			List<String> list = query.list();
			if(list.size()>0){
				if(list.get(0)!=null){
					scene_id = list.get(0)+"";
				}
			}
			//System.out.println(scene_id);
		}catch(Exception ex){ex.printStackTrace();return null;}
		return scene_id;
	}

	/**
	 * 指定用户ID 查询用户关联的第一个最高等级的场景ID
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 * 0702 弃用 王雨良 据王振智需求
	 */
	public String findByRank0AndUser_id(String user_id){		
		String scene_id = null;
		try{
			String sql = "select c.scene_id from op_userinfo_scene as c where c.user_id='"+user_id+"' and (select s.scene_rank from op_scene as s where s.scene_id=c.scene_id)=0";			
			SQLQuery query = getSession().createSQLQuery(sql);
			List<String> list = query.list();
			if(list.size()>0){
				if(list.get(0)!=null){
					scene_id = list.get(0)+"";
				}
			}
			//System.out.println(scene_id);
		}catch(Exception ex){ex.printStackTrace();return null;}
		return scene_id;
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
		try{
			String sql = "select s.scene_id,s.scene_pid,s.scene_name,(select s.scene_id in(select c.scene_id from op_userinfo_scene as c where user_id='"+user_id+"')) as checked from op_scene as s where s.scene_state=1 and (s.scene_pid<>'FF' or s.scene_pid is null) order by s.scene_id";			
			SQLQuery query = getSession().createSQLQuery(sql);
			List<Object[]> list = query.list();
			String json = "[";
			if(list.size()>0){
				for(Object[] row : list){
					if(row!=null){
						String pid = null;
						if(row[1]!=null){
							pid = String.valueOf(row[1]);
						}else{
							pid = "0";
						}
						json += "{id:'"+row[0]+"', pId:'"+pid+"', name:'"+row[2]+"', url:'#',open:true,checked:"+row[3]+",target:'_self'},";
					}
				}
			}
			if(json.length()>1){
				json = json.substring(0,(json.length()-1));
			}
//			System.out.println(json+"]");
			return json+"]";
		}catch(Exception ex){ex.printStackTrace();return "[]";}
	}

	
	/**
	 * 根据用户id查询出此用户下所管理的最小场景，视频点，气象站
	 */
	@SuppressWarnings("unchecked")
	public List<Op_Scene> findMapSceneByUserId(String userId) {
		String hql = "select s from op_scene s inner join op_userinfo_scene us where s.scene_gtype=1 and s.scene_gtype=97 and s.scene_gtype=98 and us.user_id.user_id=?";
		return this.findByHql(hql, userId);
	}

	public void deleteByScene(Op_Scene scene) {
		Search search = new Search();
		search.addFilterEqual("scene_id", scene);
		List<Op_UserInfo_Scene> userInfo_Scenes = this.search(search);
		for (Op_UserInfo_Scene op_UserInfo_Scene : userInfo_Scenes) {
			this.delete(op_UserInfo_Scene);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Op_UserInfo_Scene> findAllByUserAndScene(Op_UserInfo op_UserInfo, Op_Scene scene) {
		Search search = new Search();
		search.addFilterEqual("scene_id", scene);
		search.addFilterEqual("user_id", op_UserInfo);
		return this.search(search);
	}	
	
	/**
	 * 根据场景id查询用户场景关联表
	 */
	public List<Op_UserInfo_Scene> findAllByScene(String scene_id) {
		Search search = new Search();
		Filter filter = Filter.equal("scene_id.scene_id", scene_id);
		search.addFilter(filter);
		return this.search(search);
	}	
}
