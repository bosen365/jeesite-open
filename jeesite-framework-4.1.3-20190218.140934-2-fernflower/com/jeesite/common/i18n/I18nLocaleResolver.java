package com.jeesite.common.i18n;	
	
import com.jeesite.autoconfigure.sys.MsgAutoConfiguration;	
import com.jeesite.common.beetl.j.e;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.mybatis.e.F;	
import java.util.Locale;	
import javax.servlet.http.HttpServletRequest;	
import javax.servlet.http.HttpServletResponse;	
import org.hyperic.sigar.ProcCredName;	
import org.springframework.context.i18n.LocaleContext;	
	
public class I18nLocaleResolver extends e {	
   private static Boolean enabled;	
	
   public Locale resolveLocale(HttpServletRequest request) {	
      return super.resolveLocale(request);	
   }	
	
   protected Locale determineDefaultLocale(HttpServletRequest request) {	
      return super.determineDefaultLocale(request);	
   }	
	
   public void setLocaleContext(HttpServletRequest request, HttpServletResponse response, LocaleContext localeContext) {	
      super.setLocaleContext(request, response, localeContext);	
   }	
	
   public static boolean enabled() {	
      if (enabled == null) {	
         if (!Global.getPropertyToBoolean("lang.enabled", "true")) {	
            enabled = false;	
         } else if (!ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnI18n"))) {	
            enabled = false;	
         } else {	
            enabled = true;	
         }	
      }	
	
      return enabled;	
   }	
	
   public LocaleContext resolveLocaleContext(HttpServletRequest request) {	
      return super.resolveLocaleContext(request);	
   }	
}	
