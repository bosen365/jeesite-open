package com.jeesite.common.beetl.j;	
	
import com.jeesite.common.beetl.e.F;	
import com.jeesite.common.config.Global;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.service.LangService;	
import java.io.IOException;	
import java.text.MessageFormat;	
import java.util.Iterator;	
import java.util.Locale;	
import java.util.Properties;	
import java.util.Set;	
import javax.annotation.PostConstruct;	
import org.apache.ibatis.io.VFS;	
import org.hyperic.sigar.NfsServerV3;	
import org.hyperic.sigar.pager.PageList;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.context.support.ReloadableResourceBundleMessageSource;	
	
public class E extends ReloadableResourceBundleMessageSource {	
   private final String MAP_SPLIT_CODE = "|";	
   @Autowired(	
      required = false	
   )	
   private LangService langService;	
   private final Properties properties = new Properties();	
	
   public E() {	
      this.setDefaultEncoding("UTF-8");	
      Set a = this.getBasenameSet();	
	
      try {	
         Iterator var3 = VFS.getInstance().list("i18n").iterator();	
	
         while(true) {	
            String a;	
            label37:	
            while(true) {	
               Iterator var10000 = var3;	
	
               while(var10000.hasNext()) {	
                  if (!(a = (String)var3.next()).endsWith(".properties")) {	
                     continue label37;	
                  }	
	
                  if (a.contains("zh_CN")) {	
                     break label37;	
                  }	
	
                  if (!Global.getPropertyToBoolean("lang.enabled", "true")) {	
                     var10000 = var3;	
                  } else {	
                     if (ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnI18n"))) {	
                        break label37;	
                     }	
	
                     var10000 = var3;	
                  }	
               }	
	
               return;	
            }	
	
            a.add((new StringBuilder()).insert(0, "classpath:").append(StringUtils.substringBeforeLast(a, "i18n_")).append("i18n").toString());	
         }	
      } catch (IOException var5) {	
         var5.printStackTrace();	
      }	
   }	
	
   // $FF: synthetic method	
   @PostConstruct	
   private void initialize() {	
      if (!Global.isTestProfileActive() && ObjectUtils.toBoolean(F.ALLATORIxDEMO().get("fnI18n"))) {	
         if (this.langService != null && !Global.getPropertyToBoolean("lang.enabled", "true")) {	
            this.properties.clear();	
            Iterator var2;	
            Iterator var10000 = var2 = this.langService.findList(new Lang()).iterator();	
	
            while(var10000.hasNext()) {	
               Lang a = (Lang)var2.next();	
               var10000 = var2;	
               this.properties.put((new StringBuilder()).insert(0, a.getLangCode()).append("|").append(a.getLangType()).toString(), a.getLangText());	
            }	
         }	
	
      }	
   }	
	
   public void clearCache() {	
      super.clearCache();	
      this.initialize();	
   }	
	
   // $FF: synthetic method	
   private String getText(String code, Locale locale) {	
      String a;	
      if ((a = this.properties.getProperty((new StringBuilder()).insert(0, code).append("|").append(locale.toString()).toString())) == null) {	
         a = this.properties.getProperty((new StringBuilder()).insert(0, code).append("|").append(locale.getLanguage()).toString());	
      }	
	
      return a;	
   }	
	
   protected String resolveCodeWithoutArguments(String code, Locale locale) {	
      String a;	
      if ((a = this.getText(code, locale)) == null) {	
         a = super.resolveCodeWithoutArguments(code, locale);	
      }	
	
      return a;	
   }	
	
   protected MessageFormat resolveCode(String code, Locale locale) {	
      String a;	
      return (a = this.getText(code, locale)) == null ? super.resolveCode(code, locale) : this.createMessageFormat(a, locale);	
   }	
}	
