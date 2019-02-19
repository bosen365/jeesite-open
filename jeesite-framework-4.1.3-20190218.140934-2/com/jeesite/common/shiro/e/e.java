package com.jeesite.common.shiro.e;	
	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.SessionContext;	
import org.apache.shiro.session.mgt.SessionFactory;	
import org.apache.shiro.session.mgt.SimpleSession;	
	
public class E implements SessionFactory {	
   public Session createSession(SessionContext initData) {	
      String a;	
      return initData != null && (a = initData.getHost()) != null ? new SimpleSession(a) : new SimpleSession();	
   }	
}	
