package com.jeesite.common.shiro.session;	
	
import java.util.Collection;	
	
public interface SessionDAO extends org.apache.shiro.session.mgt.eis.SessionDAO {	
   Collection getActiveSessions(boolean var1, boolean var2);	
	
   Collection getActiveSessions(boolean var1, boolean var2, String var3, String var4, String var5);	
}	
