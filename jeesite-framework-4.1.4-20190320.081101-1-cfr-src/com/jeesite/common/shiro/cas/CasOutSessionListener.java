/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  javax.servlet.http.HttpSession	
 *  javax.servlet.http.HttpSessionEvent	
 *  javax.servlet.http.HttpSessionListener	
 *  org.jasig.cas.client.session.SessionMappingStorage	
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
	
    public void sessionCreated(HttpSessionEvent event) {	
    }	
}	
	
