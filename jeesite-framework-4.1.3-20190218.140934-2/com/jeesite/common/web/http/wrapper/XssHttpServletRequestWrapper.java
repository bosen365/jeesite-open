package com.jeesite.common.web.http.wrapper;	
	
import com.jeesite.common.codec.EncodeUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletRequestWrapper;	
	
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {	
   public String getQueryString() {	
      return super.getQueryString();	
   }	
	
   public String getParameter(String name) {	
      return EncodeUtils.xssFilter(super.getParameter(name));	
   }	
	
   public String getHeader(String name) {	
      return super.getHeader(name);	
   }	
	
   public XssHttpServletRequestWrapper(HttpServletRequest request) {	
      super(request);	
   }	
	
   public String[] getParameterValues(String name) {	
      String[] a;	
      if ((a = super.getParameterValues(name)) == null) {	
         return a;	
      } else {	
         int a;	
         String[] var10000 = new String[a = a.length];	
         boolean var10002 = true;	
         String[] a = var10000;	
	
         int a;	
         for(int var6 = a = 0; var6 < a; var6 = a) {	
            int var10001 = a;	
            String var7 = EncodeUtils.xssFilter(a[a]);	
            ++a;	
            a[var10001] = var7;	
         }	
	
         return a;	
      }	
   }	
}	
