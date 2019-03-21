/*	
 * Decompiled with CFR 0.140.	
 */	
package com.jeesite.common.shiro.v;	
	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.SessionContext;	
import org.apache.shiro.session.mgt.SessionFactory;	
import org.apache.shiro.session.mgt.SimpleSession;	
	
public class i	
implements SessionFactory {	
    @Override	
    public Session createSession(SessionContext initData) {	
        String a;	
        if (initData != null && (a = initData.getHost()) != null) {	
            return new SimpleSession(a);	
        }	
        return new SimpleSession();	
    }	
}	
	
