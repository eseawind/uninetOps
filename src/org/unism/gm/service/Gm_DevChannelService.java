package org.unism.gm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.dao.Gm_ChannelDao;
import org.unism.gm.dao.Gm_DevChannelDao;
import org.unism.gm.dao.Gm_DevNetDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
@Service
public class Gm_DevChannelService extends BaseService<Gm_DevChannel, String> {
	
	@Autowired Gm_DevChannelDao gm_DevChannelDao;
	@Autowired Gm_DevNetDao gm_DevNetDao;
	@Autowired Gm_ChannelDao gm_ChannelDao;
	
	@Override
	protected IBaseDao<Gm_DevChannel, String> getEntityDao() {
		// TODO Auto-generated method stub
		return gm_DevChannelDao;
	}

	/**
	 * 指定设备ID 查询设备上报通道配置信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevChannel> findByDev_id(String dev_id){
		Search search = new Search();
		Filter filter = Filter.equal("dev_id.dev_id", dev_id);
		search.addFilter(filter);
		return this.search(search);
	}
	
	/**
	 * 指定设备地址 查询设备通道配置信息
	 * @param dev_addr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gm_DevChannel> findBydDev_addr(String dev_addr){
		Search search = new Search();
		Filter filter = Filter.equal("dev_addr", dev_addr);
		search.addFilter(filter);
		search.addSortAsc("dch_order");
		return this.search(search);
	}
	
	/**
	 * 指定通道ID 查询设备上报通道配置信息
	 * @param ch_id
	 * @return
	 * @author Wang_Yuliang
	 */
	public Gm_DevChannel findByCh_id(String ch_id){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("ch_id.ch_id", ch_id));
		search.addFilter(filter);
		List<Gm_DevChannel> list = this.search(search);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}


	/**
	 * 根据设备地址查ch_id，ch_name。
	 * @param net_addr
	 * @return
	 */
	public List<Object[]> findCh_idAndCh_nameByAddr(String net_addr)
	{
		return this.gm_DevChannelDao.findCh_idAndCh_nameByAddr(net_addr);
	}

	

	@Transactional
	public String manage(String net_id, String ch_id_arr, String uc_ch_id_arr, String dev_id_arr, String dch_order_arr, String ch_procesStyle_arr, String ch_memoryPeriod_arr) throws Exception{
		Gm_DevNet M2M = this.gm_DevNetDao.findById(net_id);
		if(M2M!=null){
			if(M2M.getNet_id()!=null){
				if(M2M.getNet_addr()!=null && M2M.getNet_state()==1){
					String[] ch_id_list = new String[0];
					if(ch_id_arr.length()>0)ch_id_list = ch_id_arr.split(",");
					String[] uc_ch_id_list = new String[0];
					if(uc_ch_id_arr.length()>0)uc_ch_id_list = uc_ch_id_arr.split(",");
					String[] dev_id_list = new String[0];
					if(dev_id_arr.length()>0)dev_id_list = dev_id_arr.split(",");
					String[] dch_order_list = new String[0];
					if(dch_order_arr.length()>0)dch_order_list = dch_order_arr.split(",");
					String[] ch_procesStyle_list = new String[0];
					if(ch_procesStyle_arr.length()>0)ch_procesStyle_list = ch_procesStyle_arr.split(",");
					String[] ch_memoryPeriod_list = new String[0];
					if(ch_memoryPeriod_arr.length()>0)ch_memoryPeriod_list = ch_memoryPeriod_arr.split(",");
					if(ch_id_list.length==dev_id_list.length && dev_id_list.length==dch_order_list.length && dch_order_list.length==dch_order_list.length && dch_order_list.length==ch_procesStyle_list.length && ch_procesStyle_list.length==ch_memoryPeriod_list.length){
						List<Gm_DevChannel> gm_DevChannel_list = new ArrayList<Gm_DevChannel>();
						List<Gm_Channel> gm_Channel_list = new ArrayList<Gm_Channel>();
						List<Gm_Channel> uc_gm_Channel_list = new ArrayList<Gm_Channel>();
						for(int i=0;i<ch_id_list.length;i++){
							String ch_id = ch_id_list[i];
							String dev_id = dev_id_list[i];
							String dch_order = dch_order_list[i];
							String ch_procesStyle = ch_procesStyle_list[i];
							String ch_memoryPeriod = ch_memoryPeriod_list[i];								
							Gm_DevChannel gm_DevChannel = new Gm_DevChannel();
							gm_DevChannel.setDev_addr(M2M.getNet_addr());
							gm_DevChannel.setDev_id(M2M.getDev_id());
							Gm_Channel gm_Channel = this.gm_ChannelDao.findById(ch_id);
							if(gm_Channel!=null){
								if(gm_Channel.getCh_state()==1){
									gm_DevChannel.setCh_id(gm_Channel);
									//更新通道编号 ‘GPRS设备编号’+ ‘-’+‘通道顺序’ 0702UP
									gm_Channel.setCh_no(M2M.getDev_id().getDev_no()+"-"+dch_order);
									gm_Channel_list.add(gm_Channel);
									//--end
								}else{
									return "{value:'通道已丢失'}";
								}									
							}else{
								return "{value:'通道已丢失'}";
							}		
							/** 数据库改成GPRS了 这里不需要啦，页面获取还留着，这里不更新就好了 反正没影响业务。
							Gm_Device gm_Device = this.gm_DeviceService.findById(dev_id);
							if(gm_Device!=null){
								if(gm_Device.getDev_state()==1){
									gm_DevChannel.setDev_id(gm_Device);

								}else{
									out.print("{value:'所属设备已丢失'}");
									return null;
								}
							}else{
								out.print("{value:'所属设备已丢失'}");
								return null;
							}
							*/
							try{
								gm_DevChannel.setDch_order(Integer.parseInt(dch_order));
								gm_DevChannel.setCh_procesStyle(Integer.parseInt(ch_procesStyle));
								gm_DevChannel.setCh_memoryPeriod(Integer.parseInt(ch_memoryPeriod));
							}catch(Exception ex){
								ex.printStackTrace();
								return "{value:'数据格式不正确'}";
							}
							gm_DevChannel_list.add(gm_DevChannel);
						}
						//--未配置的通道编号 更新为 ‘GPRS设备编号’+ ‘-’+‘x’ 0702
						//--未配置的通道不对外提供服务 0702
						for(int i=0;i<uc_ch_id_list.length;i++){
							String uc_ch_id = uc_ch_id_list[i];
							Gm_Channel gm_Channel = this.gm_ChannelDao.findById(uc_ch_id);
							Gm_Device gm_Device = gm_Channel.getDev_collectId();
							if(gm_Device != null){
								if(gm_Device.getDev_state()==1){
									if(gm_Device.getDev_no()!=null){
										gm_Channel.setCh_no(M2M.getDev_id().getDev_no()+"-x");
										gm_Channel.setCh_offerSer(0);
										uc_gm_Channel_list.add(gm_Channel);
									}else{
										return "{value:'所属设备编号未知'}";
									}
								}else{
									return "{value:'所属设备已丢失'}";
								}
							}else{
								return "{value:'所属设备已丢失'}";
							}
						}
						//--end
						List<Gm_DevChannel> gm_DevChannel_list_del = this.findAllEq("dev_addr", M2M.getNet_addr());
						for(Gm_DevChannel gm_DevChannel : gm_DevChannel_list_del){
							this.delete(gm_DevChannel);
						}
						for(Gm_DevChannel gm_DevChannel : gm_DevChannel_list){
							this.save(gm_DevChannel);
						}
						for(Gm_Channel gm_Channel : gm_Channel_list){
							this.gm_ChannelDao.update(gm_Channel);
						}
						for(Gm_Channel gm_Channel : uc_gm_Channel_list){
							this.gm_ChannelDao.update(gm_Channel);
						}
						return "{value:'操作成功'}";
					}else{
						return "{value:'通道配置信息不完整'}";
					}
				}else{
					return "{value:'没有指定GPRS设备在网络中的地址'}";
				}
			}else{
				return "{value:'没有指定GPRS设备在网络中的地址'}";
			}
		}else{
			return "{value:'没有指定GPRS设备在网络中的地址'}";
		}	
	}

	/**
	 * 通过设备Id查询设备下所有通道
	 * @param devId
	 * @return
	 */
	public List<Gm_DevChannel> findDevChannelByDevId(String devId) {
		Search search = new Search();
		search.addFilterEqual("dev_id.dev_id", devId);
		return this.gm_DevChannelDao.search(search);
	}

	public List<Object[]> findCh_IdByDev_Id(String dev_id_selectAll) {
		return this.gm_DevChannelDao.findCh_IdByDev_Id(dev_id_selectAll);
	}

	

}
