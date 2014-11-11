package org.unism.gm.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unism.gm.dao.Gm_HistorydataDao;
import org.unism.gm.domain.Gm_Historydata;
import org.unism.gm.util.DateTool;
import org.wangzz.core.orm.IBaseDao;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.BaseService;
import org.wangzz.core.utils.DateUtil;

@Service
public class Gm_HistorydataService extends BaseService<Gm_Historydata, String>{
	@Autowired Gm_HistorydataDao gm_HistorydataDao;
	
	@Override
	protected IBaseDao<Gm_Historydata, String> getEntityDao() {
		return gm_HistorydataDao;
	}
	
	/**
	 * 曲线分析 指定采集通道id 起止时间 这里处理的比较简单了，但可以满足页面的需求，即起止时间差>1000*60*60*24*7 显示日均线，否则显示日均线
	 * @return json for Highcharts data 
	 * 			[[毫秒数,value],[毫秒数,value],[毫秒数,value],[毫秒数,value],[毫秒数,value][毫秒数,value],[毫秒数,value]]
	 * @author Wang_Yuliang
	 * 2011-05-19 11:15 弃用
	 */
	public String json_findHistoryByCh_idAndTime(String ch_id, String beginTime, String endTime){
		return this.gm_HistorydataDao.json_findHistoryByCh_idAndTime(ch_id, beginTime, endTime);
	}
	
	/**
	 * 场景监控曲线图 测试
	 * @return
	 */
	public String tt(){
		return this.gm_HistorydataDao.tt();
	}
	
	public Double findHistoryByCh_idAndTime(String ch_id, String beginTime, String endTime){		
		return this.gm_HistorydataDao.findHistoryByCh_idAndTime(ch_id, beginTime, endTime);
	}
	
	
//	public List<Gm_Historydata> findHistoryByChannelsAndTime(String ch_id,String beginTime, String endTime){		
//		System.out.println(">>>>>>>>>>>>>通道ID"+ch_id);
//		System.out.println(">>>>>>>>>>>>>开始时间"+beginTime);
//		System.out.println(">>>>>>>>>>>>>结束时间"+endTime);
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date begin = null, end = null;
//		try{
//			begin = df.parse(beginTime);
//			end = df.parse(endTime);
//		}catch(Exception ex){ex.printStackTrace();return null;}			
//		Search search = new Search();
//		Filter f1 = Filter.equal("ch_id.ch_id", ch_id);	
//		Filter f2 = Filter.greaterOrEqual("hida_record_time", begin);
//		Filter f3 = Filter.lessOrEqual("hida_record_time", end);
//		Filter filter = Filter.and(f1,Filter.and(f2,f3));
//		search.addFilter(filter);
//		search.addSortDesc("hida_record_time");
//		return this.search(search);
//	}
	/**
	 * 指定通道id 查找最近144条数据
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findLimit144ByCh_id(String ch_id){
		return this.gm_HistorydataDao.findLimit144ByCh_id(ch_id);		
	}

	/**
	 * 根据通道id的数组 and  开始时间  and  结束时间查询
	 * @param channelId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gm_Historydata> findHistoryByCh_idAndTime(String[] channelId,
			String beginTime, String endTime) {
		Search search = new Search();
		search.addFilterIn("ch_id.ch_id", channelId);
		search.addFilterGreaterOrEqual("hida_record_time", DateUtil.getDate(beginTime, "yyyy-MM-dd HH:mm:ss"));
		search.addFilterLessOrEqual("hida_record_time", DateUtil.getDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		search.addSortAsc("ch_id.ch_id");
		search.addSortAsc("hida_record_time");
		return gm_HistorydataDao.search(search);
	}

	@SuppressWarnings("unchecked")
	public String findByCh_idAndTime(String chId, String beginTime,String endTime) {
		Search search = new Search();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		search.addFilterEqual("ch_id.ch_id", chId);
		search.addFilterGreaterOrEqual("hida_record_time", DateUtil.getDate(beginTime, "yyyy-MM-dd HH:mm:ss"));
		search.addFilterLessOrEqual("hida_record_time", DateUtil.getDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		search.addSortAsc("hida_record_time");
		List<Gm_Historydata> gmHistorydatas = gm_HistorydataDao.search(search);
		String data = "[";
		String time = "";
		for (Gm_Historydata gmHistorydata : gmHistorydatas) {
			if(!time.equals("")){
				data += "["+gmHistorydata.getHida_record_time().getTime()+","+DateTool.dateToDate(time, sdf.format(gmHistorydata.getHida_record_time()))+"],";
			}
			time = sdf.format(gmHistorydata.getHida_record_time());
		}
		if(data.length() > 1){
			data = data.substring(0,data.length()-1);
		}
		data += "]";
		return data;
	}

	public String findByChIdAndTime(String chId, String beginTime,
			String endTime) {
		Search search = new Search();
		search.addFilterEqual("ch_id.ch_id", chId);
		search.addFilterGreaterOrEqual("hida_record_time", DateUtil.getDate(beginTime, "yyyy-MM-dd HH:mm:ss"));
		search.addFilterLessOrEqual("hida_record_time", DateUtil.getDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		search.addSortAsc("hida_record_time");
		List<Gm_Historydata> gmHistorydatas = gm_HistorydataDao.search(search);
		String data = "[";
		for (Gm_Historydata gmHistorydata : gmHistorydatas) {
			data += "["+gmHistorydata.getHida_record_time().getTime()+","+ gmHistorydata.getHida_reportTime().getTime() +"],";
		}
		if(data.length() > 1){
			data = data.substring(0,data.length()-1);
		}
		data += "]";
		return data;
	}

	public BigInteger findCountByCh_IdAndTime(String beginTime, String endTime,
			String ch_id) {
		return this.gm_HistorydataDao.findCountByCh_IdAndTime(beginTime,endTime,ch_id);
	}

	/**
	 * 根据通道id and  开始时间  and  结束时间查询
	 * @param channelId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Gm_Historydata> findHistoryByChIdAndTime(String chId,
			String beginTime, String endTime) {
		Search search = new Search();
		search.addFilterIn("ch_id.ch_id", chId);
		search.addFilterGreaterOrEqual("hida_record_time", DateUtil.getDate(beginTime, "yyyy-MM-dd HH:mm:ss"));
		search.addFilterLessOrEqual("hida_record_time", DateUtil.getDate(endTime, "yyyy-MM-dd HH:mm:ss"));
		search.addSortAsc("ch_id.ch_id");
		search.addSortAsc("hida_record_time");
		return gm_HistorydataDao.search(search);
	}
	
	public List<Gm_Historydata> findHistorydataByChId(String[] chIdArr,Date beginTime,Date endTime) {
		Search search = new Search();
		search.addFilterIn("ch_id.ch_id", chIdArr);
		search.addFilterGreaterOrEqual("hida_record_time", beginTime);
		search.addFilterLessOrEqual("hida_record_time", endTime);
		search.addSortAsc("hida_record_time");
		return gm_HistorydataDao.search(search);
	}

	public List<Gm_Historydata> findHistoryByChIdArrAndTime(String[] chIdArr,
			Date beginTime, Date endTime) {
		Search search = new Search();
		search.addFilterIn("ch_id.ch_id", chIdArr);
		search.addFilterGreaterOrEqual("hida_record_time", beginTime);
		search.addFilterLessOrEqual("hida_record_time", endTime);
		search.addSortAsc("hida_record_time");
		return gm_HistorydataDao.search(search);
	}

	/**
	 * 查询历史数据，根据通道Id开始时间结束时间，所需数据条数
	 * @param glList 
	 */
	public List<Gm_Historydata> findHistoryByChIdAndTimeStartingPosition(String chId, String beginTime, String endTime, int dataBegin, List<String> glList) {
		return gm_HistorydataDao.findHistoryByChIdAndTimeStartingPosition(chId,beginTime,endTime,dataBegin,glList);
	}

	public BigInteger findCountByCh_IdAndTime(String beginTime, String endTime,
			String[] chIds) {
		return gm_HistorydataDao.findCountByCh_IdAndTime(beginTime,endTime,chIds);
	}

	


}
