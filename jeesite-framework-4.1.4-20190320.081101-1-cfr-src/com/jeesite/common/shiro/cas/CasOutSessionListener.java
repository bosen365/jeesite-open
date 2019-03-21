/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.cas;	
	
import com.jeesite.common.shiro.cas.CasOutHandler;	
import com.jeesite.common.utils.SpringUtils;	
import javax.servlet.http.HttpSession;	
import javax.servlet.http.HttpSessionEvent;	
import javax.servlet.http.HttpSessionListener;	
import org.jasig.cas.client.session.SessionMappingStorage;	
	
public final class CasOutSessionListener	
implements HttpSessionListener {	
    private CasOutHandler casOutHandler;	
	
    @Override	
    public void sessionDestroyed(HttpSessionEvent event) {	
        HttpSession a = event.getSession();	
        this.getSessionMappingStorage().removeBySessionById(a.getId());	
    }	
	
    public SessionMappingStorage getSessionMappingStorage() {	
        if (this.casOutHandler == null) {	
            this.casOutHandler = SpringUtils.getBean(CasOutHandler.class);	
        }	
        return this.casOutHandler.getSessionMappingStorage();	
    }	
	
    @Override	
    public void sessionCreated(HttpSessionEvent event) {	
    }	
}	
	
