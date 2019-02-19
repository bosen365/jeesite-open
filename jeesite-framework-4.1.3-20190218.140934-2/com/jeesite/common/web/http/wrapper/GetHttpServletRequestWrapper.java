package com.jeesite.common.web.http.wrapper;	
	
import javax.servlet.ServletRequest;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletRequestWrapper;	
import org.hyperic.sigar.shell.ShellCommandUsageException;	
	
public class GetHttpServletRequestWrapper extends HttpServletRequestWrapper {	
   public GetHttpServletRequestWrapper(HttpServletRequest request) {	
      super(request);	
   }	
	
   public String getMethod() {	
      return "GET";	
   }	
	
   public GetHttpServletRequestWrapper(ServletRequest request) {	
      super((HttpServletRequest)request);	
   }	
}	
