package org.unism.util;

import java.io.InputStream;
import java.util.Properties;

public class Env extends Properties {
	
	private static Env instance=null;
	
	private Env(){
		try{
			InputStream input=Env.class.getResourceAsStream("/application.properties");
			load(input);
		}catch(Exception ex){ex.printStackTrace();}
	}
	
	public synchronized static Env getInstance(){
		if(instance==null){
			instance=new Env();
		}
		return instance;
	}
}
