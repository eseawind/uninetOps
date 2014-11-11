package org.unism.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.unism.pro.domain.Pro_Fisheries;

/**
 * 养殖池 短信重发的MAP2
 * @author Wang_Yuliang
 *
 */
@Component
public class Fisheries_Message_Map2 {

	private final Map<String, Pro_Fisheries> fisheries_Message_Map2 = Collections.synchronizedMap(new HashMap<String, Pro_Fisheries>());//管理重发的消息2

	/**
	 * 放入一条养殖池信息到重发队列中
	 * @param key 养殖池ID
	 * @param message 养殖池信息
	 */
	public void putMessage(String key, Pro_Fisheries pro_Fisheries) {
		fisheries_Message_Map2.put(key, pro_Fisheries);
	}

	
	/**
	 * 移除一条养殖池信息
	 * @param key 养殖池ID
	 */
	public void removeMessage(String key) {
		fisheries_Message_Map2.remove(key);
	}
	
	/**
	 * 获取一条养殖池信息
	 * @param key 养殖池ID
	 * @return 养殖池信息
	 */
	public Pro_Fisheries getSendMessage(String key) {
		return fisheries_Message_Map2.get(key);
	}

	/**
	 * 获取所有需要重发消息的养殖池Map
	 * @return fisheries_Message_Map
	 */
	public Map<String, Pro_Fisheries> getMessageMap() {
		return fisheries_Message_Map2;
	}
}
