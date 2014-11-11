package org.unism.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.unism.phone.domain.TblSendSMS;

/**
 * 养殖池 短信重发的MAP
 * @author Wang_Yuliang
 *
 */
@Component
public class Fisheries_Message_Map {

	private final Map<String, TblSendSMS> fisheries_Message_Map = Collections.synchronizedMap(new HashMap<String, TblSendSMS>());//管理重发的消息
	
	/**
	 * 放入一条已发送消息到重发队列中
	 * @param key 养殖池ID
	 * @param message 消息实体
	 */
	public void putMessage(String key, TblSendSMS message) {
		fisheries_Message_Map.put(key, message);
	}

	
	/**
	 * 移除一条已发送消息
	 * @param key 养殖池ID
	 */
	public void removeMessage(String key) {
		fisheries_Message_Map.remove(key);
	}
	
	/**
	 * 获取一条发送消息
	 * @param key 养殖池ID
	 * @return SendMessage
	 */
	public TblSendSMS getSendMessage(String key) {
		return fisheries_Message_Map.get(key);
	}

	/**
	 * 获取所有需要重发的消息Map
	 * @return fisheries_Message_Map
	 */
	public Map<String, TblSendSMS> getMessageMap() {
		return fisheries_Message_Map;
	}	
}
