package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_RoleInfoDao;
import org.unism.op.dao.Op_RoleRegithDao;
import org.unism.op.dao.Op_SysFunDao;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_RoleRegith;
import org.unism.op.domain.Op_SysFun;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
@SuppressWarnings("unchecked")
public class Op_SysFunService extends BaseService<Op_SysFun, String> {
	
	@Autowired Op_SysFunDao op_SysFunDao;
	@Autowired Op_RoleInfoDao op_RoleInfoDao;
	@Autowired Op_RoleRegithDao op_RoleRegithDao;
	
	@Override
	protected IBaseDao<Op_SysFun, String> getEntityDao() {
		return op_SysFunDao;
	}
	
	/**
	 * 根据场景父ID, 查询菜单
	 * @param node_pid
	 * @return
	 */
	public List<Op_SysFun> findByNode_pid(String node_pid){
		Search search = new Search();
		Filter filter = Filter.equal("node_pid", node_pid);
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 根据角色信息 生成菜单树 json
	 * @author Wang_Yuliang
	 * @return
	 */
	public String findMenuTree(Op_RoleInfo op_RoleInfo){
		String menu_tree = "[";
		String role_id = op_RoleInfo.getRole_id();
		List<Op_RoleRegith> op_RoleRegith_list = this.op_RoleRegithDao.findByRole_idOrderByNode_id(role_id);
		if(op_RoleRegith_list.size()>0){
			for(Op_RoleRegith op_RoleRegith : op_RoleRegith_list){
				Op_SysFun op_SysFun = op_RoleRegith.getOpSysFun();
				if(op_SysFun.getNode_pid() == null){
					menu_tree += "{id:\""+op_SysFun.getNode_id()+"\",pid:\"0\",name:\""+op_SysFun.getNode_displayName()+"\",url:\""+op_SysFun.getNode_url()+"\",target:\"centerFrame\"},";
				}else{
					menu_tree += "{id:\""+op_SysFun.getNode_id()+"\",pid:\""+op_SysFun.getNode_pid().getNode_id()+"\",name:\""+op_SysFun.getNode_displayName()+"\",url:\""+op_SysFun.getNode_url()+"\",target:\"centerFrame\"},";
				}
			}
			menu_tree = menu_tree.substring(0,menu_tree.length()-1);
		}
		return menu_tree + "]";
	}
	
	/**
	 * 输入节点ID 返回rootID
	 * @param node_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findRoot(String node_id){
		Op_SysFun op_SysFun = this.op_SysFunDao.findById(node_id);
		if(op_SysFun.getNode_pid() != null){
			node_id = findRoot(op_SysFun.getNode_pid().getNode_id());
		}
		return node_id;
	}
}
