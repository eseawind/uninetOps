package org.unism.op.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.unism.op.domain.Op_Areas;
import org.wangzz.core.orm.hibernate.HibernateBaseDao;

@Repository
public class Op_AreasDao extends HibernateBaseDao<Op_Areas, String> {

	/**
	 * 查找省级区划 
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findSheng(){
		String sql = "select area_id,area_name,area_desc from op_areas where area_id like '__0000' order by area_id asc";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> lst = query.list();
		StringBuilder builder = new StringBuilder("[");
		for(Object[] array : lst){
			builder.append("{");
			if(array[0]!=null)builder.append("area_id:\"").append(array[0]).append("\",");
			if(array[0]!=null)builder.append("area_name:\"").append(array[1]).append("\",");
			if(array[0]!=null)builder.append("area_desc:\"").append(array[2]).append("\"");
			builder.append("},");
		}
		if(lst!=null && lst.size()>0){
			builder.deleteCharAt(builder.length()-1);
		}
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * 查询市级区划 据省级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findShiBySheng(String area_id){		
		String sql = "select area_id,area_name,area_desc from op_areas where area_id!='"+area_id+"' and area_id like '"+ area_id.substring(0,2) +"__00' order by area_id asc";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> lst = query.list();
		StringBuilder builder = new StringBuilder("[");
		for(Object[] array : lst){
			builder.append("{");
			if(array[0]!=null)builder.append("area_id:\"").append(array[0]).append("\",");
			if(array[0]!=null)builder.append("area_name:\"").append(array[1]).append("\",");
			if(array[0]!=null)builder.append("area_desc:\"").append(array[2]).append("\"");
			builder.append("},");
		}
		if(lst!=null && lst.size()>0){
			builder.deleteCharAt(builder.length()-1);
		}
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * 查询县级级区划 据省级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findXianBySheng(String area_id){		
		String sql = "select area_id,area_name,area_desc from op_areas where area_id!='"+area_id+"' and area_id like '"+ area_id.substring(0,2) +"____' order by area_id asc";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> lst = query.list();
		StringBuilder builder = new StringBuilder("[");
		for(Object[] array : lst){
			builder.append("{");
			if(array[0]!=null)builder.append("area_id:\"").append(array[0]).append("\",");
			if(array[0]!=null)builder.append("area_name:\"").append(array[1]).append("\",");
			if(array[0]!=null)builder.append("area_desc:\"").append(array[2]).append("\"");
			builder.append("},");
		}
		if(lst!=null && lst.size()>0){
			builder.deleteCharAt(builder.length()-1);
		}
		builder.append("]");
		return builder.toString();
	}
	
	/**
	 * 查询县级区划 据市级区划
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String findXianByShi(String area_id){		
		String sql = "select area_id,area_name,area_desc from op_areas where area_id!='"+area_id+"' and area_id like '"+ area_id.substring(0,4) +"__' order by area_id asc";
		SQLQuery query = getSession().createSQLQuery(sql);
		List<Object[]> lst = query.list();
		StringBuilder builder = new StringBuilder("[");
		for(Object[] array : lst){
			builder.append("{");
			if(array[0]!=null)builder.append("area_id:\"").append(array[0]).append("\",");
			if(array[0]!=null)builder.append("area_name:\"").append(array[1]).append("\",");
			if(array[0]!=null)builder.append("area_desc:\"").append(array[2]).append("\"");
			builder.append("},");
		}
		if(lst!=null && lst.size()>0){
			builder.deleteCharAt(builder.length()-1);
		}
		builder.append("]");
		return builder.toString();
	}
}
