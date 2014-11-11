package org.unism.util;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthenticationInterceptor extends AbstractInterceptor  {

	private static final long serialVersionUID = 8414382202732689470L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		if (null == session.get("user")) {
			return Action.LOGIN;
		} else {
			return invocation.invoke();
		}
	}

}