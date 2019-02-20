/*	
 * Decompiled with CFR 0.139.	
 * 	
 * Could not load the following classes:	
 *  org.apache.shiro.mgt.SecurityManager	
 *  org.apache.shiro.spring.web.ShiroFilterFactoryBean	
 *  org.apache.shiro.web.filter.mgt.FilterChainManager	
 *  org.apache.shiro.web.filter.mgt.FilterChainResolver	
 *  org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver	
 *  org.apache.shiro.web.mgt.WebSecurityManager	
 *  org.apache.shiro.web.servlet.AbstractShiroFilter	
 *  org.slf4j.Logger	
 *  org.slf4j.LoggerFactory	
 *  org.springframework.beans.factory.BeanInitializationException	
 */	
package com.jeesite.common.shiro.web;	
	
import com.jeesite.common.j2cache.cache.support.redis.ConfigureNotifyKeyspaceEventsAction;	
import com.jeesite.common.shiro.web.e;	
import org.apache.shiro.mgt.SecurityManager;	
import org.apache.shiro.web.filter.mgt.FilterChainManager;	
import org.apache.shiro.web.filter.mgt.FilterChainResolver;	
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;	
import org.apache.shiro.web.mgt.WebSecurityManager;	
import org.apache.shiro.web.servlet.AbstractShiroFilter;	
import org.hyperic.jni.ArchNotSupportedException;	
import org.slf4j.Logger;	
import org.slf4j.LoggerFactory;	
import org.springframework.beans.factory.BeanInitializationException;	
	
public class ShiroFilterFactoryBean	
extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {	
    private static final transient Logger log = LoggerFactory.getLogger(org.apache.shiro.spring.web.ShiroFilterFactoryBean.class);	
	
    public Object getObject() throws Exception {	
        return this;	
    }	
	
    protected AbstractShiroFilter createInstance() throws Exception {	
        log.debug("Creating Shiro Filter instance.");	
        SecurityManager a2 = this.getSecurityManager();	
        if (a2 == null) {	
            String a3 = "SecurityManager property must be set.";	
            throw new BeanInitializationException(a3);	
        }	
        if (!(a2 instanceof WebSecurityManager)) {	
            String a4 = "The security manager does not implement the WebSe\turityManager interface.";	
            throw new BeanInitializationException(a4);	
        }	
        FilterChainManager a5 = this.createFilterChainManager();	
        PathMatchingFilterChainResolver a6 = new PathMatchingFilterChainResolver();	
        a6.setFilterChainManager(a5);	
        return new e((WebSecurityManager)a2, (FilterChainResolver)a6);	
    }	
	
    public Class<?> getObjectType() {	
        return ShiroFilterFactoryBean.class;	
    }	
	
    public AbstractShiroFilter getInstance() throws Exception {	
        return (AbstractShiroFilter)super.getObject();	
    }	
}	
	
