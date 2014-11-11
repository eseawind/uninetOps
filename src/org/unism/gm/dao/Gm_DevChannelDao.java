package org.unism.gm.dao;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_DevChannel;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;
@Repository
public class Gm_DevChannelDao extends HibernateBaseDao<Gm_DevChannel, String>{

	/**
	 * 根据设备地址查ch_id，ch_name。
	 * @param net_addr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findCh_idAndCh_nameByAddr(String net_addr)
	{
		String sql = "select gm_channel.ch_id,gm_channel.ch_name,op_scene.scene_name,op_channeltype.chtype_no,op_channeltype.chtype_displayName from gm_channel,gm_devchannel,op_scene,op_channeltype where gm_channel.ch_id=gm_devchannel.ch_id and gm_channel.ch_state=1 and gm_channel.chtype_id=op_channeltype.chtype_id and gm_devchannel.dev_addr='"+ net_addr +"' and gm_channel.scene_id=op_scene.scene_id and gm_devchannel.ch_procesStyle <> 0 order by op_channeltype.chtype_no";
		SQLQuery query = getSession().createSQLQuery(sql);
		return query.list();
	}

	public List<Object[]> findCh_IdByDev_Id(String dev_id_selectAll) {
		String sql="SELECT  se.scene_name,dc.dev_id,dc.dev_addr,dc.ch_id FROM gm_devchannel as dc, gm_channel as ch,op_scene se where  se.scene_id=ch.scene_id and ch.ch_id=dc.ch_id";		
	    if(dev_id_selectAll!=null || !"".equals(dev_id_selectAll)){
	    	sql+=" and dc.dev_id in ('"+dev_id_selectAll+"')";
	    }
	    sql+=" group by dc.dev_id";
		SQLQuery query=this.getSession().createSQLQuery(sql);
		return query.list();
	}
}
