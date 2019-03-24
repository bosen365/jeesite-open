/*	
 * Decompiled with CFR 0.141.	
 */	
package com.jeesite.common.shiro.session;	
	
import com.jeesite.common.collect.MapUtils;	
import java.io.Serializable;	
import java.util.Collection;	
import java.util.Date;	
import java.util.Map;	
import java.util.Set;	
import org.apache.shiro.session.InvalidSessionException;	
import org.apache.shiro.session.mgt.ValidatingSession;	
	
public class StaticSession	
implements ValidatingSession,	
Serializable {	
    private static final long serialVersionUID = 1L;	
    private Map<Object, Object> attributes;	
    public static final StaticSession INSTANCE = new StaticSession();	
    private Date startTimestamp;	
    private Serializable id = "1";	
	
    @Override	
    public Object removeAttribute(Object key) throws InvalidSessionException {	
        return this.attributes.remove(key);	
    }	
	
    @Override	
    public Date getStartTimestamp() {	
        return this.startTimestamp;	
    }	
	
    @Override	
    public String getHost() {	
        return null;	
    }	
	
    @Override	
    public long getTimeout() throws InvalidSessionException {	
        return Long.MAX_VALUE;	
    }	
	
    @Override	
    public void touch() throws InvalidSessionException {	
    }	
	
    @Override	
    public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {	
    }	
	
    @Override	
    public Serializable getId() {	
        return this.id;	
    }	
	
    @Override	
    public boolean isValid() {	
        return true;	
    }	
	
    @Override	
    public Object getAttribute(Object key) throws InvalidSessionException {	
        return this.attributes.get(key);	
    }	
	
    public StaticSession() {	
        StaticSession staticSession = this;	
        this.startTimestamp = new Date();	
        staticSession.attributes = MapUtils.newHashMap();	
    }	
	
    @Override	
    public void stop() throws InvalidSessionException {	
    }	
	
    @Override	
    public Date getLastAccessTime() {	
        return new Date();	
    }	
	
    @Override	
    public Collection<Object> getAttributeKeys() throws InvalidSessionException {	
        return this.attributes.keySet();	
    }	
	
    @Override	
    public void validate() throws InvalidSessionException {	
    }	
	
    @Override	
    public void setAttribute(Object key, Object value) throws InvalidSessionException {	
        this.attributes.put(key, value);	
    }	
}	
	
