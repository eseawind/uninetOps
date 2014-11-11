package org.unism.op.service;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.op.dao.Op_ChannelTypeDao;
import org.unism.op.domain.Op_ChannelType;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

@Service
public class Op_ChannelTypeService extends BaseService<Op_ChannelType, String> {
	
	@Autowired Op_ChannelTypeDao op_ChannelTypeDao;
	
	private final ConcurrentMap<String, Op_ChannelType> cache; 

	@Override
	protected IBaseDao<Op_ChannelType, String> getEntityDao() {
		return op_ChannelTypeDao;
	}
	
	public Op_ChannelTypeService() {//缓存(键:编号,值:通道应用类型), 50小时后过期
		cache = new MapMaker().expiration(50, TimeUnit.HOURS).makeComputingMap(new Function<String, Op_ChannelType>(){
			//@Override
			public Op_ChannelType apply(String code) {
				return findByChTypeNo(code);
			} 
			 
        });   
	}
	
	/**
	 * 根据编号缓存查询应用类型信息
	 */
	public Op_ChannelType findByChtype_no(String chtype_no){
		return this.cache.get(chtype_no);
	}
	
	/**
	 * 根据编号查询应用类型信息
	 * @param typeNo 应用类型编号
	 * @return 应用类型信息
	 */
	public Op_ChannelType findByChTypeNo(String typeNo) {
		return this.op_ChannelTypeDao.findUniqueByProperty("chtype_no", typeNo);
	}
	
}
