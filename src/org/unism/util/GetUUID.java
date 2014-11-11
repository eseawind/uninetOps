package org.unism.util;

import java.util.UUID;

public class GetUUID {

	/**
	 * @param args
	 */

	public static String getUUID(){
        UUID uuid = UUID.randomUUID();      
        return uuid.toString();    		
	}
}
