package com.jeesite.common.web.j;	
	
import java.util.Locale;	
import org.hyperic.sigar.ptql.ProcessFinder;	
import org.hyperic.sigar.shell.ShellCommandInitException;	
import org.springframework.web.servlet.view.JstlView;	
	
public class H extends JstlView {	
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
