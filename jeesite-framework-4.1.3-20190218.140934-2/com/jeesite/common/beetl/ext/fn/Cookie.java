package com.jeesite.common.beetl.ext.fn;	
	
import com.jeesite.autoconfigure.core.DataSourceAutoConfiguration;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.mapper.MapperHelper;	
import com.jeesite.common.web.CookieUtils;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.beetl.core.Context;	
import org.beetl.core.Function;	
	
public class Cookie implements Function {	
   public Object call(Object[] paras, Context ctx) {	
      String a = ObjectUtils.toString(paras.length >= 1 ? paras[0] : null);	
      int a = ObjectUtils.toBoolean(paras.length >= 2 ? paras[1] : false);	
      String a = ObjectUtils.toString(paras.length >= 3 ? paras[2] : null);	
      HttpServletRequest a = (HttpServletRequest)ctx.getGlobal("request");	
      HttpServletResponse a = (HttpServletResponse)ctx.getGlobal("response");	
      String a;	
      return (a = CookieUtils.getCookie(a, a, a, a)) != null ? a : a;	
   }	
}	
