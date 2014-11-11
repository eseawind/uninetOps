package org.unism.phone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.phone.dao.TblReceiveDao;
import org.unism.phone.domain.TblReceive;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;

@Service
public class TblReceiveService extends BaseService<TblReceive, Integer> {
	@Autowired TblReceiveDao tblReceiveDao;
	
	@Override
	protected IBaseDao<TblReceive, Integer> getEntityDao() {
		return tblReceiveDao;
	}
	
	/**
	 * 查找未读短信
	 * @return
	 * @author Wang_Yuliang
	 */
	public List<TblReceive> findByFlag(){
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("flag", "0"));
		search.addFilter(filter);
		search.addSortAsc("dd");
		return this.search(search);
	}
}
