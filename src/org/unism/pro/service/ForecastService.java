package org.unism.pro.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.pro.dao.Pro_forecastDao;
import org.unism.pro.domain.Pro_Fisheries;
import org.unism.pro.domain.Pro_forecast;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.service.BaseService;

@Service
public class ForecastService extends BaseService<Pro_forecast, String> {

	@Autowired Pro_forecastDao pro_forecastDao;
	
	@Override
	protected IBaseDao<Pro_forecast, String> getEntityDao() {
		return pro_forecastDao;
	}

	/**
	 * 查询出离当前时间最近的整点数据
	 * @param time
	 * @param count
	 * @return
	 */
	public List<Object[]> findHourDataByHistoryData(String time, int count) {
		return pro_forecastDao.findHourDataByHistoryData(time,count);
	}
}
