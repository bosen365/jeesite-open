package com.jeesite.common.i18n;	
	
import com.jeesite.common.beetl.j.E;	
import java.text.MessageFormat;	
import java.util.Locale;	
	
public class I18nMessageSource extends E {	
   public void clearCache() {	
      super.clearCache();	
   }	
	
   protected String resolveCodeWithoutArguments(String code, Locale locale) {	
      return super.resolveCodeWithoutArguments(code, locale);	
   }	
	
   protected MessageFormat resolveCode(String code, Locale locale) {	
      return super.resolveCode(code, locale);	
   }	
}	
