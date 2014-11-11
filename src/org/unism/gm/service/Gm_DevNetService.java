package org.unism.gm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.dao.Gm_DevNetDao;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Devsts;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class Gm_DevNetService extends BaseService<Gm_DevNet, String>{
	@Autowired Gm_DevNetDao gm_DevNetDao;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_DevstsService gm_DevstsService;
	@Autowired Gm_DevChannelService gm_DevChannelService;
	@Override
	protected IBaseDao<Gm_DevNet, String> getEntityDao() {
		return gm_DevNetDao;
	}
	
	/**
	 * 通过用户ID 查找设备ID集合
	 * @param user_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findNet_idByUser_id(String user_id){
		List<String> dev_id_list = this.gm_DeviceService.findDev_idByUser_id(user_id);		
		List<String> list = new ArrayList();
		//空集合会出错吗？
		list.add("-1");
		Search search = new Search();
		Filter filter = Filter.in("dev_id.dev_id", dev_id_list);
		search.addFilter(filter);
		List<Gm_DevNet> gm_devNet_list = this.search(search);
		for(Gm_DevNet gm_devNet : gm_devNet_list){
			list.add(gm_devNet.getNet_id());
		}
		return list;	
	}
	
	/**
	 * 验证网络节点是否已存在
	 * @param gm_DevNet
	 * @return
	 * @author Wang_Yuliang
	 */
	public boolean isExist(Gm_DevNet gm_DevNet){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("net_state", 1),Filter.equal("net_no", gm_DevNet.getNet_no()));
		search.addFilter(filter);
		List lst = this.search(search);
		if(lst.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * M2M 远程网 节点地址是否已存在
	 * @param net_addr
	 * @return
	 * @
	 */
	public boolean addIsUq(String net_addr){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("net_state", 1),Filter.equal("net_type", 0),Filter.equal("net_addr", net_addr));
		search.addFilter(filter);
		List lst = this.search(search);
		if(lst.size() > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 指定网络节点ID 返回根节点ID
	 * @param net_addr
	 * @return
	 * @author Wang_Yuliang
	 * 注 如果net_pid为FF 则返回null
	 */
	public String findRootByNet_id(String net_id){
		if(net_id.equals("FF")){return null;}
		Gm_DevNet gm_DevNet = this.gm_DevNetDao.findById(net_id);
		if(gm_DevNet.getNet_pid() != null){
			net_id = gm_DevNet.getNet_pid();
			net_id = findRootByNet_id(net_id);
		}
		return net_id;
	}
	
	/**
	 * 指定网络信息，场景ID集合 查询与用户相关的场景中的设备关联的 在用的 下一级网络信息
	 * @return json
	 * 			[
	 * 				{net_id:'?',net_no:'?',net_addr:'?'},
	 * 				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String findChildList(Gm_DevNet gm_DevNet, List<String> scene_id_list){
		return this.gm_DevNetDao.findChildList(gm_DevNet, scene_id_list);
	}
	
	/**
	 * 指定网络信息，场景ID集合 查询与用户相关的场景中的设备关联的 在用的 子网络信息
	 * @return json
	 * 			[
	 * 				{net_id:'?',net_no:'?',net_addr:'?'},
	 * 				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String findChildedList(Gm_DevNet gm_DevNet, List<String> scene_id_list){
		return this.gm_DevNetDao.findChildedList(gm_DevNet, scene_id_list);
	}
	
	/**
	 * 指定网络节点信息集合，含一元素，即M2M节点ID 返回所有节点ID
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDeviceTreeByNet_id(List<String> net_id_arr){
		String net_id = net_id_arr.get(net_id_arr.size()-1);
		List<Gm_DevNet> child_list = this.findByNet_pid(net_id);
		for(Gm_DevNet gm_DevNet : child_list){
			net_id_arr.add(gm_DevNet.getNet_id());
			net_id_arr = findDeviceTreeByNet_id(net_id_arr);
		}
		return net_id_arr;
	}
	
	/**
	 * 指定网络节点信息集合，含一元素，即M2M节点信息 返回所有节点信息 (不能在循环里写查询)
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevNet> findDeviceTreeByDevNet(List<Gm_DevNet> gm_DevNet_arr, List<Gm_DevNet> gm_DevNet_child_list){
		Gm_DevNet gm_DevNet = gm_DevNet_arr.get(gm_DevNet_arr.size()-1);
		List<Gm_DevNet> child_list = this.findByPDevNet(gm_DevNet, gm_DevNet_child_list);
		for(Gm_DevNet child_gm_DevNet : child_list){
			gm_DevNet_arr.add(child_gm_DevNet);
			gm_DevNet_arr = findDeviceTreeByDevNet(gm_DevNet_arr, gm_DevNet_child_list);
		}
		return gm_DevNet_arr;
	}
	
	/**
	 * 指定网络节点信息 查询在用子节点信息 (不能在循环里写查询)
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevNet> findByPDevNet(Gm_DevNet gm_DevNet, List<Gm_DevNet> gm_DevNet_child_list){		
		List<Gm_DevNet> list = new ArrayList<Gm_DevNet>();
		for(Gm_DevNet gm_DevNet_child : gm_DevNet_child_list){
			if(gm_DevNet!=null && gm_DevNet.getNet_id()!=null){
				if(gm_DevNet.getNet_id().equals(gm_DevNet_child.getNet_pid())){
					list.add(gm_DevNet_child);
				}
			}
		}		
		return list;
	}
	
	/**
	 * 指定网络节点信息集合，含一元素，即M2M节点ID 返回所有节点ID (与当前用相关的)
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<String> findDeviceTreeByNet_id(List<String> net_id_arr, List<String> net_id_list){
		String net_id = net_id_arr.get(net_id_arr.size()-1);
		List<Gm_DevNet> child_list = this.findByNet_pid(net_id, net_id_list);
		for(Gm_DevNet gm_DevNet : child_list){
			net_id_arr.add(gm_DevNet.getNet_id());
			net_id_arr = findDeviceTreeByNet_id(net_id_arr);
		}
		return net_id_arr;
	}
	
	/**
	 * 指定网络节点ID 查询在用子节点ID 同级节点以网络地址排序
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevNet> findByNet_pid(String net_pid){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("net_state", 1),Filter.equal("net_pid", net_pid));
		search.addFilter(filter);
		search.addSortAsc("net_addr");
		return this.search(search);
	}
	
	/**
	 * 指定网络节点ID 查询在用子节点ID 同级节点以网络地址排序 (与当前用相关的)
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevNet> findByNet_pid(String net_pid, List<String> net_id_list){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("net_state", 1),Filter.equal("net_pid", net_pid),Filter.in("net_id", net_id_list));
		search.addFilter(filter);
		search.addSortAsc("net_addr");
		return this.search(search);
	}
	
	/**
	 * 指定设备ID 查询网络节点信息
	 * @param dev_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public Gm_DevNet findByDev_id(String dev_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("net_state", 1),Filter.equal("dev_id.dev_id", dev_id));
		search.addFilter(filter);
		List<Gm_DevNet> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查找父网络编号
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findNet_pno(String net_pid){
		return this.gm_DevNetDao.findNet_pno(net_pid);
	}

	public Gm_DevNet findByNet_addr(String net_addr)
	{
		return this.gm_DevNetDao.findByNet_addr(net_addr);
	}
	
	/**
	 * 删除
	 * @throws Exception
	 */
	@Transactional
	public void delete_wangyuliang(Gm_DevNet gm_DevNet) throws Exception{
		gm_DevNet.setNet_state(0);
		this.update(gm_DevNet);
		Gm_Devsts gm_Devsts = this.gm_DevstsService.findByDev_id(gm_DevNet.getDev_id().getDev_id());
		if(gm_Devsts != null){
			this.gm_DevstsService.delete(gm_Devsts);
		}
		List<Gm_DevChannel> gm_DevChannel_list = this.gm_DevChannelService.findByDev_id(gm_DevNet.getDev_id().getDev_id());
		for(Gm_DevChannel gm_DevChannel : gm_DevChannel_list){
			this.gm_DevChannelService.delete(gm_DevChannel);
		}
	}
	
	/**
	 * 添加M2M节点
	 * @param gm_DevNet
	 * @param dest_id
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void saveM2M(Gm_DevNet gm_DevNet) throws Exception{
		gm_DevNet.setDev_id(this.gm_DeviceService.findById(gm_DevNet.getDev_id().getDev_id()));
		gm_DevNet.setNet_no(gm_DevNet.getDev_id().getDev_no());
		this.save(gm_DevNet);
		Gm_Devsts gm_Devsts = this.gm_DevstsService.findByDev_addr(gm_DevNet.getNet_addr());
		if(gm_Devsts==null)gm_Devsts = new Gm_Devsts();
		gm_Devsts.setDev_id(gm_DevNet.getDev_id());
		gm_Devsts.setDev_addr(gm_DevNet.getNet_addr());
		gm_Devsts.setDest_regSts(0);
		this.gm_DevstsService.saveOrUpdate(gm_Devsts);
	}
	
	/**
	 * 添加WSN节点
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void saveWSN(Gm_DevNet gm_DevNet) throws Exception{
		gm_DevNet.setDev_id(this.gm_DeviceService.findById(gm_DevNet.getDev_id().getDev_id()));
		gm_DevNet.setNet_no(gm_DevNet.getDev_id().getDev_no());
		this.save(gm_DevNet);		
	}
	
	/**
	 * 编辑 M2M 改为 M2M的情况
	 * @param gm_DevNet
	 * @param dest_id
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void edit_M2M_M2M(Gm_DevNet gm_DevNet, String dest_id) throws Exception{
		//更新网络信息
//		获得原来的智能设备状态信息的ID  
//		获得原来的智能设备状态信息					
		//如果原智能设备状态信息中的设备地址==新网络信息中的网络地址
			//更新智能设备状态信息(注册身份不变)  返回
//		否则
//			删除原智能设备状态信息
//			删除与新网络信息地址等值的智能设备状态信息
//			添加智能设备状态信息（含关联设备，注册身份0，设备地址）
		this.update(gm_DevNet);
		Gm_Devsts gm_Devsts = this.gm_DevstsService.findById(dest_id);
		if(gm_Devsts.getDev_addr().equals(gm_DevNet.getNet_addr())){
			gm_Devsts.setDev_id(gm_DevNet.getDev_id());
			gm_Devsts.setDest_linkSts(gm_DevNet.getNet_linkSts());
			this.gm_DevstsService.update(gm_Devsts);
			return;
		}else{
			this.gm_DevstsService.delete(gm_Devsts);
			List<Gm_Devsts> gm_Devsts_list = this.gm_DevstsService.findAllEq("dev_addr", gm_DevNet.getNet_addr());
			for(Gm_Devsts gm_Devsts_del : gm_Devsts_list){
				this.gm_DevstsService.delete(gm_Devsts_del);
			}
			Gm_Devsts gm_Devsts_new = new Gm_Devsts();
			gm_Devsts_new.setDev_id(gm_DevNet.getDev_id());
			gm_Devsts_new.setDev_addr(gm_DevNet.getNet_addr());
			gm_Devsts_new.setDest_regSts(0);
			gm_Devsts.setDest_linkSts(gm_DevNet.getNet_linkSts());
			this.gm_DevstsService.save(gm_Devsts_new);
		}	
	}
	
	/**
	 * 编辑 M2M 改为WSN的情况
	 * @param gm_DevNet
	 * @param dest_id
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void edit_M2M_WSN(Gm_DevNet gm_DevNet, String dest_id) throws Exception{
		//Pid设置为FF 	
		//删除原来的智能设备状态信息
		//		 	 更新网络信息 返回
		gm_DevNet.setNet_pid("FF");
		this.gm_DevstsService.deleteById(dest_id);
		this.update(gm_DevNet);
	}
	
	/**
	 * 编辑 WSN 改为 M2M的情况
	 * @param gm_DevNet
	 * @param dest_id
	 * @author Wang_Yuliang
	 */
	@Transactional
	public void edit_WSN_M2M(Gm_DevNet gm_DevNet) throws Exception{
		//更新网络信息					
		this.update(gm_DevNet);
		//并查看
		//智能设备状态表中 有没有设备地址 与 所添加的网络信息的网络地址字段等值的记录。
		Gm_Devsts gm_Devsts_exist = this.gm_DevstsService.findByDev_addr(gm_DevNet.getNet_addr());
		//如果有
		if(gm_Devsts_exist != null){
			//则更新这条记录的关联设备、设备地址，并将注册身份改为0未注册。
			gm_Devsts_exist.setDev_id(gm_DevNet.getDev_id());
			gm_Devsts_exist.setDev_addr(gm_DevNet.getNet_addr());
			gm_Devsts_exist.setDest_regSts(0);
			gm_Devsts_exist.setDest_linkSts(gm_DevNet.getNet_linkSts());
			this.gm_DevstsService.update(gm_Devsts_exist);
		}
		else{
			//没有 则追加
			Gm_Devsts gm_Devsts_new = new Gm_Devsts();
			gm_Devsts_new.setDev_id(gm_DevNet.getDev_id());
			gm_Devsts_new.setDev_addr(gm_DevNet.getNet_addr());
			gm_Devsts_new.setDest_regSts(0);
			gm_Devsts_new.setDest_linkSts(gm_DevNet.getNet_linkSts());
			this.gm_DevstsService.save(gm_Devsts_new);
		}
	}

}
