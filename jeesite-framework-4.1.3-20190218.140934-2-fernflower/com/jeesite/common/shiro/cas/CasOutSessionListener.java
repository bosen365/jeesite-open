package com.jeesite.common.shiro.cas;	
	
import com.jeesite.common.utils.SpringUtils;	
import javax.servlet.http.HttpSession;	
import javax.servlet.http.HttpSessionEvent;	
import javax.servlet.http.HttpSessionListener;	
import org.jasig.cas.client.session.SessionMappingStorage;	
	
public final class CasOutSessionListener implements HttpSessionListener {	
   private CasOutHandler casOutHandler;	
	
   public SessionMappingStorage getSessionMappingStorage() {	
      if (this.casOutHandler == null) {	
         this.casOutHandler = (CasOutHandler)SpringUtils.getBean(CasOutHandler.class);	
      }	
	
      return this.casOutHandler.getSessionMappingStorage();	
   }	
	
   public void sessionDestroyed(HttpSessionEvent event) {	
      HttpSession a = event.getSession();	
      this.getSessionMappingStorage().removeBySessionById(a.getId());	
   }	
	
   public void sessionCreated(HttpSessionEvent event) {	
   }	
}	
