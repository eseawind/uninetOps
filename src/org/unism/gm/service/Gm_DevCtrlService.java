package org.unism.gm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.unism.gm.action.form.DevCtrlBtnForm;
import org.unism.gm.action.form.DevCtrlStsForm;
import org.unism.gm.dao.Gm_DevCtrlDao;
import org.unism.gm.dao.Gm_DevCtrlOperateDao;
import org.unism.gm.dao.Gm_DevCtrlOperateHistoryDao;
import org.unism.gm.dao.Gm_DevCtrlStsDao;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevCtrlOperate;
import org.unism.gm.domain.Gm_DevCtrlOperateHistory;
import org.unism.gm.domain.Gm_DevCtrlSts;
import org.unism.op.dao.Op_DevCtrlBtnDao;
import org.unism.op.dao.Op_DevCtrlStsDao;
import org.unism.op.domain.Op_DevCtrlBtn;
import org.unism.op.domain.Op_DevCtrlSts;
import org.unism.op.service.Op_DevCtrlStsService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
@SuppressWarnings("unchecked")
public class Gm_DevCtrlService extends BaseService<Gm_DevCtrl, String> {
	
	@Autowired Gm_DevCtrlDao gm_DevCtrlDao;
	@Autowired Gm_DevCtrlOperateDao devCtrlOperateDao;
	@Autowired Gm_DevCtrlOperateHistoryDao devCtrlOperateHistoryDao;
	@Autowired Op_DevCtrlBtnDao devCtrlBtnDao;
	@Autowired Gm_DevCtrlStsDao gm_devCtrlStsDao;
	@Autowired Op_DevCtrlStsDao op_DevCtrlStsDao;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Op_DevCtrlStsService opDevCtrlStsService;
	@Autowired Op_DevCtrlStsDao opDevCtrlStsDao;

	@Override
	protected IBaseDao<Gm_DevCtrl, String> getEntityDao() {
		return gm_DevCtrlDao;
	}
	
	
	/**
	 * 据场景ID 查找在用控制设备
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevCtrl> findByScene_id(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_id.scene_id", scene_id),Filter.equal("dect_state", 1));
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 据场景ID 查找对外提供服务的在用控制设备
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevCtrl> findByScene_idAndCh_offerSer(String scene_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_id.scene_id", scene_id),Filter.equal("dect_state", 1),Filter.equal("ch_offerSer", 1));
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 通过用户ID 查找控制设备ID集合
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDect_idByUser_id(String user_id){
		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);		
		List<String> list = new ArrayList();
		//空集合会出错吗？
		list.add("-1");
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dect_state", 1),Filter.in("scene_id.scene_id", scene_id_list));
		search.addFilter(filter);
		List<Gm_DevCtrl> gm_devctrl_list = this.search(search);
		for(Gm_DevCtrl gm_devctrl : gm_devctrl_list){
			list.add(gm_devctrl.getDect_id());
		}		
		return list;	
	}
	
	/**
	 * 指定设备编号 模糊查询一组设备 ID
	 * @param dev_no
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDev_id_arrByDev_no(String dev_no){
		return this.gm_DevCtrlDao.findDev_id_arrByDev_no(dev_no);		
	}


	/**
	 * 删除控制设备<br>
	 * 删除控制设备操作历史,删除控制设备操作,删除控制设备状态配置,删除控制设备状态,删除控制设备按钮
	 */
	@Override
	public void deleteById(String id) {
		List<Gm_DevCtrlOperateHistory> ctrlOperateHistories = devCtrlOperateHistoryDao.findAllEq("dect_id.id", id);
		for(Gm_DevCtrlOperateHistory operateHistory : ctrlOperateHistories) {
			devCtrlOperateHistoryDao.delete(operateHistory);
		}
		
		Gm_DevCtrlOperate devCtrlOperate = devCtrlOperateDao.findUniqueByProperty("dect_id.id", id);
		if(devCtrlOperate!= null)
			devCtrlOperateDao.delete(devCtrlOperate);
		
		List<Op_DevCtrlBtn> devCtrlBtns = devCtrlBtnDao.findAllEq("dect_id", id);
		for(Op_DevCtrlBtn ctrlBtn : devCtrlBtns) {
			devCtrlBtnDao.delete(ctrlBtn);
		}
		
		List<Op_DevCtrlSts> opDevCtrlStsList = op_DevCtrlStsDao.findAllEq("dect_id.id", id);
		for(Op_DevCtrlSts opDevCtrlSts : opDevCtrlStsList) {
			op_DevCtrlStsDao.delete(opDevCtrlSts);
		}
		
		Gm_DevCtrlSts devCtrlSts = gm_devCtrlStsDao.findUniqueByProperty("dect_id.id", id);
		if(devCtrlSts!= null)
			gm_devCtrlStsDao.delete(devCtrlSts);
		
		super.deleteById(id);
		
	}

	/**
	 * 指定控制设备编号 模糊查询一组控制设备的ID
	 * @param dev_no
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> dect_id_by_desc_no(String queryName,String desc_no) {
		return this.gm_DevCtrlDao.dect_id_by_desc_no(queryName,desc_no);
	}
	
	/**
	 * 保存控制设备信息, 包括控制设备按钮信息, 控制设备操作信息 , 控制设备状态信息
	 * @param devCtrl 控制设备信息实体
	 * @param devCtrlBtnForm 控制设备按钮列表
	 */
	public void save(Gm_DevCtrl devCtrl, DevCtrlBtnForm devCtrlBtnForm, DevCtrlStsForm devCtrlStsForm) {
		int btnCount = devCtrlBtnForm.getBtnName().length;
		devCtrl.setDecttype_btnNum(btnCount);
		this.gm_DevCtrlDao.save(devCtrl);
		
		for(int i=0; i<=btnCount-1; i++) {
			Op_DevCtrlBtn devCtrlBtn = new Op_DevCtrlBtn();
			devCtrlBtn.setDect_id(devCtrl.getDect_id());
			devCtrlBtn.setDectbtn_name(devCtrlBtnForm.getBtnName()[i]);
			devCtrlBtn.setDeco_type(Integer.parseInt(devCtrlBtnForm.getBtnType()[i]));
			devCtrlBtn.setDect_chlNo(Integer.parseInt(devCtrlBtnForm.getBtnChl()[i]));
			devCtrlBtn.setDect_ctlType(Integer.parseInt(devCtrlBtnForm.getBtnCtrlType()[i]));
			devCtrlBtn.setDect_ctlDelay(Integer.parseInt(devCtrlBtnForm.getBtnDelay()[i]));
			devCtrlBtn.setDev_id(devCtrl.getDev_id());
			devCtrlBtnDao.save(devCtrlBtn);
		}
		
		Gm_DevCtrlOperate gm_DevCtrlOperate = new Gm_DevCtrlOperate();				
		gm_DevCtrlOperate.setDect_id(devCtrl);
		gm_DevCtrlOperate.setPla_id(null);
		gm_DevCtrlOperate.setDeco_state(0);
		gm_DevCtrlOperate.setDeco_result(0);
		this.devCtrlOperateDao.save(gm_DevCtrlOperate);
		
		Gm_DevCtrlSts gm_DevCtrlSts = new Gm_DevCtrlSts();
		gm_DevCtrlSts.setDect_id(devCtrl);
		gm_DevCtrlSts.setDecst_valid(1);
		gm_DevCtrlSts.setDect_state(2);
		this.gm_devCtrlStsDao.save(gm_DevCtrlSts);
		
		if(devCtrlStsForm != null){
			int dectStsNameCount = devCtrlStsForm.getDectStsName().length;
			for (int i = 0; i < dectStsNameCount; i++) {
				Op_DevCtrlSts opDevCtrlSts = new Op_DevCtrlSts();
				opDevCtrlSts.setDectSts_name(devCtrlStsForm.getDectStsName()[i]);
				opDevCtrlSts.setDect_state(Integer.parseInt(devCtrlStsForm.getDectState()[i]));
				opDevCtrlSts.setDect_id(devCtrl);
				opDevCtrlSts.setCh_id(devCtrlStsForm.getChannel(devCtrlStsForm.getChId()).get(i));
				if(StringUtils.hasText(devCtrlStsForm.getChMax()[i])){
					opDevCtrlSts.setCh_max(Float.parseFloat(devCtrlStsForm.getChMax()[i]));
				}
				if(StringUtils.hasText(devCtrlStsForm.getChMin()[i])){
					opDevCtrlSts.setCh_min(Float.parseFloat(devCtrlStsForm.getChMin()[i]));
				}

				opDevCtrlStsDao.save(opDevCtrlSts);
			}
		}
	}


	public void update(Gm_DevCtrl devCtrl, DevCtrlBtnForm devCtrlBtnForm, DevCtrlStsForm devCtrlStsForm) {
		int btnCount = devCtrlBtnForm.getBtnName().length;
		devCtrl.setDecttype_btnNum(btnCount);
		this.gm_DevCtrlDao.update(devCtrl);
		
		List<Op_DevCtrlBtn> devCtrlBtns = devCtrlBtnDao.findAllEq("dect_id", devCtrl.getDect_id());
		for(Op_DevCtrlBtn ctrlBtn : devCtrlBtns) {
			devCtrlBtnDao.delete(ctrlBtn);
		}
		
		for(int i=0; i<=btnCount-1; i++) {
			Op_DevCtrlBtn devCtrlBtn = new Op_DevCtrlBtn();
			devCtrlBtn.setDect_id(devCtrl.getDect_id());
			devCtrlBtn.setDectbtn_name(devCtrlBtnForm.getBtnName()[i]);
			devCtrlBtn.setDeco_type(Integer.parseInt(devCtrlBtnForm.getBtnType()[i]));
			devCtrlBtn.setDect_chlNo(Integer.parseInt(devCtrlBtnForm.getBtnChl()[i]));
			devCtrlBtn.setDect_ctlType(Integer.parseInt(devCtrlBtnForm.getBtnCtrlType()[i]));
			devCtrlBtn.setDect_ctlDelay(Integer.parseInt(devCtrlBtnForm.getBtnDelay()[i]));
			devCtrlBtn.setDev_id(devCtrl.getDev_id());
			devCtrlBtnDao.save(devCtrlBtn);
		}
		
		List<Op_DevCtrlSts> opDevCtrlStses = opDevCtrlStsDao.findAllEq("dect_id.dect_id", devCtrl.getDect_id());
		for (Op_DevCtrlSts opDevCtrlSts : opDevCtrlStses) {
			opDevCtrlStsDao.delete(opDevCtrlSts);
		}
		if(devCtrlStsForm != null){
			int dectStsNameCount = devCtrlStsForm.getDectStsName().length;
			for (int i = 0; i < dectStsNameCount; i++) {
				Op_DevCtrlSts opDevCtrlSts = new Op_DevCtrlSts();
				opDevCtrlSts.setDectSts_name(devCtrlStsForm.getDectStsName()[i]);
				opDevCtrlSts.setDect_state(Integer.parseInt(devCtrlStsForm.getDectState()[i]));
				opDevCtrlSts.setDect_id(devCtrl);
				opDevCtrlSts.setCh_id(devCtrlStsForm.getChannel(devCtrlStsForm.getChId()).get(i));
				if(StringUtils.hasText(devCtrlStsForm.getChMax()[i])){
					opDevCtrlSts.setCh_max(Float.parseFloat(devCtrlStsForm.getChMax()[i]));
				}
				if(StringUtils.hasText(devCtrlStsForm.getChMin()[i])){
					opDevCtrlSts.setCh_min(Float.parseFloat(devCtrlStsForm.getChMin()[i]));
				}

				opDevCtrlStsDao.save(opDevCtrlSts);
			}
		}
	}


	public List<String> findDect_idByScene_id(String scene_id)
	{
		// TODO Auto-generated method stub
		return this.gm_DevCtrlDao.findDect_idByScene_id(scene_id);
	}


	public List<String> findDectIdByDect_no(String queryValue,
			List<String> dectIdList)
	{
		return this.gm_DevCtrlDao.findDectIdByDect_no(queryValue,dectIdList);
	}


	public List<String> findDectIdByscene_idList(List<String> scene_idList,
			List<String> dectIdList)
	{
		return this.gm_DevCtrlDao.findDectIdByscene_idList(scene_idList,dectIdList);
	}


	public Gm_DevCtrl findByDectNo(String[] dectNo) {
		Search search = new Search();
		search.addFilterIn("dect_no", dectNo);
		List<Gm_DevCtrl> list = this.gm_DevCtrlDao.search(search);
		for (Gm_DevCtrl gmDevCtrl : list) {
			return gmDevCtrl;
		}
		return null;
	}



}
