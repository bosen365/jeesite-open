/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.session.mgt.SessionContext	
 *  org.apache.shiro.session.mgt.SessionFactory	
 *  org.apache.shiro.session.mgt.SimpleSession	
 */	
package com.jeesite.common.shiro.e;	
	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.SessionContext;	
import org.apache.shiro.session.mgt.SessionFactory;	
import org.apache.shiro.session.mgt.SimpleSession;	
	
public class E	
implements SessionFactory {	
    public Session createSession(SessionContext initData) {	
        String a2;	
        if (initData != null && (a2 = initData.getHost()) != null) {	
            return new SimpleSession(a2);	
        }	
        return new SimpleSession();	
    }	
}	
	
