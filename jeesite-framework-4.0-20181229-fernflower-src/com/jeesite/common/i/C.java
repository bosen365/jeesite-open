package com.jeesite.common.i;	
	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.j;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.service.BaseService;	
import java.util.Locale;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.springframework.context.i18n.LocaleContext;	
import org.springframework.context.i18n.SimpleLocaleContext;	
import org.springframework.web.servlet.i18n.CookieLocaleResolver;	
	
public class C extends CookieLocaleResolver {	
   public static final String ALLATORIxDEMO = (new StringBuilder()).insert(0, C.class.getName()).append(".LOCALE").toString();	
	
   public LocaleContext resolveLocaleContext(HttpServletRequest request) {	
      return (LocaleContext)(!ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n")) ? new SimpleLocaleContext(Locale.CHINA) : super.resolveLocaleContext(request));	
   }	
	
   public void setLocaleContext(HttpServletRequest request, HttpServletResponse response, LocaleContext localeContext) {	
      super.setLocaleContext(request, response, localeContext);	
      request.getSession().setAttribute(ALLATORIxDEMO, localeContext.getLocale());	
   }	
	
   protected Locale determineDefaultLocale(HttpServletRequest request) {	
      Locale a;	
      return (a = (Locale)request.getSession().getAttribute(ALLATORIxDEMO)) != null ? a : super.determineDefaultLocale(request);	
   }	
	
   public Locale resolveLocale(HttpServletRequest request) {	
      return !ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n")) ? Locale.CHINA : super.resolveLocale(request);	
   }	
	
   public C() {	
      this.setCookieName("jeesite.locale");	
   }	
}	
