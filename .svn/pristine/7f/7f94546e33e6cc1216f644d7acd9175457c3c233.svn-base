package org.unism.op.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_DevCtrlBtnDao;
import org.unism.op.domain.Op_DevCtrlBtn;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Op_DevCtrlBtnService extends BaseService<Op_DevCtrlBtn, String> {
	@Autowired Op_DevCtrlBtnDao op_DevCtrlBtnDao;

	@Override
	protected IBaseDao<Op_DevCtrlBtn, String> getEntityDao() {
		// TODO Auto-generated method stub
		return op_DevCtrlBtnDao;
	}
	
	/**
	 * 指定控制设备ID 查询控制按钮信息 根据操作类型排序
	 * @param dect_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Op_DevCtrlBtn> findByDect_id(String dect_id){
		Search search = new Search();
		Filter filter = Filter.equal("dect_id", dect_id);
		search.addFilter(filter);
		search.addSortAsc("deco_type");
		return this.search(search);		
	}
	
	/**
	 * 指定控制设备ID 查询控制按钮信息 根据操作类型排序
	 * @param dect_id
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String json_findByDect_id(String dect_id){
		try{
			Search search = new Search();
			Filter filter = Filter.equal("dect_id", dect_id);
			search.addFilter(filter);
			search.addSortAsc("deco_type");
			List<Op_DevCtrlBtn> list = this.search(search);
			String json = "[";
			if(list.size()>0){
				for(Op_DevCtrlBtn op_DevCtrlBtn : list){
					String dev_id = "null";
					if(op_DevCtrlBtn.getDev_id()!=null){
						dev_id = "'" + op_DevCtrlBtn.getDev_id().getDev_id() + "'";
					}
					json += "{dectbtn_id:'"+op_DevCtrlBtn.getDectbtn_id()+"',Dectbtn_Name:'"+op_DevCtrlBtn.getDectbtn_name()+"',deco_type:'"+op_DevCtrlBtn.getDeco_type()+"',dect_id:'"+op_DevCtrlBtn.getDect_id()+"',dev_id:"+dev_id+",dect_ChlNo:'"+op_DevCtrlBtn.getDect_chlNo()+"',dect_ctlType:'"+op_DevCtrlBtn.getDect_ctlType()+"',dect_ctlDelay:'"+op_DevCtrlBtn.getDect_ctlDelay()+"'},";
				}
				json = json.substring(0,json.length()-1);
			}
			//System.out.println(json + "]");
			return json+"]";
		}catch(Exception ex){
			ex.printStackTrace();return "[]";
		}
	}
}