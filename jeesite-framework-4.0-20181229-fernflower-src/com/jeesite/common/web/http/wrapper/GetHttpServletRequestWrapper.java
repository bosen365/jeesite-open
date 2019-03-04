package com.jeesite.common.web.http.wrapper;	
	
import com.jeesite.common.service.BaseService;	
import javax.servlet.ServletRequest;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletRequestWrapper;	
	
public class GetHttpServletRequestWrapper extends HttpServletRequestWrapper {	
   public String getMethod() {	
      return "GET";	
   }	
	
   public GetHttpServletRequestWrapper(HttpServletRequest request) {	
      super(request);	
   }	
	
   public GetHttpServletRequestWrapper(ServletRequest request) {	
      super((HttpServletRequest)request);	
   }	
}	
