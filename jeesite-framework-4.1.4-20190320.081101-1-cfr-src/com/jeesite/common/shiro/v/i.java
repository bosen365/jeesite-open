/*	
 * Decompiled with CFR 0.140.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.session.Session	
 *  org.apache.shiro.session.mgt.SessionContext	
 *  org.apache.shiro.session.mgt.SessionFactory	
 *  org.apache.shiro.session.mgt.SimpleSession	
 */	
package com.jeesite.common.shiro.v;	
	
import org.apache.shiro.session.Session;	
import org.apache.shiro.session.mgt.SessionContext;	
import org.apache.shiro.session.mgt.SessionFactory;	
import org.apache.shiro.session.mgt.SimpleSession;	
	
public class i	
implements SessionFactory {	
    public Session createSession(SessionContext initData) {	
        String a;	
        if (initData != null && (a = initData.getHost()) != null) {	
            return new SimpleSession(a);	
        }	
        return new SimpleSession();	
    }	
}	
	
