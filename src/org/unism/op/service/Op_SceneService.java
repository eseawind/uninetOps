package org.unism.op.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.op.dao.Op_SceneDao;
import org.unism.op.dao.Op_UserInfo_SceneDao;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
import org.wangzz.core.service.ServiceException;

@Service
public class Op_SceneService extends BaseService<Op_Scene, String> {
	
	@Autowired Op_SceneDao op_SceneDao;
	@Autowired Op_UserInfo_SceneDao op_UserInfo_SceneDao;
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Gm_DevCtrlService gm_DevCtrlService; 
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	
	@Override
	protected IBaseDao<Op_Scene, String> getEntityDao() {
		return op_SceneDao;
	}
	
	/**
	 * 验证该场景是否已存在
	 * @param scene_no
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isExist(String scene_no){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_no", scene_no),Filter.equal("scene_state", 1));
		search.addFilter(filter);
		List<Op_Scene> list = this.search(search);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 添加（附 图片）
	 * @param op_Scene
	 * @param inputStream
	 * @param outputStream
	 * @param fileName
	 * @throws ServiceException
	 * @throws IOException
	 * @author Wang_Yuliang 
	 * Wang_Yuliang 不用了 2011-04-04
	 */
	@Transactional
	public void save1(Op_Scene op_Scene, InputStream inputStream, OutputStream outputStream, String fileName) throws ServiceException, IOException{
		//上载图片
		int i = -1;
		while ((i = inputStream.read()) != -1) {
			outputStream.write(i);
		}
		inputStream.close();
		outputStream.flush();
		outputStream.close();
		op_Scene.setScene_image(fileName);
		this.op_SceneDao.save(op_Scene);
	}
	
	/**
	 * 添加
	 * @param op_Scene
	 * @author Wang_Yuliang
	 */
	public void save2(Op_Scene op_Scene){
		this.op_SceneDao.save(op_Scene);
	}
	
	/**
	 * 更新
	 * @param op_Scene
	 * @author Wang_Yuliang
	 */
	public void update(Op_Scene op_Scene){
		this.op_SceneDao.update(op_Scene);
	}
	
//	/**
//	 * 查询 不是最低级的场景
//	 * @return
//	 * @author Wang_Yuliang
//	 */
//	public List<Op_Scene> findByScene_rankNoEq0(){
//		Search search = new Search();
//		Filter filter = Filter.notEqual("scene_rank", 0);
//		search.addFilter(filter);
//		return this.search(search);
//	}
	
	/**
	 * 根据场景父ID, 查询场景 删除验证用，谁的子场景也不能删呀！
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Op_Scene> findByScene_pid(String scene_pid){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_pid", scene_pid),Filter.equal("scene_state", 1));
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 场景树
	 * @param op_UserInfo
	 * @return
	 * @author Wnag_Yuliang
	 */
	public String findSceneTree(Op_UserInfo op_UserInfo){
		String scene_tree = "[";
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.op_UserInfo_SceneDao.findAllEq("user_id.user_id", op_UserInfo.getUser_id());
		List<Op_Scene> op_Scene_list = new ArrayList<Op_Scene>();
		for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
			if(op_UserInfo_Scene.getScene_id().getScene_state() == 1){
				op_Scene_list.add(op_UserInfo_Scene.getScene_id());
			}
		}
		if(op_Scene_list.size()>0){
			for(Op_Scene op_Scene : op_Scene_list){
				//....
				String url = "javascript:document.getElementById('centerFrame').contentWindow.echoSceneTree('"+op_Scene.getScene_id()+"','"+op_Scene.getScene_name()+"','"+op_Scene.getScene_no()+"','" + op_Scene.getScene_rank()+"','" + op_Scene.getScene_gtype() + "','"+op_Scene.getScene_videoUrl()+"');";
				if(op_Scene.getScene_gtype() == 97){
					url = op_Scene.getScene_videoUrl();
				}
				//....唉 623蛋疼需求
				if(op_Scene.getScene_pid() == null){
					scene_tree += "{type:\"scene\",node:\"s_" + op_Scene.getScene_id() + "\",pnode:\"0\",text:\"" + op_Scene.getScene_name() + "\",url:\""+url+"\",scene_id:\""+op_Scene.getScene_id()+"\",scene_name:\""+op_Scene.getScene_name()+"\",scene_no:\""+op_Scene.getScene_no()+"\",scene_ctype:\""+op_Scene.getScene_ctype()+"\",scene_rank:\"" + op_Scene.getScene_rank() + "\",scene_gtype:\""+op_Scene.getScene_gtype()+"\"},";
				}else{
					scene_tree += "{type:\"scene\",node:\"s_" + op_Scene.getScene_id() + "\",pnode:\"s_" + op_Scene.getScene_pid() + "\",text:\"" + op_Scene.getScene_name() + "\",url:\""+url+"\",scene_id:\""+op_Scene.getScene_id()+"\",scene_name:\""+op_Scene.getScene_name()+"\",scene_no:\""+op_Scene.getScene_no()+"\",scene_ctype:\""+op_Scene.getScene_ctype()+"\",scene_rank:\"" + op_Scene.getScene_rank() + "\",scene_gtype:\""+op_Scene.getScene_gtype()+"\"},";
				}
//				List<Gm_Channel> gm_channel_list = this.gm_ChannelService.findAllEq("scene_id.scene_id", op_Scene.getScene_id());
				//据魏小华 0622需求更改 不要再查通道和控制设备了  --begin
				//for(Gm_Channel gm_Channel : gm_channel_list){
				//	scene_tree += "{type:\"channel\",node:\"c_" + gm_Channel.getCh_id() + "\",pnode:\"s_" + gm_Channel.getScene_id().getScene_id() + "\",text:\"" + gm_Channel.getCh_name() + "\",url:\"#\",scene_ctype:\"-1\",scene_rank:\"-1\"},";
				//}
				//List<Gm_DevCtrl> gm_devCtrl_list = this.gm_DevCtrlService.findAllEq("scene_id.scene_id", op_Scene.getScene_id());
				//for(Gm_DevCtrl gm_DevCtrl : gm_devCtrl_list){
				//	scene_tree += "{type:\"devctrl\",node:\"t_" + gm_DevCtrl.getDect_id() + "\",pnode:\"s_" + gm_DevCtrl.getScene_id().getScene_id() + "\",text:\"" + gm_DevCtrl.getDect_name() + "\",url:\"#\",scene_ctype:\"-1\",scene_rank:\"-1\"},";
				//}				
				//--end
			}
			scene_tree = scene_tree.substring(0,scene_tree.length()-1);
		}
		return scene_tree + "]";		
	}
	
	/**
	 * 查找父场景编号
	 * @param scene_pid
	 * @return
	 */
	public String findScene_pno(String scene_pid){
		return this.op_SceneDao.findScene_pno(scene_pid);
	}
	
	/**
	 * 查找父场景名称
	 * @param scene_pid
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findScene_pname(String scene_pid){
		return this.op_SceneDao.findScene_pname(scene_pid);
	}
	
	/**
	 * 指定用户，场景 查询同子类在用下一级场景
	 * @param op_Scene
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 * 0715 UP Wangg_Yuliang 据王振智需求
	 */
	public List<Op_Scene> findChildList(Op_Scene op_Scene, String user_id){
		Search search = new Search();
		//0715 UP Wangg_Yuliang 据王振智需求 begin Filter filter = Filter.and(Filter.in("scene_id",this.op_UserInfo_SceneDao.findScene_idByUser_id(user_id)),Filter.equal("scene_ctype", op_Scene.getScene_ctype()),Filter.equal("scene_rank", (op_Scene.getScene_rank()+1)),Filter.equal("scene_pid", "FF"),Filter.equal("scene_state", 1));
		//不在验证子类和所属等级了
		Filter filter = Filter.and(Filter.in("scene_id",this.op_UserInfo_SceneDao.findScene_idByUser_id(user_id)),Filter.equal("scene_pid", "FF"),Filter.equal("scene_state", 1));
		//--end
		search.addFilter(filter);		
		return this.search(search);
	}	
	
	/**
	 * 指定用户，场景 查询同子类在用子场景
	 * @param op_scene
	 * @param user_id
	 * @return
	 */
	public List<Op_Scene> findChildedList(Op_Scene op_Scene, String user_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.in("scene_id",this.op_UserInfo_SceneDao.findScene_idByUser_id(user_id)),Filter.equal("scene_state", 1),Filter.equal("scene_pid", op_Scene.getScene_id()));
		search.addFilter(filter);		
		return this.search(search);
	}
	
	/**
	 * 根据场景ID集合 返回所有相关场景ID集合 同级以场景编号排序
	 * @param DEV_ID_ARR
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findSceneTree_wangyuliang(List<String> scene_id_arr){		
		return this.op_SceneDao.findSceneTree_wangyuliang(scene_id_arr);
	}
	
	/**
	 * 根据场景ID集合 返回所有相关场景ID集合 同级以场景编号排序 (限定scene_id范围，的通道用回与用户相关的场景树向下查询)
	 * @param DEV_ID_ARR
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findSceneTree_wangyuliang(List<String> scene_id_arr, List<String> scene_id_list){		
		return this.op_SceneDao.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
	}
	
	public List<Op_Scene> findOp_Scene()
	{
		return this.op_SceneDao.findOp_Scene();
	}
	
	/**
	 * 根据父场景名称查询场景ID集合
	 * @param pname
	 * @author Wang_Yuliang
	 */
	public List<String> findScene_id_arrByPname(String pname){
		return this.op_SceneDao.findScene_id_arrByPname(pname);
	}
	
	/**
	 * 指定场景ID 删除用户场景关联信息 更新场景使用状态
	 * @param scene_id
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void deleteById_this(String scene_id) throws Exception{
		List<Op_UserInfo_Scene> op_UserInfo_Scene_list = this.op_UserInfo_SceneService.findByScene_id(scene_id);
		for(Op_UserInfo_Scene op_UserInfo_Scene : op_UserInfo_Scene_list){
			this.op_UserInfo_SceneDao.delete(op_UserInfo_Scene);
		}
		Op_Scene op_Scene = this.findById(scene_id);
		op_Scene.setScene_state(0);
		this.update(op_Scene);
	}
	
	/**
	 * 根据场景列表生成场景树
	 * @param list 场景列表
	 * @return 场景树json
	 */
	public String generateSceneTree(List<Op_Scene> list) {
		String scene_tree = "[";
		if(list.size() > 0){
			for(Op_Scene op_Scene : list){
				String url = "javascript:window.parent.document.getElementById('centerFrame').contentWindow.echoSceneTree('"+op_Scene.getScene_id()+"','"+op_Scene.getScene_name()+"','"+op_Scene.getScene_no()+"','" + op_Scene.getScene_rank()+"','" + op_Scene.getScene_gtype() + "','"+op_Scene.getScene_videoUrl()+"');";
				if(op_Scene.getScene_gtype() == 97){
					url = op_Scene.getScene_videoUrl();
				}
				if(op_Scene.getScene_pid() == null){
					scene_tree += "{type:\"scene\",node:\"s_" + op_Scene.getScene_id() + "\",pnode:\"0\",text:\"" + op_Scene.getScene_name() + "\",url:\""+url+"\",scene_id:\""+op_Scene.getScene_id()+"\",scene_name:\""+op_Scene.getScene_name()+"\",scene_no:\""+op_Scene.getScene_no()+"\",scene_ctype:\""+op_Scene.getScene_ctype()+"\",scene_rank:\"" + op_Scene.getScene_rank() + "\",scene_gtype:\""+op_Scene.getScene_gtype()+"\"},";
				}else{
					scene_tree += "{type:\"scene\",node:\"s_" + op_Scene.getScene_id() + "\",pnode:\"s_" + op_Scene.getScene_pid() + "\",text:\"" + op_Scene.getScene_name() + "\",url:\""+url+"\",scene_id:\""+op_Scene.getScene_id()+"\",scene_name:\""+op_Scene.getScene_name()+"\",scene_no:\""+op_Scene.getScene_no()+"\",scene_ctype:\""+op_Scene.getScene_ctype()+"\",scene_rank:\"" + op_Scene.getScene_rank() + "\",scene_gtype:\""+op_Scene.getScene_gtype()+"\"},";
				}
			}
			scene_tree = scene_tree.substring(0,scene_tree.length()-1);
		}
		return scene_tree + "]";		
	}
	
	/**
	 * 根据场景父ID, 查询场景,以场景编号排序 (限定scene_id范围，的通道用回与用户相关的场景树向下查询)
	 * @param scene_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Op_Scene> findByScene_pid(String scene_pid,List<String> scene_id_list){
		return this.op_SceneDao.findByScene_pid(scene_pid, scene_id_list);
	}

	public List<String> findLikeSceneName(String queryValue)
	{
		return this.op_SceneDao.findLikeSceneName(queryValue);
	}
}
