package org.unism.phone.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.phone.service.TblSendSMSService;
import org.unism.util.StringToDate;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.utils.DateUtil;

public class TblSendSMS
{
	public String findTblSendSMSPage(){
		Search search = new Search();
		Filter filter = new Filter();
		//System.out.println(queryName);
		//System.out.println(queryValue);
		//System.out.println(beginTime);
		//System.out.println(endTime);
		if(queryName != null && queryValue != null && !queryName.equals("") && !queryValue.equals("")){
			if(beginTime != null && endTime != null && !beginTime.equals("") && !beginTime.equals("")){
				filter = Filter.and(Filter.like(queryName, queryValue),Filter.greaterOrEqual("dd", DateUtil.getDate(beginTime + " 00:00:00", "yyyy-MM-dd hh:mm:ss")),Filter.lessOrEqual("dd", DateUtil.getDate(endTime + " 23:59:59", "yyyy-MM-dd hh:mm:ss")));
			}else {
				filter = Filter.like(queryName, queryValue);
			}
		}else {
			if(beginTime != null && endTime != null && !beginTime.equals("") && !beginTime.equals("")){
				filter = Filter.and(Filter.greaterOrEqual("dd", DateUtil.getDate(beginTime + " 00:00:00", "yyyy-MM-dd hh:mm:ss")),Filter.lessOrEqual("dd", DateUtil.getDate(endTime + " 23:59:59", "yyyy-MM-dd hh:mm:ss")));
			}
		}
		search.addFilter(filter);
		search.addSortDesc("dd");
		page = this.sendSMSService.search(page, search);
		return "tblSendSMS";
	}
	
	
	
	
//------------------------------------------------------------------
	
	@Autowired
	TblSendSMSService sendSMSService;
	
	private Page<TblSendSMS> page = new Page<TblSendSMS>();
	
	private String queryName;
	
	private String queryValue;
	
	private String beginTime;
	
	private String endTime;
	

	public String getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getQueryName()
	{
		return queryName;
	}

	public void setQueryName(String queryName)
	{
		this.queryName = queryName;
	}

	public String getQueryValue()
	{
		return queryValue;
	}

	public void setQueryValue(String queryValue)
	{
		this.queryValue = queryValue;
	}

	public Page<TblSendSMS> getPage()
	{
		return page;
	}

	public void setPage(Page<TblSendSMS> page)
	{
		this.page = page;
	}

	
}
