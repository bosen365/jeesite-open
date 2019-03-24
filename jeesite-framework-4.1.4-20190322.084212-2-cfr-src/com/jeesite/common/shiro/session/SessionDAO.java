/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.session;	
	
import java.util.Collection;	
import org.apache.shiro.session.Session;	
	
public interface SessionDAO	
extends org.apache.shiro.session.mgt.eis.SessionDAO {	
    public Collection<Session> getActiveSessions(boolean var1, boolean var2);	
	
    public Collection<Session> getActiveSessions(boolean var1, boolean var2, String var3, String var4, String var5);	
}	
	
