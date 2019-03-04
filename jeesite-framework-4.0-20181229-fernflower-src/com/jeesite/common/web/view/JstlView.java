package com.jeesite.common.web.view;	
	
import com.jeesite.common.mybatis.mapper.query.QueryDataScope;	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import java.util.Locale;	
	
public class JstlView extends org.springframework.web.servlet.view.JstlView {	
   public boolean checkResource(Locale locale) throws Exception {	
      Object var2 = null;	
	
      try {	
         if (this.getApplicationContext().getResource(this.getUrl()).exists()) {	
            return true;	
         }	
	
         this.logger.debug((new StringBuilder()).insert(0, "View not exists [").append(this.getUrl()).append("], to access the default view. ").toString());	
      } catch (Exception var4) {	
      }	
	
      return false;	
   }	
}	
