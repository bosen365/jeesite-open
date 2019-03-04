package com.jeesite.common.shiro.session;	
	
import com.jeesite.common.collect.MapUtils;	
import java.io.Serializable;	
import java.util.Collection;	
import java.util.Date;	
import java.util.Map;	
import org.apache.shiro.session.InvalidSessionException;	
import org.apache.shiro.session.mgt.ValidatingSession;	
	
public class StaticSession implements ValidatingSession, Serializable {	
   private static final long serialVersionUID = 1L;	
   private Map attributes = MapUtils.newHashMap();	
   private Date startTimestamp = new Date();	
   private Serializable id = "1";	
   public static final StaticSession INSTANCE = new StaticSession();	
	
   public void setTimeout(long maxIdleTimeInMillis) throws InvalidSessionException {	
   }	
	
   public Object removeAttribute(Object key) throws InvalidSessionException {	
      return this.attributes.remove(key);	
   }	
	
   public String getHost() {	
      return null;	
   }	
	
   public boolean isValid() {	
      return true;	
   }	
	
   public Serializable getId() {	
      return this.id;	
   }	
	
   public void setAttribute(Object key, Object value) throws InvalidSessionException {	
      this.attributes.put(key, value);	
   }	
	
   public void validate() throws InvalidSessionException {	
   }	
	
   public Object getAttribute(Object key) throws InvalidSessionException {	
      return this.attributes.get(key);	
   }	
	
   public long getTimeout() throws InvalidSessionException {	
      return Long.MAX_VALUE;	
   }	
	
   public Date getStartTimestamp() {	
      return this.startTimestamp;	
   }	
	
   public void touch() throws InvalidSessionException {	
   }	
	
   public Date getLastAccessTime() {	
      return new Date();	
   }	
	
   public Collection getAttributeKeys() throws InvalidSessionException {	
      return this.attributes.keySet();	
   }	
	
   public void stop() throws InvalidSessionException {	
   }	
}	
