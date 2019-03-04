package com.jeesite.common.i;	
	
import com.jeesite.common.config.Global;	
import com.jeesite.common.l.d.j;	
import com.jeesite.common.lang.ObjectUtils;	
import com.jeesite.common.lang.StringUtils;	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.modules.sys.entity.Lang;	
import com.jeesite.modules.sys.service.LangService;	
import com.jeesite.modules.sys.utils.ModuleUtils;	
import java.io.IOException;	
import java.text.MessageFormat;	
import java.util.Iterator;	
import java.util.Locale;	
import java.util.Properties;	
import java.util.Set;	
import javax.annotation.PostConstruct;	
import org.apache.ibatis.io.VFS;	
import org.springframework.beans.factory.annotation.Autowired;	
import org.springframework.context.support.ReloadableResourceBundleMessageSource;	
	
public class l extends ReloadableResourceBundleMessageSource {	
   @Autowired	
   private LangService d;	
   private final Properties c = new Properties();	
   private final String ALLATORIxDEMO = "|";	
	
   public l() {	
      this.setDefaultEncoding("UTF-8");	
      Set a = this.getBasenameSet();	
	
      try {	
         Iterator var3 = VFS.getInstance().list("i18n").iterator();	
	
         while(true) {	
            label32:	
            while(true) {	
               for(Iterator var10000 = var3; var10000.hasNext(); var10000 = var3) {	
                  String a;	
                  if (!(a = (String)var3.next()).endsWith(".properties")) {	
                     continue label32;	
                  }	
	
                  if (ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n")) || a.contains("zh_CN")) {	
                     a.add((new StringBuilder()).insert(0, "classpath:").append(StringUtils.substringBeforeLast(a, "i18n_")).append("i18n").toString());	
                     continue label32;	
                  }	
               }	
	
               return;	
            }	
         }	
      } catch (IOException var5) {	
         var5.printStackTrace();	
      }	
   }	
	
   // $FF: synthetic method	
   @PostConstruct	
   private void ALLATORIxDEMO() {	
      if (!Global.isTestProfileActive() && ObjectUtils.toBoolean(j.ALLATORIxDEMO().get("fnI18n"))) {	
         this.c.clear();	
         Iterator var2;	
         Iterator var10000 = var2 = this.d.findList(new Lang()).iterator();	
	
         while(var10000.hasNext()) {	
            Lang a = (Lang)var2.next();	
            var10000 = var2;	
            this.c.put((new StringBuilder()).insert(0, a.getLangCode()).append("|").append(a.getLangType()).toString(), a.getLangText());	
         }	
	
      }	
   }	
	
   protected MessageFormat resolveCode(String code, Locale locale) {	
      String a;	
      return (a = this.ALLATORIxDEMO(code, locale)) == null ? super.resolveCode(code, locale) : this.createMessageFormat(a, locale);	
   }	
	
   public void clearCache() {	
      super.clearCache();	
      this.ALLATORIxDEMO();	
   }	
	
   protected String resolveCodeWithoutArguments(String code, Locale locale) {	
      String a;	
      if ((a = this.ALLATORIxDEMO(code, locale)) == null) {	
         a = super.resolveCodeWithoutArguments(code, locale);	
      }	
	
      return a;	
   }	
	
   // $FF: synthetic method	
   private String ALLATORIxDEMO(String code, Locale locale) {	
      String a;	
      if ((a = this.c.getProperty((new StringBuilder()).insert(0, code).append("|").append(locale.toString()).toString())) == null) {	
         a = this.c.getProperty((new StringBuilder()).insert(0, code).append("|").append(locale.getLanguage()).toString());	
      }	
	
      return a;	
   }	
}	
