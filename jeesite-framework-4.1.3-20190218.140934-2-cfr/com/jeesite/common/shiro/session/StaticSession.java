/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  com.jeesite.common.collect.MapUtils	
 *  org.apache.shiro.session.InvalidSessionException	
 *  org.apache.shiro.session.mgt.ValidatingSession	
 */	
package com.jeesite.common.shiro.session;	
	
import com.jeesite.common.collect.MapUtils;	
import java.io.Serializable;	
import java.util.Collection;	
import java.util.Date;	
import java.util.HashMap;	
import java.util.Map;	
import java.util.Set;	
import org.apache.shiro.session.InvalidSessionException;	
import org.apache.shiro.session.mgt.ValidatingSession;	
	
public class StaticSession	
implements ValidatingSession,	
Serializable {	
    private Serializable id = "1";	
    public static final StaticSession INSTANCE = new StaticSession();	
    private Map<Object, Object> attributes;	
    private Date startTimestamp;	
    private static final long serialVersionUID = 1L;	
	
    public void stop() throws InvalidSessionException {	
    }	
	
    public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {	
    }	
	
    public Date getStartTimestamp() {	
        return this.startTimestamp;	
    }	
	
    public long getTimeout() throws InvalidSessionException {	
        return Long.MAX_VALUE;	
    }	
	
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {	
        return this.attributes.keySet();	
    }	
	
    public void touch() throws InvalidSessionException {	
    }	
	
    public Object removeAttribute(Object key) throws InvalidSessionException {	
        return this.attributes.remove(key);	
    }	
	
    public void setAttribute(Object key, Object value) throws InvalidSessionException {	
        this.attributes.put(key, value);	
    }	
	
    public String getHost() {	
        return null;	
    }	
	
    public Serializable getId() {	
        return this.id;	
    }	
	
    public boolean isValid() {	
        return true;	
    }	
	
    public StaticSession() {	
        StaticSession staticSession = this;	
        this.startTimestamp = new Date();	
        staticSession.attributes = MapUtils.newHashMap();	
    }	
	
    public Date getLastAccessTime() {	
        return new Date();	
    }	
	
    public Object getAttribute(Object key) throws InvalidSessionException {	
        return this.attributes.get(key);	
    }	
	
    public void validate() throws InvalidSessionException {	
    }	
}	
	
