package org.unism.util;

import javax.servlet.http.HttpServletRequest;

public class JsMessage {

	public static String alertRequestAttributeMessage(HttpServletRequest request,String attName) {
		 Object obj=request.getAttribute(attName);
		 if(obj!=null){
			 StringBuffer sb=new StringBuffer();
			 sb.append("<script language=\"javascript\">");
			 sb.append("alert('").append(obj.toString()).append("');");
			 sb.append("</script>");
			 return sb.toString();
		 }else{
			 return "";
		 }
	}
}
