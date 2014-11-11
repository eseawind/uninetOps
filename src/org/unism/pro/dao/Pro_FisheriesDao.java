package org.unism.pro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.op.domain.Op_Scene;
import org.unism.pro.domain.Pro_Fisheries;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Pro_FisheriesDao extends HibernateBaseDao<Pro_Fisheries, String> {
	
	/**
	 * 指定养殖池编号，手机号，查询养殖池信息 输入的手机号只要包含字段值即可匹配
	 * @return
	 * @author Wang_Yuliang
	 */
	public Pro_Fisheries findByFi_pondNoAndFi_phone(String fi_pondNo, String fi_phone){
		Pro_Fisheries pro_Fisheries = null;
		String sql = "select " +
				"fi_id," +
				"fi_aquaticType," +
				"fi_area," +
				"fi_batch," +
				"fi_cultureObject," +
				"fi_depth," +
				"fi_do," +
				"fi_pondName," +
				"fi_pondNo," +
				"fi_productionStage," +
				"fi_remainNum," +
				"fi_species," +
				"scene_id," +
				"fi_doyj," +
				"fi_phone," +
				"fi_state," +
				"dect_id," +
				"fi_userName" +
				" from pro_fisheries where fi_pondNo='"+fi_pondNo+"' and locate(fi_phone,'"+fi_phone+"')>0";		
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> lst = query.list();
		if(lst.size()>0){
			if(lst.get(0)!=null){
				Object[] row = lst.get(0);
				if(row[0]!=null){
					//这里还没写呢--------------
					pro_Fisheries = new Pro_Fisheries();
					pro_Fisheries.setFi_id(row[0]+"");
					if(row[1]!=null)pro_Fisheries.setFi_aquaticType(row[1]+"");
					if(row[2]!=null)pro_Fisheries.setFi_area(Double.parseDouble(row[2]+""));
					if(row[3]!=null)pro_Fisheries.setFi_batch(row[3]+"");
					if(row[4]!=null)pro_Fisheries.setFi_cultureObject(row[4]+"");
					if(row[5]!=null)pro_Fisheries.setFi_depth(Double.parseDouble(row[5]+""));
					if(row[6]!=null)pro_Fisheries.setFi_do(row[6]+"");
					if(row[7]!=null)pro_Fisheries.setFi_pondName(row[7]+"");
					if(row[8]!=null)pro_Fisheries.setFi_pondNo(row[8]+"");
					if(row[9]!=null)pro_Fisheries.setFi_productionStage(row[9]+"");
					if(row[10]!=null)pro_Fisheries.setFi_remainNum(Integer.parseInt(row[10]+""));
					if(row[11]!=null)pro_Fisheries.setFi_species(row[11]+"");
					if(row[12]!=null){
						Op_Scene op_Scene = new Op_Scene();
						op_Scene.setScene_id(row[12]+"");
						pro_Fisheries.setScene(op_Scene);
					}
					if(row[13]!=null)pro_Fisheries.setFi_doyj(row[13]+"");
					if(row[14]!=null)pro_Fisheries.setFi_phone(row[14]+"");
					if(row[15]!=null)pro_Fisheries.setFi_state(Integer.parseInt(row[15]+""));
					if(row[16]!=null){
						Gm_DevCtrl gm_DevCtrl = new Gm_DevCtrl();
						gm_DevCtrl.setDect_id(row[16]+"");
						pro_Fisheries.setDect_id(gm_DevCtrl);
					}
					if(row[17]!=null)pro_Fisheries.setFi_userName(row[17]+"");
				}
			}
		}
		return pro_Fisheries;
	}
	
//通过场景查
	public List<Pro_Fisheries> findPro_FisheriesByScene_id(List<String> scene_idList)
	{
		String hql = "FROM Pro_Fisheries WHERE scene_id in ('-1',";
		if(scene_idList.size() > 0 && scene_idList != null){
			for (String scene_id : scene_idList)
			{
				if(!scene_id.equals("") && scene_id != null){
					hql += "'"+scene_id+"',";
				}
				if(hql.length() > 0){
					hql = hql.substring(0,hql.length()-1);
				}
			}
			hql += ")";
		}
		Query query = getSession().createQuery(hql);
		
		return query.list();
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findSceneFishDataByUserId(String userId) {
		String sql = "SELECT op_scene.scene_id, op_scene.scene_latitude, op_scene.scene_longitude, op_scene.scene_no, op_scene.scene_gtype, Min(gm_latestdata.hida_corrValue)," +
		" pro_fisheries.fi_area, pro_fisheries.fi_pondName, pro_fisheries.fi_userName, pro_fisheries.fi_aquaticType, pro_fisheries.fi_cultureObject, " +
		" pro_fisheries.fi_putTime,gm_devctrlsts.dect_state,op_scene.scene_image " +
		" FROM op_scene Inner Join op_userinfo_scene ON op_scene.scene_id = op_userinfo_scene.scene_id Inner Join gm_channel ON op_scene.scene_id = gm_channel.scene_id" +
		" Inner Join gm_latestdata ON gm_channel.ch_id = gm_latestdata.ch_id Inner Join op_channeltype ON gm_channel.chtype_id = op_channeltype.chtype_id" +
		" Inner Join pro_fisheries ON op_scene.scene_id = pro_fisheries.scene_id " +
		" Inner Join gm_devctrl ON op_scene.scene_id = gm_devctrl.scene_id "+
		" Inner Join gm_devctrlsts ON gm_devctrl.dect_id = gm_devctrlsts.dect_id"+
		" where op_userinfo_scene.user_id = '"+userId+"' " + 
		" and op_scene.scene_latitude is not null and op_scene.scene_longitude is not null " +
		//" and op_scene.scene_latitude is not null and op_scene.scene_longitude is not null and gm_latestdata.hida_corrValue is not null " +
		" and op_channeltype.chtype_no = 'DO10-O' "+
		//" and gm_devctrl.dect_state='1' "+
		//" and gm_devctrl.dect_state='1' and gm_devctrl.ch_offerSer='1' "+
		" group by  op_scene.scene_id";
		return this.getSession().createSQLQuery(sql).list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findSceneWeatherVidioByUserId(String userId) {
		String sql = "SELECT op_scene.scene_id, op_scene.scene_latitude, op_scene.scene_longitude, op_scene.scene_no, op_scene.scene_gtype, op_scene.scene_name, op_scene.scene_videoUrl,op_scene.scene_image " +
					" FROM op_scene Inner Join op_userinfo_scene ON op_scene.scene_id = op_userinfo_scene.scene_id where " +
					" op_userinfo_scene.user_id = '"+userId+"' and op_scene.scene_latitude is not null and op_scene.scene_longitude is not null " +
					" and (op_scene.scene_gtype = 98 or op_scene.scene_gtype = 97)";
		return this.getSession().createSQLQuery(sql).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findWeatherDataBySceneId(String sceneId) {
		String sql = "SELECT gm_latestdata.hida_corrValue, gm_channel.ch_corrUnit, op_channeltype.chtype_no  FROM gm_latestdata " +
		" Inner Join gm_channel ON gm_latestdata.ch_id = gm_channel.ch_id  Inner Join op_channeltype ON gm_channel.chtype_id = op_channeltype.chtype_id" +
		" where op_channeltype.chtype_no in ( 'KQSD1201-T',  'KQSD1201-H',  'CMP6-P',  '1200-S',  '1200-D') and gm_channel.scene_id = '"+sceneId+"'";
		return this.getSession().createSQLQuery(sql).list();
	}
	
	public List<Object[]> findAllFisheriesMsg(String userId)
	{
		String sql = "SELECT " +
						"op_scene.scene_id," +
						"op_scene.scene_no," +
						"op_scene.scene_gtype," +
						"Min(gm_latestdata.hida_corrValue)," +
						"pro_fisheries.fi_area," +
						"pro_fisheries.fi_pondName," +
						"pro_fisheries.fi_userName," +
						"pro_fisheries.fi_aquaticType," +
						"pro_fisheries.fi_cultureObject," +
						"pro_fisheries.fi_putTime," +
						"gm_devctrlsts.dect_state " +
						"FROM op_scene " +
						"Inner Join op_userinfo_scene ON op_scene.scene_id = op_userinfo_scene.scene_id " +
						"Inner Join gm_channel ON op_scene.scene_id = gm_channel.scene_id " +
						"Inner Join gm_latestdata ON gm_channel.ch_id = gm_latestdata.ch_id " +
						"Inner Join op_channeltype ON gm_channel.chtype_id = op_channeltype.chtype_id " +
						"Inner Join pro_fisheries ON op_scene.scene_id = pro_fisheries.scene_id " +
						"Inner Join gm_devctrl ON op_scene.scene_id = gm_devctrl.scene_id " +
						"Inner Join gm_devctrlsts ON gm_devctrl.dect_id = gm_devctrlsts.dect_id " +
						"where op_userinfo_scene.user_id = '"+userId+"' and op_scene.scene_latitude is not null " +
						"and op_scene.scene_longitude is not null and gm_latestdata.hida_corrValue is not null " +
						"and gm_latestdata.hida_corrValue>0 and gm_latestdata.hida_corrValue<30 "+
						"and op_channeltype.chtype_no = 'DO10-O' and gm_devctrl.dect_state='1' " +
						"and gm_devctrl.ch_offerSer='1' group by op_scene.scene_id " +
						"order by Min(gm_latestdata.hida_corrValue) asc";
		
		SQLQuery query = getSession().createSQLQuery(sql);
		
		return query.list();
	}
	
}
