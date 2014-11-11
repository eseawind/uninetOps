
	package org.unism.util;

	import java.io.InputStream;
	import java.util.Properties;

	public class ImageUrl extends Properties {
		
		private static ImageUrl instance=null;
		
		private ImageUrl(){
			try{
				InputStream input=ImageUrl.class.getResourceAsStream("/imageUrl.properties");
				load(input);
			}catch(Exception ex){ex.printStackTrace();}
		}
		
		public synchronized static ImageUrl getInstance(){
			if(instance==null){
				instance=new ImageUrl();
			}
			return instance;
		}
	}
