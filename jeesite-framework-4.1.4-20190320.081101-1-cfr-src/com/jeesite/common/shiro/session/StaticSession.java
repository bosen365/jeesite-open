/*	
 * Decompiled with CFR 0.140.	
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
    private Map<Object, Object> attributes;	
    public static final StaticSession INSTANCE = new StaticSession();	
    private static final long serialVersionUID = 1L;	
    private Serializable id = "1";	
    private Date startTimestamp;	
	
    public void setAttribute(Object key, Object value) throws InvalidSessionException {	
        this.attributes.put(key, value);	
    }	
	
    public Object getAttribute(Object key) throws InvalidSessionException {	
        return this.attributes.get(key);	
    }	
	
    public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {	
    }	
	
    public String getHost() {	
        return null;	
    }	
	
    public void touch() throws InvalidSessionException {	
    }	
	
    public Object removeAttribute(Object key) throws InvalidSessionException {	
        return this.attributes.remove(key);	
    }	
	
    public long getTimeout() throws InvalidSessionException {	
        return Long.MAX_VALUE;	
    }	
	
    public Date getLastAccessTime() {	
        return new Date();	
    }	
	
    public Serializable getId() {	
        return this.id;	
    }	
	
    public void validate() throws InvalidSessionException {	
    }	
	
    public Date getStartTimestamp() {	
        return this.startTimestamp;	
    }	
	
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {	
        return this.attributes.keySet();	
    }	
	
    public boolean isValid() {	
        return true;	
    }	
	
    public void stop() throws InvalidSessionException {	
    }	
	
    public StaticSession() {	
        StaticSession staticSession = this;	
        this.startTimestamp = new Date();	
        staticSession.attributes = MapUtils.newHashMap();	
    }	
}	
	
