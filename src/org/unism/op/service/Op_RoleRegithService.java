package org.unism.op.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class Op_RoleRegithService extends BaseService<Op_RoleRegith, String> {
	@Autowired Op_RoleRegithDao op_RoleRegithDao;
	@Autowired Op_SysFunDao op_SysFunDao;
	@Override
	protected IBaseDao<Op_RoleRegith, String> getEntityDao() {
		// TODO Auto-generated method stub
		return op_RoleRegithDao;
	}
	
	/**
	 * 根据用户角色ID, 查询菜单
	 * @param node_pid
	 * @return
	 */
	public List<Op_RoleRegith> findNode_idByRole_id(Op_RoleInfo role_id){		
		Search search = new Search();
		Filter filter = Filter.equal("role_id.role_id",role_id.getRole_id());
		search.addFilter(filter);		
		return this.search(search);
	}
	/**
	 * 根据角色信息 生成菜单树 json
	 * @author Wang_Yuliang
	 * @return
	 */
	public String findMenuTree(){
		String menu_tree = "[";		
		List<Op_SysFun> op_SysFun_list = this.op_SysFunDao.findAll();		
		if(op_SysFun_list.size()>0){
			for(Op_SysFun op_SysFun : op_SysFun_list){
				if(op_SysFun.getNode_pid() == null){
					menu_tree += "{node:\""+op_SysFun.getNode_id()+"\",pnode:\"0\",text:\""+op_SysFun.getNode_displayName()+"\",url:\""+op_SysFun.getNode_url()+"\"},";
				}else{
					menu_tree += "{node:\""+op_SysFun.getNode_id()+"\",pnode:\""+op_SysFun.getNode_pid().getNode_id()+"\",text:\""+op_SysFun.getNode_displayName()+"\",url:\""+op_SysFun.getNode_url()+"\"},";
				}
			}
			menu_tree = menu_tree.substring(0,menu_tree.length()-1);
		}
		return menu_tree + "]";
	}
}
