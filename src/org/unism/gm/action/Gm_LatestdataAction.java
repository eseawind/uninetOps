package org.unism.gm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_LatestdataService;
import org.unism.gm.util.DateTool;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.utils.DateUtil;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.gson.Gson;

public class Gm_LatestdataAction {
	
	/**
	 * //场景监控 0518 指定通道编号 曲线编号 返回曲线编号，值
	 * @return
	 */
	public String json_findHida_corrValueByCh_noByNo(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Search search = new Search();
			Filter filter = Filter.equal("ch_no", ch_no);
			search.addFilter(filter);
			List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.search(search);
			if(gm_Channel_list.size()>0){
				Gm_Channel gm_Channel = gm_Channel_list.get(0);
				Gm_Latestdata gm_Latestdata = this.gm_LatestdataService.findByCh_id(gm_Channel.getCh_id());
				if(gm_Latestdata!=null && gm_Latestdata.getHida_corrValue()!=null){
					//System.out.println(gm_Channel.getCh_no()+"-"+gm_Latestdata.getHida_corrValue());
					//System.out.println("--");
					Long time = null;
					if(gm_Latestdata.getHida_record_time()!=null){
						time = gm_Latestdata.getHida_record_time().getTime();
					}
					out.print("{no:"+this.no+",time:"+time+",value:"+gm_Latestdata.getHida_corrValue()+"}");
				}else{out.print("{no:'"+this.no+"',null}");}				
			}else{
				out.print("{no:'"+this.no+"',null}");
			}
		}catch(Exception ex){ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{no:'"+this.no+"',null}");
			}catch(Exception exx){exx.printStackTrace();}	
		}
		return null;
	}
	
	public String toLatestdata(){
		return "toLatestdata";
	}
	
	public String findLatestdataByScene(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			List<Gm_Channel> gmChannels = new ArrayList<Gm_Channel>();
			if("scene".equals(searchType)){
				gmChannels = gm_ChannelService.findByScene_id(id);
			}else if("device".equals(searchType)){
				gmChannels = gm_ChannelService.findByDev_collectId(id);
			}
			if(gmChannels != null&&gmChannels.size() > 0){
				List<Gm_Latestdata> gmLatestdatas = gm_LatestdataService.findByChannel(gmChannels);
				List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
				for (Gm_Latestdata gmLatestdata : gmLatestdatas) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("chNo", gmLatestdata.getCh_id().getCh_no());
					map.put("origValue", gmLatestdata.getHida_origValue()==null?"null":gmLatestdata.getHida_origValue().toString());
					map.put("corrValue", gmLatestdata.getHida_corrValue()==null?"null":gmLatestdata.getHida_corrValue().toString());
					map.put("corrUnit", gmLatestdata.getCh_id().getCh_corrUnit()==null?"":gmLatestdata.getCh_id().getCh_corrUnit().toString());
					map.put("recordTime", DateTool.getDateTime(gmLatestdata.getHida_record_time()));
					map.put("reportTime", DateTool.getDateTime(gmLatestdata.getHida_reportTime()));
					jsons.add(map);
				}
				String json = new Gson().toJson(jsons);
				out.print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

	
//分割线-----------------------------------------------------------------------------------------------------
	@Autowired Gm_LatestdataService gm_LatestdataService;
	@Autowired Gm_ChannelService gm_ChannelService;
	
	private String ch_no; //场景监控 0518 指定通道编号 曲线编号 返回曲线编号，值
	private String no; //场景监控 0518 指定通道编号 曲线编号 返回曲线编号，值
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private String searchType;
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}


	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCh_no() {
		return ch_no;
	}

	public void setCh_no(String ch_no) {
		this.ch_no = ch_no;
	}
	
}
